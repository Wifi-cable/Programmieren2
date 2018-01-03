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
	SearchTree<Integer> miniTree3 = new SearchTree<Integer>();
	SearchTree<String> stringTree1 = new SearchTree<String>();
	SearchTree<String> stringTree2 = new SearchTree<String>();
	SearchTree<Integer> nullTree = new SearchTree<Integer>();


	@Before
	public void setUp() {

		miniTree1.add(8);
		miniTree1.add(3);
		miniTree1.add(16);
		//miniTree1.sort();
		
		miniTree3.add(20);
		
		nullTree.add(null);
		//nullTree.sort();
		
		stringTree1.add("G");
		stringTree1.add("C");
		//stringTree1.sort();
	}
	
	/**
	 * Erster Test der Methode hasNext(), Elemente sind in miniTree1 noch vorhanden.
	 */
	@Test
	public void testHasNext1() {
		Iterator<Integer> itr = miniTree1.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Testen eines nullTree, es sind keine Elemente vorhanden.
	 * Es wurde null durch add hinzugefügt.
	 */
	@Test
	public void testHasNext2() {
		Iterator<Integer> itr = nullTree.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Testen von vorzeitigen einfügen durch add und nach dem erzeugen des Iterators,
	 * ein weiteres Element hinzufügen.
	 */
	@Test
	public void testHasNext3() {
		miniTree2.add(5);
		Iterator<Integer> itr = miniTree2.iterator();
		miniTree2.add(4);
		//miniTree2.sort();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selber Test wie bei testHasNext1, bloß mit Strings.
	 */
	@Test
	public void testHasNext4() {
		Iterator<String> itr = stringTree1.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selber Test wie bei testHasNext3, bloß mit Strings.
	 */
	@Test
	public void testHasNext5() {
		stringTree2.add("X");
		Iterator<String> itr = stringTree2.iterator();
		stringTree2.add("A");
		//stringTree2.sort();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Testen eines einzelnen Elementes.
	 * Erwartet False? Weiteres Element nach dem ersten nicht vorhanden?
	 */
	@Test
	public void testHasNext6() {
		Iterator<Integer> itr = miniTree3.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Test eines NullTree, bei dem keine Elemete hinzugefügt worden sind.
	 * 
	 * NullPointer erwartet? Es wurde kein Element hinzugefügt, es existiert auch keines im Baum.
	 */
	@Test
	public void testHasNext7() {
		SearchTree<Integer> nullTree1 = new SearchTree<Integer>();
		Iterator<Integer> itr = nullTree1.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Test für next() ????
	 */
	@Test
	public void testNext() {
		Iterator<Integer> itr = miniTree1.iterator();
		itr.next();
	}
	
	/**
	 * Teste einen Baum, bei dem keine Elemente hinzugefügt wurden.
	 * 
	 * NullPointer erwartet? Es wurde kein Element hinzugefügt, es existiert auch keines im Baum.
	 */
	@Test
	public void testNext1() {
		SearchTree<Integer> nullTree2 = new SearchTree<Integer>();
		Iterator<Integer> itr = nullTree2.iterator();
		itr.next();
	}
}
