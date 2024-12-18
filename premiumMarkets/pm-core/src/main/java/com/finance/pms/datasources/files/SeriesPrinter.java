package com.finance.pms.datasources.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.finance.pms.admin.install.logging.MyLogger;
import com.google.common.primitives.Doubles;

public class SeriesPrinter {
	
	private static MyLogger LOGGER = MyLogger.getLogger(SeriesPrinter.class);
	
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
    
    public static String printo(String fileName, String subFolder, LinkedHashMap<String, SortedMap<Date, double[]>> series, String... forcedBaseFileName) {
		LinkedHashMap<String, List<String>> headers = new LinkedHashMap<>();
		series.keySet().forEach(k -> headers.put(k, new ArrayList<>()));
		return printo(fileName, subFolder, headers, series, forcedBaseFileName);
	}
    

    //Append
	public static String appendto(String fileFullPathStr, LinkedHashMap<String, List<String>> headersPrefixes, LinkedHashMap<String, SortedMap<Date, double[]>> series) throws OvewriteDeltaException {
		
		if (series.isEmpty() || series.values().stream().anyMatch(v -> v.isEmpty())) {
			LOGGER.warn("Empty series. No append was made: " + fileFullPathStr);
			return fileFullPathStr;
		}
		
		synchronized (LOGGER) {
        	String tmpExtenstion = "." + UUID.randomUUID().toString();
        	String tmpFileName = fileFullPathStr + tmpExtenstion;
			Path tmpFilePath = Path.of(URI.create("file://" + tmpFileName));
	        try {
	        	
	        	//Read existing and remove NaNs out of it
	        	String last = null;
	        	List<Integer> seriesWidths = new ArrayList<>();

				try (BufferedReader inputR = new BufferedReader(new FileReader(fileFullPathStr)); BufferedWriter inputWTmp = new BufferedWriter(new FileWriter(tmpFileName))) {
	   
		    		//Check headers
		        	String line = inputR.readLine();
		        	List<String> existingHeaders = Arrays.asList(line.split(","));
		        	List<String> existingHeaders_wo_date = existingHeaders.subList(1, existingHeaders.size());
		            List<String> newHeaders = Arrays.asList(makeHeaders(headersPrefixes, series, seriesWidths).get(0).split(","));
		        	boolean sameHeader = IntStream.range(0, newHeaders.size()).allMatch(i -> existingHeaders_wo_date.get(i).trim().equals(newHeaders.get(i)));
		        	if (newHeaders.size() != existingHeaders_wo_date.size() || !sameHeader) {
		        		Boolean overwriteInvalidDelta = Boolean.valueOf(MainPMScmd.getMyPrefs().get("print.delta.overwriteinvalid", "false"));
		        		if (overwriteInvalidDelta) {
		        			throw new OvewriteDeltaException("fileFullPathStr");
		        		} else {
		        			throw new Exception("Headers: " + newHeaders + " don't match existing: " + existingHeaders_wo_date);
		        		}
		        	}
					
					//Find the last date and trunk series to last date (ex. lines containing NaN)
		        	inputWTmp.write(line); //headers
		        	inputWTmp.newLine();
					while ((line = inputR.readLine()) != null) {
						if (!line.contains("NaN")) {
							last = line;
							inputWTmp.write(line);
							inputWTmp.newLine();
						}
					}
					
		        }

				Path fileFullPath = Path.of(URI.create("file://" + fileFullPathStr));
				Files.delete(fileFullPath);
				Files.move(tmpFilePath, fileFullPath);
	        	
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				Date lastCalculatedDate = dateFormat.parse(last.split(",")[0]);
				Calendar lastCalculatedCal = Calendar.getInstance();
				lastCalculatedCal.setTime(lastCalculatedDate);
				lastCalculatedCal.add(Calendar.DAY_OF_YEAR, 1);
				
				series.entrySet().stream().forEach(s -> LOGGER.info("Received series: " + s.getKey() + " from " + s.getValue().firstKey() + " to " + s.getValue().lastKey()));
				LOGGER.info("Tailing from: " + lastCalculatedCal.getTime());
				
				LinkedHashMap<String, SortedMap<Date, double[]>> tailSeries = series.entrySet().stream()
						.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().tailMap(lastCalculatedCal.getTime()), (a,b) -> a, LinkedHashMap::new));
				
				 try (BufferedWriter inputW = new BufferedWriter(new FileWriter(fileFullPathStr, true))) {
					 appendContent(tailSeries, seriesWidths, inputW);
				 }
				
				 return fileFullPathStr;
				
			} catch (Exception e) {
				throw new RuntimeException(" in " + fileFullPathStr, e);
			} finally {
				try { //In case of failure
					Files.delete(tmpFilePath);
	        	} catch (NoSuchFileException e) {
	        		LOGGER.info("Could not delete temporary file " + tmpFilePath + " as it does not exists. All is well.");
				} catch (IOException e) {
					LOGGER.error(e, e);
				}
			}
		}

	}
	
	//Over Write
    public static String printo(String exportFile, LinkedHashMap<String, List<String>> headersPrefixes, LinkedHashMap<String, SortedMap<Date, double[]>> series) {
    	
    	if (series.isEmpty() || series.values().stream().anyMatch(v -> v.isEmpty())) return "NONE";

        try (FileWriter fileWriter = new FileWriter(exportFile, false); BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            
        	List<Integer> seriesWidths = new ArrayList<>();
        	List<String> headers;
        	if (headersPrefixes.size() == 1 && headersPrefixes.containsKey("keepHeaders")) {
        		int maxWidth = series.get("keepHeaders").values().stream().max( (x,y) -> x.length - y.length ).orElse(new double[1]).length; //hmm??
        		seriesWidths.add(maxWidth);
        		headers = headersPrefixes.get("keepHeaders");
        	} else {
        		headers = makeHeaders(headersPrefixes, series, seriesWidths);
        	}
            
            //Write headers
            String headerString = headers.toString();
            bufferWriter.write("date," + headerString.substring(1, headerString.length()-1));
            bufferWriter.newLine();

            appendContent(series, seriesWidths, bufferWriter);
            
            return exportFile;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    //Over Write with Generated file name
    public static String printo(String fileName, String subFolder, LinkedHashMap<String, List<String>> headersPrefixes, LinkedHashMap<String, SortedMap<Date, double[]>> series, String ...forcedBaseFileName) {

    	Boolean printOutputs = Boolean.valueOf(MainPMScmd.getMyPrefs().get("print.outputs", "true"));
    	if (!printOutputs) return "NONE";

        String baseFileName = (forcedBaseFileName == null || forcedBaseFileName.length != 1)?SeriesPrinter.appRunStamp.toString():forcedBaseFileName[0];
        File exportFile = new File(System.getProperty("installdir") + File.separator + ((subFolder != null)? subFolder + File.separator:"") + baseFileName + "_" + fileName + ".csv");

        try (FileWriter fileWriter = new FileWriter(exportFile); BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            
        	List<Integer> seriesWidths = new ArrayList<>();
            List<String> headers = makeHeaders(headersPrefixes, series, seriesWidths);
            
            //Write headers
            String headerString = headers.toString();
            bufferWriter.write("date," + headerString.substring(1, headerString.length()-1));
            bufferWriter.newLine();

            appendContent(series, seriesWidths, bufferWriter);
            
            return exportFile.getAbsolutePath();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

	private static void appendContent(LinkedHashMap<String, SortedMap<Date, double[]>> series, List<Integer> seriesWidths, BufferedWriter bufferWriter)
			throws IOException {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
		
	}

	private static ArrayList<String> makeHeaders(LinkedHashMap<String, List<String>> headersPrefixes, LinkedHashMap<String, SortedMap<Date, double[]>> series, List<Integer> seriesWidths) {
		return series.entrySet().stream().collect(
		        ArrayList::new, (r, e) -> {
		            SortedMap<Date, double[]> seriesN = e.getValue();
		            String seriesNKey = e.getKey();
		            int maxWidth = seriesN.values().stream().max( (x,y) -> x.length - y.length ).orElse(new double[1]).length;
		            if (maxWidth > 1) {
		            	int paddingSize = String.format("%d", maxWidth).length();
		                Optional<String> header = IntStream.range(0, maxWidth)
		                		.mapToObj(i -> {
									String prefix = (headersPrefixes.get(seriesNKey).isEmpty())? "" : headersPrefixes.get(seriesNKey).get(i) + "_";
		                        	return  String.format("%0" + paddingSize + "d", (int)i) + "_" + prefix + seriesNKey;
		                        })
		                		.reduce((a, b) -> a + "," + b);
		                r.add(header.get());
		            } else {
						String prefix = (headersPrefixes.get(seriesNKey).isEmpty())? "" : headersPrefixes.get(seriesNKey).get(0) + "_";
		                r.add(prefix + seriesNKey);
		            }
		            seriesWidths.add(maxWidth);
		        },
		        ArrayList::addAll);
	}

    protected static String printArray(double[] value, int seriesLength) {
        List<Double> vList = new ArrayList<>(Doubles.asList(value));
        for(int i = vList.size(); i < seriesLength; i++) vList.add(Double.NaN); //Padding
        String valueString = vList.stream().map(e -> e.toString()).reduce((a,s) -> a + ","+ s).get();
        return valueString;
    }

}
