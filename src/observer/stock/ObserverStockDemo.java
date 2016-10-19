package observer.stock;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ObserverStockDemo extends JFrame implements ActionListener {
	Stock s;
	CurrentPriceBoard priceBoard;

	public ObserverStockDemo() {
		super();

		s = new Stock(100);
		priceBoard = new CurrentPriceBoard();
		s.addObserver(priceBoard);

		this.setLayout(new GridLayout(2, 2));
		add(this.priceBoard);
		JButton startBtn = new JButton("Change");
		startBtn.addActionListener(this);
		add(startBtn);
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		Random r = new Random();
		double diff = (r.nextDouble() * 0.07);
		boolean positive = r.nextBoolean();
		double newPrice;
		if (positive)
			newPrice = s.getPrice() * (1.0 + diff);
		else
			newPrice = s.getPrice() * (1.0 - diff);
		s.setPrice(Math.round(newPrice));
	}

	public static void main(String[] args) {
		ObserverStockDemo demo = new ObserverStockDemo();
		demo.setVisible(true);
	}

}

class Stock extends java.util.Observable {
	double yesdayPrice;
	double currentPrice;
	int currentAmount;

	public Stock(int yesterday) {
		this.yesdayPrice = yesterday;
		this.currentPrice = this.yesdayPrice;
		this.setChanged();
		this.notifyObservers();
	}

	void setPrice(double p) {
		this.currentPrice = p;
		this.setChanged();
		this.notifyObservers();
	}

	double getPrice() {
		return this.currentPrice;
	}

	void setAmount(int a) {
		this.currentAmount = a;
		this.setChanged();
		this.notifyObservers();
	}
}

class CurrentPriceBoard extends JPanel implements java.util.Observer {
	JTextField price = new JTextField(10);

	public CurrentPriceBoard() {
		super();
		add(new JLabel("Current price"));
		add(price);
	}

	public void update(Observable arg0, Object arg1) {
		double p = ((Stock) arg0).getPrice();
		price.setText(Double.toString(p));
	}
}