/**
 * This program is design for demo "dependency inversion principle"
 * - the ButtonPanel is a general panel that can be used to turn on/off many clients
 * - the devices want to connect to the panel should implement the ButtonClient
 */
package principle.dip;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ButtonPanelDemo extends JFrame {
	private static final long serialVersionUID = 4648629127697664508L;

	public static void main(String[] args) {
		ButtonPanelDemo d = new ButtonPanelDemo("Demo");
	}

	public ButtonPanelDemo(String title) {
		super(title);
		ButtonPanel buttonPanel = new ButtonPanel();

		this.add(buttonPanel);
		this.setSize(180, 100);

		this.setVisible(true);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		buttonPanel.connect(new Computer("My mac"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/*
 * The ButtonPanel does not depends on the detail object, it 
 * depends on the abstract "ButtonClient"
 */
class ButtonPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton onButton;
	JButton offButton;
	JLabel status;

	ButtonClient bc;

	public ButtonPanel() {
		onButton = new JButton("ON");
		offButton = new JButton("OFF");
		status = new JLabel("Panel is created");
		onButton.addActionListener(this);
		offButton.addActionListener(this);
		this.setLayout(new FlowLayout());
		this.add(onButton);
		this.add(offButton);
		this.add(status);
	}

	/*
	 * Bind the real object to the panel
	 */
	public void connect(ButtonClient bc) {
		this.bc = bc;
		setStatus(bc.toString() + " is connected");
	}

	public void setStatus(String status) {
		this.status.setText(status);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "ON") {
			bc.turnOn(this);
		} else if (arg0.getActionCommand() == "OFF") {
			bc.turnOff(this);
		}
	}

}

interface ButtonClient {
	public void turnOn(ButtonPanel bp);
	public void turnOff(ButtonPanel bp);
}

/*
 * A computer is a button client that can be connected to
 * the ButtonPanel
 */
class Computer implements ButtonClient {
	String title;

	public Computer(String title) {
		this.title = title;
	}

	public void turnOn(ButtonPanel bp) {
		bp.setStatus(title + " is ON");

	}

	public void turnOff(ButtonPanel bp) {
		bp.setStatus(title + " is OFF");
	}

	public String toString() {
		return title;
	}

}

/*
 * A lamp is a button client that can be connected to
 * the ButtonPanel
 */
class Lamp implements ButtonClient {
	String title;

	public Lamp(String title) {
		this.title = title;
	}

	public void turnOn(ButtonPanel bp) {
		bp.setStatus(title + " is ON");

	}

	public void turnOff(ButtonPanel bp) {
		bp.setStatus(title + " is OFF");
	}

	public String toString() {
		return title;
	}

}