package de.hsmannheim.inf.pr2.io.compress;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;
import org.junit.Before;
import org.junit.Test;

public class TestCompressingOutput {
	byte []tester1={1,2,4,-1,6,-1,-1,3};
	byte []tester2= new byte[200];	//immer die selbe zahl
	byte []tester3=new byte [50];	// random zahlen
	byte[]tester4= {2,2,2,2,3,3,5,5,5,5,5,5,5,5,5,5};
	CompressingOutputStream outPut;
	//File ex= new File("example");
	OutputStream out;

	@Before
	public void setUp() throws Exception {
		for (int i=0; i<200;i++){
			tester2[i]=42;
		}
		for (int i=0; i<50;i++){
			tester3[i]= (byte)((Math.random()*11)-2); // zahlen zwischen -2 und 9 
		}
		try{ // try catch weil outputstreams einem dauernt um die ohren fliegen. 
			 outPut= new CompressingOutputStream(tester1, new FilterOutputStream( out));
			}
		catch(IOException inOutError){
			inOutError.printStackTrace();
		}
		catch(Exception error){
			error.printStackTrace();
		}
		
	}

	//@Test	// geht er mit 126 gleichen zahlen richtig um?
	public void byteoverflow() throws IOException{
		
		//assertEquals();
	}
	
	//@Test // funst das mit den minus einsen?
	public void negativeOne(){
		
	}
	@Test // tested wiederholungen
	public void repetitions()throws IOException{
		byte []result;
		outPut= new CompressingOutputStream(tester4, new FilterOutputStream( out));
		outPut.compressToArray();
		result= outPut.getZipData();
		assertEquals(-1,result[0]);
		assertEquals(2, result[1]);
		assertEquals(4, result[2]);
		assertEquals(-1,result[3]);	
		assertEquals(3, result[4]);	
		assertEquals(2, result[5]);	
		assertEquals(-1, result[6]);
		assertEquals(5, result[7]);
		assertEquals(10, result[8]);
	}


	
}
