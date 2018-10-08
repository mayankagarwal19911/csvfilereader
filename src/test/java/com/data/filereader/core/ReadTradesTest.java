package com.data.filereader.core;

import com.data.filereader.util.PropertyUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadTradesTest {

    private static String filePath = null;
    private static String outputFilePath = null;

    @Before
    public void setUp() throws IOException {
        filePath = PropertyUtil.getPropertyKey("file.path");
        outputFilePath = PropertyUtil.getPropertyKey("file.outputfile");
    }


    @Test
    public void testPrepareTradeReport()throws IOException{
        ReadTrades readTrades = new ReadTrades();
        assertTrue(readTrades.prepareTradeReport(filePath, outputFilePath));
    }

}