package com.rwl.treemodel.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataFile {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static DataFile getDataFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = getFileFromResourceAsStream(fileName);
        return mapper.readValue(inputStream, DataFile.class);
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = DataFile.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

}
