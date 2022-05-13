/*
 * Project: # 3
 * Filename: BinaryTree.java
 * Author:  Herman Mann
 * Date: 02/21/2022
 * Description: This is a Binary tree java class should contain a static nested
 * class to define the nodes of the binary tree, together with a constructor that 
 * is called when the Make Tree button is clicked and is supplied the string 
 * representation of the tree and constructs the actual tree from that string. 
 * In addition, it should have public methods that are called when each of the 
 * remaining six buttons are clicked. All of those public methods should have 
 * corresponding private methods that accomplish their tasks using recursion
 */

//Package name
package cmsc350_project3_hermanmann;

//Import files
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 *To binary tree related operations with recursive approach
 */
public class BinaryTree {

    //Parent node
    private Node parent;
    //Child node
    private Node child;

    /**
     * Constructor to create the binary tree from the string based on the
     * parenthesis.
     *
     * @param inputTreeExpression - string with parenthesis
     * @throws InvalidTreeSyntax - throw invalid tree syntax exception
     */
    public BinaryTree(String inputTreeExpression) throws InvalidTreeSyntax {
        //Node stack
        Stack<Node> nodeStack = new Stack<>();
        //Remove spaces from input if any
        inputTreeExpression = inputTreeExpression.replace(Utility.STR_SPACE, Utility.STR_EMPTY);
        int indexOfLastClosedParenthesis = inputTreeExpression.lastIndexOf(Utility.STR_CLOSED_PARENTHESIS);
        int indexOfCurrentToken = 0;
        //tokenize the string
        StringTokenizer stringTokenizer = Utility.CreateStringTokenizer(inputTreeExpression);
        //Loop until it has more tokens
        while (stringTokenizer.hasMoreTokens()) {
            //get the next valid token
            String token = Utility.GetNextValidToken(stringTokenizer);
            //check for token
            switch (token) {
                case Utility.STR_OPEN_PARENTHESIS:
                    if (parent != null) {
                        //push parent to stack
                        nodeStack.push(parent);
                        if (child != null) {
                            //make child as parent
                            parent = child;
                        }
                    }
                    break;
                case Utility.STR_CLOSED_PARENTHESIS:
                    try {
                        //Set parent as child
                        child = parent;
                        //If it is not last closed parenthesis then 
                        //set parent as popped item from the stack
                        if (indexOfLastClosedParenthesis != indexOfCurrentToken) {
                            parent = nodeStack.pop();
                        }
                    } catch (EmptyStackException emptyStack) {
                        throw new InvalidTreeSyntax(Utility.MSG_INVALID_PARENTHESIS);
                    }
                    break;
                case " ":
                    break;
                default:
                    if (parent == null) {
                        //set token as parent node
                        parent = new Node(token);
                    } else {
                        //create child node
                        child = new Node(token);
                        if (parent != null) {
                            //Add child node to parent
                            parent.addChild(child);
                        }
                    }
                    break;
            }
            indexOfCurrentToken++;
        }
        //Throw exception if tree syntax is invalid
        if (isValidTreeSyntax(inputTreeExpression)) {
            throw new InvalidTreeSyntax(Utility.MSG_INCORRECT_SYNTAX);
        }
    }

    /**
     * To validate whether tree expression has a valid syntax or not
     *
     * @param treeExpression - input tree expression
     * @return - true if tree expression is valid else false
     */
    private boolean isValidTreeSyntax(String treeExpression) {
        return treeExpression.length() != this.nodes(parent) * 3;
    }

    /**
     * To check whether the binary tree is balanced, which means for each node
     * in the tree, the absolute difference between the height of its left and
     * right subtrees is at most 1.
     *
     * @return - true if balanced tree else false
     */
    public boolean isBalanced() {
        return isBalanced(this.parent);
    }

    /**
     * To check whether the binary tree is balanced or not by calling
     * recursively
     *
     * @param node - current node
     * @return - true if balanced else false
     */
    private boolean isBalanced(Node node) {
        //primary base case
        if (node == null) {
            return true;
        }
        //return true if the absolute difference is at most 1 by calling recursively
        return (Math.abs(height(node.left) - height(node.right)) <= 1)
                && (isBalanced(node.left) && isBalanced(node.right));
    }

    /**
     * To check whether a tree is full or not
     *
     * @return - true if tree is full else false
     */
    public boolean isFull() {
        return isFull(this.parent, height(this.parent), 0);
    }

