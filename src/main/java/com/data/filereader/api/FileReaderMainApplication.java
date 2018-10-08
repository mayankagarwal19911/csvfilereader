package com.data.filereader.api;

import com.data.filereader.core.ReadTrades;
import com.data.filereader.util.PropertyUtil;

import java.io.IOException;

public class FileReaderMainApplication {

    private static String inputFilePath = PropertyUtil.getPropertyKey("file.path");
    private static String outputFilePath = PropertyUtil.getPropertyKey("file.outputfile");

    public static void main(String[] args)throws IOException {

        ReadTrades readTrades = new ReadTrades();
        readTrades.prepareTradeReport(inputFilePath, outputFilePath);
    }
}
