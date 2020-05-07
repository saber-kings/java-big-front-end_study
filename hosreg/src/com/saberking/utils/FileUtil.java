package com.saberking.utils;
 
 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
 
public class FileUtil {
	
	private static List<File> fileList = new ArrayList<>();
	
	public static boolean isClassFile(File file) {
		return isClassFile(file.getName());
	}
	
	public static boolean isClassFile(String fileName) {
		return fileName.endsWith(".class");
	}
	
	public static String getClassNameByFile(File file){
		String filePath = file.getAbsolutePath().replace("\\", "/");
		String className = filePath.split("bin/")[1].replaceAll("/", ".").replaceAll(".class", "");
		return className;
	}
	
 
 
	
	
	/**
	 * 递归查找当前类所在包下的所有文件
	 * @param rootPath
	 * @return
	 */
	public static List<File> getCurrentPathAllFile(String rootPath) {
		File[] files = new File(rootPath).listFiles();
 
 
		for (File file : files) {
			if (file.isDirectory()) {
				getCurrentPathAllFile(file.getAbsolutePath());
			} else if(isClassFile(file)){
				fileList.add(file);
			}
		}
		return fileList;
	}
	
	public static void main(String[] args) {
		System.out.println(isClassFile("jj.class.1"));
	}
}