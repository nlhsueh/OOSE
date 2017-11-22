/*
many-to-one ASSOCIATION
"Offer" relationship between Teacher and Course
* the 1-* is implemented by a simple array. I know it is
  not a good way. Later version will be better.  

* Teacher can "navigate" the Course, but Course can't 
  naviate the Teacher (one way navigation)  
*/

public class GradeBookApp1 {
	public static void main(String args[]) {
		Student Jie = new Student ("Jie");
		Student Albert = new Student ("Albert");

		Teacher Nick = new Teacher ("Nick");

		Course Java = new Course ("Java", 3);
		Course Python = new Course ("Python", 3);

		Nick.offer(Java); //++++++++++
		Nick.offer(Python); //++++++++++
		Nick.showCourse(); //++++++++++
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
	private String email;	
	Course[] courses = new Course[10]; //++++++++++
	int couseCount = 0; //++++++++++
	public Teacher(String name) {
		this.tName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}	
	public void offer(Course c) { //++++++++++
		courses[couseCount++] = c;
	}
	
	public void showCourse() { //++++++++++
		for (Course c: courses) {
			if (c != null)
				System.out.println(c.cName);
			else 
				break;
		}
	}
}

class Student {
	String sName;
	private String email;
	public Student (String name){
		this.sName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}	
}