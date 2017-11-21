package uml;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class UniversityApp {

	public static void main(String[] args) {
		University fcu = new University("FCU");
		Teacher nick = new Teacher("Nick");
		Student albert = new Student("Albert");
		Student jie = new Student("Jie");
		Student alex = new Student("Alex");
		Course java = new Course("Java");
		Course python = new Course("Python");
		fcu.hire(nick);
		nick.offer(java);
		nick.offer(python);

		albert.enter(fcu);
		alex.enter(fcu);
		jie.enter(fcu);
		fcu.showMembers(); // list all members, including teachers and students
		fcu.showCourses(); // list all courses offered by all teachers
		albert.takeCourse(java);
		jie.takeCourse(java);
		alex.takeCourse(java);

		nick.score(java, jie, 50);
		nick.score(java, albert, 100);
		nick.score(java, alex, 40);
		java.listGrade(); // show all students' name and grade
		// Jie: 50, Albert: 100
		nick.showAverage(java); // show average of the course java
		// java average: 75
		fcu.showNoPass(); // show student’s name, course, teacher’s name
		// Jie: 50, Course: Java, Teacher: Nick
	}
}

class University {
	String name;
	ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	ArrayList<Student> students = new ArrayList<Student>();
	ArrayList<Course> courses = new ArrayList<Course>();
	Map<Pair<Course, Student>, Integer> gbook = new HashMap<Pair<Course, Student>, Integer>();

	public University(String s) {
		name = s;
	}

	public void setScore(Course c, Student s, int g) {
		gbook.put(new Pair<Course, Student>(c, s), g);
	}

	public void showNoPass() {
		for (Entry<Pair<Course, Student>, Integer> e : gbook.entrySet()) {
			if (e.getValue() < 60) {
				System.out.print("\nNo Pass Course: " + e.getKey().c);
				System.out.print("\t Student: " + e.getKey().s.name + "\tScore: " + e.getValue());
			}
		}
	}

	public void showCourses() {
		System.out.println("\nThe courses offered by " + name + " are " + courses.toString());

	}

	public void showMembers() {
		System.out.print("MEMBERS of " + name + ":");
		System.out.print("\nThe teachers: ");
		for (Teacher t : teachers) {
			System.out.print(t.name + "\t");
		}
		System.out.print("\nThe students: ");
		for (Student s : students) {
			System.out.print(s.name + "\t");
		}
	}

	public void hire(Teacher t) {
		teachers.add(t);
		t.setUniversity(this);
	}

	public void registered(Student s) {
		students.add(s);
		s.setUniversity(this);
	}

	public void showAverage(Course c) {
		double sum = 0;
		int count = 0;
		for (Entry<Pair<Course, Student>, Integer> e : gbook.entrySet()) {
			if (e.getKey().c.equals(c)) {
				sum += e.getValue();
				count++;
			}
		}
		System.out.printf("\nAverage of the course %s is %.2f", c.toString(), sum / count);
	}

	public void addCourse(Course c) {
		courses.add(c);
	}

	public Iterator<Entry<Pair<Course, Student>, Integer>> getGBookIterator() {
		Iterator<Entry<Pair<Course, Student>, Integer>> it = gbook.entrySet().iterator();
		return it;
	}
}

class Member {
	String name;
	University school;

	public Member(String n) {
		this.name = n;
	}

	public void setUniversity(University u) {
		this.school = u;
	}
}

class Teacher extends Member {
	ArrayList<Course> courses = new ArrayList<Course>();

	public Teacher(String n) {
		super(n);
	}

	public void showAverage(Course c) {
		school.showAverage(c);
	}

	public void score(Course c, Student s, int g) {
		if (courses.contains(c))
			school.setScore(c, s, g);
		else
			System.out.println("The teacher can't score this course");
	}

	public void offer(Course c) {
		courses.add(c);
		school.addCourse(c);
		c.setTeacher(this);
	}

}

class Course {
	String name;
	Teacher t;
	ArrayList<Student> students = new ArrayList<Student>();

	public Course(String c) {
		name = c;
	}

	public void setTeacher(Teacher t) {
		this.t = t;
	}

	public void listGrade() {
		Iterator<Entry<Pair<Course, Student>, Integer>> gb = t.school.getGBookIterator();
		System.out.print("\nThe grade of the course are: ");
		while (gb.hasNext()) {
			Entry<Pair<Course, Student>, Integer> s = gb.next();
			if (s.getKey().getCourse().equals(this))
				System.out.print(s.getValue() + "\t");
		}
	}

	public String toString() {
		return name;
	}

	public void addStudent(Student s) {
		students.add(s);
	}

}

class Pair<Course, Student> {
	Course c;
	Student s;

	public Pair(Course c, Student s) {
		this.c = c;
		this.s = s;
	}

	public Course getCourse() {
		return c;
	}

	public Student getStudent() {
		return s;
	}

	public boolean equals(Object otherPair) {
		if (!(otherPair instanceof Pair))
			return false;
		Pair<Course, Student> p = (Pair<Course, Student>)otherPair;
		return this.c.equals(p.c) && this.s.equals(p.s);
	}
}

class Student extends Member {

	public Student(String n) {
		super(n);
	}

	public void takeCourse(Course c) {
		c.addStudent(this);
	}

	public void enter(University fcu) {
		fcu.registered(this);

	}
}
