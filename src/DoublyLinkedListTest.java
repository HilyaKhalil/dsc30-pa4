import org.junit.Before;
import org.junit.Test;
import sun.tools.tree.DoubleExpression;

import static org.junit.Assert.*;

/**
 * Doubly-Linked List Tester
 *
 * @author Hilya Khalil
 * @since 10/26/2019
 */

public class DoublyLinkedListTest {
    @SuppressWarnings({"WeakerAccess", "RedundantSuppression"})
    private DoublyLinkedList<Integer> firstList;
    private DoublyLinkedList emptyList;
    private DoublyLinkedList<String> thirdList;
    private DoublyLinkedList<String> fourthList;

    @Before
    public void setUp() throws Exception {

        firstList = new DoublyLinkedList<>();
        firstList.add(1); firstList.add(2); firstList.add(3);
        emptyList = new DoublyLinkedList<>();
        thirdList = new DoublyLinkedList<>();
        thirdList.add("One"); thirdList.add("Two"); thirdList.add("Three");
        fourthList = new DoublyLinkedList<>();
        fourthList.add("Four"); fourthList.add("Five"); fourthList.add("Six");
    }

    @Test
    public void add() {

        assertTrue("Add 2 gives two", emptyList.add(2));
        assertEquals("2 is the head of the list", (Integer) 2, emptyList.get(0));
        assertTrue("Add 7 gives seven", emptyList.add(7));
        assertEquals("7 is the second value in list", (Integer) 7, emptyList.get(1));
    }

    // Tests that a null pointer exception
    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAdd() {

        firstList.add(15);
        firstList.add(25);
        firstList.add(null);
    }

    @Test
    public void addAtIndex() {

        firstList.add(1, 12);
        assertEquals("12 is the second value in list", (Integer) 12, firstList.get(1));
        firstList.add(2, 19);
        assertEquals("19 is the third value in list", (Integer) 19, firstList.get(2));
    }

    // Tests that an IndexOutOfBoundsException is thrown when adding at an invalid index
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsInAddAtIndex() {

        emptyList.add(12);
        emptyList.add(44);
        emptyList.add(3, 3);

    }

    // Tests that a NullPointerException is thrown when adding a null value at an index
    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAddAtIndex() {
        firstList.add(2, 8);
        // No exception
        emptyList.add(0, null);
        // Exception thrown
        firstList.add(2, null);
        // Exception thrown
    }

    @Test
    public void clear() {
        assertEquals("firstList has 3 elements", 3, firstList.size());
        firstList.clear();
        assertEquals("firstList has 0 elements", 0, firstList.size());
        emptyList.clear();
        assertEquals("emptyList has 0 elements", 0, emptyList.size());

    }

    @Test
    public void contains() {

        assertTrue(firstList.contains(2));
        assertFalse(emptyList.contains(1));
        assertFalse(firstList.contains(6));
    }

    @Test (expected = NullPointerException.class)
    public void testNullPointerExceptionInContains() {

        assertTrue(firstList.contains(2));
        assertFalse(emptyList.contains(1));
        assertFalse(firstList.contains(null));
    }

    @Test
    public void get() {
        assertEquals("Value 1 is at index 0", (Integer) 1, firstList.get(0));
        assertEquals("Value 2 is at index 2", (Integer) 2, firstList.get(1));
        assertEquals("Value 3 is at index 3", (Integer) 3, firstList.get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInGet() {

        assertEquals("Value 1 is at index 0", (Integer) 1, firstList.get(0));
        assertEquals("Value 2 is at index 2", (Integer) 2, firstList.get(-1));
        assertEquals("Value 3 is at index 3", (Integer) 3, firstList.get(4));
    }

    @Test
    public void isEmpty() {
        assertFalse(firstList.isEmpty());
        assertTrue(emptyList.isEmpty());
        emptyList.add(8);
        assertFalse(emptyList.isEmpty());
    }

    @Test
    public void remove() {

        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(1));
        assertEquals("Size() is 2", 2, firstList.size());
        assertEquals("Index 1 now contains 3", (Integer) 3, firstList.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInRemove() {

        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(1));
        assertEquals("Size() is now 2", 2, firstList.size());
        assertEquals("Index 1 now contains 3", (Integer) 3, firstList.get(1));
        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(2));
        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(-1));

    }

    @Test
    public void set() {

        assertEquals("Returns old value 1", (Integer) 1, firstList.set(0, 5));
        assertEquals("New value 5 is at index 1", (Integer) 5, firstList.get(0));
        assertEquals("Returns old value 3", (Integer) 3, firstList.set(2, 4));
        assertEquals("New value 4 is at index 2", (Integer) 4, firstList.get(2));


    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSet() {

        assertEquals("Returns old value 1", (Integer) 1, firstList.set(5, 5));
        assertEquals("Returns old value 3", (Integer) 3, firstList.set(2, 4));
        assertEquals("New value 4 is at index 2", (Integer) 4, firstList.get(2));
        assertEquals("Returns old value 1", (Integer) 1, firstList.set(-1, 5));
    }

    @Test (expected = NullPointerException.class)
    public void testNullPointerExceptionInSet() {

        assertEquals("Returns old value 3", (Integer) 3, firstList.set(2, 4));
        assertEquals("Returns old value 2", (Integer) 2, firstList.set(1, 5));
        assertEquals((Integer) 1, firstList.set(0, null));
    }

    @Test
    public void size() {
        assertEquals(3, firstList.size());
        assertEquals(0, emptyList.size());
        assertTrue(emptyList.add(2));
        assertEquals(1, emptyList.size());
    }

    @Test
    public void spliceMid() {
        thirdList.splice(0, fourthList);
        assertEquals("Four", thirdList.get(0));
        assertEquals("One", thirdList.get(3));
        thirdList = new DoublyLinkedList<>();
        thirdList.add("One"); thirdList.add("Two"); thirdList.add("Three");
        fourthList.splice(3, thirdList);
        assertEquals("Three", fourthList.get(5));
        assertEquals("One", fourthList.get(3));

    }

    @Test
    public void spliceEnd() {
        thirdList.splice(3, fourthList);
        assertEquals("Six", thirdList.get(5));
        assertEquals("Four", thirdList.get(3));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSplice() {
        thirdList.splice(4, fourthList);
    }

    @Test
    public void match() {

        DoublyLinkedList<String> thirdList2 = new DoublyLinkedList<>();
        thirdList2.add("One"); thirdList2.add("Two"); thirdList2.add("Three");
        thirdList.splice(3, thirdList2);
        DoublyLinkedList<String> subSeq = new DoublyLinkedList<>();
        subSeq.add("One"); subSeq.add("Two"); subSeq.add("Three");
        int[] indices = {0, 3};
        int[] matchIndices = thirdList.match(subSeq);
        assertEquals(indices[0], matchIndices[0]);
        assertEquals(indices[1], matchIndices[1]);
        assertEquals(2, matchIndices.length);

    }
}