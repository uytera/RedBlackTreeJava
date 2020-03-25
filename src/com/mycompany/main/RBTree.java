package com.mycompany.main;

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
        if(node.parent.position == Position.RIGHT)
            return grandparent.leftChild;
        else
            return grandparent.rightChild;
    }
}
