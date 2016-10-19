package refactoring;

//
public class Triangle {
	int a, b, c;

	public Triangle(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public String getType() {
		String result = "";
		
		if ((a + b < c) || (a + c < b) || (c + b < a)) {
			result = "���O�T����";
			return result;
		}	
		else
			result = "�@��T����";

		if (a == b && b == c ) {
			result = "���T����";
			return result;
		}

		if ((a == b) || (b == c) || (a == c)) {
			result = "���y�T����";
			return result;
		}

		if ((a * a + b * b == c * c) || (b * b + c * c == a * a)
				|| (a * a + c * c == b * b)) {
			result = "�����T����";
			return result;
		}

		return result;
	}

}
