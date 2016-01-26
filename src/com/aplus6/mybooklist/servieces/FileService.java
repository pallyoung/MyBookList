package com.aplus6.mybooklist.servieces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.aplus6.mybooklist.Config;
import com.aplus6.mybooklist.MainActivity;
import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.util.FileReader;

import android.content.Context;

public class FileService {
	private static String DATA_PATH;
	private static String list_file;
	public static void init(Context ctx){
		Config config =( (MainActivity)ctx).config;
		DATA_PATH = config.DATA_PATH;
		list_file = config.list_file;
	}
	public static ArrayList<MBookList> getBookList(){
		ArrayList<MBookList> list = new ArrayList<MBookList>();
		try {
			FileReader fr = new FileReader(list_file);
			String result =  fr.readAsString();			
			if(!result.equals("")){
				JSONObject json = new JSONObject(result);
				Iterator<String> iter = json.keys();
				while(iter.hasNext()){
					MBookList m = new MBookList();
					JSONObject b = json.getJSONObject(iter.next());
					m.setC_time(b.getLong("c_time"));
					m.setId(b.getLong("id"));
					m.setName(b.getString("name"));
					m.setM_time(b.getLong("m_time"));
					list.add(m);
				}
				Collections.sort(list,new Comparator<MBookList>(){
					@Override
					public int compare(MBookList o0, MBookList o1) {
						if(o0.getM_time()>o1.getM_time()){
							return -1;
						}else{
							return 1;
						}						
					}					
				});
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public static ArrayList<Book> getBooks(){
		return null;
	}
}
