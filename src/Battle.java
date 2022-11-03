import java.util.ArrayList;
import java.util.Random;

public class Battle {

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

    private static void executeDuel(Character selectedCharacterParty1, Character selectedCharacterParty2) {
        do {
            System.out.print(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp() + " ");
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());

            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

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


    //private static int getPlayerDamage(Character player) {
    //    return player instanceof Warrior ? ((Warrior) player).attack() : ((Wizard) player).attack();
    //}


}
