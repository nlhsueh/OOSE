package mvc.noobserver;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;

/* MODEL */
class Counter {
	int counter = 0;

	public void incCounter() {
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}

/* VIEW */
class CounterView extends JFrame  {
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

	public void update(int v) {
		tf.setText(String.valueOf(v));
	}
}

/* CONTROLLER */
class CounterController extends JFrame implements ActionListener {
	static int id = 0; //just for setting location
	private JButton inc = new JButton("INC");
	Counter c;
	CounterView v;

	public CounterController(Counter c, CounterView v) {
		this.c = c;
		inc.addActionListener(this);
		this.v = v;
		add(inc);
		this.setTitle("Controller " + id);
		this.setSize(200, 100);
		this.setLocation(100 + (id++) * 200, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		c.incCounter();
		v.update(c.getCounter());
	}
}

public class NoObserverDemo {

	public static void main(String[] args) {
		Counter c = new Counter();

		CounterView view1 = new CounterView("View0");
		view1.setVisible(true);
		CounterView view2 = new CounterView("View1");
		view2.setVisible(true);

		CounterController controller1 = new CounterController(c,view1);
		controller1.setVisible(true);
		CounterController controller2 = new CounterController(c,view2);
		controller2.setVisible(true);
	}
}