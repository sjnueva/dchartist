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
package com.dchartist.tools.jpsegets;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dchartist.tools.jpsegets.entity.Trade;
import com.dchartist.tools.jpsegets.util.ApplicationUtil;
import com.dchartist.tools.jpsegets.util.FileUtil;
import com.dchartist.tools.jpsegets.util.PdfCompletedListTextPad;


import com.dchartist.tools.jpsegets.util.TextPad;
import com.dchartist.tools.jpsegets.util.TradeConfiguration;
import com.dchartist.tools.jpsegets.util.TradeManager;
/**
 * @author Sir John Nueva
 */
@SpringBootApplication
public class DchartistJPseGetsApplication  implements CommandLineRunner {
	@Autowired
	TradeConfiguration tconf;
	
	public static void main(String[] args) {
		SpringApplication.run(DchartistJPseGetsApplication.class, args);
		
		
	}
	
	Logger						logger				= Logger.getLogger(getClass().getName());
	
	@Override
	public void run(String... args) 
	{
		PdfCompletedListTextPad pdfCompletedListTp = null;
		Logger applog = null;

		int numberOfProcessedFile = 0;
		try {
			

			String filenamelog = ApplicationUtil.PrepareFileName(
					tconf.getLogsFolder() , 
					DchartistJPseGetsApplication.class.getName(), 
					tconf.getExtFilenameLog() );
			
			applog = Logger.getLogger((filenamelog));
			
			String folderName =tconf.getPdfToTextInputFolder();
			String[] files = FileUtil.ReadDirectoryPDFOnly(folderName);
			if(files!=null && files.length!=0){
				applog.info("Directory : "+folderName);
				applog.info("Number of files : "+files.length );

		
				String pdfCompletedList_filename =tconf.getPdfCompletedListFilename() ;
				//clean output directory
				if(!TextPad.isFile(pdfCompletedList_filename)){
					FileUtil.DeleteContentFiles(tconf.getPdfToTextOutputFolder());
					pdfCompletedListTp = new PdfCompletedListTextPad(pdfCompletedList_filename,false);
					pdfCompletedListTp.writeComment("New pdf completed list.");
				}else{
					pdfCompletedListTp = new PdfCompletedListTextPad(pdfCompletedList_filename,true);
					pdfCompletedListTp.writeComment("Appended to pdf completed list.");
				}
				
				int i = 0;
				while(i < files.length)
				{
					
					applog.info(i+" : "+files[i]);
					String pdfFilename=files[i];
					String pdfFilenameAbsolutePath = folderName+File.separatorChar+pdfFilename;
					//validate if the file is a valid pdf file
					if(validateFiles(folderName,files[i],applog) && !pdfCompletedListTp.checkIfProcessed(pdfFilename)){
						applog.info("Processing "+files[i]);
						//Extract text from the pdf file put put to S
						pdfCompletedListTp.startTimer();
						
						String textContent =DchartistJPseGetsApplication.PDFTextParser(pdfFilenameAbsolutePath,applog);
						
						String filenameabsolutepath = ApplicationUtil.PrepareFileName(
								tconf.getPdfToTextOutputFolder(),
								removeExt(files[i]), 
								tconf.getExtFilenameCsv());
						
	
						
						//Extract list of StockDailyTrade object from the textContent
						List<Trade> stocks = filterText(textContent,extractDate(files[i]));
						//Write the list of StockDailyTrade to CSV fomat file
						FileUtil.WriteTexttoFileBuffered(filenameabsolutepath, listTostring(stocks));
						numberOfProcessedFile++;
						
						pdfCompletedListTp.markFileAsCompleted(pdfFilename,filenameabsolutepath);
						
					}else{
						applog.info("Skip "+files[i]);
					}
					
					i++;
				}

			}else{
				applog.info("There is no file to process.");
			
			}
			applog.info("Number of processed file: "+numberOfProcessedFile);
		} catch (Exception e) {
			applog.info("Error:"+e.getMessage());
			
			e.printStackTrace();
		}finally{
			
			//if(applog!=null)applog.writeClose();

		}
		


	}







	private String removeExt(String string) {
		return string.substring(0, string.lastIndexOf("."));
	}


	private Date extractDate(String filename) {
		String[] arr = filename.split("_");
		Date date = null;
		try {
			//System.out.println(arr[1]);
			date = new SimpleDateFormat("MMddyyyy", Locale.ENGLISH).parse(arr[1]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	private String listTostring(List<Trade> stocks) {
		StringBuilder sb = new StringBuilder();
		
		for(Trade stock:stocks){
			sb.append(stock.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	private boolean validateFiles(String location,String filename,Logger log)
	{	
		boolean valid = true;
		//Is it a pdf file
		if(".PDF".equalsIgnoreCase(filename.trim().substring(
				( filename.trim().length()-4), filename.trim().length()))){
			//log.write(filename+" is a PDF file");
			
		}else{
			valid =false;
		}
		// is it a file and not directory
		File f = new File(location +File.separatorChar+filename);
		if( f.exists() && f.isFile())
		{
			//log.write(filename+" is a file");
		}else{
			valid =false;
		}
		//is there a date in filename
		String[] fString  = filename.split("[_.]");
		if(fString.length>=2){
			log.info("Date : " + TradeManager.StringToDateDDMMYYYY(fString[1]));
		}else{
			valid = false;
		}
		
		return valid;
	}
	

	
	
	
    public static String PDFTextParser(String fileName,Logger log) {
        PDFParser parser = null;
        String parsedText = null;
        PDFTextStripper pdfStripper = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDDocumentInformation pdDocInfo = null;
        
        log.info("Parsing text from PDF file " + fileName + "....");
        File f = new File(fileName);
        
        if (!f.isFile()) {
            log.info("File " + fileName + " does not exist.");
            return null;
        }
        
        try {
            parser = new PDFParser(new FileInputStream(f));
        } catch (Exception e) {
            log.info("Unable to open PDF Parser.");
            return null;
        }
        
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc); 
        } catch (Exception e) {
            log.info("An exception occured in parsing the PDF Document.");
            e.printStackTrace();
            try {
                   if (cosDoc != null) cosDoc.close();
                   if (pdDoc != null) pdDoc.close();
               } catch (Exception e1) {
               e.printStackTrace();
            }

        }      
        log.info("Done.");
        return parsedText;
    }


	
	private static List<Trade> filterText(String s,Date date) {
		List<Trade> stocks = new ArrayList<Trade>();
		
		String[] arr = s.split("\n");

		for(String obj:arr){
			Trade stockDailyTrade =TradeManager.Instantiate(obj.trim(), date);
			if(stockDailyTrade!=null)
			{
				stocks.add(stockDailyTrade);
			}else{
				//System.out.println("&&&&&&&&&&SKIP="+obj);
			}
		}
		return stocks;
	}
}
