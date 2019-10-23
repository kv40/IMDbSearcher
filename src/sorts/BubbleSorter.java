package sorts;
import java.util.Comparator;
import java.util.List;

/**
 * This class sorts a generic list using the bubble sort technique.
 * 
 * @author Kyle Vinsand
 *
 * @param <E> The type of the generic object being sorted.
 */
public class BubbleSorter<E> implements Sorter<E> {

  /**
   * This method bubble sorts a list of generic objects.
   * 
   * @param list The list being sorted
   * @param comp The comparator being used to sort the list
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    //Iterate through each index of the list.
    for (int i = 0; i < list.size() - 1; i++) {
      //Iterate through the array excluding the bubbled objects at the end.
      for (int j = 0; j < list.size() - i - 1; j++) {
        //Check if the current object is less than the next object.
        if (comp.compare(list.get(j), list.get(j + 1)) > 0) {
          //Swap the current object with the next.
          E temp = list.get(j);
          list.set(j, list.get(j + 1));
          list.set(j + 1, temp);
        }
      }
    }
  }
}
