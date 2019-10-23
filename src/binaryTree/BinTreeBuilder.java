package binaryTree;
import java.util.List;

/**
 * Builds an immutable binary tree from a sorted list. Building the tree
 * should be accomplished recursively so that an in-order traversal
 * returns the same order of elements. One way to to this is to set the
 * root element to the middle element of that portion of the list. The
 * left sub-tree is then created from all elements preceding the root in
 * the list, while the right sub-tree is the later elements. For example,
 * consider the list 0 1 2 3 4 5 6 7 8. The resulting tree should look
 * as follows:
 *         4
 *       /   \
 *     1       6
 *    / \     / \
 *   0   2   5   7
 *        \       \
 *         3       8  
 * @param <E> The object type of the items
 */
public class BinTreeBuilder<E> {

  /**
   * Builds the tree from the given list.
   *
   * @param list The list of elements to form the tree with.
   * @return The root of the tree.
   */
  public BinTree<E> build(List<E> list) {
    //Check if the list contains one object.
    if (list.size() == 1) {
      return new BinTree<E>(list.get(0));
      //Check if the list is empty.
    } else if (list.size() == 0) {
      return null;
    }
    //Grab the mid point the build the left and right node recursively.
    int mid = (list.size() - 1) / 2;
    BinTree<E> tree = new BinTree<E>(list.get(mid));
    tree.left = build(list.subList(0, mid));
    tree.right = build(list.subList(mid + 1, list.size()));
    return tree;
  }

}
