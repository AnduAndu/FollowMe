package com.anduandu.activity;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anduandu.followme.R;
import com.anduandu.user.LoggedInWith;
import com.anduandu.user.UserVO;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class FacebookLoginActivity extends Activity {

	private UiLifecycleHelper uihelper;
	private UserVO userVO;

	void showMsg(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}

	private Session.StatusCallback callback = new Session.StatusCallback() {

		@Override
		public void call(Session session, SessionState state,
				Exception exception) {

			onSessionStateChange(session, state, exception);
		}
	};

	void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i("facebook", "Logged in...");
			Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {

					if (user != null) {
						
						Calendar calendar = Calendar.getInstance();
						userVO = new UserVO();
						userVO.setUserName(user.getName());
						userVO.setFirstName(user.getFirstName());
						userVO.setLastName(user.getLastName());
						userVO.setEmailID(user.getProperty("email").toString());
						userVO.setGender(user.getProperty("gender").toString());
						userVO.setLastLoggedInTime(calendar.getTime());
						userVO.setLoggedInWith(LoggedInWith.Facebook);
						Log.d("Current time", calendar.getTime().toString());
						
						showMsg(user.getName());
						showMsg(user.getProperty("email") + "");
						showMsg(user.getProperty("gender") + "");
						showMsg(user.getId() + "");
						
						fetchUserDetails();
					} else {
						showMsg("its null");
						System.out.println(response.getError()
								.getErrorMessage());
						Log.e("Error", response.getError().getErrorMessage());
						showMsg(response.getError().getErrorMessage());
					}
				}

			}).executeAsync();

		} else if (state.isClosed()) {
			Log.i("facebook", "Logged out...");
		}
	}
	
	private void fetchUserDetails() {
		Log.d("User name", userVO.getUserName());
		Log.d("User name", userVO.getFirstName());
		Log.d("User name", userVO.getLastName());
		Log.d("User name", userVO.getEmailID());
		Log.d("User name", userVO.getGender());
	}

	@Override
	protected void onResume() {
		super.onResume();
		uihelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uihelper.onSaveInstanceState(outState);
	}

	@Override
	protected void onPause() {
		super.onPause();
		uihelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uihelper.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uihelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook_login);
		uihelper = new UiLifecycleHelper(this, callback);
		uihelper.onCreate(savedInstanceState);

		ArrayList<String> permission = new ArrayList<String>();
		permission.add("email");
		permission.add("public_profile");
		permission.add("user_friends");

		LoginButton btn = (LoginButton) findViewById(R.id.fbbtn);
		btn.setPublishPermissions(permission);
		
	}

}
