package uml;

public class GradeBook {

	// how can we keep the grades private?
	int grades[][];
	int subjectAvg[];
	int studentAvg[];

	public GradeBook(int[][] grades) {

	}

	public String getSubjectAvg(int jid) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getStudentAvg(int sid) {
		// TODO Auto-generated method stub
		return null;
	}

	private double[] getSubjectAvg() {
		// TODO Auto-generated method stub
		return null;
	}

	private double[] getStudentAvg() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		int[][] grades = { { 12, 23, 34 }, { 34, 45, 56 }, { 67, 78, 90 }, { 1, 1, 1 } };
		GradeBook g = new GradeBook(grades);

		int sid = 1;
		System.out.println("The average for student " + sid + " is " + g.getStudentAvg(sid));

		int jid = 0; // subject ID
		System.out.println("The average for subject " + jid + " is " + g.getSubjectAvg(jid));

		double[] subjectAvg = g.getStudentAvg();
		double[] studentAvg = g.getSubjectAvg();

	}

}
