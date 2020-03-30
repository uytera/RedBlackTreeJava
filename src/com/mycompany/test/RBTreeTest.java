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

    @Test
    public void InsCase1() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 20,  Color.RED);

        tree.insertCase1(root);

        Assert.assertEquals(Color.BLACK,  root.color);
    }

    @Test
    public void InsCase2() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 20,  Color.BLACK);
        Node level2Left = new Node(root, null, null, 18, Color.RED);

        root.leftChild = level2Left;

        tree.insertCase1(level2Left);

        Assert.assertEquals(Color.RED,  level2Left.color);
    }

    @Test
    public void InsCase3() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, 50, Color.RED);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.RED);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_Left = new Node(level3Left_Left, null, null, 24, Color.BLACK);
        Node level4Right_Left  = new Node(level3Left_Left, null, null, 26, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Left_Left.leftChild =level4Left_Left;
        level3Left_Left.rightChild = level4Right_Left;

        tree.insertCase1(level3Left_Left);

        Assert.assertEquals(Color.BLACK,  level2Left.color);
        Assert.assertEquals(Color.BLACK,  level2Right.color);
        Assert.assertEquals(Color.BLACK,  root.color);
    }

    @Test
    public void InsCase4and5LeftPosition() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, 50, Color.BLACK);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.RED);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_Right = new Node(level3Right_Left, null, null, 33, Color.BLACK);
        Node level4Right_Right  = new Node(level3Right_Left, null, null, 36, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Right_Left.leftChild =level4Left_Right;
        level3Right_Left.rightChild = level4Right_Right;

        tree.insertCase1(level3Right_Left);

        Assert.assertEquals(level4Right_Right, root.leftChild);
        Assert.assertEquals(root, level3Right_Left.rightChild);
        Assert.assertEquals(level2Left, level3Right_Left.leftChild);
        Assert.assertEquals(Color.BLACK, level3Right_Left.color);
        Assert.assertEquals(Color.RED, root.color);
        Assert.assertEquals(Color.RED,  level2Left.color);
    }

    @Test
    public void InsCase4and5LeftPositionWithoutRotatingOn4Case() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, 50, Color.BLACK);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.RED);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_Right = new Node(level3Left_Left, null, null, 24, Color.BLACK);
        Node level4Right_Right  = new Node(level3Left_Left, null, null, 26, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Left_Left.leftChild =level4Left_Right;
        level3Left_Left.rightChild = level4Right_Right;

        tree.insertCase1(level3Left_Left);

        Assert.assertEquals(level3Right_Left, root.leftChild);
        Assert.assertEquals(root, level2Left.rightChild);
        Assert.assertEquals(level3Left_Left, level2Left.leftChild);
        Assert.assertEquals(Color.BLACK, level2Left.color);
        Assert.assertEquals(Color.RED, root.color);
        Assert.assertEquals(Color.RED,  level3Left_Left.color);
    }

    @Test
    public void InsCase4and5RightPosition() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, 50, Color.RED);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.RED);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_LeftRight = new Node(level3Left_Right, null, null, 44, Color.BLACK);
        Node level4Right_LeftRight  = new Node(level3Left_Right, null, null, 46, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Left_Right.leftChild =level4Left_LeftRight;
        level3Left_Right.rightChild = level4Right_LeftRight;

        tree.insertCase1(level3Left_Right);

        Assert.assertEquals(level4Left_LeftRight, root.rightChild);
        Assert.assertEquals(root, level3Left_Right.leftChild);
        Assert.assertEquals(level2Right, level3Left_Right.rightChild);
        Assert.assertEquals(Color.BLACK, level3Left_Right.color);
        Assert.assertEquals(Color.RED, root.color);
        Assert.assertEquals(Color.RED,  level2Right.color);
    }

    @Test
    public void InsCase4and5RightPositionWithoutRotatingOn4Case() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, 50, Color.RED);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.RED);

        Node level4Left_RigthRight = new Node(level3Right_Right, null, null, 54, Color.BLACK);
        Node level4Rigth_RigthRight  = new Node(level3Right_Right, null, null, 56, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Right_Right.leftChild =level4Left_RigthRight;
        level3Right_Right.rightChild = level4Rigth_RigthRight;

        tree.insertCase1(level3Right_Right);

        Assert.assertEquals(level3Left_Right, root.rightChild);
        Assert.assertEquals(root, level2Right.leftChild);
        Assert.assertEquals(level3Right_Right, level2Right.rightChild);
        Assert.assertEquals(Color.BLACK, level2Right.color);
        Assert.assertEquals(Color.RED, root.color);
        Assert.assertEquals(Color.RED,  level3Right_Right.color);
    }

    @Test
    public void DelCaseOnlyReplaceRedBlack() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.RED);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, -10, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        tree.DeleteOneChild(root);
        Assert.assertEquals(null, level2Left.parent);
        Assert.assertEquals(Color.BLACK, level2Left.color);
    }

    @Test
    public void DelCaseOnlyReplaceBlackRed() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, -10, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        tree.DeleteOneChild(root);
        Assert.assertEquals(null, level2Left.parent);
        Assert.assertEquals(Color.BLACK, level2Left.color);
    }

    @Test
    public void DelCase1() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, -10, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        tree.DeleteOneChild(root);
        Assert.assertEquals(null, level2Left.parent);
        Assert.assertEquals(Color.BLACK, level2Left.color);
    }

    @Test
    public void DelCase2and4RightSubtree() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, 50, Color.RED);

        Node level3Left_Left = new Node(level2Left, null, null, -10, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_RightLeft = new Node(level3Left_Right, null, null, 44, Color.BLACK);
        Node level4Right_RightLeft = new Node(level3Left_Right, null, null, 46, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Left_Right.leftChild = level4Left_RightLeft;
        level3Left_Right.rightChild = level4Right_RightLeft;

        tree.DeleteOneChild(level2Left);

        Assert.assertEquals(null, level2Right.parent);
        Assert.assertEquals(Color.BLACK, level2Right.color);
        Assert.assertEquals(root, level2Right.leftChild);
        Assert.assertEquals(Color.BLACK, root.color);
        Assert.assertEquals(level3Left_Right, root.rightChild);
        Assert.assertEquals(level3Right_Left, root.leftChild);
        Assert.assertEquals(Color.RED, level3Left_Right.color);
    }

    @Test
    public void DelCase2and4LeftSubtree() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, 50, Color.BLACK);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, -10, Color.BLACK);

        Node level4Left_LeftRight = new Node(level3Right_Left, null, null, 34, Color.BLACK);
        Node level4Right_LeftRight = new Node(level3Right_Left, null, null, 36, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Right_Left.leftChild = level4Left_LeftRight;
        level3Right_Left.rightChild = level4Right_LeftRight;

        tree.DeleteOneChild(level2Right);

        Assert.assertEquals(null, level2Left.parent);
        Assert.assertEquals(Color.BLACK, level2Left.color);
        Assert.assertEquals(root, level2Left.rightChild);
        Assert.assertEquals(Color.BLACK, root.color);
        Assert.assertEquals(level3Left_Right, root.rightChild);
        Assert.assertEquals(level3Right_Left, root.leftChild);
        Assert.assertEquals(Color.RED, level3Right_Left.color);
    }

    @Test
    public void DelCase2and5and6RightSubtree() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.BLACK);
        Node level2Right = new Node(root, null, null, 50, Color.RED);

        Node level3Left_Left = new Node(level2Left, null, null, -10, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, 55, Color.BLACK);

        Node level4Left_RightLeft = new Node(level3Left_Right, null, null, 43, Color.RED);
        Node level4Right_RightLeft = new Node(level3Left_Right, null, null, 46, Color.BLACK);

        Node ADDDITIONALLLLLfor43Left = new Node(level4Left_RightLeft, null, null, 42, Color.BLACK);
        Node ADDDITIONALLLLLfor43Right = new Node(level4Left_RightLeft, null, null, 44, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Left_Right.leftChild = level4Left_RightLeft;
        level3Left_Right.rightChild = level4Right_RightLeft;

        level4Left_RightLeft.leftChild = ADDDITIONALLLLLfor43Left;
        level4Left_RightLeft.rightChild = ADDDITIONALLLLLfor43Right;

        tree.DeleteOneChild(level2Left);

        Assert.assertEquals(null, level2Right.parent);
        Assert.assertEquals(Color.BLACK, level2Right.color);
        Assert.assertEquals(level4Left_RightLeft, level2Right.leftChild);

        Assert.assertEquals(level3Left_Right, level4Left_RightLeft.rightChild);
        Assert.assertEquals(Color.BLACK, level3Left_Right.color);
        Assert.assertEquals(ADDDITIONALLLLLfor43Right, level3Left_Right.leftChild);

        Assert.assertEquals(level3Right_Left, root.leftChild);
        Assert.assertEquals(ADDDITIONALLLLLfor43Left, root.rightChild);
        Assert.assertEquals(Color.BLACK, root.color);

        Assert.assertEquals(Color.RED, level4Left_RightLeft.color);
    }

    @Test
    public void DelCase2and5and6LeftSubtree() {
        RBTree tree = new RBTree();
        Node root = new Node(null, null, null, 40,  Color.BLACK);

        Node level2Left = new Node(root, null, null, 30, Color.RED);
        Node level2Right = new Node(root, null, null, 50, Color.BLACK);

        Node level3Left_Left = new Node(level2Left, null, null, 25, Color.BLACK);
        Node level3Right_Left  = new Node(level2Left, null, null, 35, Color.BLACK);
        Node level3Left_Right = new Node(level2Right, null, null, 45, Color.BLACK);
        Node level3Right_Right = new Node(level2Right, null, null, -10, Color.BLACK);

        Node level4Left_LeftRight = new Node(level3Right_Left, null, null, 34, Color.BLACK);
        Node level4Right_LeftRight = new Node(level3Right_Left, null, null, 37, Color.RED);

        Node ADDDITIONALLLLLfor37Left = new Node(level3Right_Left, null, null, 36, Color.BLACK);
        Node ADDDITIONALLLLLfor37Right = new Node(level3Right_Left, null, null, 38, Color.BLACK);

        root.leftChild = level2Left;
        root.rightChild = level2Right;

        level2Left.leftChild = level3Left_Left;
        level2Left.rightChild = level3Right_Left;

        level2Right.leftChild = level3Left_Right;
        level2Right.rightChild = level3Right_Right;

        level3Right_Left.leftChild = level4Left_LeftRight;
        level3Right_Left.rightChild = level4Right_LeftRight;



        level4Right_LeftRight.leftChild = ADDDITIONALLLLLfor37Left;
        level4Right_LeftRight.rightChild = ADDDITIONALLLLLfor37Right;

        tree.DeleteOneChild(level2Right);

        Assert.assertEquals(null, level2Left.parent);
        Assert.assertEquals(Color.BLACK, level2Left.color);
        Assert.assertEquals(level4Right_LeftRight, level2Left.rightChild);

        Assert.assertEquals(Color.RED, level4Right_LeftRight.color);
        Assert.assertEquals(root, level4Right_LeftRight.rightChild);
        Assert.assertEquals(level3Right_Left, level4Right_LeftRight.leftChild);

        Assert.assertEquals(Color.BLACK, root.color);
        Assert.assertEquals(level3Left_Right, root.rightChild);
        Assert.assertEquals(ADDDITIONALLLLLfor37Right, root.leftChild);

        Assert.assertEquals(Color.BLACK, level3Right_Left.color);
        Assert.assertEquals(ADDDITIONALLLLLfor37Left, level3Right_Left.rightChild);


    }
}