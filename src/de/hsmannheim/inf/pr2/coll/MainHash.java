package de.hsmannheim.inf.pr2.coll;

class MainHash {

	public static <E> void main(String[] args) {
		Hashtable<E> moreStrings= new Hashtable<E>(17,1);
		System.out.println("frontier".hashCode());
		System.out.println((-1242784755+1*0)%17);
		//problem ist: modulo kann negative werte haben in java. ein zugriff auf array index -10 geht nicht.
		moreStrings.add((E)"frontier");
		
	}
		

}
