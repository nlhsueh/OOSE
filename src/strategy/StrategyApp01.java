class Data {
	int[] data = {100,92,43,87,23};
	Sorter s;
	
	public Data(Sorter s) {
		this.s = s;
	}
	public void setSorter(Sorter s) {
		this.s = s;
	}
	public void sort() {
		System.out.println("Sorting...");
		s.sort(data);
	}
	public void show() {
		for (int d: data)
			System.out.println(d);
	}
}

class SelectionSorter implements Sorter {
	public void sort(int[] d) {
		System.out.println("selection sort done");
	}
}

class BubbleSorter implements Sorter {
	public void sort(int[] d) {
		System.out.println("BubbleSorter sort done");
	}
}

interface Sorter{
	public void sort(int[] d);
}



public class StrategyApp01 {

	public static void main(String[] args) {

		Sorter s1 = new SelectionSorter();
		Data data = new Data(s1);
		data.sort();
		data.show();

		Sorter s2 = new BubbleSorter();
		data.setSorter(s2);
		data.sort();
		data.show();
	}
}
