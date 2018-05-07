package com.mybatis.demo.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liyao
 * @Description:
 * @Date: Created in 2018/05/07 15:13
 */

public class BinaryTree {

    private BinaryTree lChild;
    private BinaryTree rChild;
    private BinaryTree root;
    private Object data;
    private List<BinaryTree> datas;

    public BinaryTree() {
    }

    public BinaryTree(Object data) {
        this.data = data;
    }

    public void create(Object[] objects) {
        datas = new ArrayList<>();
        for (Object o : objects) {
            datas.add(new BinaryTree(o));
        }

        root = datas.get(0);
        for (int i = 0; i < datas.size() / 2; i++) {
            datas.get(i).setlChild(datas.get(2 * i + 1));
            if (2 * i + 2 < datas.size()) {
                datas.get(i).setrChild(datas.get(2 * i + 2));
            }
        }
    }

    public void preOrder(BinaryTree binaryTree) {
        if (binaryTree != null) {
            visit(binaryTree.getData());
            preOrder(binaryTree.lChild);
            preOrder(binaryTree.rChild);
        }
    }

    public void inOrder(BinaryTree binaryTree) {
        if (binaryTree != null) {
            inOrder(binaryTree.lChild);
            visit(binaryTree.getData());
            inOrder(binaryTree.rChild);
        }
    }

    public void afterOrder(BinaryTree binaryTree) {
        if (binaryTree != null) {
            afterOrder(binaryTree.lChild);
            afterOrder(binaryTree.rChild);
            visit(binaryTree.getData());
        }
    }

    public void visit(Object data) {
        System.out.print(data + " ");
    }

    public BinaryTree getlChild() {
        return lChild;
    }

    public void setlChild(BinaryTree lChild) {
        this.lChild = lChild;
    }

    public BinaryTree getrChild() {
        return rChild;
    }

    public void setrChild(BinaryTree rChild) {
        this.rChild = rChild;
    }

    public BinaryTree getRoot() {
        return root;
    }

    public void setRoot(BinaryTree root) {
        this.root = root;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<BinaryTree> getDatas() {
        return datas;
    }

    public void setDatas(List<BinaryTree> datas) {
        this.datas = datas;
    }

    public static void main(String[] args) {
        Object[] objects = new Object[]{0, 1, 2, 3, 4, 5, 6, 7};
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.create(objects);
        binaryTree.preOrder(binaryTree.getRoot());
        System.out.println("\n");
        binaryTree.inOrder(binaryTree.getRoot());
        System.out.println("\n");
        binaryTree.afterOrder(binaryTree.getRoot());


    }
}
