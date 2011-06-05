package edu.boris.brainprovoker.android;


import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ScoreListActivity extends ListActivity implements OnClickListener{
	Brain_provoker_app app;
	Button getrezultati,sendrezultati;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list_layout);
		app = (Brain_provoker_app) getApplication();
		setListAdapter(app.rezultati);
		app.lista.clear();
		app.fillFromDB();
		getrezultati=(Button)findViewById(R.id.btnGetScores);
		sendrezultati=(Button)findViewById(R.id.btnSendScores);
		getrezultati.setOnClickListener(this);
		sendrezultati.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId()==R.id.btnGetScores)
		{
			String returnstring="";
			getScores scores=new getScores();
			try {
				returnstring=scores.GetRezultat();
				//Toast.makeText(this, scores.GetRezultat(), Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
			if(returnstring.length()>3)
			{
				String[] words;
				words=returnstring.split(";");
				
				for(int i=0;i<words.length;i=i+2)
				{
					igralec tmp=new igralec();
					tmp.ime=words[i];
					tmp.score=Integer.parseInt(words[i+1]);
					//System.out.println(words[i]);
					//System.out.println(words[i+1]);
					app.addIgralec(tmp);
				}
				app.lista.clear();
				app.fillFromDB();
			}
		}
		else
			if (v.getId()==R.id.btnSendScores)
			{
				String poslji="";
				for (int i = 0; i < app.lista.size(); i++)
	            {
	                poslji=poslji+app.lista.get(i).ime+";"+app.lista.get(i).score+";";
	            }

				if(poslji.length()>1)
				{
					try {
						getScores scores=new getScores();
						System.out.println(poslji);
						scores.SendRezultat(poslji);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (XmlPullParserException e) {
						e.printStackTrace();
					}
				}
			}
		
		
	}

}
