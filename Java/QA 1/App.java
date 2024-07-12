package Java.Personal;

public class App {
    public static void main(String[] args) {
        CsvParser parser = new CsvParser("Java\\Personal\\csvFile.csv");
        parser.print();
    }
}
