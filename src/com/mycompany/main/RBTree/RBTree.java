package com.mycompany.main.RBTree;

public class RBTree {

    public Node Grandparent(Node node){
        if(node != null && node.parent != null)
            return node.parent.parent;
        return null;
    }

    public Node Uncle(Node node){
        Node grandparent = Grandparent(node);
        if(grandparent == null)
            return  null;
        if(node.parent == grandparent.rightChild)
            return grandparent.leftChild;
        else
            return grandparent.rightChild;
    }

    public void RotateLeft(Node node){
        Node pivot = node.rightChild;

        pivot.parent = node.parent;
        if(node.parent != null){
            if(node.parent.leftChild == node)
                node.parent.leftChild = pivot;
            else
                node.parent.rightChild = pivot;
        }

        node.rightChild = pivot.leftChild;
        if(pivot.leftChild != null)
            pivot.leftChild.parent = node;

        node.parent = pivot;
        pivot.leftChild = node;
    }

    public void RotateRight(Node node){
        Node pivot = node.leftChild;

        pivot.parent = node.parent;
        if(node.parent != null){
            if(node.parent.leftChild == node)
                node.parent.leftChild = pivot;
            else
                node.parent.rightChild = pivot;
        }

        node.leftChild = pivot.rightChild;
        if(pivot.rightChild != null)
            pivot.rightChild.parent = node;

        node.parent = pivot;
        pivot.rightChild = node;
    }
}
