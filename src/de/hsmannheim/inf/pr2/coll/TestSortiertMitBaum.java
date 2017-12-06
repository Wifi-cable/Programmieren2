package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestSortiertMitBaum {
	 SearchTree<Integer> miniTree= new SearchTree<Integer>();	
	 SearchTree<Integer> miniTree2= new SearchTree<Integer>();
	 SearchTree<Integer> biggerTree= new SearchTree<Integer>();
	 SearchTree<Integer> biggerTree2= new SearchTree<Integer>();
	 SearchTree<String>christmasTree= new SearchTree<String>();
	 SearchTree<String>nullTree= new SearchTree<String>();
	 
	@Before
	public void setUp() throws Exception {
		miniTree.add(8);
		miniTree2.add(16);
		
		miniTree.add(3);
		miniTree2.add(8);
		
		miniTree.add(16);
		miniTree2.add(3);
		
		miniTree.sort();
		miniTree2.sort();
		
		biggerTree.add(8);
		biggerTree.add(3);
		biggerTree.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree2
		biggerTree.add(2);
		biggerTree.add(12);
		biggerTree.sort();
		
		biggerTree2.add(8);
		biggerTree2.add(3);
		biggerTree2.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree1
		biggerTree2.add(5);
		biggerTree2.add(20);
		biggerTree.sort();
		nullTree.add(null);
		nullTree.sort();
		christmasTree.add("Ich bin ein Weihnachstsbaum. ");
		christmasTree.sort();
	}

	@Test
	public void testMini() {
		ArrayList <Integer>sortedList= miniTree2.sort();
		assertEquals(sortedList.get(0),(Integer)3);
		assertEquals(sortedList.get(1),(Integer)8);
		assertEquals(sortedList.get(2),(Integer)16);
		
	
//		assertEquals(miniTree.sortedList.get(0),3);
//		assertEquals(miniTree.sortedList.get(1),8);
//		assertEquals(miniTree.sortedList.get(2),16);
	}
	@Test
	public void testMini2() {
		ArrayList <Integer>sortedList= miniTree2.sort();
		assertEquals(sortedList.get(0),(Integer)3);
		assertEquals(sortedList.get(1),(Integer)8);
		assertEquals(sortedList.get(2),(Integer)16);
	}
	
	@Test
	public void testBiggerTree(){
	ArrayList <Integer>sortedList= biggerTree2.sort();		
	assertEquals(sortedList.get(0),(Integer)2);	
	assertEquals(sortedList.get(1),(Integer)3);
	assertEquals(sortedList.get(2),(Integer)8);
	assertEquals(sortedList.get(3),(Integer)12);
	assertEquals(sortedList.get(4),(Integer)16);	
	
//	assertEquals(biggerTree.sortedList.get(0),2);	
//	assertEquals(biggerTree.sortedList.get(1),3);
//	assertEquals(biggerTree.sortedList.get(2),8);
//	assertEquals(biggerTree.sortedList.get(3),12);
//	assertEquals(biggerTree.sortedList.get(4),16);
	}
	@Test
	public void testBiggerTree2(){
	ArrayList <Integer>sortedList= biggerTree2.sort();	
	assertEquals(sortedList.get(0),(Integer)3);	
	assertEquals(sortedList.get(1),(Integer)5);
	assertEquals(sortedList.get(2),(Integer)8);
	assertEquals(sortedList.get(3),(Integer)16);
	assertEquals(sortedList.get(4),(Integer)20);
	
//	assertEquals(biggerTree2.sortedList.get(0),3);	
//	assertEquals(biggerTree2.sortedList.get(1),5);
//	assertEquals(biggerTree2.sortedList.get(2),8);
//	assertEquals(biggerTree2.sortedList.get(3),16);
//	assertEquals(biggerTree2.sortedList.get(4),20);
	}
	@Test 
	public void stringTree(){
		ArrayList<String>sortedList=christmasTree.sort();
		assertEquals(sortedList.get(0),"Ich bin ein Weihnachstsbaum. ");
	}
	@Test
	public void nullTest(){	
		ArrayList<String>sortedList=nullTree.sort();
		assertNull(sortedList.get(0),null);
		//assertNull(nullTree.sortedList.get(0),null);
	}
}
