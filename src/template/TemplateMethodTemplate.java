package template;

abstract class AbstractClass {
	final void templateMethod() {
		System.out.println("Template method");
		primitiveOp1();
		primitiveOp2();
	}
	abstract void primitiveOp1();
	abstract void primitiveOp2();
}

class ConcreteClass extends AbstractClass {
	void primitiveOp1() {
		System.out.println("Primitive operation 1");
	}
	void primitiveOp2() {
		System.out.println("Primitive operation 2");
	}
}

public class TemplateMethodTemplate {
	
}
