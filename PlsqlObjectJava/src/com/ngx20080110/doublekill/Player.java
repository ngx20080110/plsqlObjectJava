package com.ngx20080110.doublekill;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {

	private String name;
	private int killTime = 0;
	private List<Match> matchs;
	public void addMatch(Match match) {
		if (matchs == null) {
			matchs = new ArrayList<>();
		}
		matchs.add(match);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKillTime() {
		return killTime;
	}
	public void setKillTime(int killTime) {
		this.killTime = killTime;
	}
	public List<Match> getMatchs() {
		return matchs;
	}
	public void setMatchs(List<Match> matchs) {
		this.matchs = matchs;
	}
	@Override
	public String toString() {
		return "Player [name=" + name + ", killTime=" + killTime + ", matchs=" + matchs + "]";
	}
	@Override
	public int compareTo(Player o) {
		if (this.killTime == o.getKillTime()) {
			return 0;
		}
		else if (this.killTime < o.getKillTime()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
