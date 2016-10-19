/*
 * To demo the Liskov Substitution Principle (LSP)
 * - the subclass "Square" should behavior like a "Rectangle", 
 *   but it does not!  
 */
package principle.LSP;

public class LSPDemo {

	public static void main(String[] args) {

		Rectangle r = new Rectangle(1, 1);
		Square s = new Square(1);
		testLSP(r);
		testLSP(s);
	}

	public static void testLSP(Rectangle r) {
		r.setHeight(10);
		r.setWeight(2);

		if (r.getArea() != 20) {
			System.out.println(r.toString() + ", Wrong area!");
		} else {
			System.out.println(r.toString() + ", Great");
		}
	}

}

class Rectangle {
	int weight;
	int height;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle(int w, int h) {
		this.weight = w;
		this.height = h;
	}

	public void setHeight(int h) {
		this.height = h;
	}

	public int getArea() {
		return this.height * this.weight;
	}

	public String toString() {
		return "Rectangle, h = " + this.height + ", w " + this.weight;
	}
}

class Square extends Rectangle {

	public Square(int w, int h) {
		super(w, h);
	}

	public Square(int i) {
		super(i, i);
	}

	public void setHeight(int h) {
		super.setHeight(h);
		super.setWeight(h);
	}

	public void setWeight(int w) {
		super.setHeight(w);
		super.setWeight(w);
	}

	public String toString() {
		return "Square, h = " + super.getHeight() + ", w " + super.getWeight();
	}
}