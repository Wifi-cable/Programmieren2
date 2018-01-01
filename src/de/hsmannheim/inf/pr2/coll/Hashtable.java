package de.hsmannheim.inf.pr2.coll;

import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Eine Hashtable, die lineares Sondieren unterstützt.
 *
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Hashtable<E> implements Set<E> {

  protected HashtableEntry<E>[] hashtable;
  private int c = 1; // Faktor für die Sondierung.

  /**
   * Erzeugt eine Hashtabelle der Kapazität 100, die
   * lineares Sondieren mit der Schrittweite c = 1 nutzt.
   */
  public Hashtable() {
    this(100);
  }
  /**
   * Erzeugt eine Hashtabelle mit der Kapazität capacity,
   * die lineares Sondieren mit Schrittweite c = 1 verwendet.
   *
   * @param capacity Die interne Größe des genutzten Arrays.
   */
  public Hashtable(int capacity) {
    this(capacity, 1);
  }


  /**
   * Erzeugt eine Hashtabelle mit der Kapazität capacity,
   * die lineares Sondieren mit Schrittweite c verwendet.
   *
   * @param capacity Die interne Größe des genutzten Arrays.
   * @param c        Schrittweite
   */
  public Hashtable(int capacity, int c) {
    hashtable = (HashtableEntry<E>[]) new HashtableEntry[capacity];
    clear();
    this.c = c;
  }

  /**
   * Fügt ein Element, sofern nicht vorhanden, hinzu.
   *
   * @return True wenn das Element eingefügt werden konnte, sonst false.
   */
  public boolean add(E element) {
    int oIdx = hash(element.hashCode(), 0); // Erstes versuchtes Bucket.
    int idx = oIdx;              // Lauf-Index.
    int i = 1;
    while (!(hashtable[idx].isEmpty() || hashtable[idx].isSame(element))) {
	
      idx = hash(oIdx, i);      // Alternativplatz.
      if (idx == oIdx) { // Erster Indexplatz wieder erreicht?
        throw new ContainerException("Hashtable full!");
      }
      i++;
    }
    if (hashtable[idx].isSame(element)) { // Element bereits enthalten?
      return false;     // Einfügen wird abgelehnt.
    } else {
      hashtable[idx].setElement(element);
      return true;
    }
  }

  /**
   * Überprüft, ob ein Element in der Hashtable enthalten ist.
   *
   * @param element Das gesuchte Element.
   * @return True wenn die Hashtable das Element beinhaltet, sonst false.
   */
  public boolean contains(E element) {
    int oIdx = hash(element.hashCode(), 0); // Erstes versuchtes Bucket.
    int idx = oIdx;
    int i = 1; 
   // while (!hashtable[idx].isEmpty()) {
    while(hashtable[idx]!=null){
      if (hashtable[idx].isSame(element)&& (!hashtable[idx].isDeleted())){
        return true;
      }
      idx = hash(oIdx, i);    // Alternativplatz.
      if (idx == oIdx) {
        // Alle buckets wurden besucht, aber das Element nicht gefunden.
        return false;
      }
      i++;
    }
    return false;
  }

  /**
   * Löscht ein Element, sofern vorhanden, aus der Hashtabelle.
   *
   * @param element Zu löschender int-Wert.
   * @return True, wenn gefunden und gelöscht, sonst false.
   */
  
  
  public boolean remove(E element) {	//finde tableEntry setze auf überschreibbar

	  int oldIndex = hash(element.hashCode(), 0); // Erstes versuchtes Bucket.
	    int index = oldIndex;
	    int i = 1; 

	    while(hashtable[index]!=null){	// suche bis du bei einem leeren eintrag bist, denn element ist vieleicht nicht wo erwartet (kollision)
	      if (hashtable[index].isSame(element)&& (!hashtable[index].isDeleted())){
	    	  hashtable[index].delete();	// wenn du es gefunden hast, loesche es,
	        return true;
	      }
	      index = hash(oldIndex, i);    // Alternativplatz suchen
	      if (index == oldIndex) {
	        // Alle buckets wurden besucht, aber das Element nicht gefunden  oder es ist schon gelöscht
	        return false;
	      }
	      i++;
	    }
	    return false; 
	  

  }

  public Iterator<E> iterator() {
  
    return null;	// next zeiger auf hashtable entry.  aber was ist das nächste? 
  }

  /**
   * Ermittelt die Größe der Hashtabelle durch Zählen.
   * Hinweis: Eine schnellere Lösung wäre, jedesmal beim
   * Hinzufügen oder Entfernen von Elementen einen Zähler
   * zu verändern.
   *
   * @return
   */
  public int size() {
    int c = 0;
    for (HashtableEntry<E> e : hashtable) {
      if (!e.isEmpty()) {
        c++;
      }
    }
    return c;
  }

  /**
   * Entfernt alle Element aus der Hashtabelle.
   */
  public void clear() {
    for (int i = 0; i < hashtable.length; i++) {
      hashtable[i] = new HashtableEntry<>();
    }
  }

  /**
   * Lineares Sondieren.
   *
   * @param idx Ursprünglicher Indexplatz, bei dem Kollision auftritt.
   * @param i   i-tes Sondieren
   * @return Neuer Indexplatz.
   */
  protected int hash(int idx, int i) {
	  int ret=(idx + c * i) % hashtable.length;
	  if(ret<0){
		  ret= ret*-1;
	  }
    return ret;
  }

  /**
   * Erzeugt eine tabellarische Darstellung der gesamten
   * Hashtabelle.
   *
   * @return
   */
  public String mkString() {
    return IntStream.range(0, hashtable.length).mapToObj(i ->
            i + ": " + hashtable[i]
    ).collect(Collectors.joining("\n"));
  }

}

/**
 * Hilfsklasse für einen Eintrag eines Bucketplatzes in der
 * Hashtable.
 *
 * @param <E>
 */
class HashtableEntry<E> {	//alles hiernach ist eine innere klasse!
  private E element = null;
  private boolean deleted = false;	// felder

  public E getElement() {	//getter
    return element;
  }

  /**
   * Schreibe das Element in das zugehörige Bucket.
   *
   * @param element
   */
  public void setElement(E element) {
    this.element = element;
    deleted = false;
  }
  public boolean isDeleted(){
	  return deleted;
  }

  /**
   * Überprüfe, ob da gespeicherte Element dasselbe ist wie der
   * Parameter.
   *
   * @param element Zu vergleichendes Element.
   * @return Wahr, wenn gleich, falsch sonst.
   */
  public boolean isSame(E element) {
    if (isEmpty()) {
      return false; // Leers Feld ist niemals gleich.
    } else {
      return this.element.equals(element); // Frage element selbst.
    }
  }

  /**
   * @return Wahr, wenn dieser Bucketplatz leer ist.
   */
  public boolean isEmpty() {
    return element == null || deleted;
  }

  /**
   * Gib diesen Bucketplatz zum Überschreiben frei.
   */
  public void delete() {
    if (!deleted) {
      deleted = true;
    } else {
      throw new ContainerException("Bucket already deleted!");
    }
  }

  public String toString() {
    if (isEmpty()) {
      if (deleted) {
        return "<* deleted>";
      } else {
        return "<empty>";
      }
    } else {
      return element.toString();
    }
  }
}
