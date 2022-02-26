package com.rwl.treemodel.util;

import com.rwl.treemodel.TreeBuilder;
import com.rwl.treemodel.model.Data;
import com.rwl.treemodel.model.DataFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class TreeUtilsTest {

    private static List<Data> dataList;

    @BeforeAll
    public static void setup() throws IOException {
        DataFile dataFile = DataFile.getDataFile("data.json");
        dataList = TreeBuilder.<Data>builder()
                .withData(dataFile.getData())
                .buildTree();
    }

    @Test
    public void test_findLeafNode() {
        List<Data> leafNodes = TreeUtils.findLeafNode(dataList);
        Assertions.assertThat(leafNodes)
                .doesNotContain(new Data(1), new Data(2), new Data(7), new Data(14));

//        printTree(leafNodes, 0);
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
