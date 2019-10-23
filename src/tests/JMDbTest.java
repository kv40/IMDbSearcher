package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import main.JMDb;

public class JMDbTest {

  private PrintStream originalOut;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  public void setup() throws IOException {
    originalOut = System.out;
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(originalOut);
  }

  private void runTest(String[] expectedMovies) {
    List<String> results = new ArrayList<>();
    for (String s : outContent.toString().split("\n")) {
      results.add(s);
    }

    List<String> expected = new ArrayList<>();
    for (int i = 0; i < expectedMovies.length; i++) {
      expected.add(expectedMovies[i]);
    }
    assertEquals(expected.size(), results.size());
    for (String s : results) {
      expected.remove(s);
    }
    assertEquals(0, expected.size(), "Not all expected results were returned");
  }

  // Combination of single dimension + genre

  @Test
  public void testStarterYearGenre() {
    JMDb.main(new String[] { "starter.csv", "merge", "year 1900 2050 AND genre Thriller Sci-Fi" });
    runTest(new String[] {
      "A Clockwork Orange [1971, 136 minutes, 8.3/10.0]",
      "Dog Day Afternoon [1975, 125 minutes, 8.0/10.0]",
      "Jaws [1975, 124 minutes, 8.0/10.0]",
      "Star Wars [1977, 121 minutes, 8.6/10.0]",
      "Carlito's Way [1993, 144 minutes, 7.9/10.0]",
      "Se7en [1995, 127 minutes, 8.6/10.0]",
      "Mr. Nobody [2009, 141 minutes, 7.9/10.0]"
    });
  }

  @Test
  public void testStarterRatingGenre() {
    JMDb.main(new String[] { "starter.csv", "quick", "rating 8.0 9.0 AND genre Thriller" });
    runTest(new String[] {
      "Dog Day Afternoon [1975, 125 minutes, 8.0/10.0]",
      "Jaws [1975, 124 minutes, 8.0/10.0]",
      "Se7en [1995, 127 minutes, 8.6/10.0]"
    });
  }

  @Test
  public void testStarterRuntimeGenre() {
    JMDb.main(new String[] { "starter.csv", "bubble", "runtime 150 250 AND genre Crime" });
    runTest(new String[] {
      "The Godfather: Part III [1990, 162 minutes, 7.6/10.0]",
      "Scarface [1983, 170 minutes, 8.3/10.0]",
      "The Godfather [1972, 175 minutes, 9.2/10.0]",
      "The Godfather: Part II [1974, 202 minutes, 9.0/10.0]",
    });
  }

  // Test a single dimension

  @Test
  public void testStarterRuntime() {
    JMDb.main(new String[] { "starter.csv", "insertion", "runtime 100 120" });
    runTest(new String[] {
      "Casablanca [1942, 102 minutes, 8.5/10.0]",
      "Taxi Driver [1976, 114 minutes, 8.3/10.0]",
      "Rocky [1976, 120 minutes, 8.1/10.0]"
    });
  }

  @Test
  public void testStarterYear() {
    JMDb.main(new String[] { "starter.csv", "bubble", "year 1900 1947" });
    runTest(new String[] {
      "Casablanca [1942, 102 minutes, 8.5/10.0]"
    });
  }

  @Test
  public void testStarterRating() {
    JMDb.main(new String[] { "starter.csv", "merge", "rating 7.9 8.1" });
    runTest(new String[] {
      "Carlito's Way [1993, 144 minutes, 7.9/10.0]",
      "Mr. Nobody [2009, 141 minutes, 7.9/10.0]",
      "Dog Day Afternoon [1975, 125 minutes, 8.0/10.0]",
      "Jaws [1975, 124 minutes, 8.0/10.0]",
      "Manhattan [1979, 96 minutes, 8.0/10.0]",
      "Network [1976, 121 minutes, 8.1/10.0]",
      "Rocky [1976, 120 minutes, 8.1/10.0]",
    });
  }

  // Test multiple dimensions on medium

  @Test
  public void testMediumYearRuntime() {
    JMDb.main(new String[] { "medium.csv", "bubble", "year 1960 1969 AND runtime 140 300" });
    runTest(new String[] {
      "2001: A Space Odyssey [1968, 149 minutes, 8.3/10.0]",
      "West Side Story [1961, 153 minutes, 7.6/10.0]",
      "Il buono, il brutto, il cattivo [1966, 161 minutes, 8.9/10.0]",
      "C'era una volta il West [1968, 164 minutes, 8.5/10.0]",
      "My Fair Lady [1964, 170 minutes, 7.9/10.0]",
      "Spartacus [1960, 197 minutes, 7.9/10.0]",
      "Andrey Rublev [1966, 205 minutes, 8.2/10.0]",
      "Lawrence of Arabia [1962, 216 minutes, 8.3/10.0]",
    });
  }

  @Test
  public void testMediumRatingGenre() {
    JMDb.main(new String[] { "medium.csv", "insertion", "rating 8.5 9.1 AND genre Sci-Fi" });
    runTest(new String[] {
      "Alien [1979, 116 minutes, 8.5/10.0]",
      "Terminator 2: Judgment Day [1991, 137 minutes, 8.5/10.0]",
      "The Prestige [2006, 130 minutes, 8.5/10.0]",
      "Star Wars [1977, 121 minutes, 8.6/10.0]",
      "Interstellar [2014, 169 minutes, 8.6/10.0]",
      "The Matrix [1999, 136 minutes, 8.7/10.0]",
      "Star Wars: Episode V - The Empire Strikes Back [1980, 124 minutes, 8.8/10.0]",
      "Inception [2010, 148 minutes, 8.8/10.0]"
    });
  }

