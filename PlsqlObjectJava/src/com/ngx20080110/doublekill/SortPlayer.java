package com.ngx20080110.doublekill;

import java.util.Comparator;

public class SortPlayer implements Comparator<Player> {

	@Override
	public int compare(Player p1, Player p2) {
		if (p1.getKillTime() > p2.getKillTime()) {
			return 1;
		}
		else if (p1.getKillTime() == p2.getKillTime()) {
			return 0;
		}
		else {
			return -1;
		}
	}

}
