package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;

import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.sqlite.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBServices {
	private static Context CTX;
	private static SQLiteDatabase SDB;
	private static String T_LIST = "list";
	private static String T_BOOK = "book";
	public static void init(Context ctx){
		CTX = ctx;
		DBHelper dh = new DBHelper(ctx);
		SDB = dh.getWritableDatabase();
	}
	public static ArrayList<MBookList> getBookList(){
		ArrayList<MBookList> mlist = new ArrayList<MBookList>();
		Cursor c = SDB.query(T_LIST, null, null, null, null, null, "m_time");
		if(c.moveToFirst()){//判断游标是否为空
		    for(int i=0;i<c.getCount();i++){
		        c.move(i);//移动到指定记录
		        MBookList l = new MBookList();
		        l.setId(c.getLong(c.getColumnIndex("id")));
		        l.setName(c.getString(c.getColumnIndex("l_name")));
		        l.setM_time(c.getLong(c.getColumnIndex("m_time")));
		        l.setC_time(c.getLong(c.getColumnIndex("c_time")));
		        mlist.add(l);
		    }
		}
		return mlist;
	}
	public static void addBookList(MBookList bl){
		ContentValues cv = new ContentValues();
		cv.put("l_name",bl.getName());
		cv.put("m_time",bl.getM_time());
		cv.put("c_time", bl.getC_time());
		SDB.insert(T_LIST,null, cv);
	}
	public static void addBook(int list_id,Book b){
		
	}
}
