package com.xingle.arithmetic.linklist;

/**
 * Created by xingle on 2018/5/8.
 */

public class Test {

    public static void main(String[] args){
        LinkList linkList = new LinkList();
        linkList.insertFirst(10);
        linkList.insertFirst(0);
        linkList.insertFirst(23);
        linkList.insertFirst(1);

        linkList.display();

        Node node = linkList.delteFirst();
        System.out.println("\ndeleteFirst node:");
        node.display();
        System.out.println();
        linkList.display();

        System.out.println("\n find node:");
        Node findNode = linkList.find(10);
        findNode.display();

        System.out.println("\n delete node:");
        Node delNode = linkList.delete(0);
        linkList.display();

    }

}
