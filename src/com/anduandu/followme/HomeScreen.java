package com.anduandu.followme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.anduandu.activity.FacebookLoginActivity;
import com.anduandu.activity.NoConnectionActivity;
import com.anduandu.network.NetworkUtil;

public class HomeScreen extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);

		IntentFilter intentFilter = new IntentFilter(
				ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(networkStateReceiver, intentFilter);

	}

	BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {

			Integer type = NetworkUtil.getConnectivityStatus(context);
			Log.d("type", type.toString());
			if (type == 0)
				onConnectionLost();
			else
				onConnectionFound();
		}

	};

	private void onConnectionLost() {
		Intent noConnectionIntent = new Intent(HomeScreen.this,
				NoConnectionActivity.class);
		startActivity(noConnectionIntent);
	}

	private void onConnectionFound() {
		Intent facebookLoginIntent = new Intent(HomeScreen.this,
				FacebookLoginActivity.class);
		startActivity(facebookLoginIntent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(networkStateReceiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
