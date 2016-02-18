package com.aplus6.mybooklist.servieces;

import java.util.ArrayList;

import com.aplus6.mybooklist.models.Book;

public class BooksManager extends ListManager<Book> {
	private BooksManager(){
		super();
	}
	public static BooksManager getInstance(){
		return LazyLoader.bManager;
	}
	@Override
	public void addItem(Book b){
		DBServices.addBook(b);
		notifyListChange();
	}
	@Override
	public void removeItem(Book b){
		DBServices.removeBook(b);
		notifyListChange();
	}
	public ArrayList<Book> getBooksByList(long l_id){
		return DBServices.getBooksByList(l_id);
	}
	private static class LazyLoader{
		private final static BooksManager bManager = new BooksManager();
	}
}
