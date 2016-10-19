package chess.chessv4;

import java.util.HashMap;
import java.util.Map;

/*
 * 規範每一個遊戲都應該有兩個玩家
 */
abstract class AbstractGame {
	abstract void setPlayers(Player p1, Player p2);
}

/*
 * Version 4 採用 Strategy 讓棋子的初始位置可以彈性設定。 
 * 新增了棋盤類別 ChessBoard, 記錄每一個位置對應的棋子，也提供介面讓使用者對某個位子進行選擇、吃子的動作。
 */
public class ChessGameV4 extends AbstractGame {
	public static final int BLACK = 0, RED = 1;
	Chess[] chesses;
	Player p1, p2;

	public static void main(String args[]) {
		// initialize the game
		ChessGameV4 game = new ChessGameV4();
		game.setPlayers(new Player("Mary"), new Player("Jack"));
		game.generateAllChess();
		ChessBoard cb = new ChessBoard();
		// game.setChessBoarding(cb, new SimpleChessBoarding());
		game.setChessBoarding(cb, new RandomChessBoarding());

		// 模擬一些使用者的行為
		cb.select(12);
		cb.kill(13);

		cb.showBoard();
	}

	protected void setChessBoarding(ChessBoard cb, ChessBoarding boarding) {
		boarding.setLocation(chesses);
		cb.putChess(chesses);
	}

	Chess makeChess(String name, int side) {
		return new Chess(name, side);
	}

	/*
	 * 採用 Factory Method 來產生棋子
	 */
	void generateAllChess() {
		chesses = new Chess[] { makeChess("將", BLACK), makeChess("士", BLACK),
				makeChess("士", BLACK), makeChess("象", BLACK),
				makeChess("象", BLACK), makeChess("車", BLACK),
				makeChess("車", BLACK), makeChess("馬", BLACK),
				makeChess("馬", BLACK), makeChess("包", BLACK),
				makeChess("包", BLACK), makeChess("卒", BLACK),
				makeChess("卒", BLACK), makeChess("卒", BLACK),
				makeChess("卒", BLACK), makeChess("卒", BLACK),
				makeChess("帥", RED), makeChess("仕", RED), makeChess("仕", RED),
				makeChess("相", RED), makeChess("相", BLACK),
				makeChess("俥", BLACK), makeChess("俥", BLACK),
				makeChess("傌", BLACK), makeChess("傌", BLACK),
				makeChess("炮", BLACK), makeChess("炮", BLACK),
				makeChess("兵", BLACK), makeChess("兵", BLACK),
				makeChess("兵", BLACK), makeChess("兵", BLACK),
				makeChess("兵", BLACK) };
	}

	void setPlayers(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	void showAllChess() {
		System.out.println("Players: " + p1 + " vs. " + p2);
		for (Chess c : chesses) {
			System.out.println(c);
		}
	}
}

class Chess {
	public static final int CREATED = 0;
	public static final int SELECTED = 1;
	public static final int KILLED = 2;

	private String name;
	private int weight;
	private int side;
	private int loc;
	private int status;

	public Chess(String name, int side) {
		this.name = name;
		this.side = side;
		status = Chess.CREATED;
	}

	public String getName() {
		return name;
	}

	public Location getLocation() {
		return Location.getLocation(loc);
	}

	public int getLoc() {
		return loc;
	}

	void setLoc(int i) {
		this.loc = i;
	}

	public String toString() {
		return name + "," + " loc=" + getLocation() + ", staus =" + status;
	}

	public void setStatus(int s) {
		this.status = s;
	}

	// 是否被選了
	public boolean isSelected() {
		return status == Chess.SELECTED ? true : false;
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

	public Location(int loc) {
		this(loc / 8, loc % 8);
	}

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

	int getLoc() {
		int result = 8 * x + y;
		return result;
	}
}

class ChessBoard {
	// board 記錄每一個 Location 對應的棋子
	Map<Location, Chess> board = new HashMap<Location, Chess>();
	Location[] locs;
	Chess[] chesses;

	// put the chess to the board
	public void putChess(Chess[] chesses) {
		this.chesses = chesses;
		locs = new Location[chesses.length];
		for (int i = 0; i < chesses.length; i++) {
			locs[i] = new Location(i);
		}
		for (int i = 0; i < chesses.length; i++) {
			Chess c = chesses[i];
			int loc = c.getLoc();
			board.put(locs[loc], c);
		}
	}

	public void kill(int loc) {
		Chess c = (Chess) board.get(locs[loc]);
		c.setStatus(Chess.KILLED);
		board.put(locs[loc], null);
	}

	public void select(int loc) {
		Chess c = (Chess) board.get(locs[loc]);
		c.setStatus(Chess.SELECTED);
	}

	void showBoard() {
		for (int i = 0; i < locs.length; i++) {
			Chess c = (Chess) (board.get(locs[i]));
			boolean noChessOnLocation = (c == null) ? true : false;
			if (noChessOnLocation)
				System.out.print("  ");
			else if (c.isSelected())
				System.out.print("[" + c.getName() + "]");
			else
				System.out.print(c.getName());
			// new row
			if ((i + 1) % 8 == 0)
				System.out.println("");
			else
				System.out.print("\t");
		}
	}
}

/*
 * 設定棋子位置的方法。Strategy 設計樣式中的 AbstractStrategy 
 */
interface ChessBoarding {
	public void setLocation(Chess[] chesses);
}

/*
 * 隨機的設定棋子的位置
 */
class RandomChessBoarding implements ChessBoarding {

	public void setLocation(Chess[] chesses) {
		// initialize location
		for (int i = 0; i < chesses.length; i++) {
			chesses[i].setLoc(i);
		}
		// exchange location
		for (int i = 0; i < chesses.length / 2; i++) {
			int a = (int) (Math.random() * 32);
			int b = (int) (Math.random() * 32);
			Chess chessA = chesses[a];
			Chess chessB = chesses[b];
			int chessALocation = chessA.getLoc();
			// switch the location
			chessA.setLoc(chessB.getLoc());
			chessB.setLoc(chessALocation);
		}
	}
}

/*
 * 簡易的棋子排版，會依照 將士...卒帥仕...兵 的方式排版
 */
class SimpleChessBoarding implements ChessBoarding {
	public void setLocation(Chess[] chesses) {
		for (int i = 0; i < chesses.length; i++) {
			chesses[i].setLoc(i);
		}
	}
}