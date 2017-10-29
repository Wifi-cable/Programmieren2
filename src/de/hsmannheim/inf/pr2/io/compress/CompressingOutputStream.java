package de.hsmannheim.inf.pr2.io.compress;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CompressingOutputStream extends FilterOutputStream {

	public CompressingOutputStream(OutputStream out) throws FileNotFoundException,IOException {
		super(out);//braucht einen file als parameter. 
		
		
	}
	// aufgabe 1.2 
	/*diese klasse soll die "Laufängen-Komprimierung" realisieren, (so etwas wie einen zip file bauen)
	 * -sie erbt schon von filtereoutputstream, wie im übungsblatt beschrieben. 
	 * 
	 * -sie benutzt zwei arrays. eins liest sie nur, das andere füllt sie mit kompremierten zahlen.*/
	
	// felder,  daten
	
	private byte[] rawData;
	private byte[] zipData;
	//int fileLengh;
	int rawIdx;//index des uncompremierten arrays
	byte value;
	
	public CompressingOutputStream( byte [] input,FilterOutputStream out) throws IOException{
			super(out);
			//input=rawData;
			rawData=input;
	}
/* kompremiert den inhalt eines arrays  und macht einen stream daraus der in eine datei geschriebn werden kann*/	

	
	public void compressStream()throws IOException{
		int len=rawData.length;
		byte repeat;
		//byte next;
		//int zipIdx=0;
		rawIdx=0; 
		value=rawData[rawIdx];
		while(rawIdx<len){
			repeat=0;
			//next=1;
			if(rawData[rawIdx]==-1){		// sonderfall nächstes byte ist ein -1
				out.write(-1);
				out.write(-1);
				//zipData[zipIdx]=-1;
				//zipData[zipIdx+1]=-1;
				//zipIdx+=2;
				rawIdx++;
			}
			else{	// nächstes byte ist keine -1 wiederholt es sich?
				while(((rawIdx+repeat)<len)&& (value==rawData[rawIdx+repeat])){//wieviele wiederholungen der zahl gibts ?
					repeat++;	
				}
				if(repeat==0){	// keine wiederholung
					out.write(value);
				}
				else if(repeat>126){	// wiederholt so oft das es einen byte overflow gibt
					out.write(-1);
					out.write(value);
					out.write(126);
					out.write(-1);
					out.write(value);
					out.write(repeat-126);
				}
				else{	// ist keine -1 wiederholt sich wehniger als 126 mal
					out.write(-1);
					out.write(value);
					out.write(repeat);
				}
				rawIdx= rawIdx+repeat;
			}
			
		}
	}
/* nimmt ein byte array des objectes( zipData)  und füllt es mit kompremierten bytes. es vergrössert das array dynamisch 
 * schreibt als marker -125 -125 wenn die orginal daten zu ende sind.
 *  */	
	 void compressToArrayOld()throws IOException{
		 int len= rawData.length;
		 int idx=0;
		 int i=0;
		 int next;
		 byte val;
		 int counter;
		 zipData=new byte[len];
		 while (i<len){
			 counter=0;
			 next=i+1;
			 val=rawData[i];
			 	
			 if(i== (len-1)){
				 //wenn das unkompremierte array abgearbeitet ist wird noch -125 -125 rein geschrieben als marker
					if(zipData.length<(idx+3)){		//falls das array zu klein wird, mach es grösser
						zipData=resizeArray(zipData);	
					 }
					zipData[idx]=rawData[len-1];
					idx++;
					zipData[idx]= (-125);
					idx++;
					zipData[idx]= (-125);
				 
			 }
			 
				if(val==(-1)){		// sonderfall -1	
					if(zipData.length<(idx+2)){		//falls das array zu klein wird, mach es grösser
						zipData=resizeArray(zipData);
					}
					zipData[idx]= (-1);
					idx++;
					zipData[idx]=(-1);
					idx++;
					i++;
				}
				else{		//wert nicht -1
					//next=counter+i;	// 0+0 ist sinnloos
					//while(((i+next)<len)&&(val==rawData[i+next])){ falsche zahlen
//					while(((i+counter)<(len-1))&&(val==rawData[next])){ //Ignoriert den letzten
//						System.out.println("@"+ (i+counter) +" / "+ len +" Val="+ val +" vs. rawData["+ next +"]="+ rawData[next] + " for "+ counter +" times.");
//						counter++;
//						next=counter+i;
//					}
					
					while((next<len)&&(val==rawData[next])){ //Ignoriert den letzten
						System.out.println("@"+ (i+counter) +" / "+ len +" Val="+ val +" vs. rawData["+ next +"]="+ rawData[next] + " for "+ counter +" times.");
						counter++;
						next=counter+i;
					}
					

					
					if(counter==0){
						zipData[idx]=rawData[i]; 
						System.out.println("@"+ i +" -> "+ rawData[i]);
						idx++;
						i++;
					}
					
					else if (counter>126){	// gäbe es einen byte overflow?
						System.out.println("@"+ i +" counter overflow ("+ counter +")! write 126 + "+ (counter-126));

						if(zipData.length<(idx+7)){		//falls das array zu klein wird, mach es grösser
							zipData=resizeArray(zipData);
						}
						zipData[idx]= (-1);
						idx++;
						zipData[idx]=rawData[i];
						idx++;
						zipData[idx]=126;
						idx++;
						zipData[idx]=(-1);
						idx++;
						zipData[idx]=rawData[i];
						idx++;
						zipData[idx]= ((byte)(counter-126));
						idx++;
						i= i+counter;
					}
					else{	//standartfall
						if(zipData.length<(idx+4)){		
							zipData=resizeArray(zipData);
						}
						System.out.println("@"+ i +" => "+ rawData[i] +" for "+ counter +" times.");

						zipData[idx]= (-1);
						idx++;
						zipData[idx]=rawData[i];	//error here
						idx++;
						zipData[idx]=(byte)counter;
						idx++;
						i= i+counter;
					}
					
				}
		 }
//		 //wenn das unkompremierte array abgearbeitet ist wird noch -125 -125 rein geschrieben als marker
//			if(zipData.length<(idx+2)){		//falls das array zu klein wird, mach es grösser
//				zipData=resizeArray(zipData);	
//			 }
//			zipData[idx]= (-125);
//			idx++;
//			zipData[idx]= (-125);
	 }
	
	/*hilfsmethode das array mit den kompremierten zahlen zu voll ist wird ein doppelt so grosses gebaut.
	 * der inhalt des alten arrays wird ins neue kopiert.
	 * die neue refernz wird auf die variable des alten arrays gesetzt- (aliasierung) */
	public byte []resizeArray(byte[]small){
		
		int old=small.length;
		int bigger=(old*2);
		byte []temp=new byte[bigger];
		for(int i=0;i<old; i++){
			temp[i]=small[i];
			}
		return temp;
	}
	// einfacher getter für die ein array mit kompremierten daten
	public byte[] getZipData(){
		return zipData;
	}
	public static void main(String[] args)throws IOException{
		byte[]tester4= {2,2,2,2,3,3,5,5,5,5,5,4,5,5,5,5,5,42};
	
		OutputStream out=new FileOutputStream("example");
		CompressingOutputStream outPut;
		byte []result;
		outPut= new CompressingOutputStream(tester4, new FilterOutputStream( out));
		outPut.compressToArray();
		result= outPut.getZipData();
		for(int i=0; i<result.length; i++ ){
			System.out.print(" "+ result[i]+" ");
		}
		
		outPut.close();
		out.close();
	}
	 void compressToArray()throws IOException{
			// String output = "OUT=";
			int count=1;
			int index=1;
			int pos=0;
			byte searchFor = rawData[0];
		
			zipData=new byte[rawData.length];
			while (index < rawData.length){
				if (searchFor == (-1)){		// -1 Sonderbehandlung!
					if(zipData.length<(pos+2)){		
						zipData=resizeArray(zipData);	
					}
					zipData[pos]=(-1);
					pos++;
					zipData[pos]=(-1);
					pos++;
					searchFor = rawData[index];
				}
				else{	// check for compression
					if (searchFor == rawData[index]){// same!
						count++;
						//System.out.println("at "+ index +" Found same "+ searchFor +" for "+ count +" times.");
					}
					else{	// Not the same!!!
						if (count>1){	// can be compressed
							// Handle count > 126 because more than one overflow is possible
							while (count >= 126){
								//zipData = zipData + "-1 "+ searchFor +" "+ 126 +" ";
								if(zipData.length<(pos+3)){		
									zipData=resizeArray(zipData);	
								}
								zipData[pos]=(-1);
								pos++;
								zipData[pos]=searchFor;
								pos++;
								zipData[pos]=126;
								pos++;
								//System.out.println("at "+ index +" output "+ searchFor +" for "+ 126 +" times (overflow).");
								count = count - 126;
							}
							// Output rest (< 126), if has repetions are not accounted for
							if (count >0){
								//zipData = zipData + "-1 "+ searchFor +" "+ count +" ";
								if(zipData.length<(pos+3)){		
									zipData=resizeArray(zipData);	
								}
								zipData[pos]=(-1);
								pos++;
								zipData[pos]=searchFor;
								pos++;
								zipData[pos]=(byte)count;
								pos++;
								//System.out.println("at "+ index +" output "+ searchFor +" for "+ count +" times.");
							}
						}
						else{	// uncompressed!
							if(zipData.length<(pos+1)){		
								zipData=resizeArray(zipData);	
							}
							//zipData = zipData + searchFor +" ";
							zipData[pos]=searchFor;
							pos++;
							//System.out.println("at "+ index +" output "+ searchFor +" (uncompressed).");
						}// What ever happend, get a new number form input
						searchFor = rawData[index];// AND reset count!
						count = 1;
					}
				}// Try next....
				index++;
			}	//end of while loop.
			// At the end, maybe we have no output yet, so do it now!
			if (count>1){// is compressed!
				// Handle count > 126
				while (count >= 126){
					if(zipData.length<(pos+3)){		
						zipData=resizeArray(zipData);	
					}
					//zipData = zipData + "-1 "+ searchFor +" "+ 126 +" ";
					zipData[pos]=-1;
					pos++;
					zipData[pos]=searchFor;
					pos++;
					zipData[pos]=126;
					pos++;
					//System.out.println("at "+ index +" output "+ searchFor +" for "+ 126 +" times (overflow) (last).");
					count = count - 126;
				}// Output rest (< 126), if has some
				if (count >0){
					//zipData = zipData + "-1 "+ searchFor +" "+ count +" ";
					if(zipData.length<(pos+3)){		
						zipData=resizeArray(zipData);	
					}
					zipData[pos]=(-1);
					pos++;
					zipData[pos]=searchFor;
					pos++;
					zipData[pos]=(byte)count;
					pos++;
					//System.out.println("at "+ index +" output "+ searchFor +" for "+ count +" times (last).");
				}
			}
			else{// can not be compressed
				if(zipData.length<(pos+3)){		
					zipData=resizeArray(zipData);
				}
				//zipData = zipData + searchFor +" ";
				if(searchFor==-1){
					zipData[pos]=(-1);
					pos++;
					zipData[pos]=(-1);
					pos++;
				}
				else{
				zipData[pos]=searchFor;
				pos++;
				//System.out.println("at "+ index +" output "+ searchFor +" (last uncompressed).");
				}
			}
			if(zipData.length<(pos+1)){		
				zipData=resizeArray(zipData);	
			}
			zipData[pos]=-125;
			pos++;
			zipData[pos]=-125;
		
			//System.out.println("[done.]");
			//System.out.println(zipData);
		}
}
