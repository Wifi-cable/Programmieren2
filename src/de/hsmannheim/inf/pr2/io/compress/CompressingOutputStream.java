package de.hsmannheim.inf.pr2.io.compress;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CompressingOutputStream extends FilterOutputStream {

	public CompressingOutputStream(OutputStream out) throws FileNotFoundException,IOException {
		super(out);//braucht einen file als parameter. 
		
		
	}
	// aufgabe 1.2 
	/*diese klasse soll die "Laufängen-Komprimierung" realisieren, (so etwas wie einen zip file bauen)
	 * -sie erbt schon von fileoutputstream, wie im übungsblatt beschrieben. 
	 * 
	 * -sie benutzt zwei arrays. eins liest sie nur, das andere füllt sie mit kompremierten zahlen.*/
	
	// felder,  daten
	File inputFile;
	private byte[] rawData;
	private byte[] zipData;
	int fileLengh;
	int rawIdx;//index des uncompremierten arrays
	byte value;
	
	public CompressingOutputStream( byte [] input,FilterOutputStream out) throws IOException{
			super(out);
			input=rawData;
		
	}
	

	
	public void compressArray(){
		byte repeat;
		byte next;
		int zipIdx=0;
		rawIdx=0; 
		value=rawData[rawIdx];
		while(rawIdx<fileLengh){
			repeat=1;
			next=1;
			if(rawData[rawIdx]==-1){		// sonderfall nächstes byte ist ein -1
				if(zipIdx>=zipData.length-1){
					resizeArray(rawData);
				}
				zipData[zipIdx]=-1;
				zipData[zipIdx+1]=-1;
				zipIdx+=2;
			}
			else{				// nächstes byte ist keine -1
				while(((rawIdx+next)<fileLengh)&& (value==rawData[next])){//wieviele wiederholungen der zahl gibts ?
					repeat++;	
				}
				// wiederholt es sich?
				
			}
			
		}
		
	}
	/*hilfsmethode das array mit den kompremierten zahlen zu voll ist wird ein doppelt so grosses gebaut.
	 * der inhalt des alten arrays wird ins neue kopiert.
	 * die neue refernz wird auf die variable des alten arrays gesetzt- (aliasierung) */
	public void resizeArray(byte[]small){
		
		int old=small.length;
		int bigger=old*2;
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
	//nimmt einen file und packt ihn in ein byte array eventuell sinvoller in aufgabe 1.3 zu packen
//	protected byte[]fileToArray(File inFile){
//		//int len= (int) inFile.length();
//		int len=10, idx=0;
//		byte[] arrayFromFile=new byte[len];
//		try{
//			InputStream in = new BufferedInputStream(new FileInputStream(inFile));
//			int counter=  in.read(arrayFromFile);
//			while(counter>0){
//				if(idx<len){
//				byte b= (byte)counter;
//				arrayFromFile[idx]=b;
//				idx++;
//				}
//				else{// geht net mit globalen arrays ohen parameter, oder? 
//					resizeArray(arrayFromFile);
//				}
//				in.close();
//			}
//			
//		}
//		catch(IOException ioex){}
//		
//		return arrayFromFile;
//	}

}
