package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import movieSearching.CSVParse;
import movieSearching.Movie;

/**
 * This test class tests the CSVParse class.
 * 
 * @author Kyle Vinsand
 *
 */
class CSVParseTest {

  /**
   * Tests CSVParse with starter.csv.
   * 
   * @throws FileNotFoundException if file could not be located
   * @throws IOException if file could not be opened
   */
  @Test
  void testBuildMovieList() throws FileNotFoundException, IOException {
    List<Movie> movies = CSVParse.buildMovieList("starter.csv");
    assertEquals("tt0068646", movies.get(0).getIdentifier());
    String print = "The Godfather [1972, 175 minutes, 9.2/10.0]";
    assertEquals(print, movies.get(0).toString());
  }

}
