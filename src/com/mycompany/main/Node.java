package com.mycompany.main;

public class Node {
    public Node parent;
    public Node leftChild;
    public Node rightChild;
    public int value;
    public Color color;
    public Position position;


    public Node(Node parent, Node leftChild, Node rightChild, int value, Color color, Position position){
        this.parent = parent;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.value = value;
        this.color = color;
        this.position = position;
    }
}
