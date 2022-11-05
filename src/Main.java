import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
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
                    randomWar();
                    break;
                case 2:
                    System.out.println("This option is not implemented yet, please pick other option:");
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

    private static void fullRandomGame() {
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
        String[] warriorNameList = {"Arne", "Birger", "Bjørn", "Bo", "Erik", "Frode", "Gorm", "Halfdan", "Rashmi", "Zhenya"};
        String[] wizardNameList = {"Leilani", "Mpho", "Rupinder", "Vinnie", "Zhihao", "Padma", "Inyene", "Ime", "Suman", "Tayler"};
        Random rand = new Random();
        int numOfPlayerInParty = rand.nextInt(10 - 5) + 5;
        int idWar = 0;
        int idWiz = 0;
        for (int i = 0; i < numOfPlayerInParty; i++) {

            int typeOfPlayer = rand.nextInt(2); // 0= Warrior, 1=Wizard
            if (typeOfPlayer == 0) {
                int index = rand.nextInt(warriorNameList.length);
                int randHp = rand.nextInt(201 - 100) + 100;
                int randStamina = rand.nextInt(51);
                int randStrength = rand.nextInt(11);
                var newWarrior = new Warrior(++idWar, warriorNameList[index], randHp, randStamina, randStrength);

                // If the name already exists add Jr.
                addJrToName(P1, newWarrior);
                P1.add(newWarrior);

            } else {
                int index = rand.nextInt(wizardNameList.length);
                int randHp = rand.nextInt(101 - 50) + 50;
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

    private static void addJrToName(ArrayList<Character> party, Character newCharacter) {
        Boolean nameExists = party.stream().map(Character::getName).anyMatch(newCharacter.name::equals);
        if (nameExists) newCharacter.name = newCharacter.name + " Jr";

    }

    private static void randomWar() {
        System.out.println("MagicWars");
        var party1 = createPartyRandom();
        System.out.println(party1);
        System.out.println(party1.size());
        var party2 = createPartyRandom();
        System.out.println(party2);
        System.out.println(party2.size());
        System.out.println(party1.get(3).getClass());
        Battle.executeRandomBattle(party1, party2);
        System.out.println("\nSomebody wins!!!!\nCongrats to somebody!!\n\nChoose another option:");
    }
}