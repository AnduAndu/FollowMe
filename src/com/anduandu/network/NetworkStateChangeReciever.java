package com.anduandu.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.anduandu.activity.NoConnectionActivity;

public class NetworkStateChangeReciever extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String status = NetworkUtil.getConnectivityStatusString(context);
		int type = NetworkUtil.getConnectivityStatus(context);
		if(type == 0) {
			context.startActivity(new Intent(context, NoConnectionActivity.class));
		}
		Toast.makeText(context, status, Toast.LENGTH_LONG).show();
	}

}
