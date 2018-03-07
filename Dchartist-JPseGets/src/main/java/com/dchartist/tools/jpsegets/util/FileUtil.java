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

 * All rights reserved. 
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;



/**
 * @author Sir John Nueva
 */
public class FileUtil {
	static Logger						logger				= Logger.getLogger(FileUtil.class.getName());

	public static String[] ReadDirectoryPDFOnly(String filename)
	{
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               if(name.lastIndexOf('.')>0)
               {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  // get extension
                  String str = name.substring(lastIndex);
                  // match path name extension
                  if(str.equalsIgnoreCase(".pdf"))
                  {
                     return true;
                  }
               }
               return false;
            }
            
         };

		File in = new File(filename);
		return in.list(fileNameFilter);
	}
	
	public static String[] ReadDirectoryCSVOnly(String filename)
	{
        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
               if(name.lastIndexOf('.')>0)
               {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  // get extension
                  String str = name.substring(lastIndex);
                  // match path name extension
                  if(str.equalsIgnoreCase(".csv"))
                  {
                     return true;
                  }
               }
               return false;
            }
            
         };

		File in = new File(filename);
		return in.list(fileNameFilter);
	}
	
	public static String ReadFileBuffered(String filename) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader in=null;FileReader fr=null;
	    try {
	    	String sCurrentLine =null;
	    	fr = new FileReader(filename);
	        in = new BufferedReader(fr);
	        while ((sCurrentLine = in.readLine()) != null) {
	        	sb.append(sCurrentLine+"\n");
	        }
	    } catch (IOException e) {
	        throw e;
	    }finally{
	    	
	    	if(in!=null){
		    	 try{in.close();}catch(IOException e){}
		    	 in=null;
	    	}
	    	
	    	if(fr!=null){
		    	 try{fr.close();}catch(IOException e){}
		    	 fr=null;
	    	}

	    	
	    }
	    return sb.toString();
	}
	


	
    public static void WriteTexttoFileBuffered(String filename, String textcontent) {
        File logFile=new File(filename);
        BufferedWriter writer = null;
    	try {
    		writer =  new BufferedWriter(new FileWriter(logFile));
    		writer.write(textcontent);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally{
    		if(writer!=null){
    			
    			try {
    				writer.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    			
    			try {

    				writer.close();
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
    		}
    	}
    }
    
    public static void WriteTexttoFile(String filename, String textcontent) {
    	
    	PrintWriter writer = null;
    	try {
    		writer = new PrintWriter(filename);
    		writer.print(textcontent);   	
    	} catch (Exception e) {
    		e.printStackTrace();
    	}finally{
    		if(writer!=null){
    			
    			try {
    				writer.flush();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    			try {

    				writer.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			
    		}
    	}
    	System.out.println("Done.");
    }

	public static void DeleteContentFiles(String filename) {
		File dir = new File(filename);
		for(File file: dir.listFiles())
		{
			if(!file.isDirectory()) {
				logger.info("deleting .... "+file.getAbsolutePath());
				file.delete();
			}
		}
		
	}
    
}
