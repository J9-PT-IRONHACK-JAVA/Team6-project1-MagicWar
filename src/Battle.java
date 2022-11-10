import java.util.ArrayList;
import java.util.Random;

public class Battle {

    //@TODO For Push merge: Adjusted the randomWar method and some others, to correct some legacy

    public static void randomWar(Party party1, Party party2) {
        if (party1.getCharactersInParty().isEmpty()) party1.setCharactersInParty(Party.createPartyRandom());
        System.out.println("Party 1:" + party1.getCharactersInParty().size() + " Characters\n" + party1.getCharactersInParty());
        if (party2.getCharactersInParty().isEmpty()) party2.setCharactersInParty(Party.createPartyRandom());
        System.out.println("Party 2:" + party2.getCharactersInParty().size() + " Characters\n" + party2.getCharactersInParty());
        executeRandomBattle(party1.getCharactersInParty(), party2.getCharactersInParty());
    }

    public static void executeRandomBattle (ArrayList<Character> party1, ArrayList<Character> party2) {

        var graveyard = new ArrayList<Character>();
        Random rand = new Random();

        do {
            var selectedCharacterParty1 = party1.get(rand.nextInt(party1.size()));
            var selectedCharacterParty2 = party2.get(rand.nextInt(party2.size()));

            // TODO improve presentation of characters to play
            System.out.print(selectedCharacterParty1 + " ");
            System.out.println(selectedCharacterParty2);

            //Duel
            executeDuel(selectedCharacterParty1, selectedCharacterParty2);
            sendToGraveyard(party1, party2, graveyard, selectedCharacterParty1, selectedCharacterParty2);

        } while (party1.size() > 0 && party2.size() > 0);

        //Show winning party and Graveyard
        if (party1.size() > 0) {
            Menu.printWithColor("Party 1 has won", ConsoleColors.GREEN_BOLD);
        } else {
            Menu.printWithColor("Party 2 has won", ConsoleColors.GREEN_BOLD);
        }

        Menu.printWithColor("Count your dead both teams: " + getDeadNames(graveyard), ConsoleColors.PURPLE);

    }

    private static ArrayList<String> getDeadNames(ArrayList<Character> graveyard){
        var deadNameList = new ArrayList<String>();
        for (int i = 0; i < graveyard.size(); i++){
            deadNameList.add(graveyard.get(i).getName());
        }
        return deadNameList;
    }

    private static void executeDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        do { //TODO improve presentation of battle. Is it possible that the HP position is static and overwritten? Maybe display the attacks happening with a time-delay?
            System.out.print(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp() + " ");
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());

            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

        //TODO improve display of result of battle
        System.out.print(selectedCharacterParty1.getName() + ": " + selectedCharacterParty1.isAlive() + " ");
        System.out.println(selectedCharacterParty2.getName() + ": " + selectedCharacterParty2.isAlive());

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

}
