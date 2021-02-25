package Version1; // version obviously depends of what version

import org.junit.Test;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import Version1.Vector; //import the version to test

/**
* This is the test class, where tests for each part of the vector class will be tested
* Our goal is for 100% of these test to pass. The tests attempt to use the
* method, if it fails, we get a message, which takes fractions of a second, when more 
* tests are done, it takes longer, but when all tests pass, the test is quick!
*/
public class TestJUnitVector {
	
	// Attempts creation of a vector
	@Test
	public void testConstructor() {
		try {
			Vector newVector = new Vector();
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// attempts to create a  vector of a size "size"
	@Test
	public void testConstructor2() {
		try {
			Vector newVector = new Vector(5, 3);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// tests doubles
	@Test
	public void testConstructor3() {
		try {
			double []arr = {32.5,21.5,35.5};
			Vector newVector = new Vector(arr);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// tests ints
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
	
	//attempts to append a double 
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
	
	// attempts to append a an array of ints
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
	
	//attempts to append a vector
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
	
	//attempts append a double
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
	
	//tests if the vectors are the same
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

	//checks the length of a vector
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
	
	// checks if the value you obtain is the same at the position
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
	
	// tries to add this to the vector, and it returns the vector of same size
	@Test
	public void testAdd1() {
		try {
			Vector newVector = new Vector(4, 2);
			Vector secondVector = new Vector(4, 3);
			newVector.add(secondVector);
			assertEquals(newVector.getValue(2), 5, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// attempts to add a doubl to the vector
	@Test
	public void testAdd2() {
		try {
			Vector newVector = new Vector(4, 2);
			newVector.add(3);
			assertEquals(newVector.getValue(2), 5, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// tries to subtract an element from the vector
	@Test
	public void testSub() {
		try {
			Vector newVector = new Vector(4, 3);
			Vector secondVector = new Vector(4, 2);
			newVector.sub(secondVector);
			assertEquals(newVector.getValue(2), 1, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	//tries to returned a subtracted vector between these two points
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
	
	// tests if every element is multiplied by its corresponding element in first vector
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
	
	// tests if every element is multiplied by a double 
	@Test
	public void testMult2() {
		try {
			Vector newVector = new Vector(4, 2.4);
			newVector.Mult(2);
			assertEquals(newVector.getValue(2), 4.8, 0.01);
		}
		catch(Exception e) {
			Assert.fail("Test Failed : " + e.getMessage());
		}
	}
	
	// tests if a normalized vector is returned
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
	
	// test to see distance between two vectors
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
	
} //end test
