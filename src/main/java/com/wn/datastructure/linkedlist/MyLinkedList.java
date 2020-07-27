package com.wn.datastructure.linkedlist;

//1、单链表
//如果不不使用size，链表处理起来会很麻烦
public class MyLinkedList {

    private Node head;

    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        if (head == null) {
            head = new Node(val, null);
        } else {
            Node newNode = new Node(val, head);
            Node f = head;
            head = newNode;
            newNode.next = f;
        }
        size++;

    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        if (head == null) {
            head = new Node(val, null);
        } else {
            Node newNode = new Node(val, null);
            Node tmpNode = head;
            for (int i = 1; i < size; i++) {
                tmpNode = tmpNode.next;
            }
            tmpNode.next = newNode;
        }
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
        } else {
            Node tmpNode = head;
            for (int i = 0; i < index - 1; i++) {
                tmpNode = tmpNode.next;
            }
            Node nextNode = tmpNode.next;
            Node newNode = new Node(val, nextNode);
            tmpNode.next = newNode;
            size++;
        }
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        if (index == 0) {
            Node nextNode = head.next;
            head = nextNode;
        } else {
            Node tmpNode = head;
            for (int i = 0; i < index - 1; i++) {
                tmpNode = tmpNode.next;
            }
            Node next2Node = tmpNode.next.next;
            tmpNode.next = next2Node;

        }

        size--;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtIndex(0,1);
        list.addAtIndex(1,2);
//        System.out.println(list.get(0));
//        list.addAtTail(1);
//        list.addAtTail(2);
//        list.deleteAtIndex(2);

        System.out.println(list);
    }

}
