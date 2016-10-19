package observer;

import java.util.Observable;

public class ObserverTemplate {

	public static void main(String[] args) {
		Subject s = new Subject();

		View1 v1 = new View1();
		View2 v2 = new View2();
		s.addObserver(v1);
		s.addObserver(v2);
	}

}

class Subject extends java.util.Observable {
	int data;

	public Subject() {
		data = 0;
	}

	public void setData(int newValue) {
		data = newValue;
		this.setChanged();
		this.notifyObservers();
	}

}

class View1 implements java.util.Observer {
	public void update(Observable arg0, Object arg1) {
		// update the view
	}
}

class View2 implements java.util.Observer {
	public void update(Observable o, Object arg) {
		// update the view
	}
}