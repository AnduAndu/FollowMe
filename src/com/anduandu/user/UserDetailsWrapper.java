package com.anduandu.user;

import com.facebook.model.GraphUser;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.model.people.Person;

public class UserDetailsWrapper {

	private GraphUser graphUser;
	private Person person;
	private GoogleApiClient googleApiClient;
	
	public GraphUser getGraphUser() {
		return graphUser;
	}
	public void setGraphUser(GraphUser graphUser) {
		this.graphUser = graphUser;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public GoogleApiClient getGoogleApiClient() {
		return googleApiClient;
	}
	public void setGoogleApiClient(GoogleApiClient googleApiClient) {
		this.googleApiClient = googleApiClient;
	}
	
}
