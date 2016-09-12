package com.ngx20080110.doublekill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game {

	public static void main(String[] args) {
		Game game;
		try {
			game = new Game(64);
			game.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void process() throws Exception {
		System.out.println("Game start");

		boolean printWinner = false;
		boolean printLoser = false;
		while (players.size() > 1) {
			printWinner = false;
			printLoser = false;
			for (int i = 0; i < players.size(); ) {
				Match match = new Match();
				Player p1 = players.get(i);
				Player p2 = null;
				if (players.size() == 2) {
					match.setP1(players.get(i));
					match.setP2(players.get(i + 1));
					match.fight();
					i += 2;
				}
				else {
					if (i < players.size() - 1) {
						p2 = players.get(i + 1);
					}
					if (p2 != null && p2.getKillTime() == p1.getKillTime()) {
						match.setP1(p1);
						match.setP2(p2);
						match.fight();
						i += 2;
					}
					else {
						i++;
					}
				}
			}
			
			// Resort
			Collections.sort(players, new SortPlayer());
			System.out.println("==================");
			for (Player player : players) {
				if (player.getKillTime() == 0 && !printWinner) {
					System.out.println("-----Winner group----");
					printWinner = true;
				}
				if (player.getKillTime() == 1 && !printLoser) {
					System.out.println("-----Loser group----");
					printLoser = true;
				}
				if (player.getKillTime() < 2) {
					System.out.println(player.getName());
				}
			}
			System.out.println("-----------------");
			
			
			Iterator<Player> iterator = players.iterator();
			while (iterator.hasNext()) {
				Player player = iterator.next();
				if (player.getKillTime() == 2) {
					iterator.remove();
				}
			}
		}
		
		Player winner = players.get(0);
		System.out.println("----------------Winner-----------------");
		System.out.println(winner.toString());
		System.out.println("----------------Winner-----------------");
		
		System.out.println("Game finish");
	}

	public Game(int n) throws Exception {
		if (n % 2 != 0) {
			throw new Exception("The number of players must be even!");
		}
		if (players == null) {
			players = new ArrayList<Player>();
		}
		for (int i = 0; i < n; i++) {
			Player player = new Player();
			player.setName("Player " + (i + 1));
			players.add(player);
		}
	}
	private List<Player> players;
}
