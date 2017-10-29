package de.hsmannheim.inf.pr2.concurrent;

public class BaustellenSimulation {
	int len;
	public BaustellenSimulation(int len){
		this.len=len;
	}
	//braucht ein strassen object. warscheinlich mit ampel objecten und auto objecten
	
	
	public static void main(String[] args) {
	

	}
	/*In einer Klasse BaustellenSimulation , die auch die main ()-Methode enthält, werden
	nun die Fahrzeuge und Ampeln auf der Straÿe positioniert und die Simulation wird
	gestartet. Nach jeder Veränderung des Zustandes soll auf der Konsole die Straÿe mit
	den Ampeln und die Positionen der Fahrzeuge ausgegeben werden. Bei den Ampeln
	wird durch ein > bzw. < angezeigt, dass die Fahrzeuge von links nach rechts bzw. von
	rechts nach links fahren dürfen. Eine beispielhafte Ausgabe sieht dann wie folgt aus:
	
	*In diese beispielhaften Ausgabe warten die Fahrzeuge D, C und A gerade an einer
	Ampel. Modellieren Sie die Ampeln und Fahrzeuge als Threads, die eigenständig lau-
   fen und über entsprechende Mittel der Threadkoordination miteinander interagieren.
   Sorgen Sie dafür, dass alle kritischen Abschnitte richtig geschützt sind und dass sich
   die Threads sauber beenden, wenn das Programm zu Ende gelaufen ist. Die Simulation
   ist dann beendet, wenn alle Fahrzeuge am Ende der Straÿe angekommen sind. Rechnen
   Sie bei den Fahrzeugen die Geschwindigkeiten von km/h einfach eins-zu-eins in km/sec
 um, damit die Simulation hinreichend schnell abläuft.*/
	
	static int gibOrdinal( String ampelnummer ){	// hilfsmehtode ist die ampel rot oder grün?
	  try {
	    return Richtung.valueOf( ampelnummer ).ordinal();
	  }
	  catch ( IllegalArgumentException e ) {
	    return -1;
	  }
	}
}
