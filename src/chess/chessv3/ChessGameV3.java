package chess.chessv3;

/*
 * Force every chess game to have two players
 */
abstract class AbstractGame {
	abstract void setPlayers(Player p1, Player p2);
}

/*
 * Version 3 chess game
 */
public class ChessGameV3 extends AbstractGame {
	public static final int BLACK = 0, RED = 1;
	Chess[] black;
	Chess[] red;
	Player p1, p2;

	public static void main(String args[]) {
		// initialize the game
		ChessGameV3 game = new ChessGameV3();
		game.setPlayers(new Player("Mary"), new Player("Jack"));
		game.generateAllChess();
		game.showAllChess();
	}

	Chess makeChess(String name, int weight, int side, int loc) {
		return new Chess(name, weight, side, loc);
	}

	/*
	 * Using Factory method to generate all chess. This will make the 
	 * chess generation more flexible, can be extended in the child class. 
	 */
	void generateAllChess() {
		black = new Chess[] { makeChess("將", 1, BLACK, 0),
				makeChess("士", 2, BLACK, 1), makeChess("士", 2, BLACK, 2),
				makeChess("象", 3, BLACK, 3), makeChess("象", 3, BLACK, 4),
				makeChess("車", 3, BLACK, 5), makeChess("車", 3, BLACK, 6),
				makeChess("馬", 3, BLACK, 7), makeChess("馬", 3, BLACK, 8),
				makeChess("包", 3, BLACK, 9), makeChess("包", 3, BLACK, 10),
				makeChess("卒", 3, BLACK, 11), makeChess("卒", 3, BLACK, 12),
				makeChess("卒", 3, BLACK, 13), makeChess("卒", 3, BLACK, 14),
				makeChess("卒", 3, BLACK, 15), };

		red = new Chess[] { makeChess("帥", 1, RED, 16),
				makeChess("仕", 2, RED, 17), makeChess("仕", 2, RED, 18),
				makeChess("相", 3, RED, 19), makeChess("相", 3, BLACK, 20),
				makeChess("俥", 3, BLACK, 21), makeChess("俥", 3, BLACK, 22),
				makeChess("傌", 3, BLACK, 23), makeChess("傌", 3, BLACK, 24),
				makeChess("炮", 3, BLACK, 25), makeChess("炮", 3, BLACK, 26),
				makeChess("兵", 3, BLACK, 27), makeChess("兵", 3, BLACK, 28),
				makeChess("兵", 3, BLACK, 29), makeChess("兵", 3, BLACK, 30),
				makeChess("兵", 3, BLACK, 31), };

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
	// name, weight, side (black/red), location (1-32)
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
 * Location of a chess. It is composed of (x,y) x should be in the range (0,3),
 * y: (0, 7)
 */
class Location {
	int x, y;

	public Location(int x, int y) {
		boolean xOK = (x >= 0 && x <= 3);
		boolean yOK = (y >= 0 && y <= 7);
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
		int x = (loc / 8);
		int y = (loc % 8);
		return new Location(x, y);
	}
}