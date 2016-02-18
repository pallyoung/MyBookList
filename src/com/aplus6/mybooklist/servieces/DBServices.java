package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;

import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.sqlite.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
			while(!c.isAfterLast()){
				MBookList l = new MBookList();
		        l.setId(c.getLong(c.getColumnIndex("id")));
		        l.setName(c.getString(c.getColumnIndex("l_name")));
		        l.setM_time(c.getLong(c.getColumnIndex("m_time")));
		        l.setC_time(c.getLong(c.getColumnIndex("c_time")));
		        mlist.add(l);
		        c.moveToNext();
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
	public static void removeBookList(long id){
		SDB.delete(T_LIST, "id=?", new String[]{String.valueOf(id)});
	}
	public static ArrayList<Book> getBooks(){
		ArrayList<Book> list = new ArrayList<Book>();
		Cursor c = SDB.query(T_BOOK, null, null, null, null, null, "m_time");
		if(c.moveToFirst()){//判断游标是否为空
			while(!c.isAfterLast()){
				Book l = new Book();
		        l.setId(c.getLong(c.getColumnIndex("id")));
		        l.setName(c.getString(c.getColumnIndex("b_name")));
		        l.setAuthor(c.getString(c.getColumnIndex("author")));
		        l.setLast_read_time(c.getLong(c.getColumnIndex("r_time")));
		        l.setP_count(c.getLong(c.getColumnIndex("p_count")));
		        l.setH_p_count(c.getLong(c.getColumnIndex("h_p_count")));	        
		        l.setC_time(c.getLong(c.getColumnIndex("c_time")));
		        l.setProcess(c.getInt(c.getColumnIndex("process")));
		        list.add(l);
		        c.moveToNext();
			}
		}
		return list;
	}
	public static ArrayList<Book> getBooksByList(long list_id){
		ArrayList<Book> list = new ArrayList<Book>();
		Cursor c = SDB.query(T_BOOK, null, "l_id=?", new String[]{String.valueOf(list_id)}, null, null, "c_time");
		//Cursor c = SDB.query(T_BOOK, null, null, null, null, null, "c_time");
		Log.e("12121212121", String.valueOf(c.getCount()));
		if(c.moveToFirst()){//判断游标是否为空
			while(!c.isAfterLast()){
				Book l = new Book();
		        l.setId(c.getLong(c.getColumnIndex("id")));
		        l.setName(c.getString(c.getColumnIndex("b_name")));
		        l.setAuthor(c.getString(c.getColumnIndex("author")));
		        l.setLast_read_time(c.getLong(c.getColumnIndex("r_time")));
		        l.setP_count(c.getLong(c.getColumnIndex("p_count")));
		        l.setH_p_count(c.getLong(c.getColumnIndex("r_p_count")));	        
		        l.setC_time(c.getLong(c.getColumnIndex("c_time")));
		        l.setProcess(c.getInt(c.getColumnIndex("process")));
		        list.add(l);
		        c.moveToNext();
			}
		}
		return list;
	}
	public static void getBookById(long b_id){
		
	}
	public static void addBook(Book b){
		ContentValues cv = new ContentValues();
		cv.put("l_id",b.getL_id());
		cv.put("b_name",b.getName());
		cv.put("r_time",b.getLast_read_time());
		cv.put("c_time", b.getC_time());
		cv.put("author", b.getAuthor());
		cv.put("r_p_count", b.getH_p_count());
		cv.put("p_count", b.getP_count());
		cv.put("process", b.getProcess());
		SDB.insert(T_BOOK,null, cv);
	}
	public static void removeBook(Book b){
		SDB.delete(T_BOOK, "id=?", new String[]{String.valueOf(b.getId())});
	}
}
