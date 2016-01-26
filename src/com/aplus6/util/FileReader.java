package com.aplus6.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {
	private FileInputStream fsm;
	public FileReader(String path) throws FileNotFoundException{
		fsm = new FileInputStream(path);
	}
	@SuppressWarnings("deprecation")
	public String readAsString() throws IOException{
		DataInputStream dis = new DataInputStream(fsm);
		String result = "";
		String line = "";
		while((line = dis.readLine())!=null){
			result+=line;
		}
		return result;
	}
	public Byte[] readAsByte(){
		
		return null;
	}
	
}
