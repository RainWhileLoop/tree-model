package com.rwl.treemodel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class TreeBuilderTest {

    @Test
    public void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getFileFromResourceAsStream("data.json");
        DataFile dataFile = mapper.readValue(inputStream, DataFile.class);

        List<Data> list = TreeBuilder.<Data>builder()
                .withData(dataFile.data)
                .buildTree();

        Assertions.assertThat(list)
                .allMatch(data -> data.getParentId() == null, "root only");

        printTree(list, 0);
    }

    private void printTree(List<Data> list, int level) {
        for(Data data : list) {
            System.out.println(data.toString().indent(level));
            if(!data.getChildNodes().isEmpty()) {
                printTree(data.getChildNodes(), level + 1);
            }
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }


    static class Data implements TreeNode<Data, Integer> {

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

    static class DataFile {

        private List<Data> data;

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }
}
