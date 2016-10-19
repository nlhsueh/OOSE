package basic.javaclass;
import java.util.*;

public class PeopleDemo {

	public static void main(String[] args) {
		
		People p1 = new People("john Lin");
		System.out.println("People created:" + People.count);
		People p2 = new People("mary Lee");
		People p3 = new People("tin Chen");
		
		System.out.println("People created:" + People.count);
		
		System.out.println("Pepple: john Lin");
		String firstName = People.getFirstName("john Lin");
		System.out.println("first name: " + firstName);
		
		String lastName = People.getLastName("john Lin");
		System.out.println("first name: " + lastName);
	}

}

class People {
	private String pName;
	private String pTel;
	private String status;
	public static int count=0;
	
	public static String getFirstName(String name) {
		String firstName="";
		StringTokenizer st = new StringTokenizer(name);
		firstName = st.nextToken();
		return firstName;
	}
	
	public static String getLastName(String name) {
		String lastName="";
		StringTokenizer st = new StringTokenizer(name);
		while (st.hasMoreTokens()) {
			lastName = st.nextToken();
		}	
		return lastName;	}
	
	
	public People(String name) {
		count++;
		pName = name;
		sleep();
	}
	
	public String getpTel() {
		return pTel;
	}
	public void setpTel(String pTel) {
		this.pTel = pTel;
	}
	public String getpName() {
		return pName;
	}	
	
	public void print() {
		System.out.println(pName + ", tel is " + pTel + ". He is "+ status);
	}
	
	public void work() {
		status = "working...";		
	}
	
	public void sleep() {
		status = "sleep...";
	}
	
	public void call () {
		if (pTel != null) {
			status = "calling...";
		}
	}
	
}
