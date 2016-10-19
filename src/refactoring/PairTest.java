package refactoring;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PairTest extends Pair {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
		
		Pair p = new Pair(10, 12);
		//Assert.assertEquals(20, p.add());
		Assert.assertEquals(22, p.add());
	}

}
