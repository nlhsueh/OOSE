/*
CLASSES and attributes, operations
A gradebook application, the basic classes are:
* Students
* Course
* Teacher 

* if we don't want other class to modify the email directly
  we should set the email attribute to "private" 
*/

public class GradeBookApp0 {
	public static void main(String args[]) {
		Student Jie = new Student ("Jie");
		Student Albert = new Student ("Albert");
		Jie.setEmail("jie@mail.com");
		Albert.setEmail("albert@mail.com");
		Jie.email = "xx@gmail.com";
		System.out.println(Jie.email);
		Teacher Nick = new Teacher ("Nick");
		Course Java = new Course ("Java", 3);
		Course Python = new Course ("Python", 3);
	}
}

class Course {
	String cName;
	private int degree;
	public Course (String name, int degree) {
		this.cName = name;
		this.degree = degree;
	}
}

class Teacher {
	String tName;
	String email;
	public Teacher(String name) {
		this.tName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}
}

class Student {
	String sName;
	String email;
	public Student (String name){
		this.sName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}
}