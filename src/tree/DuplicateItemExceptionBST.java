package tree;

/**
 * Thrown to indicate that there exists a duplicate element in an instance of 
 * {@code BinarySearchTree}.
 * 
 * @author  Jesus R. Mendoza
 * @see     BinarySearchTree
 */
public class DuplicateItemExceptionBST extends RuntimeException 
{
    /**
     * Constructs a {@code DuplicateItemException} without a detail message.
     */
    public DuplicateItemExceptionBST() {
        super();
    }
    
    /**
     * Constructs a {@code DuplicateItemException} with a detail message.
     * 
     * @param msg the detail message 
     */
    public DuplicateItemExceptionBST(String msg) {
        super(msg);
    }    
}