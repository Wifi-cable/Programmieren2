package de.hsmannheim.inf.pr2.coll.sort;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ShakerSortTest {

	List<Integer> miniList1 = new ArrayList<Integer>();
	List<Integer> miniList2 = new ArrayList<Integer>();
	List<Integer> bigList1 = new ArrayList<Integer>();
	List<Integer> bigList2 = new ArrayList<Integer>();
	List<String> colourList = new ArrayList<String>();
	List<String> nullList = new ArrayList<String>();
	
	@Before
	public void setUp() throws Exception{
		miniList1.add(8);
		miniList1.add(2);
		miniList1.add(5);
		
		miniList2.add(5);
		miniList2.add(2);
		miniList2.add(8);
		
		for(int i = 9; i > 0; i--){
		bigList1.add(i);
		}
		
		bigList2.add(7);
		bigList2.add(2);
		bigList2.add(1);
		bigList2.add(6);
		bigList2.add(5);
		bigList2.add(8);
		bigList2.add(3);
		bigList2.add(9);
		bigList2.add(4);
		
		colourList.add("blue");
		colourList.add("orange");
		colourList.add("pink");
		colourList.add("yellow");
		colourList.add("gray");
		colourList.add("red");
		
		nullList.add(null);
	}
	
	@Test
	public void minitest1() {
		Sort<Integer> sortList = new ShakerSort<Integer>();
		sortList.sort(miniList1);
		assertEquals(miniList1.get(0),(Integer)2);
		assertEquals(miniList1.get(1),(Integer)5);
		assertEquals(miniList1.get(2),(Integer)8);
		
	}
	
	@Test
	public void minitest2() {
		Sort<Integer> sortList = new ShakerSort<Integer>();
		sortList.sort(miniList2);
		assertEquals(miniList2.get(0),(Integer)2);
		assertEquals(miniList2.get(1),(Integer)5);
		assertEquals(miniList2.get(2),(Integer)8);
		
	}
	
	@Test
	public void bigtest1() {
		Sort<Integer> sortList = new ShakerSort<Integer>();
		sortList.sort(bigList1);
		for(int i = 0, j=1; i<8; i++, j++){
		assertEquals(bigList1.get(i),(Integer)j);
		}
		
	}
	
	@Test
	public void bigtest2() {
		Sort<Integer> sortList = new ShakerSort<Integer>();
		sortList.sort(bigList2);
		for(int i = 0, j=1; i<8; i++, j++){
			assertEquals(bigList2.get(i),(Integer)j);
			}
		
	}
	
	@Test
	public void colourtest() {
		Sort<String> sortList = new ShakerSort<String>();
		sortList.sort(colourList);
		assertEquals(colourList.get(0),"blue");
		assertEquals(colourList.get(1),"gray");
		assertEquals(colourList.get(2),"orange");
		assertEquals(colourList.get(3),"pink");
		assertEquals(colourList.get(4),"red");
		assertEquals(colourList.get(5),"yellow");
		
	}
	
	@Test
	public void nulltest() {
		Sort<String> sortList = new ShakerSort<String>();
		sortList.sort(nullList);
		assertNull(nullList.get(0),null);
		
	}

}
