/**
 * 
 */
package de.hsmannheim.inf.pr2.coll;

/**
 * @author wifi-cable
 *
 */
public class ListNode<E> {

	/**
	 * 
	 */
	public ListNode() {
		// TODO Auto-generated constructor stub
	}
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
	    return new ListNode<E>(value, this);
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

}
