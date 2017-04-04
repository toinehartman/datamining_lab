import static org.junit.Assert.*;

import org.junit.Test;

public class DRTest {

	@Test
	public void testMatrixTranspose() {
		Matrix m1 = new Matrix(2,2);
		Matrix m2 = new Matrix(2,2);
		
		m1.set(0,0, 2);
		m1.set(0,1, 3);
		m1.set(1,0, 4);
		m1.set(1,1, 5);
		
		m2.set(0,0, 2);
		m2.set(0,1, 4);
		m2.set(1,0, 3);
		m2.set(1,1, 5);
		
		assertEquals(m2, m1.transpose());
		
	}
	
	@Test
	public void testMatrixNorm() {
		Matrix m = new Matrix(2,2);
		
		m.set(0,0, 2);
		m.set(0,1, 3);
		m.set(1,0, 4);
		m.set(1,1, 5);
		
		assertEquals(Math.sqrt(2*2+3*3+4*4+5*5), m.norm(), 0.05);
		
	}
	
	@Test
	public void testMatrixMeanRow() {
		Matrix m = new Matrix(2,2);
		
		m.set(0,0, 2);
		m.set(0,1, 3);
		m.set(1,0, 4);
		m.set(1,1, 5);
	
		assertEquals(3, m.meanRow().get(0,0), 0.05);
		assertEquals(4, m.meanRow().get(0,1), 0.05);
		
	}
	
	@Test
	public void testMatrixSubtractRow() {
		Matrix m = new Matrix(2,2);
		Matrix r = new Matrix(1,2);
		
		m.set(0,0, 2);
		m.set(0,1, 3);
		m.set(1,0, 4);
		m.set(1,1, 5);
		
		r.set(0,0, 1);
		r.set(0,1, 1);
		
		m = m.subtractRow(r);
		assertEquals(1, m.get(0,0), 0.05);
		
	}

}
