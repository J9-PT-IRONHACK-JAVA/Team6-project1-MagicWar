import java.util.ArrayList;
import java.util.Scanner;

public class Party {
     protected ArrayList<Character> currentParty = new ArrayList<Character>();

     private Character selectedCharacter;
    private final Scanner scanner = new Scanner(System.in);

    public Party() {

    }

    public ArrayList<Character> createManualParty() {

         Scanner scanner = new Scanner(System.in);
         //input variables through console menu

         String input;
         do {
             var manualPartyMenu = """
                     Manual Party Creation
                     =====================
                     Your current party has %s players
                     Which character you want to create next?
                                         
                     1) WARRIOR
                     2) WIZARD
                                               
                     DONE - party creation completed
                     CANCEL - abort and go to previous Menu
                     ===============
                     Write your COMMAND:
                     """.formatted(currentParty.size());

             System.out.println(manualPartyMenu);
             input = scanner.nextLine().trim().toLowerCase();
             switch (input) {
                 case "1" -> currentParty.add(createWarriorManual());
                 case "2" -> currentParty.add(createWizardManual());
                 case "done" -> {
                     if (currentParty.isEmpty()) printWithColor("Party is empty! Create at least one character or write CANCEL to abort\n", ConsoleColors.RED);
                     else input = "cancel";
                 }
                 case "cancel" -> currentParty.clear();
                 default -> printWithColor("Command not recognized!", ConsoleColors.RED);
             }
         } while (!input.equals("cancel"));
        scanner.close();
        return currentParty;
     }



         public Warrior createWarriorManual() {
             String input;
             //Scanner scanner = new Scanner(System.in);
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

    public Wizard createWizardManual() {
        String input;
        var newCurrentWizard = new Wizard();
        //Scanner scanner = new Scanner(System.in);
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

         private int processPropertyInput(String input) {
            int propertyValueChosen = 0;
             //Scanner scanner = new Scanner(System.in);
            if (input.matches("^\\d+$")) {
                propertyValueChosen = Integer.parseInt(input);
            } else {
                do {
                    printWithColor("Error\nInvalid input, must be a number", ConsoleColors.RED);
                    input = scanner.nextLine().trim().toLowerCase();
                } while (!input.matches("^\\d+$"));
                propertyValueChosen = Integer.parseInt(input);
            }
            //scanner.close();
            return propertyValueChosen;
         }



         public static void printWithColor(String text, String color){
             System.out.println(color + text + ConsoleColors.RESET);
         }


         //var manualParty = new ArrayList<Character>();
         //var newWarrior = new Warrior(++idWar, warriorName, randHp, randStamina, randStrength);
         //P1.add(newWarrior);
         //var newWizard = new Wizard(++idWiz, wizardNameList, randHp, randMana, randIntelligence);
         //P1.add(newWizard);


    public Party(ArrayList<Character> characterArraylist) {
        this.currentParty = characterArraylist;
    }

    public ArrayList<Character> getCurrentParty() {
        return currentParty;
    }

    public void setCurrentParty(ArrayList<Character> currentParty) {
        this.currentParty = currentParty;
    }
    public void setRandomCharacterArraylist(ArrayList<Character> characterArraylist) {
        this.currentParty = characterArraylist;
    }
}
