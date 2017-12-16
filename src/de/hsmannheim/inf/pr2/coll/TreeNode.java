package de.hsmannheim.inf.pr2.coll;


import java.util.ArrayList;

/**
 * Ein Knoten für einen Binärbaum.
 * Dieser Knoten kann selbst als eigener Binärbaum aufgefasst werden.
 *
 * @author Markus Gumbel
 */
public class TreeNode<E extends Comparable<E>> {

  public E value = null;           // Wert des Knotens.
  public TreeNode<E> left = null;  // Linker Teilbaum.
  public TreeNode<E> right = null; // Rechter Teilbaum.

  /**
   * Erzeuge einen neuen Binärbaum-Knoten. Nachfolger und Vorgänger sind nicht
   * gesetzt.
   *
   * @param v Der Wert des Knotens.
   */
  public TreeNode(E v) {
    value = v;
    left = right = null;
  }

  /**
   * Erzeuge einen neuen Binärbaum(-Knoten), entspricht tree().
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
   * Bestimme die Höhe des Baums.
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
      // Größe ist dieser Knoten plus solche in den Teilbäumen:
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
   * Durchläuft einen (Teil-)Baum vom Wurzelknoten an in
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
   * aufgabe 2.2  implementierung 
   * */
  
public boolean equalStructure(TreeNode<E> otherNode) {
	  
	  if(this.value == null && otherNode.value == null) {	//vergleicht ob beide werte null sind
		  return true;
	  }
	  	
	  else if((this != null) && (otherNode != null) && (otherNode instanceof TreeNode<?>)) {	
		 //wenn  der zu vergleichende baum an der selben stelle einen knoten hat...
		 if(this.getValue().equals(otherNode.getValue())) {
			//nur wenn die aktuellen knoten den gleichen wert haben wird weiter verglichen. 
			 
			 if(getLeft() == null && otherNode.getLeft() == null) {	//wenn an beiden knoten keinen linken nachfolger haben
				 return true;
			 }
			 
			 else if(getRight() == null && otherNode.getRight() == null) {//wenn beide keinen rechten nachfolger haben 
				 return true;
			 }
			 
			 else if(getLeft() != null && otherNode.getLeft() == null) {	//wenn beim einen links kein knoten ist beim andern schon
				 return false;
			 }
			 //wenn  ein baum links nichts hat, der andere schon
			 else if(getLeft() == null && otherNode.getLeft() != null) {
				 return false;
			 }
			 //wenn rechts von aktuellen knoten keiner ist aber der vergleichsbaum einen hat
			 else if(getRight() != null && otherNode.getRight() == null) {
				 return false;
			 }
			 
			 //wenn rechts von aktuellen knoten noch einer kommt aber im vergleichsbaum nicht
			 else if(getRight() == null && otherNode.getRight() != null) {
				 return false;
			 }
			 //wenn der linke knoten der aktuellen knoten beider bäume gleich ist
			 else if(getLeft().getValue().equals(otherNode.getLeft().getValue())) {
				//ruf funktion rekursiv wieder auf 
				 if(getLeft().equalStructure(otherNode.getLeft())) {
					 //das selbe rechts 
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
public ArrayList<E> getList(){
    ArrayList<E> list = new ArrayList<>();
    return fillList(list, this);
  }

  /**
   * Rekusiver aufbau einer Liste, die jeden vorhandenen Knoten
   * in die Liste hinzufügt.
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
