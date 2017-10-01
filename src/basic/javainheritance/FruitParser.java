package basic.javainheritance;

import java.util.StringTokenizer;

public class FruitParser extends StringTokenizer {
	String[] fruit_set = { "apple", "avocado", "banana", "cherry", "coconut", "jujube", "durian", "grape", "grapefruit",
			"guava", "lemon", "lichee", "orange", "kiwi" };
	
	public String[] getFruits() {
		String [] r = {};
		return r;
	}

	public static void main(String[] args) {
		String s = "I like apple, banana, and orange. Marry like kiwi";
		FruitParser f = new FruitParser(s);
		String[] fruits = f.getFruits();		
	}
}
