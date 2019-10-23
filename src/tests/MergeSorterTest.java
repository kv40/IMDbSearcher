package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import sorts.MergeSorter;

/**
 * This class tests the MergerSorter class.
 * 
 * @author Kyle Vinsand
 *
 */
class MergeSorterTest {

  /**
   * This method tests the merge sort method with a list of integers.
   */
  @Test
  void testWithIntegerList() {
    MergeSorter<Integer> sorter = new MergeSorter<Integer>();
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

  /**
   * This method tests the quick sort method with a list of Strings with many of the same sizes.
   */
  @Test
  void testWithStringStable() {
    MergeSorter<String> sorter = new MergeSorter<String>();
    String[] sorted = {"hi", "are", "our", "cat", "mouse", "horse", "rides", "house"};
    List<String> list = new ArrayList<String>();
    list.add("mouse");
    list.add("horse");
    list.add("are");
    list.add("hi");
    list.add("our");
    list.add("rides");
    list.add("cat");
    list.add("house");
    Comparator<String> comp = (i1, i2) -> i1.length() - i2.length();
    sorter.sort(list, comp);
    for (int j = 0; j < list.size(); j++) {
      System.out.println(list.get(j));
    }
    for (int i = 0; i < list.size(); i++) {
      assertTrue(sorted[i].equals(list.get(i)));
    }
  }

}
