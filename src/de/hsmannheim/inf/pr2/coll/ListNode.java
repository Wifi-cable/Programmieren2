package de.hsmannheim.inf.pr2.coll;

/**
 * Ein Knoten einer verketteten Liste, der <code>int</code>-Elemente aufnehmen kann.
 * </p>
 * Diese Klasse zeigt kompletten Source-Code von Beispielen, die in der
 * Vorlesung gezeigt werden. Es werden bewusst nicht alle Eigenschaften von Java
 * genutzt, da diese zum Zeitpunkt der Vorlesung offiziell nicht bekannt sind.
 *
 */
public class ListNode<E>  {
  /**
   * Wert, den dieser Knoten aufnimmt.
   */
  public E value;

  /**
   * Nachfolger dieses Knotens. null bedeutet: kein Nachfolger.
   */
  public ListNode next = null;

  // Konstruktoren

  /**
   * Erzeugt einen neuen Knoten.
   *
   * @param value Wert dieses Knotens
   * @param n     Nachfolger-Knoten
   */
  public ListNode(E value, ListNode n) {
    this.value = value;
    next = n;
  }


  /**
   * Erzeugt einen neuen Knoten (neue Liste).
   *
   * @param value int-Wert dieses Knotens
   */
  public ListNode(E value) {
    this.value = value;
  }

  /**
   * @return Den Wert des ersten Knotens der Liste.
   */
  public E getHead() {
    return value;
  }

  /**
   * @return Die Restliste.
   */
  public ListNode<E>  getTail() {
    return next;
  }

  /**
   * Erzeugt einen neuen Listenknoten und lässt ihn auf diese
   * Liste zeigen.
   *
   * @param value Wert des neuen Listenknotens.
   * @return Die neue Liste mit neuem Knoten am Anfang.
   */
  public ListNode<E>  addFirst(E value) {
    return new ListNode<>(value, this);
  }


  /**
   * @return Die Anzahl der Elemente in der Liste.
   */
  public int size() {
    if (getTail() == null) { // Kein Nachfolger-Knoten vorhanden?
      return 1; // Liste hat genau ein Element.
    } else {
      return 1 + getTail().size();
    }
  }

  /**
   * Gibt das Element auf die Console aus (ohne Zeilenumbruch).
   */
  public void print() {
    System.out.print(value + " ");
  }

  public String toString() {
    return value + " ";
  }
	 public  boolean contains(E e){
//		 System.out.println(value);
//		 if(this.getTail()==null){
//			 System.out.println("folge nicht dem null pointer");
//		 }
		 
		  if(this.getTail()!=null){
			  
			  if(this.value.equals(e)){
				  System.out.println("hab eins gefunden");
				  return true;
			  }
			  else{ 
				  System.out.println("suche "+e.hashCode()+" ist nicht gleich "+value.hashCode()+" suche weiter");
				return this.getTail().contains(e);
			  }
		  }
		  else{
		  return false;
		  }
	  }
		int coutIf(E e){
			  return 0;
		  }
}
