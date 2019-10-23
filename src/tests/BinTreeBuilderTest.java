package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import binaryTree.BinTree;
import binaryTree.BinTreeBuilder;
/**
 * 
 * @author Kyle Vinsand
 *
 */
class BinTreeBuilderTest {

  /**
   * Tests the BinTreeBuilder build method with integers.
   */
  @Test
  void testBuildWith1Through8() {
    List<Integer> unsorted = new ArrayList<Integer>();
    unsorted.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
    BinTreeBuilder<Integer> builder = new BinTreeBuilder<Integer>();
    BinTree<Integer> tree = builder.build(unsorted);
    assertTrue(4 == tree.getElement());
    assertTrue(1 == tree.getLeft().getElement());
    assertTrue(0 == tree.getLeft().getLeft().getElement());
    assertTrue(2 == tree.getLeft().getRight().getElement());
    assertTrue(3 == tree.getLeft().getRight().getRight().getElement());
    assertTrue(6 == tree.getRight().getElement());
    assertTrue(5 == tree.getRight().getLeft().getElement());
    assertTrue(7 == tree.getRight().getRight().getElement());
    assertTrue(8 == tree.getRight().getRight().getRight().getElement());
  }
  
  /**
   * Tests BinTreeBuilder build method with strings.
   */
  @Test
  void testBuildWithStrings() {
    List<String> unsorted = new ArrayList<String>();
    unsorted.addAll(Arrays.asList("hi", "hey", "meow", "woof", "horse", "house"));
    BinTreeBuilder<String> builder = new BinTreeBuilder<String>();
    BinTree<String> tree = builder.build(unsorted);
    assertEquals("meow", tree.getElement());
    assertEquals("hi", tree.getLeft().getElement());
    assertEquals("hey", tree.getLeft().getRight().getElement());
    assertEquals("horse", tree.getRight().getElement());
    assertEquals("woof", tree.getRight().getLeft().getElement());
    assertEquals("house", tree.getRight().getRight().getElement());
  }

}
