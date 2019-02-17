package com.zsc.study.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

/**
 * 文件工具
 * @author bin.song
 *
 */
public class FileHelper {
	
	/**
	 * 获取文件内容
	 * @param filename
	 * 文件全路经
	 * @return
	 */
	@SuppressWarnings("resource")
	public static String getFileBuffer(String filename){
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		try {						
			FileInputStream fis = new FileInputStream(filename); 
	        InputStreamReader isr = new InputStreamReader(fis, "UTF-8"); 
	        reader = new BufferedReader(isr); 
	        /*File file = new File(filename);
			FileReader fileReader = new FileReader(file);
			reader = new BufferedReader(fileReader);*/
			String line = null;
			while(( line = reader.readLine()) != null){
				sb.append(line);
				sb.append("\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
			//LogHelper.Log("get " + filename + " error, detail message : "+ e.getMessage()); 
		}
		return sb.toString();
	}
	
	/**
	 * 创建文件
	 * @throws IOException 
	 */
	public static boolean createFile(String filePath, String content) throws IOException {
		//检查文件
		File file = new File(filePath);
		if(!file.exists()) {
			boolean success = file.createNewFile();
			if(!success) {
				return false;
			}
		}
		//写文件		
		FileOutputStream fileOutput = new FileOutputStream(file);
        PrintWriter fileWrite = new PrintWriter(new OutputStreamWriter(fileOutput, "UTF-8"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
        bufferedWriter.write(content);
        bufferedWriter.close(); 
		/*FileWriter fileWriter = new FileWriter(file, false);
		fileWriter.write(content);
		fileWriter.close();*/	
        
		return true;
	}	

	/**
	 * 获取classpath
	 * @return
	 */
	public static String getClassPath(){
		URL url = FileHelper.class.getResource("/");
		String classpath = url.getPath();
		return classpath;
	}
}
