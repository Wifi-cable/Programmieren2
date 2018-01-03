package de.hsmannheim.inf.pr2.coll.sort;

import java.util.Comparator;
import java.util.List;

/**
 * Ein Interface, das sortieren kann.
 *
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public interface Sort<E extends Comparable<E>> {

  /**
   * Sortiert die Liste aufsteigend.
   *
   * @param list
   */
  default void sort(List<E> list) {
    Comparator<E> cmp = E::compareTo;
    sort(list, cmp);
  }

  /**
   * Sortiert die Liste nach dem gegebenem Kriterium.
   *
   * @param list
   * @param cmp  Definiert die Reihenfolge.
   */
  void sort(List<E> list, Comparator<E> cmp);
}
