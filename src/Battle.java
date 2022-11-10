import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Battle {

    public static void randomWar() {
        System.out.println("MagicWars");
        var party1 = Party.createPartyRandom();
        System.out.println(party1);
        System.out.println(party1.size());
        var party2 = Party.createPartyRandom();
        System.out.println(party2);
        System.out.println(party2.size());
        System.out.println(party1.get(3).getClass());
        executeRandomBattle(party1, party2);
    }

    public static void executeRandomBattle (ArrayList<Character> party1, ArrayList<Character> party2) {

        var graveyard = new ArrayList<Character>();
        Random rand = new Random();

        do {
            var selectedCharacterParty1 = party1.get(rand.nextInt(party1.size()));
            var selectedCharacterParty2 = party2.get(rand.nextInt(party2.size()));

            System.out.print(selectedCharacterParty1 + " ");
            System.out.println(selectedCharacterParty2);

            //Duel
            executeDuel(selectedCharacterParty1, selectedCharacterParty2);
            sendToGraveyard(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);

        } while (party1.size() > 0 && party2.size() > 0);

        //Show winning party and Graveyard
        if (party1.size() > 0) {
            System.out.println("Team 1 has won");
        } else {
            System.out.println("Team 2 has won");
        }

        System.out.println("Count your dead both teams: " + getDeadNames(graveyard));

    }

    private static ArrayList<Character> getDeadNames(ArrayList<Character> graveyard){
        ArrayList deadNameList = new ArrayList<Character>();
        for (int i = 0; i < graveyard.size(); i++){
            deadNameList.add(graveyard.get(i).getName());
        }
        return deadNameList;
    }

    public static void executeDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        do {


            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

            System.out.print(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp() + " ");
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());

        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

        System.out.println(selectedCharacterParty1.getName() + ": is Alive-> " + selectedCharacterParty1.isAlive() + " " + ", Health points-> " + selectedCharacterParty1.getHp());
        System.out.println(selectedCharacterParty2.getName() + ": is Alive-> " + selectedCharacterParty2.isAlive()  + ", Health points-> " + selectedCharacterParty2.getHp() );
    }

    private static void sendToGraveyard(ArrayList<Character> party1, ArrayList<Character> party2, ArrayList<Character> graveyard, Character selectedCharacterParty1, Character selectedCharacterParty2) {
        if (!selectedCharacterParty1.isAlive()) {
            graveyard.add(selectedCharacterParty1);
            party1.remove(selectedCharacterParty1);
        }
        if (!selectedCharacterParty2.isAlive()) {
            graveyard.add(selectedCharacterParty2);
            party2.remove(selectedCharacterParty2);
        }
    }

    public static Character pickPlayer(ArrayList<Character> party1) {
        Scanner scanner = new Scanner(System.in);
        String input= null;
        Character selectedPlayer= null;
        for (int i = 0; i < party1.size(); i++) {
            System.out.println(i + " - Name:" + party1.get(i).name + " Health points:" + party1.get(i).hp + " " );
            input = scanner.nextLine().trim().toLowerCase();
        }
        if(isNumeric(input)){
            int parsedInput=Integer.parseInt(input);
            selectedPlayer =party1.get(parsedInput);
            selectedPlayer.setAlive(true);
        }
        if (selectedPlayer != null) {
            System.out.println(selectedPlayer);
        } else {
            System.out.println("You haven't selected a player");
        }

        return selectedPlayer;
    }
    public static boolean isNumeric(String str) {
        if(str !=null){
        return str.matches("-?\\d+(\\.\\d+)?");  }
            else return false;
    }


}
