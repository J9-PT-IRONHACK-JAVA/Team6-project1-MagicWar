import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;

public class UtilsIO {

    //final variable to manage all system entries
    public final static Scanner DATA = new Scanner(System.in);
    private static String path = "./savedParties.csv";

    //reads csv file and print

    public static void printCsv(File csvFile){
        try{
            if (csvFile.length() == 0L) {
                System.out.println("No parties stored");
            }else{
                printPartiesFromCsv(csvFile);
            }
        }catch(Exception e){
            System.out.println("File does not exist, error: " +e);
        }
    }

    //write files to csv

    public static void writePartyToCsv(File csvFile, ArrayList<Character> party){
        if(createCsvFileIfNotExists(path)) {


            try(PrintWriter writer = new PrintWriter(csvFile)) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < party.size(); i++) {
                    //add each object
                    builder.append("\n");
                    builder.append(party.get(i));
                }
                writer.write(builder.toString());
                writer.close();
                System.out.println("Success writing a party.");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }





    //create file if it doesn't exist

    public static boolean createCsvFileIfNotExists(String path){
        try {
            File csvFile = new File(path);
            System.out.println("Success.");
            return true;
        }catch(Exception e){
            System.out.println("Error creating the file: " +e);
            return false;
        }
    }


    private static void printPartiesFromCsv(File csvFile) {
        try {
            //parsing a CSV file into Scanner class constructor
            Scanner lector = new Scanner(csvFile);
            lector.useDelimiter(",");
            while(lector.hasNext()){
                System.out.print(lector.next());
            }
            lector.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }





}
