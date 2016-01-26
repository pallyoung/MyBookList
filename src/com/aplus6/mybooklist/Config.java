package com.aplus6.mybooklist;

import java.io.File;
import java.io.IOException;

import com.aplus6.mybooklist.servieces.DBServices;
import com.aplus6.mybooklist.servieces.MBookListManager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Config {
	public  Context CTX;
	public  String LOG_PATH;
	public  String DATA_PATH;
	public  String FILE_PATH;
	public  String list_file;
	public Config(Context ctx){
		init(ctx);
	}
	public  void init(Context ctx){
		CTX = ctx;
		FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+"/mybooklist"; 
		DATA_PATH = FILE_PATH+"/data";
		LOG_PATH = FILE_PATH+"/logs";
		list_file = DATA_PATH +"/list";
	}	
	public void checkEnv(){
		DBServices.init(CTX);
		MBookListManager.init();
		File f2 = new File(DATA_PATH);
		if(!f2.exists()){
			f2.mkdirs();
		}
		File f3 = new File(LOG_PATH);
		if(!f3.exists()){
			f3.mkdirs();
		}
		File f4 = new File(list_file);
		if(!f4.exists()){
			try {
				f4.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
