package movieSearching;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser for creating a list of movies from a comma-separated value (CSV) file. The allowable
 * syntax of CSV files can be somewhat complex. Our simplified format allows anything to be part of
 * any data field, except for quotation marks ("). Quotation marks are reserved for grouping data
 * fields that themselves contain a comma. For instance, consider the following line:
 *
 * hello,"Peter, Paul, and Mary",how,are,you
 *
 * This line contains 5 data fields, where the second is the string "Peter, Paul, and Mary". Note
 * that your String object should not contain the quotation marks.
 *
 * Another simplifying factor is that every line has exactly one data field per member of the Movie
 * class. You do not need to handle ill-formed data files.
 */
public class CSVParse {

  /**
   * Main interface for parsing a CSV file into a movie list.
   *
   * @param csvFile The name of the file to parse.
   * @return The list of movies in the same order as the file contents.
   * @throws FileNotFoundException if file cannot be found with given file name
   */
  public static List<Movie> buildMovieList(String csvFile) {
    List<Movie> movies = new ArrayList<Movie>();
    String currentMovie;
    try {
      //Create a buffered reader with the given file name.
      BufferedReader csv = new BufferedReader(new FileReader(csvFile));
      //Call the getMovie method while the csv file has a next line.
      while ((currentMovie = csv.readLine()) != null) {
      //Add movies returned from the getMovie() method to a list.
        movies.add(getMovie(currentMovie));
      }
      csv.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return movies;
  }

  /**
   * Returns the next movie and all its date in a csv.
   * 
   * @param csv the csv as a String
   * @return the next movie
   */
  public static Movie getMovie(String csv) {
    String line = csv;
    String[] assets = new String[6];
    int start;
    int end = 0;
    //Loop four times for each property of a Movie object minus genre.
    for (int i = 0; i < 5; i++) {
      start = 0;
      //Check if the next property is surrounded with quotation marks.
      if (line.charAt(0) == '"') {
        end = line.indexOf(",", line.indexOf('"', 1));
      } else {
        end = line.indexOf(",");
      }
      //Remove the property from the current line and add it to the array.
      assets[i] = line.substring(start, end);
      line = line.substring(end+1, line.length());
    }
    //We can assume the rest of the line contains the genres
    assets[5] = line.substring(1, line.length()-1);
    //Substring the name property to remove the quotation marks.
    assets[1] = assets[1].substring(1, assets[1].length()-1);
    //Set the properties to its appropriate data type.
    double imdbRating = Double.parseDouble(assets[2]);
    int runtime = Integer.parseInt(assets[3]);
    int year = Integer.parseInt(assets[4]);
    List<String> genres = new ArrayList<String>();
    //Split the genres and add them to a String list.
    String[] splits = assets[5].split(",");
    for (String s : splits) {
      genres.add(s);
    }
    //Create and return the Movie object for the parsed properties.
    Movie movie =  new Movie(assets[0], assets[1], imdbRating, runtime, year, genres);
    return movie;
  }
}
