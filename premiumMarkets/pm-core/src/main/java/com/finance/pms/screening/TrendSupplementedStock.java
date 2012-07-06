/**
 * Premium Markets is an automated financial technical analysis system. 
 * It implements a graphical environment for monitoring financial technical analysis
 * major indicators and for portfolio management.
 * In its advanced packaging, not provided under this license, it also includes :
 * Screening of financial web sites to pickup the best market shares, 
 * Forecast of share prices trend changes on the basis of financial technical analysis,
 * (with a rate of around 70% of forecasts being successful observed while back testing 
 * over DJI, FTSE, DAX and SBF),
 * Back testing and Email sending on buy and sell alerts triggered while scanning markets
 * and user defined portfolios.
 * Please refer to Premium Markets PRICE TREND FORECAST web portal at 
 * http://premiummarkets.elasticbeanstalk.com/ for a preview of more advanced features. 
 * 
 * Copyright (C) 2008-2012 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.screening;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.finance.pms.admin.install.logging.MyLogger;
import com.finance.pms.datasources.db.Query;
import com.finance.pms.datasources.db.Validatable;
import com.finance.pms.datasources.shares.Market;
import com.finance.pms.datasources.shares.Stock;
import com.finance.pms.events.quotations.Quotations;
import com.finance.pms.events.quotations.QuotationsFactories;

@Entity
@Table(name="TREND_SUPPLEMENT")
public class TrendSupplementedStock extends Validatable {

	private static final long serialVersionUID = -4962928490295672287L;


	private static final double IDEALPOT = .30;
	private static final double MINIDEALPRICECHG =.10;
	private static final Integer IDEALREC = 5;
	private static final double IDEALPAYOUT = 0.5;
	private static final double IDEALPEG = 0.75;
	private static final double IDEALEPSG = 0.20;
	private static final double IDEALPE = 15.00;

	
	/** The LOGGER. */
	protected static MyLogger LOGGER = MyLogger.getLogger(TrendSupplementedStock.class);

	private Stock stock;
	
	private Date trendDate;
	private String symbol;
	private String isin;

	private BigDecimal yahooMeanRecommendations;
	private BigDecimal yahooTargetPrice;
	private BigDecimal boursoMeanRecommendations;
	private BigDecimal boursoTargetPrice;
	
	private BigDecimal dividend; //being overridden in this order :  from yahoo then bourso then reuters
	
	private BigDecimal boursoBNA; //BNA == EPS (annual Earning Per Share)
	private BigDecimal boursoEstBNA; // == Estimated next BNA or estimated EPS

	private BigDecimal yahooEPS;
	private BigDecimal yahooEstEPS;

	private BigDecimal reutersYield; //= div / price
	private BigDecimal reutersPayoutRatio; // == dividend per share/earning per share. The best is a high div and low a ratio as the earnings support the dividend.	
	private BigDecimal reutersEPS;
	private BigDecimal reutersEstEPS;
	
	private Date endDate;
	private BigDecimal close;
	private BigDecimal ttmClose;
	private MyBigDec priceChangeTTM;
	private MyBigDec fullRating;
	
	//CREATE TABLE "APP"."TREND_SUPPLEMENT" ("SYMBOL" VARCHAR(20) NOT NULL, "ISIN" VARCHAR(20) NOT NULL, 
	//"BOURSOMEANRECOMMENDATIONS" NUMERIC(19,2) NOT NULL, "BOURSOTARGETPRICE" NUMERIC(19,2) NOT NULL, "YAHOOMEANRECOMMENDATIONS" NUMERIC(19,2) NOT NULL, "YAHOOTARGETPRICE" NUMERIC(19,2) NOT NULL, 
	//"DIVIDEND" DECIMAL(10,2) NOT NULL DEFAULT 0, 
	//"yahooEPS" NUMERIC(19,2) DEFAULT 0, "yahooEstEPS" NUMERIC(19,2) NOT NULL DEFAULT 0, 
	//"reutersEPS" NUMERIC(19,2) NOT NULL DEFAULT 0, "reutersEstEPS" NUMERIC(19,2) NOT NULL DEFAULT 0, "reutersPayoutRatio" NUMERIC(19,2) NOT NULL DEFAULT 0, 
	//"boursoBNA" NUMERIC(19,2) NOT NULL DEFAULT 0, "boursoEstBNA" NUMERIC(19,2) NOT NULL DEFAULT 0, 
	//"TRENDDATE" DATE NOT NULL DEFAULT '1970-01-01');

	
	@SuppressWarnings("unused")
	//Hibernate
	private TrendSupplementedStock() {
	}

	public TrendSupplementedStock(Stock stock) {
		this.stock = stock;
		this.isin = stock.getIsin();
		this.symbol = stock.getSymbol();

		this.trendDate = Calendar.getInstance().getTime();
	
//		yahooMeanRecommendations = BigDecimal.ZERO;
//		boursoMeanRecommendations = BigDecimal.ZERO;
//		yahooTargetPrice = BigDecimal.ZERO;
//		boursoTargetPrice = BigDecimal.ZERO;
//		dividend = BigDecimal.ZERO;
//		
//		yahooEPS = BigDecimal.ZERO;
//		yahooEstEPS = BigDecimal.ZERO;
//		
//		reutersPayoutRatio = BigDecimal.ZERO;
//		reutersEPS = BigDecimal.ZERO;
//		reutersEstEPS = BigDecimal.ZERO;
//		
//		boursoBNA = BigDecimal.ZERO;
//		boursoEstBNA = BigDecimal.ZERO;
		
	}
	
	@Transient
	public Boolean isNOTSetReutersEPS() {
		//return (this.reutersEPS == null || this.reutersEPS.compareTo(BigDecimal.ZERO) == 0);
		return (this.reutersEPS == null);
	}
	
	@Transient
	public Boolean isNOTSetReutersPayoutRatio() {
		return (this.reutersPayoutRatio == null);// || this.reutersPayoutRatio.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetReutersEstEPS() {
		return (this.reutersEstEPS == null);// || this.reutersEstEPS.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetBoursoBNA() {
		return (this.boursoBNA == null);// || this.boursoBNA.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetBoursoEstBNA() {
		return (this.boursoEstBNA == null);// || this.boursoEstBNA.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetYahooEstEPS() {
		return (this.yahooEstEPS == null);// || this.yahooEstEPS.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetYahooMeanRecommendations() {
		return (this.yahooMeanRecommendations == null);// || this.yahooMeanRecommendations.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetYahooTargetPrice() {
		return (this.yahooTargetPrice == null);// || this.yahooTargetPrice.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetBoursoMeanRecommendations() {
		return (this.boursoMeanRecommendations == null);// || this.boursoMeanRecommendations.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetBoursoTargetPrice() {
		return (this.boursoTargetPrice == null);// || this.boursoTargetPrice.compareTo(BigDecimal.ZERO) == 0);
	}
	
	@Transient
	public Boolean isNOTSetYahooEPS() {
		return (this.yahooEPS == null);// || this.yahooEPS.compareTo(BigDecimal.ZERO) == 0);
	}

	public void init(Date end) {
		
		this.endDate = end;
		
		try {
			Date ttm = ttm(endDate);
			Quotations quotationsInstance = QuotationsFactories.getFactory().getQuotationsInstance(stock, ttm, endDate, true, null, 0);
			close = quotationsInstance.getCloseForDate(endDate);
			ttmClose = quotationsInstance.getCloseForDate(ttm);
			
		} catch (Exception e) {
			LOGGER.warn(e,e);
		}
		
	}

	public MyBigDec pastRating() {
		MyBigDec payoutRat = this.payoutRating(); 
		MyBigDec prcRat = this.priceChangeRating();
		
		MyBigDec ret = payoutRat.add(prcRat);
		Integer nbV = isValid(payoutRat)+isValid(prcRat);
		
		return perCentOf(ret, nbV);
	}

	//Ideal Payout is 50%. It shouldn't be outside the 40%, 60% limits.
	//ie :
	//payout between 0 and 0.4 or 0.6 and 1 is bad
	//payout close to 0.5 is good
	public MyBigDec payoutRating() {
		return ratingFormula(this.payoutRatio(), IDEALPAYOUT);
	}

	private MyBigDec ratingFormula(MyBigDec value, Double ideal) {
		
		if (!value.isValid()) {//no value
			return value;
		}
		return new MyBigDec(value.getValue().divide(BigDecimal.valueOf(ideal),4,BigDecimal.ROUND_DOWN).subtract(BigDecimal.ONE).abs(), true);
	}

	public MyBigDec boursoPE() {
		return calculatePE(close, this.boursoBNA);
	}
	
	public MyBigDec boursoEPSG() {
		return calculateEPSG(this.boursoBNA, this.boursoEstBNA);
	}

	public MyBigDec boursoPEG() {
		return this.calculatePEG(boursoPE(), boursoEPSG());
	}
	
	public MyBigDec boursoPEGRating() {
		return peEpsgPegRating(boursoPE(), boursoEPSG(), boursoPEG());
	}
	
	private MyBigDec calculateEPSG(BigDecimal currentEPS, BigDecimal estEPS) {
		if (currentEPS == null  || currentEPS.compareTo(BigDecimal.ZERO) == 0 || estEPS == null || estEPS.compareTo(BigDecimal.ZERO) == 0	) {
			return new MyBigDec(null, false);
		}
		return new MyBigDec(estEPS.subtract(currentEPS).divide(currentEPS, 2, BigDecimal.ROUND_DOWN), true);
	}

	public MyBigDec calculatePE(BigDecimal currentPrice, BigDecimal currentEPS) {
		if (currentPrice == null || currentEPS == null || currentEPS.compareTo(BigDecimal.ZERO) == 0) {
			return new MyBigDec(null, false);
		}
		return new MyBigDec(currentPrice.divide(currentEPS, 2, BigDecimal.ROUND_DOWN), true);
	}

	public MyBigDec reutersPE() {
		return calculatePE(close, this.reutersEPS);
	}
	
	public MyBigDec reutersEPSG() {
		return calculateEPSG(this.reutersEPS, this.reutersEstEPS);
	}
	
	public MyBigDec reutersPEG() {
		return this.calculatePEG(reutersPE(), reutersEPSG());
	}
	
	public MyBigDec reutersPEGRating() {
		return peEpsgPegRating(reutersPE(), reutersEPSG(), reutersPEG());
	}

	public MyBigDec yahooPE() {
		return calculatePE(close, this.yahooEPS);
	}

	public MyBigDec yahooEPSG() {
		return calculateEPSG(this.yahooEPS, this.yahooEstEPS);
	}

	public MyBigDec yahooPEG() {
		return this.calculatePEG(yahooPE(), yahooEPSG());
	}
	
	public MyBigDec yahooPEGRating() {
		return peEpsgPegRating(yahooPE(), yahooEPSG(), yahooPEG());
	}

	// PEG ratio is obtained by dividing the  P/E ratio by the  annual earnings growth rate. 
	// It is considered a form of normalisation because higher growth rates should cause higher P/E ratios.
	private MyBigDec peEpsgPegRating(MyBigDec pe,MyBigDec epsg, MyBigDec peg) {
		// P/E : below 15 (ie not over priced),  EPS growth above .20,  PEG : below 0.75 (ie 15/20)
		MyBigDec peRatio = ratingFormula(pe, IDEALPE);
		MyBigDec epsgRatio = new MyBigDec(null, false);
		if (epsg.isValid()) {
			epsgRatio = ratingFormula(new MyBigDec(adjustedMaxValue(epsg.getValue(), IDEALEPSG), true), IDEALEPSG) ;
		}
		MyBigDec pegRatio = ratingFormula(peg, IDEALPEG);
		
		MyBigDec ret = new MyBigDec(null, false);
		ret =  peRatio.add(epsgRatio).add(pegRatio);
		Integer nbV = isValid(peRatio) + isValid(epsgRatio) + isValid(pegRatio);
		return perCentOf(ret, nbV);
	}
	
	public MyBigDec recRating() {
		MyBigDec ret = new MyBigDec(BigDecimal.ZERO, false);
		
		MyBigDec yahooRecRat = recRatingFormula(yahooMeanRecommendations,IDEALREC);
		MyBigDec boursoRecRat = recRatingFormula(boursoMeanRecommendations,IDEALREC);
		MyBigDec yahooPricePotentialRat = pricePotentialRatingFormula(yahooPotentielPrice(), IDEALPOT);
		MyBigDec boursoPricePotentialRat = pricePotentialRatingFormula(boursoPricePotentiel(), IDEALPOT);
		ret = ret
			.add(yahooRecRat)
			.add(boursoRecRat)
			.add(yahooPricePotentialRat)
			.add(boursoPricePotentialRat);
		
		Integer nbV = isValid(yahooRecRat) + isValid(boursoRecRat) + isValid(yahooPricePotentialRat) + isValid(boursoPricePotentialRat);
		
		return perCentOf(ret, nbV);
	}
	
	//Full rating
	//	<= past & opinions
	//		<= past
	//			<= pay out
	//			<= perfs
	//		<= future
	//			<= Yahoo rec + potential
	//			<= Bourso rec + potential
	//	<= peg
	//		<= Yahoo peg
	//		<= Bourso peg
	//		<= Reuters peg
	public MyBigDec fullRating() {

		if (fullRating == null) {
			
			MyBigDec ret = new MyBigDec(null, false);
			MyBigDec pastRatings = pastRating();
			MyBigDec recRatings = recRating();
			MyBigDec pegRatings = pegRatings();

			//We want at least one valid past rating or rec rating and one valid peg
			if ((isValid(pastRatings) == 0 && isValid(recRatings) == 0) || isValid(pegRatings) == 0) {
				fullRating =  new MyBigDec(null, false);
			} else {
				ret = pastRatings.add(recRatings).add(pegRatings);
				Integer nbV = isValid(pastRatings)+isValid(recRatings)+isValid(pegRatings);
				fullRating = perCentOf(ret,nbV);
			}
		} 

		return fullRating;
	}
	

	public MyBigDec estimationRating() {
		MyBigDec ret =  new MyBigDec(null, false);
		MyBigDec recRating = recRating();
		MyBigDec pegRatings = pegRatings();
		
		//We want at least one valid future rating and one valid peg
		if (isValid(recRating) == 0 || isValid(pegRatings) == 0) {
			return ret;
		}
		
		ret = recRating.add(pegRatings);
		Integer nbV = isValid(recRating)+isValid(pegRatings);
		
		return perCentOf(ret,nbV);
	}
	

	public MyBigDec noPayoutFullRating() {
		MyBigDec ret =  new MyBigDec(null, false);
		MyBigDec perfs = priceChangeRating();
		MyBigDec recRating = recRating();
		MyBigDec pegRatings = pegRatings();
		
		//We want at least one valid rec rating and one valid peg
		if (isValid(recRating) == 0 || isValid(pegRatings) == 0) {
			return ret;
		}
		
		ret = recRating.add(pegRatings).add(perfs);
		Integer nbV = isValid(recRating)+isValid(pegRatings)+isValid(perfs);
		
		return perCentOf(ret,nbV);
	}
	
	public MyBigDec pegRatings() {
		
		MyBigDec ret = new MyBigDec(BigDecimal.ZERO, false);
		MyBigDec yahooPEGRating = yahooPEGRating();
		MyBigDec reutersPEGRating = reutersPEGRating();
		MyBigDec boursoPEGRating = boursoPEGRating();
		
		ret = yahooPEGRating.add(reutersPEGRating).add(boursoPEGRating);
		Integer nbV = isValid(yahooPEGRating)+isValid(reutersPEGRating)+isValid(boursoPEGRating);
		
		return perCentOf(ret,nbV);
	}

	private Integer isValid(MyBigDec value) {
		//return (value == null || value.compareTo(BigDecimal.ZERO) == 0)?0:1;
		return (value.isValid())?1:0;
	}
	
	//The lower the better in bourso and Yahoo => we need to inverse that as ideal must be the high value
	private MyBigDec recRatingFormula(BigDecimal value, Integer idealRec) {
		return ratingFormula(new MyBigDec(BigDecimal.valueOf(idealRec).subtract(value), value.compareTo(BigDecimal.ZERO) != 0), idealRec.doubleValue());
		
	}
	
	private MyBigDec perCentOf(MyBigDec value, Integer factor) {
		if (factor == null || factor == 0 || !value.isValid()) {
			return new MyBigDec(null, false);
		}
		return new MyBigDec(value.getValue().divide(new BigDecimal(factor),4,BigDecimal.ROUND_DOWN), true);
	}
	


	public void resetStock(TrendSupplementedStock completedStock) {
		
		yahooMeanRecommendations = completedStock.getYahooMeanRecommendations();
		boursoMeanRecommendations = completedStock.getBoursoMeanRecommendations();
		yahooTargetPrice = completedStock.getYahooTargetPrice();
		boursoTargetPrice = completedStock.getBoursoTargetPrice();
		
		dividend = completedStock.getDividend();

		yahooEPS = completedStock.getYahooEPS();
		yahooEstEPS = completedStock.getYahooEstEPS();
		
		reutersPayoutRatio = completedStock.getReutersPayoutRatio();
		reutersEPS = completedStock.getReutersEPS();
		reutersEstEPS = completedStock.getReutersEstEPS();

		boursoEstBNA = completedStock.getBoursoEstBNA();
		boursoBNA = completedStock.getBoursoBNA();	
		
	}

	@Override
	public Query toDataBase() {
		return null;
	}

	public int compareTo(Validatable o) {
		return this.stock.compareTo(((TrendSupplementedStock)o).getStock());
	}
	
	
	public BigDecimal getYahooMeanRecommendations() {
		return yahooMeanRecommendations;
	}

	public BigDecimal getBoursoMeanRecommendations() {
		return boursoMeanRecommendations;
	}

	public BigDecimal getYahooTargetPrice() {
		return yahooTargetPrice;
	}

	public BigDecimal getBoursoTargetPrice() {
		return boursoTargetPrice;
	}

	public void setYahooMeanRecommendations(BigDecimal yahooMeanRecommendations) {
		this.yahooMeanRecommendations = yahooMeanRecommendations;
	}

	public void setBoursoMeanRecommendations(BigDecimal boursoramaMeanRecommendations) {
		this.boursoMeanRecommendations = boursoramaMeanRecommendations;
	}

	public void setYahooTargetPrice(BigDecimal yahooTargetPrice) {
		this.yahooTargetPrice = yahooTargetPrice;
	}

	public void setBoursoTargetPrice(BigDecimal boursoramaTargetPrice) {
		this.boursoTargetPrice = boursoramaTargetPrice;
	}

	public MyBigDec yahooPotentielPrice() {
		return pricePotential(this.getYahooTargetPrice());
	}

	public MyBigDec boursoPricePotentiel() {
		return pricePotential(this.getBoursoTargetPrice());
	}

	/**
	 * @param targetPrice 
	 * @return
	 */
	//Ideal = +infinite => n%
	private MyBigDec pricePotential(BigDecimal targetPrice) {
		
		MyBigDec targetPercent = new MyBigDec(null, false);
		if (this.close != null && targetPrice != null && targetPrice.compareTo(BigDecimal.ZERO) != 0) {
			targetPercent = new MyBigDec(targetPrice.subtract(close).divide(close, 4, BigDecimal.ROUND_DOWN), true);
		}

		return targetPercent;
	}
	
	private MyBigDec pricePotentialRatingFormula(MyBigDec targetPercent, double idealPot) {
		if (targetPercent.isValid()) {
			BigDecimal adjstedPriceChange = adjustedMaxValue(targetPercent.getValue(), idealPot);
			return ratingFormula(new MyBigDec(adjstedPriceChange, targetPercent.isValid()), idealPot);
		}
		return new MyBigDec(null,false);
	}

	private BigDecimal adjustedMaxValue(BigDecimal value, double artiMax) {
		BigDecimal adjstedValue = value;
		if (adjstedValue.compareTo(BigDecimal.ZERO) <= 0) {
			adjstedValue = BigDecimal.valueOf(artiMax*2).add(value.abs()); //neg price changes == artiMax + artiMax(to zero) + abs neg value
		} else if (adjstedValue.compareTo(BigDecimal.valueOf(artiMax)) >= 0) { //pos price change > max == max
			adjstedValue = BigDecimal.valueOf(artiMax); 
		}
		return adjstedValue;
	}
	
	public BigDecimal yield() {
		if  (close == null || this.getDividend() == null || this.getDividend().compareTo(BigDecimal.ZERO) == 0) {
			if (reutersYield == null) {
				return BigDecimal.ZERO;
			} else {
				return reutersYield;
			}
		}
		return this.getDividend().divide(this.close, 4, RoundingMode.DOWN);
	}
	
	public MyBigDec payoutRatio() {
		BigDecimal div = dividend; 
		MyBigDec avgEps = avgEps();
		
		if (!avgEps.isValid()) return new MyBigDec(null, false);
		if (div == null || div.compareTo(BigDecimal.ZERO) == 0) {
			if (reutersYield != null && reutersYield.compareTo(BigDecimal.ZERO) !=0 && close != null) {
				div = reutersYield.multiply(close);
			} else {
				//return new MyBigDec(null, false);
				//We set a ZERO dividend payout as valid as it is valid that a company doesn't distribute dividends
				return new MyBigDec(BigDecimal.ZERO, true);
			}
		}
		
		return new MyBigDec(div.divide(avgEps.getValue(),2,BigDecimal.ROUND_DOWN), true);
	}
	
	private MyBigDec avgEps() {
		MyBigDec bBNA = new MyBigDec(boursoBNA, boursoBNA != null && boursoBNA.compareTo(BigDecimal.ZERO) != 0);
		MyBigDec yEPS = new MyBigDec(yahooEPS, yahooEPS != null && yahooEPS.compareTo(BigDecimal.ZERO) != 0);
		MyBigDec rEPS = new MyBigDec(reutersEPS, reutersEPS != null && reutersEPS.compareTo(BigDecimal.ZERO) != 0);
		MyBigDec epsSum = bBNA.add(yEPS).add(rEPS);
		int nbv = isValid(bBNA)+isValid(yEPS)+isValid(rEPS);
		if (nbv == 0) return new MyBigDec(null,false);
		return new MyBigDec(epsSum.getValue().divide(new BigDecimal(nbv),2,BigDecimal.ROUND_DOWN), true);
	}

	//PEG == PE / EPS Growth
	//PEG ratio is obtained by dividing the P/E ratio by the annual earnings growth rate. 
	//It is considered a form of normalization because higher growth rates should cause higher P/E ratios.
	public MyBigDec calculatePEG(MyBigDec pe, MyBigDec epsg) {
		if (!pe.isValid() || !epsg.isValid()) {
			return new MyBigDec(null, false);
		}
		if (epsg.isZero()) {
			return new MyBigDec(null, false);
		}
		return new MyBigDec(pe.getValue().divide(epsg.getValue(),2,RoundingMode.DOWN).movePointLeft(2),true);
	}

	@Transient
	public String getName() {
		return stock.getName();
	}
	
	@Transient
	public String getSectorHint() {
		return stock.getSectorHint();
	}
	
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.MERGE)
	@JoinColumns ({
        @JoinColumn(name="isin", referencedColumnName = "isin"),
        @JoinColumn(name="symbol", referencedColumnName = "symbol")
    })
    @Id
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public MyBigDec priceChangeTTM() {

		if (this.priceChangeTTM  == null) {

			if (this.close == null || ttmClose == null) {
				this.priceChangeTTM = new MyBigDec(null, false);
			} else {
				BigDecimal priceDiffTTM = close.subtract(ttmClose);
				this.priceChangeTTM = new MyBigDec(priceDiffTTM.divide(ttmClose, 4, BigDecimal.ROUND_DOWN), true);
			}
		}

		return this.priceChangeTTM;

	}
	
	//ideal price change => +infinite => becomes 0 to 10%
	public MyBigDec priceChangeRating() {
		
		if (!priceChangeTTM().isValid()) return new MyBigDec(null, false);
		
		BigDecimal adjstedPriceChange = adjustedMaxValue(priceChangeTTM().getValue(), MINIDEALPRICECHG);
		return ratingFormula(new MyBigDec(adjstedPriceChange, true), MINIDEALPRICECHG);
	}

	/**
	 * @return
	 */
	private Date ttm(Date day) {
		Calendar ttm = Calendar.getInstance();
		ttm.setTime(day);
		ttm.add(Calendar.MONTH, -12);
		return ttm.getTime();
	}

	@Transient
	public String getSymbol() {
		return this.stock.getSymbol();
	}
	
	@Transient
	public String getIsin() {
		return this.stock.getIsin();
		
	}

	@Transient
	public Market getMarket() {
		return this.stock.getMarket();
	}
	
	public String dividendToString() {
		if (dividend != null) return dividend.toString();
		return "NA";
	}
	
	public BigDecimal getDividend() {
		return dividend;
	}

	public void setDividend(BigDecimal dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getYahooEstEPS() {
		return yahooEstEPS;
	}

	public void setYahooEstEPS(BigDecimal yahooFEPSGrowth) {
		this.yahooEstEPS = yahooFEPSGrowth;
	}
	
	public BigDecimal getReutersEstEPS() {
		return reutersEstEPS;
	}

	public void setReutersEstEPS(BigDecimal reutersEPSGrowth) {
		this.reutersEstEPS = reutersEPSGrowth;
	}
	
	public BigDecimal getReutersEPS() {
		return reutersEPS;
	}

	public void setReutersEPS(BigDecimal reutersEPS) {
		this.reutersEPS = reutersEPS;
	}

	public void setReutersPayoutRatio(BigDecimal payoutRatio) {
		this.reutersPayoutRatio=payoutRatio;
	}

	public BigDecimal getReutersPayoutRatio() {
		return reutersPayoutRatio;
	}

	public BigDecimal getBoursoEstBNA() {
		return boursoEstBNA;
	}

	public void setBoursoEstBNA(BigDecimal boursoFBNAGrowth) {
		this.boursoEstBNA = boursoFBNAGrowth;
	}
	
	public BigDecimal getBoursoBNA() {
		return boursoBNA;
	}

	public void setBoursoBNA(BigDecimal boursoBNAG) {
		this.boursoBNA = boursoBNAG;
	}
	
	public BigDecimal getYahooEPS() {
		return yahooEPS;
	}

	public void setYahooEPS(BigDecimal yahooEPS) {
		this.yahooEPS = yahooEPS;
	}

	@Id
	public Date getTrendDate() {
		return trendDate;
	}

	public void setTrendDate(Date trendDate) {
		this.trendDate = trendDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		result = prime * result + ((trendDate == null) ? 0 : trendDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrendSupplementedStock other = (TrendSupplementedStock) obj;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!stock.equals(other.stock))
			return false;
		if (trendDate == null) {
			if (other.trendDate != null)
				return false;
		} else if (!trendDate.equals(other.trendDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrendSupplementedStock [stock=" + stock + ", trendDate=" + trendDate + ", symbol=" + symbol + ", isin=" + isin + ", yahooMeanRecommendations="
				+ yahooMeanRecommendations + ", yahooTargetPrice=" + yahooTargetPrice + ", boursoMeanRecommendations=" + boursoMeanRecommendations
				+ ", boursoTargetPrice=" + boursoTargetPrice + ", dividend=" + dividend + ", boursoBNA=" + boursoBNA + ", boursoEstBNA=" + boursoEstBNA
				+ ", yahooEPS=" + yahooEPS + ", yahooEstEPS=" + yahooEstEPS + ", reutersPayoutRatio=" + reutersPayoutRatio + ", reutersEPS=" + reutersEPS
				+ ", reutersEstEPS=" + reutersEstEPS + "]";
	}

	public BigDecimal getReutersYield() {
		return reutersYield;
	}

	public void setReutersYield(BigDecimal reutersYield) {
		this.reutersYield = reutersYield;
	}
	
	public class MyBigDec implements Comparable<MyBigDec>{
		
		BigDecimal value;
		Boolean valid;

		public MyBigDec(BigDecimal value, Boolean valid) {
			super();
			this.value = value;
			this.valid = valid;
		}

		public MyBigDec add(MyBigDec addedValue) {
			if (this.isValid() && addedValue.isValid()) {
				this.value = this.value.add(addedValue.getValue());
				return this;
			}
			if (this.isValid()) {
				return this;
			}
			return addedValue;
		}

		public BigDecimal getValue() {
			return (value == null)?BigDecimal.ZERO:value;
		}
		
		public Double doubleValue() {
			return (value == null)?Double.NaN:value.doubleValue();
		}

		public Boolean isValid() {
			return valid;
		}

		public int compareTo(MyBigDec o2r) {
			if (this.isValid() && o2r.isValid()) return this.getValue().compareTo(o2r.getValue());
			if (this.isValid()) return -1;
			if (o2r.isValid()) return 1;
			//if (!this.isValid() && !o2r.isValid()) return 0;
			return 0;
			
		}
		
		public Boolean isZero() {
			if (isValid()) {
				return this.getValue().compareTo(BigDecimal.ZERO) == 0;
			}
			return true;
		}
		
		@Override
		public String toString() {
			if (!valid) return "NA";
			return value.toString();
		}
		
		
	}

	public BigDecimal close() {
		return close;
	}
	
	public String closeToString() {
		if (close != null) return close.toString();
		return "NA";
	}

	public BigDecimal ttmClose() {
		return ttmClose;
	}
	
	public String ttmCloseToString() {
		if (ttmClose != null) return ttmClose.toString();
		return "NA";
	}

}
