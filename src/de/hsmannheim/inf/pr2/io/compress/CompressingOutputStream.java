package de.hsmannheim.inf.pr2.io.compress;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CompressingOutputStream extends FilterOutputStream {

	public CompressingOutputStream(OutputStream out) throws FileNotFoundException,IOException {
		super(out);//braucht einen file als parameter. 	
	}
	// aufgabe 1.2 
	// felder,  daten
	
	private byte[] rawData;
	private byte[] zipData;
	//int fileLengh;
	int rawIdx;//index des uncompremierten arrays
	byte value;
	FilterOutputStream out;
	InputStream in=new FileInputStream("example");
	public CompressingOutputStream( byte [] input,FilterOutputStream out) throws IOException{
			super(out);
			this.out=out;
			//input=rawData;
			rawData=input;
	}	

/* nimmt ein byte array des objectes( zipData)  und füllt es mit kompremierten bytes. es vergrössert das array dynamisch 
 * schreibt als marker -125 -125 wenn die orginal daten zu ende sind.
 *  */	
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
	public void setRawData(byte []array){
			rawData=array;
	}
	 void compressToArray()throws IOException{
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
					}
					else{	// Not the same!!!
						if (count>1){	// can be compressed
							// Handle count > 126 because more than one overflow is possible
							while (count >= 126){
								if(zipData.length<(pos+3)){		
									zipData=resizeArray(zipData);	
								}
								zipData[pos]=(-1);
								pos++;
								zipData[pos]=searchFor;
								pos++;
								zipData[pos]=126;
								pos++;
								count = count - 126;
							}
							// Output rest (< 126), if has repetions are not accounted for
							if (count >0){
								if(zipData.length<(pos+3)){		
									zipData=resizeArray(zipData);	
								}
								zipData[pos]=(-1);
								pos++;
								zipData[pos]=searchFor;
								pos++;
								zipData[pos]=(byte)count;
								pos++;
							}
						}
						else{	// can not  be compressedc
							if(zipData.length<(pos+1)){		
								zipData=resizeArray(zipData);	
							}
							zipData[pos]=searchFor;
							pos++;
						}// What ever happend, get a new number form input
						searchFor = rawData[index];// AND reset count!
						count = 1;
					}
				}// Try next number....
				index++;
			}	//end of while loop.
			// At the end, maybe we have no output yet, so do it now!
			if (count>1){// can be compressed
				// Handle count > 126
				while (count >= 126){
					if(zipData.length<(pos+3)){		
						zipData=resizeArray(zipData);	
					}
					zipData[pos]=-1;
					pos++;
					zipData[pos]=searchFor;
					pos++;
					zipData[pos]=126;
					pos++;
					count = count - 126;
				}
				if (count >0){

					if(zipData.length<(pos+3)){		
						zipData=resizeArray(zipData);	
					}
					zipData[pos]=(-1);
					pos++;
					zipData[pos]=searchFor;
					pos++;
					zipData[pos]=(byte)count;
					pos++;
				}
			}
			else{// can not be compressed
				if(zipData.length<(pos+3)){		
					zipData=resizeArray(zipData);
				}
				if(searchFor==-1){
					zipData[pos]=(-1);
					pos++;
					zipData[pos]=(-1);
					pos++;
				}
				else{
				zipData[pos]=searchFor;
				pos++;
				}
			}
			if(zipData.length<(pos+1)){		
				zipData=resizeArray(zipData);	
			}
			zipData[pos]=-125;
			pos++;
			zipData[pos]=-125;
		}
		public static void main(String[] args)throws IOException{
		byte[]tester4= {2,2,2,2,3,3,5,5,5,5,5,4,5,5,5,5,5,42};
	
		OutputStream out=new FileOutputStream("example");
		CompressingOutputStream outPut;
		byte []result;
		outPut= new CompressingOutputStream(tester4, new FilterOutputStream( out));
		CompressingOutputStream output2=new CompressingOutputStream(new FileOutputStream("random"));
		String rand= "samplefile";
		RandomFiller raFill = new RandomFiller(rand);
		File rand1=raFill.writeRandomFile(50);
		byte[]test=output2.readFromFile(rand1);		//baut aus einem file ein byte array
		for (int i =0; i<test.length; i++){
			System.out.print(test[i]+", ");
		}
	
		output2.setRawData(test);
		output2.compressToArray();
		byte[]test2=output2.getZipData();
		System.out.println("");
		System.out.println("das kompremierte array");
		for (int i =0; i<test2.length; i++){
			System.out.print(test2[i]+", ");
		}
		output2.flush();
		output2.close();
		outPut.close();
		out.close();
		raFill.close();
	}
	 
	 //schreibt das "zipData" also das kompremierte array eines compressingoutputstream objectes in eine datei
	 protected void writeToFile(String fileName) throws IOException{
		 File zip = new File("/"+fileName) ;
			boolean hasFile = zip.createNewFile();
			String absolut= zip.getAbsolutePath();
			OutputStream oput= new FileOutputStream(absolut);
		 for(int i=0; i<zipData.length; i++){
			 out.write(zipData[i]);
		 }	 
	 }
	 /* baut aus einem file ein byte array. */
	 protected byte[] readFromFile(File inFile)throws IOException{

			    InputStream fis = new FileInputStream(inFile);
			   
			    int len= (int)inFile.length();
			    byte[] daten = new byte[len];
			    
			    int bytesRead =fis.read(daten);

			    while ((bytesRead) > -1) {
			     bytesRead =fis.read(daten);
			    	
			     
			    }
			    fis.close();
			    return daten;
	 }
}
