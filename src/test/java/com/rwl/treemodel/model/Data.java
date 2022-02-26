package com.rwl.treemodel.model;

import java.util.LinkedList;
import java.util.List;

public class Data implements TreeNode<Data, Integer> {

    private Integer id;
    private Integer parentId;

    private Data parentNode;
    private List<Data> childNodes = new LinkedList<>();

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public void setParentNode(Data parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public List<Data> getChildNodes() {
        return childNodes;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", parentId=" + parentId +
                '}';
    }
}
