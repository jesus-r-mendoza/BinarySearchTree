package tree;

import java.util.ArrayList;
import stack.Stack;

/**
 * A tree structure which holds data based on their respective values, corresponding
 * to each other since object types are bounded by the {@code Comparable} 
 * interface. As each node is inserted, it is placed as a left child of another 
 * node if its value is less than that node's, it is placed as a right child of 
 * that node if its value is greater than that node; that node is then called its
 * parent.
 * 
 * @author Jesus R Mendoza
 * @param <E> specified object type this tree can hold
 * @see BSTNode
 */
public class BinarySearchTree<E extends Comparable<E>>
{
    /**
     * The top most node of this {@code BinarySearchTree}; also, by definition, 
     * the ancestor of all the nodes that are beneath it.
     */
    protected BSTNode<E> root;
    
    /**
     * Constructs a new and empty {@code BinarySearchTree}.
     */
    public BinarySearchTree() {
        root = null;
    }
    
    /**
     * Constructs a new {@code BinarySearchTree} and populates its nodes with the
     * values provided in the array
     * 
     * @param elems the provided array to populate the tree with
     */
    public BinarySearchTree(E[] elems) {
        for(E elem : elems) 
            insert(elem);
    }
    
    /**
     * Inserts a given value based on its whether it is greater than or less than
     * an already existing node, if one exists. If the given value already exists
     * then a {@code DuplicateItemException} will be caught and re-thrown, otherwise
     * values less than will be added as a left child and greater values will be added
     * as a right child.
     * 
     * @param key the value to be inserted
     */
    public void insert(E key) {
        BSTNode<E> child = new BSTNode<>(key);
        if(this.isEmpty())
            root = child;
        else {
            try {
                BSTNode<E> par = insertionPoint(key);
                if(key.compareTo(par.getData()) < 0 ) {
                    par.left = child;
                    child.parent = par;
                }
                else if(key.compareTo(par.getData()) > 0) {
                    par.right = child; 
                    child.parent = par;
                }
            } catch (DuplicateItemExceptionBST ex) {
                throw ex;
            } 
        }
    }
    
    /**
     * Finds the node for which the given value should be attached to; returns
     * the node that will become the parent of the node to be inserted.
     * 
     * @param key the value of the node to be inserted
     * @return    the node that will become the parent, if one exists
     */
    private BSTNode<E> insertionPoint(E key) {
        BSTNode<E> curr = root;
        BSTNode<E> par = null;
        while(curr != null) {
            if(key.compareTo(curr.getData()) == 0)
                throw new DuplicateItemExceptionBST();
            else if(key.compareTo(curr.getData()) < 0 ) {
                par = curr;
                curr = curr.left;
            }
            else if(key.compareTo(curr.getData()) > 0) {
                par = curr;
                curr = curr.right;            
            }
        }        
        return par;        
    }
    
    /**
     * Deletes the node which holds the given value.
     * 
     * @param key the value to be deleted
     */
    public void delete(E key) {
        deleteNode(deletionPoint(key));
    }
    
    /**
     * Deletes the actual node, which also deletes the value it held.
     * 
     * @param node the to delete from the tree
     */
    private void deleteNode(BSTNode<E> node) {
        if(node != null) {
            if(isLeaf(node)) 
                deleteLeafNode(node);            
            else if(numChildren(node) == 1) 
                deleteNodeWith1Child(node);            
            else if(numChildren(node) == 2) 
                deleteNodeWith2Child(node);
        }
    }
    
    /**
     * Finds the node that will be deleted based of the provided value.
     * 
     * @param key the value to delete from this tree
     * @return    the node that will be deleted
     */
    private BSTNode<E> deletionPoint(E key){
        BSTNode<E> curr = root;
        while(curr != null) {
            if(key.compareTo(curr.getData()) == 0)
                return curr;
            else if(key.compareTo(curr.getData()) < 0) 
                curr = curr.left;
            else if(key.compareTo(curr.getData()) > 0)
                curr = curr.right;
        }
        return null;
    }
    
    /**
     * Deletes a node if it has no children, unlinks its reference to its parent
     * and therefore is collected by he java GC.
     * 
     * @param node the node, with no children, that will be deleted
     */
    private void deleteLeafNode(BSTNode<E> node) {        
        if(node.equals(root))
            root = null;
        else if(isLeftChild(node))
            node.parent.left = null;
        else if(isRightChild(node))
            node.parent.right = null;        
        node = null;
    }
    
    /**
     * Deletes a node that has only one child, attaches its child to its parent,
     * thereby replacing and3 deleting the node.
     * 
     * @param node the node, with only one child, that will be deleted
     */
    private void deleteNodeWith1Child(BSTNode<E> node) {
        BSTNode<E> child = node.left;
        if(child == null) 
            child = node.right;
        if(node.equals(root)) {
            root = child;
            child.parent = null;
        }
        else if(isLeftChild(node)) {
            node.parent.left = child;
            child.parent = node.parent;
        }
        else if(isRightChild(node)) {
            node.parent.right = child;
            child.parent = node.parent;
        }
        node = null;
    }
    
