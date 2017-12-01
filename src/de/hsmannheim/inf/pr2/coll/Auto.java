package de.hsmannheim.inf.pr2.coll;

//public class Auto extends Object implements Comparable<Auto>{
public class Auto {	
	String marke;
	String modell;
	int ps;
	public Auto(String marke,String modell, int ps) {
		this.marke=marke;
		this.modell=modell;
		this.ps=ps;
	}
	String getModell(){
		return this.modell;
	}
	String getMarke(){
		return this.marke;
	}
	int getPS(){
		return this.ps;
	}
	// sollen die einen hashcode haben? comparable sein? 
	@Override
	public boolean equals(Object o ){
		System.out.println(" auto equals wird ausgeführt");
		if(this==o){	// 2x das gleiche object ist logischwerweise gleich
		return true;	
		}
//		if(this.hashCode()==o.hashCode()){
//			System.out.println("hashcode the same "+ this.hashCode()+" "+o.hashCode());
//		}
		if(!(o instanceof Auto)){	//wenn das andere kein auto ist, sind sie auf keinen fall gleich
			return false;
		}
		Auto that=(Auto)o;	//wenn "o" ein auto ist kann ich es so casten. 
		if(( this.marke.equals(that.getMarke())&&(this.modell.equals(that.getModell()))&&(this.ps==that.getPS()) )){
			return true;	//wenn alle felder gleich sind, ist das auto gleich. 
		}
		return false;
	}

	@Override 
	public String toString(){
		
		return "[Marke: "+marke+" Modell: "+modell+" PS anzahl: "+ps+" ]";
	}

	@Override
	public int hashCode(){
	int prime= 41;
	if((marke!= null)&&(modell!=null)){
		prime= prime +marke.hashCode();
		prime= prime+modell.hashCode();
	}
	
	return (prime-ps)%5000;
		
	}
	
//	public int compareTo(Auto o) {
//		System.out.println("compare to in use ");
//			if(o instanceof Auto){
//				Auto that=(Auto)o;
//				if(this.ps==that.getPS()){	// gleich viel ps
//					return 0;
//				}
//				else if(this.ps<that.getPS()){	//wehniger PS als das andere
//					return -1;
//				}
//				else{						// mehr ps als das andere
//					return 1;
//				}
//			}
//			return -100; 	// das andere ist kein auto.
//		
//		
//	}
//	@Override
//	public int compareTo(Object o){
//		
//			Auto pkw= (Auto)o;
//		
//		return this.compareTo(pkw);
//	}
	
}
