public class LampDemo {
	public static void main(String args[]) {
		ButtonClient lamp = new Lamp();
		Button b = new Button(lamp); //開關控制檯燈
		b.turnOn();
		b.turnOff();
		b.turnOn();		

		ButtonClient ac = new AC();
		b = new Button(ac); //可以換成控制冷氣機
		((AC)ac).setDegree(30);
		b.turnOn();
		b.turnOff();
		((AC)ac).setDegree(21);
		b.turnOn();
	}
}

// 開關按鈕的對象（小家電）
abstract class ButtonClient {
   	public abstract void turnOn();
    public abstract void turnOff();
}

// 開關按鈕，Button，相依於一個抽象通用的 ButtonClient
class Button {
  	private ButtonClient  bClient;
    public Button(ButtonClient b) { //連結真實的 ButtonClient
   	    bClient = b;
	}
	public void turnOn() {
		bClient.turnOn();
	}
	public void turnOff() {
		bClient.turnOff();
	}
}

// Lamp 自己定義開關
class Lamp extends ButtonClient {
	String state="off";
   	public  void turnOn() {
   		state = "on";
   		printState();
    }
    public  void turnOff() {
    	state = "off";
   		printState();    	
   	}
   	private void printState() {
   		System.out.println("Lamp is " + state);   		
   	}
}

// 冷氣機 自己定義開關
class AC extends ButtonClient {
	int currentDegree = 28;
	String state="off";

	// 高於 28 度才可以開啟
   	public  void turnOn() {
   		if (currentDegree > 28) {
   			state = "on";
   		}
   		printState();
    }
    public  void turnOff() {
    	state = "off";
   		printState();    	
   	}
   	private void printState() {
   		System.out.println("AC is " + state);   		
   	}
   	public void setDegree(int d) {
   		this.currentDegree = d;
   	}
}