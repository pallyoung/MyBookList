package com.aplus6.mybooklist.views;

import com.aplus6.mybooklist.R;
import com.aplus6.mybooklist.models.Book;
import com.aplus6.mybooklist.models.MBookList;
import com.aplus6.mybooklist.servieces.BooksManager;
import com.aplus6.mybooklist.servieces.MBookListManager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class BookInfoFragment extends Fragment {
	private Button cancel;
	private Button ok;
	private EditText bookName;
	private EditText author;
	private BooksManager mblManager;
	private FragmentManager fm;
	private MBookList mbl;
	public BookInfoFragment ( MBookList mbl){
		this.mbl = mbl;
	}
	@Override  
    public View onCreateView( LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)  
    {  
		fm = getFragmentManager(); 
		mblManager = BooksManager.getInstance();
		View view  = inflater.inflate(R.layout.view_add_book, container, false);
		cancel = (Button)view.findViewById(R.id.cancel);
		ok = (Button)view.findViewById(R.id.ok);
		bookName = (EditText)view.findViewById(R.id.bookname);
		author = (EditText)view.findViewById(R.id.author);
		cancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				fm.popBackStack();
			}
			
		});
		ok.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				String name = bookName.getText().toString();
				String author = BookInfoFragment.this.author.getText().toString();
				Book b = new Book();
				b.setAuthor(author);
				b.setName(name);
				b.setC_time(System.currentTimeMillis());
				b.setLast_read_time(0);
				b.setL_id(mbl.getId());
				mblManager.addItem(b);
				fm.popBackStack();
			}
			
		});
        return view;  
    }  
}
