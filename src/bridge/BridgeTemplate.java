package bridge;

interface Implementor {
	public void m1();
	public void m2();
}

class ConcreteImplA implements Implementor {
	public void m1() {
		// ...
	}

	public void m2() {
		// ...
	}
}

class ConcreteImplB implements Implementor {
	public void m1() {
		// ...
	}

	public void m2() {
		// ...
	}
}

abstract class Abstraction {
	Implementor imp;

	public Abstraction(Implementor imp) {
		this.imp = imp;
	}

	protected void m1() {
		imp.m1();
	}

	protected void m2() {
		imp.m2();
	}

	abstract void operation();
}

class RefinedAbstraction1 extends Abstraction {
	public RefinedAbstraction1(Implementor imp) {
		super(imp);
	}

	// 每個 RefinedAbstraction 執行 operation 的方式可能不同。先 m1() 再 m2()
	void operation() {
		m1();
		m2();
		// ...
	}
}

class RefinedAbstraction2 extends Abstraction {
	public RefinedAbstraction2(Implementor imp) {
		super(imp);
	}

	// 每個 RefinedAbstraction 執行 operation 的方式可能不同。先 m2() 再 m1()
	void operation() {
		m2();
		m1();
		// ...
	}
}