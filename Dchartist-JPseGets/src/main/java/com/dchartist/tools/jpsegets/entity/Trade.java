/**
 *    Copyright 2017-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.dchartist.tools.jpsegets.entity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.dchartist.tools.jpsegets.util.TradeConfiguration;




/**
 * @author Sir John Nueva
 */
@Entity
@Table(name = "TRADE")
public class Trade implements java.io.Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 7654654691037091106L;
	//Name Symbol Bid Ask Open High Low Close Volume Value, Php
	public final static String DATE_FORMAT="yyyy-MM-dd";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "TRADING_DATE", nullable = false, length = 26)
	private Date tradingDate;
	
	@Column(name = "CO_NAME", length=50)
	private String name;
	
	@Column(name = "TRADE_SYMBOL", length=5)
	private String symbol;
	
	@Column(name = "BID", length=10)
	private Double bid;
	
	@Column(name = "ASK", length=10)
	private Double ask;
	
	@Column(name = "OPEN", length=10)
	private Double open;
	
	@Column(name = "HIGH", length=10)
	private Double high;
	
	@Column(name = "LOW", length=10)
	private Double low;
	
	@Column(name = "CLOSE", length=10)
	private Double close;
	
	@Column(name = "VOLUME", length=20)
	private Long volume;
	
	@Column(name = "VALUE", length=20)
	private Double value;
	
	@Column(name = "FOREIGN_VAL", length=20)
	private Double foreignValue;
	
	@Column(name = "FOREIGN_BUY", length=10)
	private Boolean foreignBuy;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	public Double getBid() {
		return bid;
	}
	public void setBid(Double bid) {
		this.bid = bid;
	}
	
	
	public Double getAsk() {
		return ask;
	}
	public void setAsk(Double ask) {
		this.ask = ask;
	}
	
	
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	
	
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	
	
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	
	
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	
	
	public Long getVolume() {
		return volume;
	}
	public void setVolume(Long volume) {
		this.volume = volume;
	}
	
	
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	
	public Double getForeignValue() {
		return foreignValue;
	}
	public void setForeignValue(Double foreign) {
		this.foreignValue = foreign;
	}
	
	
	public Boolean getForeignBuy() {
		return foreignBuy;
	}
	public void setForeignBuy(Boolean foreignBuy) {
		this.foreignBuy = foreignBuy;
	}

	
	public Date getTradingDate() {
		return tradingDate;
	}
	public void setTradingDate(Date date) {
		this.tradingDate = date;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\""+(tradingDate!=null?new SimpleDateFormat(Trade.DATE_FORMAT).format(tradingDate):"-")+"\",");
		sb.append("\""+(name!=null?name:"-")+"\",");
		sb.append("\""+(symbol!=null?symbol:"-")+"\",");
		sb.append("\""+(bid!=null?bid:"-")+"\",");
		sb.append("\""+(ask!=null?ask:"-")+"\",");
		sb.append("\""+(open!=null?open:"-")+"\",");
		sb.append("\""+(high!=null?high:"-")+"\",");
		sb.append("\""+(low!=null?low:"-")+"\",");
		sb.append("\""+(close!=null?close:"-")+"\",");
		sb.append("\""+(volume!=null?volume:"-")+"\",");
		sb.append("\""+(value!=null?value:"-")+"\",");
		sb.append("\""+(foreignValue!=null?foreignValue:"-")+"\",");
		sb.append("\""+(foreignBuy!=null?foreignBuy:"-")+"\",");

		return sb.toString();
	}
	

	
	public static List HeaderToArray(TradeConfiguration tc) throws Exception{
		


		List lst = new ArrayList();
		
		lst.add(tc.getDate());
		//lst.add(prop.get("name"));
		lst.add(tc.getSymbol());
		lst.add(tc.getBid());
		lst.add(tc.getAsk());
		lst.add(tc.getOpen());
		lst.add(tc.getHigh());
		lst.add(tc.getLow());
		lst.add(tc.getClose());
		lst.add(tc.getVolume());
		lst.add(tc.getValue());
		lst.add(tc.getForeignValue());
		//lst.add(prop.get("stockdailytrade_foreign_buy"));
		
		return lst;
	}
		
	public List toArray(){
		List lst = new ArrayList();

		lst.add(tradingDate);
		//lst.add(name!=null?name:"-");
		lst.add(symbol!=null?symbol:"-");
		lst.add(bid!=null?bid:"-");
		lst.add(ask!=null?ask:"-");
		lst.add(open!=null?open:"-");
		lst.add(high!=null?high:"-");
		lst.add(low!=null?low:"-");
		lst.add(close!=null?close:"-");
		lst.add(volume!=null?volume:"-");
		lst.add(value!=null?value:"-");
		
		
		if(foreignBuy!=null) {
			if(!foreignBuy.booleanValue()){
				lst.add(foreignValue!=null?(foreignValue*-1):"-");
			}else{
				if(foreignValue!=null){
					lst.add((foreignValue!=null?(foreignValue):"-"));
				}else{
					lst.add("-");
				}
			}
		}
            
		//lst.add(foreignBuy!=null?foreignBuy:"-");
		return lst;
	}
	
	
}
