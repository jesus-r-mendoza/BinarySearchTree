package tree;

public class Driver {
    public static void main(String[] args) {
        
    
        Integer[] nums = {56, 45, 65, 25, 49, 61, 73, 14, 30, 48, 55, 60, 62, 68, 75, 5, 15, 33, 50, 58, 64, 66, 70};
        //Integer[] nums = {2,4,6,3,8,10,5,7,12,14,15};
        //String[] nums = {"k","a","b","c","d","e"};
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(nums);
        bst.printTree();
        
        System.out.println();
        System.out.print("Pre order     | "); 
        for(BSTNode<Integer> e : bst.preorder()) {
            System.out.print(e.getData() + " | ");
        }
        System.out.println(); 
        System.out.print("In order      | "); 
        for(BSTNode<Integer> e : bst.inorder()) {
            System.out.print(e.getData() + " | ");
        }
        System.out.println(); 
        System.out.print("Post order    | "); 
        for(BSTNode<Integer> e : bst.postorder()) {
            System.out.print(e.getData() + " | ");
        }
        System.out.println(); 
        System.out.print("Breadth First | "); 
        for(BSTNode<Integer> e : bst.breadthfirst()) {
            System.out.print(e.getData() + " | ");
        }
        System.out.println(); 
    
    
    BSTUnitTester tests = new BSTUnitTester(false);     
    tests.runUnitTests();
    
    }
}