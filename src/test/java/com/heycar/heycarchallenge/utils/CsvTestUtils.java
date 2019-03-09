package com.heycar.heycarchallenge.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

public final class CsvTestUtils {
    public FileInputStream loadCsvFile(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return new FileInputStream(file);
    }

    public String readCsvFile(String fileName) throws IOException {
        FileInputStream stream = loadCsvFile(fileName);
        return IOUtils.toString(stream);
    }
}
