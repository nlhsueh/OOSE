package etc;

import java.util.Scanner;

public class ExceptionTest {

	public static void main(String[] args) throws Exception {
		ExceptionTest t = new ExceptionTest();
		t.m1();

	}

	public void m1() throws Exception{
		int x = 0;
		Scanner sc = new Scanner(System.in);
		try {
			if (x >= 0)
				throw new Exception();
			x++;
		} catch (Exception e) {
			throw new Exception();
			//return;
			// System.exit(0);
		} finally {
			System.out.println("finally");
		}
		System.out.println("after");

	}

}
