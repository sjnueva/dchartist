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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
/**
 * @author Sir John Nueva
 */
public class TextPad {
	private BufferedWriter writer;
	private long timer;
	private String filename;
	private String content;
	public TextPad(){
		
	}
	public TextPad(String filename,boolean append) throws IOException{
		this.filename=filename;
		
		if(!append){
			writeClear();
		}
	}
	
	protected void open(boolean append) throws IOException{

		writer = new BufferedWriter(new FileWriter(filename,append));
	}
	
	public static boolean isFile(String filename){
		File f = new File(filename);
		return f.isFile();
	}
	public void writeClear(){
		try {
			open(false);
			writer.write ("");
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
	public void write(String msg){
			try {
				open(true);
				writer.write (msg);
				close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void writeComment(String msg) {
		try {
			open(true);
			writer.write ("#"+TextPad.DisplayTime(new Date(), null)+" | "+msg+"\n");
			close();
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	protected void close(){
		if(writer!=null){
			
			try {
				writer.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	public void startTimer(){
		this.timer = System.currentTimeMillis();
	}
	
	public String stopTimer(){
		long duration = System.currentTimeMillis() - timer;
		return String.format("%d min, %d sec", 
			    TimeUnit.MILLISECONDS.toMinutes(duration),
			    TimeUnit.MILLISECONDS.toSeconds(duration) - 
			    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));

	}
	
	public static String DisplayTime(Date date,String pattern){
		if(pattern==null)pattern="yyyy:dd:MM_HH:mm:ss";
		
		return new SimpleDateFormat(pattern).format(date);
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContent() throws IOException {
		if(isFile(filename)){
			this.content = FileUtil.ReadFileBuffered(filename);
		}else{
			this.content="";
		}
		
		return this.content;
	}

}
