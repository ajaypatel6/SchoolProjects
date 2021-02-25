// Ajay Patel, 5660055, COSC 2P03 Assignment 3, AVL trees, runs on java 1.8

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The AVL class is gunna be an adt, and well be using integers and strings for
 * the trees A variety of classes that involve the structure of a binary search
 * tree. A binary search tree is used, but this tree is going to be AVL
 * compliant. This structure is a normal binary search tree along with all of
 * the methods that are associated with it. SInce we need the tree to be AVL, we
 * need to be able to measure the height so they can be compared between both
 * sides to see if the tree is still AVL compliant. Also, due to the
 * specification in the assignment, we need to count the elements so that we can
 * check how many times it occurs and this also helps for the deletion of
 * whatever element that starts with the specific letter. For the tree to stay
 * AVL compliant, the specific rotations to allow the AVL tree to go from
 * non-avl to AVL again.
 */
public class AVL<T extends Comparable<T>> {

    /**
     * The root node that can be any type
     */
    treeNode<T> root;

    /**
     * The default constructor creates an empty tree with a null root
     */
    public AVL() {
    }

    /**
     * Inserts an Item at the given node as a normal binary insert. The position will eventually change
     * due to the rotations Nodes are added depending on its key, and are placed
     * in their correct spot.
     */
    treeNode<T> add(T item, treeNode<T> node) {
        if (node == null) {
            return new treeNode<T>(item);
        }
        int i = item.compareTo(node.item);
        if (i == 0) {
            node.count++;
            return node;
        } else if (i < 0) {
            node.left = add(item, node.left);
        } else if (i > 0) {
            node.right = add(item, node.right);
        }

        return balance(node, i);
    }

    /**
     * Balances the BST tree - makes it an AVL tree
     *
     * The height of the right and left branches are checked to see which
     * rotations are needed to make the tree AVL once again.
     * The series of if statements are used to know what rotation is going to be necessary to allow the tree to maintain
     * the AVL property. The certain rotation is returned to perform the operation.  
     */
    private treeNode<T> balance(treeNode<T> node, int i) {
   /**
    * upto this point it's BST insert. Now it updates the heights
    * and calculate the balances for the branches
    */
        updateHeights(node);
        int balance = getBalance(node);

        if (balance > 1 && i < 0) {
            return rotateRight(node);
        }
        if (balance < -1 && i > 0) {
            return rotateLeft(node);
        }
        if (balance > 1 && i > 0) {
            return rotateRight(node);
        }
        if (balance < -1 && i < 0) {
            return rotateLeft(node);
        }
        return node;
    }

    /**
     * Rotates the given node to the left
     *
     * Since it is a left rotation, we are basically putting the right node into
     * the former node to its left.
     */
    private treeNode<T> rotateLeft(treeNode<T> node) {
        treeNode<T> right = node.right;
        treeNode<T> left = right.left;

        right.left = node;
        node.right = left;

        updateHeights(node);
        updateHeights(right);

        return right;
    }

    /**
     * Rotates the given node to the right.
     *
     * Accepts a node and performs a right rotation, the given node is used to
     * move the correct node to the right from the former node to its right.
     */
    private treeNode<T> rotateRight(treeNode<T> node) {
        treeNode<T> left = node.left;
        treeNode<T> right = left.right;
        left.right = node;
        node.left = right;

        updateHeights(left);
        updateHeights(node);
        return left;
    }

    /**
     * Adds an item to the tree , if its empty create a new tree.
     */
    public void add(T item) {
        if (root == null) {
            root = new treeNode<T>(item);
        } else {
            root = add(item, root);
        }
    }

    /**
     * Removes an item from the AVL Tree. It accepts the item as a parameter. If
     * the root isnt null it proceeds. If the item occurs exactly once it's
     * removed from the tree. If there's more than one the count is reduced and
     * further removed until its elimnated
     *
     */
    public void delete(T item) {
        if (root != null) {
            root = deleteNode(root, item);
        }
    }

    /**
     * Delete an item completely from the AVL Tree.
     *
     * This is a recursive method, and looks for the item in subtrees until it
     * is found
     *
     * Uses the comparison method to see if it is eligible for deletion, and
     * goes through the tree.
     *
     * return the new root node of the subtree.
     */
    private treeNode<T> deleteNode(treeNode<T> node, T item) {
        if (node == null) {
            return node;
        }
        int cmp = item.compareTo(node.item);

        if (cmp < 0) {
            node.left = deleteNode(node.left, item);
        } else if (cmp > 0) {
            node.right = deleteNode(node.right, item);
        } else {
            if (node.count > 1) {
                node.count--;
                return node;
            }
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            treeNode<T> min = minValue(node.right);
            node.item = min.item;
            node.count = min.count;
            node.right = deleteNode(node.right, node.item);
        }

        return node;
    }

