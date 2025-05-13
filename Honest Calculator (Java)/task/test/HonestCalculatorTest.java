import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

public class HonestCalculatorTest extends StageTest {

    private String[] msg = new String[] {"Enter an equation",
            "Do you even know what numbers are? Stay focused!",
            "Yes ... an interesting math operation. You've slept through all classes, haven't you?"};
    private Object[][] data = {
           {
               new String[][]{
                   {"2 + 1.1", ""}
                }
           },
           {
               new String[][] {
                       {"2 + m", join("\n", msg[1], msg[0])}, {"3 + 3", ""}
               }
           },
           {
                   new String[][]{
                           {"2 + m", join("\n", msg[1], msg[0])}, {"3 n 3", join("\n", msg[2], msg[0])},
                           {"m - 2", join("\n", msg[1], msg[0])}, {"4 * 5.2", ""}
                   }
           }
   };


    @DynamicTest(data = "data")
    CheckResult test(String[][] items) {
        TestedProgram pr = new TestedProgram();
        String output = pr.start();

        if (!output.strip().contains(msg[0])) {
            return CheckResult.wrong(String.format("Expected: %s;\nFound:    %s", msg[0], output.strip()));
        }

        for (String[] item : items) {
            output = pr.execute(item[0]);

            String responseString = "";
            if(!item[1].trim().equals(output.strip())) {
                responseString = String.format("Expected: %s\nFound:    %s", item[1], output);

                if (item[1].strip().length() == 0){
                    responseString = responseString + "\n" + "A correct expression should terminate the program per the flowchart.";
                }
                return CheckResult.wrong(responseString);
            }

        }
        return CheckResult.correct();
    }

    private String join(String delim, String ... words) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            stringBuilder.append(word).append(delim);
        }

        return stringBuilder.toString();
    }
}