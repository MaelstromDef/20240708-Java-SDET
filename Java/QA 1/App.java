package Java.Personal;

public class App {
    public static void main(String[] args) {
        CsvParser parser = new CsvParser("Java\\QA 1\\csvFile.csv");
        parser.print();
    }
}
