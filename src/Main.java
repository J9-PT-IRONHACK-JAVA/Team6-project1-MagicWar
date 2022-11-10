import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        var party1 = new Party(1);
        var party2 = new Party(2);

        Menu.mainMenu(party1, party2);
    }
}