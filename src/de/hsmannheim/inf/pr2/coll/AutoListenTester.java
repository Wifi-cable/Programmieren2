package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AutoListenTester {
	// einige autos zum verleichen und zaelen
	Auto vw= new Auto("VW","polo", 60);
	Auto voksWagen= new Auto("VW","golf", 45);
	Auto smart= new Auto("Smart","cabrio", 50);
	Auto audi=  new Auto("Audi", "quadro", 120);
	Auto audi2= new Auto("Audi", "quadro", 120);
	Auto sportwagen= new Auto("porsche","cayenne",220);
	Auto potenzHelfer= new Auto ("porsche","cayenne",420);
	//einige listen zum fuellen vergleichen und testen
	ListNode<Auto> firstList = new ListNode<Auto>(vw);
	ListNode <Auto>secondList= null;
	ListNode<Auto>thirdList= null;
	ListNode<Auto> single=new ListNode<Auto>(potenzHelfer);
	@Before
	 public void init(){
		firstList= new ListNode<Auto>(vw);
		firstList=firstList.addFirst(voksWagen);
		firstList=firstList.addFirst(smart);
		firstList=firstList.addFirst(audi);
		
		secondList=new ListNode<Auto>(smart);
		secondList=secondList.addFirst(sportwagen);
		secondList=secondList.addFirst(sportwagen);
		secondList= secondList.addFirst(audi);
		
		thirdList= new ListNode<Auto>(vw);
		thirdList=thirdList.addFirst(sportwagen);
		thirdList=thirdList.addFirst(vw);
		thirdList=thirdList.addFirst(sportwagen);
	}

	@Test
	public void containTester() {
		assertFalse(firstList.contains(sportwagen));	//sollte false sein
		assertFalse(firstList.contains(potenzHelfer));
		assertTrue(firstList.contains(audi));		//ist wirklich drinn
		assertTrue(firstList.contains(audi2));		// ist identisch, sollte also drinn sein
		assertTrue(firstList.contains(vw));
		assertTrue(firstList.contains(voksWagen));
		assertTrue(firstList.contains(smart));
	}
	@Test
	public void einDuplicateMitte(){
		assertEquals(2, secondList.countIf(sportwagen));		// testet einen doppelten
		assertEquals(1, secondList.countIf(audi));			// kein doppelter, steht vorne
		assertEquals(1, secondList.countIf(smart));			// kein doppelter, steht hinten
	}
	@Test
	public void erstesLetztesDoppelt(){
		assertEquals(2, thirdList.countIf(sportwagen));	//2x drinn einer als erstes
		assertEquals(2,thirdList.countIf(vw));			// 2x drinn einer als letztes
		assertEquals(0,thirdList.countIf(smart));	//nicht drin
	}
	@Test
	public void nurEins(){
		assertTrue(single.contains(potenzHelfer));	
		assertFalse(single.contains(vw));
		assertEquals(1, single.countIf(potenzHelfer));
		assertEquals(0,single.countIf(voksWagen));
	}

	

}
