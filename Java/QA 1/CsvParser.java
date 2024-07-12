package Java.Personal;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CsvParser{
    // Holds parsed file contents.
    ArrayList<LinkedList<String>> fileContents = new ArrayList<LinkedList<String>>();

    LinkedList<String> items;
    String current;
    boolean inQuotes;

    public CsvParser(){}

    public CsvParser(String filename){
        parseCsv(filename);
    }

    public void parseCsv(String filename){
        inQuotes = false;
        try(Scanner lineScanner = new Scanner(new File(filename))){
            while(lineScanner.hasNext()){
                String str = lineScanner.nextLine();
                fileContents.add(parseCsvString(str));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public LinkedList<String> parseCsvString(String str){
        this.current = "";
        int i = 0;

        // Corrective patching for remaining in quotes between lines.
        if(inQuotes){
            // Set parser assisting items to a previous state.
            items = fileContents.get(fileContents.size() - 1);
            fileContents.remove(fileContents.size() - 1);

            if(!items.isEmpty()) current = items.removeLast() + "\n";

            for(; i < str.length() && inQuotes; i++)
                current += handleCharacter(str.charAt(i));

            // Still within same item.
            if(inQuotes) {
                items.add(current);
                current = "";
                return items;
            }
        }else{
            items = new LinkedList<>();
        }

        // Not in quotes, parse string normally.
        for(; i < str.length(); i++){
            String returned = handleCharacter(str.charAt(i));

            if(returned == null) current = "";
            else current += returned;
        }

        items.add(current);
        current = "";
        return items;
    }

    private String handleCharacter(char c){
        // Swap between in and out of quotes.
        if(c == '\"'){
            inQuotes = !inQuotes;
            return "";
        }

        // Ignore
        if(inQuotes) return Character.toString(c);

        // Handle item separators
        if(c == ','){
            System.out.println("ADDING:\n" + current + "\n");
            items.add(current);
            current = "";
            return null;
        }

        return Character.toString(c);
    }

    public void print(){
        for(LinkedList<String> ll : fileContents){
            System.out.println("-------------------------------------");
            for(String str : ll){
                System.out.print(str + "\t|\t");
            }

            System.out.println();
        }
    }
}
