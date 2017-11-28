/*
Refactoring
* Use Class to encapsulate a grade, to avoid the possible
  error when assignning a grade.
*/

import java.util.Arrays;	

public class GradeBookApp8 { 
	private Student Jie, Albert, Alex; 
	private Teacher Nick;
	private IndustryExpert Peter;
	private Course Java, Python;
	private Member[] members; //include all students and teachers
	private Instructor[] instructors;
	private Course[] courses;

	public static void main(String args[]) { 
		GradeBookApp8 gb = new GradeBookApp8(); //********
		gb.init(); 
		gb.takeCourse();
		gb.score();
		gb.show();
	}	

	private void init() { 
		// create students
		Alex = new Student ("Alex"); 
		Jie = new Student ("Jie");
		Albert = new Student ("Albert");

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

		Alex.takeCourse(Java); 
		Jie.takeCourse(Java);
		Albert.takeCourse(Java);
	}

	private void score() { 	// ********************
		try {
			Nick.score(Java, Alex, new Grade(70)); 
			Nick.score(Java, Jie, new Grade(100)); 
			Nick.score(Java, Albert, new Grade(98)); 
		}		
		catch (Exception e) {
			System.out.println("Illegal grade assignment");
		}

		Java.setSorter(new SelectionSorter()); 
		Java.showSortedGrades(); 
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

interface Config { 
	public static int MAX_STUDENT = 10; // max student number in a school
	public static int MAX_COURSE = 10; // max course number in a school
	// public static int NOT_SCORE = -1; // not a legal grade //----------
}

class Grade { //++++++++++++++++
	static int MAX=100;
	static int MIN=0;
	public static int NOT_SCORE = -1; // not a legal grade 
	private int v;

	public Grade(int g) throws Exception {
		if (g>MAX || g<MIN) {
			throw new Exception("illegal grade");
		}
		this.v = g;
	}

	public static String toLevel(int v) {
		String level="";
		if (v>=90) level = "A";
		if (v>=80 && v<90) level = "B";
		if (v>=70 && v<80) level = "C";
		if (v>=60 && v<70) level = "D";
		if (v<60) level = "E";
		return level;
	}

	public int v() { 
		return v;
	}
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

		for (int i=0; i< Config.MAX_STUDENT; i++)
			grades[i]=Grade.NOT_SCORE;
	}

	public void registeredBy(Student s) {
		if (studentCount <= Config.MAX_STUDENT-1) //**********
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
			g += Grade.toLevel(grades[i]) + ", "; //**********
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

	public void setSorter(SortStrategy s) { 
		this.sorter = s;
	}

	public void showSortedGrades() { 
		int size = 0;
		for (int i=0; i<grades.length; i++) {
			if (grades[i] == Grade.NOT_SCORE) {
				size = i;
				break;
			}
		}
		int[] g = new int[size];
		for (int i=0; i< size; i++) {
			g[i] = grades[i];
		}

		g = sorter.sort(g);
		System.out.println("The sorted grades are " + Arrays.toString(g));
	}
}

interface SortStrategy { 
	public int[] sort(int [] s);
}

class SelectionSorter implements SortStrategy { 
	public int[] sort(int [] s) {
		int [] s2 = s.clone();
		for (int i=1; i<=s2.length-1; i++)
			for (int j=0; j<s2.length-i; j++) {
				if (s2[j] < s2[j+1]) {
					int temp = s2[j];
					s2[j] = s2[j+1];
					s2[j+1] = temp;
				}	
			}
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

	public void score(Course c, Student s, Grade g) {  //**********
		if (offeredByMe(c) && c.takenBy(s)) {
			c.score(s, g.v()); //**********
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
		if (courseCount <= Config.MAX_COURSE-1 ) { //*****
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
			if (g != Grade.NOT_SCORE) gString = Integer.toString(g); //*****
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