package de.hsmannheim.inf.pr2.concurrent;

class Strasse  {
	int laenge;
	private volatile boolean[]obereFahrBahn=new boolean[laenge];	//nachLinks
	private volatile boolean[]untereFahrBahn=new boolean[laenge];	//nachRechts
	private volatile Ampel[]mittelstreifen= new Ampel[laenge];
	private volatile char[][]anzeigeArray= new char[laenge][3];
	
	
	public Strasse(int laenge){		//constructor  
		this.laenge=laenge;
		for(int i=0; i<laenge; i++){	//setzt alles in ampeln[] auf gruen, oder du kannst fahren
			mittelstreifen[i]=null;
		}
		for(int i=0; i<laenge; i++){	//freihe farht 
			obereFahrBahn[i]=false;	//steht hier schon ein auto? false 
		}
		for(int i=0; i<laenge; i++){	
			untereFahrBahn[i]=false;	
		}
	}
	public boolean []getFahrbahnArray(Richtung rl){
		if(rl.ordinal()==0){
			return untereFahrBahn;
		}
		else {
			return obereFahrBahn;
		}
	}
	// setter  für eins der fahrban arrays, belegt eine array stelle (mit einem auto) 
	void besetzen(int p, boolean[]fahrbahn){
		fahrbahn[p]=true;	// ist da noch frei? false!
	}
	// setter für farbahn array, gibt eine stelle wieder frei.
	void freiGeben(int p, boolean []fahrbahn){
		fahrbahn[p]=false;
	}
	// erlaubt ausserhalb der klasse auf ein char array zur anzeige  zuzugreifen
	 public char[][] getAnzeige(){
		return anzeigeArray;
	}
	protected void setAutoAnzeige(char autoName, int index){
		//noch nicht implementiert wie weiss die strasse welche fahrbahn?
	} 
	protected void setAmpelAnzeige(Richtung rl, int index){
		// noch nicht implementiert
	}
	
	/*@pram  aktuelle position des autos.  
	 * @return  ist die nächste ampel fuer das auto grün*/
	boolean istGruen(int autoPos, Richtung fahrtRichtung){
		boolean gruen=false;
		Richtung ampelStellung;	
		Ampel naechsteAmpel=mittelstreifen[autoPos];
		while((naechsteAmpel==null)&& !((autoPos>1)||(autoPos<mittelstreifen.length-1))){
			if(fahrtRichtung.ordinal()==0){  //nachRechts
				autoPos++;
			}
			else{	// nach links
				autoPos--;
			}
			naechsteAmpel=mittelstreifen[autoPos];
		}
		ampelStellung= naechsteAmpel.getRichtung();
		
		if(ampelStellung==fahrtRichtung){
			gruen = true;
		}// else waere false aber das ist der default wert
		return gruen;
	}
	
	void setupAmpel(int ort, long intervall, Richtung rOl )throws SimulationsException{// stellt eine neue ampel auf
		if(!((ort<0)||(ort>mittelstreifen.length))){
			if(mittelstreifen[ort]==null){
				mittelstreifen[ort]=new Ampel(intervall,rOl );
				}
			}
		else{
			throw new SimulationsException();
		}
		
		
	}
		/* diese methode wird warscheinlich nicht gebraucht. sie würde nachsehen ob die naechste position des
		 *  autos frei ist, das kann das auto aber selbst. ausserdem macht es keinen sinn 5x abzufraen ob der nächste halt
		 *  einen index höher oder niedriger ist.  code doppelung */
//	public boolean istWegFrei(int pos, Richtung rechtsLinks){
//		boolean frei;
//		if(rechtsLinks.ordinal()==0){
//			pos--;
//			frei=untereFahrBahn[pos];
//		}
//		else{
//			pos++;
//			frei=obereFahrBahn[pos];
//		}
//		return frei;
//	}


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
