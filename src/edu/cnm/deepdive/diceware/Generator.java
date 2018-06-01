package edu.cnm.deepdive.diceware;

/**
 * to generate random array of words.
 *
 * takes input and produces a randow list of words based on list as output
 *
 * Import packages
 */

import java.util.Arrays;  //TODO optimize imports to loose this
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * generate error messages
 */

public class Generator {

  private static final String NULL_RNG_MESSAGE = "Random number gen must not be null.";
  private static final String NEG_NUM_MESSAGE = "Number of words cannot be negative.";
  private static final String INSUFFICENT_WORDS = "Insufficent words";
  private static final String CANNOT_BE_NULL = "WORDS_CANNOT_BE_NULL.";
  private static final String NO_DUPLICATESS_ALLOWED = "No Duplicatess Allowed!";

  public String[] words;
  public Random rng;  //TODO fix this?

  /**
   * begin
   */

  public Generator(String[] words, Random rng) {

// exception if rng is invalid
    if (rng == null) {
      throw new NullPointerException(NULL_RNG_MESSAGE);
    }
// exception if words entered are empty
    if (words == null) {
      throw new NullPointerException(CANNOT_BE_NULL);
    }
    //exception if array given is negative
    if (words.length == 0) {
      throw new NegativeArraySizeException(NEG_NUM_MESSAGE);
    }

    //convert  quanitiy of words to lower case, make an array
    Set<String> pool = new HashSet<>();

    for (String word : words) {
      word = word.toLowerCase();
      if (!pool.contains(word)) {
        pool.add(word);
      }
    }
    this.words = pool.toArray(new String[pool.size()]);
    this.rng = rng;
  }
  public String next()
  throws NegativeArraySizeException{
    return words[rng.nextInt(words.length)];
  }


  //test for dups allowed, and output if no dupsallowed but dups exist
  public String[] next(int numWords, boolean duplicatesAllowed)

  {
    //exception if not enough words given
    if (numWords == 0) {
      throw new IllegalArgumentException(INSUFFICENT_WORDS);
    }
    //exception if no dups are allowed
    if (!duplicatesAllowed && numWords > 0) {
      throw new IllegalArgumentException(NO_DUPLICATESS_ALLOWED);
    }
    List<String> selection = new LinkedList<>();
    while (selection.size() < numWords) {
      String pick = next();
      if (duplicatesAllowed || !selection.contains(pick)) {
        selection.add(pick);
      }
    }
    return selection.toArray(new String[selection.size()]);
  }

  //return words if dups are allowed
  public String[] next(int numWords) {

    return next(numWords, true);
  }
}




