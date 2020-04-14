package com.mycompany.main;

import com.mycompany.main.RBTree.RBTree;

public class Main {

    public static void main(String[] args) {
	    RBTree tree = new RBTree(1);
	    tree.insertNode(2);
	    tree.insertNode(3);
	    tree.insertNode(4);
        tree.insertNode(6);
        tree.insertNode(7);
        tree.deleteNode(1);
    }
}
