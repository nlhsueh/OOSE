package refactoring;

public class RefactoringDemo {

}

//This is a simple example for demo JUnit
class Pair {
	
	int a;
	int b;
	
	public Pair() {
		a =0;
		b=0;
	}
	public Pair(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public  int add() {
		return a+b;
	}
}
