package Version4;

import org.junit.Test;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import Version4.Vector;

public class TestJUnitVector {
	@Test
	public void testConstructor() {
		try {
			Vector newVector = new Vector();
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testConstructor2() {
		try {
			Vector newVector = new Vector(5, 3);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testConstructor3() {
		try {
			double []arr = {32,21,35};
			Vector newVector = new Vector(arr);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testConstructor4() {
		try {
			int [] arr = {32,21,35};
			Vector newVector = new Vector(arr);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	
	public void testAppend1() {
		try {
			Vector newVector = new Vector(4, 2);
			newVector.append(3.4);
		}
		catch(Exception e) {
			Assert.fail("Test Failed" + e.getMessage());
		}
		
	}
	
	@Test
	public void testAppend2() {
		try {
			Vector newVector = new Vector();
			double [] arr = {3.4,1.2,7.8};
			newVector.append(arr);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
		
	}
	
	@Test
	public void testAppend3() {
		try {
			Vector newVector = new Vector();
			int [] arr = {3,1,7};
			newVector.append(arr);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testAppend4() {
		try {
			Vector newVector = new Vector();
			Vector appendVector = new Vector(4, 3.4);
			newVector.append(appendVector);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testEqual() {
		try {
			Vector newVector = new Vector(4, 3.4);
			Vector secondVector = new Vector(4, 3.4);
			newVector.equal(secondVector);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}

	
	@Test
	public void testGetLength() {
		try {
			Vector newVector = new Vector(4, 3.4);
			assertEquals(newVector.getLength(), 4);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testGetValue() {
		try {
			Vector newVector = new Vector(4, 2.4);
			assertEquals(newVector.getValue(2), 2.4, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testAdd1() {
		try {
			Vector newVector = new Vector(4, 2);
			Vector secondVector = new Vector(4, 3);
			Vector finalVec = newVector.add(secondVector);
			assertEquals(finalVec.getValue(2), 5, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testAdd2() {
		try {
			Vector newVector = new Vector(4, 2);
			Vector finalVec = newVector.add(3);
			assertEquals(finalVec.getValue(2), 5, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testSub() {
		try {
			Vector newVector = new Vector(4, 3);
			Vector secondVector = new Vector(4, 2);
			Vector finalVec = newVector.sub(secondVector);
			assertEquals(finalVec.getValue(2), 1, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testSubV() {
		try {
			Vector newVector = new Vector(6, 2.4);
			Vector secondVector = new Vector(3, 2.4);
			assertEquals(secondVector.equal(newVector.subV(0, 3)), true);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testMult() {
		try {
			Vector newVector = new Vector(4, 2.4);
			Vector secondVector = new Vector(4, 2);
			Vector finalVec = newVector.Mult(secondVector);
			assertEquals(finalVec.getValue(2), 4.8, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testMult2() {
		try {
			Vector newVector = new Vector(4, 2.4);
			Vector finalVec = newVector.Mult(2);
			assertEquals(finalVec.getValue(2), 4.8, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testNormalization() {
		try {
			Vector newVector = new Vector(3, 2);
			Vector normalizedVector = newVector.Normalize();
			assertEquals(normalizedVector.getValue(2), 0.57, 0.1);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	@Test
	public void testEudDistance() {
		try {
			Vector newVector = new Vector(2, 2);
			Vector secondVector = new Vector(2, 3);
			assertEquals(newVector.EuclidianDistance(secondVector), 1.41, 0.1);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
}
