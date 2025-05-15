import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

public class HonestCalculatorTest extends StageTest {

    private String[] msg = {"Enter an equation",
            "Do you even know what numbers are? Stay focused!",
            "Yes ... an interesting math operation. You've slept through all classes, haven't you?",
            "Yeah... division by zero. Smart move..."};

    private Object[][] data = {
            {
                    new String[][]{
                            {"2 + 1.1", "3.1"}
                    }
            },
            {
                    new String[][] {
                            {"2 + m", join("\n", msg[1], msg[0])}, {"3 + 3", "6.0"}
                    }
            },
            {
                    new String[][] {
                            {"2 + m", join("\n", msg[1], msg[0])}, {"3 n 3", join("\n", msg[2], msg[0])},
                            {"m - 2", join("\n", msg[1], msg[0])}, {"4 * 5", "20.0"}
                    }
            },
            {
                    new String[][] {
                            {"2 + m", join("\n", msg[1], msg[0])}, {"3 n 3", join("\n", msg[2], msg[0])},
                            {"4 / 0", join("\n", msg[3], msg[0])},{"4 * 5.2", "20.8"}
                    }
            },
            {
                    new String[][] {
                            {"2.0 + 1", "3.0"}
                    }
            },
            {
                    new String[][] {
                            {"411 - 211", "200.0"}
                    }
            }
    };

    @DynamicTest(data="data")
    CheckResult test(String[][] items) {
        TestedProgram pr = new TestedProgram();

        String output = pr.start();
        if (!output.contains(msg[0])) {
            return CheckResult.wrong(String.format("Expected: %s\nFound:    %s", msg[0], output.trim()));
        }

        for (String[] item : items) {
            output = pr.execute(item[0]);

            if(!item[1].trim().equals(output.trim()))
                return CheckResult.wrong(String.format("Expected: %s\nFound:    %s", item[1].trim(), output.trim()));
        }

        if (!pr.isFinished())
            return CheckResult.wrong("Your program unnecessarily waiting for input.");

        return CheckResult.correct();
    }

    private String addEnter(String text) {
        return join("\n", text, msg[0]);
    }

    private String addMemory(String text) {
        return join("\n", text, msg[4]);
    }

    private String join(String delim, int start, int end) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = start; i < end; i++) {
            stringBuilder.append(msg[i]);
        }

        return stringBuilder.toString();
    }

    private String join(String delim, String ... words) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            stringBuilder.append(word).append(delim);
        }

        return stringBuilder.toString();
    }
}