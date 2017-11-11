package de.hsmannheim.inf.pr2.concurrent;

public class Ampel implements Runnable {
	long umschaltzeit;
	private Richtung RechtsOLinks= Richtung.NACHRECHTS;	// ist grün für die autos die nach rechts wollen oder für die autos die nach links wollen?
	int standort;
	Strasse dieStrasse;
	Ampel(long umschaltzeit, int standort,Strasse dieStrasse) throws SimulationsException{	//constructor
		
			this. umschaltzeit=umschaltzeit;
			this.standort= standort;
			this.dieStrasse=dieStrasse;
	}		

	public void setStandord(int k){
		
	}
	 public Richtung getRichtung(){
		return RechtsOLinks;
	 }

	public void run() {	//schaltet um
		try{
		Thread.sleep(umschaltzeit);
		}
		catch(Exception e){
			System.out.println("Tote Threads kann man nicht schlafen legen.");
			e.getMessage();
		}
		synchronized (dieStrasse) {
		if( this.RechtsOLinks==Richtung.NACHRECHTS){	// wenn sie vorher nach rechts zeigt, lass sie nach  links zeigen
			RechtsOLinks=Richtung.NACHLINKS;
		}
		else{
			RechtsOLinks=Richtung.NACHRECHTS;	
		}
		dieStrasse.notifyAll();
		}
	
		try {
			displayAmpelChar();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	

	
	private void displayAmpelChar() throws Exception{
		if(this.getRichtung().ordinal()==0){
			 dieStrasse.setAnzeigeArray('>', standort, 1);
		}
		else {
			dieStrasse.setAnzeigeArray('<', standort, 1);
		}
	}
	/*Schreiben Sie eine Klasse Ampel , die eine Ampel an einer Engstellen repräsentiert. Eine
Ampel lässt immer nur Autos in die eine oder andere Richtung durchfahren, d. h. sie
zeigt auf der einen Seite Rot und auf der anderen Grün an. Ampeln können an belie-
bigen Stellen auf der Straÿe positioniert werden und haben als wichtigste Eigenschaft
die Zeit in Sekunden, nach der die Ampel die Anzeige wechselt, d. h. die Fahrzeuge aus
der anderen Richtung fahren lässt.*/
	
	

}
