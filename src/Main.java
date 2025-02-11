class Node {
    int data;
    Node next;
    Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

interface IQueue {
    void insert(int value);
    int remove();
}

class Queue implements IQueue {
    protected Node head;
    protected Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void insert(int value) {
        Node newNode = new Node(value);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    @Override
    public int remove() {
        if (head == null) {
            throw new IllegalStateException("Queue is empty");
        }
        int value = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class PriorityQueue extends Queue {
    @Override
    public void insert(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        if (value % 2 != 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.insert(117);
        queue.insert(54);
        queue.insert(4);
        queue.insert(0);
        queue.insert(111);
        queue.insert(53);
        queue.insert(12);

        while (!queue.isEmpty()) {
            System.out.print(queue.remove() + " ");
        }
    }
}