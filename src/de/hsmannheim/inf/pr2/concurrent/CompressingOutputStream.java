package de.hsmannheim.inf.pr2.concurrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class CompressingOutputStream extends FileOutputStream {

	public CompressingOutputStream(File arg0) throws FileNotFoundException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	// aufgabe 1.2 
	/*diese klasse soll die "Laufängen-Komprimierung" realisieren, (so etwas wie einen zip file bauen)
	 * -sie erbt schon von fileoutputstream, wie im übungsblatt beschrieben. 
	 * */
}