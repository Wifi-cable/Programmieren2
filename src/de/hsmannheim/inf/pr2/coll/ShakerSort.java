package de.hsmannheim.inf.pr2.coll.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ShakerSort<E extends Comparable<E>> implements Sort<E> {

	
	public static void main(String[] args) {
		Sort<Integer> intsort = new ShakerSort<>();
		Sort<String> stringsort = new ShakerSort<>();
		List<Integer> test1 = new ArrayList<Integer>();
		List<Integer> testnull = new ArrayList<Integer>();
		test1.add(3);
		test1.add(1);
		test1.add(6);
		test1.add(7);
		test1.add(3);
		test1.add(2);
		test1.add(8);
		test1.add(2);
		test1.add(9);
		System.out.println(test1);
		intsort.sort(test1);
		System.out.println(test1);
		List<String> test2 = new ArrayList<String>();
		test2.add("a");
		test2.add("wgw");
		test2.add("sdv");
		test2.add("olikjuhzg");
		test2.add("wfee");
		test2.add("bd");
		test2.add("s");
		test2.add("aaa");
		System.out.println(test2);
		stringsort.sort(test2);
		System.out.println(test2);
		intsort.sort(testnull);
		System.out.println(testnull);
		
		
	}
	

	public void sort(List<E> list, Comparator<E> cmp) {
		
	    for (int i = 1; i <= (list.size()-1)/2; i++) {
	    	
	      for (int leftP = 0; leftP <= (list.size()-1) - i; leftP++) {
	    	  
	        if (cmp.compare(list.get(leftP), list.get(leftP+1)) > 0) {
	          swap(list, leftP, leftP + 1); // Tausche Elemente.
	        }
	      }
	      
	      for (int rightP = list.size()-1; rightP >= i; rightP--) {
	    	  
		        if (cmp.compare(list.get(rightP), list.get(rightP-1)) < 0) {
		          swap(list, rightP - 1, rightP); // Tausche Elemente.
		        }
	      }
	    }
	}
	  
	
	 private void swap(List<E> list, int idx1, int idx2) {
		    E tmp = list.get(idx1);
		    list.set(idx1, list.get(idx2));
		    list.set(idx2, tmp);
		  }

}
