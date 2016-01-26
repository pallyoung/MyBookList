package com.aplus6.mybooklist;



import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	public Config config;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		config = new Config(this);
		config.checkEnv();	
		setContentView(R.layout.activity_main);			
	}
}
