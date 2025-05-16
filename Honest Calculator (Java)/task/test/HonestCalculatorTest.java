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
            "You are",
            "Are you sure? It is only one digit! (y / n)",
            "Don't be silly! It's just one number! Add to the memory? (y / n)",
            "Last chance! Do you really want to embarrass yourself? (y / n)"
    };

    private Object[][] data = {
            {
                    new String[][] {{"4 * 5.0", join("\n", msg[9] + msg[6], addMemory("20.0"))}, {"n", msg[5]}, {"n", ""}}
            },
            {
                    new String[][] {{"2 + 5.5", addMemory("7.5")}, {"y", msg[5]}, {"y", msg[0]}, {"M - 9", addMemory("-1.5")}, {"n", msg[5]}, {"n", ""}}
            },
            {
                    new String[][] {
                            {"225 / 15", addMemory("15.0")}, {"y", msg[5]}, {"y",msg[0]},
                            {"1 * 5", join("\n", msg[9] + msg[6] + msg[7], addMemory("5.0"))}, {"y", msg[10]}, {"y", msg[11]}, {"n", msg[5]}, {"y", msg[0]},
                            {"M - 10", addMemory("5.0")}, {"y", msg[10]}, {"y", msg[11]}, {"y", msg[12]}, {"y", msg[5]}, {"y", msg[0]},
                            {"M / M",  join("\n", msg[9] + msg[6], addMemory("1.0"))}, {"n", msg[5]}, {"n", ""}
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
            return CheckResult.wrong("Your program unnecessarily waiting for input.");

        return CheckResult.correct();
    }


    private String addMemory(String text) {
        return String.format("%s\n%s", text, msg[4]);
    }

    private String join(String delim, String ... words) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            stringBuilder.append(word).append(delim);
        }

        return stringBuilder.toString();
    }
}