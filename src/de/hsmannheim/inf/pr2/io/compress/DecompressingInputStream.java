package de.hsmannheim.inf.pr2.io.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

<<<<<<< HEAD
/*Ein ArrayIndexOutOfBoundException wird geworfen, liegt am Z�hler, da dieser �ber die L�nge des Arrays hinausz�hlt.
 *Folgender Fall, wei� ich nicht wie ich diesen betrachten soll, wie z.B. am Ende des Arrays die letzte Zahl, sollte diese
 *eine einzelne Zahl sein. 
 *Der Anzahl Z�hler in der Methode read gibt nicht f�r alle Kombinationen eines Arrays die richtige Ausgabe, hier fehlt
 *noch ein einheitliches Hochz�hlen, was f�r alle Kombinationen gilt. */
||||||| merged common ancestors
/*Ein ArrayIndexOutOfBoundException wird geworfen, liegt am Z�hler, da dieser �ber die L�nge des Arrays hinausz�hlt.
 *Folgender Fall, wei� ich nicht wie ich diesen betrachten soll, wie z.B. am Ende des Arrays die letzte Zahl, sollte diese
 *eine einzelne Zahl sein. 
 *Der Anzahl Z�hler in der Methode read gibt nicht f�r alle Kombinationen eines Arrays die richtige Ausgabe, hier fehlt
 *noch ein einheitliches Hochz�hlen, was f�r alle Kombinationen gilt. */
=======
/*Eine Sonderfall fehlt und die ArrayOutOfBoundException verursacht durch den Z�hler ist noch nicht behoben*/
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721

public class DecompressingInputStream extends InputStream {
	/*aufgabe 1.3 
	 * diese Klasse soll eine Dekomprimierung der Daten durchf�hren,
	 * die vorher mit einem CompressingOutputStream komprimiert wurden*/
	
	private byte [] oldData;
	private byte [] newData;
	int index;
	private int x = 0;
	private int pos = 0;
	private int zaehler = 1;

	public DecompressingInputStream(byte [] b) {
		oldData = b;
		
	}

