import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

/* 
 Group 用 Vector 來儲存一群成績
*/
class Group {
	Vector<Integer> grade;

	public Group() {
		grade = new Vector<Integer>();
		grade.add(80);
		grade.add(80);
		grade.add(70);
		grade.add(90);
	}

	Enumeration<Integer> getGrade() {
		return grade.elements();
	}
}

/*
 * School 用 Iterator 來計算平均
*/
class School {
	public static double getAverage(Iterator<Integer> it) {
		double avg = 0, sum = 0, count = 0;

		// YOU CODE HERE

		return avg;
	}
}

/* 
* Adaptor. YOU CODE HERE
*/
class GradeIterator<E> implements Iterator<E> {
	Enumeration<E> gradeList;

	public GradeIterator(Enumeration<E> e) {
		// YOU CODE HERE
	}

	public boolean hasNext() {
		// YOU CODE HERE
	}

	public E next() {
		// YOU CODE HERE
	}

	public void remove() {
		// enumeration can't remove element
	}
}

public class AdaptorApp01 {

	public static void main(String[] args) {
		Group g = new Group();
		Enumeration<Integer> e = g.getGrade();

		// 建立一個 adaptor 
		// YOU CODE HERE
		
		// 傳給 School 來計算
		// YOU CODE HERE
		System.out.println(average);
	}

}