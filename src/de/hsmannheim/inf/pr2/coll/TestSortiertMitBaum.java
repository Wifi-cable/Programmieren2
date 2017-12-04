package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

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
	
		
		biggerTree.add(8);
		biggerTree.add(3);
		biggerTree.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree2
		biggerTree.add(2);
		biggerTree.add(12);
		
		biggerTree2.add(8);
		biggerTree2.add(3);
		biggerTree2.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree1
		biggerTree2.add(5);
		biggerTree2.add(20);
		nullTree.add(null);
		christmasTree.add("Ich bin ein Weihnachstsbaum. ");
	}

	@Test
	public void testMini() {
		assertEquals(miniTree.sortedList.getAt(0),3);
		assertEquals(miniTree.sortedList.getAt(1),8);
		assertEquals(miniTree.sortedList.getAt(2),16);
	}
	@Test
	public void testMini2() {
		assertEquals(miniTree2.sortedList.getAt(0),3);
		assertEquals(miniTree2.sortedList.getAt(1),8);
		assertEquals(miniTree2.sortedList.getAt(2),16);
	}
	
	@Test
	public void testBiggerTree(){
	assertEquals(biggerTree.sortedList.getAt(0),2);	
	assertEquals(biggerTree.sortedList.getAt(1),3);
	assertEquals(biggerTree.sortedList.getAt(2),8);
	assertEquals(biggerTree.sortedList.getAt(3),12);
	assertEquals(biggerTree.sortedList.getAt(4),16);
	}
	@Test
	public void testBiggerTree2(){
	assertEquals(biggerTree2.sortedList.getAt(0),3);	
	assertEquals(biggerTree2.sortedList.getAt(1),5);
	assertEquals(biggerTree2.sortedList.getAt(2),8);
	assertEquals(biggerTree2.sortedList.getAt(3),16);
	assertEquals(biggerTree2.sortedList.getAt(4),20);
	}
	

}
