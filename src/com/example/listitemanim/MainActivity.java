package com.example.listitemanim;

import android.app.ListActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getListView().setDividerHeight(10);
		getListView().setAdapter(new ItemAnimAdapter(this));
	}
	
}
