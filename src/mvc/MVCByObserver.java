package mvc;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;

/* MODEL */
class Counter extends Observable {
	int counter = 0;

	public void incCounter() {
		counter++;
		setChanged();
		notifyObservers(new Integer(counter));
	}

	public int getCounter() {
		return counter;
	}
}

/* VIEW */
class CounterView extends JFrame implements Observer {
	static int id = 0; //just for setting location
	private TextField tf = new TextField(10);

	public CounterView(String title) {
		super(title);
		tf.setText("0");
		add(tf);
		this.setSize(200, 100);
		this.setLocation(100 + (id++) * 200, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void update(Observable arg0, Object arg1) {
		int c = ((Integer) arg1).intValue();
		tf.setText(String.valueOf(c));
	}
}

/* CONTROLLER */
class CounterController extends JFrame implements ActionListener {
	static int id = 0; //just for setting location
	private JButton inc = new JButton("INC");
	Counter c;

	public CounterController(Counter c) {
		this.c = c;
		inc.addActionListener(this);

		add(inc);
		this.setTitle("Controller " + id);
		this.setSize(200, 100);
		this.setLocation(100 + (id++) * 200, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		c.incCounter();
	}
}

public class MVCByObserver {
	public static void main(String[] args) {
		Counter c = new Counter();
		CounterView view1 = new CounterView("View0");
		view1.setVisible(true);
		CounterView view2 = new CounterView("View1");
		view2.setVisible(true);
		c.addObserver(view1); //add view
		c.addObserver(view2); //add view
		CounterController controller1 = new CounterController(c);
		controller1.setVisible(true);
		CounterController controller2 = new CounterController(c);
		controller2.setVisible(true);
	}
}