package uml;

public class BoxApp {

	public static void main(String[] args) {
		Shape[] box = { new Rectangle(4, 5), new Triangle(20, 4), new Circle(10) };
		double a = new BoxApp().totalArea(box);
		System.out.println(a);
	}

	public double totalArea(Shape[] box) {
		double area = 0;
		for (Shape s : box) {
			area += s.getArea();
		}
		return area;
	}
}

interface Shape {
	public double getArea();
}

class Rectangle implements Shape {

	double h, w;

	public Rectangle(int h, int w) {
		this.h = h;
		this.w = w;
	}

	@Override
	public double getArea() {
		return h * w;
	}
}

class Triangle implements Shape {
	double base, h;

	public Triangle(int base, int h) {
		this.base = base;
		this.h = h;
	}

	@Override
	public double getArea() {
		return (base * h) / 2;
	}
}

class Circle implements Shape {
	double r;

	public Circle(double r) {
		this.r = r;
	}

	@Override
	public double getArea() {
		return (r / 2) * (r / 2) * 3.14;
	}
}
