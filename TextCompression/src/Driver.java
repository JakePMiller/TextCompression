/**
 * Running this driver will print some statistics about a small sample
 * text. 
 * 
 * TODO
 * 
 * OG test - The original text has 110 characters.
 * 6
 * The average length of a code is 2.24 bits.
 * The text is encoded in 246 bits.
 * The savings is 634 bits.
 * The compressed text is encoded in 32 characters.
 * The savings is 78 characters.
 * Decompressing yields 246 bits.
 * The recovered text has 110 characters.

 * 
 * Alice - The original text has 163675 characters.
 * 84
 * The average length of a code is 4.60 bits.
 * The text is encoded in 752855 bits.
 * The savings is 556545 bits.
 * The compressed text is encoded in 94108 characters.
 * The savings is 69567 characters.
 * Decompressing yields 752855 bits.
 * The recovered text has 163675 characters.
 * 
 * Moby - The original text has 12352 characters.
 * 60
 * The average length of a code is 4.43 bits.
 * The text is encoded in 54691 bits.
 * The savings is 44125 bits.
 * The compressed text is encoded in 6838 characters.
 * The savings is 5514 characters.
 * Decompressing yields 54691 bits.
 * The recovered text has 12352 characters.
 * 
 * Ulysses - The original text has 264299 characters.
 * 97
 * The average length of a code is 4.68 bits.
 * The text is encoded in 1236597 bits.
 * The savings is 877795 bits.
 * The compressed text is encoded in 154576 characters.
 * The savings is 109723 characters.
 * Decompressing yields 1236597 bits.
 * The recovered text has 264299 characters.
 * 
 * 
 * Once your code passes all the unit tests, replace text with the contents
 * of Alice In Wonderland. Figure out how to calculate the percentage of 
 * bits saved. Do the same for Moby Dick. Then, download the plain text version 
 * of a favorite book from http://www.gutenberg.org to your project directory,
 * and collect those statistics.
 * 
 * Create a text file named report.txt in your project directory. This file should 
 * include your full name, username, and lab section, and a table of your 
 * statistics regarding the three books.
 */

public class Driver {
  public static void main(String[] args) {
    System.out.println(Constants.TITLE);
//    String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
//        + "bbbbbbbbbbbbbb"
//        + "cccccccccccc"
//        + "ddddddddddddddddddddd" 
//        + "eeeeeeeee"
//        + "fffff";
    String text = Util.loadFile(Constants.ALICE);
//    String text = Util.loadFile(Constants.MOBY_DICK);
//    String text = Util.loadFile(Constants.ULYSSES);
    
    System.out.println();
    System.out.println("The original text has " + text.length() + " characters.");
    CodeBook book = new CodeBook(text);
    System.out.println(String.format("The average length of a code is %.2f bits.",
        book.getWeightedAverage()));
    Zipper zipper = new Zipper(book);
    String bits = zipper.encode(text);
    System.out.println("The text is encoded in " + bits.length() + " bits.");
    System.out.println("The savings is " + 
        (text.length() * Constants.BITESIZE - bits.length()) + " bits.");
    String packing = zipper.compress(bits);
    System.out.println("The compressed text is encoded in " + packing.length() + " characters.");
    System.out.println("The savings is " + 
        (text.length() - packing.length()) + " characters.");    
    String unpacking = zipper.decompress(packing);
    System.out.println("Decompressing yields " + unpacking.length() + " bits.");
    String recoveredText = zipper.decode(unpacking);
    System.out.println("The recovered text has " + recoveredText.length() + " characters.");
    assert recoveredText.equals(text);    
    System.out.println("\nAll tests passed...");
  }
}

