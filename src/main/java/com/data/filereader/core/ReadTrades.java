package com.data.filereader.core;

import com.data.filereader.util.PropertyUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ReadTrades {

    private static Map<String, List<String>> tradesMap = new LinkedHashMap<>();
    private static  Map<String, Integer> headerMap = null;

    public boolean prepareTradeReport(String filePath, String outputFile)throws IOException{
        headerMap = prepareHeaderMap();
        try (Stream<String> stream = Files.lines(Paths.get(filePath)).skip(1))  {
            stream.forEach(s->{
                String key = getHeaderKey(s.split(","));
                if(tradesMap.get(key) == null){
                    List<String> data = new ArrayList<>();
                    data.add(s);
                    tradesMap.put(key, data);
                }
                else{
                    tradesMap.get(key).add(s);
                }
            });

        } catch (IOException ioe) {
            System.out.print("Exception occurred in reading a file: "+ioe);
        }

        return (writeTradeReport(outputFile));
    }

    private Map<String, Integer> prepareHeaderMap(){
        headerMap = new LinkedHashMap<>();
        String[] headerKeys = PropertyUtil.getPropertyKey("column.headerkeys").split(",");
        for(int i = 0;i <headerKeys.length; i++) headerMap.put(headerKeys[i], i);
        return headerMap;

    }

    private String getHeaderKey(String[] rowData){
        String[] groupByKeys = PropertyUtil.getPropertyKey("column.groupby").split(",");
        String keyName = "";
        for(String data : groupByKeys) keyName += rowData[headerMap.get(data)];
        return keyName;
    }

    private boolean writeTradeReport(String outputFile)throws IOException{
        BufferedWriter writer = null;
        boolean ifFileWritten = false;
        try{
            writer = new BufferedWriter(new FileWriter(outputFile, true));
            WriteReportToFile writeReportToFile = new WriteReportToFile(writer);
            writeReportToFile.writeDataToCSVFile(tradesMap);
            ifFileWritten = true;
        }
        catch(IOException io){
            System.out.println("Error occurred in Writing data to file : "+io);
        }
        finally {
            writer.close();
        }

        return ifFileWritten;
    }

}
