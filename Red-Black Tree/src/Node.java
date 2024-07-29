enum Color{
    BLACK, RED
}

public class Node<T extends Comparable<T>> {
    T data;
    Node<T> left, right, parent;
    Color color;

    Node() {
        this.data = null;
        this.color = null;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}