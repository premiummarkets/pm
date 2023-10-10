package com.finance.pms.screening;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.DataSource;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.datasources.web.ProvidersYahooPython;
import com.finance.pms.portfolio.IndepShareList;
import com.finance.pms.portfolio.PortfolioDAO;

public class YahooStockScreener implements ScreenerCalculator<Set<Stock>> {
	
	private static final String YAHOOINDICES_BETA_SCREENER = "YAHOOINDICES,BETA:SCREENER";

	private static MyLogger LOGGER = MyLogger.getLogger(YahooStockScreener.class);
	
	Boolean updateShareList;
	
	public YahooStockScreener(Boolean updateShareList) {
		super();
		this.updateShareList = updateShareList;
	}

	@Override
	public Set<Stock> calculate() {
		
		Set<Stock> returned = new HashSet<>(); 
		
		try {
			
			if (updateShareList) {
			
				Set<Stock> filtered = new HashSet<>();
				
				Path python_py = Files.createTempFile("screnner", "py");
				try (InputStream stream = ProvidersYahooPython.class.getResourceAsStream("/yahooQuotes/screnner.py")) {
					Files.copy(stream, python_py, StandardCopyOption.REPLACE_EXISTING);
					PosixFileAttributeView view = Files.getFileAttributeView(python_py, PosixFileAttributeView.class);
					if (view != null) {
						Set<PosixFilePermission> perms = view.readAttributes().permissions();
						if (perms.add(PosixFilePermission.OWNER_EXECUTE)) {
							view.setPermissions(perms);
						}
					}
				}
				
				LOGGER.info("Screnner python file path: " + python_py.toString());
				ProcessBuilder pb = new ProcessBuilder("python3", python_py.toString());
				Process p = pb.start();

				try (BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));){

					String line = null;
					while ((line = in.readLine()) != null) {
						LOGGER.info("Screnner output: " + line);
						if (line.isEmpty()) continue;
						String[] stockLine = line.split(",");
						String symbol = stockLine[1];
						List<Stock> stocks = DataSource.getInstance().getShareDAO().loadStockByIsinOrSymbol(symbol);
						if (stocks.isEmpty()) {
							LOGGER.warn("Symbol '" + symbol + "' not found in db, please updated the share lists");
						} else if (stocks.size() == 1) {
							filtered.addAll(stocks);
						} else {
							LOGGER.warn("Symbol '" + symbol + "' has multiple matched in db: " + stocks);
						}
					}

				} catch (IOException e) {
					LOGGER.error(e, e);
					throw e;
				}
				
				if (!filtered.isEmpty()) {
					
					PortfolioDAO portfolioDAO = DataSource.getInstance().getPortfolioDAO();
					IndepShareList shareList = portfolioDAO.loadIndepShareList(YAHOOINDICES_BETA_SCREENER);
					shareList.getListShares().clear();
					filtered.stream().forEach(e -> {
						shareList.addShare(e);
					});
					portfolioDAO.saveOrUpdatePortfolio(shareList);
					
					try {
						Files.delete(python_py);
					} catch (IOException e) {
						LOGGER.error("Error deleting screnner.py: " + e, e);
					}
					
				} else {
					LOGGER.error("The screnner did not return anything usable: " + filtered + " for script: " + python_py.toString());
				}
				
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			
		} finally {
			PortfolioDAO portfolioDAO = DataSource.getInstance().getPortfolioDAO();
			IndepShareList shareList = portfolioDAO.loadIndepShareList(YAHOOINDICES_BETA_SCREENER);
			returned.addAll(shareList.getListShares().keySet());
		}
		
		return returned;
	}

}
