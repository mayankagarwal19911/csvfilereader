package com.data.filereader.core;

import com.data.filereader.util.PropertyUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class WriteReportsToFileTest {

    private static String outputFilePath = null;
    BufferedWriter writerMock = null;
    Map<String, List<String>> groupMap = null;

    @Before
    public void setUp() throws Exception {
        outputFilePath = PropertyUtil.getPropertyKey("file.outputfile");
        writerMock = mock(BufferedWriter.class);
        groupMap = new LinkedHashMap<>();
        List<String> list = new ArrayList<>();
        list.add("\"04/11/2014\",\"MSFT\",\"NASDAQ\",\"50\",\"47.57\",\"BUY\",\"0.8708\",\"9.99\",\"USD\",\"A sample comment\"");
        groupMap.put("MSFT", list);
    }

    @Test
    public void testWriteDataToCSVFile()throws IOException{
        WriteReportToFile writeReportToFile = new WriteReportToFile(writerMock);
        when(writerMock.append("character")).thenReturn(null);
        writeReportToFile.writeDataToCSVFile(groupMap);

    }

}