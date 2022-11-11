import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
    static Random rand = new Random();

    public static void randomWar(Party party1, Party party2) {
        if (party1.getCharactersInParty().isEmpty()) party1.setCharactersInParty(Party.createPartyRandom());
        System.out.println("Party 1:" + party1.getCharactersInParty().size() + " Characters\n" + party1.getCharactersInParty());
        if (party2.getCharactersInParty().isEmpty()) party2.setCharactersInParty(Party.createPartyRandom());
        System.out.println("Party 2:" + party2.getCharactersInParty().size() + " Characters\n" + party2.getCharactersInParty());
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
        Random rand = new Random();

        do {
            var selectedCharacterParty1 = pickPlayer(party1);
            var selectedCharacterParty2 = party2.getCharactersInParty().get(rand.nextInt(party2.getCharactersInParty().size()));

            announceDuel(selectedCharacterParty1, selectedCharacterParty2);
            executeDuelAndBury(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);


        } while (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty());

        announceBattleOutcome(party1, graveyard);

    }

    public static void executeVSBattle (Party party1, Party party2) {
        var graveyard = new ArrayList<Character>();
        Random rand = new Random();
        do {
            var selectedCharacterParty1 = pickPlayer(party1);
            var selectedCharacterParty2 = pickPlayer(party2);

            announceDuel(selectedCharacterParty1, selectedCharacterParty2);
            executeDuelAndBury(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);

        } while (!party1.getCharactersInParty().isEmpty() && !party2.getCharactersInParty().isEmpty());

        announceBattleOutcome(party1, graveyard);

    }
    private static void announceDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        // TODO improve presentation of characters to play
        System.out.print(selectedCharacterParty1 + " ");
        System.out.println(selectedCharacterParty2);
    }

    private static void announceBattleOutcome(Party party1, ArrayList<Character> graveyard) {
        //Show winning party and Graveyard
        if (!party1.getCharactersInParty().isEmpty()) {
            Menu.printWithColor("Party 1 has won", ConsoleColors.GREEN_BOLD);
        } else {
            Menu.printWithColor("Party 2 has won", ConsoleColors.GREEN_BOLD);
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
                                             ░          ░ ░                         ░        \s
                                
                """, ConsoleColors.PURPLE);
        Menu.printWithColor("\u2620 Rest in peace " + getDeadNames(graveyard) + " \u2620", ConsoleColors.PURPLE);
    }

    private static void executeDuelAndBury(Party party1, Party party2, ArrayList<Character> graveyard, Character selectedCharacterParty1, Character selectedCharacterParty2) {
        //Duel
        executeDuel(selectedCharacterParty1, selectedCharacterParty2);
        sendToGraveyard(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);
    }


    private static ArrayList<String> getDeadNames(ArrayList<Character> graveyard){
        var deadNameList = new ArrayList<String>();
        for (int i = 0; i < graveyard.size(); i++){
            deadNameList.add(graveyard.get(i).getName());
        }
        return deadNameList;
    }

    static void executeDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        do { //TODO improve presentation of battle. Is it possible that the HP position is static and overwritten? Maybe display the attacks happening with a time-delay?
            System.out.print(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp() + " ");
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());

            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

            System.out.print(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp() + " ");
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());


        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

        //TODO improve display of result of battle
        System.out.println(selectedCharacterParty1.getName() + ": is Alive-> " + selectedCharacterParty1.isAlive() + " " + ", Health points-> " + selectedCharacterParty1.getHp());
        System.out.println(selectedCharacterParty2.getName() + ": is Alive-> " + selectedCharacterParty2.isAlive()  + ", Health points-> " + selectedCharacterParty2.getHp() );
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


    public static Character pickPlayer(Party party1) {
        Scanner scanner = new Scanner(System.in);
        String input= null;
        Character selectedPlayer= null;
        for (int i = 0; i < party1.getCharactersInParty().size(); i++) {
            System.out.println(i + " - Name:" + party1.getCharactersInParty().get(i).name + " Health points:" + party1.getCharactersInParty().get(i).hp + " " );

        }
       // input = UtilsIO.DATA.nextLine().trim().toLowerCase();
        input = scanner.nextLine().trim().toLowerCase();

        if(isNumeric(input)){
            int parsedInput=Integer.parseInt(input);
            if(parsedInput <= party1.getCharactersInParty().size() &&  parsedInput > 0) selectedPlayer =  party1.getCharactersInParty().get(parsedInput);
        } else {
            Menu.printWithColor("You have not inserted any of the options so we chose one for you. Be more careful next time \uD83D\uDE08", ConsoleColors.PURPLE);
            selectedPlayer =  party1.getCharactersInParty().get(0);
        }
            System.out.println(selectedPlayer);

        return selectedPlayer;
    }
    public static boolean isNumeric(String str) {
        if(str !=null){
        return str.matches("-?\\d+(\\.\\d+)?");  }
            else return false;
    }

}
