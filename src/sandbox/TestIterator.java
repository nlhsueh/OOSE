package sandbox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class TestIterator {

	public static void main(String[] args) {

		Question q1 = new Question("1+1=");
		Question q2 = new Question("1+2=");
		Question q3 = new Question("1+3=");

		Paper p1 = new Paper();
		p1.add(q1);
		p1.add(q2);
		p1.add(q3);
		p1.showQuestion();

		// Question q4 = new Question("2+1=");
		//
		// Paper p2 = new Paper();
		// p2.add(p1);
		// p2.add(q4);
		//
		// p2.showQuestion();

	}

}

interface IPaper {
	void showQuestion();
}

class Question implements IPaper {
	String description;

	public Question(String s) {
		description = s;
	}

	public void showQuestion() {
		// TODO Auto-generated method stub
		System.out.println(description);
	}

}

class Paper implements IPaper {
	ArrayList<IPaper> papers;

	public Paper() {
		papers = new ArrayList<IPaper>();
	}

	public void showQuestion() {
		// ListIterator it = papers.listIterator();
		// Iterator it = papers.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.nextIndex());
		// IPaper p = (IPaper) it.next();
		// p.showQuestion();
		// }
		for (IPaper p : papers) {
			p.showQuestion();
		}

	}

	public void add(IPaper p) {
		papers.add(p);
	}
}
