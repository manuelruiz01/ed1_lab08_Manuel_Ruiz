package ed.lab;

import java.util.ArrayList;
import java.util.List;

public class E01KthSmallest {

    public int kthSmallest(TreeNode<Integer> root, int k) {
        List<Integer> arr = new ArrayList<>();
        inorder(root, arr);

        if (k <= 0 || arr.size() < k) {
            return -1;
        }
        return arr.get(k - 1);
    }

    private  void inorder(TreeNode<Integer> root, List<Integer> arr) {
        if (root == null) { return;}
        inorder(root.left, arr);
        arr.add(root.value);
        inorder(root.right, arr);
    }

}