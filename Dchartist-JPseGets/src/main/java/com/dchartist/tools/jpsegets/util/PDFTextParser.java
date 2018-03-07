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

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.util.PDFTextStripper;


/**
 * @author Sir John Nueva
 */
public class PDFTextParser {
	Logger log;
    PDFParser parser;
    String parsedText;
    PDFTextStripper pdfStripper;
    PDDocument pdDoc;
    COSDocument cosDoc;
    PDDocumentInformation pdDocInfo;
    
    // PDFTextParser Constructor 
    public PDFTextParser() {
    	
    }
    public PDFTextParser(Logger log) {
    	this.log=log;
    }
    
    // Extract text from PDF Document
    String pdftoText(String fileName) {
        
    	if(log!=null)log.info("Parsing text from PDF file " + fileName + "....");
        File f = new File(fileName);
        
        if (!f.isFile()) {
        	if(log!=null)log.info("File " + fileName + " does not exist.");
            return null;
        }
        
        try {
            parser = new PDFParser(new FileInputStream(f));
        } catch (Exception e) {
        	if(log!=null)log.info("Unable to open PDF Parser.");
            return null;
        }
        
        try {
            parser.parse();
            cosDoc = parser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc); 
        } catch (Exception e) {
        	if(log!=null)log.info("An exception occured in parsing the PDF Document.");
            e.printStackTrace();
            try {
                   if (cosDoc != null) cosDoc.close();
                   if (pdDoc != null) pdDoc.close();
               } catch (Exception e1) {
               e.printStackTrace();
            }
            return null;
        }      
        if(log!=null) log.info("Done.");
        return parsedText;
    }
    
    // Write the parsed text from PDF to a file
    void writeTexttoFile(String pdfText, String fileName) {
    	
    	if(log!=null)log.info("\nWriting PDF text to output text file " + fileName + "....");
    	try {
    		PrintWriter pw = new PrintWriter(fileName);
    		pw.print(pdfText);
    		pw.close();    	
    	} catch (Exception e) {
    		if(log!=null)log.info("An exception occured in writing the pdf text to file.");
    		e.printStackTrace();
    	}
    	if(log!=null)log.info("Done.");
    }
    
    //Extracts text from a PDF Document and writes it to a text file
    public static void main(String args[]) {
    	
    	if (args.length != 2) {
        	System.out.println("Usage: java PDFTextParser <InputPDFFilename> <OutputTextFile>");
        	System.exit(1);
        }
        
        PDFTextParser pdfTextParserObj = new PDFTextParser();
        String pdfToText = pdfTextParserObj.pdftoText(args[0]);
        
        if (pdfToText == null) {
        	System.out.println("PDF to Text Conversion failed.");
        }
        else {
        	System.out.println("\nThe text parsed from the PDF Document....\n" + pdfToText);
        	pdfTextParserObj.writeTexttoFile(pdfToText, args[1]);
        }
    }  
}