package com.anduandu.network;

import android.net.ConnectivityManager;

public enum NetworkConnectionType {

	NOT_CONNECTED(0,0, "Not Connected to Internet"), WIFI(ConnectivityManager.TYPE_WIFI,1, "WiFi Enabled"), MOBILE(
			ConnectivityManager.TYPE_MOBILE,2, "Mobile Data Enabled");

	private int connectionManagerType;
	private int connectionType;
	private String status;

	private NetworkConnectionType(int connectionManagerType, int networkConnectionType, String status) {
		setConnectionManagerType(connectionManagerType);
		setConnectionType(networkConnectionType);
		setStatus(status);
	}

	public int getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(int networkConnectionType) {
		this.connectionType = networkConnectionType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getConnectionManagerType() {
		return connectionManagerType;
	}

	public void setConnectionManagerType(int connectionManagerType) {
		this.connectionManagerType = connectionManagerType;
	}
}
