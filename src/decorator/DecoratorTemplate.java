package decorator;

abstract class Component {
	abstract void op();
}

class ConcreteComponent extends Component {
	void op() {
		System.out.println("Basic behavior");
	}
}

abstract class Decorator extends Component {
	Component c;

	public Decorator(Component c) {
		this.c = c;
	}

	void op() {
		c.op();
	}
}

class ConcreteDecorator1 extends Decorator {
	public ConcreteDecorator1(Component c) {
		super(c);
	}

	void op() {
		super.op();
		addedBehavior();
	}

	void addedBehavior() {
		System.out.println("Added behavior 1");
	}
}

class ConcreteDecorator2 extends Decorator {
	public ConcreteDecorator2(Component c) {
		super(c);
	}

	void op() {
		super.op();
		addedBehavior();
	}

	void addedBehavior() {
		System.out.println("Added behavior 2");
	}
}

public class DecoratorTemplate {
	public static void main(String[] args) {
		Component cc = new ConcreteComponent();
		cc.op();
		Component c1 = new ConcreteDecorator1(new ConcreteDecorator2(cc));
		c1.op();
	}
}
