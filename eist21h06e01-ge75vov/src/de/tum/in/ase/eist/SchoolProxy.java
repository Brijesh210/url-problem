package de.tum.in.ase.eist;

import java.net.URL;
import java.util.Set;

public class SchoolProxy implements ConnectionInterface {

	private Set<String> blacklistedHosts;
	private URL redirectPage;
	private Set<Integer> teacherIDs;
	private boolean authorized = false;
	private NetworkConnection networkConnection;

	public SchoolProxy(Set<String> blHosts, URL rPage, Set<Integer> tIDs) {
		networkConnection = new NetworkConnection();

		this.blacklistedHosts = blHosts;
		this.redirectPage = rPage;
		this.teacherIDs = tIDs;

	}

	@Override
	public void connect(URL url) {

		if (blacklistedHosts.equals(url)) {
			System.out.println("Your requested " + url + " was rejected!");
			System.out.println("You are redirected to " + redirectPage);

		} else {
			System.out.println("Connecting to url " + url);
			networkConnection.connect(url);
		}

	}

	@Override
	public void disconnect() {

		networkConnection.disconnect();

	}

	@Override
	public boolean isConnected() {
		return networkConnection.isConnected();
	}

	public void login(int teacher) {
		if (teacherIDs.contains(teacher)) {
			this.authorized = true;
		}

	}

	public void logout() {
		this.authorized = false;

	}

}
