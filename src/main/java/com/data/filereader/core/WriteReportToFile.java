package com.data.filereader.core;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WriteReportToFile {

    private final BufferedWriter writer;

    WriteReportToFile(BufferedWriter writer){
        this.writer = writer;
    }

    public void writeDataToCSVFile(Map<String, List<String>> groupMap)throws IOException {

        groupMap.forEach((tradeKey,tradeValue)->{
            try {
                writer.append(tradeValue.toString().replace("[", "").replace("]", ""));
                writer.append("\n");
            }
            catch(IOException io){
                io.printStackTrace();
            }
        });

    }
}
