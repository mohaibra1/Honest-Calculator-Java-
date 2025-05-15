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
            "Do you want to continue calculations? (y / n):"};

    private Object[][] data = {
            {
                    new String[][] {
                            {"4 * 5", addMemory("20.0")}, {"y", msg[5]}, {"n", ""}
                    }
            },
            {
                    new String[][] {
                            {"4 * 5.2", addMemory("20.8")}, {"y", msg[5]}, {"y", msg[0]},
                            {"1 + M", addMemory("21.8")}, {"y", msg[5]}, {"n", ""}
                    }
            },
            {
                    new String[][] {
                            {"2 + 5", addMemory("7.0")}, {"n", msg[5]}, {"y", msg[0]},
                            {"21.0 / M", addEnter(msg[3])}, {"5 + M", addMemory("5.0")},
                            {"y", msg[5]}, {"n", ""}
                    }
            }
    };

    @DynamicTest(data = "data")
    CheckResult test(String[][] items) {
        TestedProgram pr = new TestedProgram();

        String output = pr.start();

        if (!output.contains(msg[0])) {
            return CheckResult.wrong(String.format("Expected: (%s);\nFound:    (%s)", msg[0], output.trim()));
        }

        for (String[] item : items) {
            output = pr.execute(item[0]);

            if (!item[1].equals(output.trim())) {
                return CheckResult.wrong(String.format("Expected: (%s);\nFound:    (%s)", item[1], output.trim()));
            }
        }

        if (!pr.isFinished())
            return CheckResult.wrong("Your program unnecessarily waiting for input.");

        return CheckResult.correct();
    }

    private String addEnter(String text) {
        return String.format("%s\n%s", text, msg[0]);
    }

    private String addMemory(String text) {
        return String.format("%s\n%s", text, msg[4]);
    }
}