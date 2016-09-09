package com.ngx20080110.doublekill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Game {

	public static void main(String[] args) {
		Game game;
		try {
			game = new Game(4);
			game.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void process() throws Exception {
		System.out.println("Game start");
		
		while (players.size() > 1) {
			for (int i = 0; i < players.size(); i += 2) {
				Match match = new Match();
				match.setP1(players.get(i));
				match.setP2(players.get(i + 1));
				match.fight();
			}
			
//			for (Player player : players) {
//				if (player.getKillTime() == 2) {
//					players.remove(player);
//				}
//			}
			
			// Resort
			Arrays.sort(players.toArray());
			// TODO
			
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
