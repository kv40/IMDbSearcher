package binaryTree;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Generic immutable binary tree node. Although the code does not enforce it, the formed tree should
 * satisfty the binary search tree property: The left sub-tree contains all values that are strictly
 * smaller than the current element; the right sub-tree contains values that are greater than or
 * equal to the current element.
 *
 * @author Kyle Vinsand
 * @version 10/21/19
 * 
 * @param <E> The object type of the items
 */

public class BinTree<E> {
  E element;
  BinTree<E> left;
  BinTree<E> right;

  /**
   * Create a new BinTree that stores the item in the current node.
   *
   * @param item The element to store in this node
   * @param left The root of the left sub-tree
   * @param right The root of the right sub-tree
   */
  public BinTree(E item, BinTree<E> left, BinTree<E> right) {
    this.element = item;
    this.left = left;
    this.right = right;
  }

  /**
   * Create a new BinTree that stores the item in the curent node. Sets the right sub-tree to null.
   *
   * @param item The element to store in this node
   * @param left The root of the left sub-tree
   */
  public BinTree(E item, BinTree<E> left) {
    this(item, left, null);
  }

  /**
   * Create a new BinTree that stores the item in the current node. Sets the both sub-trees to null.
   *
   * @param item The element to store in this node
   */
  public BinTree(E item) {
    this(item, null, null);
  }

  /**
   * Get the BinTree node's element.
   *
   * @return The element stored in this BinTree.
   */
  public E getElement() {
    return element;
  }

  /**
   * Get the left sub-tree.
   *
   * @return The root of the left sub-tree if it exists.
   */
  public BinTree<E> getLeft() {
    return left;
  }

  /**
   * Get the right sub-tree.
   *
   * @return The root of the right sub-tree if it exists.
   */
  public BinTree<E> getRight() {
    return right;
  }

  /**
   * Search through a binary tree for nodes that match a particular criterion. If the passed
   * function returns 0, the current BinTree's element matches the criterion. If the passed function
   * returns a negative value, the current element is too small, and only the right sub-tree needs
   * searched. If the value returned is positive, the current element is too large, and only the
   * left sub-tree needs searched.
   *
   * @param criterion The function specifying the match criterion.
   * @return A list of matching elements from an in-order traversal.
   */
  public List<E> search(Function<E, Integer> criterion) {
    List<E> matches = new ArrayList<E>();
    searchHelper(criterion, matches);
    return matches;
  }

  /**
   * Searches recursively for results.
   * 
   * @param criterion what to search for
   * @param matches list of results
   */
  public void searchHelper(Function<E, Integer> criterion, List<E> matches) {
    //Check if the current node is a leaf.
    if (getLeft() == null && getRight() == null) {
      if (criterion.apply(element) == 0) {
        matches.add(element);
      }
    } else {
      //Check if there is a left node and if criteria can be in that direction.
      if(getLeft() != null && criterion.apply(element) >= 0) {
        getLeft().searchHelper(criterion, matches);
      }
      if (criterion.apply(element) == 0) {
        matches.add(element);
      }
    //Check if there is a right node and if criteria can be in that direction.
      if(getRight() != null && criterion.apply(element) <= 0) {
        getRight().searchHelper(criterion, matches);
      }
    }
  }
}
