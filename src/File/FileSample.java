package File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileSample {
    public void lineWriter(String fileName) {
        try {
            File myFile = new File(fileName);
            if (!myFile.exists()) {
                FileWriter myFileWriter = new FileWriter(myFile);
                BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
                PrintWriter myPrintWriter = new PrintWriter(myBufferedWriter);

                String line;
                int lineCounter = 1;
                do {
                    line = "This is line " + lineCounter++;
                    myPrintWriter.println(line);
                    System.out.println(line);
                    if (lineCounter == 1000) {
                        break;
                    }
                } while (line != null);

                myPrintWriter.close();
                myBufferedWriter.close();
                myFileWriter.close();
            } else {
                // throw ApplicationException(...);
                System.out.println("File not found:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lineReader(String fileName) {
        try {
            File myFile = new File(fileName);
            if (myFile.exists() && myFile.canRead()) {
                FileReader myFileReader = new FileReader(myFile);
                BufferedReader myBufferedReader = new BufferedReader(myFileReader);

                String line;
                int lineCounter = 1;
                do {
                    line = myBufferedReader.readLine();
                    System.out.println(lineCounter++ + ":" + line);
                } while (line != null);

                myBufferedReader.close();
                myFileReader.close();
            } else {
                // throw ApplicationException(...);
                System.out.println("File not found:" + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
