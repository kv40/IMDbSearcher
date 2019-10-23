package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import sorts.InsertionSorter;

/**
 * This class tests the InsertionSorter class.
 * @author Kyle Vinsand
 *
 */
class InsertionSorterTest {

  /**
   * This method tests the insertion sort method with a list of integers.
   */
  @Test
  void testWithIntegerList() {
    InsertionSorter<Integer> sorter = new InsertionSorter<Integer>();
    List<Integer> list = new ArrayList<Integer>();
    int[] sorted = {2, 5, 6, 7, 8, 9};
    list.add(9);
    list.add(5);
    list.add(6);
    list.add(7);
    list.add(2);
    list.add(8);
    Comparator<Integer> comp = (i1, i2) -> i1 - i2;
    sorter.sort(list, comp);
    for (int i = 0; i < list.size(); i++) {
      assertTrue(sorted[i] == list.get(i));
    }
  }

}