    /**
     * Deletes a node with two children; finds the maximum value of its left 
     * subtree in order to replace it, and then deletes the node where the max
     * value was found (since that value now replaces the given node as the root
     * of its subtree).
     * 
     * @param node the node, which has two children, to be deleted
     */
    private void deleteNodeWith2Child(BSTNode<E> node) {
        BSTNode<E> max = maxLeftSubTree(node);
        node.setData(max.getData());
        deleteNode(max);
    }
    
    /**
     * Finds the maximum value of a node's left subtree.
     * 
     * @param node the node which serves as the root of the entire subtree
     * @return 
     */
    private BSTNode<E> maxLeftSubTree(BSTNode<E> node) {
        BSTNode<E> max = node.left;
        while(max.right != null) 
            max = max.right;        
        return max;
    }
    
    /**
     * Counts the number of children a given node has.
     * 
     * @param node the node whose children will be counted
     * @return 
     */
    private int numChildren(BSTNode<E> node) {
        int num = 0;
        if(node.left != null)
            num++;
        if(node.right != null)
            num++;
        return num;
    }
    
    /**
     * Searches the tree and returns true if the given value is found (already
     * contained in this tree) or false if it is not.
     * 
     * @param key the value to be checked
     * @return    true if the tree contains the value given
     */
    public boolean find(E key) {
        BSTNode<E> curr = root;
        while(curr != null) {
            if(key.compareTo(curr.getData()) == 0)
                return true;
            else if(key.compareTo(curr.getData()) < 0 )
                curr = curr.left;
            else if(key.compareTo(curr.getData()) > 0)
                curr = curr.right;            
        }
        return false;
    }
    
    /**
     * Calculates the depth of a given node; how many levels deep it is with respect 
     * to the root of the entire tree.
     * 
     * @param node the node whose depth will be calculated
     * @return     the depth of the given node
     */
    public int depth(BSTNode<E> node) {
        if(node.equals(root))
            return 0; 
        else 
            return 1 + depth(node.parent);
    }
    
