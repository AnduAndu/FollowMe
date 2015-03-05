package com.anduandu.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.anduandu.followme.R;
import com.anduandu.user.LoggedInWith;
import com.anduandu.user.UserVO;
import com.anduandu.util.CalendarUtil;
import com.anduandu.util.FacebookUtil;
import com.anduandu.util.UserUtil;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.Name;

public class FacebookLoginActivity extends Activity implements OnClickListener, ConnectionCallbacks, OnConnectionFailedListener {

	private Button googleSignOutButton; 
	private SignInButton googleSignInButton;
	private static final int RC_SIGN_IN = 0;
	private UserVO userVO;
	private UiLifecycleHelper uiLifecycleHelper;

	private GoogleApiClient googleApiClient;
	private boolean isIntentInProgress;
	private boolean signedInUser;
	private ConnectionResult connectionResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook_login);
		
		uiLifecycleHelper = getUILifeCycleHelperInstance();
		uiLifecycleHelper.onCreate(savedInstanceState);
		LoginButton faecbookLoginButton = (LoginButton) findViewById(R.id.facebookLoginBtn);
		List<String> permissions = FacebookUtil.getPermissions();
		faecbookLoginButton.setPublishPermissions(permissions);
		
		googleSignInButton = (SignInButton) findViewById(R.id.signinGoogle);
		googleSignInButton.setOnClickListener(this);
		googleApiClient = getGoogleAPIClient();
		googleSignOutButton = (Button) findViewById(R.id.googleLogout);
		
	}
	
	private void logUserDetails(UserVO userVO) {
		if(userVO != null) {
			Log.i("First name", userVO.getFirstName());
			Log.i("Last name", userVO.getLastName());
			Log.i("User name", userVO.getUserName());
			Log.i("Gender", userVO.getGender());
			Log.i("Email-ID", userVO.getEmailID());
			Log.i("Last login time", userVO.getLastLoggedInTime().toString());
			Log.i("Logged in with", userVO.getLoggedInWith().getLoggedInWith());
		}
		else {
			Log.e("User is not logged in", " user data is null");
		}
	}

	private GoogleApiClient getGoogleAPIClient() {
		return new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(Plus.API, Plus.PlusOptions.builder().build()).addScope(Plus.SCOPE_PLUS_LOGIN).build();
	}


	private UiLifecycleHelper getUILifeCycleHelperInstance() {
		return new UiLifecycleHelper(this, statusCallback);
	}


	void showMsg(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();
	}
	
	private Session.StatusCallback statusCallback = getFacebookStatusCallback();

	private StatusCallback getFacebookStatusCallback() {
		return new Session.StatusCallback() {

			@Override
			public void call(Session session, SessionState state,
					Exception exception) {

				onSessionStateChange(session, state, exception);
			}
		};
	}

	void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i("facebook", "Logged in...");
			Request.newMeRequest(session, new Request.GraphUserCallback() {

				@Override
				public void onCompleted(GraphUser user, Response response) {

					Log.d("user created", "in completed");
					if (user != null) {
						userVO = createFacebookUserAndStoreDetails(user);
						Log.d("user created", userVO.getLoggedInWith().getLoggedInWith());
						logUserDetails(userVO);
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
	
	private UserVO createFacebookUserAndStoreDetails(GraphUser user) {
		
		userVO = new UserVO();
		userVO.setUserName(user.getName());
		userVO.setFirstName(user.getFirstName());
		userVO.setLastName(user.getLastName());
		userVO.setEmailID(user.getProperty("email").toString());
		userVO.setGender(user.getProperty("gender").toString());
		userVO.setLastLoggedInTime(CalendarUtil.getCurrentTime());
		userVO.setLoggedInWith(LoggedInWith.Facebook);
		return userVO;
	}

	private void updateStatus(boolean isSignedIn) {
		if(isSignedIn) {
			googleSignOutButton.setVisibility(View.VISIBLE);
			googleSignInButton.setVisibility(View.GONE);
		}
		else {
			googleSignOutButton.setVisibility(View.GONE);
			googleSignInButton.setVisibility(View.VISIBLE);
		}
	}
	
	private void resolveSignInError() {
		if (connectionResult.hasResolution()) {
			try {
				isIntentInProgress = true;
				connectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				isIntentInProgress = false;
				googleApiClient.connect();
			}
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
			return;
		}
		if (!isIntentInProgress) {
			connectionResult = result;
			if (signedInUser) {
				resolveSignInError();
			}
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiLifecycleHelper.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case RC_SIGN_IN:
			if (resultCode == RESULT_OK) {
				signedInUser = false;

			}
			isIntentInProgress = false;
			if (!googleApiClient.isConnecting()) {
				googleApiClient.connect();
			}
			break;
		}

	}


	@Override
	public void onConnected(Bundle arg0) {
		signedInUser = false;
		Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show();
		getProfileInformation();
	}

	private void getProfileInformation() {
		try {
			Person currentPerson = getCurrentPerson();
			if (currentPerson != null) {
				UserVO userVO = createGoogleUserAndStoreDetails(currentPerson);
				logUserDetails(userVO);
				updateStatus(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private UserVO createGoogleUserAndStoreDetails(Person currentPerson) {
		
		userVO = new UserVO();
		Name name = currentPerson.getName();
		userVO.setFirstName(name.getGivenName());
		userVO.setLastName(name.getFamilyName());
		userVO.setUserName(currentPerson.getDisplayName());
		userVO.setGender(UserUtil.getGenderString(currentPerson.getGender()));
		userVO.setEmailID(Plus.AccountApi.getAccountName(googleApiClient));
		userVO.setLastLoggedInTime(CalendarUtil.getCurrentTime());
		userVO.setLoggedInWith(LoggedInWith.GooglePlus);
		return userVO;
	}

	private Person getCurrentPerson() {
		return getPeopleAPI().getCurrentPerson(googleApiClient);
	}


	private People getPeopleAPI() {
		return Plus.PeopleApi;
	}

	@Override
	public void onConnectionSuspended(int cause) {
		googleApiClient.connect();
		updateStatus(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signinGoogle:
			googlePlusLogin();
			break;
		}
	}
	public void signIn(View v) {
		googlePlusLogin();
	}

	public void logout(View v) {
		googlePlusLogout();
	}
	public void googlePlusLogin() {
		
		Log.d("google login", "in login");
		if (!googleApiClient.isConnecting()) {
			signedInUser = true;
			resolveSignInError();
		}
	}
	private void googlePlusLogout() {
		if (googleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(googleApiClient);
			googleApiClient.disconnect();
			googleApiClient.connect();
			updateStatus(false);
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		uiLifecycleHelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiLifecycleHelper.onSaveInstanceState(outState);
	}
	
	protected void onStart() {
		super.onStart();
		googleApiClient = getGoogleAPIClient();
		googleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		if (googleApiClient.isConnected()) {
			googleApiClient.disconnect();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		uiLifecycleHelper.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		uiLifecycleHelper.onDestroy();
	}
	
}
