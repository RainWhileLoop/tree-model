package com.rwl.treemodel;

import java.util.*;
import java.util.stream.Collectors;

public class TreeBuilder<T extends TreeNode> {

    private Collection<T> list;

    public static TreeBuilder builder() {
        return new TreeBuilder();
    }

    public TreeBuilder<T> withData(Collection<T> list) {
        this.list = list;
        return this;
    }

    public List<T> buildTree() {
        if (list == null || list.isEmpty()) {
            return null;
        }

        Map<T, Boolean> treeMap = new LinkedHashMap<>();
        for (T t : list) {
            treeMap.put(t, true);
        }

        return processBuildTree(treeMap);
    }

    private List<T> processBuildTree(Map<T, Boolean> map) {
        List<T> unrelateList = map.entrySet().stream()
                .filter(e -> e.getValue() == true)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        List<T> roots = new LinkedList<>();
        for (T node : unrelateList) {
            if (node.getParentId() == null) {
                roots.add(node);
                map.replace(node, false);
            } else {
                Optional<T> parentOptional = findParent(list, node.getParentId());
                if (parentOptional.isPresent()) {
                    T parent = parentOptional.get();
                    parent.addChild(node);
                    map.replace(node, false);
                } else {
                    throw new IllegalArgumentException("Cannot find parentId : " + node.getParentId());
                }
            }
        }

        if (map.containsValue(true)) {
            processBuildTree(map);
        }

        return roots;
    }

    private Optional<T> findParent(Collection<T> list, Object parentId) {
        return list.stream()
                .filter(t -> t.getId().equals(parentId))
                .findFirst();
    }
}
