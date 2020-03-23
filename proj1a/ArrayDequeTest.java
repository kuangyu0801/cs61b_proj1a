import org.junit.Test;

import java.nio.charset.IllegalCharsetNameException;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testGet(){
        ArrayDeque<String> lld1 = new ArrayDeque<>();
        lld1.addFirst("front");
        lld1.addLast("middle");
        assertEquals("front", lld1.get(0));
        assertEquals("middle", lld1.get(1));
    }


    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }

    @Test
    public void removeIsEmptySizeTest() {
        ArrayDeque<String> lld2 = new ArrayDeque<>();
        assertEquals(true, lld2.isEmpty());
        lld2.addFirst("front");
        lld2.printDeque();
        lld2.removeFirst();
        lld2.printDeque();
        assertEquals(0, lld2.size());
        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        lld2.removeLast();
        lld2.printDeque();
        assertEquals(2, lld2.size());

    }

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> lld2 = new ArrayDeque<>();
        assertEquals(true, lld2.isEmpty());
        lld2.addFirst("front");
        lld2.addLast("middle");
        assertEquals(2, lld2.size());
        lld2.addLast("back");
        lld2.printDeque();
    }

    @Test
    public void checkResizeUpTest(){
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        lld2.addFirst(4);
        lld2.addFirst(3);
        lld2.addFirst(2);
        lld2.addFirst(1);
        lld2.addLast(5);
        lld2.addLast(6);
        lld2.addLast(7);
        lld2.addLast(8);
        assertEquals(8, lld2.size());
        lld2.addLast(9);
        assertEquals(9, lld2.size());
    }

    @Test
    public void checkResizeDownTest(){
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        for (int i = 0; i < 32; i += 1) {
            lld2.addFirst(i);
        }
        lld2.printDeque();
        for (int i = 0; i < 24; i += 1) {
            lld2.removeFirst();
        }
        lld2.printDeque();
    }
 /*
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

    */

}
