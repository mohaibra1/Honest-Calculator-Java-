package honestcalculator;

import java.util.Scanner;

public class Main {
    private static float memory = 0;

    private final static String msg_0 = "Enter an equation";
    private final static String msg_1 = "Do you even know what numbers are? Stay focused!";
    private final static String msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
    private final static String msg_3 = "Yeah... division by zero. Smart move...";
    private final static String msg_4 = "Do you want to store the result? (y / n):";
    private final static String msg_5 ="Do you want to continue calculations? (y / n):";

    public static void main(String[] args) {
        // write your code here
        calculator();
    }

    private static void calculator() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //Output msg and user input
            System.out.println(msg_0);
            String input = scanner.nextLine();

            //If input contains M use the memory if not negative
            boolean matched = input.contains("M");
            boolean matchedSign = input.contains("/");
            if (matchedSign && memory == 0){
                System.out.println(msg_3);
                continue;
            }else if (matched && memory >= 0) {
                //Calculate using saved number
                input = input.replace("M", "" + memory);
            }else if (matched && memory < 0) {
                //Otherwise continue
                continue;
            }

            //check the validity of the input
            boolean valid = !input.isEmpty() && isValid(input);
            if (!valid) continue;

            //Calculations begins
            float result = processAction(input);
            System.out.println(result);

            //Storing the results
            System.out.println("Do you want to store the result? (y / n):");
            String choice = scanner.nextLine();
            storeResult(result, choice);

            //Continue Calculating
            System.out.println("Do you want to continue calculations? (y / n):");
            String choice1 = scanner.nextLine();
            boolean continueCal = continueCalculation(choice1);

            //User doesn't want to continue
            if(!continueCal){
                break;
            }
        }
    }


    private static void storeResult(float result, String choice) {
        if(choice.equals("y")) {
            if (result > 0) {
                memory = result;
            }
        }
    }

    private static boolean continueCalculation(String choice) {
        return choice.equals("y");
    }


    private static float processAction(String input) {
        String replaceInput = input.replace(" ", "");
        String[] parts = replaceInput.split("[-+*/]");

        float x = Float.parseFloat(parts[0]);
        float y = Float.parseFloat(parts[1]);
        String oper = replaceInput.replaceAll("[\\d]*\\.*","");

        return operation(x,oper,y);
    }

    private static float operation(float x, String oper, float y) {
        return switch (oper) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };
    }


    private static boolean isValid (String input){
            if (input.matches("[0-9]\\.?[0-9]? [a-z!@\\s.] [0-9]\\.?[0-9]?")) {
                System.out.println(msg_2);
                return false;
            } else if (input.matches("[a-z] [-+*] [0-9]?\\.?[0-9]?") ||
            input.matches("[0-9]?\\.?[0-9]? [-+*] [a-z]")) {
                System.out.println(msg_1);
                return false;
            }else if (input.matches("[a-z] [-+*/] [a-z]")){
                System.out.println(msg_1);
                return false;
            }else if(input.matches("[0-9]\\.?[]0-9]? ?\\\\ ?[0-9]\\.?[0-9]?")){
                System.out.println(msg_1);
                return false;
            }else if(input.matches("[0-9]\\.?[0-9]? ?/ ?0")){
                System.out.println(msg_3);
                return false;
            }
            return true;
        }
}
