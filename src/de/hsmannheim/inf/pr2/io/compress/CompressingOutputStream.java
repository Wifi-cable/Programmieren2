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
	 void compressToArray()throws IOException{
		 int len= rawData.length;
		 int idx=0;
		 int i=0;
		 int next;
		 byte val;
		 int counter;
		 zipData=new byte[len];
		 while (i<len){
			 counter=0;
			 next=1;
			 val=rawData[i];
			
				if(val==-1){		// sonderfall -1	
					if(zipData.length<(idx+2)){		//falls das array zu klein wird, mach es grösser
						resizeArray(zipData);
					}
					zipData[idx]= (-1);
					idx++;
					zipData[idx]=(-1);
					idx++;
					i++;
				}
				else{		//wert nicht -1
					next=counter+i;
					while(((i+counter)<len)&&(val==rawData[next])){	//wiederholt sich der wert?
					
					counter++;
					next=counter+i;
					}
					if(counter==0){
						zipData[idx]=rawData[i]; 
						idx++;
						i++;
					}
					
					else if (counter>126){	// gäbe es einen byte overflow? 
						if(zipData.length<(idx+7)){		//falls das array zu klein wird, mach es grösser
							resizeArray(zipData);
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
						if(zipData.length<(idx+4)){		//falls das array zu klein wird, mach es grösser
							resizeArray(zipData);
						}
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
		 //wenn das unkompremierte array abgearbeitet ist wird noch -125 -125 rein geschrieben als marker
			if(zipData.length<(idx+2)){		//falls das array zu klein wird, mach es grösser
				resizeArray(zipData);	
			 }
			zipData[idx]= (-125);
			idx++;
			zipData[idx]= (-125);
	 }
	
	/*hilfsmethode das array mit den kompremierten zahlen zu voll ist wird ein doppelt so grosses gebaut.
	 * der inhalt des alten arrays wird ins neue kopiert.
	 * die neue refernz wird auf die variable des alten arrays gesetzt- (aliasierung) */
	public void resizeArray(byte[]small){
		
		int old=small.length;
		int bigger=(old*2);
		byte []temp=new byte[bigger];
		for(int i=0;i<old; i++){
			temp[i]=small[i];
			}
		small= temp;
		temp=null; // referenz löschen	
	}
	// einfacher getter für die ein array mit kompremierten daten
	public byte[] getZipData(){
		return zipData;
	}
	public static void main(String[] args)throws IOException{
		byte[]tester4= {2,2,2,2,3,3,5,5,5,5,5,5,5,5,5,5};
	
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
	
}
