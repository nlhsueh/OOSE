package strategy;

class Context {
	Strategy s;
	
	public Context(Strategy s) {
		this.s = s;
	}
	public void doIt() {
		System.out.println("Doing something");
		s.execute();
	}
}

class Strategy1 implements Strategy {
	public void execute() {
		System.out.println("Using strategy 1");
	}
}

class Strategy2 implements Strategy {
	public void execute() {
		System.out.println("Using strategy 2");
	}
}

interface Strategy{
	public void execute();
}

public class StrategyTemplate {

	public static void main(String[] args) {
		Strategy s1 = new Strategy1();
		Context context = new Context(s1);
		context.doIt();
	}
}
