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
	public void testMini() {	//tstet den mini tree, ein baum mit drei knoten, balanciert 
		ArrayList <Integer>sortedList= miniTree2.sort();
		assertEquals(sortedList.get(0),(Integer)3);
		assertEquals(sortedList.get(1),(Integer)8);
		assertEquals(sortedList.get(2),(Integer)16);
		
	}
	@Test
	public void testMini2() {	//anderer baum mit nur 3 knoten in anderer anordnung, nicht balanciert
		ArrayList <Integer>sortedList= miniTree2.sort();
		assertEquals(sortedList.get(0),(Integer)3);
		assertEquals(sortedList.get(1),(Integer)8);
		assertEquals(sortedList.get(2),(Integer)16);
	}
	
	@Test
	public void testBiggerTree(){	// testet grösserer baum, funktiniert die rekursion
	ArrayList <Integer>sortedList= biggerTree.sort();		
	assertEquals(sortedList.get(0),(Integer)2);	
	assertEquals(sortedList.get(1),(Integer)3);
	assertEquals(sortedList.get(2),(Integer)8);
	assertEquals(sortedList.get(3),(Integer)12);
	assertEquals(sortedList.get(4),(Integer)16);	
	

	}
	@Test
	public void testBiggerTree2(){	// un ausgegleichener grösserer bauem
	ArrayList <Integer>sortedList= biggerTree2.sort();	
	assertEquals(sortedList.get(0),(Integer)3);	
	assertEquals(sortedList.get(1),(Integer)5);
	assertEquals(sortedList.get(2),(Integer)8);
	assertEquals(sortedList.get(3),(Integer)16);
	assertEquals(sortedList.get(4),(Integer)20);
	}
	@Test 
	public void stringTree(){	// anderer inhalt fuktioniert nicht wenn mit == verglichen wurde
		ArrayList<String>sortedList=christmasTree.sort();
		assertEquals(sortedList.get(0),"Ich bin ein Weihnachstsbaum. ");
	}
	@Test
	public void nullTest(){	// testet ob methode mit dem wert null klarkommt
		ArrayList<String>sortedList=nullTree.sort();
		assertNull(sortedList.get(0),null);
	
	}
}
