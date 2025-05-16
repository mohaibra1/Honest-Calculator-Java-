import org.hyperskill.hstest.dynamic.DynamicTest;
import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testing.TestedProgram;

public class HonestCalculatorTest extends StageTest {

    private String[] msg = {"Enter an equation",
            "Do you even know what numbers are? Stay focused!",
            "Yes ... an interesting math operation. You've slept through all classes, haven't you?",
            "Yeah... division by zero. Smart move...",
            "Do you want to store the result? (y / n):",
            "Do you want to continue calculations? (y / n):",
            " ... lazy",
            " ... very lazy",
            " ... very, very lazy",
            "You are"};

    private Object[][] data = {
            {
                    new String[][] {{"4 * 5.5", addMemory("22.0")}, {"y", msg[5]}, {"n", ""}}
            },
            {
                    new String[][] {
                            {"11 * 11.1", addMemory("122.1")}, {"y", msg[5]}, {"n", ""}
                    }
            },
            {
                    new String[][] {
                                    {"1 * 5", join("\n", msg[9] + msg[6] + msg[7], addMemory("5.0"))},
                                    {"y", msg[5]},
                                    {"y", msg[0]},
                                    {"0 + M", join("\n", msg[9] + msg[6] + msg[8], addMemory("5.0"))},
                                    {"y", msg[5]},
                                    {"n", ""}
                    }
            },
            {
                    new String[][] {
                            {"2 / M", join("\n", msg[9] + msg[6], addEnter(msg[3]))},
                            {"1 * M", join("\n", msg[9] + join("", 6, 9), addMemory("0.0"))},
                            {"n", msg[5]},
                            {"y", msg[0]},
                            {"899 * 0", join("\n", msg[9] + msg[8], addMemory("0.0"))},
                            {"n", msg[5]},
                            {"n", ""}
                    }
            }

    };

    @DynamicTest(data = "data")
    CheckResult test(String[][] items) {
        TestedProgram pr = new TestedProgram();

        String output = pr.start();

        if (!output.contains(msg[0])) {
            return CheckResult.wrong(String.format("Expected: (%s);\nFound:    (%s)", msg[0], output.strip()));
        }

        for (String[] item : items) {
            output = pr.execute(item[0]);

            if (!item[1].trim().equals(output.trim())) {
                return CheckResult.wrong(String.format("Expected:\n%s\nFound:\n%s", item[1], output.strip()));
            }
        }

        if (!pr.isFinished())
            return CheckResult.wrong("Your program is unnecessarily waiting for input.");

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