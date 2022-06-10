//Wynona Lam 5/22/21
//Takes in a Patreon CSV file and gets rid of all $3 tier info automatically

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Main {
  public static void main(String[] args) throws IOException {
    File in = new File("Members.txt"); // open input file
    Scanner inFile = new Scanner(in);

    FileWriter fileOut = new FileWriter("memsOut.txt"); // open output file
    BufferedWriter outFile = new BufferedWriter(fileOut); // use Buffered reader to write out file

    String line = "";

    while (inFile.hasNext()) { // read input file until end of lines

      line = inFile.nextLine(); // read a line from input and store into line variable
      String[] tokens = line.split(","); // split lines words around each comma
      List<String> validMems = new ArrayList<String>(Arrays.asList(tokens)); // casting the List to an arrayList

      for (int i = 0; i < validMems.size(); i++) {
        if (validMems.get(i).equals(" Cloudy Star ")) {
          validMems.remove(i);
          for (int j = 0; j < 10; j++) { // removes the first half of the info
            validMems.remove(i - j);
          }
          for (int k = 15; k > -1; k--) { // removes the second half
            validMems.remove(i - 9 + k);
          }
          i--;
        }
      }
      for (int j = 0; j < validMems.size(); j++) {
        if (j % 26 != 0 || j == 0) {
          outFile.write(validMems.get(j) + ",");// don't want comma at end of line
        } else if (j % 26 == 0 && j > 0) { // 26 spots each person, 9 before sub tier info
          outFile.write(validMems.get(j));
          outFile.newLine();
        }
      }

    }
    inFile.close();
    outFile.close();
  }
}

