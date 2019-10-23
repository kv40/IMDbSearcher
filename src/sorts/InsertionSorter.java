package sorts;
import java.util.Comparator;
import java.util.List;

/**
 * This sorts a list using insertion sort.
 * @author Kyle Vinsand
 *
 * @param <E> The object type that is being sorted
 */
public class InsertionSorter<E> implements Sorter<E> {

  /**
   * Sorts a list using the insertion sort algorithm.
   * @param list being sorted using insertion sort
   * @param comp how to sort the list
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    //Iterate through each index in the list.
    for (int i = 1; i < list.size(); i++) {
      int j = i;
      //Loop while the current object is greater than the last
      while (j > 0 && comp.compare(list.get(j - 1), list.get(j)) > 0) {
        //Swap the current object with the index before it.
        E temp = list.get(j);
        list.set(j, list.get(j - 1));
        list.set(j - 1, temp);
        //Decrement the current index.
        j--;
      }
    }
  }
}
