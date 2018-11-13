package basisObj;

public class Cylinder {
	private static final int XZSTEP = 30;

	private static float[] createVertices() {
		int step = 8;
		double r = 0.5;
		float[] vertices = new float[(XZSTEP*4+2)*step];
		
		double theta,x,y,z;
		int base = 0;
		vertices[base + 0] = (float) 0;
		vertices[base + 1] = (float) 0.5;
		vertices[base + 2] = (float) 0;
		vertices[base + 3] = (float) 0;
		vertices[base + 4] = (float) 1;
		vertices[base+ 5] = (float) 0;
		vertices[base + 6] = (float) 0.5;
		vertices[base + 7] = (float) 0.5;
		base += step;
		// top centre point
		
		for(int i = 0; i < XZSTEP; i++ ) {
			theta = Math.toRadians(360*(double)(i)/(XZSTEP-1));
			x = r*Math.sin(theta);
			z = r*Math.cos(theta);
			y = 0.5;
			vertices[base + 0] = (float) x;
			vertices[base + 1] = (float) y;
			vertices[base + 2] = (float) z;
			vertices[base + 3] = (float) 0;
			vertices[base + 4] = (float) 1;
			vertices[base + 5] = (float) 0;
			vertices[base + 6] = (float) 0;
			vertices[base + 7] = (float) 0;
			base += step;
		}
		// top circle
		
		vertices[base + 0] = (float) 0;
		vertices[base + 1] = (float) -0.5;
		vertices[base + 2] = (float) 0;
		vertices[base + 3] = (float) 0;
		vertices[base + 4] = (float) -1;
		vertices[base+ 5] = (float) 0;
		vertices[base + 6] = (float) 0.5;
		vertices[base + 7] = (float) 0.5;
		base += step;
		// bottom centre point
		
		for(int i = 0; i < XZSTEP; i++ ) {
			theta = Math.toRadians(360*(double)(i)/(XZSTEP-1));
			x = r*Math.sin(theta);
			z = r*Math.cos(theta);
			y = -0.5;
			vertices[base + 0] = (float) x;
			vertices[base + 1] = (float) y;
			vertices[base + 2] = (float) z;
			vertices[base + 3] = (float) 0;
			vertices[base + 4] = (float) -1;
			vertices[base + 5] = (float) 0;
			vertices[base + 6] = (float) 0;
			vertices[base + 7] = (float) 0;
			base += step;
		}
		// bottom circle
		

		for(int i = 0; i < XZSTEP; i++ ) {
			theta = Math.toRadians(360*(double)(i)/(XZSTEP-1));
			x = r*Math.sin(theta);
			z = r*Math.cos(theta);
			y = 0.5;
			vertices[base + 0] = (float) x;
			vertices[base + 1] = (float) y;
			vertices[base + 2] = (float) z;
			vertices[base + 3] = (float) x;
			vertices[base + 4] = (float) 0;
			vertices[base + 5] = (float) z;
			vertices[base + 6] = (float) ((float) theta/(2*Math.PI));
			vertices[base + 7] = (float) 1;
			base += step;
		}
		// top edge
		
		for(int i = 0; i < XZSTEP; i++ ) {
			theta = Math.toRadians(360*(double)(i)/(XZSTEP-1));
			x = r*Math.sin(theta);
			z = r*Math.cos(theta);
			y = -0.5;
			vertices[base + 0] = (float) x;
			vertices[base + 1] = (float) y;
			vertices[base + 2] = (float) z;
			vertices[base + 3] = (float) x;
			vertices[base + 4] = (float) 0;
			vertices[base + 5] = (float) z;
			vertices[base + 6] = (float) ((float) theta/(2*Math.PI));
			vertices[base + 7] = (float) 0;
			base += step;
		}
		// bottom edge
		
		return vertices;
	}
	
	private static int[] createIndices() {
		int[] indices = new int[XZSTEP*3*4];
		int step = 3;
		int base = 0;
		int point_base = 0;
		
//		for(int i = 0; i < XZSTEP; i++) {
//			if(i == XZSTEP-1) {
//				indices[base + i*step + 0] = 0;
//				indices[base + i*step + 1] = point_base + (i+1);
//				indices[base + i*step + 2] = point_base + 1;
//			} else {
//				indices[base + i*step + 0] = 0;
//				indices[base + i*step + 1] = point_base + (i+1);
//				indices[base + i*step + 2] = point_base + (i+2);
//			}
//		}
		base += XZSTEP*step;
		point_base += (XZSTEP+1);
		// top
		
//		for(int i = 0; i < XZSTEP; i++) {
//			if(i == XZSTEP-1) {
//				indices[base + i*step + 0] = point_base;
//				indices[base + i*step + 1] = point_base + ( i + 1);
//				indices[base + i*step + 2] = point_base + i - XZSTEP + 2;
//			} else {
//				indices[base + i*step + 0] = point_base;
//				indices[base + i*step + 1] = point_base + (i+1);
//				indices[base + i*step + 2] = point_base + (i+2);
//			}
//		}
		base += XZSTEP*step;
		point_base += (XZSTEP+1);
		//bottom
		
		for(int i = XZSTEP; i >0; i--) {
			indices[base + i*step + 0] = point_base + i;
			indices[base + i*step + 1] = point_base + (i+1);
			indices[base + i*step + 2] = point_base + XZSTEP  + i;
		}
		
//		base += XZSTEP*step;
//		for(int i = 0; i < XZSTEP; i++) {
//			indices[base + i*step + 0] = point_base + (i+1);
//			indices[base + i*step + 1] = point_base + XZSTEP + i;
//			indices[base + i*step + 2] = point_base + XZSTEP  + (i+1);
//		}
		//body

		return indices;
	}
	
	  public static final float[] vertices = createVertices();	  
	  public static final int[] indices = createIndices();  
}
