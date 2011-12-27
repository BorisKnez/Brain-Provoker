package edu.boris.brainprovoker.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ScoreListActivity extends ListActivity implements OnClickListener {
	Brain_provoker_app app;
	Button getrezultati, sendrezultati;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_list_layout);
		app = (Brain_provoker_app) getApplication();
		setListAdapter(app.rezultati);
		app.lista.clear();
		app.fillFromDB();
		getrezultati = (Button) findViewById(R.id.btnGetScores);
		sendrezultati = (Button) findViewById(R.id.btnSendScores);
		getrezultati.setOnClickListener(this);
		sendrezultati.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnGetScores) {
			List<igralec> polje = new ArrayList<igralec>();
			getScores scores = new getScores();
			try {
				polje = scores.GetRezultat();
				// Toast.makeText(this, scores.GetRezultat(),
				// Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}

			if (polje.size() > 0) {
				boolean obstaja = false;
				igralec tmp = new igralec();
				for (int i = 0; i < polje.size(); i++) {
					tmp = polje.get(i);
					System.out.println(tmp.ime + " " + tmp.score);
					obstaja = false;
					for (int j = 0; j < app.lista.size(); j++) {
						if (tmp.ime.hashCode() == app.lista.get(j).ime
								.hashCode()) {
							obstaja = true;
							break;
						}
					}
					if (obstaja == false)
						app.addIgralec(tmp);
					tmp = null;
				}
			}
			app.lista.clear();
			app.fillFromDB();
			Toast.makeText(this, "Konèano! Èe ni sprememb ni novih rezultatov",
					Toast.LENGTH_LONG).show();
		} else if (v.getId() == R.id.btnSendScores) {
			String poslji = "";
			for (int i = 0; i < app.lista.size(); i++) {
				poslji = poslji + (app.lista.get(i).ime.replace(" ", ""));
				poslji = poslji + ";";
				poslji = poslji
						+ Integer.toString(app.lista.get(i).score).replace(" ",
								"");
				if (i < app.lista.size() - 1) {
					poslji = poslji + ";";
				}
			}
			try {
				getScores scores = new getScores();
				scores.SendRezultat(poslji);
				Toast.makeText(this, "Rezultati uspešni poslani!", Toast.LENGTH_LONG).show();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			}
		}

	}

}
