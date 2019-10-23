package movieSearching;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import binaryTree.BinTree;
import binaryTree.BinTreeBuilder;
import sorts.Sorter;

/**
 * This class searches for movies.
 * 
 * @author Kyle Vinsand
 *
 */
public class MovieSearcher {

  private List<Movie> filteredMovies;

  /**
   * Creates a movie searcher object and declares a list to hold movies that meet all criteria.
   */
  public MovieSearcher() {
    filteredMovies = new ArrayList<Movie>();
  }

  /**
   * Searches a tree for all Movies that meet the criteria.
   * 
   * @param tree sorted containing movies
   * @param searchType string name of how to search the binary tree
   * @param searchCriteria what we are comparing the movie to
   * @return list of movies meeting search criteria
   */
  public List<Movie> searchTree(BinTree<Movie> tree, String searchType, String[] searchCriteria) {
    //Create the function needed for the current search criteria.
    Function<Movie, Integer> function = createFunction(searchType, searchCriteria);
    //Search through the tree to find movies that meet the criteria.
    List<Movie> results = tree.search(function);
    return results;
  }

  /**
   * Creates a function needed for the search.
   * 
   * @param searchType what to search for
   * @param criteria what we are comparing the movie to
   * @return function taking a Movie and returning an integer
   */
  private Function<Movie, Integer> createFunction(String searchType, String[] criteria) {
    Function<Movie, Integer> function;
    //Switch case for each possible search criteria.
    //Determines what the function will be for the search.
    switch (searchType) {
      case "year":
        function = (x) -> {
          if (x.getYear() < Integer.parseInt(criteria[1])) {
            return -1;
          } else if (x.getYear() > Integer.parseInt(criteria[2])) {
            return 1;
          } else {
            return 0;
          }
        };
        break;
      case "runtime": {
        function = (x) -> {
          if (x.getRuntime() < Integer.parseInt(criteria[1])) {
            return -1;
          } else if (x.getRuntime() > Integer.parseInt(criteria[2])) {
            return 1;
          } else {
            return 0;
          }
        };
        break;
      }
      case "rating":
        function = (x) -> {
          if (x.getImdbRating() < Double.parseDouble(criteria[1])) {
            return -1;
          } else if (x.getImdbRating() > Double.parseDouble(criteria[2])) {
            return 1;
          } else {
            return 0;
          }
        };
        break;
      default:
        function = null;
        break;
    }
    return function;
  }

  /**
   * Creates binary tree needed for a search criteria.
   * 
   * @param movies an unsorted list of movies
   * @param sorter the sorting method to use
   * @param sortCriteria how to sort the list
   * @return binary tree
   */
  public BinTree<Movie> createBinaryTree(List<Movie> movies, Sorter<Movie> sorter,
      String sortCriteria) {
    //Get the appropriate comparator for the sort method.
    Comparator<Movie> compare = getComparator(sortCriteria);
    //Create a copy of the movies being searched, sort the copy, then build a binary tree.
    List<Movie> sortedMovies = new ArrayList<Movie>(movies);
    sorter.sort(sortedMovies, compare);
    BinTree<Movie> root = new BinTreeBuilder<Movie>().build(sortedMovies);
    return root;
  }

  /**
   * Creates a comparator for the sort.
   * 
   * @param sortCriteria how to sort the list
   * @return the comparator
   */
  private Comparator<Movie> getComparator(String sortCriteria) {
    Comparator<Movie> compare;
    //Switch case for each possible way to sort a list of Movies.
    //Determines what the comparator will be for the sort.
    switch (sortCriteria) {
      case "year":
        compare = (m1, m2) -> m1.getYear() - m2.getYear();
        break;
      case "runtime":
        compare = (m1, m2) -> m1.getRuntime() - m2.getRuntime();
        break;
      case "rating":
        compare = (m1, m2) -> {
          if (m1.getImdbRating() < m2.getImdbRating()) {
            return -1;
          } else if (m1.getImdbRating() > m2.getImdbRating()) {
            return 1;
          } else {
            return 0;
          }
        };
        break;
      default:
        compare = null;
        break;
    }
    return compare;
  }

  /**
   * Searches the list of movies linearly.
   * 
   * @param movies unsorted list of movies
   * @param criteria what the movie must match
   * @return list of movies adhering to criteria
   */
  public List<Movie> linearSearch(List<Movie> movies, String[] criteria) {
    List<Movie> matches = new ArrayList<Movie>();
    //Create predicate for a linear search checking each genre.
    Predicate<Movie> pred = (x) -> {
      for (int i = 1; i < criteria.length; i++) {
        if (x.getGenres().contains(criteria[i])) {
          return true;
        }
      }
      return false;
    };
    //Iterate through each movie and check if it has at least one genre from the criteria.
    for (Movie currentMovie : movies) {
      if (pred.test(currentMovie)) {
        matches.add(currentMovie);
      }
    }
    return matches;
  }

  /**
   * Finds the shared movies from two different searches.
   * 
   * @param searchResults results from first binary tree search
   */
  public void treeMatches(List<Movie> searchResults) {
    //Check if the list of Movie results is empty.
    if (filteredMovies.isEmpty()) {
      setFilteredMovies(searchResults);
      return;
    }
    List<Movie> matches = new ArrayList<Movie>();
    //Compare each prior Movie that met the criteria with the new results.
    for (Movie currMovie : searchResults) {
      if (filteredMovies.contains(currMovie)) {
        matches.add(currMovie);
      }
    }
    setFilteredMovies(matches);
  }

  /**
   * Overrides the current filtered movie list.
   * 
   * @param newMovieList movies that met new criteria
   */
  private void setFilteredMovies(List<Movie> newMovieList) {
    //Clear the saved Movie results and replace with a new List of Movies.
    filteredMovies.clear();
    filteredMovies.addAll(newMovieList);
  }

  /**
   * Returns the movies that met all criteria tested against it.
   * 
   * @return list of movies
   */
  public List<Movie> getFilteredMovies() {
    return filteredMovies;
  }
}
