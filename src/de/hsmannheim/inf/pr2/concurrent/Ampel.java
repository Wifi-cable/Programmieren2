package de.hsmannheim.inf.pr2.concurrent;

public class Ampel implements Runnable {
	long umschaltzeit;
	private Richtung RechtsOLinks;	// ist grün für die autos die nach rechts wollen oder für die autos die nach links wollen?
	// ist es der ampel wichtig wo sie steht?
	int standort;
	Ampel(long umschaltzeit, Richtung RechtsLinks) throws SimulationsException{	//constructor
		if((RechtsLinks==Richtung.NACHLINKS)||(RechtsLinks==Richtung.NACHRECHTS)){
			
			this. umschaltzeit=umschaltzeit;
			this.RechtsOLinks=RechtsLinks;
		}
		else{
			throw new SimulationsException();
		}

	}
	

	public void run() {	//schaltet um
		try{
		Thread.sleep(umschaltzeit);
		if( this.RechtsOLinks==Richtung.NACHRECHTS){
			RechtsOLinks=Richtung.NACHLINKS;
		}
		else{
			RechtsOLinks=Richtung.NACHRECHTS;
		}
		
		
		}
		catch(Exception e){
			System.out.println("Tote Threads kann man nicht schlafen legen.");
			e.getMessage();
		}
		

	}
	
	Richtung getRichtung(){
		return RechtsOLinks;
	}
	/*Schreiben Sie eine Klasse Ampel , die eine Ampel an einer Engstellen repräsentiert. Eine
Ampel lässt immer nur Autos in die eine oder andere Richtung durchfahren, d. h. sie
zeigt auf der einen Seite Rot und auf der anderen Grün an. Ampeln können an belie-
bigen Stellen auf der Straÿe positioniert werden und haben als wichtigste Eigenschaft
die Zeit in Sekunden, nach der die Ampel die Anzeige wechselt, d. h. die Fahrzeuge aus
der anderen Richtung fahren lässt.*/
	
	

}
