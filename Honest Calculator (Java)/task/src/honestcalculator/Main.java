package honestcalculator;

import java.util.Scanner;

public class Main {
    private static float memory = 0;
    private static boolean stop = true;

    private final static String msg_0 = "Enter an equation";
    private final static String msg_1 = "Do you even know what numbers are? Stay focused!";
    private final static String msg_2 = "Yes ... an interesting math operation. You've slept through all classes, haven't you?";
    private final static String msg_3 = "Yeah... division by zero. Smart move...";
    private final static String msg_4 = "Do you want to store the result? (y / n):";
    private final static String msg_5 ="Do you want to continue calculations? (y / n):";
    private final static String msg_6 = " ... lazy";
    private final static String msg_7 = " ... very lazy";
    private final static String msg_8 = " ... very, very lazy";
    private final static String msg_9 = "You are";
    private final static String msg_10 = "Are you sure? It is only one digit! (y / n)";
    private final static String msg_11 = "Don't be silly! It's just one number! Add to the memory? (y / n)";
    private final static String msg_12 = "Last chance! Do you really want to embarrass yourself? (y / n)";

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

            //check the validity of the input
            boolean valid = !input.isEmpty() && isValid(input);
            if (!valid) continue;

            //Calculations begins
            processAction(input);

            //User doesn't want to continue
            if(!stop){
                break;
            }
        }
    }


    private static void storeResult(float result, String choice) {
        Scanner scanner = new Scanner(System.in);
        String[] messages = {msg_10, msg_11, msg_12};
        if(choice.equals("y")) {
            boolean isDigit = is_one_digit(result + "");
            if(isDigit){
                int msgIndex = 0;
                //System.out.println(msg_10);
                while(msgIndex < 3){
                    System.out.println(messages[msgIndex++]);
                    String ans = scanner.next();
                    if(ans.equals("n")){
                        break;
                    }
//                    else if (ans.equals("y")){
//                        System.out.println((messages[msgIndex++]));
//                    }
                    if(msgIndex == 3){
                        if(result != 0) {
                            memory = result;
                        }
                    }
                }
            }else{
                if(result != 0) {
                    memory = result;
                }
            }
        }
    }

    private static void processAction(String input) {
        //If input contains M use the memory if not negative
        boolean matched = input.contains("M");
        //boolean matchedSign = input.contains("/");
        if (matched) {
            input = input.replace("M", "" + memory);
        }

        String replaceInput = input.replace(" ", "");
        String[] parts = replaceInput.split("[-+*/]");

        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        String oper = replaceInput.replaceAll("[\\d]*\\.*","");
        check(parts[0], oper, parts[1]);
        float result = operation(x,oper,y);
        if(y == 0 && oper.equals("/")) {
            return;
        }else{
            System.out.println(result);
            continueCalculating(result);
        }
    }

    private static void continueCalculating(float result) {
        Scanner scanner = new Scanner(System.in);
        //Storing the results
        System.out.println(msg_4);
        String choice = scanner.nextLine();
        storeResult(result, choice);

        //Continue Calculating
        System.out.println(msg_5);
        String choice1 = scanner.nextLine();
        stop = choice1.equals("y");;
    }

    private static void check(String x, String oper, String y) {
        String msg = "";
        if(x.matches("^[0-9]*\\.0$")){
            x = x.replace(".0", "");
        }if(y.matches("^[0-9]*\\.0$")){
            y = y.replace(".0", "");
        }
        if(is_one_digit(x) && is_one_digit(y)){
            msg = msg + msg_6;
        }if((x.equals("1") || y.equals("1")) && oper.equals("*")){
            msg = msg + msg_7;
        }if((x.equals("0") || y.equals("0")) && (oper.equals("*") || oper.equals("+") || oper.equals("-"))){
            msg = msg + msg_8;
        }if(!msg.isEmpty()){
            msg = msg_9 + msg;
            System.out.println(msg);
        }

    }

    private static float operation(double x, String oper, double y) {
        if(y == 0 && oper.equals("/")){
            System.out.println(msg_3);
            return 0;
        }
        return (float) switch (oper) {
            case "+" -> x + y;
            case "-" -> x - y;
            case "*" -> x * y;
            case "/" -> x / y;
            default -> 0;
        };

    }

    private static boolean is_one_digit(String digit){
        if(digit.matches("^[0-9]*\\.0$")){
            digit = digit.replace(".0", "");
        }
        try{
            int x = Integer.parseInt(digit);
            return x > -10 && x < 10;
        }catch (NumberFormatException ex){
            return false;
        }
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
            }
//            else if(input.matches("[0-9]\\.?[0-9]? ?/ ?0")){
//                System.out.println(msg_3);
//                return false;
//            }
            return true;
        }
}
