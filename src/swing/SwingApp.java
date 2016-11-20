import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;

public class SwingApp {
	public static void main(String[] args) {
		MainPanel p = new MainPanel();

		p.setVisible(true);
	}
}

class MainPanel extends JFrame implements ActionListener {
	static int id = 0; //just for setting location
	private JButton inc = new JButton("INC");
	private JButton dec = new JButton("DEC");

	public MainPanel() {
		inc.addActionListener(this);
		dec.addActionListener(this);

		getContentPane().setLayout(new FlowLayout());
		add(inc);
		add(dec);
		this.setTitle("demo");
		this.setSize(200, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand() == "INC") {
			System.out.println("INC");
		}	
		else {
			System.out.println("DEC");
		}
	}
}

