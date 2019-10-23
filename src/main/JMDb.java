package main;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import binaryTree.BinTree;
import sorts.SortFactory;
import sorts.Sorter;
import movieSearching.*;

/*
 * This work complies with the JMU Honor Code.
 * References and Acknowledgments: I received no outside help with this
 * programming assignment.
 */

/**
 * Runner class for the JMU Movie Database (JMDb).
 */
public class JMDb {

  /**
   * The main entry point to the program. The first argument is the name of the CSV file to parse.
   * The second argument is which sorting algorithm to use. The third argument is the search
   * criteria.
   *
   * @param args The command-line arguments.
   * @throws IOException If the file cannot be read.
   * @throws FileNotFoundException If filename does not resolve to a file
   */
  public static void main(String[] args) {
    List<Movie> results = new ArrayList<Movie>();
    List<Movie> movies = CSVParse.buildMovieList(args[0]);
    MovieSearcher searcher = new MovieSearcher();
    String[] argCriteria;
    //Split the criteria separated by the word "AND".
    if (args[2].contains("AND")) {
      argCriteria = args[2].split(" AND ");
    } else {
      argCriteria = new String[1];
      argCriteria[0] = args[2];
    }
    //Iterate through the criteria passed through the command line.
    for (String criteria : argCriteria) {
      //Split the current criteria by its spaces.
      String[] specificCriteria = criteria.split(" ");
      //Check if the current criteria is for genre.
      if (specificCriteria[0].equalsIgnoreCase("genre")) {
        //Perform a linear search of the movies.
        results = searcher.linearSearch(movies, specificCriteria);
      } else {
        //Sort the movies, create a binary tree, then search the tree.
        Sorter<Movie> sort = new SortFactory<Movie>().buildSorter(args[1]);
        BinTree<Movie> tree = searcher.createBinaryTree(movies, sort, specificCriteria[0]);
        results = searcher.searchTree(tree, specificCriteria[0], specificCriteria);
      }
      //Save the results returned from the last search that abide by the previous criteria.
      searcher.treeMatches(results);
    }
    //Get the movies that meet all criteria then print them to the command line.
    results = searcher.getFilteredMovies();
    for (int i = 0; i < results.size(); i++) {
      System.out.print(results.get(i).toString());
      System.out.print("\n");
    }
  }
}
