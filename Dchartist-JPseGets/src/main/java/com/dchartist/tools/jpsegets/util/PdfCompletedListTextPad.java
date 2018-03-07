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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 * @author Sir John Nueva
 */
public class PdfCompletedListTextPad extends TextPad {
	Logger						logger				= Logger.getLogger(getClass().getName());
	List completeList;
	public PdfCompletedListTextPad(String pdfCompletedList_filename,boolean append) throws IOException {
		super(pdfCompletedList_filename,append);
		initiate();
		
	}
	
	public void open(boolean append) throws IOException{
		super.open(append);
		initiate();
	}
	
	private void initiate() throws IOException {
		completeList = new ArrayList();
		
		String[] arr = this.getContent().split("\n");

		for(String obj:arr){
			if(!(obj.trim().substring(0).equalsIgnoreCase("#")) && obj.trim().length()!=0){
				completeList.add(obj);
			}
			

		}
	}

	public boolean checkIfProcessed(String pdfFilename) {
		boolean ret = false;
		for(Object s:completeList){//s[0]= pdffilename s[1]= timestamp s[3]=output file
			
			String pdf = (((String)s).split(","))[0];
			this.logger.info("s="+s);
			this.logger.info("pdf="+pdf);
			if(pdf.equals(pdfFilename)){
				ret = true;
			}
		}
		return ret;
	}



	public void markFileAsCompleted(String pdfFilename, String filename) throws IOException {
		this.write(pdfFilename+","+TextPad.DisplayTime(new Date(), null)+ ","+filename+"\n");
	}
	
}
