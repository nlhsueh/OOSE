package mediator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BookStoreDemo extends JFrame implements ActionListener {
	IMediator med = new BookStoreMediator();

	BookStoreDemo() {
		JPanel p = new JPanel();
		p.add(new BtnView(this, med));
		p.add(new BtnBook(this, med));
		p.add(new BtnSearch(this, med));
		getContentPane().add(new LblDisplay(med), "North");
		getContentPane().add(p, "South");
		setSize(300, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 所有的 Colleague 都有統一的介面：Command
	public void actionPerformed(ActionEvent ae) {
		Command comd = (Command) ae.getSource();
		comd.execute();
	}

	public static void main(String[] args) {
		new BookStoreDemo();
	}
}

// Colleague interface
interface Command {
	void execute();
}

// Abstract Mediator, 定義所有 Mediator 的規格
interface IMediator {
	public void book();

	public void view();

	public void search();

	public void registerView(BtnView v);

	public void registerSearch(BtnSearch s);

	public void registerBook(BtnBook b);

	public void registerDisplay(LblDisplay d);
}

// Concrete mediator
class BookStoreMediator implements IMediator {

	BtnView btnView;
	BtnSearch btnSearch;
	BtnBook btnBook;
	LblDisplay show;

	// 註冊後 mediator 才知道 BtnView
	public void registerView(BtnView v) {
		btnView = v;
	}

	public void registerSearch(BtnSearch s) {
		btnSearch = s;
	}

	public void registerBook(BtnBook b) {
		btnBook = b;
	}

	public void registerDisplay(LblDisplay d) {
		show = d;
	}

	// book 時所有需要溝通設定的都在這裡進行
	public void book() {
		btnBook.setEnabled(false);
		btnView.setEnabled(true);
		btnSearch.setEnabled(true);
		show.setText("Booking...");
	}

	// view 時所有需要溝通設定的都在這裡進行
	public void view() {
		btnView.setEnabled(false);
		btnSearch.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("Viewing...");
	}

	// search 時所有需要溝通設定的都在這裡進行
	public void search() {
		btnSearch.setEnabled(false);
		btnView.setEnabled(true);
		btnBook.setEnabled(true);
		show.setText("Searching...");
	}
}

// A concrete colleague
class BtnView extends JButton implements Command {
	IMediator med;

	// colleague 也需要知道 mediator
	BtnView(ActionListener al, IMediator m) {
		super("View");
		addActionListener(al);
		med = m;
		med.registerView(this);
	}

	// 轉呼叫 medicator 來處理
	public void execute() {
		med.view();
	}
}

// A concrete colleague
class BtnSearch extends JButton implements Command {
	IMediator med;

	BtnSearch(ActionListener al, IMediator m) {
		super("Search");
		addActionListener(al);
		med = m;
		med.registerSearch(this);
	}

	public void execute() {
		med.search();
	}

}

// A concrete colleague
class BtnBook extends JButton implements Command {
	IMediator med;

	BtnBook(ActionListener al, IMediator m) {
		super("Book");
		addActionListener(al);
		med = m;
		med.registerBook(this);
	}

	public void execute() {
		med.book();
	}

}

class LblDisplay extends JLabel {
	IMediator med;

	LblDisplay(IMediator m) {
		super("Just start...");
		med = m;
		med.registerDisplay(this);
		setFont(new Font("Arial", Font.BOLD, 24));
	}
}
