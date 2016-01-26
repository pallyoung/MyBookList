package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.aplus6.mybooklist.models.MBookList;


public class MBookListManager {
	private static  ArrayList<MBookList> myList;
	private static HashSet<OnListChangedListener> hs;
	public static void init(){
		myList = DBServices.getBookList();
		hs = new HashSet<OnListChangedListener>();
	}
	public static  ArrayList<MBookList> getMBookLists(){
		if(myList==null){
			
		}
		return myList;
	}
	public static void addMBookList(MBookList bl){
		DBServices.addBookList(bl);
		myList.add(bl);
		notifyListChange();
	}
	public static void removeMBookListById(int id){
		notifyListChange();
	}
	public static void updateMBookListById(MBookList bl){
		notifyListChange();
	}
	private static void notifyListChange(){
		Iterator<OnListChangedListener> iter = hs.iterator();
		while(iter.hasNext()){
			OnListChangedListener cb = iter.next();
			cb.onReceive(myList);
		}
	}
	public static void setOnListChangedListener(OnListChangedListener cb){
		hs.add(cb);
	}
	public abstract static class  OnListChangedListener{
		public abstract void onReceive(ArrayList<MBookList> value);
	}
}
