package iterator;

import java.util.*;

public class IterationDemo {

	public static void main(String[] args) {
		// Vector並且增加內容
		Vector<String> v = new Vector<String>();
		v.addElement(new String("Hello"));
		v.addElement(new String("Taichung"));
		v.addElement(new String("Have a nice day"));
		// 瀏覽 Vector內的內容
		Iterator<String> it1 = v.iterator();
		System.out.print("Vector 內的內容為:");
		traverse(it1);

		// ArrayList
		ArrayList<String> v2 = new ArrayList<String>();
		v2.add(new String("Hello"));
		v2.add(new String("Taipei"));
		v2.add(new String("Good morning"));
		// 瀏覽 ArrayList 內的內容
		Iterator<String> it2 = v2.iterator();
		System.out.print("\nArrayList 內的內容為:");
		traverse(it2);

		// HashMap
		HashMap<String, Integer> v3 = new HashMap<String, Integer>();
		v3.put("John", new Integer(172));
		v3.put("Mary", new Integer(168));
		v3.put("Nick", new Integer(180));
		// 瀏覽 HashMap 的 Key
		Iterator<String> it3 = v3.keySet().iterator();
		System.out.print("\nHashMap 內的內容為:");
		traverse(it3);
	}

	static void traverse(Iterator<String> e) {
		while (e.hasNext()) {
			System.out.print(e.next() + ", ");
		}
	}

}
