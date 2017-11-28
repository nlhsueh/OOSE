/*
Strategy design pattern
* Use strategy design pattern to sort the grade in Course
	* we can apply different sorting strategy in the future
*/

public class GradeBookApp7 { 
	private Student Jie, Albert, Alex; 
	private Teacher Nick;
	private IndustryExpert Peter;
	private Course Java, Python;
	private Member[] members; //include all students and teachers
	private Instructor[] instructors;
	private Course[] courses;

	public static void main(String args[]) { 
		GradeBookApp7 gb = new GradeBookApp7(); //********
		gb.init(); 
		gb.takeCourse();
		gb.score();
		gb.show();
	}	

	private void init() { 
		// create students
		Jie = new Student ("Jie");
		Albert = new Student ("Albert");
		Alex = new Student ("Alex"); 

		// create teachers
		Nick = new Teacher ("Nick");

		Albert.setEmail("albert@gmail.com"); 
		Jie.setEmail("jie@gmail.com"); 
		Nick.setEmail("nick@gmail.com"); 

		members = new Member[]{Jie, Albert, Alex, Nick};

		// create course
		Java = new Course ("Java", 3);
		Python = new Course ("Python", 3);
	
		courses = new Course[] {Java, Python};

		// setQualification
		Peter = new IndustryExpert("Peter"); 
		Nick.setQualification(new Qualification("IECS Ph.D")); 
		Peter.setQualification(new Certification(2000, "Cisco")); 

		instructors = new Instructor[] {(Instructor)Nick, (Instructor)Peter}; 
	}

	private void takeCourse() {	
		Nick.offer(Java);
		Nick.offer(Python);

		Jie.takeCourse(Java);
		Albert.takeCourse(Java);
		Alex.takeCourse(Java); 
	}

	private void score() { 
		Nick.score(Java, Jie, 100); 
		Nick.score(Java, Albert, 98); 
		Nick.score(Java, Alex, 20); 		
		Java.showSortedGrades(); //++++++++++
	}

	private void show() { 
		System.out.println("\n=== INSTRUCTORS QUALIFICATION ===");
		for (Instructor i: instructors)
			i.showQualification();
		System.out.println("\n=== MEMBERS ===");		
		for (Member m: members) 
			m.showInfo();
		System.out.println("\n=== COURSES ===");		
		for (Course c: courses) 
			c.showCourseInfo();
	}
}

interface Config { //++++++++++
	public static int MAX_STUDENT = 10; // max student number in a school
	public static int MAX_COURSE = 10; //max course number in a school
}

class Course {
	String cName;
	private int degree;
	Student[] students = new Student[Config.MAX_STUDENT]; 
	int studentCount = 0;
	Teacher teacher = new Teacher("None");
	int [] grades = new int[Config.MAX_STUDENT]; 
	int scoreCount = 0; 
	double sum=0; 
	double average=0; 
	SortStrategy sorter;

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
		System.out.println("-- Teacher: " + teacher.name); 
		String s = "";
		String g = "";
		for (int i=0; i<studentCount ; i++) {
			s += students[i].name + ", "; 
			g += Integer.toString(grades[i]) + ", ";
		}
		System.out.println("-- Students: " + s);
		System.out.println("-- Grades: " + g);
		System.out.println("-- Average: "+ average); 
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
			scoreCount++; 	
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

	public void setSorter(SortStrategy s) { //++++++++++
		this.sorter = s;
	}

	public void showSortedGrades() { //++++++++++
		sorter.sort(grades);
		// int i=0;
		// while (s[i] <> null) {
		// 	System.out.println(i);
		// 	i++;
		// }
		// System.out.println(s);
	}
}

interface SortStrategy { //++++++++++
	public int[] sort(int [] s);
}

class SelectionSorter implements SortStrategy { //++++++++++
	public int[] sort(int [] s) {
		System.out.println(s);
		int [] s2 = s.clone();
		return s2;
	}
}

class Teacher extends Member implements Instructor {  	
	Course[] courses = new Course[Config.MAX_COURSE]; 
	int courseCount = 0;
	Qualification qualification; 

	public Teacher(String name) { 
		super(name); 
	}

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

	public void setQualification(Qualification q) { 
		this.qualification = q;
	}

	public void showQualification() { 
		System.out.println(name + " is a teacher because " + qualification);
	}
}

class Student extends Member {
	Course[] courses = new Course[Config.MAX_COURSE]; 

	int courseCount = 0;
	public Student (String name){ 
		super(name); 
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
		System.out.println("The grades of student " + name); 
		for (int i=0; i<courseCount; i++) {
			Course c = courses[i];
			String gString = "no grade";
			int g = c.getGrade(this);
			if (g != -1) gString = Integer.toString(g);
			System.out.println("-- " + c.cName + ": " + gString);
		}
	}
}

abstract class Member { 
	String name;
	private String email;
	public Member(String name) {
		this.name = name;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	public void showInfo() { 
		System.out.println(name + ", email: " + email);
	}	
}

interface Instructor { 
	public void setQualification(Qualification q);
	public void showQualification();
}

class Qualification { 
	String description;
	public Qualification(String desc) {
		this.description = desc;
	}
	public String toString() {
		return description;
	}
}

class Certification extends Qualification { 
	public Certification(int year, String desc) {
		super(desc + " " + Integer.toString(year));
	}
}

class IndustryExpert implements Instructor { 
	Qualification q;
	String name;
	public IndustryExpert(String name) {
		this.name = name;
	}
	public void setQualification(Qualification q) {
		this.q = q;
	}
	public void showQualification() {
		System.out.println(name + " is qualified to be an instructor because " + q);
	}
}