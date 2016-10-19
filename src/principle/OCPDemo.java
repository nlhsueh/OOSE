/*
 * Demo the principle of "Open for extension, Close for modification"
 * - The price policy is encapsulated as a class, 
 *   which can be "extended" as you want.
 */
package principle;

public class OCPDemo {

	public static void main(String[] args) {

		CPU cpu = new CPU();
		Memory memory = new Memory();
		Part[] all = { cpu, memory };

		// change to Sale policy
		System.out.println("=== SalePrice Policy ===");
		PricePolicy p1 = new SalePricePolicy(100, 0.8);
		cpu.setPolicy(p1);
		PricePolicy p2 = new SalePricePolicy(200, 0.8);
		memory.setPolicy(p2);
		calculate(all);

		// change to Chrismas policy
		System.out.println("=== Chrismas Price Policy ===");
		PricePolicy p3 = new ChrismasPricePolicy(100);
		cpu.setPolicy(p3);
		PricePolicy p4 = new ChrismasPricePolicy(200);
		memory.setPolicy(p4);
		calculate(all);
	}

	public static void calculate(Part[] all) {
		double allPrice = 0;
		for (int i = 0; i < all.length; i++) {
			allPrice += all[i].getPrice();
		}

		System.out.println(allPrice);

	}
}

class Part {
	PricePolicy policy;

	public void setPolicy(PricePolicy p) {
		this.policy = p;
	}

	double getPrice() {
		if (policy != null)
			return policy.getPrice();
		else {
			System.out.println("No price policy");
			System.exit(0);
		}
		return 0;
	}

	void setPrice(double p) {
		if (policy != null) {
			policy.setPrice(p);
		} else {
			System.out.println("No price policy");
			System.exit(0);
		}

	}
}

class PricePolicy {
	double basePrice;

	public PricePolicy(double p) {
		basePrice = p;
	}

	void setPrice(double p) {
		this.basePrice = p;
	}

	double getPrice() {
		return basePrice;
	}
}

class SalePricePolicy extends PricePolicy {
	double discount;

	public SalePricePolicy(double base, double discount) {
		super(base);
		this.discount = discount;
	}

	double getPrice() {
		return super.getPrice() * this.discount;
	}

}

/*
 * Chrismas is a special policy
 */
class ChrismasPricePolicy extends PricePolicy {
	public ChrismasPricePolicy(double price) {
		// TODO Auto-generated constructor stub
		super(price);
	}

	double getPrice() {
		return super.getPrice() * 0.5;
	}
}

class Memory extends Part {
}

class CPU extends Part {

}
