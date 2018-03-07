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

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 * @author Sir John Nueva
 */
public class ApplicationUtil {
	static Logger						logger				= Logger.getLogger(ApplicationUtil.class.getName());

	public static Properties ReadProperties(String fileName,boolean inClasspath) throws Exception{
		Properties props = null;
		try{
			props = new Properties();
			if(inClasspath){
				URL url = ClassLoader.getSystemResource(fileName);
				if(url!=null){
					props.load(url.openStream());
				}else{
					throw new Exception("Unable to find "+fileName);
				}
			}else{
				props.load(new FileInputStream(fileName));
			}
	        
	    } catch(Exception e){  
	    	System.out.println("Check for "+fileName);
	    	logger.log(Level.SEVERE,"Check for "+fileName);
	    	e.printStackTrace();
		  	throw e;
	    }
		return props;
	}
	
	public static String PrepareFileName(String outputlocation,String basefilename, String extension){
		String retbasefilename=null;

		retbasefilename=outputlocation + File.separatorChar+ basefilename+"_"+TodaysDateToString()+extension;
		
		if(isExistingFile(retbasefilename)){
			renameOldFile(retbasefilename,extension) ;
		}

		return retbasefilename;
		
	}
	
	private static void deleteIfExist(String oldfilename) {
		File file = new File(oldfilename);
		if(file.isFile()){
			file.delete();
		}
		
	}

	private static void renameOldFile(String newfilename,String extension) {
		String testFilename = newfilename;
		int runningSuffix = 0;
		while(isExistingFile(testFilename)){
			runningSuffix++;
			testFilename = newfilename.replace(extension, "")+"_"+runningSuffix+extension;
		}
		
		if(runningSuffix!=0){
			File file = new File(newfilename);
			file.renameTo(new File(newfilename.replace(extension, "")+"_"+runningSuffix+extension));
		}

	}
	public static boolean isExistingFile(String newfilename) {
		return (new File(newfilename)).isFile();
	}

	
    private static String TodaysDateToString(){
		SimpleDateFormat format =
		         new SimpleDateFormat("yyyy-MM-dd");
				String parsedDate=format.format(new Date());
				return parsedDate;
    }
    
    
    
    
	
}
