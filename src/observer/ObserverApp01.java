import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

/* MODEL */
class Counter extends Observable {
	int counter = 0;

	public void incCounter() {
		counter++;
		setChanged();
		notifyObservers(new Integer(counter));
	}

	// YOU CODE HERE
	public void decCounter() {
	}


	public int getCounter() {
		return counter;
	}
}

/* VIEW */
class Watch extends JFrame implements Observer {
	static int id = 0; //just for setting location
	private TextField tf = new TextField(10);

	public Watch(String title) {
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

/* View */
class Monitor implements Observer {

	// YOU CODE HERE
	public void update(?) {
		//?
		System.out.println("MONITOR report: Counter changes to " + c);
	}
}

/* CONTROLLER */
class CounterController extends JFrame implements ActionListener {
	static int id = 0; //just for setting location
	private JButton inc = new JButton("INC");
	private JButton dec = new JButton("DEC");

	Counter c;

	public CounterController(Counter c) {
		this.c = c;
		inc.addActionListener(this);
		dec.addActionListener(this);

		getContentPane().setLayout(new FlowLayout());
		add(inc);
		add(dec);
		this.setTitle("Counter Panel");
		this.setSize(200, 100);
		this.setLocation(100 + (id++) * 200, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "INC") {
			c.incCounter();
		}	
		else {
			c.decCounter();
		}
	}
}

public class ObserverApp01 {
	public static void main(String[] args) {
		Counter c = new Counter();

		Watch watch = new Watch("Watch");
		watch.setVisible(true);
		Monitor monitor = new Monitor();

		c.addObserver(watch); //add view
		// YOU CODE HERE -- add a monitor to the counter

		CounterController controller1 = new CounterController(c);
		controller1.setVisible(true);
	}
}