package proxy;

public class ProxyDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {	
		Country c = new Country("Taiwan");
		Ambassador a = new Ambassador(c);
		a.requestHelp(false);
		
		a.requestHelp(true);
	}

}


interface handleHelp {
	public void requestHelp(boolean isComplexity);
}

class Country implements handleHelp {
	String name;
	public Country(String name) {
		this.name = name;
	}
	public void requestHelp(boolean isComplexity) {
		System.out.println("I am "+ name + ", I will help you");		
	}
}

class Ambassador implements handleHelp {
	Country c;
	
	public Ambassador(Country c) {
		this.c = c;
	}
	
	public void requestHelp(boolean isComplexity) {
		System.out.println("I ambassador Hsueh, I am help you...");
		if (isComplexity) {
			c.requestHelp(true);
		}
		else {
			System.out.println("Done!");
		}
	}
}
