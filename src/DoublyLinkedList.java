/*
 * name: Hilya Khalil
 * pid: A15646071
 */

import java.util.AbstractList;

/**
 * Doubly-Linked List Implementation
 *
 * @author Hilya Khalil
 * @since 10/29/19
 */
public class DoublyLinkedList<T> extends AbstractList<T> {
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {

            data = element;

        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {

            data = element;
            next = nextNode;
            prev = prevNode;

        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {

            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {

            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {

            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {

            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {

            prev = p;

        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {

            return prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {

            getPrev().setNext(getNext());
            getNext().setPrev(getPrev());

        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {

        head = new Node(null);
        tail = new Node(null);

        head.setNext(tail);
        head.setPrev(null);
        tail.setNext(null);
        tail.setPrev(head);

        nelems = 0;

    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {

        if (element == null) {
            throw new NullPointerException();
        }

        Node a = new Node(element, tail, tail.getPrev());
        tail.getPrev().setNext(a);
        tail.setPrev(a);
        //head.setPrev(a);

        //implementation of adding the new data
        nelems++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * Appends a node containing the specified data to the end of the list.
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        if (element == null) {
            throw new NullPointerException();
        }

        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        Node a = getNth(index);
        Node b = new Node(element, a, a.getPrev());
        a.getPrev().setNext(b);
        a.setPrev(b);
        nelems ++;

        //implementation of adding the new data
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {

        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;

    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * Checks if list contains a specified element at least once.
     */
    @Override
    public boolean contains(Object element) {

        T data = (T)element;
        Node a = head;

        if (element == null) {
            throw new NullPointerException();
        }

        for (int i = 0; i <= nelems; i++) {
            a = a.getNext();
            if (a == tail || a.getElement() == null) {
                return false;
            }
            if (a.getElement().equals(data)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * Access the data contained in the node at the specified index.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {

        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        return getNth(index).getElement();

    }

    /**
     * Helper method to get the Nth node in our list
     *
     * A helper method for accessing the node in the specified index of the list.
     */
    private Node getNth(int index) {

        Node a = head;
        for (int i = 0; i <= index; i++) {
            a = a.getNext();
        }
        return a;
    }

    /**
     * Determine if the list empty
     *
     * Returns true if the list contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {

        if (nelems == 0) {
            return true;
        }
           return false;
    }

    /**
     * Remove the element from position index in the list
     *
     * Removes the element at the specified index from the list and return the data it contained.
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {

        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }


        Node a = getNth(index);
        T data = a.getElement();


        a.getNext().setNext(a.getPrev());
        a.getPrev().setNext(a.getNext());
        a.remove();
        nelems--;
        return data;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * Alters data stored in the node at the specified index and returns the data previously at the specified position.
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {

        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (element == null) {
            throw new NullPointerException();
        }

        Node a = getNth(index);
        T b = a.getElement();
        a.setElement(element);
        return b;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * Return the number of elements stored in the list.
     */
    @Override
    public int size() {

        return nelems;
    }

    /**
     * Inserts another linked list of the same type into this one
     *
     * Splice otherList into the current list at the specified index.
     */
    public void splice(int index, DoublyLinkedList<T> otherList) throws IndexOutOfBoundsException {

        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (otherList.isEmpty()) {
            return;
        }

        Node h = otherList.head.getNext();
        Node t = otherList.tail.getPrev();
        Node a = getNth(index);
        Node b = a.getPrev();


        h.setPrev(b);
        t.setNext(a);
        a.setPrev(t);
        b.setNext(h);

        nelems += otherList.size();

    }

    /**
     * Determine the starting indices that match the subSequence
     *
     * Determine the starting indices for all match locations. These indices can result in overlapping subsequences.
     */
    public int[] match(DoublyLinkedList<T> subsequence) {

        //A list to hold all the starting indices found
        DoublyLinkedList<Integer> indices = new DoublyLinkedList<>();

        if (size() <= 0) {
            return new int[0];
        }

        if (subsequence.size() > 0) {
            Node a = head;
            for (int i = 0; i < size(); i++) {
                if (a == null) {
                    break;
                }

                a = a.getNext();
                Node next = a;

                for (int j = 0; j < subsequence.size(); j++) {
                    if (next == null) {
                        break;
                    }

                    T el = next.getElement();
                    T subElem = subsequence.get(j);
                    if (el == null) {
                        break;

                    }
                    if (el.equals(subElem)) {
                        if (j == subsequence.size() -1) {
                            indices.add(i);

                        }
                        next = next.getNext();
                    }
                    else {
                        break;
                    }
                }
            }

        } else {

            for (int i = 0; i < size(); i++) {
                indices.add(i);
            }
        }


        // Array Conversion
        int[] startingIndices = new int[indices.size()];
        for (int i = 0; i < indices.size(); i++) {
            startingIndices[i] = indices.get(i);
        }
        return startingIndices;
    }

}
