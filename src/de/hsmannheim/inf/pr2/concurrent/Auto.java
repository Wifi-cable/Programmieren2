package de.hsmannheim.inf.pr2.concurrent;

public class Auto implements Runnable {
	int position, geschwindikeit;
	char name;
	Richtung whereTo;//ennum rechts oder links
	boolean endOfRoad;
	int lenghtOfRoad;
	
	public Auto(int position, int geschwindikeit,Richtung LR,int lenghtOfRoad){	//constructor
		this.position=position;
		this.geschwindikeit=geschwindikeit;
		whereTo=LR;
		this.lenghtOfRoad=lenghtOfRoad;
		}
	

	public void run() {
		while(!endOfRoad){
			try {
				Thread.sleep( geschwindikeit);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			drive();
		}
		/* hier kommt der code rein der paralell laufen kann. mehrere autos können schliesslich auf der 
		 * selben strasse fahren. */

	}
/*Schreiben Sie eine Klasse Auto , die die Autos darstellt, die auf der Straÿe fahren kön-
nen. Ein Auto kann an einer beliebigen Stelle der Straÿe losfahren und fährt in eine
der beiden möglichen Richtungen. Jedes Auto hat eine bestimmte Geschwindigkeit in
km/Stunde, mit der es auf der Straÿe fährt. Weiterhin haben Autos noch einen ein-
buchstabigen Namen, um sie in der Simulation besser darstellen zu können.
Für die Angabe der Fahrtrichtung benutzen Sie bitte eine Enumeration Richtung
und zur Anzeige von ungültigen Eingabewerten (Positionen etc.) eine Runtime-Exception
namens SimulationsException .*/
	void drive(){}
	
	boolean isEndOfRoad(){
	return false; // wenn es nix zurück gibt ist eclipse unglücklich. 
	}
	/*kann der fahrere überhaubt fahren
	 * checkliste
	 * -strasse noch nicht vorbei (arry grenzen verlezten ist bloed)
	 * - kein auto im array feld direkt vor ihm,  (inhalt an array stelle überschreiben, unfall ist bloed)
	 * - ist ampel gruen?  (darf man in der richtung fahren in die er will) */
	boolean canDrive(){
		boolean dr=false;
		if((!endOfRoad)&&(green())){
			dr=true;
		}
		
		return dr;
	}
	boolean green(){// welche ampel ist die nächste, und ist sie grün? 
		return false;
	}
}
