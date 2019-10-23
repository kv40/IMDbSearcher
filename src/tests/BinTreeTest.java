package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;
import binaryTree.BinTree;
import binaryTree.BinTreeBuilder;
/**
 * 
 * @author Kyle Vinsand
 *
 */
class BinTreeTest {

  /**
   * Tests the search mathod.
   */
  @Test
  void testSearch() {
    Function<Integer, Integer> criterion = (x) -> {
      if(x < 3) {
        return -1;
      } else if (x > 6) {
        return 1;
      } else {
        return 0;
      }
    };
    List<Integer> list = new ArrayList<Integer>();
    for(int i = 0; i < 10; i++) {
      list.add(i);
    }
    BinTreeBuilder<Integer> build = new BinTreeBuilder<Integer>();
    BinTree<Integer> root = build.build(list);
    List<Integer> testList = root.search(criterion);
    assertTrue(testList.get(0) == 3);
    assertTrue(testList.get(1) == 4);
    assertTrue(testList.get(2) == 5);
    assertTrue(testList.get(3) == 6);
  }

}
