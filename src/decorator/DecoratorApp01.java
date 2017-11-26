abstract class AbstractTree {
	abstract void sing();
}

class Tree extends AbstractTree {
	void sing() {
		System.out.println("I am a Chrismax.");
	}
}

abstract class TreeDecorator extends AbstractTree {
	// YOUR CODE HERE

}

class Bell extends TreeDecorator {
	public Bell(AbstractTree c) {
		super(c);
	}

	void sing() {
		System.out.print("I have a Bell, ");
		// ONE LINE CODE
	}
}

class Candy extends TreeDecorator {
	public Candy(AbstractTree c) {
		super(c);
	}

	void sing() {
		System.out.print("I have a Candy, ");
		super.sing();
	}
}

class Gift extends TreeDecorator {
	public Gift(AbstractTree c) {
		super(c);
	}

	void sing() {
		// YOUR CODE HERE
	}
}

public class DecoratorApp02 {

	public static void main(String[] args) {
		// a simple tree
		AbstractTree t1 = new Tree();
		t1.sing();

		AbstractTree t2 = new Bell(t1);
		t2.sing();

		/*
		If I want to show
		I have a Bell, I have a Gift, I have a Candy, I am a Chrismax. 
		*/
		// YOUR CODE HERE
	}

}