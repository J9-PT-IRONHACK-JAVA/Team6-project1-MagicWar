import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    static Random rand = new Random();

    public static void randomWar(Party party1, Party party2) {
        if (party1.getCharactersInParty().isEmpty()) party1.setCharactersInParty(Party.createPartyRandom());
        System.out.println("\nPARTY 1:");
        System.out.println("=========\n");
        printParty(party1);

        if (party2.getCharactersInParty().isEmpty()) party2.setCharactersInParty(Party.createPartyRandom());
        System.out.println("\nPARTY 2:");
        System.out.println("=========\n");
        printParty(party2);

        executeRandomBattle(party1, party2);
    }

    public static void executeRandomBattle (Party party1, Party party2) {
        var graveyard = new ArrayList<Character>();
        do {
            var selectedCharacterParty1 = party1.getCharactersInParty().get(rand.nextInt(party1.getCharactersInParty().size()));
            var selectedCharacterParty2 = party2.getCharactersInParty().get(rand.nextInt(party2.getCharactersInParty().size()));

            announceDuel(selectedCharacterParty1, selectedCharacterParty2);
            executeDuelAndBury(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);

        } while (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty());

        announceBattleOutcome(party1, graveyard);
    }


    public static void executeArcadeBattle (Party party1, Party party2) {

        var graveyard = new ArrayList<Character>();

        do {
            System.out.println("\nSelect a character from Party1 to fight:");
            System.out.println("========================================\n");
            var selectedCharacterParty1 = pickPlayer(party1);
            var selectedCharacterParty2 = party2.getCharactersInParty().get(rand.nextInt(party2.getCharactersInParty().size()));

            announceDuel(selectedCharacterParty1, selectedCharacterParty2);
            executeDuelAndBury(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);


        } while (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty());

        announceBattleOutcome(party1, graveyard);

    }

    public static void executeVSBattle (Party party1, Party party2) {
        var graveyard = new ArrayList<Character>();
        do {
            System.out.println("\nPLAYER 1: Select a character from Party1 to fight:");
            System.out.println("==================================================\n");
            var selectedCharacterParty1 = pickPlayer(party1);
            System.out.println("\nPLAYER 2: Select a character from Party2 to fight:");
            System.out.println("==================================================\n");
            var selectedCharacterParty2 = pickPlayer(party2);

            announceDuel(selectedCharacterParty1, selectedCharacterParty2);
            executeDuelAndBury(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);

        } while (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty());

        announceBattleOutcome(party1, graveyard);

    }
    private static void announceDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {

        System.out.println();
        Menu.printWithColor("Players to fight", ConsoleColors.BLACK_BACKGROUND);
        System.out.println("Party\tName\t\tHP\t\tStrength/Intelligence\tStamina/Mana");
        System.out.println("-----\t----\t\t--\t\t---------------------\t------------");
        
        printWarriorWizard("1", selectedCharacterParty1);
        printWarriorWizard("2", selectedCharacterParty2);
    }

    private static void printWarriorWizard(String playerNum, Character selectedCharacterPartyX) {
        if (selectedCharacterPartyX instanceof Warrior) {
            Warrior playerToPrint = (Warrior) selectedCharacterPartyX;
            System.out.printf("%s\t\t%s\t%s\t\t%s\t\t\t\t\t\t%s\n",
                    playerNum, playerToPrint.getName(), playerToPrint.getHp()
                    , playerToPrint.getStrength(), playerToPrint.getStamina());
        } else {
            Wizard playerToPrint = (Wizard) selectedCharacterPartyX;
            System.out.printf("%s\t\t%s\t%s\t\t%s\t\t\t\t\t\t%s\n",
                    playerNum, playerToPrint.getName(), playerToPrint.getHp()
                    , playerToPrint.getIntelligence(), playerToPrint.getMana());
        }
    }

    private static void announceBattleOutcome(Party party1, ArrayList<Character> graveyard) {
        //Show winning party and Graveyard
        System.out.println();
        if (!party1.getCharactersInParty().isEmpty()) {

            Menu.printWithColor("""
                    
                    \uD83C\uDFC6 \uD83E\uDD47 Party 1 has won!! \uD83C\uDFC6 \uD83E\uDD47
                    
                    """, ConsoleColors.GREEN_BACKGROUND);
        } else {
            Menu.printWithColor("""
                    
                    \uD83C\uDFC6 \uD83E\uDD47 Party 2 has won!! \uD83C\uDFC6 \uD83E\uDD47
                    
                    """, ConsoleColors.GREEN_BACKGROUND);
        }


        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }

        Menu.printWithColor("""
                  
                  ▄████  ██▀███   ▄▄▄    ██▒   █▓▓█████▓██   ██▓ ▄▄▄       ██▀███  ▓█████▄   \s
                 ██▒ ▀█▒▓██ ▒ ██▒▒████▄ ▓██░   █▒▓█   ▀ ▒██  ██▒▒████▄    ▓██ ▒ ██▒▒██▀ ██▌  \s
                ▒██░▄▄▄░▓██ ░▄█ ▒▒██  ▀█▄▓██  █▒░▒███    ▒██ ██░▒██  ▀█▄  ▓██ ░▄█ ▒░██   █▌  \s
                ░▓█  ██▓▒██▀▀█▄  ░██▄▄▄▄██▒██ █░░▒▓█  ▄  ░ ▐██▓░░██▄▄▄▄██ ▒██▀▀█▄  ░▓█▄   ▌  \s
                ░▒▓███▀▒░██▓ ▒██▒ ▓█   ▓██▒▒▀█░  ░▒████▒ ░ ██▒▓░ ▓█   ▓██▒░██▓ ▒██▒░▒████▓   \s
                 ░▒   ▒ ░ ▒▓ ░▒▓░ ▒▒   ▓▒█░░ ▐░  ░░ ▒░ ░  ██▒▒▒  ▒▒   ▓▒█░░ ▒▓ ░▒▓░ ▒▒▓  ▒   \s
                  ░   ░   ░▒ ░ ▒░  ▒   ▒▒ ░░ ░░   ░ ░  ░▓██ ░▒░   ▒   ▒▒ ░  ░▒ ░ ▒░ ░ ▒  ▒   \s
                ░ ░   ░   ░░   ░   ░   ▒     ░░     ░   ▒ ▒ ░░    ░   ▒     ░░   ░  ░ ░  ░   \s
                      ░    ░           ░  ░   ░     ░  ░░ ░           ░  ░   ░        ░      \s
                                             ░          ░ ░                         ░        \

                                
                """, ConsoleColors.PURPLE);
        Menu.printWithColor("\u2620 Rest in peace " + getDeadNames(graveyard) + " \u2620", ConsoleColors.PURPLE);
    }

    private static void executeDuelAndBury(Party party1, Party party2, ArrayList<Character> graveyard, Character selectedCharacterParty1, Character selectedCharacterParty2) {
        executeDuel(selectedCharacterParty1, selectedCharacterParty2);
        sendToGraveyard(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);
    }


    private static ArrayList<String> getDeadNames(ArrayList<Character> graveyard){
        var deadNameList = new ArrayList<String>();
        for (Character character : graveyard) {
            deadNameList.add(character.getName());
        }
        return deadNameList;
    }

    static void executeDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        Menu.printWithColor("FIGHT!", ConsoleColors.BLACK_BACKGROUND_BRIGHT);
        do {
            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

            System.out.printf("Player1 HP: %s \t\t Player2 HP: %s\r", selectedCharacterParty1.getHp(), selectedCharacterParty2.getHp());

            try{
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Sleep interrupted");
            }

        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

        System.out.println();
        if (!selectedCharacterParty1.isAlive()) Menu.printWithColor("Player1 " + selectedCharacterParty1.getName() + " is dead", ConsoleColors.RED_BACKGROUND);
        else Menu.printWithColor("Player2 " + selectedCharacterParty2.getName() + " is dead", ConsoleColors.RED_BACKGROUND);

    }

    private static void sendToGraveyard(Party party1, Party party2, ArrayList<Character> graveyard, Character selectedCharacterParty1, Character selectedCharacterParty2) {
        if (!selectedCharacterParty1.isAlive()) {
            graveyard.add(selectedCharacterParty1);
            party1.getCharactersInParty().remove(selectedCharacterParty1);
        }
        if (!selectedCharacterParty2.isAlive()) {
            graveyard.add(selectedCharacterParty2);
            party2.getCharactersInParty().remove(selectedCharacterParty2);
        }
    }

    public static Character pickPlayer(Party party) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Character selectedPlayer;
        printParty(party);
        System.out.println("\nPlease type the position of your selection:");

        input = scanner.nextLine().trim().toLowerCase();

        if(isNumeric(input)){
            int parsedInput = Integer.parseInt(input);
            selectedPlayer = party.getCharactersInParty().get(Math.max(party.getCharactersInParty().size()-1 ,parsedInput));
        } else {
            Menu.printWithColor("You have not inserted any of the options so we chose one for you. Be more careful next time \uD83D\uDE08", ConsoleColors.PURPLE);
            selectedPlayer =  party.getCharactersInParty().get(0);
        }
        return selectedPlayer;
    }

    public static boolean isNumeric(String str) {
        if(str !=null){
        return str.matches("-?\\d+(\\.\\d+)?");  }
            else return false;
    }

    private static void printParty(Party party) {
        System.out.println("Place\tName\t\tHP\t\tStrength/Intelligence\tStamina/Mana");
        System.out.println("-----\t----\t\t--\t\t---------------------\t------------");
        for (int i = 0; i < party.getCharactersInParty().size(); i++) {
            printWarriorWizard(Integer.toString(i), party.getCharactersInParty().get(i));
        }
    }

}
