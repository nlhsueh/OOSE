/**
 * This program is to demo the design principle "Don¡¦t repeat yourself (DRY)"
 * 
 */

package principle;


class DRYDemo {
	public static void main(String[] arg) {
		People p1 = new People("john", 1.7, 75);
		System.out.println(p1.toString());
	}
}

class People {

	String name;
	double height;
	int weight;
	double BMI; // when you change the height or weight.

	public People(String name, double h, int w) {
		this.name = name;
		this.height = h;
		this.weight = w;
		setBMI();
	}

	public void setHeight(double h) {
		this.height = h;
		setBMI();
	}

	public void setWeight(int w) {
		this.weight = w;
		setBMI();
	}

	/*
	 * move the setting of BMI as a method, thus reduce the repeat of the
	 */
	private void setBMI() {
		BMI = (double) weight / (height * height);
	}

}
