package edu.boris.brainprovoker.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class trening extends Activity implements OnClickListener{
	ImageButton spomin, ujemanje, hitri_prsti;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trening_layout);
		spomin = (ImageButton) findViewById(R.id.imageButtonTrening1);
		ujemanje = (ImageButton) findViewById(R.id.imageButtonTrening2);
		hitri_prsti = (ImageButton) findViewById(R.id.imageButtonTrening3);
		
		spomin.setOnClickListener(this);
		ujemanje.setOnClickListener(this);
		hitri_prsti.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.imageButtonTrening1) {
			Intent trening1 = new Intent(this, spomin_trening.class);
			startActivity(trening1);
		}
		else if (v.getId() == R.id.imageButtonTrening2) {
			Intent trening2 = new Intent(this, ujemanje_barv_trening.class);
			startActivity(trening2);
		}
		else if (v.getId() == R.id.imageButtonTrening3) {
			Intent trening3 = new Intent(this, hitri_prsti_trening.class);
			startActivity(trening3);
		}
		
	}

}
