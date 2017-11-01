package de.hsmannheim.inf.pr2.io.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/*Ein ArrayIndexOutOfBoundException wird geworfen, liegt am Zähler, da dieser über die Länge des Arrays hinauszählt.
 *Folgender Fall, weiß ich nicht wie ich diesen betrachten soll, wie z.B. am Ende des Arrays die letzte Zahl, sollte diese
 *eine einzelne Zahl sein. 
 *Der Anzahl Zähler in der Methode read gibt nicht für alle Kombinationen eines Arrays die richtige Ausgabe, hier fehlt
 *noch ein einheitliches Hochzählen, was für alle Kombinationen gilt. */

public class DecompressingInputStream extends InputStream {
	/*aufgabe 1.3 
	 * diese Klasse soll eine Dekomprimierung der Daten durchführen,
	 * die vorher mit einem CompressingOutputStream komprimiert wurden*/
	
	private byte [] oldData;
	private byte [] newData;
	int index;
	public static int x = 0;

	public DecompressingInputStream(byte [] b) {
		oldData = b;
		
	}

	@Override
	public int read() throws IOException {
		// implementierung der abstrakten methoder der oberklasse. 
		//ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Zähler zählt über die 7 hoch
		int pos = 0;
		int zähler = 1;
		int anzahl = 0;
		index = 0;
				
		while (index < oldData.length) {
			//Betrachtet dem Fall -1 -1
			if(oldData[pos] == -1 && oldData[zähler] == -1){
				
				decompressing(oldData[pos]);
				zähler += 2;
				pos += 2;
				anzahl++;
				index += 2;
				// Zählt die Anzahl zur richtigen Stelle hoch
				if (oldData[pos] == -1 && oldData[zähler] != -1) {
					//anzahl += 3;
					index += 3;
				
				}
				
			}
			//Betrachtet die -1 5 8 z.B.
			else if (oldData[pos] == -1 && oldData[zähler] != -1) {
				decompressing(oldData[zähler]);
				anzahl += 2;
				berechneAnzahl(oldData[zähler], oldData[anzahl]);
				zähler += 3;
				pos += 3;
				//anzahl += 2;
				index += 2;
				
			}
			// Betrachtet einzelne Bytes
			else if (oldData[pos] != -1 && oldData[zähler] == -1) {
				decompressing(oldData[pos]);
				pos++;
				zähler++;
				anzahl += 3;
				index++;
			}
			//Betrachtet einzelne Bytes die hintereinander laufen
			else if (oldData[pos] != -1 && oldData[zähler] != -1) {
				decompressing(oldData[pos]);
				decompressing(oldData[zähler]);
				pos += 2;
				zähler += 2;
				anzahl++;
				index += 2;
			}

			//zähler -= 1;
			
		}
		
		return 0;
	}
	
	// Soll aus -1 3 2 3 3 machen, indem er die Schleife dürchläuft und immer wieder decompressing aufruft mit der Zahl
	public void berechneAnzahl(int zahl, int menge) {
		
		for(int i = 0; i < menge - 1; i++) {
			int y = zahl;
			decompressing(y);
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
		
		//byte test [] = {3, 5, 3, -1, -1, 8, -1, 3, 5};
		byte test [] = {-1, 5, 2, -1, -1, -1, -1, 3, 8};
		
		DecompressingInputStream in = new DecompressingInputStream(test);
		
		in.read();
		
		
		in.close();
				
	}

}