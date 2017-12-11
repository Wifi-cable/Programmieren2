package de.hsmannheim.inf.pr2.coll;


import java.util.ArrayList;

/**
 * Ein Knoten f�r einen Bin�rbaum.
 * Dieser Knoten kann selbst als eigener Bin�rbaum aufgefasst werden.
 *
 * @author Markus Gumbel
 */
public class TreeNode<E extends Comparable<E>> {

  //value ist auch die Wurzel
  public E value = null;           // Wert des Knotens.
  public TreeNode<E> left = null;  // Linker Teilbaum.
  public TreeNode<E> right = null; // Rechter Teilbaum.

  /**
   * Erzeuge einen neuen Bin�rbaum-Knoten. Nachfolger und Vorg�nger sind nicht
   * gesetzt.
   *
   * @param v Der Wert des Knotens.
   */
  public TreeNode(E v) {
    value = v;
    left = right = null;
  }

  /**
   * Erzeuge einen neuen Bin�rbaum(-Knoten), entspricht tree().
   * @param value Der Wert des Knotens.
   * @param left Der linke Teilbaum.
   * @param right Der rechte Teilbaum.
   */
  public TreeNode(E value, TreeNode<E> left, TreeNode<E> right) {
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public E getValue() {
    return value;
  }

  public TreeNode<E> getLeft() {
    return left;
  }

  public TreeNode<E> getRight() {
    return right;
  }

  /**
   * Bestimme die H�he des Baums.
   *
   * @return
   */
  public int height() {
    // NOCH NICHT IMPLEMENTIERT!
    return 0;
  }

  /**
   * Bestimmt die Anzahl der Elemente in diesem Baum.
   *
   * @return
   */
  public int size() {
    return size(this); // this meint: dieses Objekt.
  }

  /**
   * Bestimmt die Anzahl der Elemente in diesem Baum.
   *
   * @param node Wurzelknoten eines (Teil-)Baums.
   * @return
   */
  private int size(TreeNode<E> node) {
    if (node == null) { // Leerer Baum?
      return 0; // Anzahl Elemente ist 0.
    } else { // Echter Teilbaum.
      // Gr��e ist dieser Knoten plus solche in den Teilb�umen:
      int sizeLeft = size(node.getLeft());
      int sizeRight = size(node.getRight());


      return 1 + sizeLeft + sizeRight;

    }
  }

  public String toString() {
    return value + " ";
  }

  public void printPreorder() {
    // NOCH NICHT IMPLEMENTIERT!
  }

  /**
   * Gibt den Baum ab dem Wurzelknoten in Inorder-Reihenfolge
   * auf die Konsole aus.
   */
  public void printInorder() {
    printInorder(this);
    System.out.println(); // Am Ende noch eine neue Zeile.
  }

  /**
   * Durchl�uft einen (Teil-)Baum vom Wurzelknoten an in
   * Inorder-Reihenfolge und gibt die Werte auf die Konsole aus.
   *
   * @param node Wurzelknoten des (Teil-)Baums
   */
  private void printInorder(TreeNode<E> node) {
    if (node != null) { // Ist der Baum nicht leer?
      // Dann kann weiter gemacht werden.
      printInorder(node.getLeft());
      System.out.println(node.toString());
      printInorder(node.getRight());
    }
  }

  public void printPostorder() {
    // NOCH NICHT IMPLEMENTIERT!
  }

  /**
   * Rekursiver Vergleich auf Gleichheit von zwei bin�ren geordneten B�umen
   * @param otherNode
   * @return
   */
  public boolean equalStructure(TreeNode<E> otherNode) {

    if(this.value == null && otherNode.value == null) {
      return true;
    }

    else if(this != null && otherNode != null && otherNode instanceof TreeNode<?>) {

      if(this.getValue().equals(otherNode.getValue())) {


        if(getLeft() == null && otherNode.getLeft() == null) {
          return true;
        }

        else if(getRight() == null && otherNode.getRight() == null) {
          return true;
        }

        else if(getLeft() != null && otherNode.getLeft() == null) {
          return false;
        }

        else if(getLeft() == null && otherNode.getLeft() != null) {
          return false;
        }

        else if(getRight() != null && otherNode.getRight() == null) {
          return false;
        }


        else if(getRight() == null && otherNode.getRight() != null) {
          return false;
        }

        else if(getLeft().getValue().equals(otherNode.getLeft().getValue())) {

          if(getLeft().equalStructure(otherNode.getLeft())) {

            if(getRight().getValue().equals(otherNode.getRight().getValue())) {

              return getRight().equalStructure(otherNode.getRight());
            }

            else {

              return false;
            }

          }

          else {
            return false;
          }

        }

        else {
          return false;
        }
      }

      else {
        return false;
      }

    }

    else {
      return false;
    }

  }

  // Aufgabe 3
  /**
   * R�ckgabe der ganzen Liste
   * @return
   */
  public ArrayList<E> getList(){
    ArrayList<E> list = new ArrayList<>();
    return fillList(list, this);
  }

  /**
   * Rekusiver aufbau einer Liste, die jeden vorhandenen Knoten
   * in die Liste hinzuf�gt.
   * @param list
   * @param node
   * @return
   */
  private ArrayList<E> fillList(ArrayList<E> list, TreeNode<E> node){
    if(node != null) {
      if (node.getLeft() != null) {
        fillList(list, node.left);
      }
      list.add(node.value);
      if (node.getRight() != null) {
        fillList(list, node.right);
      }
    }
    return list;
  }


}