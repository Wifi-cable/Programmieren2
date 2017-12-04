package de.hsmannheim.inf.pr2.coll;

public class TreeNodeMain {
	
	public static void main(String [] args) {
		
		TreeNode<Integer> t1 = new TreeNode<>(5);
		TreeNode<Integer> t2 = new TreeNode<>(9);
		TreeNode<Integer> t3 = new TreeNode<>(1, t1, t2);
		
		TreeNode<Integer> z1 = new TreeNode<>(5);
		TreeNode<Integer> z2 = new TreeNode<>(9);
		TreeNode<Integer> z3 = new TreeNode<>(1, z1, z2);
		
		TreeNode<Integer> t4 = new TreeNode<>(10);
		TreeNode<Integer> t5 = new TreeNode<>(15, t3, t4);
		
		TreeNode<Integer> z4 = new TreeNode<>(10);
		TreeNode<Integer> z5 = new TreeNode<>(15, z4, z3);
		
		System.out.println(t5.equalStructure(z5));
	}

}