    /**
     * To check whether the binary tree is full or not by calling recursively. A
     * full binary tree has the maximum number of nodes for a tree of its
     * height.
     *
     * @param node - current node
     * @param height - tree height
     * @param index - current node index
     * @return - true if tree is full else false
     */
    private boolean isFull(Node node, int height, int index) {
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return (height == index + 1);
        }
        if (node.left == null || node.right == null) {
            return false;
        }
        //check other nodes recursively
        return isFull(node.left, height, index + 1) && isFull(node.right, height, index + 1);
    }

    /**
     * To check whether a tree is proper or not
     *
     * @return - true if proper tree else false
     */
    public boolean isProper() {
        return isProper(this.parent);
    }

    /**
     * To check whether the binary tree is proper or not by calling recursively.
     * In a proper binary tree, every node has either 0 or 2 children
     *
     * @param node - current node
     * @return - true if proper tree else false
     */
    private boolean isProper(Node node) {
        //primary base case
        if (node == null) {
            return true;
        }
        //check for child nodes by calling recursively
        return ((node.left != null || node.right == null) && (node.left == null || node.right != null))
                && (isProper(node.left) && isProper(node.right));
    }

    /**
     * To get height of the tree
     *
     * @return - height of the tree
     */
    public int height() {
        return height(this.parent) - 1;
    }

    /**
     * To get height of the tree by calling recursively. The height of a tree is
     * the maximum level of all of its nodes. The root node containing A is at
     * the level 0
     *
     * @param node - current node
     * @return - height of the tree
     */
    private int height(Node node) {
        //call nodes recursively to get height of tree
        return (node == null) ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * To get number of node for the tree.
     *
     * @return - number of nodes
     */
    public int nodes() {
        return nodes(this.parent);
    }

    /**
     * To get number of node for the tree by calling recursively.
     *
     * @param node - current node
     * @return - number of node for the tree
     */
    private int nodes(Node node) {
        //call child nodes recursively
        return (node == null) ? 0 : 1 + nodes(node.left) + nodes(node.right);
    }

    /**
     * To print the tree nodes in the inorder format.
     *
     * @return - nodes in inorder format
     */
    public String inOrder() {
        return inOrder(this.parent);
    }

    /**
     * To get the tree nodes in the inorder format by calling recursively.
     *
     * @param node - current node
     * @return - nodes in inorder format
     */
    private String inOrder(Node node) {
        //call nodes recursively
        return (node == null) ? Utility.STR_EMPTY : Utility.STR_OPEN_PARENTHESIS
                + inOrder(node.left) + node.info + inOrder(node.right) + Utility.STR_CLOSED_PARENTHESIS;
    }

    /**
     * To get parent as formatted string .
     *
     * @return - parent as formatted string
     */
    @Override
    public String toString() {
        return parent.toString();
    }

    /**
     * To create tree nodes and method to perform tree operations
     */
    public static class Node {

        //Node information
        private String info;
        //left node
        private Node left;
        //right node
        private Node right;

        /**
         * constructor to create node
         *
         * @param info - node information
         */
        public Node(String info) {
            this.info = info;
        }

        /**
         * To add child to a given node
         *
         * @param node - given node to add
         * @throws InvalidTreeSyntax - throw invalid tree syntax exception
         */
        private void addChild(Node node) throws InvalidTreeSyntax {
            //check for left node and set if null
            //else check for right node and set if null
            if (this.left == null) {
                this.setLeft(node);
            } else if (this.right == null) {
                this.setRight(node);
            } else {
                throw new InvalidTreeSyntax(Utility.MSG_INVALID_NODES_SYNTAX);
            }
        }

        /**
         * to set left node
         *
         * @param node - node to set
         */
        public void setLeft(Node node) {
            left = node;
        }

        /**
         * to set right node
         *
         * @param node - node to set
         */
        public void setRight(Node node) {
            right = node;
        }

        /**
         * To get node as formatted string .
         *
         * @return - node as formatted string
         */
        @Override
        public String toString() {
            return toString(this);
        }

        /**
         * to get nodes as a formatted string by calling recursively
         *
         * @param node - current node
         * @return - nodes as a formatted string
         */
        private static String toString(Node node) {
            return (node == null) ? Utility.STR_EMPTY : Utility.STR_OPEN_PARENTHESIS
                    + node.info + toString(node.left) + toString(node.right) + Utility.STR_CLOSED_PARENTHESIS;
        }
    }
}
