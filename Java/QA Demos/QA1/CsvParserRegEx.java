package Java.Personal;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

public class CsvParserRegEx implements ICsvParser {
    LinkedList<String> fileContents;

    public CsvParserRegEx(){}

    public CsvParserRegEx(String filename){
        parseCsv(filename);
    }

    public void parseCsv(String filename){
        fileContents = new LinkedList<>();
        try(Scanner scanner = new Scanner(new File(filename))){
            Pattern csvPattern = Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
            scanner.useDelimiter(csvPattern);
            while(scanner.hasNext()){
                fileContents.add(scanner.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printFileContents(){
        System.out.println("------------------------------------");
        for(String str : fileContents){
            System.out.println(str + "\n------------------------------------");
        }
    }
}
