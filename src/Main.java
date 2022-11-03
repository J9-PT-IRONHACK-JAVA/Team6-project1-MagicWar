import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        System.out.println("MagicWars");
        var party1 = createPartyRandom();
        System.out.println(party1);
        System.out.println(party1.size());
        var party2 = createPartyRandom();
        System.out.println(party2);
        System.out.println(party2.size());

        System.out.println(party1.get(3).getClass());

        Battle.executeRandomBattle(party1, party2);

    }
    public static ArrayList<Character> createPartyRandom() {

        // assign party name?
        var P1 = new ArrayList<Character>();
        String[] warriorNameList = {"Arne"};
        String[] wizardNameList = {"Leilani"};
        Random rand = new Random();
        int numOfPlayerInParty = rand.nextInt(10-5)+5;
        int idWar = 0;
        int idWiz = 0;
        for (int i = 0; i < numOfPlayerInParty; i++) {

            int typeOfPlayer = rand.nextInt(2); // 0= Warrior, 1=Wizard
            if (typeOfPlayer == 0) {
                int index = rand.nextInt(warriorNameList.length);
                int randHp = rand.nextInt(201-100)+100;
                int randStamina = rand.nextInt(51);
                int randStrength = rand.nextInt(11);
                var newWarrior = new Warrior(++idWar, warriorNameList[index], randHp, randStamina, randStrength);

                // If the name already exists add Jr.
                addJrToName(P1, newWarrior);
                P1.add(newWarrior);

            } else {
                int index = rand.nextInt(wizardNameList.length);
                int randHp = rand.nextInt(101-50)+50;
                int randMana = rand.nextInt(51);
                int randIntelligence = rand.nextInt(51);
                var newWizard = new Wizard(++idWiz, wizardNameList[index], randHp, randMana, randIntelligence);
                // If the name already exists add Jr.
                addJrToName(P1, newWizard);
                P1.add(newWizard);
            }

        }
        return P1;
    }

    private static void addJrToName(ArrayList<Character> P1, Character newCharacter) {
        Boolean nameExists = P1.stream().map(Character::getName).anyMatch(newCharacter.name::equals);
        if(nameExists) newCharacter.name = newCharacter.name + " Jr" ;
    }
}