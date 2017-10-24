package de.hsmannheim.inf.pr2.io.compress;

import java.io.IOException;
import java.io.InputStream;

public class DecompressingInputStream extends InputStream {
	/*aufgabe 1.3 
	 * diese Klasse soll eine Dekomprimierung der Daten durchführen,
	 * die vorher mit einem CompressingOutputStream komprimiert wurden*/

	public DecompressingInputStream() {
		// constructor der superklasse
	}

	@Override
	public int read() throws IOException {
		// implementierung der abstrakten methoder der oberklasse. 
		return 0;
	}

}
