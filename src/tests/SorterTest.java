package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import movieSearching.Movie;
import sorts.Sorter;
import sorts.SortFactory;
import movieSearching.CSVParse;

public class SorterTest {

  // We only want to do the IO once, so cache a copy in here
  private static List<Movie> starterList;
  private static List<Movie> mediumList;
  private static List<Movie> imdbList;

  private List<Movie> duplicate(List<Movie> orig) {
    List<Movie> newList = new ArrayList<>(orig.size());
    for (Movie m : orig) {
      newList.add(m);
    }
    return newList;
  }

  // Initialize the lists from the CSV files
  @BeforeAll
  public static void buildLists() {
    starterList = buildList("starter.csv");
    mediumList = buildList("medium.csv");
    imdbList = buildList("imdb.csv");
  }

  private static List<Movie> buildList(String file) {
    CSVParse parser = new CSVParse();
    List<Movie> movies = parser.buildMovieList(file);
    return movies;
  }

  // Test a list for correct ordering based on rating, title, or year
  private boolean testRatingSort(List<Movie> orig, String algorithm) {
    // Build the expected list of rating sorted
    double ratings[] = new double[orig.size()];
    int i = 0;
    for (Movie m : orig) {
      ratings[i++] = m.getImdbRating();
    }
    Arrays.sort(ratings);
    
    // Make a duplicate of the original list and sort it
    List<Movie> working = duplicate(orig);
    SortFactory<Movie> factory = new SortFactory<>();
    Sorter<Movie> sorter = factory.buildSorter(algorithm);
    sorter.sort(working, (Movie m1, Movie m2) -> {
      if (m1.getImdbRating() < m2.getImdbRating()) {
        return -1;
      } else if (m1.getImdbRating() > m2.getImdbRating()) {
        return 1;
      } else {
        return 0;
      }
    });

    // All years must match in order (does not assume stability)
    boolean matches = true;
    i = 0;
    for (Movie m : working) {
      if (m.getImdbRating() != ratings[i++]) {
        matches = false;
      }
    }
    return matches;
  }

  private boolean testTitleSort(List<Movie> orig, String algorithm) {
    // Build the expected list of title sorted
    String titles[] = new String[orig.size()];
    int i = 0;
    for (Movie m : orig) {
      titles[i++] = m.getTitle();
    }
    Arrays.sort(titles);
    
    // Make a duplicate of the original list and sort it
    List<Movie> working = duplicate(orig);
    SortFactory<Movie> factory = new SortFactory<>();
    Sorter<Movie> sorter = factory.buildSorter(algorithm);
    sorter.sort(working, (Movie m1, Movie m2) -> m1.getTitle().compareTo(m2.getTitle()));

    // All years must match in order (does not assume stability)
    boolean matches = true;
    i = 0;
    for (Movie m : working) {
      if (! m.getTitle().equals(titles[i++])) {
        matches = false;
      }
    }
    return matches;
  }

  private boolean testYearSort(List<Movie> orig, String algorithm) {
    // Build the expected list of years sorted
    int years[] = new int[orig.size()];
    int i = 0;
    for (Movie m : orig) {
      years[i++] = m.getYear();
    }
    Arrays.sort(years);

    // Make a duplicate of the original list and sort it
    List<Movie> working = duplicate(orig);
    SortFactory<Movie> factory = new SortFactory<>();
    Sorter<Movie> sorter = factory.buildSorter(algorithm);
    sorter.sort(working, (Movie m1, Movie m2) -> m1.getYear() - m2.getYear());

    // All years must match in order (does not assume stability)
    boolean matches = true;
    i = 0;
    for (Movie m : working) {
      if (m.getYear() != years[i++]) {
        matches = false;
      }
    }
    return matches;
  }

  // ----------------------------------------------------------
  // SORTING CORRECTNESS TESTS - 9 points total
  // ----------------------------------------------------------

  // Integer sorting - test starter list using each

  @Test
  public void testBubbleStarterYear() {
    assertTrue(testYearSort(starterList, "bubble"));
  }

  @Test
  public void testSelectionStarterYear() {
    assertTrue(testYearSort(starterList, "selection"));
  }

  @Test
  public void testInsertionStarterYear() {
    assertTrue(testYearSort(starterList, "insertion"));
  }

  @Test
  public void testMergeStarterYear() {
    assertTrue(testYearSort(starterList, "merge"));
  }

  @Test
  public void testQuickStarterYear() {
    assertTrue(testYearSort(starterList, "quick"));
  }

