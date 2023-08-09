public class BST {
    TreeNode root;

    void insert(String word) {
        root = insertRec(root, word);
    }

    TreeNode insertRec(TreeNode root, String word) {
        if (root == null) {
            root = new TreeNode(word);
            return root;
        }

        if (word.compareTo(root.word) < 0) {
            root.left = insertRec(root.left, word);
        } else if (word.compareTo(root.word) > 0) {
            root.right = insertRec(root.right, word);
        }

        return root;
    }

    boolean search(String word) {
        return searchRec(root, word);
    }

    boolean searchRec(TreeNode root, String word) {
        if (root == null) {
            return false;
        }

        if (word.equals(root.word)) {
            return true;
        }

        if (word.compareTo(root.word) < 0) {
            return searchRec(root.left, word);
        } else {
            return searchRec(root.right, word);
        }
    }
    void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.word + " ");
            printInOrder(node.right);
        }
    }
}