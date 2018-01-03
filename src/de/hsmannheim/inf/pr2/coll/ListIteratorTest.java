package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

import org.junit.Test;

public class ListIteratorTest {
	
	List<Integer> list1 = new List<Integer>();
	List<Integer> list2 = new List<Integer>();
	List<Integer> list3 = new List<Integer>();
	List<Integer> list4 = new List<Integer>();
	List<String> list5 = new List<String>();
	List<String> list6 = new List<String>();	
	List<String> list7 = new List<String>();
	
	@Before
	public void setUp() {	
		list1.add(5);
		list1.add(4);
		list2.add(10);
		list3.add(null);
		list5.add("Hallo");
		list5.add("nichts");
		list6.add("Lord");
	}
	
	/**
	 * Weitere Elemente sind vorhanden, dadurch wird true erwartet
	 */
	@Test
	public void testHasNext1() {
		Iterator<Integer> itr = list1.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Ein weiteres Element ist nicht vorhanden, also erwartet False?
	 */
	@Test
	public void testHasNext2() {
		Iterator<Integer> itr = list2.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Leere Liste, erwartet also False
	 */
	@Test
	public void testHasNext3() {
		Iterator<Integer> itr = list3.iterator();
		assertFalse(itr.hasNext());
	}
	
	// NullPointerException, bei zuweisen eines Objektes ohne ein einziges Element.
	@Test
	public void testHasNext4a() {
		List<Integer> list4 = new List<Integer>();
		Iterator<Integer> itr = list4.iterator();
		assertFalse(itr.hasNext());
	}
	
	/**
	 * Einf�gen der Elemente, nach Erzeugen des Iterators
	 */
	@Test
	public void testHasNext4() {
		list4.add(7);
		Iterator<Integer> itr = list4.iterator();
		list4.add(99);
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selbes mit Strings
	 */
	@Test
	public void testHasNext5() {
		Iterator<String> itr = list5.iterator();
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Selbes mit Strings
	 */
	@Test
	public void testHasNext6() {
		Iterator<String> itr = list6.iterator();
		assertFalse(itr.hasNext());
	}
	
	@Test
	public void testHasNext7() {
		list7.add("Rambo");
		Iterator<String> itr = list7.iterator();
		list7.add("Gewehr");
		assertTrue(itr.hasNext());
	}
	
	/**
	 * Test f�r Next???
	 */
	@Test
	public void testNext() {
		Iterator<Integer> itr = list1.iterator();
		itr.next();
	}

	

}
