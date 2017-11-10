package de.hsmannheim.inf.pr2.concurrent;

public class Auto implements Runnable {
	int position, geschwindigkeit, naechstePos;
	char name;
	Richtung richtung;// ennum rechts oder links
	int strassenEnde;
	int strassenLaenge;
	private Strasse meineStrasse;
	private boolean[] fahrBahn; // auf welcher fahrban ist das auto?

	public Auto(int position, int geschwindigkeit, Richtung LR, Strasse meineStrasse, char name) { // constructor
		this.position = position;
		this.geschwindigkeit = geschwindigkeit;
		this.meineStrasse = meineStrasse;
		richtung = LR;
		strassenLaenge = meineStrasse.laenge;
		this.name = name;
		this.naechstePos = position;
		fahrBahn = meineStrasse.getFahrbahnArray(LR);
		strassenEnde=berechenStrassenEnde();
	}

	public void run() {
		int zeit=wievielZeit();	//errechnet wie lange ein auto sleep macht bis es weiter faert
		int anzeigeFahrbahn;	//welche reihe im anzeigeArray benutzt ein auto, also welche "fahrbahn"
		if(richtung.ordinal()==0){
			anzeigeFahrbahn=2;
		}
		else{
			anzeigeFahrbahn=0;
		}
		meineStrasse.setAnzeigeArray(name, position, anzeigeFahrbahn);	// auto erst mal auf die strasse setzen
	//while !end of road, extra schleife	
		while(position!=strassenEnde){
		synchronized (meineStrasse) {
			
		while(!(meineStrasse.istGruen(position,richtung )&& (fahrBahn[naechstePos]))){
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				}
			}
		
		try {	// fahren
			meineStrasse.besetzen(naechstePos, fahrBahn);	//boolean array updaten
			meineStrasse.freiGeben(position, fahrBahn);
			meineStrasse.setAnzeigeArray(name, naechstePos, anzeigeFahrbahn);	//anzeigeArray updaten
			meineStrasse.setAnzeigeArray(' ', position, anzeigeFahrbahn);
			position=naechstePos;	// auto eins weiter, jetzt beide zeiger updaten
			nachster();									
			
			Thread.sleep( zeit);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		}	
		}// end sync
	}


	/*
	 * methode um herauszufinden was die nächste postition für das auto währe.
	 * da sie in entgegengesetzte richtungen fahren, ist das nicht immer
	 * position ++
	 */
	void nachster() {
		if (richtung.ordinal() == 0) { // auto faert nach rechts, das heisst
										// linke fahrban
			naechstePos++;
		} else {
			naechstePos--;

		}
		
	}
	/*
	 * Schreiben Sie eine Klasse Auto , die die Autos darstellt, die auf der
	 * Straÿe fahren kön- nen. Ein Auto kann an einer beliebigen Stelle der
	 * Straÿe losfahren und fährt in eine der beiden möglichen Richtungen. Jedes
	 * Auto hat eine bestimmte Geschwindigkeit in km/Stunde, mit der es auf der
	 * Straÿe fährt. Weiterhin haben Autos noch einen ein- buchstabigen Namen,
	 * um sie in der Simulation besser darstellen zu können. Für die Angabe der
	 * Fahrtrichtung benutzen Sie bitte eine Enumeration Richtung und zur
	 * Anzeige von ungültigen Eingabewerten (Positionen etc.) eine
	 * Runtime-Exception namens SimulationsException .
	 */

	int wievielZeit() {
		int zeit = ((1 / geschwindigkeit) * 1000);
		return zeit;
	}
	int berechenStrassenEnde(){// wo die strasse zu ende ist, kommt drauf an in welche richtung man fährt
		if(richtung.ordinal() == 0){
			return strassenLaenge;
		}
		else{
			return 0;
		}
			
	}

}
