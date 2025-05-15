package honestcalculator;

import java.util.Scanner;

public class Main {
    private final static String msg_0 = "Enter an equation";
    private final static String msg_1 = "Do you even know what numbers are? Stay focused!";
    private final static String msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
    private final static String msg_3 = "Yeah... division by zero. Smart move...";
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
               operation(input);
               break;
            }
        }
    }

    private static void operation(String input) {
        String replaceInput = input.replace(" ", "");
        String[] parts = replaceInput.split("[-+*/]");

        float x = Float.parseFloat(parts[0]);
        float y = Float.parseFloat(parts[1]);
        String oper = replaceInput.replaceAll("[\\d]*\\.*","");

        switch (oper){
            case "+":
                addition(x,y);
                break;
            case "-":
                substraction(x,y);
                break;
            case "*":
                multiplication(x,y);
                break;
            case "/":
                division(x,y);
                break;
            default:
                System.out.println("Invalid operator!");
        }
    }

    private static void addition(float x, float y) {
        System.out.println(x+y);
    }

    private static void substraction(float x,float y) {
        System.out.println(x-y);
    }

    private static void multiplication(float x, float y) {
        System.out.println(x*y);
    }

    private static void division(float x, float y) {
        System.out.println(x/y);
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
