package test;

import basisObj.*;

public class TestObj {
public static void main(String[] args) {
	float[] vec = Cylinder.vertices.clone();
	int[] index = Cylinder.indices.clone();

	float[] svec = Sphere.vertices.clone();
	int[] sindex = Sphere.indices.clone();
//	for(int i=0; i< vec.length; i++) {
//		if(i%8==0) {
//			System.out.println((i/8)+"__:__");
//		}
//		System.out.println(vec[i]);
//	}
	
	print(0, svec, 8);
	print(1, svec, 8);
	print(37, svec, 8);
	print(36, svec, 8);
//	print(92, svec, 8);
//	print(93, svec, 8);
	
//
//	for(int i=0; i< sindex.length; i++) {
//		System.out.println(sindex[i]);
//	}
//	System.out.println(index.length);
	print(62, index, 3);
	print(63, index, 3);
//	print(92, index, 3);
//	print(63, index, 3);
//	print(92, index, 3);
//	print(93, index, 3);

}

public static void print(int index, float[] a,int step) {
	System.out.printf(index + "__");
	for (int i = 0; i < step; i++) {
		System.out.printf(a[step*index+i]+"===");
	}
	System.out.println();
}
public static void print(int index, int[] a,int step) {
	System.out.printf(index + "__");
	for (int i = 0; i < step; i++) {
		System.out.printf(a[step*index+i]+"===");
	}
	System.out.println();
}
}
