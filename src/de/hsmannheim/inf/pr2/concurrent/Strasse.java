package de.hsmannheim.inf.pr2.concurrent;

class Strasse implements Runnable {
	int laenge;
	// sollte diese felder besser in die strasse?
	char[][]strassenSimulation=new char[3][laenge];// simuliert die strasse
	int[] ampelposition={10,30,60,70};	// beispielhalfte ampel positionen:
	Richtung[]ampelStand={Richtung.NACHRECHTS,Richtung.NACHRECHTS, Richtung.NACHLINKS,Richtung.NACHLINKS,Richtung.NACHLINKS};	// professors beispiel
	
/*füllt das strassenarray mit ampeln und mittel line*/	
	private void strasseBauen(){
		int j=0;
		for(int i=0; i<laenge; i++){
			if(i==ampelposition[j]){
				if(ampelStand[j]==Richtung.valueOf("NACHRECHTS")){
					strassenSimulation [1][i]='>';
				}
				else{
					strassenSimulation [1][i]='<';
				}
				j++;
			}
			else{	
			strassenSimulation[1][i]='.';
			}
		}
	}
	
	
	
	public void run() {
		// TODO Auto-generated method stub

	}
	
	/*Schreiben Sie eine Klasse Strasse , die eine Straÿe symbolisiert. Die Straÿe hat eine
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
