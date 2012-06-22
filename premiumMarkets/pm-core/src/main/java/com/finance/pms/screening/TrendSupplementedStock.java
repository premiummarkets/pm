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

import org.hibernate.loader.custom.Return;

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

	private static final BigDecimal PAYOUT_HIGH_ZERO = new BigDecimal(0.60).setScale(2,BigDecimal.ROUND_UP);
	private static final BigDecimal PAYOUT_HIGH_MINNEG = new BigDecimal(1.00).setScale(2,BigDecimal.ROUND_UP);
	private static final BigDecimal PAYOUT_MAXPOS = new BigDecimal(0.50).setScale(2,BigDecimal.ROUND_DOWN);
	private static final BigDecimal PAYOUT_LOW_ZERO = new BigDecimal(0.40).setScale(2,BigDecimal.ROUND_DOWN);
	private static final BigDecimal PAYPOUT_LOW_MINNEG = new BigDecimal(0.00).setScale(2,BigDecimal.ROUND_DOWN);
	
	private static final BigDecimal FORWARD_PE_MINNEG = new BigDecimal(30);
	private static final BigDecimal FORWARD_PE_ZERO = new BigDecimal(15);
	private static final BigDecimal FORWARD_PE_MAXPOS = BigDecimal.ZERO;
	
	private static final BigDecimal PEG_MINNEG = new BigDecimal(2);
	private static final BigDecimal PEG_ZERO = new BigDecimal(0.75);
	private static final BigDecimal PEG_MAXPOS = BigDecimal.ZERO;
	
	private static final BigDecimal EPS_GROWTH_MINNEG = BigDecimal.ZERO;
	private static final BigDecimal EPS_GROWTH_ZERO = new BigDecimal(.20);
	private static final BigDecimal EPS_GROWTH_MAXPOS = new BigDecimal(1);

	private static final long serialVersionUID = -4962928490295672287L;

	
	private static Integer MAXREC = 5;
	private static Integer MINREC = 1;
	
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
	private BigDecimal quotePerfOverPeriod;
	private BigDecimal fullRating;
	
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

	public BigDecimal pastRating() {
		BigDecimal o2payout = this.payoutRating(); 
		BigDecimal o2c = this.quotePerfOverPeriod();

		BigDecimal ret = o2payout.add(o2c);
		Integer nbV = isValid(o2payout)+isValid(o2c);
		
		return perCentOf(ret, nbV);
	}

	//Ideal Payout is 50%. It shouldn't be outside the 40%, 60% limits.
	//ie :
	//payout between 0 and 0.4 or 0.6 and 1 is bad
	//payout close to 0.5 is good
	private BigDecimal payoutRating() {
		
		BigDecimal payout = validated(this.payoutRatio());
		
//		BigDecimal o2payout = BigDecimal.ZERO;
//		if (payout.compareTo(new BigDecimal(0.50)) <= 0) {
//			//0.00, 0.40, 0.50
//			o2payout = distanceRatioFromConstant(payout,PAYPOUT_LOW_MINNEG, PAYOUT_LOW_ZERO, PAYOUT_MAXPOS);
//			
//		} else if (payout.compareTo(new BigDecimal(0.50)) > 0) {
//			//1.00, 0.60, 0.50
//			o2payout = distanceRatioFromConstant(payout,PAYOUT_HIGH_MINNEG, PAYOUT_HIGH_ZERO, PAYOUT_MAXPOS);
//		}
//		return o2payout;
		if (payout.compareTo(PAYOUT_HIGH_MINNEG) >= 0) return BigDecimal.ZERO;
		if (payout.compareTo(PAYPOUT_LOW_MINNEG) <= 0) return BigDecimal.ZERO; 
		
		BigDecimal distanceToIdeal = payout.subtract(PAYOUT_MAXPOS).abs();
		BigDecimal distBroughtToOne = distanceToIdeal.divide(PAYOUT_MAXPOS);
		
		//The shorter the distance, the better so we do 1 - dist
		return BigDecimal.ONE.subtract(distBroughtToOne);
	}

	public BigDecimal boursoPE() {
		return calculatePE(close, this.boursoBNA);
	}
	
	public BigDecimal boursoEPSG() {
		return calculateEPSG(this.boursoBNA, this.boursoEstBNA);
	}

	public BigDecimal boursoPEG() {
		return this.calculatePEG(boursoPE(), boursoEPSG());
	}
	
	public BigDecimal boursoPEGRating() {
		return peEpsgPegRating(boursoPE(), boursoEPSG(), boursoPEG());
	}
	
	private BigDecimal calculateEPSG(BigDecimal currentEPS, BigDecimal estEPS) {
		if (currentEPS == null  || currentEPS.compareTo(BigDecimal.ZERO) == 0 || estEPS == null) {
			return BigDecimal.ZERO;
		}
		return estEPS.subtract(currentEPS).divide(currentEPS, 2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal calculatePE(BigDecimal currentPrice, BigDecimal currentEPS) {
		if (currentPrice == null || currentEPS == null || currentEPS.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		return currentPrice.divide(currentEPS, 2, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal reutersPE() {
		return calculatePE(close, this.reutersEPS);
	}
	
	public BigDecimal reutersEPSG() {
		return calculateEPSG(this.reutersEPS, this.reutersEstEPS);
	}
	
	public BigDecimal reutersPEG() {
		return this.calculatePEG(reutersPE(), reutersEPSG());
	}
	
	public BigDecimal reutersPEGRating() {
		return peEpsgPegRating(reutersPE(), reutersEPSG(), reutersPEG());
	}

	public BigDecimal yahooPE() {
		return calculatePE(close, this.yahooEPS);
	}

	public BigDecimal yahooEPSG() {
		return calculateEPSG(this.yahooEPS, this.yahooEstEPS);
	}

	public BigDecimal yahooPEG() {
		return this.calculatePEG(yahooPE(), yahooEPSG());
	}
	
	public BigDecimal yahooPEGRating() {
		return peEpsgPegRating( yahooPE(), yahooEPSG(), yahooPEG());
	}

	/**
	 * @return
	 */
	// PEG ratio is obtained by dividing the  P/E ratio by the  annual earnings growth rate. 
	// It is considered a form of normalisation because higher growth rates should cause higher P/E ratios.
	private BigDecimal peEpsgPegRating(BigDecimal pe,BigDecimal epsg, BigDecimal peg) {
		// P/E : below 15 (ie not over priced),  EPS growth above .20,  PEG : below 0.75 (ie 15/20)
		//30, 15, 0
		BigDecimal peRatio = distanceRatioFromConstant(validated(pe),FORWARD_PE_MINNEG, FORWARD_PE_ZERO, FORWARD_PE_MAXPOS);
		//0, 0.20, 1
		BigDecimal epsRatio = distanceRatioFromConstant(validated(epsg), EPS_GROWTH_MINNEG, EPS_GROWTH_ZERO, EPS_GROWTH_MAXPOS);
		//2, .75, 0
		BigDecimal pegRatio = distanceRatioFromConstant(validated(peg), PEG_MINNEG, PEG_ZERO, PEG_MAXPOS);
		
		BigDecimal ret = BigDecimal.ZERO;
		ret =  peRatio.add(epsRatio).add(pegRatio).setScale(2,BigDecimal.ROUND_DOWN);
		Integer nbV = 3;
		return perCentOf(ret, nbV);
	}
	
	public BigDecimal recRating() {
		BigDecimal ret = BigDecimal.ZERO;
		
		ret = ret
			.add(complementarPerCentOf(yahooMeanRecommendations,MAXREC,MINREC))
			.add(complementarPerCentOf(boursoMeanRecommendations,MAXREC,MINREC))
			.add(validated(getYahooPotentielPrice()))
			.add(validated(getBoursoPricePotentiel()));
		
		Integer nbV = 
					isValid(yahooMeanRecommendations) + isValid(boursoMeanRecommendations) +
					isValid(getYahooPotentielPrice()) + isValid(getBoursoPricePotentiel());
		
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
	public BigDecimal fullRating() {

		if (fullRating == null) {
			
			BigDecimal ret = BigDecimal.ZERO;
			//BigDecimal pastAndOpinions = pastAndFuture();
			BigDecimal pastRatings = pastRating();
			BigDecimal recRatings = recRating();
			BigDecimal pegRatings = pegRatings();

			//We want at least one valid past future rating and one valid peg
			//if (isValid(pastAndOpinions) == 0 || isValid(pegRatings) == 0) {
			if (isValid(pastRatings) == 0 || isValid(recRatings) == 0 || isValid(pegRatings) == 0) {
				
				fullRating =  BigDecimal.ZERO;
			} else {

				//ret = pastAndOpinions.add(pegRatings);
				ret = pastRatings.add(recRatings).add(pegRatings);
				//Integer nbV = isValid(pastAndOpinions)+isValid(pegRatings);
				Integer nbV = isValid(pastRatings)+isValid(recRatings)+isValid(pegRatings);
				fullRating = perCentOf(ret,nbV);
			}
		} 

		return fullRating;
	}
	

	public BigDecimal estimatedRating() {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal futurRating = recRating();
		BigDecimal pegRatings = pegRatings();
		
		//We want at least one valid future rating and one valid peg
		if (isValid(futurRating) == 0 || isValid(pegRatings) == 0) {
			return BigDecimal.ZERO;
		}
		
		ret = futurRating.add(pegRatings);
		Integer nbV = isValid(futurRating)+isValid(pegRatings);
		
		return perCentOf(ret,nbV);
	}
	

	public BigDecimal noDivFulRating() {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal perfs = quotePerfOverPeriod();
		BigDecimal futurRating = recRating();
		BigDecimal pegRatings = pegRatings();
		
		//We want at least one valid future rating and one valid peg
		if (isValid(futurRating) == 0 || isValid(pegRatings) == 0) {
			return BigDecimal.ZERO;
		}
		
		ret = futurRating.add(pegRatings).add(perfs);
		Integer nbV = isValid(futurRating)+isValid(pegRatings)+isValid(perfs);
		
		return perCentOf(ret,nbV);
	}
	
	public BigDecimal pastAndFuture() {
		
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal pastRating = pastRating();
		BigDecimal futurRating = recRating();
		
		//An invalid future rating invalidates the past and future rating
		if (isValid(futurRating) == 0) {
			return BigDecimal.ZERO;
		}
		
		ret = pastRating.add(futurRating);
		Integer nbV = isValid(pastRating)+isValid(futurRating);
		
		return perCentOf(ret,nbV);
	}
	
	public BigDecimal pegRatings() {
		
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal yahooPEGRating = yahooPEGRating();
		BigDecimal reutersPEGRating = reutersPEGRating();
		BigDecimal boursoPEGRating = boursoPEGRating();
		
		ret = yahooPEGRating.add(reutersPEGRating).add(boursoPEGRating);
		Integer nbV = isValid(yahooPEGRating)+isValid(reutersPEGRating)+isValid(boursoPEGRating);
		
		return perCentOf(ret,nbV);
	}

	private BigDecimal validated(BigDecimal value) {
		return (value == null || value.compareTo(BigDecimal.ZERO) == 0)?BigDecimal.ZERO.setScale(4):value;
	}
	
	private Integer isValid(BigDecimal value) {
		return (value == null || value.compareTo(BigDecimal.ZERO) == 0)?0:1;
	}
	
	private BigDecimal complementarPerCentOf(BigDecimal value, Integer factorUpLimit, Integer factorLowLimit) {
		BigDecimal range = new BigDecimal(factorUpLimit - factorLowLimit + 1);
		BigDecimal midle = range.divide(new BigDecimal(2),0,BigDecimal.ROUND_UP);
		
		value = (value == null || value.compareTo(BigDecimal.ZERO) == 0)?midle:value;
		
		return midle.subtract(value).divide(range,4,BigDecimal.ROUND_DOWN);
	}
	
	private BigDecimal perCentOf(BigDecimal value, Integer factor) {
		if (factor == null || factor == 0 || value == null || value.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		return value.divide(new BigDecimal(factor),4,BigDecimal.ROUND_DOWN);
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

	@Transient
	public BigDecimal getYahooPotentielPrice() {
		return pricePotential(this.getYahooTargetPrice());
	}

	@Transient
	public BigDecimal getBoursoPricePotentiel() {
		return pricePotential(this.getBoursoTargetPrice());
	}

	/**
	 * @param targetPrice 
	 * @return
	 */
	private BigDecimal pricePotential(BigDecimal targetPrice) {
		if (close == null  || targetPrice.compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		return targetPrice.subtract(this.close).divide(this.close, 4, RoundingMode.DOWN);
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
	
	public BigDecimal payoutRatio() {
		BigDecimal div = dividend; 
		if (div == null || div.compareTo(BigDecimal.ZERO) == 0) {
			if (reutersYield != null && close != null) {
				div = reutersYield.multiply(close);
			} else {
				div = BigDecimal.ZERO;
			}
		}
		
		return div.divide(avgEps(),2,BigDecimal.ROUND_DOWN);
	}
	
	private BigDecimal avgEps() {
		BigDecimal epsSum = validated(boursoBNA).add(validated(yahooEPS)).add(validated(reutersEPS));
		int nbv = isValid(boursoBNA)+isValid(yahooEPS)+isValid(reutersEPS);
		if (nbv == 0) return BigDecimal.ZERO; 
		return epsSum.divide(new BigDecimal(nbv),2,BigDecimal.ROUND_DOWN);
	}

	//PEG == PE / EPS Growth
	//PEG ratio is obtained by dividing the P/E ratio by the annual earnings growth rate. 
	//It is considered a form of normalization because higher growth rates should cause higher P/E ratios.
	public BigDecimal calculatePEG(BigDecimal pe, BigDecimal epsg) {
		if  (pe == null || epsg == null || epsg.compareTo(BigDecimal.ZERO) <= 0) {
			return BigDecimal.ZERO;
		}
		return pe.divide(epsg,2,RoundingMode.DOWN).movePointLeft(2);
	}
	
	private BigDecimal distanceRatioFromConstant(BigDecimal value, BigDecimal limiteBasseNeg, BigDecimal middleZero, BigDecimal limiteHautePos) {
		
			BigDecimal distanceRatioFromConstant;
			
			if (BigDecimal.ZERO.compareTo(value) == 0) {
				
				distanceRatioFromConstant = BigDecimal.ZERO;
				
			} else {
			
				BigDecimal ratio = BigDecimal.ZERO;
				BigDecimal totSpan = limiteHautePos.subtract(limiteBasseNeg);
				BigDecimal lowSpan = middleZero.subtract(limiteBasseNeg);
				BigDecimal highSpan = limiteHautePos.subtract(middleZero);
				BigDecimal direction = (totSpan.compareTo(BigDecimal.ZERO) < 0)?new BigDecimal(-1):BigDecimal.ONE;
				BigDecimal signedValue = value.multiply(direction);
				
				BigDecimal signedMiddle = middleZero.multiply(direction);
				
				if (signedValue.compareTo(signedMiddle) < 0) {
					if (signedValue.compareTo(limiteBasseNeg.multiply(direction)) > 0) {
						ratio = signedValue.subtract(signedMiddle).divide(lowSpan,2,BigDecimal.ROUND_DOWN);
					} else {
						distanceRatioFromConstant = BigDecimal.ONE.negate();
					}
				}
				if (signedValue.compareTo(signedMiddle) > 0) {
					if (signedValue.compareTo(limiteHautePos.multiply(direction)) < 0) {
						ratio = signedValue.subtract(signedMiddle).divide(highSpan,2,BigDecimal.ROUND_DOWN);
					} else {
						distanceRatioFromConstant = BigDecimal.ONE;
					}
				} 
				distanceRatioFromConstant = ratio.multiply(direction);
			
			}
		
		return distanceRatioFromConstant;
		
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

	@Transient
	public BigDecimal quotePerfOverPeriod() {

		if (this.quotePerfOverPeriod  == null) {

			if (this.close == null || ttmClose == null) {
				this.quotePerfOverPeriod = BigDecimal.ZERO;

			} else {
				BigDecimal diff = close.subtract(ttmClose);
				this.quotePerfOverPeriod = diff.divide(ttmClose, 4, BigDecimal.ROUND_DOWN);
			}
		}

		return this.quotePerfOverPeriod;

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

}
