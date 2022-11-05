package com.finance.pms.datasources.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.http.HttpException;

import com.finance.pms.MainPMScmd;
import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Stock;

public class ProvidersYahooPythonLocal extends ProvidersYahooPython {
	
    private static MyLogger LOGGER = MyLogger.getLogger(ProvidersYahooPythonLocal.class);
	
	private Path python_py;


	public ProvidersYahooPythonLocal(String pathToProps) {
        super(pathToProps);
		try {
			python_py = Files.createTempFile("yahooQuotes", "py");
			try (InputStream stream = ProvidersYahooPython.class.getResourceAsStream("/yahooQuotes/main.py")) {
				Files.copy(stream, python_py, StandardCopyOption.REPLACE_EXISTING);
				PosixFileAttributeView view = Files.getFileAttributeView(python_py, PosixFileAttributeView.class);
				if (view != null) {
					Set<PosixFilePermission> perms = view.readAttributes().permissions();
					if (perms.add(PosixFilePermission.OWNER_EXECUTE)) {
						view.setPermissions(perms);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error(e);
		}
    }
	
    @Override
	public void getQuotes(Stock stock, Date start, Date end) throws HttpException, SQLException {
    	
        YahooPythonQuotationFixer yahooPythonQuotationFixer = new YahooPythonQuotationFixer(python_py, stock, end);
        yahooPythonQuotationFixer.fixQuotations();
        
		super.getQuotes(stock, start, end);
	}
    
	protected InputStream readInput(String symbol, Date start, Date end) throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String pythonExec = MainPMScmd.getMyPrefs().get("quotes.pythonPath", "python3");
		ProcessBuilder pb = new ProcessBuilder(pythonExec, python_py.toString(), symbol, dateFormat.format(start), dateFormat.format(end));
		Process process = pb.start();
		InputStream processInputStream = process.getInputStream();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getErrorStream()));) {
			String line = null;
			while ((line = in.readLine()) != null) {
				LOGGER.error(line);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return processInputStream;
	}
    
    
    public void close() {
    	try {
			Files.delete(python_py);
		} catch (IOException e) {
			System.out.println("Error closing ProvidersYahooPython : " + e);
			e.printStackTrace();
		}
    }

}
