package com.finance.pms.events.calculation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.shares.Currency;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.NoQuotationsException;
import com.finance.pms.events.quotations.StripedQuotations;
import com.finance.pms.talib.indicators.SMA;
import com.finance.pms.talib.indicators.TalibException;
import com.tictactec.ta.lib.MInteger;

public class HouseDerivation extends Indicator {
	
	private static MyLogger LOGGER = MyLogger.getLogger(HouseDerivation.class);
	
	private double[] houseDerivation;
	private MInteger outBegIdx = new MInteger();
	private MInteger outNBElement = new MInteger();
	private Date startDate;
	private Date endDate;
	
	private SMA sma;
	private int smaPeriod;

	public HouseDerivation(Stock stock, Date firstDate, Date lastDate, Currency calculationCurrency, Integer smaPeriod) throws NoQuotationsException, TalibException {
		super(stock, firstDate, lastDate, calculationCurrency, 1, 0);
		this.startDate = firstDate;
		this.endDate = lastDate;
		this.smaPeriod = smaPeriod;
		if (hasQuotations()) this.calculateIndicator();
	}

	private void calculateIndicator() throws TalibException, NoQuotationsException {

		if (!this.hasQuotations()) {
			LOGGER.warn("No Quotations for :" + this.getStockName() + " !! ");
			return;
		}
		
		sma = new SMA(stock, smaPeriod, startDate, endDate, stock.getMarket().getCurrency());
		sma.exportToCSV();

		Integer startIdx = 0;
		Integer endIdx = sma.getOutNBElement().value -1;
		
		initResArray(endIdx-startIdx+1);
		
		calculate(startIdx, endIdx, sma.getSma());
	}

	private void initResArray(int length) {
		houseDerivation = new double[length];
	}
	
	private void calculate(Integer startIdx, Integer endIdx, double[] inData) {

			for (int i=startIdx+1; i <= endIdx; i++) {
				if (inData[i] == 0 || inData[i-1] == 0) {
					houseDerivation[i-startIdx-1]  = 0;
				} else {
					houseDerivation[i-startIdx-1] = Math.log10(inData[i]/inData[i-1]);
				}
			}
			
			outBegIdx.value = startIdx + 1;
			outNBElement.value = endIdx - startIdx;
	}

	@Override
	public void exportToCSV() {
		File export = new File(
				System.getProperty("installdir") + File.separator + "tmp" + File.separator +
				this.getStockName().replaceAll("[/\\*\\.\\?,;><|\\!\\(\\) \\&]", "_") + "_"+ this.getClass().getSimpleName() +".csv");

		try {
			FileWriter fos = new FileWriter(export);
			String header = getHeader();
			fos.write(header);
			for (int i = 0; i < this.outNBElement.value; i++) {
				String line = getLine(i , i + this.outBegIdx.value);
				fos.write(line);
			}
			fos.close();
		} catch (IOException e) {
			LOGGER.error("", e);
		}  finally {
			
		}
	}
	
	private String getHeader() {
		return "DATE,QUOTE,SMA, HouseDrv\n";
	}
	
	private String getLine(int indicator, int quotations) {

		String line =
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
					this.sma.getIndicatorQuotationData().get(quotations + sma.getOutBegIdx().value).getDate()) + "," +
					this.sma.getIndicatorQuotationData().get(quotations + sma.getOutBegIdx().value).getClose() + "," +
					this.sma.getSma()[quotations]+"," +
					houseDerivation[indicator] + "\n";
		return line;
	}

	@Override
	public Stock getStock() {
		return super.getStock();
	}
	
	//TODO mv up
	public StripedQuotations getStripedData() {

		StripedQuotations ret = new StripedQuotations(this.outNBElement.value);

		for (int i = 0; i < this.outNBElement.value; i++) {
			ret.addBar(i, this.sma.getIndicatorQuotationData().get(i + this.outBegIdx.value + this.sma.getOutBegIdx().value).getDate(), houseDerivation[i]);
		}
		
		return ret;
	}

	public double[] getHouseDerivation() {
		return houseDerivation;
	}

	public Integer getQuotationIndexFromIndicatorIndex(Integer indicatorIdx) {
		return indicatorIdx + (this.outBegIdx.value + sma.getOutBegIdx().value);
	}
	
	public Integer getIndicatorIndexFromQuotationIndex(Integer quotationIdx) {
		return quotationIdx - (this.outBegIdx.value + sma.getOutBegIdx().value);
	}

}