    /**
     * Calculates how many levels tall a given node's subtree is, from the given
     * node all the way to the deepest leaf node (whose ancestor is the given node).
     * 
     * @param node the node whose height will be calculated
     * @return     the the height of the given node
     */
    public int height(BSTNode<E> node) { 
        int n = numChildren(node);
        int h = 0;
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                if(node.left != null) 
                    h = Math.max(h, 1 + height(node.left));
                else if(node.right != null)
                    h = Math.max(h, 1 + height(node.right));
            }
            else if(i == 1) {
                if(node.right != null) 
                    h = Math.max(h, 1 + height(node.right));
                else if(node.left != null)
                    h = Math.max(h, 1 + height(node.left));
            }            
        }
        return h;
    }
    
    /**
     * Checks whether this tree is empty (has no existing nodes).
     * 
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() { 
        return root == null;
    }
    
    /**
     * Checks whether a given node is a leaf (has no children).
     * 
     * @param node the node to be checked
     * @return     true if the given node has no children
     */
    public boolean isLeaf(BSTNode<E> node) {
        return node.left == null && node.right == null;
    }
    
    /**
     * Checks whether the given node is a left child of its parent.
     * 
     * @param node the node to be checked
     * @return     true if the given node is a left child of its parent, false otherwise
     */
    public boolean isLeftChild(BSTNode<E> node) {
        if(node.parent != null && node.parent.left != null)
            return node.getData().equals(node.parent.left.getData());
        return false;
    }
    
    /**
     * Checks whether the given node is a right child of its parent.
     * 
     * @param node the node to be checked
     * @return     true if the given node is a right child of its parent, false otherwise
     */
    public boolean isRightChild(BSTNode<E> node) {
        if(node.parent != null && node.parent.right != null)
            return node.getData().equals(node.parent.right.getData());
        return false;
    }
    
    /**
     * Returns the sibling of the given node if one exists, otherwise returns {@code null}.
     * The sibling of a node which is a left child of its parent would return the 
     * right child of that parent, and vice versa.
     * 
     * @param node the given node to find its sibling
     * @return     the sibling of the given node
     */
    public BSTNode<E> sibling(BSTNode<E> node) {
        if(node.parent != null) {
            if(isLeftChild(node))
                return node.parent.right;
            return node.parent.left;
        }
        return null;
    }
    
    /**
     * Returns the sibling of the parent if one exists, otherwise returns {@code Null}. 
     * 
     * @param node the node whose uncle will be retrieved
     * @return     the uncle of the given node; a.k.a. the parent's sibling
     */
    public BSTNode<E> uncle(BSTNode<E> node) { 
        if(node.parent != null)
            return sibling(node.parent);
        return null; 
    }
    
    /**
     * Returns the parent of the parent of the given node if one exists, otherwise
     * returns {@code null}.
     * 
     * @param node the node whose grandparent will be retrieved
     * @return     the grandparent of the given node; a.k.a. the parent's parent.
     */
    public BSTNode<E> grandparent(BSTNode<E> node) { 
        if(node.parent != null) {
            if(node.parent.parent != null)
                return node.parent.parent;
        }
        return null; 
    }
    
    /**
     * Returns an {@code ArrayList}, of the class's specified object type, ordered
     * by the {@code preorder} traversal algorithm (i.e. visiting the first node,
     * then its left child, and then its right child).
     * 
     * @return the ordered ArrayList
     */
    public ArrayList<BSTNode<E>> preorder() { 
        ArrayList<BSTNode<E>> arr = new ArrayList<>();
        if(!this.isEmpty()) {            
            Stack<BSTNode<E>> stk = new Stack<>();
            BSTNode<E> curr = root;
            stk.push(curr);
            
            while(!stk.isEmpty()) {
                curr = stk.pop();
                arr.add(curr);
                if(curr.right != null)
                    stk.push(curr.right);
                if(curr.left != null)
                    stk.push(curr.left);
            }
        }
        return arr;
    }
    
    /**
     * Returns an {@code ArrayList}, of the class's specified object type, ordered
     * by the {@code inorder} traversal algorithm (i.e. visiting the left child,
     * then the actual node, and then its right child).
     * 
     * @return the ordered ArrayList 
     */
    public ArrayList<BSTNode<E>> inorder() { 
        ArrayList<BSTNode<E>> arr = new ArrayList<>();
        if(!this.isEmpty()) { 
            Stack<BSTNode<E>> stk = new Stack<>();
            BSTNode<E> curr = root;
            
            while(!stk.isEmpty() || curr != null) {
                if(curr != null) {
                    stk.push(curr);
                    curr = curr.left;
                }
                else {
                    curr = stk.pop();
                    arr.add(curr);
                    curr = curr.right;
                }
            }
        }
        return arr;
    }
    
    /**
     * Returns an {@code ArrayList}, of the class's specified object type, ordered
     * by the {@code postorder} traversal algorithm (i.e. visiting the left child,
     * then the right child, and then the actual node).
     * 
     * @return the ordered ArrayList 
     */
    public ArrayList<BSTNode<E>> postorder() { 
        ArrayList<BSTNode<E>> arr = new ArrayList<>();
        if(!this.isEmpty()) { 
            Stack<BSTNode<E>> st1 = new Stack<>();
            Stack<BSTNode<E>> st2 = new Stack<>();
            BSTNode<E> curr = root;
            st1.push(curr);
            
            while(!st1.isEmpty()) {
                curr = st1.pop();
                st2.push(curr);                
                if(curr.left != null)
                    st1.push(curr.left);
                if(curr.right != null)
                    st1.push(curr.right);
            }
            
            while(!st2.isEmpty()) {
                curr = st2.pop();
                arr.add(curr);
            }
        }
        return arr;
    }
    
    /**
     * Returns an {@code ArrayList}, of the class's specified object type, ordered
     * by the {@code breadthfirst} traversal algorithm (i.e. visiting each node
     * from left to right for each level of the tree).
     * 
     * @return the ordered ArrayList
     */
    public ArrayList<BSTNode<E>> breadthfirst() { 
        ArrayList<BSTNode<E>> arr = new ArrayList<>();
        if(!this.isEmpty()) {
            ArrayList<BSTNode<E>> q = new ArrayList<>();
            BSTNode<E> curr = root;
            q.add(curr);
            
            while(!q.isEmpty()) {
                curr = q.remove(0);
                arr.add(curr);
                if(curr.left != null)
                    q.add(curr.left);
                if(curr.right != null)
                    q.add(curr.right);
            }
        }
        return arr;
    }
    
    /**
     * Method used to print the tree.
     */
    public void printTree() {
        if (this.root.right != null) {
            this.printTree(this.root.right, true, "");
        }

        printNodeValue(this.root);

        if (this.root.left != null) {
            this.printTree(this.root.left, false, "");
        }
    }
    
    /**
     * Private helper method used to print the tree.
     * 
     * @param node    the node of reference
     * @param isRight whether that node is a right child
     * @param indent  a String indentation
     */
    private void printTree(BSTNode<E> node, boolean isRight, String indent) {
        if (node.right != null) {
            printTree(node.right, true, indent + (isRight ? "        " : " |      "));
        }

        System.out.print(indent);

        if (isRight) {
            System.out.print(" /");
        }
        else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(node);
        if (node.left != null) {
            printTree(node.left, false, indent + (isRight ? " |      " : "        "));
        }
    }
    
    /**
     * 
     * @param node 
     */
    private void printNodeValue(BSTNode<E> node) {
        if (node == null) {
            System.out.print("<null>");
        }
        else {
            System.out.print(node.getData());
        }
        System.out.println();
    }
}