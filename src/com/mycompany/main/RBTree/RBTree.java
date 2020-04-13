package com.mycompany.main.RBTree;

import com.mycompany.main.Enums.Color;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class RBTree {

    public Node root;

    public RBTree(int RootValue){
        root = new Node(null, RootValue, Color.BLACK);
    }

    public Node grandparent(Node node){
        if(node != null && node.parent != null)
            return node.parent.parent;
        return null;
    }

    public void rootFind() {
        while(root.parent != null)
            root = root.parent;
    }

    public Node uncle(Node node){
        Node grandparent = grandparent(node);
        if(grandparent == null)
            return  null;
        if(node.parent == grandparent.rightChild)
            return grandparent.leftChild;
        else
            return grandparent.rightChild;
    }

    public Node sibling(Node node){
        if(node == node.parent.leftChild){
            return node.parent.rightChild;
        }
        else return node.parent.leftChild;
    }

    public void rotateLeft(Node node){
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

    public void rotateRight(Node node){
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

    public Node findNode(int value){
        Node currentNode = root;
        while(currentNode.value > 0){
            if(currentNode.value == value)
                return currentNode;
            if(currentNode.value > value){
                currentNode = currentNode.leftChild;
            }
            else
                currentNode = currentNode.rightChild;
        }
        return currentNode;
    }

    public Node maxInSubTree(Node subRoot){
        Node maxNode = subRoot;
        while(maxNode.value > 0){
            maxNode = maxNode.rightChild;
        }

        return maxNode.parent;
    }

    public Node minInSubTree(Node subRoot){
        Node minNode = subRoot;
        while(minNode.value > 0){
            minNode = minNode.leftChild;
        }
        return minNode.parent;
    }

    public void insertNode(int value){
        Node nodeForInsert = findNode(value);

        if(nodeForInsert.value == value){
            return;
        }

        nodeForInsert.AddNullChilds();
        nodeForInsert.value = value;
        nodeForInsert.color = Color.RED;

        insertCase1(nodeForInsert);
        rootFind();
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

        Node uncle = uncle(node);

        if(uncle != null && uncle.color == Color.RED){
            node.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            Node grandparent = grandparent(node);
            grandparent.color = Color.RED;
            insertCase1(grandparent);
        } else {
            insertCase4(node);
        }
    }

    public void insertCase4(Node node){
        Node grandparent = grandparent(node);

        if(node == node.parent.rightChild && node.parent == grandparent.leftChild) {
            this.rotateLeft(node.parent);
            node = node.leftChild;
        }

        if(node == node.parent.leftChild && node.parent == grandparent.rightChild) {
            rotateRight(node.parent);
            node = node.rightChild;
        }

        insertCase5(node);
    }

    public void insertCase5(Node node){
        Node grandparent = grandparent(node);

        node.parent.color = Color.BLACK;
        grandparent.color = Color.RED;
        if(node == node.parent.leftChild && node.parent == grandparent.leftChild){
            rotateRight(grandparent);
        }
        else
            rotateLeft(grandparent);
    }

    public void deleteNode(int value){
        Node nodeForReplace = findNode(value);
        Node nodeFoeDelete = minInSubTree(nodeForReplace.rightChild);

        nodeForReplace.value = nodeFoeDelete.value;

        deleteOneChild(nodeFoeDelete);
    }

    public void replaceNode(Node node, Node child){
        child.parent = node.parent;
        if(node.parent == null)
            return;
        if(node == node.parent.leftChild){
            node.parent.leftChild = child;
        }
        else
            node.parent.rightChild = child;
    }

    public void deleteOneChild(Node node){
        Node child = node.rightChild.value < 0 ? node.leftChild : node.rightChild;

        replaceNode(node, child);
        if(node.color == Color.BLACK){
            if(child.color == Color.RED) {
                child.color = Color.BLACK;
            }
            else
                deleteCase1(child);
        }
    }

    public void deleteCase1(Node node){
        if(node.parent != null)
            deleteCase2(node);
    }

    public void deleteCase2(Node node){
        Node sibling = sibling(node);

        if(sibling.color == Color.RED){
            node.parent.color = Color.RED;
            sibling.color = Color.BLACK;
            if(node == node.parent.leftChild)
                rotateLeft(node.parent);
            else
                rotateRight(node.parent);
        }

        deleteCase3(node);
    }

    public void deleteCase3(Node node){
        Node sibling = sibling(node);

        if(node.parent.color == Color.BLACK
                && sibling.color == Color.BLACK
                && sibling.leftChild.color == Color.BLACK
                && sibling.rightChild.color == Color.BLACK){
            sibling.color = Color.RED;
            deleteCase1(node);
        }
        else
            deleteCase4(node);
    }

    public void deleteCase4(Node node){
        Node sibling = this.sibling(node);

        if(node.parent.color == Color.RED
                && sibling.color == Color.BLACK
                && sibling.leftChild.color == Color.BLACK
                && sibling.rightChild.color == Color.BLACK){
            sibling.color = Color.RED;
            node.parent.color = Color.BLACK;
        }
        else
            deleteCase5(node);
    }

    public void deleteCase5(Node node){
        Node sibling = sibling(node);

        if(sibling.color == Color.BLACK){
            if(node == node.parent.leftChild
                && sibling.rightChild.color == Color.BLACK
                && sibling.leftChild.color == Color.RED){
                sibling.color = Color.RED;
                sibling.color = Color.BLACK;
                this.rotateRight(sibling);
            }
            else if(node == node.parent.rightChild
                    && sibling.leftChild.color == Color.BLACK
                    && sibling.rightChild.color == Color.RED) {
                    sibling.color = Color.RED;
                    sibling.rightChild.color = Color.BLACK;
                    rotateLeft(sibling);
            }
        }
        deleteCase6(node);
    }

    public void deleteCase6(Node node){
        Node sibling = sibling(node);

        sibling.color = node.parent.color;
        node.parent.color = Color.BLACK;

        if(node == node.parent.leftChild){
            sibling.rightChild.color = Color.BLACK;
            rotateLeft(node.parent);
        }
        else {
            sibling.leftChild.color = Color.BLACK;
            rotateRight(node.parent);
        }
    }
}