  @Test
  public void testMediumYearRuntimeRating() {
    JMDb.main(new String[] { "medium.csv", "quick", "year 1960 2000 AND runtime 90 120 AND rating 6.2 6.4" });
    runTest(new String[] {
      "Nick of Time [1995, 90 minutes, 6.3/10.0]",
      "The Money Pit [1986, 91 minutes, 6.3/10.0]",
      "Big Daddy [1999, 93 minutes, 6.4/10.0]",
      "Rambo: First Blood Part II [1985, 96 minutes, 6.4/10.0]",
      "Money Talks [1997, 97 minutes, 6.2/10.0]",
      "Tango & Cash [1989, 104 minutes, 6.4/10.0]",
      "Goin' South [1978, 105 minutes, 6.3/10.0]",
      "Mars Attacks! [1996, 106 minutes, 6.3/10.0]",
      "The Cell [2000, 107 minutes, 6.3/10.0]",
      "City Hall [1996, 111 minutes, 6.2/10.0]"
    });
  }

  // Test full database

  @Test
  public void testImdbYearGenre() {
    JMDb.main(new String[] { "imdb.csv", "quick", "year 1999 1999 AND genre History" });
    runTest(new String[] {
      "Wisconsin Death Trip [1999, 76 minutes, 6.7/10.0]",
      "The 13th Warrior [1999, 102 minutes, 6.6/10.0]",
      "Hans Warns - Mein 20. Jahrhundert [1999, 105 minutes, 6.1/10.0]",
      "Ferdinando e Carolina [1999, 102 minutes, 6.1/10.0]",
      "With Fire and Sword [1999, 175 minutes, 7.1/10.0]",
      "Anna and the King [1999, 148 minutes, 6.7/10.0]",
      "The Einstein of Sex [1999, 100 minutes, 5.4/10.0]",
      "Rabbit in the Moon [1999, 85 minutes, 6.7/10.0]",
      "Nezrimyy puteshestvennik [1999, 87 minutes, 6.8/10.0]",
      "The Trench [1999, 98 minutes, 6.1/10.0]",
      "The Last September [1999, 103 minutes, 6.1/10.0]",
      "The Hitler Youth [1999, 60 minutes, 7.2/10.0]",
      "Harem Suare [1999, 125 minutes, 6.2/10.0]",
      "Gold Fever in Lapland [1999, 158 minutes, 5.8/10.0]",
      "Taboo [1999, 100 minutes, 6.9/10.0]",
      "Show Girls [1999, 52 minutes, 5.2/10.0]",
      "After Stonewall [1999, 88 minutes, 7.4/10.0]",
      "One Day in September [1999, 94 minutes, 7.9/10.0]",
      "I predatori delle Antille [1999, 95 minutes, 5.2/10.0]",
      "Unbowed [1999, 125 minutes, 6.8/10.0]",
      "Grass [1999, 80 minutes, 7.3/10.0]",
      "Khalsa [1999, 132 minutes, 6.6/10.0]",
      "Sunshine [1999, 181 minutes, 7.5/10.0]",
      "Tian ma cha fang [1999, 93 minutes, 7.1/10.0]",
      "Kazachya byl [1999, 78 minutes, 5.5/10.0]",
      "The Road Home [1999, 89 minutes, 7.8/10.0]",
      "Tops & Bottoms [1999, 80 minutes, 5.4/10.0]",
      "Coup [1999, 158 minutes, 7.3/10.0]",
      "Quand je serai parti... vous vivrez encore [1999, 120 minutes, 6.3/10.0]"
    });
  }

  @Test
  public void testImdbRuntimeRatingGenre() {
    JMDb.main(new String[] { "imdb.csv", "merge", "runtime 0 100 AND rating 9.5 10.0 AND genre Drama Crime Romance" });
    runTest(new String[] {
      "Stalemate [2014, 60 minutes, 10.0/10.0]",
      "Ufrivillig [2017, 60 minutes, 9.6/10.0]",
      "Gangter in Morteni [2017, 69 minutes, 9.9/10.0]",
      "Sun Water Stone [2016, 70 minutes, 9.6/10.0]",
      "Memoirs of a Broken Mind [2015, 71 minutes, 9.6/10.0]",
      "Summerhouse [2018, 73 minutes, 9.5/10.0]",
      "Somewhere Over That Rainbow [2016, 75 minutes, 10.0/10.0]",
      "4th Floor of Singapore [2016, 80 minutes, 9.7/10.0]",
      "Love Possibly [2018, 83 minutes, 10.0/10.0]",
      "Maggie Black [2017, 84 minutes, 9.6/10.0]",
      "The Butterfly Effect [2010, 85 minutes, 9.6/10.0]",
      "Polterheist [2018, 86 minutes, 9.5/10.0]",
      "The Musician [2017, 94 minutes, 9.7/10.0]",
      "Taawdo the Sunlight [2017, 98 minutes, 9.6/10.0]",
      "Minuvshie dni [1969, 100 minutes, 9.6/10.0]",
    });
  }

  @Test
  public void testImdbUnique() {
    JMDb.main(new String[] { "imdb.csv", "quick", "runtime 500 1000 AND year 1970 1990 AND rating 7.5 8.0" });
    runTest(new String[] { "Noce i dnie [1975, 632 minutes, 7.5/10.0]" });
  }

}
