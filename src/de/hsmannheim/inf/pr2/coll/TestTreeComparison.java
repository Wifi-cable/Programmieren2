package de.hsmannheim.inf.pr2.coll;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTreeComparison {
	TreeNode<Integer> emptyTree= new TreeNode<Integer>(null);	//zwei leere baueme zum vergleichen
	TreeNode<Integer> nullTree= new TreeNode<Integer>(null);
	//SearchTree<Integer> miniTree= new SearchTree<Integer>();	//minimaler baum mit gleicher füllung
	TreeNode<Integer> miniTree;
	//SearchTree<Integer> miniTree2= new SearchTree<Integer>();
	TreeNode<Integer> miniTree2;
	//SearchTree<Integer> fakeTree= new SearchTree<Integer>();	//gleiche zahlen, andere reihenfolge
	TreeNode<Integer>fakeTree;	//andere ordnung
	//SearchTree<String>stringTree=new SearchTree<String>();
	TreeNode<String> stringTree;
	//SearchTree<String>stringTree2=new SearchTree<String>();
	TreeNode<String> stringTree2;
	TreeNode<Integer> biggerTree;
	//SearchTree<Integer> biggerTree=new SearchTree<Integer>();
	//SearchTree<Integer> biggerTree2=new SearchTree<Integer>();
	TreeNode<Integer>biggerTree2;
	//SearchTree <Integer>backwards= new SearchTree<Integer>();
	//SearchTree <Integer>slantedTree= new SearchTree<Integer>();
	TreeNode<Integer>SlantedTree;

	@Before
	public void setUp() throws Exception {
	TreeNode<Integer> n1=new TreeNode<Integer>(3);	
	TreeNode<Integer> n2=new TreeNode<Integer>(16);
	miniTree=new TreeNode<Integer>(8,n2,n1);
	
	TreeNode<Integer> n3=new TreeNode<Integer>(3);	
	TreeNode<Integer> n4=new TreeNode<Integer>(16);
	miniTree2=new TreeNode<Integer>(8,n4,n3);
	fakeTree=new TreeNode<Integer>(8,n3,n2);
	
	TreeNode<String> sn= new TreeNode<String>("froehliche");
	TreeNode <String>sn1= new TreeNode<String>("du");
	stringTree= new TreeNode<String>("oh", sn, sn1);
	
	TreeNode<String> sn2= new TreeNode<String>("sehlige");
	TreeNode <String>sn3= new TreeNode<String>("du");
	stringTree2= new TreeNode<String>("oh", sn2, sn3);
	
	TreeNode<Integer> n5=new TreeNode<Integer>(2);
	TreeNode<Integer> n6=new TreeNode<Integer>(5);
	TreeNode<Integer> n7=new TreeNode<Integer>(12);
	TreeNode<Integer> n8=new TreeNode<Integer>(20);//blätter
	
	TreeNode<Integer> n9=new TreeNode<Integer>(3,n6 ,n5);
	TreeNode<Integer> n10=new TreeNode<Integer>(16,n8,n7);
	biggerTree=new TreeNode<Integer>(8,n10,n9);
	
	TreeNode<Integer> n11=new TreeNode<Integer>(3,n5 ,n6);
	TreeNode<Integer> n12=new TreeNode<Integer>(16,n7,n8);
	biggerTree2=new TreeNode<Integer>(8,n11,n12);
	
	TreeNode<Integer> n13=new TreeNode<Integer>(8);
	TreeNode<Integer> n14=new TreeNode<Integer>(2,n13, null);
	TreeNode<Integer> n15=new TreeNode<Integer>(4,n14, null);
	TreeNode<Integer> n16=new TreeNode<Integer>(8,n15, null);
	SlantedTree=new TreeNode<Integer>(16,n16, null);
	
	
	
	
//	miniTree.add(8);
//	miniTree2.add(8);
//	
//	miniTree.add(3);
//	miniTree2.add(3);
//	
//	miniTree.add(16);
//	miniTree2.add(16);
//
//	fakeTree.add(8);
//	TreeNode<Integer> right=new TreeNode<Integer>(3);	// rueckwärts bauen
//	TreeNode<Integer> left=new TreeNode<Integer>(16);
//	fakeTree.root.left=left;
//	fakeTree.root.right=right;
//	
//	stringTree.add("ich");
//	stringTree.add("bin");
//	stringTree.add("ein");
//	stringTree.add("weihnachtsbaum");
//	
//	stringTree2.add("stern");
//	stringTree2.add("kerzte");
//	stringTree2.add("christbaumkugel");
//	
//	biggerTree.add(8);
//	biggerTree.add(3);
//	biggerTree.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree2
//	biggerTree.add(2);
//	biggerTree.add(12);
//	
//	biggerTree2.add(8);
//	biggerTree2.add(3);
//	biggerTree2.add(16);	//bis hier gleich miniTree , miniTree2 und biggerTree1
//	biggerTree2.add(5);
//	biggerTree2.add(20);
//	
//	backwards.add(20);
//	backwards.add(16);
//	backwards.add(12);
//	backwards.add(5);
//	backwards.add(3);
//	backwards.add(2);
//	
//	slantedTree.add(3);
//	slantedTree.add(8);
//	slantedTree.add(16);

	
	
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
		assertFalse(miniTree.equalStructure(SlantedTree));
		assertFalse(SlantedTree.equalStructure(miniTree2));
		
	}
}
