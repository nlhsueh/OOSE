package adaptor.objectadaptor;

// Adaptee的specificRequest()對應到Target的request()
class Target {
	public void request(Object arg) {
		// ...
	}
}

class Adapter extends Target {
	Adaptee adaptee;

	public Adapter(Adaptee a) {
		this.adaptee = a;
	}

	public void request(Object arg) {
		adaptee.specificRequest(arg);
	}
}

class Adaptee {
	public void specificRequest(Object arg) {
		// ...
	}

}

class Client {
	public void main(String args[]) {
		Target t = new Adapter(new Adaptee());
		t.request(new Integer(1));
	}
}
