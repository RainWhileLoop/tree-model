package com.rwl.treemodel.model;

import java.util.List;

public interface TreeNode<T extends TreeNode, ID> {

    void setParentNode(T parentNode);

    List<T> getChildNodes();

    ID getId();

    ID getParentId();

    default boolean addChild(T child) {
        child.setParentNode(this);
        return getChildNodes().add(child);
    }


}
