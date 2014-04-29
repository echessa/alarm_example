package com.example.alarmexample;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private PendingIntent pendingIntent;
	private AlarmManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // Retrieve a PendingIntent that will perform a broadcast
		Intent alarmIntent = new Intent(this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startAlarm(View view) {
		manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		int interval = 10000; // 10 seconds
		
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
	}
	
	public void cancelAlarm(View view) {
		if (manager != null) {
			manager.cancel(pendingIntent);
	        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
		}

	}	
}
