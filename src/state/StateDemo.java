package state;

public class StateDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		A a = new A();
		System.out.println(a);
		a.request();
		System.out.println(a);
		a.request();
		System.out.println(a);
		a.request();
		System.out.println(a);
		a.request();
		System.out.println(a);
	}
}

class A {
	State s;

	// the default state is S1
	public A() {
		s = new S1();
	}

	public void request() {
		s.handleRequest(this);
	}

	void setState(State _s) {
		this.s = _s;
	}

	public String toString() {
		return s.toString();
	}

}

abstract class State {
	abstract public void handleRequest(A a);
}

class S1 extends State {

	@Override
	public void handleRequest(A a) {
		a.setState(new S2());
	}

	public String toString() {
		return "S1";
	}
}

class S2 extends State {
	public void handleRequest(A a) {
		a.setState(new S3());
	}

	public String toString() {
		return "S2";
	}

}

class S3 extends State {
	public void handleRequest(A a) {
		a.setState(new S1());
	}

	public String toString() {
		return "S3";
	}

}
