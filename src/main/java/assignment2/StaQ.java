package assignment2;

public class StaQ {

    public static void main(String[] args) {
        StaQ stackQueue = new StaQ();

        for (int i = 1; i <= 20; i++) {
            stackQueue.push(i);
        }


        System.out.println("stackPop() -> " + stackQueue.stackPop().getContent());
        System.out.println("queuePop() -> " + stackQueue.queuePop().getContent());
        System.out.println("stackPop() -> " + stackQueue.stackPop().getContent());
        System.out.println("stackPop() -> " + stackQueue.stackPop().getContent());
        System.out.println("queuePop() -> " + stackQueue.queuePop().getContent());
    }

    Node front;
    Node back;

    StaQ() {
        front = new Node();
        back = new Node();
        back.prev = front;
        front.next = back;

    }

    public void push(Object content) {
        Node newNode = new Node(back.prev, back, content);
        back.prev.next = newNode;
        back.prev = newNode;
    }

    public Node queuePop() {
        Node temp = front.next;
        front.next = front.next.next;
        front.next.prev = front;
        return temp;
    }

    public Node stackPop() {
        Node temp = back.prev;
        back.prev = back.prev.prev;
        back.prev.next = back;
        return temp;
    }



    private class Node {
        Node prev;
        Node next;
        Object content;

        Node() {
            prev = this;
            next = this;
            content = null;
        }

        Node (Node prev, Node next, Object content) {
            this.prev = prev;
            this.next = next;
            this.content = content;
        }

        Object getContent() {
            return content;
        }
    }
}
