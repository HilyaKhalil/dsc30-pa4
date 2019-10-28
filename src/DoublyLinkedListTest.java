import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Doubly-Linked List Tester
 *
 * @author Hilya Khalil
 * @since 10/26/2019
 */

public class DoublyLinkedListTest {
    @SuppressWarnings({"WeakerAccess", "RedundantSuppression"})
    private DoublyLinkedList<Integer> listA;
    private DoublyLinkedList<Integer> listB;
    private DoublyLinkedList<String> listC;
    private DoublyLinkedList<String> listD;

    @Before
    public void setUp() throws Exception {
        listA = new DoublyLinkedList<>();
        listA.add(1); listA.add(2); listA.add(3);
        listB = new DoublyLinkedList<>();
        listC = new DoublyLinkedList<>();
        listC.add("A"); listC.add("B"); listC.add("C");
        listD = new DoublyLinkedList<>();
        listD.add("D"); listD.add("E"); listD.add("F");
    }

    @Test
    public void add() {
        assertTrue("Adding 4 returns true", listB.add(4));
        assertEquals("4 is the first item in the list", (Integer) 4, listB.get(0));
        assertTrue("Adding 5 returns true", listB.add(5));
        assertEquals("5 is the second item in the list", (Integer) 5, listB.get(1));
    }

    // Tests that a null pointer exception
    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAdd() {
        listA.add(10);
        // No exception
        listA.add(20);
        // No exception
        listA.add(null);
        // Exception thrown
    }

    @Test
    public void addAtIndex() {
        listA.add(1, 4);
        assertEquals("4 is the second item in the list", (Integer) 4, listA.get(1));
        listA.add(2, 5);
        assertEquals("5 is the third item in the list", (Integer) 5, listA.get(2));
    }

    // Tests that an IndexOutOfBoundsException is thrown when adding at an invalid index
    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsInAddAtIndex() {
        listB.add(1);
        // No exception
        listB.add(80);
        // No exception
        listB.add(5, 6);
        // Exception thrown
    }

    // Tests that a NullPointerException is thrown when adding a null value at an index
    @Test(expected = NullPointerException.class)
    public void testNullPointerExceptionInAddAtIndex() {
        listA.add(2, 8);
        // No exception
        listB.add(0, null);
        // Exception thrown
        listA.add(2, null);
        // Exception thrown
    }

    @Test
    public void clear() {
        assertEquals("listA has 3 elements", 3, listA.size());
        listA.clear();
        assertEquals("listA has 0 elements", 0, listA.size());
        listB.clear();
        assertEquals("listB has 0 elements", 0, listB.size());

    }

    @Test
    public void contains() {
        assertTrue(listA.contains(2));
        assertFalse(listB.contains(1));
        assertFalse(listA.contains(6));
    }

    @Test (expected = NullPointerException.class)
    public void testNullPointerExceptionInContains() {
        assertTrue(listA.contains(2));
        // No exception
        assertFalse(listB.contains(1));
        // No exception
        assertFalse(listA.contains(null));
        // Exception thrown
    }

    @Test
    public void get() {
        assertEquals("1 is at index 0", (Integer) 1, listA.get(0));
        assertEquals("2 is at index 2", (Integer) 2, listA.get(1));
        assertEquals("3 is at index 3", (Integer) 3, listA.get(2));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInGet() {
        assertEquals("1 is at index 0", (Integer) 1, listA.get(0));
        // No exception
        assertEquals("2 is at index 2", (Integer) 2, listA.get(-1));
        // Exception thrown
        assertEquals("3 is at index 3", (Integer) 3, listA.get(4));
        // Exception thrown
    }

    @Test
    public void isEmpty() {
        assertFalse(listA.isEmpty());
        assertTrue(listB.isEmpty());
        listB.add(8);
        assertFalse(listB.isEmpty());
    }

    @Test
    public void remove() {
        assertEquals("2 is returned and removed", (Integer) 2, listA.remove(1));
        assertEquals("Size() is now 2", 2, listA.size());
        assertEquals("Index 1 now contains 3", (Integer) 3, listA.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInRemove() {
        assertEquals("2 is returned and removed", (Integer) 2, listA.remove(1));
        // No exception
        assertEquals("Size() is now 2", 2, listA.size());
        // No exception
        assertEquals("Index 1 now contains 3", (Integer) 3, listA.get(1));
        // No exception
        assertEquals("2 is returned and removed", (Integer) 2, listA.remove(2));
        // Exception thrown
        assertEquals("2 is returned and removed", (Integer) 2, listA.remove(-1));
        // Exception thrown
    }

    @Test
    public void set() {
        assertEquals("Returns old element 3", (Integer) 3, listA.set(2, 4));
        assertEquals("New element 4 is at index 2", (Integer) 4, listA.get(2));
        assertEquals("Returns old element 1", (Integer) 1, listA.set(0, 5));
        assertEquals("New element 5 is at index 1", (Integer) 5, listA.get(0));

    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsExceptionInSet() {
        assertEquals("Returns old element 3", (Integer) 3, listA.set(2, 4));
        // No exception
        assertEquals("New element 4 is at index 2", (Integer) 4, listA.get(2));
        // No exception
        assertEquals("Returns old element 1", (Integer) 1, listA.set(5, 5));
        // Exception thrown
        assertEquals("Returns old element 1", (Integer) 1, listA.set(-1, 5));
        // Exception thrown
    }

    @Test (expected = NullPointerException.class)
    public void testNullPointerExceptionInSet() {
        assertEquals("Returns old element 3", (Integer) 3, listA.set(2, 4));
        // No exception
        assertEquals("Returns old element 2", (Integer) 2, listA.set(1, 5));
        // No exception
        assertEquals((Integer) 1, listA.set(0, null));
        // Exception thrown
    }

    @Test
    public void size() {
        assertEquals(3, listA.size());
        assertEquals(0, listB.size());
        assertTrue(listB.add(2));
        assertEquals(1, listB.size());
    }

    @Test
    public void splice() {
        listC.splice(0, listD);
        assertEquals("D", listC.get(0));
        assertEquals("A", listC.get(3));
        listC = new DoublyLinkedList<>();
        listC.add("A"); listC.add("B"); listC.add("C");
        listD.splice(2, listC);
    }

    @Test
    public void match() {
        // TODO
    }
}