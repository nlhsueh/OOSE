package template;

abstract class Game {
	protected int playersCount;

	// TEMPLATE METHOD
	final void playOneGame(int playersCount) {
		this.playersCount = playersCount;
		initializeGame();
		int j = 0;
		while (!endOfGame()) {
			makePlay(j);
			j = (j + 1) % playersCount;
		}
		printWinner();
	}

	abstract void initializeGame(); // PRIMITIVE METHOD

	abstract void makePlay(int player); // PRIMITIVE METHOD

	abstract boolean endOfGame(); // PRIMITIVE METHOD

	abstract void printWinner(); // PRIMITIVE METHOD
}

class Monopoly extends Game {
	void initializeGame() {
		// ...
	}

	void makePlay(int player) {
		// ...
	}

	boolean endOfGame() {
		// ...
	}

	void printWinner() {
		// ...
	}
}

class ChessGame extends Game {
	void initializeGame() {
		// ...
	}

	void makePlay(int player) {
		// ...
	}

	boolean endOfGame() {
		// ...
	}

	void printWinner() {
		// ...
	}
}

public class GameApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
