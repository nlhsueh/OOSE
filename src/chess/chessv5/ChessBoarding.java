package chess.chessv5;

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
 * Side by side
 */
class SideBySideChessBoarding implements ChessBoarding {
	public void setLocation(Chess[] chesses) {
		for (int i = 0; i < chesses.length / 2; i++) {
			chesses[i].setLoc(i * 2);
			chesses[i + 16].setLoc(i * 2 + 1);
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
