import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.StringBuilder;

public class UtilsIO {

    //final variable to manage all system entries
    public final static Scanner DATA = new Scanner(System.in);

    //path to save parties
    private static final String path = "./savedParties.csv";

    private static File csvFile = new File(path);

    //write files to csv

    public static void writePartyToCsv(File csvFile, ArrayList<Character> party) {
        try {
            FileWriter writer = new FileWriter(csvFile, true);
            StringBuilder builder = new StringBuilder();
            for (Character el : party) {
                builder.append(el.getId());
                builder.append(", ");
                builder.append(el.getName());
                builder.append(", ");
                builder.append(el.getHp());
                builder.append(", ");
                //warrior: stamina strength wizard: mana intelligence
                if(el instanceof Warrior) {
                    builder.append(((Warrior) el).getStamina());
                    builder.append(", ");
                    builder.append(((Warrior) el).getStrength());
                    builder.append(", ");
                    builder.append(1); //@TODO this is an index to let know that what's readen is a warrior - implement
                }else if(el instanceof Wizard){
                    builder.append(((Wizard) el).getMana());
                    builder.append(", ");
                    builder.append(((Wizard) el).getIntelligence());
                    builder.append(", ");
                    builder.append(2); //@TODO this is an index to let know that what's readen is a wizard - implement
                }
                builder.append(";\n");
                writer.write(builder.toString());
            }
            writer.write('/'); //@TODO this is the delimiter of a party ending - implement
            writer.close();
            System.out.println("Success writing party in file.");
        }catch(Exception e){
            System.out.println(e);
        }
    }


    //reads csv file and prints it

    public static void printCsv(File csvFile) {
        try {
            if (csvFile.length() == 0L) {

            } else {
                printPartiesFromCsv(csvFile);
            }
        } catch (Exception e) {
            System.out.println("File does not exist, error: " + e);
        }
    }

    //auxiliar method to print the csv
    private static void printPartiesFromCsv(File csvFile) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader(csvFile));
            String line = lector.readLine();
            while (null != line) {
                String[] itemArr = line.split(";");
                System.out.println(Arrays.toString(itemArr));
                line = lector.readLine();
            }
//            System.out.println(Arrays.toString(itemArr));
//            lector.useDelimiter(";");
//            while (lector.hasNext()) System.out.print(lector.next());
            lector.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //create file if it doesn't exist

    public static boolean createCsvFileIfNotExists(String path) {
        try {
            File csvFile = new File(path);
            if (!csvFile.exists()) csvFile.createNewFile();
            System.out.println("\nSuccess creating " + csvFile + ".");
            return true;
        } catch (Exception e) {
            System.out.println("\nError creating the file: " + e);
            return false;
        }
    }

    public static File getCsvFile() {
        return csvFile;
    }

    public void setCsvFile(File csvFile) {
        this.csvFile = csvFile;
    }
}
