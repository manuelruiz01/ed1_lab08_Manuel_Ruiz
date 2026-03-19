package ed.lab;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class E02AVLTree<T> {

    private final Comparator<T> comparator;

    private Node<T> root;
    private int size;

    public E02AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
        this.size = 0;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            size++;
            return new Node<>(value);
        }

        int compare = comparator.compare(value, root.value);

        if (compare < 0) {
            root.left = insert(root.left, value);
        }
        else if (compare > 0) {
            root.right = insert(root.right, value);
        } else {
            return root;
        }

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        //Balanceo

        int balance = getBalance(root);

        //izquierda-izquierda
        if (balance > 1 && comparator.compare(value, root.left.value) < 0) {
            return rotateRight(root);
        }

        //derecha - derecha
        if (balance < -1 && comparator.compare(value, root.right.value) > 0) {
            return rotateLeft(root);
        }

        //derecha - izquierda
        if (balance < -1 && comparator.compare(value, root.right.value) < 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        //izquierda - derecha
        if (balance > 1 && comparator.compare(value, root.left.value) > 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }

        return root;
    }

    public T search(T value) {
        Node<T> root = search(this.root, value);

        if (root == null) {
            return null;
        }

        return root.value;
    }

    private Node<T> search(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        int compare = comparator.compare(value, root.value);

        if (compare == 0) {
            return root;
        }

        if (compare < 0) {
            return search(root.left, value);
        }

        return search(root.right, value);
    }

    private Node<T> min(Node<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public void delete(T value) {
        if(search(value) != null) {
            root = delete(this.root, value);
            size--;
        }
    }

    private Node<T> delete(Node<T> root, T value) {
        if (root == null) { return null; }
        int comparition  = comparator.compare(value, root.value);
        if(comparition < 0){
            root.left = delete(root.left, value);
        } else if (comparition > 0){
            root.right = delete(root.right, value);
        } else{
            if(root.left == null){ return root.right;}
            if(root.right == null){ return root.left;}

            Node<T> sucesor = min(root.right);
            root.value = sucesor.value;
            root.right = delete(root.right, sucesor.value);
        }
        updateHeight(root);

        //Balanceo
        int balance = getBalance(root);


        if (balance > 1 && getBalance(root.left)>= 0) {
            return rotateRight(root);
        }


        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }


        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }


        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }

        return root;

    }

    public int height() {
        if (this.root == null)
            return 0;
        return this.root.height;
    }

    private void updateHeight(Node<T> root) {
        if (root == null) return;

        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    private int getHeight(Node<T> root) {
        if (root == null)
            return 0;

        return root.height;
    }

    public int size() {
        return this.size;
    }

    private int getBalance(Node<T> root) {
        if (root == null)
            return 0;

        return getHeight(root.left) - getHeight(root.right);
    }

    private Node<T> rotateLeft(Node<T> root) {
        if (root == null || root.right == null) return root;

        Node<T> newRoot = root.right;

        root.right = newRoot.left; ;
        newRoot.left = root;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }

    private Node<T> rotateRight(Node<T> root) {
        if (root == null || root.left == null) return root;

        Node<T> newRoot = root.left;

        root.left = newRoot.right; ;
        newRoot.right = root;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }


    private static class Node<T> {
        protected T value;
        protected Node<T> left;
        protected Node<T> right;
        protected int height;

        public Node(T value) {
            this.value = value;
            this.height = 1;
        }
    }
}

