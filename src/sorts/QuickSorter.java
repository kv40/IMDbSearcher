package sorts;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Sorts a generic list using the quick sort method.
 * 
 * @author Kyle Vinsand
 *
 * @param <E> The object type we are sorting
 */
public class QuickSorter<E> implements Sorter<E> {

  /**
   * Method that sorts any list using quicksort methodology.
   * @param list of Objects being sorted
   * @param comp how to sort the list
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    //Sort the list passed in as the parameter.
    quickSort(list, comp, 0, list.size()-1);
  }

  /**
   * Recursive method to sort the array.
   * 
   * @param list The list of objects being sorted
   * @param comp Comparator determining how to sort the list
   * @param left The left index of the list
   * @param right The right index of the list
   */
  private void quickSort(List<E> list, Comparator<E> comp, int left, int right) {
    //Pick pivot as middle index.
    int pivotindex = (left + right) / 2;
    //Swap the pivot and the object at the right most index.
    E temp = list.get(right);
    list.set(right, list.get(pivotindex));
    list.set(pivotindex, temp);
    //Partition the list and return where the left and right bounds cross.
    int k = partition(list, left, right-1, comp, list.get(right));
    //Swap the pivot with the object where the bounds crossed.
    temp = list.get(right);
    list.set(right, list.get(k));
    list.set(k, temp);
    //Sort the left side of the pivot.
    if ((k-left) > 1) {
      quickSort(list, comp, left, k-1);
    }
    //Sort the right side of pivot.
    if ((right-k) > 1) {
      quickSort(list, comp, k+1, right);
    }
  }
  
  /**
   * Method for partitioning a list and returning where bounds cross.
   * @param list The list being partitioned
   * @param left The left index of where to begin right bound
   * @param right The right index of where to begin left bound
   * @param comp Comparator controlling how the list is being sorted
   * @param pivot The pivot object being compared to all values in the list
   * @return The index where the left and right bounds cross
   */
  private int partition(List<E> list, int left, int right, Comparator<E> comp, E pivot) {
    int l = left;
    int r = right;
    //Loop while the left bound is less than or equal to the right bound.
    while (l <= r) {
      //Find where an object is greater than the pivot.
      while (comp.compare(list.get(l), pivot) < 0) {
        l++;
      }
      //Find where an object is less than or equal to the pivot.
      while ((r >= l) && (comp.compare(list.get(r), pivot) >= 0)) {
        r--;
      }
      //Swap the two objects if the bounds did not cross.
      if (r > l) {
        E temp = list.get(l);
        list.set(l, list.get(r));
        list.set(r, temp);
      }
    }
    return l; 
  }
  
}
