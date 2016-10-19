package basic.javainheritance;
 
/**
 * Demonstrates sorting arrays for classes that implement the Comparable
 * interface.
 */
public class ComparableDemo {
	public static void main(String[] args) {
		
		// ===== Sort Double 
		Double[] d = new Double[10];
		for (int i = 0; i < d.length; i++)
			d[i] = new Double(d.length - i);

		System.out.println("Before sorting:");
		int i;
		for (i = 0; i < d.length; i++)
			System.out.print(d[i].doubleValue() + ", ");
		System.out.println();

		GeneralizedSelectionSort.sort(d, d.length);

		System.out.println("After sorting:");
		for (i = 0; i < d.length; i++)
			System.out.print(d[i].doubleValue() + ", ");
		System.out.println();

		// ===== Sort String[]
		String[] a = new String[10];
		a[0] = "dog";
		a[1] = "cat";
		a[2] = "cornish game hen";
		int numberUsed = 3;

		System.out.println("Before sorting:");
		for (i = 0; i < numberUsed; i++)
			System.out.print(a[i] + ", ");
		System.out.println();

		GeneralizedSelectionSort.sort(a, numberUsed);

		System.out.println("After sorting:");
		for (i = 0; i < numberUsed; i++)
			System.out.print(a[i] + ", ");
		System.out.println();
		
		// ===== Sort People
		People[] p = new People[10];
		p[0] = new People("J", 12);
		p[1] = new People("M", 99);
		p[2] = new People("K", 43);
		numberUsed = 3;

		System.out.println("Before sorting:");
		for (i = 0; i < numberUsed; i++)
			System.out.print(p[i] + ", ");
		System.out.println();

		GeneralizedSelectionSort.sort(p, numberUsed);

		System.out.println("After sorting:");
		for (i = 0; i < numberUsed; i++)
			System.out.print(p[i] + ", ");
		System.out.println();

	}
}

class GeneralizedSelectionSort {
	/**
	 * Precondition: numberUsed <= a.length; The first numberUsed indexed
	 * variables have values. Action: Sorts a so that a[0, a[1], ... ,
	 * a[numberUsed - 1] are in increasing order by the compareTo method.
	 */
	public static void sort(Comparable[] a, int numberUsed) {
		int index, indexOfNextSmallest;
		for (index = 0; index < numberUsed - 1; index++) {// Place the correct
															// value in
															// a[index]:
			indexOfNextSmallest = indexOfSmallest(index, a, numberUsed);
			interchange(index, indexOfNextSmallest, a);
			// a[0], a[1],..., a[index] are correctly ordered and these are
			// the smallest of the original array elements. The remaining
			// positions contain the rest of the original array elements.
		}
	}

	/**
	 * Returns the index of the smallest value among a[startIndex],
	 * a[startIndex+1], ... a[numberUsed - 1]
	 */
	private static int indexOfSmallest(int startIndex, Comparable[] a,
			int numberUsed) {
		Comparable min = a[startIndex];
		int indexOfMin = startIndex;
		int index;
		for (index = startIndex + 1; index < numberUsed; index++)
			if (a[index].compareTo(min) < 0)// if a[index] is less than min
			{
				min = a[index];
				indexOfMin = index;
				// min is smallest of a[startIndex] through a[index]
			}
		return indexOfMin;
	}

	/**
	 * Precondition: i and j are legal indices for the array a. Postcondition:
	 * Values of a[i] and a[j] have been interchanged.
	 */
	private static void interchange(int i, int j, Comparable[] a) {
		Comparable temp;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp; // original value of a[i]
	}

}

class People implements Comparable {

	int age;
	String name;

	public People(String n, int age) {
		super();
		this.name = n;
		this.age = age;
	}

	public int compareTo(Object arg0) {
		if (age >= ((People) arg0).getAge()) {
			return 1;
		} else
			return -1;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
