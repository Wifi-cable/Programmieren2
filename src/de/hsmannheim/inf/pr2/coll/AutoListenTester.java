package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AutoListenTester {

	//Auto(String marke,String modell, int ps) {
	Auto vw= new Auto("VW","polo", 30);
	Auto voksWagen= new Auto("VW","golf", 45);
	Auto smart= new Auto("Smart","cabrio", 30);
	Auto audi=  new Auto("Audi", "quadro", 80);
	Auto audi2= new Auto("Audi", "quadro", 80);
	Auto cabrio= new Auto("porsche","cayenne",120);
	
	ListNode<Auto> firstList = new ListNode<Auto>(vw);
	ListNode <Auto>secondList= null;
	ListNode<Auto>thirdList= null;
	ListNode<Auto> empty=null;
	@Before
	 public void init(){
		firstList= new ListNode<Auto>(vw);
		firstList=firstList.addFirst(voksWagen);
		firstList=firstList.addFirst(smart);
		firstList=firstList.addFirst(audi);
		
	}
	
	/* ListNode<Integer> list = null;   // Leere Liste.
    list = new ListNode<Integer>(1); // ergibt 1.
     list = list.addFirst(2);*/

	@Test
	public void testKeinDuplicat() {
		assertFalse(firstList.contains(cabrio));
		assertTrue(firstList.contains(audi2));
		//assertTrue(firstList.contains(audi));
	
	}

}
