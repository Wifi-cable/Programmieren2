package de.hsmannheim.inf.pr2.coll;

//		aufgabe 1.2
public class Auto {	
	String marke;
	String modell;
	int ps;
	public Auto(String marke,String modell, int ps) {	//constructor
		this.marke=marke;
		this.modell=modell;
		this.ps=ps;
	}
	String getModell(){	// getter für die privaten felder.
		return this.modell;
	}
	String getMarke(){
		return this.marke;
	}
	int getPS(){
		return this.ps;
	}
	
	@Override
	public boolean equals(Object o ){
		System.out.println(" auto equals wird ausgeführt");
		if(this==o){	// 2x das gleiche object ist logischwerweise gleich
		return true;	
		}

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
	public String toString(){	// möglichst sinnvolle to string methode für autos
		return "[Marke: "+marke+" Modell: "+modell+" PS anzahl: "+ps+" ]";
	}
	/*
	 * hascode methode die hilft autos zu vergleichen wenn sie von den feldern her gleich sind aber ein anderes object erzeugt wird
	 * nutzt primzahl und alle felder*/

	@Override
	public int hashCode(){	
	int prime= 41;
	if((marke!= null)&&(modell!=null)){
		prime= prime +marke.hashCode();
		prime= prime+modell.hashCode();
	}
	
	return (prime-ps)%5000;	// modulo 
		
	}
	
	
}
