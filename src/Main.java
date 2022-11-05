import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        var party1 = new ArrayList<Character>();
        var party2 = new ArrayList<Character>();

        System.out.println("Welcome to Magic War!\nSelect an option to play, and press enter:");
        var mainMenu = """                                
                1- Random battle
                2- Pick players to start a battle
                3- Load a battle
                4- Exit""";
        boolean quitsGame = true;


        do {
            System.out.println(mainMenu);
            Scanner lector = new Scanner(System.in);
            var pickedOption = lector.nextInt();
            switch (pickedOption) {
                case 1:
                    System.out.println("Random battle begins!");
                    Battle.randomWar();
                    break;
                case 2:
                    boolean hasTwoParties = false;
                    do {
                        if (!party1.isEmpty() && !party2.isEmpty()) hasTwoParties = true;
                        var partyCreationMenu = """                                
                                How do you want to create the party?
                                1- Random generated party
                                2- Manual creation
                                3- Load from a CSV file
                                4- Exit""";
                        System.out.println(partyCreationMenu);
                        pickedOption = lector.nextInt();
                        switch (pickedOption) {
                            case 1 -> {
                                if ((party1.isEmpty())) {
                                    party1 = Party.createPartyRandom();
                                } else {
                                    party2 = Party.createPartyRandom();
                                }
                            }
                            case 2 -> {
                                if ((party1.isEmpty())) {
                                    party1 = Party.createManualParty();
                                } else {
                                    party2 = Party.createManualParty();
                                }
                            }
                            case 3 -> System.out.println("Not implemented yet select another option");
                            case 4 -> hasTwoParties = true;
                            default -> System.out.println("Select a valid option");
                        }
                        if (!party1.isEmpty() && !party2.isEmpty()) hasTwoParties = true;
                    } while (!hasTwoParties);
                    break;
                case 3:
                    System.out.println("This option is not implemented yet, please pick other option");
                    break;
                case 4:
                    System.out.println("\nSee you next time!");
                    quitsGame = false;
            }
        } while (quitsGame);
        
  }


}