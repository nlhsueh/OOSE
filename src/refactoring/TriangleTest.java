package refactoring;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testGetType() {
		// fail("Not yet implemented");

		String expected = "���T����";
		Triangle t1 = new Triangle(10, 10, 10);
		Assert.assertEquals(expected, t1.getType());

		expected = "���y�T����";
		Triangle t2 = new Triangle(10, 10, 18);
		Assert.assertEquals(expected, t2.getType());
		t2 = new Triangle(18, 10, 10);
		Assert.assertEquals(expected, t2.getType());
		t2 = new Triangle(10, 18, 10);
		Assert.assertEquals("���y�T����", t2.getType());

		expected = "�����T����";
		Triangle t3 = new Triangle(3, 4, 5);
		Assert.assertEquals(expected, t3.getType());
		t3 = new Triangle(5, 4, 3);
		Assert.assertEquals(expected, t3.getType());
		t3 = new Triangle(4, 5, 3);
		Assert.assertEquals(expected, t3.getType());
  
		expected = "�@��T����";
		Triangle t4 = new Triangle(3, 4, 6);
		Assert.assertEquals(expected, t4.getType());
		t4 = new Triangle(4, 6, 3);
		Assert.assertEquals(expected, t4.getType());
		t4 = new Triangle(6, 3, 4);
		Assert.assertEquals(expected, t4.getType());

		expected = "���O�T����";
		Triangle t5 = new Triangle(1, 1, 3);
		Assert.assertEquals(expected, t5.getType());
		t5 = new Triangle(3, 1, 1);
		Assert.assertEquals(expected, t5.getType());
		t5 = new Triangle(1, 3, 1);
		Assert.assertEquals(expected, t5.getType());
		
		//Assert.

	}

}
