package com.finance.pms.datasources.web.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.tools.ant.filters.StringInputStream;
import org.eclipse.jetty.io.WriterOutputStream;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//FIXME implement a Deletable interface which could be called add the end of the calculation on all operands recursively. Problem how to pass the file name to be deleted?
public class WebDelegate {
	
	private static MyLogger LOGGER = MyLogger.getLogger(WebDelegate.class);
	
	private CloseableHttpClient httpClient;
	private String tensorflowHostIp;
	private String tensorflowHostPort;

	public WebDelegate() {
		this.tensorflowHostIp = MainPMScmd.getMyPrefs().get("kerasweb.hostip","0.0.0.0");
		this.tensorflowHostPort = MainPMScmd.getMyPrefs().get("kerasweb.hostport","1234");
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public String getTensorflowHostIp() {
		return tensorflowHostIp;
	}

	public void setTensorflowHostIp(String tensorflowHostIp) {
		this.tensorflowHostIp = tensorflowHostIp;
	}

	public String getTensorflowHostPort() {
		return tensorflowHostPort;
	}

	public void setTensorflowHostPort(String tensorflowHostPort) {
		this.tensorflowHostPort = tensorflowHostPort;
	}

	public CloseableHttpClient getHttpClient() {
		if (httpClient == null) {
			int timeout = 0;
			RequestConfig config = RequestConfig.custom()
					  .setConnectTimeout(timeout  * 1000)
					  .setConnectionRequestTimeout(timeout * 1000)
					  .setSocketTimeout(timeout * 1000).build();
			HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultRequestConfig(config);
			httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
			httpClient = httpClientBuilder.build();
		}
		return httpClient;
	}
	
	public JsonObject postHttpJson(HttpPost postRequest) throws IOException, ClientProtocolException, ServerException {
		JsonObject responseJson;
		try (
				CloseableHttpResponse postResponse = getHttpClient().execute(postRequest);
				StringWriter writer = new StringWriter();
				WriterOutputStream outStream = new WriterOutputStream(writer);
			) {
			if (postResponse.getStatusLine().getStatusCode() != 200) {
				throw new ServerException(
    					"Invalid response from the api: " + postResponse.getStatusLine().getStatusCode() + ". "
    					+ "Offended request " + postRequest);
			}
			postResponse.getEntity().writeTo(outStream);
			responseJson = new JsonParser().parse(writer.toString()).getAsJsonObject();
			if (responseJson.size() == 0) throw new RuntimeException("The api didn't return a valid response: " + responseJson + ", url " + postRequest.toString());
		}
		return responseJson;
	}
	
	public String postHttpString(HttpPost postRequest) throws IOException, ClientProtocolException, ServerException {
		try (
				CloseableHttpResponse postResponse = getHttpClient().execute(postRequest);
				StringWriter writer = new StringWriter();
				WriterOutputStream outStream = new WriterOutputStream(writer);
			) {
			postResponse.getEntity().writeTo(outStream);
			if (postResponse.getStatusLine().getStatusCode() != 200) {
				throw new ServerException(
    					"Invalid response from the api: " + postResponse.getStatusLine().getStatusCode() + ". "
    					+ "Offended request " + postRequest);
			}
			return writer.toString();
		}
		
	}
	
	public String getHttpCSVFile(String fileName) throws ServerException {
		
		String[] split = fileName.split(".+?" + File.separator + "(?=[^" + File.separator + "]+$)"); //".+?/(?=[^/]+$)"
		String fileNameBaseName = split[split.length-1];
		String fileNameBaseNameWOExt = fileNameBaseName.endsWith(".csv")?fileNameBaseName.replaceAll(".csv$", ""):fileNameBaseName;	
		
		String localFileCopyName = System.getProperty("installdir") + File.separator + "autoPortfolioLogs" + File.separator + fileNameBaseNameWOExt + ".csv";
		
		Path localFileCopyPath = Path.of(URI.create("file://" + localFileCopyName));
		boolean exist = Files.exists(localFileCopyPath);
		if (exist) {
			String remoteFileCopy = doGetHttpCSVFile(fileNameBaseNameWOExt, localFileCopyName + "." + UUID.randomUUID());
			Path remoteFilePathCopy = Path.of(URI.create("file://" + remoteFileCopy));
			try {
				if (localFileCopyPath.toFile().length() != remoteFilePathCopy.toFile().length()) {
					Files.delete(localFileCopyPath);
					Files.copy(remoteFilePathCopy, localFileCopyPath);
				}
				Files.delete(remoteFilePathCopy);
				return localFileCopyPath.toString();
			} catch (IOException e) {
				LOGGER.error(e, e);
				return "NONE";
			}
		} else {
			return doGetHttpCSVFile(fileNameBaseNameWOExt, localFileCopyName);
		}
		
	}

	private String doGetHttpCSVFile(String fileNameBaseNameWOExt, String localFileCopyName) throws ServerException {
		String encodedFileName;
		try {
			encodedFileName = URLEncoder.encode(fileNameBaseNameWOExt, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServerException(e);
		}
		HttpGet getPredictionFile = new HttpGet("http://"+getTensorflowHostIp()+":"+getTensorflowHostPort()+"/csvfile/autoPortfolioLogs/"+encodedFileName);
	    try (
	    		CloseableHttpResponse getResponse = getHttpClient().execute(getPredictionFile);
	    		FileWriter outFile = new FileWriter(new File(localFileCopyName), false);
	    		PrintWriter out1 = new PrintWriter(outFile);
	    ) {
    		if (getResponse.getStatusLine().getStatusCode() != 200) {
    			throw new ServerException(
    					"Invalid response from the api: " + getResponse.getStatusLine().getStatusCode() + ". "
    					+ "The requested file " + fileNameBaseNameWOExt + " may not be present on the server.");
    		} else {
    			out1.append(EntityUtils.toString(getResponse.getEntity()));
    		}
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new ServerException(e);
		}
		return localFileCopyName;
	}
	
	public String getHttpGetFile(String fileName, String localFileCopyName, Boolean isDeleteRemote) throws ServerException {
		String encodedFileName;
		try {
			encodedFileName = URLEncoder.encode(fileName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new ServerException(e);
		}
		HttpGet getPredictionFile = new HttpGet("http://"+getTensorflowHostIp()+":"+getTensorflowHostPort()+"/binaryfile/"+encodedFileName+"?is_delete="+isDeleteRemote.toString().toLowerCase());
	    try (
	    		CloseableHttpResponse getResponse = getHttpClient().execute(getPredictionFile);
	    		FileOutputStream outFileStream = new FileOutputStream(new File(localFileCopyName));
	    ) {
    		if (getResponse.getStatusLine().getStatusCode() != 200) {
    			throw new ServerException(
    					"Invalid response from the api: " + getResponse.getStatusLine().getStatusCode() + ". "
    					+ "The requested file " + fileName + " may not be present on the server.");
    		} else {
    			outFileStream.write(getResponse.getEntity().getContent().readAllBytes());
    		}
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw new ServerException(e);
		}
		return localFileCopyName;
	}
	
	public InputStream runAny(List<Path> files, String... cmdAndArgs) {		
	
		HttpPost postCmd = new HttpPost("http://"+getTensorflowHostIp()+":"+getTensorflowHostPort()+"/runany");
		
		MultipartEntityBuilder entitybuilder = MultipartEntityBuilder.create();
		entitybuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		
		JsonArray cmdAndArgsJson = new JsonArray();
		Arrays.stream(cmdAndArgs).forEach(p -> cmdAndArgsJson.add(p));
		
		ByteArrayBody cmdAndParams = new ByteArrayBody(new GsonBuilder().serializeNulls().create().toJson(cmdAndArgsJson).getBytes(), ContentType.DEFAULT_BINARY, "cmd_and_args");
		entitybuilder.addPart("cmd_and_args", cmdAndParams);
		
		files.forEach(f -> {
			String fileName = f.getFileName().toString();
			FileBody filebody = new FileBody(f.toFile(), ContentType.DEFAULT_BINARY, fileName);
			entitybuilder.addPart(fileName, filebody);
		});
		
		HttpEntity mutiPartHttpEntity = entitybuilder.build();
		postCmd.setEntity(mutiPartHttpEntity);
		
	    try (
	    		CloseableHttpResponse getResponse = getHttpClient().execute(postCmd);
	    		StringWriter writer = new StringWriter();
				WriterOutputStream outStream = new WriterOutputStream(writer);
	    ) {
    		if (getResponse.getStatusLine().getStatusCode() != 200) {
    			LOGGER.warn("Invalid response from the api: " + getResponse.getStatusLine().getStatusCode() + ". It is assumed that the file is not present in the server.");
    			LOGGER.warn(EntityUtils.toString(getResponse.getEntity()));
    		} else {
    			getResponse.getEntity().writeTo(outStream);
    			return new StringInputStream(writer.toString());
    		}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	    
	    return new StringInputStream("");
		
	}

	public void deleteLocalFile(String trainingCsv) {

		try {
			Path trainingCsvFilePath = Path.of(URI.create("file://" + trainingCsv));
			LOGGER.info("Deleting file local copy: " + trainingCsvFilePath.toString());
			boolean exist = Files.exists(trainingCsvFilePath);
			if (exist) {
				try {
					Files.delete(trainingCsvFilePath);
				} catch (IOException e) {
					LOGGER.error(e, e);
				}
			}
		} catch (Exception e1) {
			LOGGER.error("Can't create path from " + trainingCsv, e1);
		}
	}
}