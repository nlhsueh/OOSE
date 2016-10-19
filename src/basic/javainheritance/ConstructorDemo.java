package basic.javainheritance;

public class ConstructorDemo {
	public static void main(String[] args) {

	}
}

class SuperClass {

}

class SubClass extends SuperClass {

}

class SuperClass2 {
	public SuperClass2(int x) {

	}
}

// This will have compile error because its superclass has no default
// constructor
class SubClass2 extends SuperClass2 {
	public SubClass2(int x) {
		super(x);
	}
}