package com.rwl.treemodel.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Data implements TreeNode<Data, Integer> {

    private Integer id;
    private Integer parentId;

    private Data parentNode;
    private List<Data> childNodes = new LinkedList<>();

    public Data() {

    }

    public Data(Integer id) {
        this.id = id;
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Data getParentNode() {
        return parentNode;
    }

    public void setChildNodes(List<Data> childNodes) {
        this.childNodes = childNodes;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", parentId=" + parentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return id.equals(data.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
