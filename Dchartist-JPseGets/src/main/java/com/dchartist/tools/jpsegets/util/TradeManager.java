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
package com.dchartist.tools.jpsegets.util;
/**

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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import com.dchartist.tools.jpsegets.entity.Trade;


/**
 * @author Sir John Nueva
 */
public class TradeManager {
	static Logger						logger				= Logger.getLogger(TradeManager.class.getName());
	
	public static Trade Instantiate(String obj) {
		Trade sdt = new Trade();
		String[] arr = obj.split("\",\"");
		//"2014-07-14","ASIA UNITED ","AUB","71.6","71.9","71.95","71.95","71.0","71.9","21010","1510334.5","474340.0","false",
		if(arr.length==13){
			
			//sdt.setId(arr[0].replace("\"", "") + "|"+arr[2]);
			
			sdt.setTradingDate(StringToDateStockDailyTrade(arr[0].replace("\"", "")));
			sdt.setName(arr[1]);
			sdt.setSymbol(arr[2]);
			sdt.setBid(ConvertDoubleNumber(arr[3]));
			sdt.setAsk(ConvertDoubleNumber(arr[4]));
			sdt.setOpen(ConvertDoubleNumber(arr[5]));
			sdt.setHigh(ConvertDoubleNumber(arr[6]));
			sdt.setLow(ConvertDoubleNumber(arr[7]));
			sdt.setClose(ConvertDoubleNumber(arr[8]));
			sdt.setVolume(ConvertLongNumber(arr[9]));
			sdt.setValue(ConvertDoubleNumber(arr[10]));
			sdt.setForeignValue(ConvertDoubleNumber(arr[11]));
			
			//logger.debug(cleanNumber(arr[12]).replace("\"", "").replace("-", ""));
			if(cleanNumber(arr[12]).replace("\"", "").replace("-", "").trim().equalsIgnoreCase("true")){
				sdt.setForeignBuy(true);
			}else{
				sdt.setForeignBuy(false);
			}
		}
		
		return sdt;
	}
	
//	public String toString(){
//		StringBuilder sb = new StringBuilder();
//		sb.append("\""+(date!=null?new SimpleDateFormat("yyyy-MM-dd").format(date):"-")+"\",");
//		sb.append("\""+(name!=null?name:"-")+"\",");
//		sb.append("\""+(symbol!=null?symbol:"-")+"\",");
//		sb.append("\""+(bid!=null?bid:"-")+"\",");
//		sb.append("\""+(ask!=null?ask:"-")+"\",");
//		sb.append("\""+(open!=null?open:"-")+"\",");
//		sb.append("\""+(high!=null?high:"-")+"\",");
//		sb.append("\""+(low!=null?low:"-")+"\",");
//		sb.append("\""+(close!=null?close:"-")+"\",");
//		sb.append("\""+(volume!=null?volume:"-")+"\",");
//		sb.append("\""+(value!=null?value:"-")+"\",");
//		sb.append("\""+(foreign!=null?foreign:"-")+"\",");
//		sb.append("\""+(foreignBuy!=null?foreignBuy:"-")+"\",");
//
//		return sb.toString();
//	}
	
