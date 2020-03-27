package com.mycompany.main.RBTree;

import com.mycompany.main.Enums.Color;

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

    public void insertCase1(Node node){
        if(node.parent == null){
            node.color = Color.BLACK;
        }
        else insertCase2(node);
    }

    public void insertCase2(Node node){
        if(node.parent.color == Color.BLACK){
            return;
        }
        else insertCase3(node);
    }

    public void insertCase3(Node node){

        Node uncle = this.Uncle(node);

        if(uncle != null && uncle.color == Color.RED){
            node.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            Node grandparent = this.Grandparent(node);
            grandparent.color = Color.RED;
            insertCase1(grandparent);
        } else {
            insertCase4(node);
        }
    }

    public void insertCase4(Node node){
        Node grandparent = this.Grandparent(node);

        if(node == node.parent.rightChild && node.parent == grandparent.leftChild) {
            this.RotateLeft(node.parent);
            node = node.leftChild;
        }

        if(node == node.parent.leftChild && node.parent == grandparent.rightChild) {
            this.RotateRight(node.parent);
            node = node.rightChild;
        }

        insertCase5(node);
    }

    public void insertCase5(Node node){
        Node grandparent = this.Grandparent(node);

        node.parent.color = Color.BLACK;
        grandparent.color = Color.RED;
        if(node == node.parent.leftChild && node.parent == grandparent.leftChild){
            this.RotateRight(grandparent);
        } else {
            this.RotateLeft(grandparent);
        }
    }
}
