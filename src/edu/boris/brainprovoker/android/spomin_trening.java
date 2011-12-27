package edu.boris.brainprovoker.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;

public class spomin_trening extends Activity implements OnClickListener {

	enum gumbi {
		rdec, // 0
		moder, // 1
		rumen, // 2
		zelen
		// 3
	}

	// /status igre koncan ali poteka
	enum status_igre {
		poteka, koncan,
	}

	Random rand = new Random();
	private AlphaAnimation alphaDown, alphaUp; // animacija za gumbke
	private static final int START_DIALOG = 1; // start dialog
	private static final int CONTINUE_DIALOG = 2; // nadaljuj dialog
	ImageButton rdec, moder, rumen, zelen;
	TextView prikaz_stopnje, txtspodaj;
	float volume;

	public List<gumbi> zaporedje = new ArrayList<gumbi>(); // polje zaporedja
	status_igre status; // status igre
	int korak = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spomin);
		
		status = status_igre.poteka;
		// povezemo gumbe
		rdec = (ImageButton) findViewById(R.id.btnRdec);
		moder = (ImageButton) findViewById(R.id.btnModer);
		rumen = (ImageButton) findViewById(R.id.btnRumen);
		zelen = (ImageButton) findViewById(R.id.btnZelen);
		prikaz_stopnje = (TextView) findViewById(R.id.txtPrikazZgoraj);
		txtspodaj = (TextView) findViewById(R.id.txtPrikazSpodaj);

		rdec.setOnClickListener(this);
		moder.setOnClickListener(this);
		rumen.setOnClickListener(this);
		zelen.setOnClickListener(this);

		onemogocigumbe();
		volume = (float) 20;
		showDialog(START_DIALOG);
	}

	@Override
	public void onClick(View arg0) {
		alphaDown = new AlphaAnimation(1.0f, 0.3f);
		alphaUp = new AlphaAnimation(0.3f, 1.0f);
		alphaDown.setDuration(1000);
		alphaUp.setDuration(1000);
		alphaDown.setFillAfter(true);
		alphaUp.setFillAfter(true);
		// /////////////////////////////zelen/////////////////////////////
		if (arg0.getId() == R.id.btnZelen) {
			if (status == status_igre.poteka && korak < zaporedje.size()) {
				if (zaporedje.get(korak) == gumbi.zelen) {
					zelen.startAnimation(alphaDown);
					MediaPlayer mp = MediaPlayer.create(this, R.raw.green);
					mp.setVolume(volume, volume);
					mp.start();
					zelen.startAnimation(alphaUp);
					while (mp.isPlaying()) {
					}
					mp.release();
					if (korak == (zaporedje.size() - 1)) {
						igraj();
						onemogocigumbe();
						prikaz_stopnje.setText("Level: " + zaporedje.size());
						return;
					}
					korak++;
				} else {
					status = status_igre.koncan;
				}
			}
		}
		// /////////////////////////////////////////////////////////////////
		// ////////////////////////moder////////////////////////////////////
		else if (arg0.getId() == R.id.btnModer) {
			if (status == status_igre.poteka && korak < zaporedje.size()) {
				if (zaporedje.get(korak) == gumbi.moder) {
					moder.startAnimation(alphaDown);
					MediaPlayer mp = MediaPlayer.create(this, R.raw.blue);
					mp.setVolume(volume, volume);
					mp.start();
					moder.startAnimation(alphaUp);
					while (mp.isPlaying()) {
					}
					mp.release();
					if (korak == (zaporedje.size() - 1)) {
						igraj();
						onemogocigumbe();
						prikaz_stopnje.setText("Level: " + zaporedje.size());
						return;
					}
					korak++;
				} else {
					status = status_igre.koncan;
				}
			}
		}
		// /////////////////////////////////////////////////////////////////////
		// //////////////////////rdec/////////////////////////////////////////////
		else if (arg0.getId() == R.id.btnRdec) {
			if (status == status_igre.poteka && korak < zaporedje.size()) {
				if (zaporedje.get(korak) == gumbi.rdec) {
					rdec.startAnimation(alphaDown);
					MediaPlayer mp = MediaPlayer.create(this, R.raw.red);
					mp.setVolume(volume, volume);
					mp.start();
					rdec.startAnimation(alphaUp);
					while (mp.isPlaying()) {
					}
					mp.release();
					if (korak == (zaporedje.size() - 1)) {
						igraj();
						onemogocigumbe();
						prikaz_stopnje.setText("Level: " + zaporedje.size());
						return;
					}
					korak++;
				} else {
					status = status_igre.koncan;
				}
			}
		}
		// //////////////////////////////////////////////////////////////////
		// /////////////////rumen/////////////////////////////////////////
		else if (arg0.getId() == R.id.btnRumen) {
			if (status == status_igre.poteka && korak < zaporedje.size()) {
				if (zaporedje.get(korak) == gumbi.rumen) {
					rumen.startAnimation(alphaDown);
					MediaPlayer mp = MediaPlayer.create(this, R.raw.yellow);
					mp.setVolume(volume, volume);
					mp.start();
					rumen.startAnimation(alphaUp);
					while (mp.isPlaying()) {
					}
					mp.release();
					if (korak == (zaporedje.size() - 1)) {
						igraj();
						onemogocigumbe();
						prikaz_stopnje.setText("Level: " + zaporedje.size());
						return;
					}
					korak++;
				} else {
					status = status_igre.koncan;
				}
			}
		}
		alphaDown.reset();
		alphaUp.reset();
		preveriigro();
	}

	// doda nakljucen zvok v listo in poklice predvajaj
	public void igraj() {
		txtspodaj.setText("Raè. na vrsti");
		korak = 0;
		int r = rand.nextInt(4);
		switch (r) {
		case 0:
			zaporedje.add(gumbi.rdec);
			break;
		case 1:
			zaporedje.add(gumbi.moder);
			break;
		case 2:
			zaporedje.add(gumbi.rumen);
			break;
		case 3:
			zaporedje.add(gumbi.zelen);
			break;
		default:
			break;
		}

		Thread thread = new Thread() {
			@Override
			public void run() {
				predvajajzvoke();
			}
		};
		thread.setPriority(10);
		thread.start();
	}

	// dobimo sporoèilo iz nitke in naredimo akcijo
	private Handler posodobi = new Handler() {
		public void handleMessage(Message msg) {
			alphaDown = new AlphaAnimation(1.0f, 0.3f);
			alphaUp = new AlphaAnimation(0.3f, 1.0f);
			alphaDown.setDuration(1000);
			alphaUp.setDuration(1000);
			alphaDown.setFillAfter(true);
			alphaUp.setFillAfter(true);
			switch (msg.what) {
			case 0:
				rdec.startAnimation(alphaDown);
				MediaPlayer mp1 = MediaPlayer.create(spomin_trening.this, R.raw.red);
				mp1.setVolume(volume, volume);
				mp1.start();
				rdec.startAnimation(alphaUp);
				while (mp1.isPlaying()) {
				}
				mp1.release();
				break;
			case 1:
				moder.startAnimation(alphaDown);
				MediaPlayer mp2 = MediaPlayer.create(spomin_trening.this, R.raw.blue);
				mp2.setVolume(volume, volume);
				mp2.start();
				moder.startAnimation(alphaUp);
				while (mp2.isPlaying()) {
				}
				mp2.release();
				break;
			case 2:
				rumen.startAnimation(alphaDown);
				MediaPlayer mp3 = MediaPlayer.create(spomin_trening.this, R.raw.yellow);
				mp3.setVolume(volume, volume);
				mp3.start();
				rumen.startAnimation(alphaUp);
				while (mp3.isPlaying()) {
				}
				mp3.release();
				break;
			case 3:
				zelen.startAnimation(alphaDown);
				MediaPlayer mp4 = MediaPlayer.create(spomin_trening.this, R.raw.green);
				mp4.setVolume(volume, volume);
				mp4.start();
				zelen.startAnimation(alphaUp);
				while (mp4.isPlaying()) {
				}
				mp4.release();
				break;
			case 4:
				omogocigumbe();
				txtspodaj.setText("Vi ste na vrsti");
				break;
			}
			alphaDown.reset();
			alphaUp.reset();

		}
	};

	// prdevaja zvoke iz zaporedja v novi nitki
	public void predvajajzvoke() {
		try {
			Thread.sleep(1000);
			for (int i = 0; i < zaporedje.size(); i++) {
				Thread.sleep(500);
				switch (zaporedje.get(i)) {
				case rdec:
					posodobi.sendMessage(Message.obtain(posodobi, 0));
					if (i == zaporedje.size() - 1)
						posodobi.sendMessage(Message.obtain(posodobi, 4));
					Thread.sleep(500);
					break;
				case moder:
					posodobi.sendMessage(Message.obtain(posodobi, 1));
					if (i == zaporedje.size() - 1)
						posodobi.sendMessage(Message.obtain(posodobi, 4));
					Thread.sleep(500);
					break;
				case rumen:
					posodobi.sendMessage(Message.obtain(posodobi, 2));
					if (i == zaporedje.size() - 1)
						posodobi.sendMessage(Message.obtain(posodobi, 4));
					Thread.sleep(500);
					break;
				case zelen:
					posodobi.sendMessage(Message.obtain(posodobi, 3));
					if (i == zaporedje.size() - 1)
						posodobi.sendMessage(Message.obtain(posodobi, 4));
					Thread.sleep(500);
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	// ////dialogi////////////////
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		switch (id) {
		case START_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Ste pripravljeni?")
					.setCancelable(false)
					.setPositiveButton("Da",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									igraj();
								}

							})
					.setNegativeButton("Ne",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									finish();
								}
							});
			return builder.create();
		case CONTINUE_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage(
					"Konec!")
					.setCancelable(false)
					.setNegativeButton("Izhod",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
									finish();
								}
							});
			return builder.create();
		}
		return null;
	}

	// //////////////////////////////

	public void drugaIgra() {
		Intent newgame = new Intent(this, ujemanje_barv.class);
		this.startActivity(newgame);
	}

	public void onemogocigumbe() {
		rdec.setEnabled(false);
		moder.setEnabled(false);
		rumen.setEnabled(false);
		zelen.setEnabled(false);
	}

	public void omogocigumbe() {
		rdec.setEnabled(true);
		moder.setEnabled(true);
		rumen.setEnabled(true);
		zelen.setEnabled(true);
		status = status_igre.poteka;
	}

	// koncamo trenutno igro(ponovimo igro ali nadaljevanje na 2 del igre)
	public void preveriigro() {
		if (status == status_igre.koncan) {
				MediaPlayer mp = MediaPlayer.create(this, R.raw.gameover);
				mp.setVolume(volume, volume);
				mp.start();
				while (mp.isPlaying()) {
			}
			mp.release();
			txtspodaj.setText("Igra koncana");
			onemogocigumbe();
			showDialog(CONTINUE_DIALOG);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					current + 1, AudioManager.FLAG_VIBRATE);
			break;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					current - 1, AudioManager.FLAG_VIBRATE);
			break;
		}
		return false;
	}
}
