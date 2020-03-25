package com.mycompany.test;

import com.mycompany.main.Enums.Color;
import com.mycompany.main.RBTree.Node;
import com.mycompany.main.RBTree.RBTree;
import org.junit.Assert;
import org.junit.Test;

public class RBTreeTest {

    @Test
    public void grandparent() {

        RBTree tree = new RBTree();
        Node grandparent = new Node(null, null, null, 10,  Color.BLACK);

        Node parentLeft = new Node(grandparent, null, null, 6, Color.RED);
        Node sampleLeft = new Node(parentLeft, null, null, 3, Color.BLACK);

        Node parentRight = new Node(grandparent, null, null, 12, Color.RED);
        Node sampleRight= new Node(parentRight, null, null, 16, Color.BLACK);

        grandparent.leftChild = parentLeft;
        grandparent.rightChild = parentRight;

        parentLeft.leftChild = sampleLeft;
        parentRight.rightChild = sampleRight;

        Assert.assertEquals(grandparent, tree.Grandparent(sampleLeft));
        Assert.assertEquals(grandparent, tree.Grandparent(sampleRight));
    }

    @Test
    public void uncle() {
        RBTree tree = new RBTree();
        Node grandparent = new Node(null, null, null, 10,  Color.BLACK);

        Node parentLeft = new Node(grandparent, null, null, 6, Color.RED);
        Node sampleLeft = new Node(parentLeft, null, null, 3, Color.BLACK);

        Node parentRight = new Node(grandparent, null, null, 12, Color.RED);
        Node sampleRight= new Node(parentRight, null, null, 16, Color.BLACK);

        grandparent.leftChild = parentLeft;
        grandparent.rightChild = parentRight;

        parentLeft.leftChild = sampleLeft;
        parentRight.rightChild = sampleRight;

        Assert.assertEquals(parentRight, tree.Uncle(sampleLeft));
        Assert.assertEquals(parentLeft,  tree.Uncle(sampleRight));
    }

    @Test
    public void LeftRotate() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 20,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 15, Color.RED);

        Node level3Left = new Node(level2Left, null, null, 10, Color.BLACK);
        Node level3Right = new Node(level2Left, null, null, 18, Color.RED);

        Node level4Left = new Node(level3Right, null, null, 17, Color.BLACK);
        Node level4Right = new Node(level3Right, null, null, 19, Color.BLACK);

        root.leftChild = level2Left;

        level2Left.leftChild = level3Left;
        level2Left.rightChild = level3Right;

        level3Right.leftChild = level4Left;
        level3Right.rightChild = level4Right;

        tree.RotateLeft(level2Left);

        //Level3
        Assert.assertEquals(root, level3Right.parent);
        Assert.assertEquals(level4Right,  level3Right.rightChild);
        Assert.assertEquals(level2Left,  level3Right.leftChild);

        //Level2
        Assert.assertEquals(level3Right,  level2Left.parent);
        Assert.assertEquals(level4Left,  level2Left.rightChild);
        Assert.assertEquals(level3Left,  level2Left.leftChild);
    }

    @Test
    public void RightRotate() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 20,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 18, Color.RED);

        Node level3Left = new Node(level2Left, null, null, 15, Color.BLACK);
        Node level3Right = new Node(level2Left, null, null, 19, Color.RED);

        Node level4Left = new Node(level3Left, null, null, 14, Color.BLACK);
        Node level4Right = new Node(level3Left, null, null, 16, Color.BLACK);

        root.leftChild = level2Left;

        level2Left.leftChild = level3Left;
        level2Left.rightChild = level3Right;

        level3Left.leftChild = level4Left;
        level3Left.rightChild = level4Right;

        tree.RotateRight(level2Left);

        //Level3
        Assert.assertEquals(root, level3Left.parent);
        Assert.assertEquals(level2Left,  level3Left.rightChild);
        Assert.assertEquals(level4Left,  level3Left.leftChild);

        //Level2
        Assert.assertEquals(level3Left,  level2Left.parent);
        Assert.assertEquals(level3Right,  level2Left.rightChild);
        Assert.assertEquals(level4Right,  level2Left.leftChild);
    }
}