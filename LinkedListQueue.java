/**
 * Created by dineshparimi on 1/28/17.
 */
public class LinkedListQueue<Item> {
    private class Node<Item> {
        private Node previous;
        private Item item;
        private Node next;
        public Node(Node p, Item i, Node n) {
            previous = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListQueue() {
        sentinel = new Node<Item>(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(Item newItem) {
        Node<Item> newNode = new Node<Item>(sentinel, newItem, sentinel.next);
        sentinel.next.previous = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(Item newItem) {
        Node<Item> newNode = new Node<Item>(sentinel.previous, newItem, sentinel);
        sentinel.previous.next = newNode;
        sentinel.previous = newNode;
        size++;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printQueue() {
        for (int n = 0; n < size; n++) {
            System.out.print(this.get(n) + " ");
        }
        System.out.println();
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Node<Item> removedNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.previous = sentinel;
        size--;
        return removedNode.item;
    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Node<Item> removedNode = sentinel.previous;
        sentinel.previous = sentinel.previous.previous;
        sentinel.previous.next = sentinel;
        size--;
        return removedNode.item;
    }

    public Item get(int index) {
        if (index >= size) {
            return null;
        }
        Node<Item> currentNode = sentinel.next;
        for (int n = 0; n < index; n++) {
            currentNode = currentNode.next;
        }
        return currentNode.item;
    }

    public Item getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getNodeRecursive(sentinel.next, index);
    }

    private Item getNodeRecursive(Node currentNode, int index) {
        if (index == 0) {
            return (Item) currentNode.item;
        }
        return getNodeRecursive(currentNode.next, index - 1);
    }
}
