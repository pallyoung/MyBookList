package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.aplus6.mybooklist.models.MBookList;


public class MBookListManager extends ListManager<MBookList> {
	
	private MBookListManager(){
		super();
		list = DBServices.getBookList();
	}
	public static MBookListManager getInstance(){
		return LazyLoad.mblManager;
	}
	@Override
	public void addItem(MBookList bl){
		super.addItem(bl);
		DBServices.addBookList(bl);
	}
	@Override
	public void removeItem(MBookList bl){
		super.removeItem(bl);
		DBServices.removeBookList(bl.getId());
	}
	private static class LazyLoad  {
		private final static 	MBookListManager mblManager = new MBookListManager();
	}
}
