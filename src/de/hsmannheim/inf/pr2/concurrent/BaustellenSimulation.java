package de.hsmannheim.inf.pr2.concurrent;

public class BaustellenSimulation {
	
	int len;
	
	public BaustellenSimulation(int len){
		
		this.len=len;
	}
	//braucht ein strassen object. warscheinlich mit ampel objecten und auto objecten
	
	
	public static void main(String[] args) throws SimulationsException {
		
		BaustellenSimulation laenge = new BaustellenSimulation(80);
		
		//Richtung rechts = Richtung.NACHRECHTS;
		//Richtung links = Richtung.NACHLINKS;
		
		Strasse strassenbau = new Strasse(laenge.len);
		
		Runnable eins = strassenbau.setupAmpel(10, 1000, strassenbau);	
		Runnable zwei = strassenbau.setupAmpel(30, 3000, strassenbau);
		Runnable drei = strassenbau.setupAmpel(60, 3000, strassenbau);
		Runnable vier = strassenbau.setupAmpel(70, 2000, strassenbau);
		
		Runnable VW = strassenbau.setupAuto(0, 10, Richtung.NACHRECHTS, strassenbau, 'A');		
		Runnable Peugot = strassenbau.setupAuto(6, 12, Richtung.NACHRECHTS, strassenbau, 'B');
		Runnable FIAT = strassenbau.setupAuto(79, 11, Richtung.NACHLINKS, strassenbau, 'C');
		Runnable Audi = strassenbau.setupAuto(71, 10, Richtung.NACHLINKS, strassenbau, 'D');
		Runnable Skoda = strassenbau.setupAuto(58, 8, Richtung.NACHLINKS, strassenbau, 'E');
		
		//Runnable VW = new Auto(0, 10, Richtung.NACHRECHTS, strassenbau, 'A');
		
		Thread ampel1 = new Thread(eins);
		Thread ampel2 = new Thread(zwei);
		Thread ampel3 = new Thread(drei);
		Thread ampel4 = new Thread(vier);
		
		Thread auto1 = new Thread(VW);
		Thread auto2 = new Thread(Peugot);
		Thread auto3 = new Thread(FIAT);
		Thread auto4 = new Thread(Audi);
		Thread auto5 = new Thread(Skoda);
		
		//Vm sollte sich beenden, sobald alle Nicht Daemon Threads beendet wurden
		ampel1.setDaemon(true);
		ampel2.setDaemon(true);
		ampel3.setDaemon(true);
		ampel4.setDaemon(true);
		
		ampel1.start();
		ampel2.start();
		ampel3.start();
		ampel4.start();
		
		auto1.start();
		auto2.start();
		auto3.start();
		auto4.start();
		auto5.start();
		
		
		// Je nach verwendetes Thread gibt es neue Ausgaben, die auch komplett anderes aussehen
		// Hier werden die Ampeln sp�ter erst ausgegeben, obwohl rein theoretisch jede Zeile durchgegangen wird
		// Thread Auto 5 terminiert nicht
		while (auto5.isAlive()) {
		char anzeige[][] = strassenbau.getAnzeige();
		
			// Anzeige der oberenFahrbahn
			for (int i = 0; i < 80; i++) {
				System.out.print(anzeige[i][0]);
			}
			System.out.println();
			
			//Anzeige der mittlerenFahrbahn
			for (int j = 0; j < 80; j++) {
				System.out.print(anzeige[j][1]);
			}
			System.out.println();
			
			// Anzeige der unterenFahrbahn
			for (int k = 0; k < 80; k++) {
				System.out.print(anzeige[k][2]);
			}
			System.out.println();
		
		
		}
		
		// Wird ein Ampel Thread verwendet, werden die Ampeln erst gegen Ende ausgegeben und alle Amplen bleiben im Zustand < und 
		// das Thread verweilt au�erdem in einer Endlosschleife
		/*while(ampel4.isAlive()) {
			
			char anzeige[][] = strassenbau.getAnzeige();
			
			for (int i = 0; i < 80; i++) {
				for(int j = 0; j < 3; j++) {
					System.out.print(anzeige[i][j]);
				}
			}
			
		}*/


	}
	/*In einer Klasse BaustellenSimulation , die auch die main ()-Methode enth�lt, werden
	nun die Fahrzeuge und Ampeln auf der Stra�e positioniert und die Simulation wird
	gestartet. Nach jeder Ver�nderung des Zustandes soll auf der Konsole die Stra�e mit
	den Ampeln und die Positionen der Fahrzeuge ausgegeben werden. Bei den Ampeln
	wird durch ein > bzw. < angezeigt, dass die Fahrzeuge von links nach rechts bzw. von
	rechts nach links fahren d�rfen. Eine beispielhafte Ausgabe sieht dann wie folgt aus:
	
	*In diese beispielhaften Ausgabe warten die Fahrzeuge D, C und A gerade an einer
	Ampel. Modellieren Sie die Ampeln und Fahrzeuge als Threads, die eigenst�ndig lau-
   fen und �ber entsprechende Mittel der Threadkoordination miteinander interagieren.
   Sorgen Sie daf�r, dass alle kritischen Abschnitte richtig gesch�tzt sind und dass sich
   die Threads sauber beenden, wenn das Programm zu Ende gelaufen ist. Die Simulation
   ist dann beendet, wenn alle Fahrzeuge am Ende der Stra�e angekommen sind. Rechnen
   Sie bei den Fahrzeugen die Geschwindigkeiten von km/h einfach eins-zu-eins in km/sec
 um, damit die Simulation hinreichend schnell abl�uft.*/
	
	static int gibOrdinal( String ampelnummer ){	// hilfsmehtode ist die ampel rot oder gr�n?
		
	  try {
		  
	    return Richtung.valueOf( ampelnummer ).ordinal();
	  }
	  catch ( IllegalArgumentException e ) {
		  
	    return -1;
	  }
	}
}