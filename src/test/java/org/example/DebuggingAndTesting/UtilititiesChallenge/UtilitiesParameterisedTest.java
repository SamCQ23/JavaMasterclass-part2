package org.example.DebuggingAndTesting.UtilititiesChallenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class UtilitiesParameterisedTest {

    private Utilities util;
    private String input;
    private String output;

    @org.junit.Before
    public void setup() {
        util = new Utilities();
        System.out.println("Running a test...");
    }

    public UtilitiesParameterisedTest(String input, String output) {
        this.input = input;
        this.output = output;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions() {
        return Arrays.asList(new Object[][]{
                {"ABCDEFF", "ABCDEF"},
                {"AB88EFFG", "AB8EFG"},
                {"112233445566", "123456"},
                {"A", "A"}
        });
    }

    @Test
    public void removePairs() throws Exception {
        String  result = util.removePairs(input);
        assertEquals(output, result);
    }
}
