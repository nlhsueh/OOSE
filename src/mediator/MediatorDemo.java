package mediator;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MediatorDemo {
	public static void main(String[] args) {
		SimpleFrame sf = new SimpleFrame();
		sf.setSize(200, 100);
		sf.setVisible(true);
	}
}

class Mediator {

	JButton b1, b2, b3, reset;

	public void setColleage(JButton b1, JButton b2, JButton b3, JButton reset) {
		this.b1 = b1;
		this.b2 = b2;
		this.b3 = b3;
		this.reset = reset;
	}

	public void handle(ActionEvent e) {
		if (e.getActionCommand() == "b1") {
			b2.setEnabled(false);
			b3.setEnabled(false);
		} else if (e.getActionCommand() == "b2") {
			b1.setEnabled(true);
			b3.setEnabled(false);
		} else if (e.getActionCommand() == "b3") {
			b1.setEnabled(false);
			b2.setEnabled(true);
		} else if (e.getActionCommand() == "reset") {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
		} else {
			System.out.println("Incorrect message");
		}
	}
}

class SimpleFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton b1, b2, b3;
	JButton reset;

	public SimpleFrame() {
		Mediator med = new Mediator();
		b1 = new ButtonB1("b1", med);
		b2 = new ButtonB2("b2", med);
		b3 = new ButtonB3("b3", med);
		reset = new ButtonReset("reset", med);

		med.setColleage(b1, b2, b3, reset);

		// layout
		Container p = this.getContentPane();
		p.setLayout(new FlowLayout());
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(reset);

		setSize(200, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

class ButtonB1 extends JButton implements ActionListener {

	Mediator med;

	public ButtonB1(String title, Mediator med) {
		super(title);
		this.med = med;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		med.handle(arg0);
	}
}

class ButtonB2 extends JButton implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Mediator med;

	public ButtonB2(String title, Mediator med) {
		super(title);
		this.med = med;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		med.handle(arg0);
	}
}

class ButtonB3 extends JButton implements ActionListener {
	Mediator med;

	public ButtonB3(String title, Mediator med) {
		super(title);
		this.med = med;
		this.addActionListener(this);
	}

	public void actionPerformed(ActionEvent arg0) {
		med.handle(arg0);
	}
}

class ButtonReset extends JButton implements ActionListener {
	Mediator med;

	public ButtonReset(String title, Mediator med) {
		super(title);
		this.med = med;
		this.addActionListener(this);

	}

	public void actionPerformed(ActionEvent arg0) {
		med.handle(arg0);
	}
}