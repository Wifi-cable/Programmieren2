package de.hsmannheim.inf.pr2.coll;


/**
 * Ein Knoten fÃ¼r einen BinÃ¤rbaum.
 * Dieser Knoten kann selbst als eigener BinÃ¤rbaum aufgefasst werden.
 *
 * @author Markus Gumbel
 */
public class TreeNode<E extends Comparable<E>> {

	//value ist auch die Wurzel
  public E value = null;           // Wert des Knotens.
  public TreeNode<E> left = null;  // Linker Teilbaum.
  public TreeNode<E> right = null; // Rechter Teilbaum.

  /**
   * Erzeuge einen neuen BinÃ¤rbaum-Knoten. Nachfolger und VorgÃ¤nger sind nicht
   * gesetzt.
   *
   * @param v Der Wert des Knotens.
   */
  public TreeNode(E v) {
    value = v;
    left = right = null;
  }

  /**
   * Erzeuge einen neuen BinÃ¤rbaum(-Knoten), entspricht tree().
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
   * Bestimme die HÃ¶he des Baums.
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
      // GrÃ¶ÃŸe ist dieser Knoten plus solche in den TeilbÃ¤umen:
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
   * DurchlÃ¤uft einen (Teil-)Baum vom Wurzelknoten an in
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
  
  //Soll einen binären, geordneten mit einem anderen binären geordneten Baum vergleichen.
 public boolean equalStructure(TreeNode<E> otherNode) {
	  
	  if(getValue() == null && otherNode.getValue() == null) {
		  System.out.println("true");
		  
		  return true;
	  }
	//Wird ein größere komplexerer Baum genommen, so werden hier nur die Werte von Value, left und right verglichen.
	 //Andere Werte bzw. Knoten die nach left und right kommen, werden nicht verglichen.
	  else if (getValue() == otherNode.getValue()) {
		  
		 if((getLeft().getValue() == otherNode.getLeft().getValue()) && (getRight().getValue() == otherNode.getRight().getValue())) {
			  System.out.println("true");
			  
			  
			  
			  return true;
		  }
		 
		 else {
			 System.out.println("false");
			  
			  return false;
			 
		 }
	  }
	  
	  else { 
	  System.out.println("false");
	  
	  return false;
	  
	  }
  }
  
 }
