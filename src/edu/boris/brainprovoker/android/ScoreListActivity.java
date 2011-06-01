package edu.boris.brainprovoker.android;


import android.app.ListActivity;
import android.os.Bundle;

public class ScoreListActivity extends ListActivity {
	Brain_provoker_app app;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list_layout);
		app = (Brain_provoker_app) getApplication();
		setListAdapter(app.rezultati);
	}

}
