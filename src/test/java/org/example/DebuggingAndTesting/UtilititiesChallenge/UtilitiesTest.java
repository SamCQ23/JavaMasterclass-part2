package org.example.DebuggingAndTesting.UtilititiesChallenge;

import org.example.DebuggingAndTesting.UnitTesting.BankAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

//@RunWith(Parameterized.class)
public class UtilitiesTest {

    private Utilities utils;
    private String input;

    private String expected;

//    public UtilitiesTest(String input, String expected) {
//        this.input = input;
//        this.expected = expected;
//    }

    @org.junit.Before
    public void setup() {
        utils = new Utilities();
        System.out.println("Running a test...");
    }

    @Test
    public void everyNthChar() {
        char[] output = utils.everyNthChar(
                new char[] {'h', 'e', 'l', 'l', 'o'}, 2);
        assertArrayEquals(new char[] {'e', 'l'}, output);
        char[] output2 = utils.everyNthChar(
                new char[] {'h', 'e', 'l', 'l', 'o'}, 8);
        assertArrayEquals(new char[] {'h', 'e', 'l', 'l', 'o'},
                output2);
    }

//    @Parameterized.Parameters
//    public static Collection<Object[]> testConditions() {
//        return Arrays.asList(new Object[][]{
//                {"AABCDDEFF", "ABCDEF"},
//                {"ABCCABDEEF", "ABCABDEF"}
//        });
//    }

    @Test
    public void removePairs() {
//        fail("test code not written yet");
//        String  result = utils.removePairs(input);
//        assertEquals(expected, result);
        assertEquals("ABCDEF", utils.removePairs("AABCDDEFF"));
        assertEquals("ABCABDEF", utils.removePairs("ABCCABDEEF"));
//        assertNull(null, utils.removePairs(null));
        assertNull("Did not get null returned when argument passed was null",
                utils.removePairs(null));
        assertEquals("A", utils.removePairs("A"));
        assertEquals("", utils.removePairs(""));
    }

    @Test
    public void converter() {
        assertEquals(300, utils.converter(10, 5));
    }

    @org.junit.Test(expected= ArithmeticException.class)
    public void converter_arithmeticException() throws Exception {

        utils.converter(10, 0);
    }

    @Test
    public void nullIfOddLength() {
        assertNull(utils.nullIfOddLength("odd"));
        assertNotNull(utils.nullIfOddLength("even"));
    }
}