package Java.Personal;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CsvParserManual implements ICsvParser{
    // Holds parsed file contents.
    LinkedList<LinkedList<String>> fileContents;

    LinkedList<String> lineItems;
    String currentItem;
    boolean escaped;
    boolean possibleQuoteEscaped;

    public CsvParserManual(){}

    public CsvParserManual(String filename){
        parseCsv(filename);
    }

    public void parseCsv(String filename){
        fileContents = new LinkedList<LinkedList<String>>();
        escaped = false;
        possibleQuoteEscaped = false;
        try(Scanner lineScanner = new Scanner(new File(filename))){
            while(lineScanner.hasNext()){
                String str = lineScanner.nextLine();
                fileContents.add(parseLine(str));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private LinkedList<String> parseLine(String line){
        // Escaped content can have new lines, if escaped on a new line load item being parsed.
        if(escaped) loadPreviousItem();
        else initializeNewRow();

        // Not in quotes, parse string normally.
        for(int i = 0; i < line.length(); i++) parseCharacter(line.charAt(i));
        
        // Account for escape characters at the end of a line.
        if(possibleQuoteEscaped){
            escaped = false;
            possibleQuoteEscaped = false;
        }

        addItem();
        return lineItems;
    }

    private void parseCharacter(char c){
        // Handle a potentially escaped escape character.
        if(c == '\"' && possibleQuoteEscaped){
            currentItem += c;
            possibleQuoteEscaped = false;
            return;
        // Previous character should have set escaped to false.
        }else if(possibleQuoteEscaped){ 
            escaped = !escaped;
            possibleQuoteEscaped = false;
        }

        // Check if the escaped character is escaped.
        if(c == '\"' && escaped){
            possibleQuoteEscaped = true;
            return;
        }

        // Check for escaped character
        if(c == '\"'){
            escaped = !escaped;
            possibleQuoteEscaped = false;
            return;
        }

        // Ignore all other rules while escaped.
        if(escaped) {
            currentItem += c;
            return;
        }

        // Handle item separators
        if(c == ',') {
            addItem();
            return;
        }

        currentItem += c;
    }

    private void loadPreviousItem(){
        lineItems = fileContents.removeLast();
        currentItem = lineItems.removeLast() + "\n";
    }

    private void initializeNewRow(){
        lineItems = new LinkedList<>();
        currentItem = "";
    }

    private void addItem(){
        lineItems.add(currentItem);
        currentItem = "";
    }    

    public void printFileContents(){
        for(LinkedList<String> ll : fileContents){
            System.out.println("-------------------------------------");
            for(String str : ll)
                System.out.print(str + "\t|\t");

            System.out.println();
        }

        System.out.println("-------------------------------------");
    }
}