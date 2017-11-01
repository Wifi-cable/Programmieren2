package de.hsmannheim.inf.pr2.io.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

/*Eine Sonderfall fehlt und die ArrayOutOfBoundException verursacht durch den Zähler ist noch nicht behoben*/

public class DecompressingInputStream extends InputStream {
	/*aufgabe 1.3 
	 * diese Klasse soll eine Dekomprimierung der Daten durchführen,
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
		//ArrayIndexOutOfBoundsException wird geworfen, Fehler derzeitig noch nicht gefunden, Zähler zählt über die 7 hoch
		index = 0;
				
		while (index < oldData.length) {
			//Betrachtet den Fall -1 -1
			if(oldData[pos] == -1 && oldData[zaehler] == -1){
				
				decompressing(oldData[pos]);
				
				zaehler += 2;
				pos += 2;
				index += 2;

			}
			//Betrachtet z.B -1 5 8
			else if (oldData[pos] == -1 && oldData[zaehler] != -1) {

				berechneAnzahl(oldData[zaehler], oldData[pos+2]);

				zaehler += 3;
				pos += 3;
				index += 2;
				
			}
			// Betrachtet einzelne Bytes
			else if (oldData[pos] != -1 && oldData[zaehler] == -1) {
				
				decompressing(oldData[pos]);
				
				pos++;
				zaehler++;
				index++;
			}
			//Betrachtet einzelne Bytes die hintereinander laufen
			else if (oldData[pos] != -1 && oldData[zaehler] != -1) {
				
				decompressing(oldData[pos]);
				decompressing(oldData[zaehler]);
				
				pos += 2;
				zaehler += 2;
				index += 2;
			}

		}
		
		return 0;
	}
	
	// Soll aus -1 3 2  / 3 3 machen, indem er die Schleife durchläuft und immer wieder decompressing aufruft mit der Zahl
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
		//Sonderfall noch nicht gelöst, die 3 wird nicht übernommen
		//byte test [] = {-1, 2, 4, 3};
		//byte test [] = {-1, 9, 15, -1, -1};
		
		DecompressingInputStream in = new DecompressingInputStream(test);
		
		in.read();
		
		
		in.close();
				
	}

}