package Version4;

import java.lang.*;

import java.util.ArrayList;

/**
* This version of the vector class has 100% of it "completed", which is 
* tested and all of the tests will pass! 
*/
public class Vector {
	
	private int N = 0;
	private ArrayList<Double> data;
	
	//empty 
	public Vector() {
		N=0;
		data = new ArrayList<Double>();
	}
	
	//a vector is created of size - size, with elements initalized to D 
	public Vector(int size, double D) {
		N = size;
		data = new ArrayList<Double>();
		for(int i=0; i<size; i++) data.add(D);
	}
	
	//a vector is created to be initialized to the array D 
	public Vector(double [] D) {
		int length = D.length;
		N = length;
		data = new ArrayList<Double>();
		for(int i=0; i<length; i++) {
			data.add(D[i]);
		}
	}
	
	//the vector is initalized to Int I 
	public Vector(int [] I) {
		int length = I.length;
		N = length;
		data = new ArrayList<Double>();
		for(int i=0; i<length; i++) {
			data.add( (double)I[i] );
		}
	}
	
	public void append(double[] doubleArray) {
		int len = doubleArray.length;
		for(int i=0; i<len; i++) {
			data.add(doubleArray[i]);
		}
	}
	
	public void append(int[] intArray) {
		int len = intArray.length;
		for(int i=0; i<len; i++) {
			data.add((double)intArray[i]);
		}
	}
	
	//this will be equivalent to the vector V
	public void append(Vector V) {
		int len = V.getLength();
		for(int i=0; i<len; i++) {
			data.add(V.getValue(i));
		}
	}
	
	public void append(double aDouble) {
		data.add(aDouble);
	}
	
	public boolean equal(Vector V) {
		int len = V.getLength();
		for(int i=0; i<len; i++) {
			if(Math.abs(data.get(i) - V.getValue(i)) >0.1)return false;
		}
		return true;
		
	}
	
	//returns the # of elements
	int getLength() {
		return N;
	}
	
	//returns the value  this[i]
	double getValue(int i) {
		return data.get(i);
	}
	
	//add this to V, returning a Vector the same size as this
	public Vector add(Vector V) {
		Vector result = new Vector();
		for(int i=0; i<N; i++) {
			result.append(data.get(i) + V.getValue(i));
		}
		return result;
	}
	
	//add aDouble to every element of this
	public Vector add(double aDouble) {
		Vector result = new Vector(N, aDouble);
		for(int i=0; i<N; i++) {
			result.set(i, result.getValue(i) + this.getValue(i));
		}
		return result;
		
	}
	
	private void set(int i, double d) {
		data.set(i, d);
	}

	//sub this – V 
	public Vector sub(Vector V) {
		Vector res = new Vector(N, 0);
		for(int i=0; i<N; i++) {
			res.set(i, this.getValue(i) - V.getValue(i));
		}
		return res;
	}
	
	//will return a sub vector between the       
	//indices l and r inclusive 
	public Vector subV(int l, int r) {
		Vector fin = new Vector();
		for(int i=l; i<=r; i++) {
			fin.append(this.getValue(i));
		}
		return fin;	
	}
	
	//Multiple every element of this by corresponding element in V 
	public Vector Mult(Vector V) {
		Vector fin = new Vector();
		for(int i=0; i<N; i++) {
			fin.append(this.getValue(i) * V.getValue(i));
		}
		return fin;
	}
	
	//Multiply every element of this by aDouble 
	public Vector Mult(double aDouble) {
		Vector fin = new Vector();
		for(int i=0; i<N; i++) {
			fin.append(this.getValue(i) * aDouble);
		}
		return fin;
	}
	
	//returns this as a normalized vector 
	public Vector Normalize() {
		double div = 0;
		Vector fin = new Vector();
		for(int i=0; i<N; i++) {
			div += data.get(i) * data.get(i);
		}
		div = Math.sqrt(div);
		for(int i=0; i<N; i++) {
			fin.append( data.get(i) / div);
		}
		return fin;
	}
	
	//Returns the euclidean distance between this and V 
	double EuclidianDistance(Vector V){
		double div = 0;
		for(int i=0; i<N; i++) {
			div += Math.pow(V.getValue(i) - this.getValue(i), 2);
		}
		div = Math.sqrt(div);
		return div;
	}

}
