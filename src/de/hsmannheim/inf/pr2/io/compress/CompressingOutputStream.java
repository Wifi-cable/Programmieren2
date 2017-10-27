package de.hsmannheim.inf.pr2.io.compress;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CompressingOutputStream extends FileOutputStream {

	public CompressingOutputStream(File arg0) throws FileNotFoundException {	//braucht einen file als parameter. 
		super(arg0);
		//constructor stub
	}
	// aufgabe 1.2 
	/*diese klasse soll die "Laufängen-Komprimierung" realisieren, (so etwas wie einen zip file bauen)
	 * -sie erbt schon von fileoutputstream, wie im übungsblatt beschrieben. 
	 * 
	 * -sie benutzt zwei arrays. eins liest sie nur, das andere füllt sie mit kompremierten zahlen.*/
	
	// felder,  daten
	private byte[] rawData;
	private byte[] zipData;
	int fileLengh;
	int rawIdx;//index des uncompremierten arrays
	byte value;
	
	public CompressingOutputStream( byte [] input,String filePath) throws FileNotFoundException{
		super(filePath);
	}
	
	/*hilfsmethode das array mit den kompremierten zahlen zu voll ist wird ein doppelt so grosses gebaut.
	 * der inhalt des alten arrays wird ins neue kopiert.
	 * die neue refernz wird auf die variable des alten arrays gesetzt- (aliasierung) */
	public void resizeArray(){
		int old=zipData.length;
		int bigger=old*2;
		byte []temp=new byte[bigger];
		for(int i=0;i<old; i++){
			temp[i]=zipData[i];
			}
		zipData= temp;
		temp=null; // referenz löschen	
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
					resizeArray();
				}
				zipData[zipIdx]=-1;
				zipData[zipIdx+1]=-1;
				zipIdx+=2;
			}
			else{				// nächstes byte ist keine -1
				while(((rawIdx+next)<fileLengh)&& (value==rawData[next])){
					repeat++;
					// zip idx++???
					
				}
				// wiederholt es sich?
				
			}
			
		}
		
	}
	
}
