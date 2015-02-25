package com.anduandu.network;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NeUt {

	private static Map<Integer, Integer> connectionTypeMap;
	private static Map<Integer, String> connectionStatusMap;

	public static int getConnectivityType(Context context) {

		initializeConnectionTypeMap();
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		int activeNetworkType = 0;
		if (networkInfo != null) {
			activeNetworkType = networkInfo.getType();
		}
		return connectionTypeMap.get(activeNetworkType);
	}

	public static String getConnectivityStatus(Context context) {

		intializeConnectionStatusMap();
		int connection = NeUt.getConnectivityType(context);
		return connectionStatusMap.get(connection);

	}

	private static void initializeConnectionTypeMap() {

		connectionTypeMap = new HashMap<Integer, Integer>();
		for (NetworkConnectionType connectionType : NetworkConnectionType
				.values()) {
			connectionTypeMap.put(connectionType.getConnectionManagerType(),
					connectionType.getConnectionType());
		}
	}

	private static void intializeConnectionStatusMap() {

		connectionStatusMap = new HashMap<Integer, String>();
		for (NetworkConnectionType connectionType : NetworkConnectionType
				.values()) {
			connectionStatusMap.put(connectionType.getConnectionType(),
					connectionType.getStatus());
		}
	}

}
