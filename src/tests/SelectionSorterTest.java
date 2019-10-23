package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import sorts.SelectionSorter;

/**
 * Tests the SelecetionSorter class.
 * @author Kyle Vinsand
 * 
 */
class SelectionSorterTest {

  /**
   * This method tests the selection sort method with a list of integers.
   */
  @Test
  void testWithIntegerList() {
    SelectionSorter<Integer> sorter = new SelectionSorter<Integer>();
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
   * This method tests the selection sort method with a list of Strings.
   */
  @Test
  void testWithStringList() {
    SelectionSorter<String> sorter = new SelectionSorter<String>();
    String[] sorted = {"hi", "how", "are", "horse", "rides"};
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
    SelectionSorter<String> sorter = new SelectionSorter<String>();
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
    for (int i = 0; i < list.size(); i++) {
      assertTrue(sorted[i].equals(list.get(i)));
    }
  }
}
