package de.hsmannheim.inf.pr2.concurrent;

public class Auto implements Runnable {
	int position, geschwindigkeit, naechstePos;
	char name;
	Richtung richtung;// ennum rechts oder links
	int strassenEnde;
	int strassenLaenge;
	private Strasse meineStrasse;
	private boolean[] meineFahrbahn; // auf welcher fahrban ist das auto?

	public Auto(int position, int geschwindigkeit, Richtung rl, Strasse meineStrasse, char name) { // constructor
		//System.out.println("auto gebaut");
		this.position = position;
		this.geschwindigkeit = geschwindigkeit;
		this.meineStrasse = meineStrasse;
		strassenLaenge = meineStrasse.laenge;
		this.name = name;
		this.naechstePos = position;
		this.richtung= rl;
		strassenEnde = berechenStrassenEnde();
		this.meineFahrbahn=meineStrasse.getFahrbahnArray(rl);
	}

	public void run() {
		int zeit = wievielZeit(); // errechnet wie lange ein auto sleep macht
									// bis es weiter faert
		int anzeigeFahrbahn=meineStrasse.berechneAnzeigeFahrbahn(richtung); // welche reihe im anzeigeArray benutzt ein auto,
		nachster();

		while (position != strassenEnde) {
			synchronized (meineStrasse) {
					//warte wenn ampel nicht grün oder strasse nicht frei
				while (!(meineStrasse.istGruen(position, richtung) && !(meineFahrbahn[naechstePos]))) {
					try {
						meineStrasse.wait();	
						//System.out.print(name+" stau ");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

//				try { // fahren
					meineStrasse.besetzen(naechstePos, meineFahrbahn); // boolean array updaten
					meineStrasse.freiGeben(position, meineFahrbahn);
					//System.out.print(" "+name+" fahren ");	//debugg statement
					//Thread.sleep(zeit);					//geschwindikeit wird durch pausen beim fahren symboliesiert.
					
					
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				} // ende der schleife "fahr wenn du kannst"

			} // end syncronized
			try {
				Thread.sleep(zeit);
				//Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}					//geschwindikeit wird durch pausen beim fahren symboliesiert.
			
			try {
				char[][]temp=meineStrasse.getAnzeige();
				meineStrasse.setAnzeigeArray(name, naechstePos, anzeigeFahrbahn);
				if(temp[position][anzeigeFahrbahn]==name ){			// überchreib die autos hinter dir nicht.
					meineStrasse.setAnzeigeArray(' ', position, anzeigeFahrbahn);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} // anzeigeArray updaten
			
			position = naechstePos; // auto eins weiter, jetzt beide teiger aktualisieren
			nachster();
			synchronized(meineStrasse){
			meineStrasse.notifyAll();
			}
		} // ende äusere schleife die autofahrt
		synchronized(meineStrasse){
			try{
			meineStrasse.freiGeben(position, meineFahrbahn);
			meineStrasse.setAnzeigeArray(' ', position, anzeigeFahrbahn);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
				System.out.println("");
		System.out.println(name + " ist angekommen");
		System.out.println("");
	}

	/*
	 * methode um herauszufinden was die nächste postition für das auto währe.
	 * da sie in entgegengesetzte richtungen fahren, ist das nicht immer
	 * position ++
	 */
	void nachster() {
		if (richtung.ordinal() == 0) { // auto faert nach rechts, untere fahrban

			naechstePos++;
		} else { // auto fährt nach links obere fahrbahn
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
		//int zeit = ((1 / geschwindigkeit) * 1000);
		int zeit= 1000/( geschwindigkeit);
		return zeit;
	}

	// wo die strasse zu ende ist, kommt drauf an in welche richtung man fährt
	int berechenStrassenEnde() {
		if (richtung.ordinal() == 0) {
			return strassenLaenge - 1;
		} else {
			return 1;
		}

	}

}
