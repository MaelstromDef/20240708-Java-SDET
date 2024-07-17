package Java.Personal;

public class App {
    public static void main(String[] args) {
        String filename = "Java\\QA Demos\\QA1\\csvFile.csv";

        System.out.println("MANUAL PARSER");
        ICsvParser parser = new CsvParserManual(filename);
        parser.printFileContents();

        System.out.println("\nREGEX PARSER");
        parser = new CsvParserRegEx(filename);
        parser.printFileContents();
    }
}
