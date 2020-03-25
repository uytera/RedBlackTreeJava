package com.mycompany.test;

import com.mycompany.main.Color;
import com.mycompany.main.Node;
import com.mycompany.main.Position;
import com.mycompany.main.RBTree;
import javafx.geometry.Pos;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RBTreeTest {
    RBTree tree = new RBTree();
    Node grandparent = new Node(null, null, null, 10,  Color.BLACK, Position.MIDDLE);

    Node parentLeft = new Node(grandparent, null, null, 6, Color.RED, Position.LEFT);
    Node sampleLeft = new Node(parentLeft, null, null, 3, Color.BLACK, Position.LEFT);

    Node parentRight = new Node(grandparent, null, null, 12, Color.RED, Position.RIGHT);
    Node sampleRight= new Node(parentRight, null, null, 16, Color.BLACK, Position.RIGHT);

    @Test
    public void grandparent() {
        grandparent.leftChild = parentLeft;
        grandparent.rightChild = parentRight;

        parentLeft.leftChild = sampleLeft;
        parentRight.rightChild = sampleRight;

        Assert.assertEquals(grandparent, tree.Grandparent(sampleLeft));
        Assert.assertEquals(grandparent, tree.Grandparent(sampleRight));
    }

    @Test
    public void uncle() {
        grandparent.leftChild = parentLeft;
        grandparent.rightChild = parentRight;

        parentLeft.leftChild = sampleLeft;
        parentRight.rightChild = sampleRight;

        Assert.assertEquals(parentRight, tree.Uncle(sampleLeft));
        Assert.assertEquals(parentLeft,  tree.Uncle(sampleRight));
    }
}