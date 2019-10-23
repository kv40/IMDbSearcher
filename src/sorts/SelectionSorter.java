package sorts;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts a generic list using the selection sort method.
 * 
 * @author Kyle Vinsand
 *
 * @param <E> The object type that is being sorted
 */
public class SelectionSorter<E> implements Sorter<E> {

  /**
   * Sorts the list using the selection sort algorithm.
   * @param list being sorted using selection sort
   * @param comp how to sort the list
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    //Iterate through each index of the list.
    for (int i = 0; i < list.size() - 1; i++) {
      int min = i;
      //Iterate through the list excluding the already sorted objects.
      for (int j = i + 1; j < list.size(); j++) {
        //Check if the current object is less than the one after it.
        if (comp.compare(list.get(j), list.get(min)) < 0) {
          min = j;
        }
      }
      //Sort the current object with the indexes before it.
      E temp = list.get(min);
      while (min != i) {
        list.set(min, list.get(min - 1));
        min--;
      }
      list.set(i, temp);
    }
  }
}
