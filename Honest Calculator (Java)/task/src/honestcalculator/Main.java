package honestcalculator;

import java.util.Scanner;

public class Main {
    private final static String msg_0 = "Enter an equation";
    private final static String msg_1 = "Do you even know what numbers are? Stay focused!";
    private final static String msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";

    public static void main(String[] args) {
        // write your code here
        calculator();
    }

    private static void calculator() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(msg_0);
            String input = scanner.nextLine();
            boolean valid = isValid(input);
            if(valid){
                break;
            }
        }
    }

        private static boolean isValid (String input){
            if (input.matches("[0-9]\\.?[0-9]? [a-z] [0-9]\\.?[0-9]?")) {
                System.out.println(msg_2);
                return false;
            } else if (input.matches("[a-z] [-|+|*] [0-9]?\\.?[0-9]?") ||
            input.matches("[0-9]?\\.?[0-9]? [-|+|*] [a-z]")) {
                System.out.println(msg_1);
                return false;
            }
            return true;
        }
}
