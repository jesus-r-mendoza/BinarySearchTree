package tree;
/**
 * A node of the {@code BinarySearchTree} which follows the properties of a 
 * Binary Search tree, where each node points to its parent, its left child, and 
 * its right child. Since the object type is bounded by the {@code Comparable}
 * interface, this class assigns nodes' their children based on whether their 
 * value is less than (left child) or greater than (right child).
 * 
 * @author Jesus R Mendoza
 * @param <E> specified object type this node can hold
 * @see BinarySearchTree
 */
public class BSTNode<E extends Comparable<E>>
{
    /**
     * The parent reference of this node.
     */
    protected BSTNode<E> parent = null;
    
    /**
     * The reference to the left child of this node.
     */
    protected BSTNode<E> left = null;
    
    /**
     * The reference to the right child of this node.
     */
    protected BSTNode<E> right = null;
    
    /**
     * The data which this node holds.
     */
    private E data;
    
    /**
     * Constructs a new {@code BSTNode} containing the specified data.
     * 
     * @param dataIn the data which this node will hold
     */
    public BSTNode(E dataIn) {
        data = dataIn;
    }
    
    /**
     * Retrieves the data which this node holds. 
     * 
     * @return the data which this node holds
     */
    public E getData() {
        return data;
    }
    
    /**
     * Sets the data which this node holds to the new specified data.
     * 
     * @param newData the specified data which this node will now hold.
     */
    public void setData(E newData) {
        data = newData;
    }
}