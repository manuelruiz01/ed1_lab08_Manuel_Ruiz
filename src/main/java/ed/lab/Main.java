package ed.lab;

public class Main {

    public static void main(String[] args) {



        E02AVLTree<Integer> avlTree = new E02AVLTree<>(Integer::compare); // Crea un árbol AVL de números enteros.
        avlTree.search(5); // retorna null porque el árbol está vacío
        avlTree.insert(5); // almacena 5 en el árbol AVL
        avlTree.insert(3); // almacena 3 en el árbol AVL
        avlTree.insert(1); // almacena 1 en el árbol AVL y lo rebalancea
        avlTree.search(5); // retorna 5
        avlTree.search(1); // retorna 1
        avlTree.size(); // retorna 3
        avlTree.height(); // retorna 2
        avlTree.delete(3); // elimina 3
        avlTree.search(3); // retorna null
        avlTree.insert(4); // almacena 4 y rebalancea el árbol AVL

    }
}