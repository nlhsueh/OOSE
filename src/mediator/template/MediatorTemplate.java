package mediator.template;

public class MediatorTemplate {

	public static void main(String[] args) {
		IMediator med = new Mediator();

		// 生成 colleague 時，設定其 Mediator
		Colleague1 c1 = new Colleague1(med);
		Colleague2 c2 = new Colleague2(med);

		// 送訊息給 c1, c2
		c1.m1();
		c2.m2();
	}
}

interface IMediator {
	void m1();

	void m2();

	void registerColleague1(Colleague1 c);

	void registerColleague2(Colleague2 c);
}

class Mediator implements IMediator {
	Colleague1 c1;
	Colleague2 c2;

	// 雖然 m1 是給 Colleague1 的訊息，但會轉給 mediator
	// mediator 來決定會與哪些其他 Colleague 互動
	public void m1() {
		System.out.println("Mediator m1");
		c2.op2();
	}

	public void m2() {
		System.out.println("Mediator m2");
		c1.op1();
	}

	// Mediator 必須識得每一個 Colleague
	public void registerColleague1(Colleague1 c) {
		this.c1 = c;
	}

	public void registerColleague2(Colleague2 c) {
		this.c2 = c;
	}

}

abstract class Colleague {
	protected IMediator med;

	public Colleague(IMediator med) {
		this.med = med;
	}
}

class Colleague1 extends Colleague {
	public Colleague1(IMediator med) {
		super(med);
		med.registerColleague1(this);
	}

	void m1() {
		System.out.println("Colleague1.m1");
		med.m1();
	}

	void op1() {
		System.out.println("Colleague1.op1");
	}
}

class Colleague2 extends Colleague {
	public Colleague2(IMediator med) {
		super(med);
		med.registerColleague2(this);
	}

	void m2() {
		System.out.println("Colleague2.m2");
		med.m2();
	}

	void op2() {
		System.out.println("Colleague2.op2");
	}
}
