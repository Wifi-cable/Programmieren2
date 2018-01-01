
package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author wifi-cable
 * @param <E>
 *
 */
public class HashTester<E> {

	/**
	 * @throws java.lang.Exception
	 */
	Hashtable <E>oneStepTable = new Hashtable<E>(10, 1) ;	//erster hashtable 10 lang sondiert mit 1
	Hashtable <E>twoStep= new Hashtable<E>(5,2);
	Hashtable <E>onlyString= new Hashtable<E>(7,3);
	Hashtable <E>moreStrings= new Hashtable<E>(21,1);
	Hashtable <E>collideMax = new Hashtable<E>(10);
	Hashtable<E> onlyOne=  new Hashtable<E>();
	Hashtable<E> emptyTable=  new Hashtable<E>();
	@Before
	public void setUp() throws Exception {
		Integer content;
		for(int i=10; i>0;i--){
			content= new Integer(i);
		oneStepTable.add((E) content);
		}
		for(int i=0; i<5;i++){
			twoStep.add((E)new Integer(i));
		}
		for(int i=10;i<70;i=(i+10)){
			collideMax.add((E)new Integer(i));
		}
		onlyString.add((E) "may");
		onlyString.add((E)"the");
		onlyString.add((E)"force");
		onlyString.add((E)"be");
		onlyString.add((E)"with");
		onlyString.add((E)"you");
		
		/*Space: the final frontier. These are the voyages of the starship Enterprise.
		 *  Its five-year mission: to explore strange new worlds, to seek out new life and new civilizations,
		 *   to boldly go where no man has gone before.*/
		moreStrings.add((E)"Space");
		moreStrings.add((E)"the");
		moreStrings.add((E)"final");
		moreStrings.add((E)"frontier");
		moreStrings.add((E)"These");
		moreStrings.add((E)"are");
		moreStrings.add((E)"the");
		moreStrings.add((E)"voyages");
		moreStrings.add((E)"of");
		moreStrings.add((E)"the");
		moreStrings.add((E)"starship");
		moreStrings.add((E)"Enterprise");
		
		onlyOne.add((E)"Frodo ist ein Hobbit");
		
	}
	// teste fast maximale kollisionen. integer
	@Test 
	public void testCollisions(){
		//System.out.println(collideMax.mkString());	// debug feature
		for(int i=10;i<70;i=(i+10)){
		assertTrue(collideMax.contains((E)(Integer)i));
		}	
		for(int i=10;i<70;i=(i+10)){
		assertTrue(collideMax.remove((E)(Integer)i));
		}
	}
	
	//tested integer  leicht testbar
	@Test
	public void removeFrontInteger() {
		assertTrue(oneStepTable.contains((E)(Integer)10));
		oneStepTable.remove((E)(Integer)10);
		assertFalse(oneStepTable.contains((E)(Integer)10));
		//assertEquals(oneStepTable.hashtable[0], 10);
	}
	@Test
	public void removeLast(){
		assertTrue(oneStepTable.contains((E)(Integer)1));
		oneStepTable.remove((E)(Integer)1);
		assertFalse(oneStepTable.contains((E)(Integer)1));
	}
	@Test
	public void removeCenter(){
		assertTrue(twoStep.contains((E)new Integer(2)));
		twoStep.remove((E)(Integer)2);
		assertFalse(twoStep.contains((E) (Integer)2));
	}
	//schlaegt der versuch fehl, etwas zu löschen das nicht drin ist? (sollte er zumindest)
	@Test
	public void removeNotContained(){
		assertFalse(twoStep.contains((E) (Integer)10));
		assertFalse(twoStep.remove((E)new Integer(10)));
	}
	//schlägt der test fehl etwas zu entfernen das schon endfernt wurde?
	@Test
	public void removeDeleted(){
		twoStep.remove((E)(Integer)2);
		assertFalse(twoStep.contains((E) (Integer)2));
		assertFalse(twoStep.remove((E)new Integer(2)));
	}
//tests fuer strings
	
	@Test
	public void rmString(){
		assertTrue(onlyString.contains((E)"you"));	// string wirklich drin
		onlyString.remove((E)"you");				// string endfernt
		assertFalse(onlyString.contains((E)"you"));	// string jetzt weg
		assertFalse(onlyString.remove((E)"you"));	// kann auch nicht mehr gelöscht werden
	}
	// testet einen string der nie drin war. löschen sollte false ergeben 
	@Test
	public void noSuchString(){	
		assertFalse(onlyString.contains((E)"pumping lemma"));	// ueberprueft ob der sting wirklich nicht drin ist. 
		assertFalse(onlyString.remove((E)"pumping lemma"));
	}
	// testet ob alles gelöscht werden kann.  
	@Test
	public void dellAll(){
		//System.out.println(onlyString.mkString());	// debug feature
		assertTrue(onlyString.remove((E)"may"));
		assertTrue(onlyString.remove((E)"the"));
		assertTrue(onlyString.remove((E)"force"));	// professors code gab -10 als hashwert aus. leider faengt ein array bei 0 an. 
		assertTrue(onlyString.remove((E)"be"));
		assertTrue(onlyString.remove((E)"with"));
		assertTrue(onlyString.contains((E)"you"));	// dieser string wurde rehashed, weil es zur kollision kam 
		assertTrue(onlyString.remove((E)"you"));	// solle trozdem gefunden werden und gelöscht
		
		//System.out.println(onlyString.mkString());	// debug feature
		assertEquals(onlyString.size(),0);
	}
	//ein einziger string im table.
	@Test
	public void onlyString(){
		assertTrue(onlyOne.remove((E)"Frodo ist ein Hobbit"));
		assertFalse(onlyOne.remove((E)"Frodo ist ein Hobbit"));
	}
	// löschen in einem leeren hashtable. kein  nullppinter und keien endloos schleife
	@Test
	public void empty(){
		assertFalse(emptyTable.remove((E)"any sting"));
	}

}