	/**
	 * Instantiate use when reading pdf to csv
	 * @param specimen
	 * @param date
	 * @return
	 */
	public static Trade Instantiate(String specimen, Date date)
	{
		Trade stockDailyTrade = null;
		String[] arr = specimen.split("\\s+");
		
		if((arr.length>=11)) // at least 11 number of column
		{
			int arrsize = arr.length;
			//Name Symbol Bid Ask Open High Low Close Volume Value, Php
			if(arr[arrsize-1].trim().length()!=0 && // valid number format
					ValidNumber(arr[arrsize-1].trim()) &&
					ValidNumber(arr[arrsize-2].trim()) &&
					ValidNumber(arr[arrsize-3].trim()) &&
					ValidNumber(arr[arrsize-4].trim()) &&
					ValidNumber(arr[arrsize-5].trim()) &&
					ValidNumber(arr[arrsize-6].trim()) &&
					ValidNumber(arr[arrsize-7].trim()) &&
					ValidNumber(arr[arrsize-8].trim()) &&
					ValidNumber(arr[arrsize-9].trim()) )
				{
					
				stockDailyTrade = new Trade();
				
				String ForeignBuy = arr[arrsize-1].trim();
				logger.info(ForeignBuy);
				
				if(ForeignBuy.equalsIgnoreCase("=")){
					stockDailyTrade.setForeignBuy(true);
				}else if(ForeignBuy.contains("(") || ForeignBuy.contains(")")){
					stockDailyTrade.setForeignBuy(false);
				}else{
					stockDailyTrade.setForeignBuy(true);
				}
				
				stockDailyTrade.setForeignValue(ConvertDoubleNumber(arr[arrsize-1].trim()));
				stockDailyTrade.setValue(ConvertDoubleNumber(arr[arrsize-2].trim()));
				stockDailyTrade.setVolume(ConvertLongNumber(arr[arrsize-3].trim()));
				stockDailyTrade.setClose(ConvertDoubleNumber(arr[arrsize-4].trim()));
				stockDailyTrade.setLow(ConvertDoubleNumber(arr[arrsize-5].trim()));
				stockDailyTrade.setHigh(ConvertDoubleNumber(arr[arrsize-6].trim()));
				stockDailyTrade.setOpen(ConvertDoubleNumber(arr[arrsize-7].trim()));
				stockDailyTrade.setAsk(ConvertDoubleNumber(arr[arrsize-8].trim()));
				stockDailyTrade.setBid(ConvertDoubleNumber(arr[arrsize-9].trim()));
				
				stockDailyTrade.setSymbol(arr[arrsize-10].trim());
				stockDailyTrade.setTradingDate(date);
				
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<=(arrsize-11);i++)
				{
					sb.append(arr[i].trim()+" ");
				}
				stockDailyTrade.setName(sb.toString());
			}
		}

