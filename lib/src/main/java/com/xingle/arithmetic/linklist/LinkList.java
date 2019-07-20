package com.xingle.arithmetic.linklist;

/**
 * Created by xingle on 2018/5/8.
 */

public class LinkList {

    private Node first;

    public LinkList() {
        first = null;
    }

    public void insertFirst(long value) {
        Node node = new Node(value);
        node.next = first;
        first = node;
        System.out.println(first.data);
        System.out.println(first.next.data);
    }

    public Node delteFirst() {
        Node temp = first;
        first = temp.next;
        return temp;
    }

    public void display() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public Node find(long value) {
        Node current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
        return current;
    }

    public Node delete(long value) {
        Node current = first;
        Node previous = first;
        while (current.data != value) {
            if (current.next == null){
                return null;
            }
            previous = current;
            current = current.next;
        }

        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

}
