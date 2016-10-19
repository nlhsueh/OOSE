package observer.noapi;

import java.util.Vector;

public class ObserverNoAPI {
	public static void main(String[] arg) {
	}
}

class Client {
	void testObserver() {
		ConcreteSubject subject = new ConcreteSubject();
		ConcreteObserver observer = new ConcreteObserver();
		subject.addObserver(observer);
		subject.setState(new Integer(12));
		subject.setState(new Integer(23));
	}
}

abstract class Subject {
	private Vector<Observer> observers;

	protected void addObserver(Observer o) {
		observers.add(o);
	}

	protected void notifyObservers() {
		for (Observer o : observers)
			o.update(this);
	}
}

class ConcreteSubject extends Subject {
	private Object subjectState;

	public Object getState() {
		return subjectState;
	}

	void setState(Object newState) {
		subjectState = newState;
		notifyObservers();
	}
}

interface Observer {
	public void update(Subject s);
}

class ConcreteObserver implements Observer {
	public void update(Subject s) {
		try {
			ConcreteSubject cs = (ConcreteSubject) s;
			System.out.println("Updated: " + cs.getState());
		} catch (Exception e) {
			System.err.println("type mismatch");
		}
	}
}