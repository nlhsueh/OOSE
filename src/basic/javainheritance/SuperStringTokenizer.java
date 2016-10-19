package basic.javainheritance;
import java.util.*;

public class SuperStringTokenizer extends StringTokenizer {

	public SuperStringTokenizer(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		
//		StringTokenizer st = new StringTokenizer("this is a book");
//		
//		while (st.hasMoreElements()) {
//			String s = st.nextToken();
//			System.out.println(s);
//		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SuperStringTokenizer st = new SuperStringTokenizer("this is a book");
		while (st.hasMoreElements()) {
			String s = st.nextToken();
			System.out.println(s);
		}		
	}
	
	public String nextToken() {
		String a = super.nextToken();
		return new String(a.toUpperCase()); 
		//return null;
		
	}
	
	

}
