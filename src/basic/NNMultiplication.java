package basic;

public class NNMultiplication {
	public static void main(String[] args) {
		NNEntity[] xListA = { new NNInteger(2), new NNInteger(3), new NNInteger(5), new NNInteger(6), new NNInteger(10) };
		NNEntity[] yListA = { new NNInteger(7), new NNInteger(2), 	new NNInteger(3), new NNInteger(4), new NNInteger(8) };
		TableDisplayer.multiplyAndShow(xListA, yListA);
		
		NNEntity[] xListB = { new NNString("Q"), new NNString("D"), new NNString("T"), new NNString("H"), new NNString("Z") };
		NNEntity[] yListB = { new NNString("Y"), new NNString("D"), new NNString("Z"), new NNString("V"), new NNString("B") };
		TableDisplayer.multiplyAndShow(xListB, yListB);
		
		NNEntity[] xListC = { new NNColor("Red"), new NNColor("Red"), new NNColor("Red"), new NNColor("Green"), new NNColor("Blue") };
		NNEntity[] yListC = { new NNColor("Green"), new NNColor("Blue"), new NNColor("Red"), new NNColor("Blue"), new NNColor("Green") };
		TableDisplayer.multiplyAndShow(xListC, yListC);
	}
}

abstract class NNEntity {
	public abstract NNEntity multiply(NNEntity otherone);
}

class NNInteger extends NNEntity {
	private int number;

	public NNInteger(int number) {
		this.number = number;
	}

	public NNInteger(NNInteger copy) {
		this(copy.number);
	}

	// 數字相乘
	public NNEntity multiply(NNEntity otherone) {
		if (otherone == null) {
			return null;
		} else if (getClass() != otherone.getClass()) {
			return null;
		} else {
			NNInteger otherone2 = (NNInteger) otherone;
			return new NNInteger(this.number * otherone2.number);
		}
	}

	public String toString() {
		return Integer.toString(number);
	}
}

class NNString extends NNEntity {
	private String words;

	public NNString(String words) {
		this.words = words;
	}

	public NNString(NNString copy) {
		this(copy.words);
	}

	// 字串相連
	public NNEntity multiply(NNEntity otherone) {
		if (otherone == null) {
			return null;
		} else if (getClass() != otherone.getClass()) {
			return null;
		} else {
			NNString otherone2 = (NNString) otherone;
			return new NNString(this.words + otherone2.words);
		}
	}

	public String toString() {
		return words;
	}
}

class NNColor extends NNEntity {
	private String color;

	public NNColor(String color) {
		this.color = color;
	}

	public NNColor(NNColor copy) {
		this(copy.color);
	}

	// 顏色相混
	public NNEntity multiply(NNEntity otherone) {
		if (otherone == null) {
			return null;
		} else if (getClass() != otherone.getClass()) {
			return null;
		} else {
			NNColor otherone2 = (NNColor) otherone;
			if (this.color.equals("Red")) {
				if (otherone2.toString().equals("Red")) {
					return new NNColor("Red");
				} else if (otherone2.toString().equals("Green")) {
					return new NNColor("Yellow");
				} else
					return new NNColor("Purple");
			} else if (this.color.equals("Green")) {
				if (otherone2.toString().equals("Red")) {
					return new NNColor("Yellow");
				} else if (otherone2.toString().equals("Green")) {
					return new NNColor("Green");
				} else
					return new NNColor("Cyan");
			} else {
				if (otherone2.toString().equals("Red")) {
					return new NNColor("Purple");
				} else if (otherone2.toString().equals("Green")) {
					return new NNColor("Cyan");
				} else
					return new NNColor("Blue");
			}
		}
	}
	public String toString() {
		return color;
	}
}

class TableDisplayer {
	public static void multiplyAndShow(NNEntity[] xList, NNEntity[] yList) {
		/* Multiply */
		NNEntity[][] table = new NNEntity[yList.length][xList.length];
		for (int i = 0; i < yList.length; i++) {
			for (int j = 0; j < xList.length; j++) {
				table[i][j] = xList[j].multiply(yList[i]);
			}
		}
		/* Show */
		System.out.printf("%7s", "");
		for (int i = 0; i < xList.length; i++) {
			System.out.printf("%7s", xList[i]);
		}
		System.out.println();
		for (int i = 0; i < yList.length; i++) {
			System.out.printf("%7s", yList[i]);
			for (int j = 0; j < xList.length; j++) {
				System.out.printf("%7s", table[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