  // String sorting - test starter list using each
 
  @Test
  public void testBubbleStarterTitle() {
    assertTrue(testTitleSort(starterList, "bubble"));
  }

  @Test
  public void testSelectionStarterTitle() {
    assertTrue(testTitleSort(starterList, "selection"));
  }

  @Test
  public void testInsertionStarterTitle() {
    assertTrue(testTitleSort(starterList, "insertion"));
  }

  @Test
  public void testMergeStarterTitle() {
    assertTrue(testTitleSort(starterList, "merge"));
  }

  @Test
  public void testQuickStarterTitle() {
    assertTrue(testTitleSort(starterList, "quick"));
  }

  // Double sorting - test starter list using each

  @Test
  public void testBubbleStarterRating() {
    assertTrue(testRatingSort(starterList, "bubble"));
  }

  @Test
  public void testSelectionStarterRating() {
    assertTrue(testRatingSort(starterList, "selection"));
  }

  @Test
  public void testInsertionStarterRating() {
    assertTrue(testRatingSort(starterList, "insertion"));
  }

  @Test
  public void testMergeStarterRating() {
    assertTrue(testRatingSort(starterList, "merge"));
  }

  @Test
  public void testQuickStarterRating() {
    assertTrue(testRatingSort(starterList, "quick"));
  }

  // ----------------------------------------------------------
  // SORTING STABILITY TESTS - NOT SELECTION OR QUICK SORT - 3 points
  // ----------------------------------------------------------

  private boolean testYearStability(List<Movie> orig, String algorithm) {
    // Build the expected list of years sorted; also, a list of original indeces
    int years[] = new int[orig.size()];
    Map<String, Integer> originalLocations = new HashMap<>();
    int i = 0;
    for (Movie m : orig) {
      originalLocations.put(m.getIdentifier(), i);
      years[i++] = m.getYear();
    }
    Arrays.sort(years);

    // Make a duplicate of the original list and sort it
    List<Movie> working = duplicate(orig);
    SortFactory<Movie> factory = new SortFactory<>();
    Sorter<Movie> sorter = factory.buildSorter(algorithm);
    sorter.sort(working, (Movie m1, Movie m2) -> m1.getYear() - m2.getYear());

    // All years must match in order (does not assume stability)
    boolean matches = true;
    i = 0;
    for (Movie m : working) {
      if (m.getYear() != years[i++]) {
        matches = false;
      }
    }

    for (i = 0; i < working.size() - 1; i++) {
      if (working.get(i).getYear() == working.get(i+1).getYear()) {
        if (originalLocations.get(working.get(i).getIdentifier()) >
            originalLocations.get(working.get(i+1).getIdentifier())) {
          return false;
        }
      }
    }

    return matches;
  }


  @Test
  public void testBubbleMediumStabilityYear() {
    assertTrue(testYearStability(mediumList, "bubble"));
  }

  @Test
  public void testInsertionMediumStabilityYear() {
    assertTrue(testYearStability(mediumList, "insertion"));
  }

  @Test
  public void testMergeMediumStabilityYear() {
    assertTrue(testYearStability(mediumList, "merge"));
  }
  
  // ----------------------------------------------------------
  // SORTING PERFORMANCE MEASURES - 3 points
  // ----------------------------------------------------------

  @Test
  public void testQuickSortSpeed() {
    long start = System.currentTimeMillis();
    assertTrue(testYearSort(imdbList, "quick"));
    long end = System.currentTimeMillis();
    assertTrue((end - start) < 5000, "Quicksort should sort imdb.csv in less than 5 seconds");
  }

  @Test
  public void testMergeSortSpeed() {
    long start = System.currentTimeMillis();
    assertTrue(testYearSort(imdbList, "merge"));
    long end = System.currentTimeMillis();
    assertTrue((end - start) < 5000, "Merge sort should sort imdb.csv in less than 5 seconds");
  }

  @Test
  public void testSlowSortSpeeds() {
    long start = System.currentTimeMillis();
    assertTrue(testYearSort(imdbList, "quick"));
    long end = System.currentTimeMillis();
    long qsTime = end - start;

    start = System.currentTimeMillis();
    assertTrue(testYearSort(imdbList, "insertion"));
    end = System.currentTimeMillis();
    long isTime = end - start;

    assertTrue(isTime > 5 * qsTime, "Insertion sort should be more than 5 times slower than quicksort");
  }


}
