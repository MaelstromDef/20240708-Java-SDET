public class App {
    public static void main(String[] args) {
        int i = 10;
        int j = 0;
        String output = "Output: ";
        while(i > j){
            output += " " + j++;
            i -= j;
            output += ":" + i;
        }
        /*
         * 0 | 1 | 9
         * 1 | 2 | 7
         * 2 | 3 | 4
         * 3 | 4 | 0
         */

        System.out.println(output);
    }
}
