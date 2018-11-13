package test;

import basisObj.Cylinder;

public class TestObj {
public static void main(String[] args) {
	float[] vec = Cylinder.vertices.clone();
	int[] index = Cylinder.indices.clone();

//	for(int i=0; i< vec.length; i++) {
//		if(i%8==0) {
//			System.out.println((i/8)+"__:__");
//		}
//		System.out.println(vec[i]);
//	}
	
	print(62, vec, 8);
	print(63, vec, 8);
	print(92, vec, 8);
	print(63, vec, 8);
	print(92, vec, 8);
	print(93, vec, 8);
//
//	for(int i=0; i< index.length; i++) {
//		System.out.println(index[i]);
//	}
//	System.out.println(index.length);
//	print(62, index, 3);
//	print(63, index, 3);
//	print(92, index, 3);
//	print(63, index, 3);
//	print(92, index, 3);
//	print(93, index, 3);

}

public static void print(int index, float[] a,int step) {
	System.out.printf(index + "__");
	for (int i = 0; i < step; i++) {
		System.out.printf(a[step*index+i]+"_");
	}
	System.out.println();
}
public static void print(int index, int[] a,int step) {
	System.out.printf(index + "__");
	for (int i = 0; i < step; i++) {
		System.out.printf(a[step*index+i]+"_");
	}
	System.out.println();
}
}
