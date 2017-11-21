/*
FIX the bug in GradeBookApp2
* In a double navigation, Teacher set reference to Course, 
  and Course set reference to Teacher
*/

public class GradeBookApp2a {
	public static void main(String args[]) {
		Student Jie = new Student ("Jie");
		Student Albert = new Student ("Albert");

		Teacher Nick = new Teacher ("Nick");

		Course Java = new Course ("Java", 3);
		Course Python = new Course ("Python", 3);

		Nick.offer(Java);
		Nick.offer(Python);

		Jie.takeCourse(Java);
		Albert.takeCourse(Java);

		Java.showCourseInfo();
	}	
}

class Course {
	String cName;
	private int degree;
	Student[] students = new Student[10];
	int studentCount = 0;
	Teacher teacher = new Teacher("None");
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
		System.out.println("-- Teacher: " + teacher.tName);
		String s = "";
		for (int i=0; i<studentCount ; i++) {
			s += students[i].sName + ", ";
		}
		System.out.println("-- Students: " + s);
	}

	public void setTeacher(Teacher t) {
		teacher = t;
	}
}

class Teacher {
	String tName;
	private String email;	
	Course[] courses = new Course[10];
	int courseCount = 0;
	public Teacher(String name) {
		this.tName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}	
	public void offer(Course c) {
		if (courseCount <= 9) {
			courses[courseCount++] = c;	
			c.setTeacher(this); // ++++++++++			
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
}

class Student {
	String sName;
	private String email;
	Course[] courses = new Course[10];
	int courseCount = 0;
	public Student (String name){
		this.sName = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	public void takeCourse(Course c) {
		if (courseCount <=9) {
			courses[courseCount++] = c;
			c.registeredBy(this); //++++++++++
		}	
		else 
			System.out.println("Hei, you take too many courses");
	}
}