    /**
     * Finds the minimum value in a subtree. Accepts a node to see its the
     * lowest by seeing if its null.
     *
     * Returns the node with the lowest value
     */
    private treeNode<T> minValue(treeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Gets an item from the tree and begins the search at the given node. Runs a possible series of comparisons that
     * will check if and direct where the specfic node will be going 
     * Returns the node, this accepted the tree item,
     *
     */
    public treeNode<T> get(T item, treeNode<T> node) {
        if (node == null) {
            return null;
        } else if (item.compareTo(node.item) < 0) {
            return get(item, node.left);
        } else if (item.compareTo(node.item) > 0) {
            return get(item, node.right);
        } else {
            return node;
        }
    }

    /**
     * Finds the item in the tree Accepts the item were looking for , in this
     * case 'e'
     *
     * return matching tree node or null
     */
    public treeNode<T> get(T item) {
        treeNode<T> node = null;
        if (root != null) {
            return get(item, root);
        }
        return null;
    }

    /**
     * Inorder tree traversal// SOT, basic symmetric order traversal
     *
     */
    public void inOrder(treeNode<T> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.item);
        System.out.print(" ");
        inOrder(node.right);
    }

    public boolean isAVL() {
        return isAVL(root);
    }

    /**
     * Checks if this is an AVL tree
     *
     * Method that returns a boolean on whether the tree is AVL compliant or
     * not, basically checks both sides of the tree and compares them to see if
     * there are any aspects of the tree that arent AVL
     */
    boolean isAVL(treeNode<T> node) {
        if (node == null) {
            return true;
        }
        boolean left = isAVL(node.left);
        if (!left) {
            return false;
        }

        boolean right = isAVL(node.right);
        if (!right) {
            return false;
        }
        updateHeights(node);

        if (getBalance(node) > 2 || getBalance(node) < -2) {
            return false;
        }
        return true;

    }

    /**
     * GEt the balance factor for the given node. Accepts the node and checks
     * its neighbours to see what its balance is compared to its surrrounding
     */
    private int getBalance(treeNode<T> node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    /**
     * Update the height of the given subtree starting at the node. When the
     * height is needing an update after an operatin, this method is used to
     * find correct new height.
     */
    private void updateHeights(treeNode<T> node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    /**
     * Gets the height of the given subtree
     *
     * With the node, finds the height
     */
    private int height(treeNode<T> node) {
        return (node == null) ? 0 : node.height;
    }

    /**
     * Tree class representation, everything that makes up what a tree is left,
     * right, count is needed, Allows the
     *
     * An AVL tree requires the subtree height to be stored. since the balance
     * is the height of the left subtree - height of the right subtree.
     *
     * A get count method to return the count, a method that gets the first
     * character of a string
     *
     * A to string method to allow output
     */
    static class treeNode<T> {

        T item;
        treeNode<T> left;
        treeNode<T> right;

        int count;
        int height = 1;

        treeNode(T item) {
            this.item = item;
            count = 1;
        }

        public int getCount() {
            return count;
        }

        public Character getFirst() {
            if (item == null) {
                return null;
            }
            if (item instanceof String) {
                return ((String) item).charAt(0);
            }
            return null;
        }

        public String toString() {
            return String.format("%s : %d", item, count);
        }
    }

    /**
     * A method to find an element by it's first letter.
     *
     * Accepts the item and returns the matching node, or null
     */
    public treeNode<T> getByLetter(T item) {
        treeNode<T> node = null;
        if (root != null) {
            return getByLetter(item, root);
        }
        return null;
    }

    /**
     * Finds an item by it's first letter. Starts the search from the given
     * node.
     *
     * Cant be null
     *
     * Once the word is found with the first letter, the tree is then searched
     * to find other likes
     * The element is compared to the node to see the direction th traversal should go to proceed. A series of logic leads
     * to a potential return of the direction to go whether it is towards the left or right.
     */
    public treeNode<T> getByLetter(T item, treeNode<T> node) {
        if (node == null) {
            return null;
        }
        if (item instanceof String) {
            Character c = ((String) item).charAt(0);
            Character o = ((String) node.item).charAt(0);
            if (c.compareTo(o) < 0) {
                return getByLetter(item, node.left);
            } else if (c.compareTo(o) > 0) {
                return getByLetter(item, node.right);
            } else {
                return node;
            }
        } else {
            if (item.compareTo(node.item) < 0) {
                return getByLetter(item, node.left);
            } else if (item.compareTo(node.item) > 0) {
                return getByLetter(item, node.right);
            } else {
                return node;
            }
        }
    }

// Main
    public static void main(String[] args) {
        AVL<String> avl = new AVL<>();

// scanner used to scan in the input from the webapge into the avl stree of strings, if its not there an exn is thrown
        Scanner sc;
        try {
            sc = new Scanner(new File("input.txt"));
            while (sc.hasNext()) {
                String s = sc.next();
                avl.add(s);
            }
//an in order traversal of the populated avl tree is used and checked for AVLness
            System.out.println("The AVL tree with the input from the assignment 3 webpage:");
           // System.out.println();
            avl.inOrder(avl.root);
            System.out.println();
            System.out.println("\nIs this an AVL? " + avl.isAVL());
            System.out.println();

            System.out.println("Counting the elements that start with e, and decrementing them as we find them");
// 20 is used, because there cant be more than 20 of the same words that start with letter e...
//if there are no more (null) then the loop is broken
            for (int i = 1; i < 20; i++) {
                treeNode<String> node = avl.getByLetter("e");
                System.err.println(node);
                if (node == null) {
                    break;
                }
                avl.delete(node.item);
            }
// runs another SOT on the avl tree after the deletion and checks AVLness as well.
            System.out.println();
            System.out.println("The AVL tree with the every element that begins with 'e' removed:");
          //  System.out.println();
            avl.inOrder(avl.root);
            System.out.println();
            System.out.println("\nIs this an AVL? " + avl.isAVL());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }//end main
}//end class 