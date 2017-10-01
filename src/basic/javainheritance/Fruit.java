package basic.javainheritance;

interface Comparable {
	public boolean betterThan(Comparable x);
}

class Util {
	public static Object best(Comparable x, Comparable y, Comparable z) {
		if (x.betterThan(y)) {
			if (x.betterThan(z))
				return x;
			else
				return z;
		} else if (y.betterThan(z)) {
			return y;
		} else
			return z;
	}
}

class Fruit implements Comparable {
	String name;
	int price;
	int sweetDegree;
	int waterDegree;

	public static void main(String args[]) {
		Fruit f1 = new Fruit(12), f2 = new Fruit(23), f3 = new Fruit(9);
		Fruit best = (Fruit) Util.best(f1, f2, f3);
		// best 為何？
	}

	public boolean betterThan(Comparable x) {
		if (x instanceof Fruit)
			if (this.sweetDegree > ((Fruit) x).sweetDegree)
				return true;
			else
				return false;
		else {
			System.out.println("錯誤的比較");
			return false;
		}
	}

	public Fruit(int sweetDegree) {
		this.sweetDegree = sweetDegree;
	}
}
