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

    public Node Sibling(Node node){
        if(node == node.parent.leftChild){
            return node.parent.rightChild;
        }
        else return node.parent.leftChild;
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

        Node uncle = Uncle(node);

        if(uncle != null && uncle.color == Color.RED){
            node.parent.color = Color.BLACK;
            uncle.color = Color.BLACK;
            Node grandparent = Grandparent(node);
            grandparent.color = Color.RED;
            insertCase1(grandparent);
        } else {
            insertCase4(node);
        }
    }

    public void insertCase4(Node node){
        Node grandparent = Grandparent(node);

        if(node == node.parent.rightChild && node.parent == grandparent.leftChild) {
            this.RotateLeft(node.parent);
            node = node.leftChild;
        }

        if(node == node.parent.leftChild && node.parent == grandparent.rightChild) {
            RotateRight(node.parent);
            node = node.rightChild;
        }

        insertCase5(node);
    }

    public void insertCase5(Node node){
        Node grandparent = Grandparent(node);

        node.parent.color = Color.BLACK;
        grandparent.color = Color.RED;
        if(node == node.parent.leftChild && node.parent == grandparent.leftChild){
            RotateRight(grandparent);
        }
        else
            RotateLeft(grandparent);
    }

    public void ReplaceNode(Node node, Node child){
        child.parent = node.parent;
        if(node.parent == null)
            return;
        if(node == node.parent.leftChild){
            node.parent.leftChild = child;
        }
        else
            node.parent.rightChild = child;
    }

    public void DeleteOneChild(Node node){
        Node child = node.rightChild.value < 0 ? node.leftChild : node.rightChild;

        ReplaceNode(node, child);
        if(node.color == Color.BLACK){
            if(child.color == Color.RED) {
                child.color = Color.BLACK;
            }
            else
                DeleteCase1(child);
        }
    }

    public void DeleteCase1(Node node){
        if(node.parent != null)
            DeleteCase2(node);
    }

    public void DeleteCase2(Node node){
        Node sibling = Sibling(node);

        if(sibling.color == Color.RED){
            node.parent.color = Color.RED;
            sibling.color = Color.BLACK;
            if(node == node.parent.leftChild)
                RotateLeft(node.parent);
            else
                RotateRight(node.parent);
        }

        DeleteCase3(node);
    }

    public void DeleteCase3(Node node){
        Node sibling = Sibling(node);

        if(node.parent.color == Color.BLACK
                && sibling.color == Color.BLACK
                && sibling.leftChild.color == Color.BLACK
                && sibling.rightChild.color == Color.BLACK){
            sibling.color = Color.RED;
            DeleteCase1(node);
        }
        else
            DeleteCase4(node);
    }

    public void DeleteCase4(Node node){
        Node sibling = this.Sibling(node);

        if(node.parent.color == Color.RED
                && sibling.color == Color.BLACK
                && sibling.leftChild.color == Color.BLACK
                && sibling.rightChild.color == Color.BLACK){
            sibling.color = Color.RED;
            node.parent.color = Color.BLACK;
        }
        else
            DeleteCase5(node);
    }

    public void DeleteCase5(Node node){
        Node sibling = Sibling(node);

        if(sibling.color == Color.BLACK){
            if(node == node.parent.leftChild
                && sibling.rightChild.color == Color.BLACK
                && sibling.leftChild.color == Color.RED){
                sibling.color = Color.RED;
                sibling.color = Color.BLACK;
                this.RotateRight(sibling);
            }
            else if(node == node.parent.rightChild
                    && sibling.leftChild.color == Color.BLACK
                    && sibling.rightChild.color == Color.RED) {
                    sibling.color = Color.RED;
                    sibling.rightChild.color = Color.BLACK;
                    RotateLeft(sibling);
            }
        }
        DeleteCase6(node);
    }

    public void DeleteCase6(Node node){
        Node sibling = Sibling(node);

        sibling.color = node.parent.color;
        node.parent.color = Color.BLACK;

        if(node == node.parent.leftChild){
            sibling.rightChild.color = Color.BLACK;
            RotateLeft(node.parent);
        }
        else {
            sibling.leftChild.color = Color.BLACK;
            RotateRight(node.parent);
        }
    }
}
