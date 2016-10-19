package observer.delegation;

import java.util.Observer;
import java.util.Observable;

public class ObserverDelegationDemo {

	public static void main(String[] args) {
		// Note I did not "addObserver()" in the first increase()
		Stock s = new Stock(100);
		s.increase();
		
		s.addObserver(new StockBoard());
		s.increase();
		
		s.addObserver(new StockPicView());
		s.increase();

	} 

}

class Something {}

/*
 * Stock inherits Something, so it can't inherit Observable directly. 
 */
class Stock extends Something {
	int price;
	DelegatedObservable observable;
	
	public Stock(int price) {
		this.price = price;
		observable = new DelegatedObservable();
	}
	
	public void increase() {
		price++;
		observable.setChanged();
		observable.notifyObservers(new Integer(price));
	}
	
	public void addObserver(Observer obs) {		
		observable.addObserver(obs);
	}
}

/*
 * Since the methods "clearChanged()" and "setChanged()" is declared as protected, 
 * not public, therefore the Stock class can't call directly. 
 * This class just "open" the interface from "protected" to "public" 
 */
class DelegatedObservable extends Observable {
	public void clearChanged() {
		super.clearChanged();
	}
	
	public void setChanged() {
		super.setChanged();
	}
}

class StockBoard implements Observer {
	public void update(Observable observable, Object value) {
		System.out.println("The value is changed to "+ ((Integer)value).intValue());
	}
}

class StockPicView implements Observer {
	public void update(Observable observable, Object value) {
		System.out.println("Hi, I am StrockPicView, the value is changed to "+ ((Integer)value).intValue());
	}
	
}