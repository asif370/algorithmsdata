import java.util.Scanner;

/** 
 * Written by Ryan D'souza
 * Reads text from System.in, breaks it into individual words, adds it to tree
 * Prints all words and the size. Useful for determining number of unique words a textfile has */
public class TreeTester { 

   public static void main(String[] args) {  
      final BinarySearchTree wordTree = new BinarySearchTree();

      final Scanner scanner = new Scanner(System.in);

      System.out.println("Enter words to see frequencies");

      String input = "";
      while((input = scanner.nextLine()) != null && input.length() != 0) {
          final String[] words = input.split(" ");

          for(String word : words) { 
              wordTree.add(word);
          }
      }

      System.out.println("\n\nPrinting Data");
      wordTree.print();
   }
}

