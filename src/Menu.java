import java.io.File;
import java.util.ArrayList;

public class Menu {

    public static void mainMenu(Party party1, Party party2) {

        printWithColor("*** Welcome to MAGIC WAR! ***", ConsoleColors.BLACK_BACKGROUND);
        var logo = """
                                
                █▀▄▀█ ██     ▄▀  ▄█ ▄█▄          ▄ ▄   ██   █▄▄▄▄\s
                █ █ █ █ █  ▄▀    ██ █▀ ▀▄       █   █  █ █  █  ▄▀\s
                █ ▄ █ █▄▄█ █ ▀▄  ██ █   ▀      █ ▄   █ █▄▄█ █▀▀▌ \s
                █   █ █  █ █   █ ▐█ █▄  ▄▀     █  █  █ █  █ █  █ \s
                   █     █  ███   ▐ ▀███▀       █ █ █     █   █  \s
                  ▀     █                        ▀ ▀     █   ▀   \s
                       ▀                                ▀        \s
                                
                """;
        var mainMenu = """  
                \nSelect an option to play:
                =================================
                1- Create Parties of characters for the battle
                2- Start a battle
                3- Read game instructions
                4- Quick battle: full random game
                5- Exit
                =================================
                Type your selection, and press enter:
                """;
        boolean quitsGame = false;

        do {
            printWithColor(logo, ConsoleColors.CYAN);
            System.out.println(mainMenu);
            var pickedOption = UtilsIO.DATA.nextInt();
            switch (pickedOption) {
                case 1:
                    boolean hasTwoParties = false;
                    boolean backToMainMenu = false;
                    var newParty = new ArrayList<Character>();
                    String partyNum;

                    do {
                        if (party1.getCharactersInParty().isEmpty()) partyNum = "PARTY 1";
                        else partyNum = "PARTY 2";
                        var partyCreationMenu = """                                
                            \n** Party Creation **
                            %s: How do you want to create this battling Party?
                            ==================================== 
                            1- Random generated party
                            2- Manual creation
                            3- Load from a CSV file
                            4- Back to Main Menu
                            ====================================
                            Type your selection, and press enter:
                            """.formatted(partyNum);

                        if (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty()) {
                            hasTwoParties = true;
                            backToMainMenu = true;
                            pickedOption = 4;
                        } else {
                            System.out.println(partyCreationMenu);
                            pickedOption = UtilsIO.DATA.nextInt();
                        }
                        switch (pickedOption) {
                            case 1 -> {
                                newParty = Party.createPartyRandom();
                                if (party1.getCharactersInParty().isEmpty()) party1.setCharactersInParty(newParty);
                                else party2.setCharactersInParty(newParty);
                            }
                            case 2 -> {
                                newParty = Party.createManualParty();
                                if (party1.getCharactersInParty().isEmpty()) party1.setCharactersInParty(newParty);
                                else party2.setCharactersInParty(newParty);
                                System.out.println("Do you want to store this party?\n1- yes\n2- no");
                                var option = UtilsIO.DATA.nextInt();
                                if (option == 1) UtilsIO.writePartyToCsv(UtilsIO.getCsvFile(), newParty);
                            }
                            case 3 -> {
                                if (((UtilsIO.getCsvFile()).length() != 0L)) {
                                    System.out.println("Select a party: ");
                                    UtilsIO.printCsv(UtilsIO.getCsvFile());
                                } else {
                                    Menu.printWithColor("\nNo parties stored yet!!\nPlease create a manual party.", ConsoleColors.RED);
                                }
                            }
                            case 4 -> backToMainMenu = true;
                            default -> printWithColor("Invalid option. Please select a number from the menu\n", ConsoleColors.RED);
                        }
                        if (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty()) {
                            hasTwoParties = true;
                            backToMainMenu = true;
                        }

                    } while (!backToMainMenu);
                    if (hasTwoParties) printWithColor("You already have two parties, so you're ready to fight!", ConsoleColors.GREEN);
                    break;
                case 2:
                    if (party1.getCharactersInParty().isEmpty() || party2.getCharactersInParty().isEmpty()) {
                        printWithColor("Can't battle yet! You first need to create two Parties", ConsoleColors.RED);
                    } else {
                        var battleMenu = """                                
                                \n** Battle Options **
                                Choose how to fight
                                =========================
                                1- Arcade
                                2- VS. (Manual Battle)
                                3- Back to Main Menu
                                ====================================
                                Type your selection, and press enter:
                                """;
                        System.out.println(battleMenu);
                        pickedOption = UtilsIO.DATA.nextInt();
                        switch (pickedOption) {
                            case 1 -> Battle.executeArcadeBattle(party1, party2);
                            case 2 -> Battle.executeVSBattle(party1, party2);
                            case 3 -> System.out.println("OK"); //TODO how to return without anything?
                            default -> System.out.println("Select a valid option");
                        }
                    }
                    break;

                case 3:
                    printWithColor("This option is not implemented yet, please pick other option", ConsoleColors.CYAN);
                    break;
                case 4:
                    System.out.println("Random battle begins!\n");
                    Battle.randomWar(party1, party2);
                    break;
                case 5:
                    System.out.println("\nSee you next time!");
                    quitsGame = true;
                    break;
                default:
                    printWithColor("Invalid option. Please select a number from the menu", ConsoleColors.RED);
            }
        } while (!quitsGame);

    }

    public static void printWithColor(String text, String color){
        System.out.println(color + text + ConsoleColors.RESET);
    }

}
