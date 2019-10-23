package sorts;
/**
 * Factory pattern implementation for creating an object that implements one of the sorting
 * algorithms. Users should create an instance of this class, then invoke buildSorter on it with the
 * desired algorithm name.
 * @param <E> Object type that is being sorted
 */
public class SortFactory<E> {

  /**
   * Create an object that implements a sorting algorithm.
   *
   * @param algorithm The name of the algorithm to implement.
   * @return The object that sorts items.
   */
  public Sorter<E> buildSorter(String algorithm) {
    switch (algorithm) {
      case "Bubble":
      case "bubble":
        return new BubbleSorter<E>();
      case "Insertion":
      case "insertion":
        return new InsertionSorter<E>();
      case "Merge":
      case "merge":
        return new MergeSorter<E>();
      case "Quick":
      case "quick":
        return new QuickSorter<E>();
      case "Selection":
      case "selection":
        return new SelectionSorter<E>();
      default:
        return null;
    }
  }
}
