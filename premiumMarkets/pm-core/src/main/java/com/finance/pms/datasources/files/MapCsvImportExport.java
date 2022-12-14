package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.google.common.primitives.Doubles;

public class MapCsvImportExport implements CsvImportExport<Date> {
    
    private static Logger LOGGER = Logger.getLogger(MapCsvImportExport.class.getName());

    @Override
    public void exportData(String baseFileName, String exportFileNameExt, Map<Date, double[]> map) {
        
        //runStamp+"_"+exportFileNameExt
        String pathname = baseFileName + "_" + exportFileNameExt;
        File exportFile = new File(System.getProperty("installdir") + File.separator + "neural" + File.separator + pathname + ".csv");

        try (FileWriter fileWriter = new FileWriter(exportFile); BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            
            int maxLength = map.values().stream().max( (x,y) -> x.length - y.length ).orElse(new double[1]).length;

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            map.entrySet().stream().forEach(entry -> {

                try {
                    bufferWriter.write(String.format("%s, ", dateFormatter.format(entry.getKey())));
                    List<Double> value = new ArrayList<>(Doubles.asList(entry.getValue()));
                    for(int i = value.size(); i < maxLength; i++) value.add(Double.NaN);
                    String valueString = value.stream().map(e -> e.toString()).reduce((a, s) -> a + ", " + s).get();
                    bufferWriter.write(valueString);
                    bufferWriter.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    @Override
    public SortedMap<Date, double[]> importData(File exportFile) {
    	return importData(exportFile, new ArrayList<>());
    }

    @Override
    public SortedMap<Date, double[]> importData(File exportFile, List<String> headers) {

        SortedMap<Date, double[]> map = new TreeMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(exportFile))) {

            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            //SimpleDateFormat isoDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String line;
            int prevLength = 0;

            while ((line = bufferedReader.readLine()) != null) {
            	
                String[] columns = line.split(",", -1);
                if (headers.isEmpty()) headers.addAll(Arrays.asList(columns));
                
                //file check
                if (prevLength > 0 && prevLength != columns.length) throw new RuntimeException("Invalid file: " + exportFile.getName() + " at: " + line);
                
                try {
                    prevLength = columns.length;
                    
                    //entry creation
                    double[] array = Arrays.asList(columns).subList(1,columns.length).stream().mapToDouble(x -> (x.isEmpty())?Double.NaN:Double.valueOf(x)).toArray();
                    map.put(dateFormatter.parse(columns[0]), array);
                } catch (Exception e) {
                    LOGGER.warn("Unreadable line in " + exportFile.getAbsolutePath() + " : " + line.substring(0, Math.min(line.length(), 200)) + "..");
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return map;
    }

}
