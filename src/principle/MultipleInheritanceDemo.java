/**
 * To demo how to use delegation to implement "multiple inheritance"
 */

package principle;

public class MultipleInheritanceDemo {

	public static void main(String[] args) {
		WaterMoto waterMoto = new WaterMoto(new Boat());

		waterMoto.drive();
		waterMoto.driveOverWater();

	}

}

class Boat {
	public void driveOverWater() {
		System.out.println("drive over water");
	}
}

class Moto {
	public void drive() {
		System.out.println("drive");
	}
}

class WaterMoto extends Moto {
	Boat boat;

	public WaterMoto(Boat b) {
		this.boat = b;
	}

	/*
	 * delegate the driveOverWater to another "boat" object
	 */
	public void driveOverWater() {
		boat.driveOverWater();
	}
}
