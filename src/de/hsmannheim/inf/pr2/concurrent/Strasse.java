package de.hsmannheim.inf.pr2.concurrent;

class Strasse  {
	int laenge;
	private volatile boolean[]obereFahrBahn;	//nachLinks
	private volatile boolean[]untereFahrBahn;	//nachRechts
	private volatile Ampel[]mittelstreifen;
	private volatile char[][]anzeigeArray;	//damit man das ganze gut ausgeben kann
	
	public Strasse(int laenge){		//constructor  
		this.laenge=laenge;
		obereFahrBahn=new boolean[laenge];	//nachLinks
		untereFahrBahn=new boolean[laenge];	//rechs
		mittelstreifen= new Ampel[laenge];
		anzeigeArray= new char[laenge][3];
		
		
		for(int i=0; i<laenge; i++){	//keine ampel
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
	/*methode zum updaten des anzeige arrays.
	 * braucht  den char den es an anderer stelle anzeigen soll, ampelrichtungsfeil oder autochar oder leerzeichen(fuer
	 * den leeren platz wo ein auto vorher war)
	 * autos loeschen  nicht automatisch ihre letzte postion, da muss ein leerzeichen hin geschrieben werden*/
	protected void setAnzeigeArray(char ampelAuto, int strassenKM, int fahrbahn )throws Exception{
		if(fahrbahn>2||(strassenKM>anzeigeArray.length)){
			System.out.println("du bist auf dem holzweg, das ist jedenfalls keien strasse mehr");
			throw new Exception();
		}
		anzeigeArray[strassenKM][fahrbahn]= ampelAuto;
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
		if(naechsteAmpel==null){	// array ende erreicht, aber es GIBT keine ampel mehr
			return true;		//wenn keine ampel da ist kann sie nicht rot sein.
		}
		ampelStellung= naechsteAmpel.getRichtung();
		
		if(ampelStellung==fahrtRichtung){
			gruen = true;
		}// else waere false aber das ist der default wert
		return gruen;
	}
	
	Ampel setupAmpel(int ort, long intervall, Strasse strassenObject )throws SimulationsException{// stellt eine neue ampel auf
		Ampel factoryAmpel=null;
		if(!((ort<0)||(ort>mittelstreifen.length))){	// wenn glütitger standort angegeben wird
			if(mittelstreifen[ort]==null){
				factoryAmpel=new Ampel(intervall, ort, strassenObject );
				mittelstreifen[ort]=factoryAmpel;
				}
			}
		else{		//beschwert sich methode und gibt null zurück
			System.out.println("wo keine strasse ist, kann man keine ampel aufstellen");
			throw new SimulationsException();
		}
		System.out.println("ampel wurde gebaut");	//debug statement
		
		return factoryAmpel;
		
	}
	
	/*factory artige methode die dir einen auto object baut und es gleich ins array packt.*/
	Auto setupAuto(int position, int geschwindikeit, Richtung fahrtRichtung, Strasse strassenObject, char name)throws SimulationsException{
		System.out.println("strasse baut gleich auto");
		Auto neuwagen=null;
		if(!((position<0)||(position>mittelstreifen.length))){	// wenn die postion gültig ist
			// welche fahrbahn? 
			boolean[]temp=strassenObject.getFahrbahnArray(fahrtRichtung);
			if(temp[position]==false){	// steht und da noch kein auto steht
				getFahrbahnArray(fahrtRichtung)[position]=true;				// geht das? 
				neuwagen=new Auto(position,geschwindikeit, fahrtRichtung, strassenObject, name);
				int spuhr=berechneAnzeigeFahrbahn(fahrtRichtung);
				try {
					strassenObject.setAnzeigeArray(name, position, spuhr);
				} catch (Exception e) {
					e.printStackTrace();
				}
				}	
			}
		else{
			System.out.println("das mit dem auto bauen klappt so nicht !");
			throw new SimulationsException();
		}
		//(int position, int geschwindigkeit, Richtung LR, Strasse meineStrasse, char name)
		return neuwagen;
	}
	int berechneAnzeigeFahrbahn(Richtung richtung){
		int ret;
		if (richtung.ordinal() == 0) {
			ret = 2;
		}
		else{
			ret = 0;
		}
		return ret;
	}
//	void syntaxcheck(){
//	Strasse nameDesStrassenobjectes= new Strasse (3);
//	try {
//		Ampel nameDerAmpel=nameDesStrassenobjectes.setupAmpel(1, 50, nameDesStrassenobjectes);
//	} catch (SimulationsException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
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
