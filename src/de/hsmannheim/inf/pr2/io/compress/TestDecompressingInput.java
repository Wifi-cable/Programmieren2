package de.hsmannheim.inf.pr2.io.compress;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;
import org.junit.Before;
import org.junit.Test;

public class TestDecompressingInput {
	
	byte []test1 ={-1,-1, -125, -125};
	byte []test2 = {-1, 5, 8, -1, 3, 10, -1, 2, 2, -125, -125};
	byte []test3 = {1, 2, 3, 4, -125, -125};
	byte[]test4 = {-1, 5, 3, 8, -1, -1, 3, -125, -125};
	
	DecompressingInputStream in;
	
	
	@Before
	public void ladeDaten() throws Exception {
		try {
			in = new DecompressingInputStream(test1);
		}
		catch(Exception fehler){
			fehler.printStackTrace();
		}
		
	}

	

	@Test
	public void negativEins() throws IOException {
				
		DecompressingInputStream in= new DecompressingInputStream(test1);
		in.read();

	}
	
	@Test
	public void kompromierteZahl() throws IOException {
		
		DecompressingInputStream in= new DecompressingInputStream(test2);
		in.read();

	}
	
	@Test
	public void einzelneZahl() throws IOException {
		
		DecompressingInputStream in= new DecompressingInputStream(test3);
		in.read();
	}
	
	@Test
	public void zahlAmEnde() throws IOException {
		
		DecompressingInputStream in= new DecompressingInputStream(test4);
		in.read();
	}

}
