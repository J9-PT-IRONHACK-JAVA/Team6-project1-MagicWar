import java.util.ArrayList;
import java.util.Random;

public class Battle {

    public static void executeFight (ArrayList<Character> party1, ArrayList<Character> party2) {

        var graveyard = new ArrayList<Character>();



        Random rand = new Random();
        var selectedCharacterParty1 = party1.get(rand.nextInt(party1.size()));
        var selectedCharacterParty2 = party2.get(rand.nextInt(party2.size()));

        System.out.println(selectedCharacterParty1);
        System.out.println(selectedCharacterParty2);

        //Duel

        do {
            System.out.println(selectedCharacterParty1.getName() + ": "+ selectedCharacterParty1.getHp());
            System.out.println(selectedCharacterParty2.getName() + ": "+ selectedCharacterParty2.getHp());

            selectedCharacterParty1.receiveDamage(selectedCharacterParty2.attack());
            selectedCharacterParty2.receiveDamage(selectedCharacterParty1.attack());

        } while (selectedCharacterParty1.isAlive() && selectedCharacterParty2.isAlive());

        System.out.println(selectedCharacterParty1.getName() + ": " + selectedCharacterParty1.isAlive());
        System.out.println(selectedCharacterParty2.getName() + ": " + selectedCharacterParty2.isAlive());


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
