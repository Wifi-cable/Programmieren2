package de.hsmannheim.inf.pr2.coll;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

import org.junit.Test;

public class SearchTreeIteratorTest {

	SearchTree<Integer> miniTree1 = new SearchTree<Integer>();
	SearchTree<Integer> miniTree2 = new SearchTree<Integer>();
	SearchTree<Integer> integerTree = new SearchTree<Integer>();
	SearchTree<String> stringTree1 = new SearchTree<String>();
	SearchTree<String> stringTree2 = new SearchTree<String>();
	SearchTree<Integer> nullTree = new SearchTree<Integer>();
	
	SearchTree<String> stringNull= new SearchTree<String>();
	
	//einige bäume zum testen werden mit werten gefullt.
	@Before
	public void setUp() {

		miniTree1.add(8);
		miniTree1.add(3);
		miniTree1.add(16);
		
		for(int i=2;i<16; i+=2){
		miniTree2.add(i);
		}
		
		integerTree.add(20);
		integerTree.add(10);
		integerTree.add(30);
		integerTree.add(5);
		integerTree.add(7);
		integerTree.add(25);
		integerTree.add(35);
		
		nullTree.add(null);
				
		stringTree1.add("G");
		stringTree1.add("C");	
	}
	
	/**
	 * Elemente sind in miniTree1 vorhanden
	 */
	@Test
	public void testHasNext1() {
		Iterator<Integer> itr = miniTree1.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Testen eines nullTree, es sind keine Elemente vorhanden.
	 * Es wurde null durch add hinzugefuegt.
	 */
	@Test
	public void testHasNext2() {
		Iterator<Integer> itr = nullTree.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Testen von vorzeitigen einfuegen durch add und nach dem erzeugen des Iterators,
	 * ein weiteres Element hinzufuegen.
	 */
	@Test
	public void testHasNext3() {
		miniTree2.add(5);
		Iterator<Integer> itr = miniTree2.iterator();
		miniTree2.add(4);
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selber Test wie bei testHasNext1, bloss mit Strings.
	 */
	@Test
	public void testHasNext4() {
		Iterator<String> itr = stringTree1.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selber Test wie bei testHasNext3, bloss mit Strings.
	 */
	@Test
	public void testHasNext5() {
		stringTree2.add("X");
		Iterator<String> itr = stringTree2.iterator();
		stringTree2.add("A");
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Test wie bei HasNext1
	 */
	@Test
	public void testHasNext6() {
		Iterator<Integer> itr = integerTree.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Test eines NullTree, bei dem keine Elemete hinzugefuegt worden sind.

	 */
	@Test
	public void testHasNext7() {
		SearchTree<Integer> nullTree1 = new SearchTree<Integer>();
		Iterator<Integer> itr = nullTree1.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * werden die richtigen werte ausgegeben?
	 */
	@Test
	public void testNext() {
		Iterator<Integer> itr = miniTree2.iterator();
		for(int i=2;i<16; i+=2){
			assertEquals((Integer)i,itr.next());
		}
		assertFalse(itr.hasNext());
		assertEquals((1+1),2);
	}
	
	/**
	vergleicht soll und ist werte bei kleinem baum
	 */
	@Test
	public void testNext1() {
		Iterator<Integer> itr = miniTree1.iterator();
		assertEquals((Integer)3,itr.next());
		assertEquals((Integer)8,itr.next());
		assertEquals((Integer)16,itr.next());
		assertNull(itr.next());
	}
	//lauft er wirklich die ganze daten struktur ab? 
	@Test
	public void testFindsAll(){
		Iterator<Integer>iter=integerTree.iterator();
		int counter=0;
		while(iter.hasNext()){
			counter++;
		}
		assertEquals(7,counter);
	}
	//stürzt es ab wenn ein baum nur "null" endhält?
	@Test
	public void nextNull(){
		Iterator <Integer> iterare =nullTree.iterator();
		assertFalse(iterare.hasNext());
		assertNull(iterare.next());
	}
	//baum in den nie etwas eingefügt wurde, stürtzt nicht ab.
	@Test
	public void noElement(){
		Iterator <String> iterare =stringNull.iterator();
		assertFalse(iterare.hasNext());
		assertNull(iterare.next());
	}
	
}