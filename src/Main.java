interface IQueue {
}

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

class Queue implements IQueue {
    protected Node head;
    protected Node tail;

    public Queue() {
        this.head = null;
        this.tail = null;
    }

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
}

class PriorityQueue extends Queue {
    public void insert(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }

        if (value % 2 != 0) {
            Node current = head;
            Node lastOdd = null;
            while (current != null && current.data % 2 != 0) {
                lastOdd = current;
                current = current.next;
            }
            if (lastOdd == null) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else {
                newNode.next = lastOdd.next;
                newNode.prev = lastOdd;
                lastOdd.next = newNode;
                if (newNode.next != null) {
                    newNode.next.prev = newNode;
                } else {
                    tail = newNode;
                }
            }
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
        queue.insert(5);
        queue.insert(6);
        queue.remove();
        queue.insert(7);
        queue.insert(3);
        queue.insert(10);
        queue.remove();
        queue.remove();

        while (true) {
            try {
                System.out.print(queue.remove() + " ");
            } catch (IllegalStateException e) {
                break;
            }
        }
    }
}
