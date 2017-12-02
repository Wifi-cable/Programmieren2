package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTreeComparison {
	SearchTree emptyTree= new SearchTree<Integer>();	//zwei leere baueme zum vergleichen
	SearchTree nullTree= new SearchTree<Integer>();
	SearchTree<Integer> miniTree= new SearchTree<Integer>();	//minimaler baum mit gleicher füllung
	SearchTree<Integer> miniTree2= new SearchTree<Integer>();
	
	SearchTree<Integer> fakeTree= new SearchTree<Integer>();	//gleiche zahlen, andere reihenfolge
	SearchTree<String>stringTree=new SearchTree<String>();
	SearchTree<String>stringTree2=new SearchTree<String>();
	SearchTree<Integer> biggerTree=new SearchTree<Integer>();
	SearchTree<Integer> biggerTree2=new SearchTree<Integer>();
	
	SearchTree <Integer>backwards= new SearchTree<Integer>();
	SearchTree <Integer>slantedTree= new SearchTree<Integer>();

	@Before
	public void setUp() throws Exception {
	miniTree.add(8);
	miniTree2.add(8);
	
	miniTree.add(3);
	miniTree2.add(3);
	
	miniTree.add(16);
	miniTree2.add(16);

	fakeTree.add(8);
	TreeNode<Integer> right=new TreeNode<Integer>(3);	// rueckwärts bauen
	TreeNode<Integer> left=new TreeNode<Integer>(16);
	fakeTree.root.left=left;
	fakeTree.root.right=right;
	
	stringTree.add("ich");
	stringTree.add("bin");
	stringTree.add("ein");
	stringTree.add("weihnachtsbaum");
	
	stringTree2.add("stern");
	stringTree2.add("kerzte");
	stringTree2.add("christbaumkugel");
	
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
	
	backwards.add(20);
	backwards.add(16);
	backwards.add(12);
	backwards.add(5);
	backwards.add(3);
	backwards.add(2);
	
	slantedTree.add(3);
	slantedTree.add(8);
	slantedTree.add(16);
	
	
	
	}

	@Test
	public void emptyTest() {	//testes ob zwei leere baume gleich sind 
		assertTrue(emptyTree.equalStructure(nullTree));
		assertTrue(nullTree.equalStructure(emptyTree));
		assertTrue(emptyTree.equalStructure(emptyTree));	//ist der leere baum gleich einem anderen leeren baum?
	}
	@Test 
	public void minimalEqualTree(){
		assertTrue(miniTree.equalStructure(miniTree2));
		assertTrue(miniTree2.equalStructure(miniTree));	//gleicher inhalt an gleicher stelle 
		assertTrue(miniTree2.equalStructure(miniTree2));	//identisch und gleich?
	}
	@Test
	public void notEqual(){
		assertFalse(miniTree.equalStructure(emptyTree));
		assertFalse(miniTree.equalStructure(fakeTree));
		assertFalse(fakeTree.equalStructure(miniTree2));
		assertFalse(stringTree.equalStructure(stringTree2));
		assertFalse(biggerTree.equalStructure(miniTree));
		assertFalse(biggerTree.equalStructure(miniTree2));
		assertFalse(biggerTree2.equalStructure(miniTree));
		assertFalse(biggerTree2.equalStructure(miniTree2));
		assertFalse(biggerTree.equalStructure(biggerTree2));
		assertFalse(miniTree.equalStructure(slantedTree));
		assertFalse(slantedTree.equalStructure(miniTree2));
		
	}
}
