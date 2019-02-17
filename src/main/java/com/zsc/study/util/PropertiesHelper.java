package com.zsc.study.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
 * @author bin.song
 *
 */
public class PropertiesHelper {

	public static Object getNotEnvProperties(String key, String proFileName) {
		String classPath = FileHelper.getClassPath();
		File file = new File(classPath + "conf/custom/notenv/" + proFileName + ".properties");
		Properties pro = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			pro.load(is);
		} catch (Exception e) {
			return "null";
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return pro.get(key);
	}

	public static Object getEnvProperties(String key, String proFileName) {
		String classPath = FileHelper.getClassPath();

		File file = new File(classPath + "conf/custom/env/" + proFileName + ".properties");
		Properties pro = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			pro.load(is);
		} catch (Exception e) {
			return "null";
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
		}
		return pro.get(key);
	}

	/**
	 * 
	 * 获取相应文件中的某个keyValue
	 *
	 * @param fileName
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getNotEnvValueByKey(String fileName, String key, String defaultValue) {
		String value = null;
		try {
			value = getBundle(fileName).getString(key);
		} catch (Exception e) {
			value = defaultValue;
		}
		return value;
	}

	/** 
	 * 得到配置文件资源 
	 *
	 * @return 得到配置文件资源
	 */
	private static ResourceBundle getBundle(String resourcePath) {
		return ResourceBundle.getBundle(resourcePath);
	}
}
