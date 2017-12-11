package de.hsmannheim.inf.pr2.coll;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Ein binärer Suchbaum.
 *
 * @author Markus Gumbel
 */
public class SearchTree<E extends Comparable<E>> implements Set<E> {

  public TreeNode<E> root; // Wurzelknoten dieses Suchbaums.
  private ArrayList <E> sortierteListe=new ArrayList<E>();


  /**
   * Füge einen Wert in den Baum hinzu. Das geht nur, wenn dieser Wert noch
   * nicht enthalten ist.
   *
   * @param value Einzufügender Wert.
   * @return True, wenn Wert hinzugefügt wurde, oder false, wenn nicht, da
   * dieser schon vorhanden war.
   */
  public boolean add(E value) {
    // Strategie: Wir suchen den Knoten, der nach dem Einfügen des neues
    // Elements der Elternknoten ist.

    TreeNode<E> parent = null; // (Vorläufiger) Elternknoten
    TreeNode<E> node = root; // (Vorläufiger) Kindknoten

    // Solange der aktuelle Kindknoten nicht null ist...
    while (node != null) {
      // Setze alten Kindknoten als neuen Elternknoten:
      parent = node;
      // Ist der Wert bereits in diesem Knoten gespeichert?
      if (node.getValue().equals(value)) {
        return false; // Ja, also kann er nicht nochmal eingefügt werden.
      } else if (value.compareTo(node.getValue()) < 0) {
        // Der einzufügende Wert ist kleiner als der aktuelle Knoten.
        node = node.getLeft(); // D.h. suche im linken Teilbaum weiter.
      } else {
        // Der einzufügende Wert ist größer als der aktuelle Knoten.
        node = node.getRight(); // D.h. suche im rechten Teilbaum weiter.
      }
    }
    // Erzeuge den neuen Knoten für den einzufügenden Wert:
    TreeNode<E> newNode = new TreeNode<>(value);
    if (parent == null) {
      // Kein Elternknoten gefunden. D.h. Baum ist leer.
      root = newNode; // Neuer Knoten wird Wurzelelement.
    } else if (value.compareTo(parent.getValue()) < 0) {
      // Hier wird festgestellt, ob der neue Knoten der rechte oder linke
      // Nachfolger des Elternknotens ist.
      parent.left = newNode; // In diesem Fall links.
    } else {
      parent.right = newNode; // Dito für rechts.
    }
    return true; // Neuer Wert konnte erfolgreich eingefügt werden.
  }

  /**
   * Suche ein Element in dem Baum.
   *
   * @param o Suchschlüssel
   * @return Wahr, wenn Wert im Baum vorhanden ist, falsch sonst.
   */
  public boolean contains(E o) {
    // return containsRec(root, o); // falls rekursiv.
    return containsIntern(root, o);
  }


  /**
   * Suche ein Element in dem Teilbaum, der durch den Noten node gegeben ist.
   *
   * @param node Wurzel-Element des Teilbaums.
   * @param o    Suchschlüssel
   * @return Wahr, wenn Wert im Teilbaum vorhanden ist, falsch sonst.
   */
  private boolean containsIntern(TreeNode<E> node, E o) {
    TreeNode<E> n = node; // Erzeuge Zeiger, der bei node beginnt.

    while (n != null) {
      if (n.getValue().equals(o)) {
        return true; // Element gefunden.
      } else if (o.compareTo(n.getValue()) < 0) {
        // Suchschlüssel kleiner, also im linken Teilbaum weitersuchen.
        n = n.getLeft();
      } else {
        // Suchschlüssel größer, also im rechtenTeilbaum weitersuchen.
        n = n.getRight();
      }
    }
    return false;     // Suche zu Ende, Wert nicht gefunden.
  }

  /**
   * Suche ein Element in dem Teilbaum, der durch den Noten node gegeben ist.
   * Rekursive Variante.
   *
   * @param node Wurzel-Element des Teilbaums.
   * @param k    Suchschlüssel, i.e. int-Wert
   * @return Wahr, wenn Wert im Teilbaum vorhanden ist, falsch sonst.
   */
  private boolean containsRec(TreeNode<E> node, E k) {
    if (node == null) {
      // Teilbaum leer, also kann k nicht enthalten sein.
      return false;
    } else {
      if (node.getValue().equals(k)) {
        // Element bereits gefunden.
        return true;
      } else if (k.compareTo(node.getValue()) < 0) {
        // Suchschlüssel kleiner, also im linken Teilbaum weitersuchen.
        return containsRec(node.left, k);
      } else {
        // Suchschlüssel größer, also im rechten Teilbaum weitersuchen.
        return containsRec(node.right, k);
      }
    }
  }

  /**
   * Entfernt einen Knoten mit dem Wert k aus dem Baum.
   *
   * @param o Schlüssel, der entfernt werden soll
   * @return True, falls Element entfernt wurde, false sonst.
   */
  public boolean remove(E o) {
    return remove(root, o);
  }

  /**
   * Entfernt einen Knoten mit dem Wert k aus dem Teilbaum, der durch den
   * Wurzelknoten n beschrieben ist.
   *
   * @param n (Wurzel-)Knoten des Teilbaums
   * @param o Schlüssel (int-Wert), der entfernt werden soll
   * @return True, falls Element entfernt wurde, false sonst.
   */
  private boolean remove(TreeNode<E> n, E o) {
    // NOCH NICHT IMPLEMENTIERT!
    return false;
  }

  public Iterator<E> iterator() {
    // Noch nicht implementiert!
    return null;
  }

  /**
   * Überprüft, ob der Baum Elemente enthält.
   *
   * @return Wahr, wenn der Baum leer ist, falsch sonst.
   */
  public boolean isEmpty() {
    return root==null;	 //gibt true zuruek wenn baum leer ist;
  }

  /**
   * @return Anzahl der Elemente in dem Baum.
   */
  public int size() {
    if (isEmpty()) { // Sonderfall leerer Baum beachten!
      return 0; // Leerer Baum hat Größe 0.
    } else {
      return root.size(); // Nimm Größe des fkt. Baums.
    }
  }

  /**
   * Entfernt alle Elemente aus dem Baum.
   */
  public void clear() {
    // NOCH NICHT IMPLEMENTIERT!
  }

  /**
   * Bestimme die Höhe des Baums.
   *
   * @return
   */
  public int height() {
    // NOCH NICHT IMPLEMENTIERT!
    return 0;
  }

  /**
   * Überprüfe, ob dieser Baum die gleiche Struktur wie der
   * andere Baum hat.
   *
   * @param otherSearchTree
   * @return
   */
  public boolean equalStructure(SearchTree<E> otherSearchTree) {
	  // aufgabe 2.2 implementiert in TreeNode(andeere klasse)
    return root.equalStructure(otherSearchTree.root);
  }

  public void printPreorder() {
    // NOCH NICHT IMPLEMENTIERT!
  }

  public void printInorder() {
    if (!isEmpty()) {
      root.printInorder();
    }
  }

  public void printPostorder() {
    // NOCH NICHT IMPLEMENTIERT!
  }

  public void printLevelorder() {
    // NOCH NICHT IMPLEMENTIERT!
  }

//  public E getValue(){
//	  return root.getValue();
//  }
  

  public ArrayList<E> sort() {
	    if (this.root != null){
	      return root.getList();
	    }
	    else{
	      return new ArrayList<>();
	    }
	}
}
