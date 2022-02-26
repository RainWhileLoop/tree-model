package com.rwl.treemodel.util;

import com.rwl.treemodel.model.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class TreeUtils {

    public static <T extends TreeNode> List<T> findLeafNode(List<T> treeList) {
        if(treeList == null || treeList.isEmpty()) {
            return null;
        }
        List<T> leafs = new LinkedList<>();
        for(T node : treeList) {
            if(node.getChildNodes() == null || node.getChildNodes().isEmpty()) {
                leafs.add(node);
            } else {
                leafs.addAll(findLeafNode(node.getChildNodes()));
            }
        }
        return leafs;
    }
}
