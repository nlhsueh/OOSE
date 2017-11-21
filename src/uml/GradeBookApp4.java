/*
* The generalization relationship between Member and (Teacher, Student)
* To compute the average of a course
>This version is enhanced from version 3

* add class Member
* modify Teacher and Student, move some attributes/method to Member
* add Course.getAverage();

* modify Course.showCourseInfo();
*/

public class GradeBookApp4 {
	public static void main(String args[]) {
		Student Jie = new Student ("Jie");
		Student Albert = new Student ("Albert");
		Student Alex = new Student ("Alex"); //++++++++++

		Teacher Nick = new Teacher ("Nick");

		Albert.setEmail("albert@gmail.com"); //++++++++++
		Jie.setEmail("jie@gmail.com"); 
		Nick.setEmail("nick@gmail.com"); 

		Member[] members = {Albert, Jie, Nick}; //++++++++++
		for (Member m: members) 
			m.showInfo();

		Course Java = new Course ("Java", 3);
		Course Python = new Course ("Python", 3);

		Nick.offer(Java);
		Nick.offer(Python);

		Jie.takeCourse(Java);
		Albert.takeCourse(Java);
		Alex.takeCourse(Java); //++++++++++

		Nick.score(Java, Jie, 100); 
		Nick.score(Java, Albert, 98); 
		Nick.score(Java, Alex, 20); //++++++++++
		
		// Jie.showGrade(); 
		// Albert.showGrade(); 
		// Alex.showGrade(); //++++++++++
		Java.showCourseInfo(); 
	}	
}

class Course {
	String cName;
	private int degree;
	Student[] students = new Student[10];
	int studentCount = 0;
	Teacher teacher = new Teacher("None");
	int [] grades = new int[10];
	int scoreCount = 0; //++++++++++
	double sum=0; //++++++++++
	double average=0; //++++++++++

	public Course (String name, int degree) {
		this.cName = name;
		this.degree = degree;
	}

	public void registeredBy(Student s) {
		if (studentCount <= 9)
			students[studentCount++] = s;
		else
			System.out.println("Students overflow in a class");
	}

	public void showCourseInfo() { 
		System.out.println("Course: "+ cName);
		System.out.println("-- Teacher: " + teacher.name); //*****
		String s = "";
		String g = "";
		for (int i=0; i<studentCount ; i++) {
			s += students[i].name + ", "; //*****
			g += Integer.toString(grades[i]) + ", ";
		}
		System.out.println("-- Students: " + s);
		System.out.println("-- Grades: " + g);
		System.out.println("-- Average: "+ average); //++++++++++
	}

	public void setTeacher(Teacher t) {
		teacher = t;
	}

	public boolean takenBy(Student s) { 
		for (int i=0; i<studentCount ; i++) {
			if (students[i] == s) {
				return true;
			}
		}
		return false;		
	}

	public void score(Student s, int g) { 
		int idx = getIndex(s);
		if (idx == -1)
			System.out.println(s.name + " is not in " + cName);
		else {
			grades[idx] = g;
			scoreCount++; 	//++++++++++
			sum += g;
			average = sum/scoreCount;
		}
	}

	private int getIndex(Student s) { 
		for (int i=0; i<studentCount; i++) {
			if (students[i] == s) {
				return i;
			}
		}
		return -1;
	}

	public int getGrade(Student s) { 
		int idx = getIndex(s);
		if (idx != -1) {
			return grades[idx];
		}
		return idx;
	}
}

class Teacher extends Member { //*****
	// String tName; //----------
	// private String email;	
	Course[] courses = new Course[10];
	int courseCount = 0;

	public Teacher(String name) { 
		super(name); //*****
	}
	// public void setEmail(String e) {
	// 	this.email = e;
	// }	
	public void offer(Course c) {
		if (courseCount <= 9) {
			courses[courseCount++] = c;	
			c.setTeacher(this); 
		}
		else
			System.out.println("Offer too many courses");
	}
	
	public void showCourse() {
		for (Course c: courses) {
			if (c != null)
				System.out.println(c.cName);
			else 
				break;
		}
	}

	public void score(Course c, Student s, int g) { 
		if (offeredByMe(c) && c.takenBy(s)) {
			c.score(s, g);
		}
	}

	private boolean offeredByMe(Course c) {
		for (int i=0; i<courseCount; i++) {
			if (courses[i] == c) {
				return true;
			}
		}
		return false;
	}
}

class Student extends Member { //*****
	Course[] courses = new Course[10];
	// String sName; //----------
	// private String email;
	int courseCount = 0;
	public Student (String name){ 
		super(name); //*****
	}
	public void takeCourse(Course c) {
		if (courseCount <=9) {
			courses[courseCount++] = c;
			c.registeredBy(this);
		}	
		else 
			System.out.println("Hei, you take too many courses");
	}
	public void showGrade() { 
		System.out.println("The grades of student " + name); //*****
		for (int i=0; i<courseCount; i++) {
			Course c = courses[i];
			String gString = "no grade";
			int g = c.getGrade(this);
			if (g != -1) gString = Integer.toString(g);
			System.out.println("-- " + c.cName + ": " + gString);
		}
	}
}

class Member { //++++++++++
	String name;
	private String email;
	public Member(String name) {
		this.name = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	public void showInfo() { //++++++++++
		System.out.println(name + ", email: " + email);
	}	
}