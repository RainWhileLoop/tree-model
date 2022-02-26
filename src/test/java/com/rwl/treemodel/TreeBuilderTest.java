package com.rwl.treemodel;

import com.rwl.treemodel.model.Data;
import com.rwl.treemodel.model.DataFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TreeBuilderTest {

    private static DataFile dataFile;

    @BeforeAll
    public static void setup() throws IOException {
        dataFile = DataFile.getDataFile("data.json");
    }

    @Test
    public void test() {
        List<Data> list = TreeBuilder.<Data>builder()
                .withData(dataFile.getData())
                .buildTree();

        Assertions.assertThat(list)
                .allMatch(data -> data.getParentId() == null, "root only");

//        printTree(list, 0);
    }

    private void printTree(List<Data> list, int level) {
        for(Data data : list) {
            System.out.println(data.toString().indent(level));
            if(!data.getChildNodes().isEmpty()) {
                printTree(data.getChildNodes(), level + 1);
            }
        }
    }

}
