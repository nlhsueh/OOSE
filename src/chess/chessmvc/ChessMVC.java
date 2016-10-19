package chess.chessmvc;

import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JPanel;

public class ChessMVC {

}

abstract class GamePlayPanel extends JPanel implements MouseListener {
	   protected void eat(Chess b) {
	       assert (selectedChess != null) : "the eater is null";
	       if ( rule.canEat(selectedChess, b) ) {
	          int eaterLoc = selectedChess.getLocation();
	          int eatenLoc = b.getLocation();
	          game.eatChess(selectedChess, b);
	          selectedChess = null;
	       }
	       repaint(); //要求重新繪出棋盤
	   }
	   protected void moveTo(int newLoc) {
	        assert (selectedChess != null) : "the mover is null";
	        if (rule.canMove(selectedChess, newLoc)) {
	   	       int loc = selectedChess.getLocation();
	   	       game.moveTo(selectedChess, newLoc);
	           selectedChess = null;
	        }
	   	    repaint();//要求重新繪出棋盤
	   }
	}

 abstract class ChessGame {
	   private transient Vector chessGameListener;
	   public void moveTo(Chess c, int newLoc) {
		 fireChessMoveEvent(new ChessMoveEvent(c, originalLoc));
	   }
	   public void eatChess(Chess a, Chess b) {
	       …
	       fireChessEatEvent(new ChessEatEvent(a, b));
	       if ( !isGameOver() ) {
	          …
	       }
	       else {
		       status = GAME_OVER;
		       fireGameOverEvent(new GameOverEvent(this, getWinnerString()));
	       }
	   }

	    public synchronized void addChessGameListener (ChessGameListener l) {
	      Vector v = chessGameListener == null ? new Vector(2) :
	          (Vector) chessGameListener.clone();
	      if (!v.contains(l)) {
	         v.addElement(l);
	         chessGameListener = v;
	      }
	    }

	    protected void fireGameOverEvent(GameOverEvent e) {
	      if (chessGameListener != null) {
	        Vector listeners = chessGameListener;
	        int count = listeners.size();
	        for (int i = 0; i < count; i++) {
	            ((ChessGameListener) listeners.elementAt(i)).gameOver(e);
	        }
	      }
	    }
	    protected void fireChessEatEvent(ChessEatEvent e) {
	       …
	       ((ChessGameListener) listeners.elementAt(i)).eatChess(e);
	    }
	    protected void fireChessMoveEvent(ChessMoveEvent e) {
	       …
	       ((ChessGameListener) listeners.elementAt(i)).moveChess(e);
	    }

	    
	     class GameSNG extends JFrame implements ChessGameListener {
	    	   String message="";
	    	   JTextArea ta = new JTextArea(4，20);
	    	   JScrollPane sp = new JScrollPane(ta);
	    	   …
	    	   public void gameOver(GameOverEvent e) {
	    	      ta.append(\nGame Over);
	    	   }

	    	   public void chessMove(ChessMoveEvent e) {
	    		  Chess c = (Chess)(e.getSource());
	    		  message = "\nChess " + c.getName() + " move from " + e.getOriginalLoc() + " to " + c.getLocation();
	    		  ta.append(message);
	    	   }

	    	   public void chessEat(ChessEatEvent e) {
	    	      message =  "\nChess " + ((Chess)(e.getSource())).getName() + " eat " +e.getTarget().getName();
	    		  ta.append(message);
	    	   }
	    	}

	    
	     class GameStatistic extends JFrame implements ChessGameListener {
	    	   int redDied = 0;
	    	   int blackDied = 0;
	    	   JTextArea ta = new JTextArea(2，20);

	    	   public GameStatistic(GameFactory gf) {
	    		   super("Game Statistic");
	    		   ChessGame game = gf.getChessGame();
	    		   game.addChessGameListener(this);
	    		   getContentPane().setLayout(new BorderLayout());
	    		   getContentPane().add(ta);
	    		   ta.setEditable(false);
	    		   setSize(200，100);
	    	   }
	    	   public void gameOver(GameOverEvent e) {
	    	      ta.append("Game Over");
	    	   }
	    	   public void chessMove(ChessMoveEvent e) {}

	    	   public void chessEat(ChessEatEvent e) {
	    	      Chess eaten = (Chess)e.getTarget();
	    	      if (eaten.getSide() == Chess.RED)
	    	         redDied ++;
	    	      else blackDied++;
	    	      String message = "RED died: " + redDied + "\nBLACK died:
	    	      " + blackDied;
	    	      ta.setText(message);
	    	   }
	    	   public void changeStatus(EventObject e) {}
	    	}

	    
	     class TChessApp extends JFrame {
	    	   public TChessApp () {
	    	        super("短棋");
	    	        GameFactory gf = new TGameFactory();
	    	        GamePlayPanel chessPanel = new TGamePlayPanel(gf);
	    	        GameSNG sng = new GameSNG();
	    	        GameStatistic statistic = new GameStatistic();

	    	        ChessGame game = chessPanel.getGame();
	    	        game.addChessGameListener(sng);
	    	        game.addChessGameListener(statistic);
	    	        sng.show();
	    	        statistic.show();
	    	        …
	    	   }
	    	}
