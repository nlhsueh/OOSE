
public class GradeBookApp1 {
	public static void main(String args[]) {
		Student Jie = new Student ("Jie");
		Student Albert = new Student ("Albert");

		Teacher Nick = new Teacher ("Nick");

		Course Java = new Course ("Java");
		Course Python = new Course ("Python");

		Nick.offer(Java);
		Nick.offer(Python);
		Nick.showCourse();

	}
	
}

class Course {
	String cName;
	public Course (String name) {
		this.cName = name;
	}
}

class Teacher {
	String tName;
	Course[] courses = new Course[10];
	int couseCount = 0;
	public Teacher(String name) {
		this.tName = name;
	}
	public void offer(Course c) {
		courses[couseCount++] = c;
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
	public Student (String name){
		this.sName = name;
	}
}