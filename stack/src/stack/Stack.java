package stack;

/**
 * A Stack data structure.
 * 
 * @author Code take from the book: Data Structures and Algoriths by Goodrich
 * @param <E> the specified type which this Stack will hold
 */
public class Stack<E> {
    
    /**
     * The underlying linked list that will be used for this Stack.
     */
    private SinglyLinkedList<E> list = new SinglyLinkedList<>();
    
    /**
     * Constructs an empty Stack.
     */
    public Stack() {}
    
    /**
     * Returns how many elements this stack contains.
     * 
     * @return the number of elements in this stack
     */
    public int size() { return list.size(); }
    
    /**
     * Checks whether the stack is empty.
     * 
     * @return whether the list is empty.
     */
    public boolean isEmpty() { return list.isEmpty(); }
    
    /**
     * Adds an element to the top of the stack.
     * 
     * @param elem the element to be added
     */
    public void push(E elem) { list.addFirst(elem); }
    
    /**
     * Returns the top element without removing it from the Stack.
     * 
     * @return the top element without removing it from the Stack.
     */
    public E top() { return list.first(); }
    
    /**
     * Removes and returns the top element of the Stack.
     * 
     * @return the top element
     */
    public E pop() { return list.removeFirst(); }
}