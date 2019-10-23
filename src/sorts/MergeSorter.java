package sorts;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This sorts a list using merge sort.
 * @author Kyle Vinsand
 *
 * @param <E> The object type of the items being sorted
 */
public class MergeSorter<E> implements Sorter<E> {

  /**
   * Sorts a list using the merge sort algorithm.
   * @param list being sorted using merge sort
   * @param comp how to sort the list
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {
    //Check if the list contains one object or is empty.
    if (list.size() <= 1) {
      return;
    }
    //Split the list in the middle.
    int middle = list.size() / 2;
    List<E> left = new ArrayList<E>(list.subList(0, middle));
    List<E> right = new ArrayList<E>(list.subList(middle, list.size()));
    //Split recursively until both lists contain less than 2 objects.
    sort(left, comp);
    sort(right, comp);
    //Merge the split left and right lists.
    merge(left, right, list, comp);
  }

  /**
   * This method merges the split lists.
   * 
   * @param left The left half of the list
   * @param right The right half of the list
   * @param comp How to compare and sort the values
   * @param list The list where they will be merged
   */
  private void merge(List<E> left, List<E> right, List<E> list, Comparator<E> comp) {
    int leftIndex = 0;
    int rightIndex = 0;
    int k = 0;
    //Loop while both indexes are less than their respective lists sizes.
    while (leftIndex < left.size() && rightIndex < right.size()) {
      //Swap based on if the left object is greater than or equal to the right object.
      if (comp.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
        list.set(k++, left.get(leftIndex++)); 
      } else {
        list.set(k++, right.get(rightIndex++));
      }
    }
    //Keep swapping while the left index is in bounds.
    while (leftIndex < left.size()) {
      list.set(k++, left.get(leftIndex++));
    }
    //Keep swapping while the right index is in bounds.
    while (rightIndex < right.size()) {
      list.set(k++, right.get(rightIndex++));
    }
  }

}
