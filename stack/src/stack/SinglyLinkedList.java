package stack;

/**
 * A Linked List with only one line, linked to the next node in the list.
 * 
 * @author Code take from the book: Data Structures and Algoriths by Goodrich
 * @param <E> the specified type which this SinglyLinkedList will hold
 */
public class SinglyLinkedList<E> {
    
    /**
     * A Node of this SInglyLinkedList
     * 
     * @param <E> the specified type which this Node will hold
     */
    private static class Node<E> {
        
        /**
         * The element this node holds.
         */
        private E element;
        
        /**
         * The next node that follows this node in the list.
         */
        private Node<E> next;
        
        /**
         * Constructs a Node of the specified type holding the specified element.
         * 
         * @param e the element this node will hold
         * @param n the node that will be linked as next, after this node in the list
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        
        /**
         * Returns the element this node holds.
         * 
         * @return the element
         */
        public E getElement() { return element; }
        
        /**
         * Returns the node that is linked to this node, which comes next in 
         * the list.
         * 
         * @return the node that is linked to this node
         */
        public Node<E> getNext() { return next; }
        
        /**
         * Links the given node to this node, making it the next one in the list.
         * 
         * @param n the node to be linked
         */
        public void setNext(Node<E> n) { next = n; }
    }
    
    /**
     * The first Node in the list.
     */
    private Node<E> head = null;
    
    /**
     * The last node in the list.
     */
    private Node<E> tail = null;
    
    /**
     * The number of elements in this list.
     */
    private int size = 0;
    
    /**
     * Constructs a new, empty SinglyLinkedList.
     */
    public SinglyLinkedList() {}
    
    /**
     * Returns the number of elements in the list.
     * 
     * @return the number of elements in the list
     */
    public int size() { return size; }
    
    /**
     * Checks whether the list is empty.
     * 
     * @return whether the list is empty.
     */
    public boolean isEmpty() { return size == 0; } 
    
    /**
     * Returns the element stored in the first node of the list, without
     * removing it. 
     * 
     * @return the head's element
     */
    public E first() {
        if(isEmpty()) return null;
        return head.getElement();
    }
    
    /**
     * Adds to the front of the list.
     * 
     * @param e the element to add 
     */
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if(size == 0)
            tail = head;
        size++;
    }
    
    /**
     * Removes the first element of the list.
     * 
     * @return the first element of the list. 
     */
    public E removeFirst() {
        if(isEmpty()) return null;
        E answer = head.getElement();
        head = head.getNext();
        size--;
        if(size == 0)
            tail = null;
        return answer;
    }
}