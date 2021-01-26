package stepik;

import java.io.*;
import java.util.LinkedList;
import java.util.stream.IntStream;


/*Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
обход дерева  */

class BinarySearchTreeNode {
    public int data;
    public BinarySearchTreeNode left;
    public BinarySearchTreeNode right;

    BinarySearchTreeNode(int nodeData) {
        this.data = nodeData;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree {
    public BinarySearchTreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insertNode(int nodeData) {
        this.root = this.insertNode(this.root, nodeData);
    }

    private BinarySearchTreeNode insertNode(BinarySearchTreeNode root, int nodeData) {
        if (root == null) {
            root = new BinarySearchTreeNode(nodeData);
        } else {
            if (nodeData <= root.data) {
                root.left = this.insertNode(root.left, nodeData);
            } else {
                root.right = this.insertNode(root.right, nodeData);
            }
        }

        return root;
    }
}

class BinarySearchTreePrintHelper {
    public static void printInorder(BinarySearchTreeNode root, String sep, BufferedWriter bufferedWriter) throws IOException {
        if (root == null) {
            return;
        }

        BinarySearchTreePrintHelper.printInorder(root.left, sep, bufferedWriter);

        if (root.left != null) {
            bufferedWriter.write(sep);
        }

        bufferedWriter.write(String.valueOf(root.data));

        if (root.right != null) {
            bufferedWriter.write(sep);
        }

        BinarySearchTreePrintHelper.printInorder(root.right, sep, bufferedWriter);
    }
}

class Result {

    /*
     * Complete the 'maxDepth' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_BINARY_SEARCH_TREE root as parameter.
     */

    /*
     * For your reference:
     *
     * BinarySearchTreeNode {
     *     int data;
     *     BinarySearchTreeNode left;
     *     BinarySearchTreeNode right;
     * }
     *
     */

    public static int maxDepth(BinarySearchTreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 1;
        int currentDepth = 1;
        LinkedList<BinarySearchTreeNode> stack = new LinkedList<>();
        stack.push(root);
        boolean goLeft = true;
        BinarySearchTreeNode current = root;
        while (stack.size() > 0) {

            if (goLeft) {
                while(current.left != null) {
                    stack.push(current);
                    current = current.left;
                    currentDepth++;
                }
            }

            if (current.right != null) {
                current = current.right;
                currentDepth++;
                goLeft = true;
            } else {
                if (currentDepth > depth) {
                    depth = currentDepth;
                }
                current = stack.pop();
                currentDepth--;
                goLeft = false;

            }
        }

        return depth;

    }

}

class MaxTreeDepth {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("OUTPUT_PATH")));

        BinarySearchTree binary_search_tree = new BinarySearchTree();

        int binary_search_treeCount = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, binary_search_treeCount).forEach(i -> {
            try {
                int binary_search_treeItem = Integer.parseInt(bufferedReader.readLine().trim());

                binary_search_tree.insertNode(binary_search_treeItem);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.maxDepth(binary_search_tree.root);


        BinarySearchTreePrintHelper.printInorder(binary_search_tree.root, "==", bufferedWriter);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
        System.out.println(result);
    }
}
/*
*
3
* 9
* 20
* 15
* 27
*
* */
