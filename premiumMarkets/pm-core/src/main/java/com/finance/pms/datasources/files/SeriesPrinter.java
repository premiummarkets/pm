package com.finance.pms.datasources.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.finance.pms.MainPMScmd;
import com.google.common.primitives.Doubles;

public class SeriesPrinter {
	

	public static UUID appRunStamp = UUID.randomUUID();
	
	
	public static String printoS(String fileName, LinkedHashMap<String, SortedMap<Date, Double>> series, String... forcedBaseFileName) {
		LinkedHashMap<String, SortedMap<Date, double[]>> collect2 = series.entrySet().stream()
		.collect(Collectors.toMap(e -> e.getKey(), e -> {
			return e.getValue().entrySet().stream()
					.collect(Collectors.toMap(es -> es.getKey(), es -> new double[] { es.getValue() }, (a, b) -> a, TreeMap::new));
			}, (a, b) -> a, LinkedHashMap::new));
		return printo(fileName, collect2, forcedBaseFileName);
	}

    public static String printo(String fileName, LinkedHashMap<String, SortedMap<Date, double[]>> series, String... forcedBaseFileName) {
        return printo(fileName, "tmp", series, forcedBaseFileName);
    }

    public static String printo(String fileName, String subFolder, LinkedHashMap<String, List<String>> headersPrefixes, LinkedHashMap<String, SortedMap<Date, double[]>> series, String ...forcedBaseFileName) {

    	Boolean printOutputs = MainPMScmd.getMyPrefs().getBoolean("print.outputs", true);
    	if (!printOutputs) return "None";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String baseFileName = (forcedBaseFileName == null || forcedBaseFileName.length != 1)?SeriesPrinter.appRunStamp.toString():forcedBaseFileName[0];
        File exportFile = new File(System.getProperty("installdir") + File.separator + ((subFolder != null)? subFolder + File.separator:"") + baseFileName + "_" + fileName+".csv");

        try (FileWriter fileWriter = new FileWriter(exportFile); BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            
        	List<Integer> seriesWidths = new ArrayList<>();
            List<String> headers = series.entrySet().stream().collect(
                    ArrayList::new, (r, e) -> {
                        SortedMap<Date, double[]> seriesN = e.getValue();
                        int maxWidth = seriesN.values().stream().max( (x,y) -> x.length - y.length ).orElse(new double[1]).length;
                        if (maxWidth > 1) {
                            Optional<String> header = IntStream.range(0, maxWidth)
                            		.mapToObj(i -> {
										String prefix = (headersPrefixes.get(e.getKey()).isEmpty())? "" : headersPrefixes.get(e.getKey()).get(i) + "_";
		                            	return  i + "_" + prefix + e.getKey();
		                            })
                            		.reduce((a, b) -> a + "," + b);
                            r.add(header.get());
                        } else {
                        	String prefix = (headersPrefixes.get(e.getKey()).isEmpty())? "" : headersPrefixes.get(e.getKey()).get(0) + "_";
                            r.add(prefix + e.getKey());
                        }
                        seriesWidths.add(maxWidth);
                    },
                    ArrayList::addAll);
            
            String headerString = headers.toString();
            bufferWriter.write("date," + headerString.substring(1, headerString.length()-1));
            bufferWriter.newLine();

            ArrayList<SortedMap<Date, double[]>> seriesValues = new ArrayList<>(series.values());
            for (Date date : seriesValues.get(0).keySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(dateFormat.format(date));
                IntStream.range(0, seriesValues.size())
                .forEach(i -> {
                    double[] ds = seriesValues.get(i).get(date);
                    stringBuilder.append("," + ((ds != null)?printArray(ds, seriesWidths.get(i)):printArray(new double[0], seriesWidths.get(i))));
                });
                bufferWriter.write(stringBuilder.toString());
                bufferWriter.newLine();
            }
            
            return exportFile.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    

    public static String printo(String fileName, String subFolder, LinkedHashMap<String, SortedMap<Date, double[]>> series, String... forcedBaseFileName) {
		LinkedHashMap<String, List<String>> headers = new LinkedHashMap<>();
		series.keySet().forEach(k -> headers.put(k, new ArrayList<>()));
		return printo(fileName, subFolder, headers, series, forcedBaseFileName);
	}

    protected static String printArray(double[] value, int seriesLength) {
        List<Double> vList = new ArrayList<>(Doubles.asList(value));
        for(int i = vList.size(); i < seriesLength; i++) vList.add(Double.NaN); //Padding
        String valueString = vList.stream().map(e -> e.toString()).reduce((a,s) -> a + ","+ s).get();
        return valueString;
    }

}
