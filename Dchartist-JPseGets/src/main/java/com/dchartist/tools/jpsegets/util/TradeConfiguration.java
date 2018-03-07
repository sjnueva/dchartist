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

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Sir John Nueva
 */
@Component
@PropertySource("classpath:Trade.properties")
@PropertySource("classpath:RunPdfExporterToCsv.properties")
@ConfigurationProperties
public class TradeConfiguration {
			String date=null;
			String name=null;
			String symbol=null;
			String bid=null;
			String ask=null;
			String open=null;
			String high=null;
            String low=null;
			String close=null;
			String volume=null;
			String value=null;
			String foreignValue=null;
			String foreignBuy=null;
			
			
			String logsFolder=null;
			String outputFolder=null;
			String pdfToTextOutputFolder=null;
			String pdfToTextInputFolder=null;
			String pdfCompletedListFilename=null;
			String extFilenameLog=null;
			String extFilenameCsv=null;
			
			
			
			
			
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
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
			public String getBid() {
				return bid;
			}
			public void setBid(String bid) {
				this.bid = bid;
			}
			public String getAsk() {
				return ask;
			}
			public void setAsk(String ask) {
				this.ask = ask;
			}
			public String getOpen() {
				return open;
			}
			public void setOpen(String open) {
				this.open = open;
			}
			public String getHigh() {
				return high;
			}
			public void setHigh(String high) {
				this.high = high;
			}
			public String getLow() {
				return low;
			}
			public void setLow(String low) {
				this.low = low;
			}
			public String getClose() {
				return close;
			}
			public void setClose(String close) {
				this.close = close;
			}
			public String getVolume() {
				return volume;
			}
			public void setVolume(String volume) {
				this.volume = volume;
			}

			public String getValue() {
				return value;
			}
			public void setValue(String value) {
				this.value = value;
			}
			public String getForeignValue() {
				return foreignValue;
			}
			public void setForeignValue(String foreignValue) {
				this.foreignValue = foreignValue;
			}
			public String getForeignBuy() {
				return foreignBuy;
			}
			public void setForeignBuy(String foreignBuy) {
				this.foreignBuy = foreignBuy;
			}
			public String getLogsFolder() {
				return logsFolder;
			}
			public void setLogsFolder(String logsFolder) {
				this.logsFolder = logsFolder;
			}
			public String getOutputFolder() {
				return outputFolder;
			}
			public void setOutputFolder(String outputFolder) {
				this.outputFolder = outputFolder;
			}
			public String getPdfToTextOutputFolder() {
				return pdfToTextOutputFolder;
			}
			public void setPdfToTextOutputFolder(String pdfToTextOutputFolder) {
				this.pdfToTextOutputFolder = pdfToTextOutputFolder;
			}
			public String getPdfToTextInputFolder() {
				return pdfToTextInputFolder;
			}
			public void setPdfToTextInputFolder(String pdfToTextInputFolder) {
				this.pdfToTextInputFolder = pdfToTextInputFolder;
			}
			public String getPdfCompletedListFilename() {
				return pdfCompletedListFilename;
			}
			public void setPdfCompletedListFilename(String pdfCompletedListFilename) {
				this.pdfCompletedListFilename = pdfCompletedListFilename;
			}
			public String getExtFilenameLog() {
				return extFilenameLog;
			}
			public void setExtFilenameLog(String extFilenameLog) {
				this.extFilenameLog = extFilenameLog;
			}
			public String getExtFilenameCsv() {
				return extFilenameCsv;
			}
			public void setExtFilenameCsv(String extFilenameCsv) {
				this.extFilenameCsv = extFilenameCsv;
			}
}