	@Override
	public int read() throws IOException {
		// implementierung der abstrakten methoder der oberklasse. 
<<<<<<< HEAD
		//ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Z�hler z�hlt �ber die 7 hoch
		int pos = 0;
		int zaeler= 1;
		int anzahl = 0;
||||||| merged common ancestors
		//ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Z�hler z�hlt �ber die 7 hoch
		int pos = 0;
		int z�hler = 1;
		int anzahl = 0;
=======
		//ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Z�hler z�hlt �ber die 7 hoch
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
		index = 0;
				
		while (index < oldData.length) {
<<<<<<< HEAD
			//Betrachtet dem Fall -1 -1
			if((oldData[pos] == -1) && (oldData[zaeler] == -1)){
||||||| merged common ancestors
			//Betrachtet dem Fall -1 -1
			if(oldData[pos] == -1 && oldData[z�hler] == -1){
=======
			//Betrachtet den Fall -1 -1
			if(oldData[pos] == -1 && oldData[zaehler] == -1){
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				
				decompressing(oldData[pos]);
<<<<<<< HEAD
				zaeler+= 2;
||||||| merged common ancestors
				z�hler += 2;
=======
				
				zaehler += 2;
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				pos += 2;
				index += 2;
<<<<<<< HEAD
				// Z�hlt die Anzahl zur richtigen Stelle hoch
				if (oldData[pos] == -1 && oldData[zaeler] != -1) {
					//anzahl += 3;
					index += 3;
				
				}
				
||||||| merged common ancestors
				// Z�hlt die Anzahl zur richtigen Stelle hoch
				if (oldData[pos] == -1 && oldData[z�hler] != -1) {
					//anzahl += 3;
					index += 3;
				
				}
				
=======

>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
			}
<<<<<<< HEAD
			//Betrachtet die -1 5 8 z.B.
			else if ((oldData[pos] == -1) && (oldData[zaeler] != -1)) {
				decompressing(oldData[zaeler]);
				anzahl += 2;
				berechneAnzahl(oldData[zaeler], oldData[anzahl]);
				zaeler+= 3;
||||||| merged common ancestors
			//Betrachtet die -1 5 8 z.B.
			else if (oldData[pos] == -1 && oldData[z�hler] != -1) {
				decompressing(oldData[z�hler]);
				anzahl += 2;
				berechneAnzahl(oldData[z�hler], oldData[anzahl]);
				z�hler += 3;
=======
			//Betrachtet z.B -1 5 8
			else if (oldData[pos] == -1 && oldData[zaehler] != -1) {

				berechneAnzahl(oldData[zaehler], oldData[pos+2]);

				zaehler += 3;
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				pos += 3;
				index += 2;
				
			}
			// Betrachtet einzelne Bytes
<<<<<<< HEAD
			else if (oldData[pos] != -1 && oldData[zaeler] == -1) {
||||||| merged common ancestors
			else if (oldData[pos] != -1 && oldData[z�hler] == -1) {
=======
			else if (oldData[pos] != -1 && oldData[zaehler] == -1) {
				
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				decompressing(oldData[pos]);
				
				pos++;
<<<<<<< HEAD
				zaeler++;
				anzahl += 3;
||||||| merged common ancestors
				z�hler++;
				anzahl += 3;
=======
				zaehler++;
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				index++;
			}
			//Betrachtet einzelne Bytes die hintereinander laufen
<<<<<<< HEAD
			else if (oldData[pos] != -1 && oldData[zaeler] != -1) {
||||||| merged common ancestors
			else if (oldData[pos] != -1 && oldData[z�hler] != -1) {
=======
			else if (oldData[pos] != -1 && oldData[zaehler] != -1) {
				
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				decompressing(oldData[pos]);
<<<<<<< HEAD
				decompressing(oldData[zaeler]);
||||||| merged common ancestors
				decompressing(oldData[z�hler]);
=======
				decompressing(oldData[zaehler]);
				
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				pos += 2;
<<<<<<< HEAD
				zaeler += 2;
				anzahl++;
||||||| merged common ancestors
				z�hler += 2;
				anzahl++;
=======
				zaehler += 2;
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
				index += 2;
			}

<<<<<<< HEAD
			//z�hler -= 1;
			
||||||| merged common ancestors
			//z�hler -= 1;
			
=======
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
		}
		
		return 0;
	}
	
<<<<<<< HEAD
	// Soll aus -1 3 2 3 3 machen, indem er die Schleife d�rchl�uft und immer wieder decompressing aufruft mit der Zahl
||||||| merged common ancestors
	// Soll aus -1 3 2 3 3 machen, indem er die Schleife d�rchl�uft und immer wieder decompressing aufruft mit der Zahl
=======
	// Soll aus -1 3 2  / 3 3 machen, indem er die Schleife durchl�uft und immer wieder decompressing aufruft mit der Zahl
>>>>>>> 7fd0a68dfa4a31bf44a2f6697d6e3e0fcffaf721
	public void berechneAnzahl(int zahl, int menge) {

		for(int i = 0; i < menge; i++) {
			decompressing(zahl);
		}
		
	}
	// Nimmt die Zahl und speichert sie in ein neues Array ab
	public void decompressing(int stelle) {
		
		newData = new byte[50];
		int wert = stelle;

		if (x < newData.length) {
			newData[x] = (byte) wert;
			System.out.print(newData[x] + " ");
			x++;
		}
	}
	
	
	public static void main(String [] args) throws IOException {
		
		//byte test [] = {3, 5, 3, -1, -1, 8, -1, 2, 8};
		//byte test [] = {-1, 5, 10, -1, -1, -1, -1, 3, 5};
		byte test [] = {-1, 3, 4};
		//byte test [] = {-1, -1, -1, 4, 7};
		//Sonderfall noch nicht gel�st, die 3 wird nicht �bernommen
		//byte test [] = {-1, 2, 4, 3};
		//byte test [] = {-1, 9, 15, -1, -1};
		
		DecompressingInputStream in = new DecompressingInputStream(test);
		
		in.read();
		
		
		in.close();
				
	}

}