package cor;

import java.util.Random;

public class CoRDemo {

	public static void main(String[] args) {
		Handler h2 = new Handler2(null);
		Handler h1 = new Handler1(h2);

		// 第一個處理者都是 Handler1
		h1.handleRequest(100);
		h1.handleRequest(0);
		h1.handleRequest(10);
	}
}

abstract class Handler {
	Handler successor;

	public Handler(Handler h) {
		successor = h;
	}

	public void handleRequest(int i) {
		if (successor != null)
			successor.handleRequest(i);
		else
			System.out.println("No one can handle it");
	}
}

class Handler1 extends Handler {
	Random rn = new Random();
	public Handler1(Handler h) {
		super(h);
	}

	public void handleRequest(int x) {
		int i = rn.nextInt(2);

		// Handler 依據請求的參數值及自身的狀態來決定是否能夠處理
		if (x > 10 || (i==1))
			System.out.println("Handler 1 will handle");
		else
			// 不能處理時就丟給下一個
			super.handleRequest(x);
	}
}

class Handler2 extends Handler {
	public Handler2(Handler h) {
		super(h);
	}

	public void handleRequest(int x) {
		if (x < 10)
			System.out.println("Handler 2 will handle");
		else
			super.handleRequest(x);
	}
}
