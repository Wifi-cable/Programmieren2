package de.hsmannheim.inf.pr2.concurrent;

class Strasse  {
	int laenge;
	private boolean[]rechteFahrBahn=new boolean[laenge];
	private boolean[]linkeFahrBahn=new boolean[laenge];
	private Richtung[]ampeln= new Richtung[laenge];
	
	
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
	void SetPark(int p, boolean[]fahrbahn){
		fahrbahn[p]=false;	// ist da noch frei? false!
	}
	// setter für farbahn array, gibt eine stelle wieder frei.
	void drive(int p, boolean []fahrbahn){
		fahrbahn[p]=true;
	}
	
	boolean istGruen(int ampelPos){
		boolean gruen=false;
	//	if(ampeln[ampelPos]){
			gruen = true;
		//}
		return gruen;
	}
	//setter für die ampeln
	void ampelUmschalten(Ampel i){}	// nicht sicher ob das boolean sein soll... ennums und so
	

	private void strasseBauen(){
//		int j=0;
//		for(int i=0; i<laenge; i++){
//			if(i==ampelposition[j]){
//				if(ampelStand[j]==Richtung.valueOf("NACHRECHTS")){
//					strassenSimulation [1][i]='>';
//				}
//				
//				else if(ampelStand[j]==Richtung.valueOf("NACHLINKS")){}
//				else{
//					strassenSimulation [1][i]='>';
//				}
//				j++;
//			}
//			else{	
//			strassenSim{}ulation[1][i]='.';
//			}
//		}
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
