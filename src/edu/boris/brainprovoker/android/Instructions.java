package edu.boris.brainprovoker.android;

import edu.boris.brainprovoker.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Instructions extends Activity implements OnClickListener {
	Button back;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        back=(Button)findViewById(R.id.btnBack);
        back.setOnClickListener(this);
	}
	
	 public void onClick(View arg0){
		 if (arg0.getId()==R.id.btnBack)
		 {
			 finish();
		 }
	 }

}
