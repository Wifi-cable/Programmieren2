package de.hsmannheim.inf.pr2.concurrent;

public class Auto implements Runnable {
	int position, geschwindigkeit, naechstePos;
	char name;
	Richtung richtung;//ennum rechts oder links
	boolean strassenEnde;
	int strassenLaenge;
	private Strasse meineStrasse;
	

	
	public Auto(int position, int geschwindigkeit,Richtung LR, Strasse meineStrasse,char name){	//constructor
		this.position=position;
		this.geschwindigkeit=geschwindigkeit;
		this.meineStrasse=meineStrasse;
		richtung=LR;
		strassenLaenge= meineStrasse.laenge;
		this.name = name;
		this.naechstePos=position;
		}
	

	public void run() {
		int zeit=wievielZeit();
		synchronized (meineStrasse) {
			
		while(!(meineStrasse.istGruen(position,richtung )&& meineStrasse.naechster()){
			try {
				wait();
			
				
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		
		try {
			// fahren
			
			Thread.sleep( zeit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			//dann fahren?
		}// end sync
	}
		/* hier kommt der code rein der paralell laufen kann. mehrere autos können schliesslich auf der 
		 * selben strasse fahren. */
	
/*methode um herauszufinden was die nächste postition für das auto währe.  da sie in entgegengesetzte richtungen fahren,
 * ist das nicht immer position ++*/
	void nachster(){
		if(richtung.ordinal()==0){	//auto faert nach rechts, das heisst linke fahrban
			naechstePos++;	
		}
		else{
			naechstePos--;
			
		}
	}
/*Schreiben Sie eine Klasse Auto , die die Autos darstellt, die auf der Straÿe fahren kön-
nen. Ein Auto kann an einer beliebigen Stelle der Straÿe losfahren und fährt in eine
der beiden möglichen Richtungen. Jedes Auto hat eine bestimmte Geschwindigkeit in
km/Stunde, mit der es auf der Straÿe fährt. Weiterhin haben Autos noch einen ein-
buchstabigen Namen, um sie in der Simulation besser darstellen zu können.
Für die Angabe der Fahrtrichtung benutzen Sie bitte eine Enumeration Richtung
und zur Anzeige von ungültigen Eingabewerten (Positionen etc.) eine Runtime-Exception
namens SimulationsException .*/

	int wievielZeit(){
		int zeit=((1/geschwindigkeit)*1000);
		return zeit;
	}
	

}
