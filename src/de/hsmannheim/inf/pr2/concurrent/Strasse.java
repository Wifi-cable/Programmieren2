package de.hsmannheim.inf.pr2.concurrent;

class Strasse  {
	int laenge;
	private boolean[]rechteFahrBahn=new boolean[laenge];
	private boolean[]linkeFahrBahn=new boolean[laenge];
	private Ampel[]ampeln= new Ampel[laenge];
	
	
	public Strasse(int laenge){		//constructor  
		this.laenge=laenge;
		for(int i=0; i<laenge; i++){	//setzt alles in ampeln[] auf gruen, oder du kannst fahren
			ampeln[i]=null;
		}
		for(int i=0; i<laenge; i++){	//freihe farht 
			rechteFahrBahn[i]=false;	//steht hier schon ein auto? false 
		}
		for(int i=0; i<laenge; i++){	
			linkeFahrBahn[i]=false;	
		}
	}
	// setter  für eins der fahrban arrays, belegt eine array stelle (mit einem auto) 
	void besetzen(int p, boolean[]fahrbahn){
		fahrbahn[p]=true;	// ist da noch frei? false!
	}
	// setter für farbahn array, gibt eine stelle wieder frei.
	void freiMachen(int p, boolean []fahrbahn){
		fahrbahn[p]=false;
	}
	
	/*@pram  aktuelle position des autos.  
	 * @return  ist die nächste ampel fuer das auto grün*/
	boolean istGruen(int autoPos, Richtung fahrbahn){
		boolean gruen=false;
		Richtung ampelStellung;	
		Ampel naechsteAmpel=ampeln[autoPos];
		while((naechsteAmpel==null)&& !((autoPos<1)||(autoPos>ampeln.length-1))){
			if(fahrbahn==Richtung.NACHRECHTS){
				autoPos++;
			}
			else{
				autoPos--;
			}
			naechsteAmpel=ampeln[autoPos];
		}
		ampelStellung= naechsteAmpel.getRichtung();
		
		if(ampelStellung==fahrbahn){
			gruen = true;
		}// else waere false aber das ist der default wert
		return gruen;
	}
	
	void setupAmpel(int ort, long intervall, Richtung rOl )throws SimulationsException{// stellt eine neue ampel auf
		if(!((ort<0)||(ort>ampeln.length))){
			if(ampeln[ort]==null){
				ampeln[ort]=new Ampel(intervall,rOl );
				}
			}
		else{
			throw new SimulationsException();
		}
		
	}

	/*Schreiben Sie eine Klasse Strasse , die eine Strase symbolisiert. Die Straÿse hat eine
	parametrierbare, aber danach für die Laufzeit der Simulation feste Länge (gemessen
	in Kilometern). Auf der Straÿe können beliebig viele Fahrzeuge fahren. Normalerwei-
	se hat die Straÿe zwei Fahrspuren (eine pro Richtung), sodass die Fahrzeuge in beide
	Richtungen ungehindert fahren können. Auf der gesamten Strecke herrscht Überhol-
	verbot, d. h. die Autos in einer Richtung können sich nicht gegenseitig überholen. Es
	kann weiterhin zu Engstellen kommen, an denen es nur eine Fahrspur gibt und daher
	abwechselnd immer nur die Autos aus der einen oder der anderen Richtung fahren
	können. Diese Engstellen werden von Ampeln reguliert.*/
	
	/*Lassen Sie das Programm mit verschiedene Fahrzeuggeschwindigkeiten und Ampeln
	laufen. Testen Sie es zusätzlich mit folgenden Einstellungen:
 	•  Länge der Straÿe: 80 km
	 Ampeln an den Positionen 10, 30, 60 und 70 km. Alle stehen am Anfang auf >,
	d. h. es darf von links nach rechts gefahren werden. Das Intervall für die erste
	Ampel ist 1 Sekunde, für die zweite und dritte 3 Sekunden und für die vierte
	Ampeln 2 Sekunden.
	• Positionieren Sie folgende Autos auf der Straÿe gemäÿ Tabelle 2.
	Damit sieht die Startausgabe Ihrer Simulation wie folgt aus*/
}
