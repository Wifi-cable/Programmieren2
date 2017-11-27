package de.hsmannheim.inf.pr2.coll;

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
}
