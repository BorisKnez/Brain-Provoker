package edu.boris.brainprovoker.android;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


public class ServiceGlasba extends Service {
	MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {		
		player = MediaPlayer.create(this, R.raw.background);
		player.setLooping(true); // Set looping
	}

	@Override
	public void onDestroy() {
		player.stop();
	}
	
	@Override
	public void onStart(Intent intent, int startid) {
		player.start();
	}
}

