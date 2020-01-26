package businesstest;

import org.junit.runner.JUnitCore;

public class TestMain {
    public static void main(String args[]) {
        JUnitCore junit = new JUnitCore();
        junit.run(TestRunner.class);
    }
}
