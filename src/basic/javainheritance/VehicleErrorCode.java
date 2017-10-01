package basic.javainheritance;

public class VehicleErrorCode {

}

class Vehicle {
	int speed;

	public Vehicle(int speed) {
		this.speed = speed;
	}
}

class Bike extends Vehicle {
	int seatHeight;

	public Bike(int seatHeight) {
	    super();
	    this.seatHeight = seatHeight;
	  }

	void setHeight(int) {
	    this.seatHeight = seatHeight;
	  }
	}
