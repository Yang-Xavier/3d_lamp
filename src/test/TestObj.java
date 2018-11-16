package test;

import basisObj.*;

public class TestObj {
public static void main(String[] args) {
	float[] vec = Cone.vertices.clone();
	int[] index = Cone.indices.clone();
	
	print(vec);
//	print(190, vec, 8);
//	System.out.println(index.length/3);
//	print(index);

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
public static void print(int[] a) {
	for (int i = 0; i < a.length; i+=3) {
		for (int j = 0; j < 3; j++) {
			System.out.printf(a[i+j]+"===");
		}
		System.out.println();
	}
}

public static void print(float[] a) {
	for (int i = 0; i < a.length; i+=8) {
		for (int j = 0; j < 8; j++) {
			System.out.printf(a[i+j]+"===");
		}
		System.out.println();
	}
}


}
