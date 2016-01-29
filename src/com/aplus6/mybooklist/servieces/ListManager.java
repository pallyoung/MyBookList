package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class ListManager<T> {
	protected ArrayList<T> list;
	protected HashSet<OnListChangedListener> hs;
	public ListManager(){
		list = new ArrayList<T>();
		hs = new HashSet<OnListChangedListener>();
	}
	public ArrayList<T> getList(){
		return list;
	}
	public void removeItem(T o){
		list.remove(o);
		notifyListChange();
	}
	public void addItem(T o){
		list.add(o);
		notifyListChange();
		
	}
	public void addItems(ArrayList<T> al){
		list.addAll(al);
		notifyListChange();
	}
	public void updateItem(T oldOne,T newOne){
		list.remove(oldOne);
		list.add(newOne);
		notifyListChange();
	}
	public void updateItems(){
		
	}
	public void setOnListChangedListener(OnListChangedListener l){
		hs.add(l);
	}
	protected void notifyListChange(){
		Iterator<OnListChangedListener> iter = hs.iterator();
		while(iter.hasNext()){
			OnListChangedListener cb = iter.next();
			cb.onReceive();
		}
	}
}