		return stockDailyTrade;
	}
	
	private static String cleanNumber(String num) {
		String retval=null;
		if(num.trim().equals("-")){
			retval=null;
		} else {
			retval = num.replace(",", "").replace("(", "").replace(")", "");
		}
		return retval;
	}
	
	private static Float ConvertFloatNumber(String num) {
		String retval = cleanNumber(num);
		return ( retval==null?null:Float.parseFloat(retval));
	}

	private static Long ConvertLongNumber(String num) {
		String retval = cleanNumber(num);
		return ( retval==null?null:Long.parseLong(retval));
	}



	private static Double ConvertDoubleNumber(String num) {
		String retval = cleanNumber(num);
		return ( retval==null?null:Double.parseDouble(retval));
	}

	public static boolean ValidNumber(String specimen) {
		return specimen.trim().length()!=0 && specimen.matches("[0-9|\\.|\\,|\\(|\\)|-]*");
	}

	public static Date StringToDateDDMMYYYY(String sdate)
	{
		Date date = null;
		try {
			date =new SimpleDateFormat("ddmmyyyy", Locale.ENGLISH).parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date StringToDateStockDailyTrade(String sdate)
	{
		
		Date date = null;
		if(sdate!=null){
			if(!sdate.trim().equals("-") && sdate.trim().length()!=0){
				try {
					date =new SimpleDateFormat(Trade.DATE_FORMAT, Locale.ENGLISH).parse(sdate);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return date;
	}
	
	public static Map ArrayGenerator(List<Trade> stocks){
		Map map = new HashMap();
		int idx = stocks.size();
		double[] volume = new double[idx];
		double[] open = new double[idx];
		double[] close = new double[idx];
		double[] high = new double[idx];
		double[] low = new double[idx];
		Date[] date = new Date[idx];
		
		double[] bid = new double[idx];
		double[] ask = new double[idx];
		double[] value = new double[idx];
		double[] foreignValue = new double[idx];

		
		int  ctr = 0;
		for(Trade t : stocks)
		{
			if(t!=null){
				
				logger.info(t.toString());
				if(ctr==0){
					map.put(PdfToCsvConstant.MAP_KEY_SYMBOL, t.getSymbol());
					map.put(PdfToCsvConstant.MAP_KEY_NAME, t.getName());
				}
				volume[ctr]=validateDouble((t.getVolume()!=null?t.getVolume().doubleValue():new Double(0)));
				open[ctr]=validateDouble(t.getOpen());
				close[ctr]=validateDouble(t.getClose());
				high[ctr]=validateDouble(t.getHigh());
				low[ctr]=validateDouble(t.getLow());
				date[ctr] = t.getTradingDate();
				
				bid[ctr] = validateDouble(t.getBid());
				ask[ctr] = validateDouble(t.getAsk());
				value[ctr]= validateDouble(t.getValue());
				
				if(t.getForeignBuy()){
					foreignValue[ctr]= validateDouble(t.getForeignValue());
				}else{
					foreignValue[ctr]= validateDouble(-1*t.getForeignValue());
				}
						
				ctr++;
			}
		}
		map.put(PdfToCsvConstant.MAP_KEY_VOLUME, volume);
		map.put(PdfToCsvConstant.MAP_KEY_OPEN, open);
		map.put(PdfToCsvConstant.MAP_KEY_CLOSE, close);
		map.put(PdfToCsvConstant.MAP_KEY_HIGH, high);
		map.put(PdfToCsvConstant.MAP_KEY_LOW, low);
		
		map.put(PdfToCsvConstant.MAP_KEY_TRADING_DATE, date);
		
		map.put(PdfToCsvConstant.MAP_KEY_BID, bid);
		map.put(PdfToCsvConstant.MAP_KEY_ASK, ask);
		map.put(PdfToCsvConstant.MAP_KEY_VALUE, value);
		map.put(PdfToCsvConstant.MAP_KEY_FOREIGN_VALUE, foreignValue);
		
		
		return map;
	}
	private static double validateFloat(Float open) {
		double ret = 0;
		if(open!=null){
			ret = open.floatValue();
		}
		return ret;
	}
	
	private static double validateDouble(Double open) {
		double ret = 0;
		if(open!=null){
			ret = open.floatValue();
		}
		return ret;
	}

	public static String ArrayGeneratorString(Map map){
		StringBuilder sb = new StringBuilder();
		sb.append(PdfToCsvConstant.MAP_KEY_SYMBOL+"="+map.get(PdfToCsvConstant.MAP_KEY_SYMBOL)+"\n");
		sb.append(PdfToCsvConstant.MAP_KEY_TRADING_DATE+"="+ArrayDateToString((Date[])map.get(PdfToCsvConstant.MAP_KEY_TRADING_DATE))+"\n");
		sb.append(PdfToCsvConstant.MAP_KEY_OPEN+"="+ArrayToString((double[])map.get(PdfToCsvConstant.MAP_KEY_OPEN))+"\n");
		sb.append(PdfToCsvConstant.MAP_KEY_HIGH+"="+ArrayToString((double[])map.get(PdfToCsvConstant.MAP_KEY_HIGH))+"\n");
		sb.append(PdfToCsvConstant.MAP_KEY_LOW+"="+ArrayToString((double[])map.get(PdfToCsvConstant.MAP_KEY_LOW))+"\n");
		sb.append(PdfToCsvConstant.MAP_KEY_CLOSE+"="+ArrayToString((double[])map.get(PdfToCsvConstant.MAP_KEY_CLOSE))+"\n");
		return sb.toString();
	}
	
	private static String ArrayToString(double[] arr){
		StringBuilder sb = new StringBuilder();
		
		for(double d:arr){
			sb.append(d+"|");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
	private static String ArrayDateToString(Date[] arr){
		StringBuilder sb = new StringBuilder();
		
		for(Date d:arr){
			sb.append(new SimpleDateFormat(Trade.DATE_FORMAT).format(d)+"|");
		}
		sb.delete(sb.length()-1, sb.length());
		return sb.toString();
	}
}
