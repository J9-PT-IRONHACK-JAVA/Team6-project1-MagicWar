import java.util.*;

public class Party {

    private int id;
    private ArrayList<Character> charactersInParty = new ArrayList<Character>();
    private static Scanner scanner=new Scanner(System.in);
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
        String[] warriorNameList = {"Arnel", "Birger", "Bjørn", "Bolsen", "Eriks", "Frode", "Gormo", "Halfda", "Rashmi", "Zhenya"};
        String[] wizardNameList = {"Leilu", "Mphor", "Rupin", "Vinnie", "Zhihao", "Padma", "Inyene", "Imeks", "Suman", "Tayler"};
        Random rand = new Random();
        int numOfPlayerInParty = rand.nextInt(5 - 3) + 3;
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
        else newCharacter.name = newCharacter.name + "   ";
    }

    public static ArrayList<Character> createManualParty() {

         var currentParty = new ArrayList<Character>();
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
             Scanner scanner = new Scanner(System.in);
             input = scanner.nextLine().trim().toLowerCase();
             if (currentParty.size() == 5) input = "3";

             switch (input) {
                 case "1" -> currentParty.add(createWarriorManual());
                 case "2" -> currentParty.add(createWizardManual());
                 case "3" -> {
                     if (currentParty.isEmpty()) Menu.printWithColor("Party is empty! Create at least one character or write CANCEL to abort\n", ConsoleColors.RED);
                     else{
                         Menu.printWithColor("Party successfully created!", ConsoleColors.GREEN);
                         input = "4";
                     }
                 }
                 case "4" -> currentParty.clear();
                 default -> Menu.printWithColor("Invalid option. Please select a number from the menu\n", ConsoleColors.RED);
             }
         } while (!input.equals("4"));
        return currentParty;
     }

     public static Warrior createWarriorManual() {
             String input;
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

             return newCurrentWarrior;
         }

    public static Wizard createWizardManual() {
        String input;
        var newCurrentWizard = new Wizard();
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

        return newCurrentWizard;
    }

    private static int processPropertyInput(String input) {
            int propertyValueChosen = 0;
            if (input.matches("^\\d+$")) {
                propertyValueChosen = Integer.parseInt(input);
            } else {
                do {
                    Menu.printWithColor("Error\nInvalid input, it must be a number", ConsoleColors.RED);
                    input = UtilsIO.DATA.nextLine().trim().toLowerCase();
                } while (!input.matches("^\\d+$"));
                propertyValueChosen = Integer.parseInt(input);
            }
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
