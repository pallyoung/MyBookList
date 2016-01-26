package com.aplus6.sqlite;

import com.aplus6.mybooklist.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	private Context ctx;
	public DBHelper(Context context) {
		super(context, context.getResources().getString(R.string.db_name), null, Integer.parseInt(context.getResources().getString(R.string.db_version)));
		ctx = context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table list (id INTEGER PRIMARY KEY AUTOINCREMENT,l_name text,c_time int,m_time int)");
		db.execSQL("create table book (id INTEGER PRIMARY KEY AUTOINCREMENT,b_name text,author text, process int,p_count int,r_p_count,c_time int,r_time int)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
