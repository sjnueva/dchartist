package com.dchartist.tools.jpsegets.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeConfigurationTest {
	
	final static String MARK="------------------------------------------------------------------------";
	@Autowired
	TradeConfiguration tc;
	@Test
	public void displayConfiguration() {
		
		Logger logger = Logger.getLogger("TEST");

		
		Assert.notNull(tc);

		logger.info(MARK);
		logger.info("Configuration");
		logger.info(MARK);
		//#Common
		//logs_folder=D:\\DChartist-MasterTradingSystem\\logs
		logger.info("logs_folder="+tc.getLogsFolder());
		//output_folder=D:\\DChartist-MasterTradingSystem\\output
		logger.info("output_folder="+tc.getOutputFolder());
		//pdf_to_text_output_folder=D:\\DChartist\\output\\files
		logger.info("pdf_to_text_output_folder="+tc.getPdfToTextOutputFolder());
		//pdf_to_text_input_folder=D:\\DChartist\\input\\files2015
		logger.info("pdf_to_text_input_folder="+tc.getPdfToTextInputFolder());
		//#PDFUtil
		//pdfCompletedList_filename=D:\\DChartist\\output\\pdfCompletedList.txt
		logger.info("pdfCompletedList_filename="+tc.getPdfCompletedListFilename());
		//ext_filename_log=.log
		logger.info("ext_filename_log="+tc.getExtFilenameLog());
		//ext_filename_csv=.csv
		logger.info("ext_filename_csv="+tc.getExtFilenameCsv());
		logger.info(MARK);
		
		
	}
}

