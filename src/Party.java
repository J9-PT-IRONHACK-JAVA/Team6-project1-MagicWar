import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Party {

    private int id;
    private ArrayList<Character> charactersInParty = new ArrayList<Character>();

    public Party() {
    }

    public Party(int id) {
        this.id = id;
    }

    public Party(int id, ArrayList<Character> charactersInParty) {
        this.id = id;
        this.charactersInParty = charactersInParty;
    }

    public static ArrayList<Character> createPartyRandom() {

        var randomParty = new ArrayList<Character>();
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
                addJrToName(randomParty, newWarrior);
                randomParty.add(newWarrior);

            } else {
                int index = rand.nextInt(wizardNameList.length);
                int randHp = rand.nextInt(101 - 50) + 50;
                int randMana = rand.nextInt(51);
                int randIntelligence = rand.nextInt(51);
                var newWizard = new Wizard(++idWiz, wizardNameList[index], randHp, randMana, randIntelligence);
                // If the name already exists add Jr.
                addJrToName(randomParty, newWizard);
                randomParty.add(newWizard);
            }

        }
        return randomParty;
    }

    private static void addJrToName(ArrayList<Character> party, Character newCharacter) {
        Boolean nameExists = party.stream().map(Character::getName).anyMatch(newCharacter.name::equals);
        if (nameExists) newCharacter.name = newCharacter.name + " Jr";

    }


    public static ArrayList<Character> createManualParty() {

         var currentParty = new ArrayList<Character>();
         Scanner scanner = new Scanner(System.in);
         //input variables through console menu

         String input;
         do {
             var manualPartyMenu = """
                     Manual Party Creation
                     =====================
                     Your current party has %s players
                     Which character you want to create next?
                                         
                     1- WARRIOR
                     2- WIZARD
                              
                     3- DONE - party creation completed
                     4- CANCEL - abort and go to previous Menu
                     ===============
                     Write your COMMAND:
                     """.formatted(currentParty.size());

             System.out.println(manualPartyMenu);
             input = scanner.nextLine().trim().toLowerCase();
             switch (input) {
                 case "1" -> currentParty.add(createWarriorManual());
                 case "2" -> currentParty.add(createWizardManual());
                 case "3" -> {
                     if (currentParty.isEmpty()) Menu.printWithColor("Party is empty! Create at least one character or write CANCEL to abort\n", ConsoleColors.RED);
                     else input = "4";
                 }
                 case "4" -> currentParty.clear();
                 default -> Menu.printWithColor("Invalid option. Please select a number from the menu\n", ConsoleColors.RED);
             }
         } while (!input.equals("4"));
        return currentParty;
     }



         public static Warrior createWarriorManual() {
             String input;
             Scanner scanner = new Scanner(System.in);
             var newCurrentWarrior = new Warrior();
             System.out.println("Type a NAME for the Warrior");
             input = scanner.nextLine().trim().toLowerCase();
             newCurrentWarrior.setName(input);
             System.out.println("Set an initial HP of the Warrior (100-200)");
             input = scanner.nextLine().trim().toLowerCase();
             int hp = processPropertyInput(input);
             newCurrentWarrior.setHp(hp);
             System.out.println("Set an initial STAMINA of the Warrior (10-50)");
             input = scanner.nextLine().trim().toLowerCase();
             int stamina = processPropertyInput(input);
             newCurrentWarrior.setStamina(stamina);
             System.out.println("Set an initial STRENGTH of the Warrior (1-50)");
             input = scanner.nextLine().trim().toLowerCase();
             int strength = processPropertyInput(input);
             newCurrentWarrior.setStrength(strength);

             //scanner.close();

             return newCurrentWarrior;
         }

    public static Wizard createWizardManual() {
        String input;
        var newCurrentWizard = new Wizard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a NAME for the Wizard");
        input = scanner.nextLine().trim().toLowerCase();
        newCurrentWizard.setName(input);
        System.out.println("Set an initial HP of the Wizard (100-200)");
        input = scanner.nextLine().trim().toLowerCase();
        int hp = processPropertyInput(input);
        newCurrentWizard.setHp(hp);
        System.out.println("Set an initial MANA of the Wizard (10-50)");
        input = scanner.nextLine().trim().toLowerCase();
        int mana = processPropertyInput(input);
        newCurrentWizard.setMana(mana);
        System.out.println("Set an initial INTELLIGENCE of the Wizard (1-50)");
        input = scanner.nextLine().trim().toLowerCase();
        int intelligence = processPropertyInput(input);
        newCurrentWizard.setIntelligence(intelligence);

        //scanner.close();

        return newCurrentWizard;
    }

         private static int processPropertyInput(String input) {
            int propertyValueChosen = 0;
             Scanner scanner = new Scanner(System.in);
            if (input.matches("^\\d+$")) {
                propertyValueChosen = Integer.parseInt(input);
            } else {
                do {
                    Menu.printWithColor("Error\nInvalid input, it must be a number", ConsoleColors.RED);
                    input = scanner.nextLine().trim().toLowerCase();
                } while (!input.matches("^\\d+$"));
                propertyValueChosen = Integer.parseInt(input);
            }
            //scanner.close();
            return propertyValueChosen;
         }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Character> getCharactersInParty() {
        return charactersInParty;
    }

    public void setCharactersInParty(ArrayList<Character> charactersInParty) {
        this.charactersInParty = charactersInParty;
    }


}
