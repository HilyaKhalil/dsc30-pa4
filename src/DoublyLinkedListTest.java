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
        thirdList.add("A"); thirdList.add("B"); thirdList.add("C");
        fourthList = new DoublyLinkedList<>();
        fourthList.add("D"); fourthList.add("E"); fourthList.add("F");
    }

    @Test
    public void add() {
        assertTrue("Adding 4 returns true", emptyList.add(4));
        assertEquals("4 is the first item in the list", (Integer) 4, emptyList.get(0));
        assertTrue("Adding 5 returns true", emptyList.add(5));
        assertEquals("5 is the second item in the list", (Integer) 5, emptyList.get(1));
    }

    // Tests that a null pointer exception
    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAdd() {
        firstList.add(10);
        // No exception
        firstList.add(20);
        // No exception
        firstList.add(null);
        // Exception thrown
    }

    @Test
    public void addAtIndex() {
        firstList.add(1, 4);
        assertEquals("4 is the second item in the list", (Integer) 4, firstList.get(1));
        firstList.add(2, 5);
        assertEquals("5 is the third item in the list", (Integer) 5, firstList.get(2));
    }

    // Tests that an IndexOutOfBoundsException is thrown when adding at an invalid index
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsInAddAtIndex() {
        emptyList.add(1);
        // No exception
        emptyList.add(80);
        // No exception
        emptyList.add(5, 6);
        // Exception thrown
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
        assertEquals("listA has 3 elements", 3, firstList.size());
        firstList.clear();
        assertEquals("listA has 0 elements", 0, firstList.size());
        emptyList.clear();
        assertEquals("secondList has 0 elements", 0, emptyList.size());

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
        // No exception
        assertFalse(emptyList.contains(1));
        // No exception
        assertFalse(firstList.contains(null));
        // Exception thrown
    }

    @Test
    public void get() {
        assertEquals("1 is at index 0", (Integer) 1, firstList.get(0));
        assertEquals("2 is at index 2", (Integer) 2, firstList.get(1));
        assertEquals("3 is at index 3", (Integer) 3, firstList.get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInGet() {
        assertEquals("1 is at index 0", (Integer) 1, firstList.get(0));
        // No exception
        assertEquals("2 is at index 2", (Integer) 2, firstList.get(-1));
        // Exception thrown
        assertEquals("3 is at index 3", (Integer) 3, firstList.get(4));
        // Exception thrown
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
        assertEquals("Size() is now 2", 2, firstList.size());
        assertEquals("Index 1 now contains 3", (Integer) 3, firstList.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInRemove() {
        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(1));
        // No exception
        assertEquals("Size() is now 2", 2, firstList.size());
        // No exception
        assertEquals("Index 1 now contains 3", (Integer) 3, firstList.get(1));
        // No exception
        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(2));
        // Exception thrown
        assertEquals("2 is returned and removed", (Integer) 2, firstList.remove(-1));
        // Exception thrown
    }

    @Test
    public void set() {
        assertEquals("Returns old element 3", (Integer) 3, firstList.set(2, 4));
        assertEquals("New element 4 is at index 2", (Integer) 4, firstList.get(2));
        assertEquals("Returns old element 1", (Integer) 1, firstList.set(0, 5));
        assertEquals("New element 5 is at index 1", (Integer) 5, firstList.get(0));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSet() {
        assertEquals("Returns old element 3", (Integer) 3, firstList.set(2, 4));
        // No exception
        assertEquals("New element 4 is at index 2", (Integer) 4, firstList.get(2));
        // No exception
        assertEquals("Returns old element 1", (Integer) 1, firstList.set(5, 5));
        // Exception thrown
        assertEquals("Returns old element 1", (Integer) 1, firstList.set(-1, 5));
        // Exception thrown
    }

    @Test (expected = NullPointerException.class)
    public void testNullPointerExceptionInSet() {
        assertEquals("Returns old element 3", (Integer) 3, firstList.set(2, 4));
        // No exception
        assertEquals("Returns old element 2", (Integer) 2, firstList.set(1, 5));
        // No exception
        assertEquals((Integer) 1, firstList.set(0, null));
        // Exception thrown
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
        assertEquals("D", thirdList.get(0));
        assertEquals("A", thirdList.get(3));
        thirdList = new DoublyLinkedList<>();
        thirdList.add("A"); thirdList.add("B"); thirdList.add("C");
        fourthList.splice(3, thirdList);
        assertEquals("C", fourthList.get(5));
        assertEquals("A", fourthList.get(3));

    }

    @Test
    public void spliceEnd() {
        thirdList.splice(3, fourthList);
        assertEquals("F", thirdList.get(5));
        assertEquals("D", thirdList.get(3));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSplice() {
        thirdList.splice(4, fourthList);
    }

    @Test
    public void match() {

        DoublyLinkedList<String> thirdList2 = new DoublyLinkedList<>();
        thirdList2.add("A"); thirdList2.add("B"); thirdList2.add("C");
        thirdList.splice(3, thirdList2);
        DoublyLinkedList<String> subSeq = new DoublyLinkedList<>();
        subSeq.add("A"); subSeq.add("B"); subSeq.add("C");
        int[] indices = {0, 3};
        int[] matchIndices = thirdList.match(subSeq);
        assertEquals(indices[0], matchIndices[0]);
        assertEquals(indices[1], matchIndices[1]);
        assertEquals(2, matchIndices.length);

    }
}