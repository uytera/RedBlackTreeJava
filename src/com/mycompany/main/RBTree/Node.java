package com.mycompany.main.RBTree;

import com.mycompany.main.Enums.Color;

public class Node {
    public Node parent;
    public Node leftChild;
    public Node rightChild;
    public int value;
    public Color color;

    public Node(Node parent, int value, Color color) {
        this.parent = parent;
        AddNullChilds();
        this.value = value;
        this.color = color;
    }
    public Node(Node parent, Node leftChild, Node rightChild, int value, Color color){
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
        this.color = color;
    }

    public void AddNullChilds(){
        this.leftChild = new Node(this, null, null, -10, Color.BLACK);
        this.rightChild = new Node(this, null, null, -10, Color.BLACK);
    }
}
