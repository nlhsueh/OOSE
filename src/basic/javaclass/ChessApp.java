package basic.javaclass;

public class ChessApp {

	public static void main(String[] args) {
		ChessGame game = new ChessGame();
		game.init(); // will generate 32 chesses
		Player p1 = new Player("John");
		Player p2 = new Player("Helen");

		game.addPlayer(p1);
		game.addPlayer(p2);
		game.show(); // show all status of the game

		int loc1 = 0;
		Chess c1 = game.getChess(loc1);
		p1.move(c1, 2);
		game.show(); // show all status of the game

	}

}

class Chess {
	String name; // for simple, just use A1, A2 as the chess name
	int weight;
	int location;

	public Chess(String name, int loc) {
		this.name = name;
		this.location = loc;

	}

	public void setLoc(int i) {
		// TODO Auto-generated method stub
		this.location = i;

	}
}

class ChessGame {

	Chess[] chess;
	int playerCnt = 0;
	Player p1;
	Player p2;

	/*
	 * show the all messages of the chess game, including - who are the players?
	 * - all chess name and their location - who's turn to play the next?
	 */
	void show() {
		for (int i = 0; i < 32; i++) {
			Chess c = chess[i];
			System.out.println(c.name + ", loc=" + c.location);
		}
		if (p1 != null && p2 != null)
			System.out.println(p1.name + " vs. " + p2.name);

	}

	public Chess getChess(int loc1) {
		// TODO Auto-generated method stub
		if (loc1 < 32)
			return chess[loc1];
		return null;
	}

	public void addPlayer(Player p) {
		// TODO Auto-generated method stub
		if (playerCnt == 0) {
			this.p1 = p;
			playerCnt++;
		} else
			this.p2 = p;
		p.setGame(this);

	}

	public void init() {
		// TODO Auto-generated method stub
		chess = new Chess[32];

		for (int i = 0; i < 16; i++) {
			chess[i] = new Chess("A" + i, i);

		}
		for (int i = 16; i < 32; i++) {
			chess[i] = new Chess("B" + (i - 16), i);
		}

	}

	public boolean move(Chess c1, int i) {
		// TODO Auto-generated method stub
		if (chess[i] == null) {
			chess[i] = c1;
			c1.setLoc(i);
			return true;
		} else {
			return false;
		}

	}

}

class Player {
	String name;
	int loc;
	ChessGame g;

	public Player(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	public void setGame(ChessGame chessGame) {
		// TODO Auto-generated method stub
		this.g = chessGame;

	}

	public void move(Chess c1, int i) {
		// TODO Auto-generated method stub
		if (!g.move(c1, i)) {
			System.out.println("move chess error");
		}

	}

}
