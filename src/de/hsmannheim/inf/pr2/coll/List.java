package de.hsmannheim.inf.pr2.coll;

import java.util.Iterator;



/**
 * @author Markus Gumbel
 */
public class List<E> implements Container<E> {
  /**
   * Listenkopf.
   */
  protected ListNode<E> head = null;

  /**
   * Falls bereits ein Element in neuer Liste sein soll.
   *
   * @param value Wert des ersten Elements.
   */
  public List(E value) {
    head = new ListNode(value, null);
  }

  /**
   * Erzeugt eine leere Liste.
   */
  public List() {
  }

  public boolean add(E value) {
    addLast(value);
    return true;
  }

  public void addFirst(E value) {
    if (isEmpty()) {
      head = new ListNode(value); // neuer Head-Knoten.
    } else {
      head = head.addFirst(value);
    }
  }


  /**
   * F�gt ein Element an das Ende der Liste an.
   *
   * @param value Element, das eingef�gt werden soll.
   */
  public void addLast(E value) {
    ListNode node = new ListNode(value); // Erzeuge Knoten.
    // Das Ende der Liste suchen:
    ListNode p = head; // Hilfsvariable.
    if (p == null) { // Leere Liste?
      head = node; // Head ist jetzt der neue Knoten.
    } else { // Liste enth�lt Elemente.
      while (p.getTail() != null) { // p am Ende?
        p = p.getTail(); // p wandert weiter.
      }
      p.next = node; // F�ge am Ende an.
    }
  }

  /**
   * �berpr�ft, ob die Liste Elemente enth�lt.
   *
   * @return Wahr, wenn die Liste leer ist, falsch sonst.
   */
  public boolean isEmpty() {
    // Es k�nnte auch �berpr�ft werden, ob size() 0 liefert,
    // dieser Test ist jedoch schneller:
    return head == null;
  }

  /**
   * Entfernt alle Elemente aus der Liste.
   */
  public void clear() {
    head = null;
  }

  /**
   * @return Anzahl der Elemente in der Liste.
   */
  public int size() {
    if (head != null) {
      return head.size();
    } else {
      return 0;
    }
  }

  /**
   * Gibt die Liste auf die Console aus.  Eine bessere L�sung ist toString().
   */
  public void print() {
    System.out.print("( ");
    ListNode p = head;
    while (p != null) {
      p.print();
      System.out.print(" ");
      p = p.getTail();
    }
    System.out.print(")");
  }

  /**
   * Erzeugt eine (kurze) Textdarstellung der Liste. Diese Methode hat
   * jede Java-Klasse.
   *
   * @return Textdarstellung der Liste.
   */
  public String toString() {
    // Anmerkung: StringBuffer w�re die bessere L�sung. Egal.
    String text = "";
    ListNode<E> p = head;
    while (p != null) {
      text += p.toString() + " ";
      p = p.getTail();
    }
    return "( " + text + ") ";
  }

  // --- Einige Methoden sind noch nicht implementiert. ---

  /**
   * @return Der Wert des ersten Elements oder -1, wenn die Liste leer ist.
   */
  public E getFirst() {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }

  /**
   * @return Der Wert des letzten Elements oder -1, wenn die Liste leer ist.
   */
  public E getLast() {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }

  /**
   * Entfernt das erste Element aus der Liste.
   *
   * @return Der Wert des ersten Elements oder -1, wenn die Liste leer ist.
   */
  public E removeFirst() {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }

  /**
   * Entfernt das letzte Element aus der Liste.
   *
   * @return Der Wert des letzten Elements oder -1, wenn die Liste leer ist.
   */
  public E removeLast() {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }

  /**
   * @param idx
   * @return Wert an Position <code>idx</code> oder -1 im Fehlerfall,
   * wenn der Index ung�ltig ist.
   */
  public E getAt(int idx) {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }

  /**
   * �berschreibe den Wert an Position <code>idx</code> mit <code>value</code>.
   * Es wird eine Fehlermeldung ausgegeben, wenn <code>idx</code> eine
   * �ng�ltige Position ist.
   *
   * @param idx
   * @param value
   */
  public void setAt(int idx, E value) {
    // NOCH NICHT IMPLEMENTIERT!
  }

  public boolean remove(Object e) {
    // NOCH NICHT IMPLEMENTIERT!
    return false;
  }

  public E removeAt(int idx) {
    // NOCH NICHT IMPLEMENTIERT!
    return null;
  }
// gibt ein Iterator object der inneren selbst implementierten klasse zurück
  public Iterator<E> iterator() {
    return new ListIterator(head);
  }

  /**
   * F�gt eine andere Liste an das Ende dieser Liste an.
   * Achtung! Die angef�gte Liste k�nnte auch weiterhin
   * doppelt verwendet werden. Das wird hier NICHT gemacht,
   * statt dessen wird die urspr�ngliche Liste geleert.
   *
   * @param otherList Die andere Liste.
   */
  public void concat(List<E> otherList) {
    // NOCH NICHT IMPLEMENTIERT!
  }

  /**
   * �berpr�ft, ob der der Wert <code>value</code> in der Liste
   * enthalten ist.
   *
   * @param value
   * @return
   */
  public boolean contains(Object value) {
    // NOCH NICHT IMPLEMENTIERT!
    return false;
  }
  //innere Iterator klasse  ohne remove
protected class ListIterator implements Iterator<E>{
	ListNode<E> head;
	ListNode<E> current;	//pointer to the current node. starts at head
	//constructor der den head der liste bekommt um über sie zu itterieren
	ListIterator(ListNode <E>head){
		this.head=head;
		current=head;	//auf den anfang setzen
	}
	// gibt true zurück solange der pointer nicht auf der letzten node ist. 
	@Override
	public boolean hasNext() {
		if(head==null){
			return false;
		}
		return current.getTail()!=null;
	}
	//liefert das nächste element wenn die liste nicht leer ist sontzt null
	@Override
	public E next() {
		ListNode <E>currentTail=current;
		E localValue=null;
		if(hasNext()){	// vermeided null pointer
			 localValue=currentTail.value;
			current= current.getTail();	// ListNode ++ geht ja schlecht. 
		}
		return (E)localValue;	//gibt den nächsten wert zurück
	}
	}
}
