package singleton;

public class SingletonDemo {
	public static void main(String[] args) {

		Radio r = Radio.instance();
		System.out.println(r);
		r = null;
		r = Radio.instance();
		System.out.println(r);
	}

}

class Radio {
	private static Radio uniqueInstance;

	String station;

	public static Radio instance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Radio();
		}
		return uniqueInstance;
	}

	private Radio() {
		station = "default station";
	}

}
