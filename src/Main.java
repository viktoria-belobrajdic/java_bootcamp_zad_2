import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {

        // kilometres input
        String msgKilometres = "broj kilometara";
        float kilometres = userInputValue(msgKilometres);

        // conversion factor input
        String msgConversionFactor = "konverzijski faktor";
        float conversionFactor = userInputValue(msgConversionFactor);

        // save conversion factor to file
        File fileWithConversionFactor = fileWithConversionFactor(conversionFactor);

        // read conversion factor from file
        String conversionFactorFromFile = readConversionFactorFromFile(fileWithConversionFactor);

        //conversion factor info
        System.out.println("Iz datoteke: " + conversionFactorFromFile);

        // kilometres to miles:
        int miles = (int) (kilometres / conversionFactor);
        String message = String.format("Uneseno je %s kilometara, što je %s milja.", kilometres, miles);
        System.out.println(message);
    }

    private static float userInputValue(String msg){
        Scanner scanner = new Scanner(System.in);
        float value = 0;
        boolean error = true;
        do {
            System.out.println("Unesite " + msg + " : ");
            try {
                value = scanner.nextFloat();
                if (value <= 0) {
                    System.out.println("Vrijednost ne može biti negativna ili nula! Unesite pozitivan broj.");
                }
                error = false;
            } catch (InputMismatchException e) {
                System.out.println("Molim unesite brojčanu vrijednost!");
                scanner.next();
            }
        } while (value <= 0 || error);

        return value;
    }

    private static File fileWithConversionFactor(float conversionFactor) throws IOException {
        File file = new File("konverzijski_faktor.txt");

        FileWriter writer = new FileWriter(file);
        writer.write("konverzijskiFaktor = " + conversionFactor);
        writer.close();

        return file;
    }

    private static String readConversionFactorFromFile(File file) throws IOException {
        FileReader reader = new FileReader(file);
        char[] buffer = new char [100];
        reader.read(buffer);
        reader.close();

        String content = new String(buffer);

        return content;
    }
}