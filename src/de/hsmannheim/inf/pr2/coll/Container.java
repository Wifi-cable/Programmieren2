package de.hsmannheim.inf.pr2.coll;

import java.util.Iterator;

/**
 * Vereinfachtes Interface mit allen Methoden, die jeder
 * Container in der Vorlesung hat.
 * Enthält nur die Methoden, die für die Vorlesung
 * didaktisch relevant sind.
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public interface Container<E> {

  boolean contains(E o);

  Iterator<E> iterator();

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  void clear();
}
