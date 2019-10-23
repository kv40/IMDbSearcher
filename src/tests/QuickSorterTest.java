package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import sorts.QuickSorter;

/**
 * Tests the QuickSorter class.
 * @author Kyle Vinsand
 *
 */
class QuickSorterTest {

  /**
   * This method tests the quick sort method with a list of integers.
   */
  @Test
  void testWithIntegerList() {
    QuickSorter<Integer> sorter = new QuickSorter<Integer>();
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
   * This method tests the quick sort method with a list of Strings.
   */
  @Test
  void testWithStringList() {
    QuickSorter<String> sorter = new QuickSorter<String>();
    String[] sorted = {"hi", "are", "how", "horse", "rides"};
    List<String> list = new ArrayList<String>();
    list.add("how");
    list.add("horse");
    list.add("are");
    list.add("hi");
    list.add("rides");
    Comparator<String> comp = (i1, i2) -> i1.length() - i2.length();
    sorter.sort(list, comp);
    for (int i = 0; i < list.size(); i++) {
      assertTrue(sorted[i].equals(list.get(i)));
    }
  }
  
  /**
   * This method tests the quick sort method with a list of Strings with many of the same sizes.
   */
  @Test
  void testWithStringStable() {
    QuickSorter<String> sorter = new QuickSorter<String>();
    String[] sorted = {"hi", "our", "are", "cat", "mouse", "house", "horse", "rides"};
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
    for (int i = 0; i < list.size(); i++) {
      System.out.println(list.get(i));
    }
    for (int i = 0; i < list.size(); i++) {
      assertTrue(sorted[i].equals(list.get(i)));
    }
  }

}
