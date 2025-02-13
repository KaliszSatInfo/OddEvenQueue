interface IQueue {
    void add(int value);
    int remove();
    boolean isEmpty();
}

class Node {
    private int data;
    private Node next;
    private Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}

class Queue implements IQueue {
    private Node head;
    private Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

    protected Node getHead() {
        return head;
    }

    protected void setHead(Node head) {
        this.head = head;
    }

    protected Node getTail() {
        return tail;
    }

    protected void setTail(Node tail) {
        this.tail = tail;
    }

    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
    }

    @Override
    public int remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot remove from an empty queue.");
        }
        int value = head.getData();
        head = head.getNext();
        if (head != null) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}

class PriorityQueue extends Queue {
    @Override
    public void add(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            super.add(value);
            return;
        }

        if (value % 2 != 0) {
            Node current = getHead();
            Node lastOdd = null;
            while (current != null && current.getData() % 2 != 0) {
                lastOdd = current;
                current = current.getNext();
            }
            if (lastOdd == null) {
                newNode.setNext(getHead());
                getHead().setPrev(newNode);
                setHead(newNode);
            } else {
                newNode.setNext(lastOdd.getNext());
                newNode.setPrev(lastOdd);
                lastOdd.setNext(newNode);
                if (newNode.getNext() != null) {
                    newNode.getNext().setPrev(newNode);
                } else {
                    setTail(newNode);
                }
            }
        } else {
            super.add(value);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.add(5);
        queue.add(6);
        queue.remove();
        queue.add(7);
        queue.add(3);
        queue.add(10);
        queue.remove();
        queue.remove();

        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }
    }
}