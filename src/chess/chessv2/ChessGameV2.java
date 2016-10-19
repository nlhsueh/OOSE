package chess.chessv2;

/*
 * Force every chess game to have two players
 */
abstract class AbstractGame {
	abstract void setPlayers(Player p1, Player p2);
}

/*
 * Version 2 chess game
 */
public class ChessGameV2 extends AbstractGame {
	public static final int BLACK = 0, RED = 1;
	Chess[] black;
	Chess[] red;
	Player p1, p2;

	public static void main(String args[]) {
		// initialize the game
		ChessGameV2 game = new ChessGameV2();
		game.setPlayers(new Player("Mary"), new Player("Jack"));
		game.generateChess();
		game.showAllChess();
	}

	/**
	 * Set all (32 chess), including their name, weight, location.
	 */
	void generateChess() {
		black = new Chess[] { new Chess("將", 1, BLACK, 0),
				new Chess("士", 2, BLACK, 1),
				new Chess("士", 2, BLACK, 2),
				new Chess("象", 3, BLACK, 3),
				new Chess("象", 3, BLACK, 4),
				new Chess("車", 3, BLACK, 5),
				new Chess("車", 3, BLACK, 6),
				new Chess("馬", 3, BLACK, 7),
				new Chess("馬", 3, BLACK, 8),
				new Chess("包", 3, BLACK, 9),
				new Chess("包", 3, BLACK, 10),
				new Chess("卒", 3, BLACK, 11),
				new Chess("卒", 3, BLACK, 12),
				new Chess("卒", 3, BLACK, 13),
				new Chess("卒", 3, BLACK, 14),
				new Chess("卒", 3, BLACK, 15), };

		red = new Chess[] { new Chess("帥", 1, RED, 16),
				new Chess("仕", 2, RED, 17),
				new Chess("仕", 2, RED, 18),
				new Chess("相", 3, RED, 19),
				new Chess("相", 3, BLACK, 20),
				new Chess("俥", 3, BLACK, 21),
				new Chess("俥", 3, BLACK, 22),
				new Chess("傌", 3, BLACK, 23),
				new Chess("傌", 3, BLACK, 24),
				new Chess("炮", 3, BLACK, 25),
				new Chess("炮", 3, BLACK, 26),
				new Chess("兵", 3, BLACK, 27),
				new Chess("兵", 3, BLACK, 28),
				new Chess("兵", 3, BLACK, 29),
				new Chess("兵", 3, BLACK, 30),
				new Chess("兵", 3, BLACK, 31), };

		// change black's location
		for (int i = 0; i < 16; i++) {
			int target = (int) (Math.random() * 32);
			if (target < 16) {
				int originalLoc = black[i].loc;
				black[i].loc = target;
				black[target].loc = originalLoc;
			} else if (target < 32) {
				target -= 16;
				int originalLoc = black[i].loc;
				black[i].loc = target;
				red[target].loc = originalLoc;
			} else {
				System.out.println("Location Error");
			}
		}

	}

	@Override
	void setPlayers(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	/**
	 * Show all chess in the game
	 */
	void showAllChess() {
		System.out.println("Players: " + p1 + " vs. " + p2);
		for (Chess c : black) {
			System.out.println(c);
		}
		for (Chess c : red) {
			System.out.println(c);
		}
	}

}

class Chess {

	public Chess(String name, int weight, int side, int loc) {
		this.name = name;
		this.weight = weight;
		this.side = side;
		this.loc = loc;
	}

	String name;
	int weight;
	int side;
	int loc;

	public String toString() {
		return name + ", \t" + weight + ", loc=" + this.getLocation();
	}

	public Location getLocation() {
		return Location.getLocation(loc);
	}

	public int getLoc() {
		return loc;
	}
}

class Player {
	String name;

	public Player(String n) {
		this.name = n;
	}

	public String toString() {
		return name;
	}
}

/*
 * Location of a chess. It is composed of (x,y)
 * x should be in the range (0,7), y: (0, 3)
 */
class Location {
	int x, y;

	public Location(int x, int y) {
		boolean xOK = (x >= 0 && x <= 7);
		boolean yOK = (y >= 0 && y <= 3);
		if (!(xOK && yOK)) {
			System.out.println("Location Error:" + x + ", " + y);
			System.exit(0);
		}
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	public static Location getLocation(int loc) {
		int x = (loc / 4);
		int y = (loc % 4);
		return new Location(x, y);
	}
}