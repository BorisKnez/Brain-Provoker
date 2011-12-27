package edu.boris.brainprovoker.android;

public class igralec {
	public String ime;
	public int score;
	private long dbID;

	igralec(String i, int s) {
		this.ime = i;
		this.score = s;
		setDbID(0);
	}

	igralec() {
	}

	public void setDbID(long dbID) {
		this.dbID = dbID;
	}

	public long getDbID() {
		return dbID;
	}

}
