package com.ngx20080110.doublekill;

public class Match {
	private Player p1;
	private Player p2;
	private String result;
	
	public void fight() throws Exception {
		if (p1 == null || p2 == null) {
			throw new Exception("Players can not be null");
		}
		
		double d1 = Math.random();
		double d2 = Math.random();
		do {
			if (d1 > d2) {
				p2.setKillTime(p2.getKillTime() + 1);
				result = p1.getName() + " kill " + p2.getName();
			}
			else {
				p1.setKillTime(p1.getKillTime() + 1);
				result = p2.getName() + " kill " + p1.getName();
			}
		}
		while (d1 == d2);
		System.out.println(result);
		
		p1.addMatch(this);
		p2.addMatch(this);
	}
	
	public Player getP1() {
		return p1;
	}
	public void setP1(Player p1) {
		this.p1 = p1;
	}
	public Player getP2() {
		return p2;
	}
	public void setP2(Player p2) {
		this.p2 = p2;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Match result=" + result + "]";
	}
	
}
