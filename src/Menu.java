import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    public static void mainMenu(Party party1, Party party2) {

        printWithColor(" \u2694 \uD83D\uDD2E  Welcome to MAGIC WAR! \u2694 \uD83D\uDD2E ", ConsoleColors.BLACK_BACKGROUND);
        var logo = """
                                
                █▀▄▀█ ██     ▄▀  ▄█ ▄█▄          ▄ ▄   ██   █▄▄▄▄\s
                █ █ █ █ █  ▄▀    ██ █▀ ▀▄       █   █  █ █  █  ▄▀\s
                █ ▄ █ █▄▄█ █ ▀▄  ██ █   ▀      █ ▄   █ █▄▄█ █▀▀▌ \s
                █   █ █  █ █   █ ▐█ █▄  ▄▀     █  █  █ █  █ █  █ \s
                   █     █  ███   ▐ ▀███▀       █ █ █     █   █  \s
                  ▀     █                        ▀ ▀     █   ▀   \s
                       ▀                                ▀        \s           
                """;

        printWithColor(logo, ConsoleColors.PURPLE);

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
            System.out.println(mainMenu);
            Scanner lector = new Scanner(System.in);
            var pickedOption = lector.nextInt();
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
                            pickedOption = lector.nextInt();
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
                            }
                            case 3 -> printWithColor("Not implemented yet select another option", ConsoleColors.CYAN);
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
                        pickedOption = lector.nextInt();
                        switch (pickedOption) {
                            case 1 -> Battle.executeArcadeBattle(party1, party2);
                            case 2 -> Battle.executeVSBattle(party1, party2);
                            case 3 -> System.out.println();
                            default -> System.out.println("Select a valid option");
                        }
                    }
                    break;

                case 3:
                    var instructions = """                                
                              \u2694 \uD83D\uDD2E  War of Magic Instructions \u2694 \uD83D\uDD2E 

                              Welcome to the land of fantasy!
                              Where humble mighty warriors meet powerful wizards in 
                              fateful battles.
                              First step is creating the two parties of fighters. 
                              They can be either warriors \u2694 or wizards \uD83D\uDD2E 

                              ====================================

                              OPTIONS
                              All warriors have a name, health points,
                              strength and stamina.
                              All wizards have also a name and health point but also 
                              intelligence and mana.

                              Party creation options:
                              -Random generated party
                              -Manual creation
                              -Load from a CSV file

                              Then you can proceed to battle.
                              These are the options:
                              -Arcade --> Play against the computer
                              -VS. (Manual Battle) --> Play with a friend and each one gets to choose his fighter

                              ====================================

                              RULES
                              Warriors are strong well armored characters that focus on the attribute strength.
                              Every round a warrior will try to do a “Heavy attack”. The damage of a heavy attack
                              is equal to their strength and every hit will decrease their stamina by 5 points. 
                              If he can’t make a heavy attack he will do a “Weak attack”. The damage of a weak attack 
                              is the half of the strength (truncate decimals). Every weak attack will recover his stamina by 1.

                              Wizards are the masters of the arcane their main attribute is intelligence.
                              Every round a wizard will try to cast a “Fireball”. The damage of a fireball is equal
                              to his intelligence and every fireball will decrease their mana by 5 points.
                              If he can’t cast a fireball he will do a “Staff hit”. The damage of a staff hit is equals to 2.
                              Every staff hit will recover his mana by 1.

                              When the battle is over the winner will return to the party and the loser
                              will be removed and sent to the graveyard. Then you can choose the combatants for the next duel.                         
                              When a party lose all their members a winner party is declared.

                              May the mightier win!                        
                              ====================================
                            """;
                    printWithColor(instructions, ConsoleColors.BLACK_BACKGROUND);
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
