package basisObj;

public class Cylinder {
	private static final int XZSTEP = 30;
	private static final int YTERML = 5;

	private static float[] createVertices() {
		int step = 8;
		double r = 0.5;
		
		float[] vertices = new float[(XZSTEP*(2+YTERML)+2)*step];

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
		
		for(int i =0; i < YTERML; i++) {
			double y_dt = i*(double)1/(double)(YTERML-1);
			for(int j = 0; j < XZSTEP; j++) {
				theta = Math.toRadians(360*(double)(j)/(XZSTEP-1));
				x = r*Math.sin(theta);
				z = r*Math.cos(theta);
				y = -0.5;
				vertices[base + 0] = (float) x;
				vertices[base + 1] = (float) ((float) y+y_dt);
				vertices[base + 2] = (float) z;
				vertices[base + 3] = (float) x;
				vertices[base + 4] = (float) 0;
				vertices[base + 5] = (float) z;
				vertices[base + 6] = (float) ((float) theta/(2*Math.PI));
				vertices[base + 7] = (float) y_dt;
				base += step;
			}
		}
		
		return vertices;
	}
	
	private static int[] createIndices() {

		int step = 3;
		int base = 0;
		int point_base = 0;
		int[] indices = new int[XZSTEP*10*step];
		
		for(int i = 0; i < XZSTEP; i++) {
//			indices[base + 0] = 0;
//			indices[base + 1] = point_base + (i+1);
//			if (i< XZSTEP-1)	{indices[base + 2] = point_base + (i+2);}
//			else					    { indices[base + 2] = point_base + 1;}
			base += step;
		}
		point_base += (XZSTEP+1);
		// top
		for(int i = 0; i < XZSTEP; i++) {
//			indices[base + 0] = point_base;
//			indices[base + 1] = point_base + (i+1);
//			if (i< XZSTEP-1)	{indices[base + 2] = point_base + (i+2);}
//			else 					{ indices[base + 2] = point_base + 1;}
		
			base += step;
		}
		point_base += (XZSTEP+1);
		//bottom

		for( int i = 0; i < YTERML-1; i++) {
			for(int j = 0; j < XZSTEP; j++) {
				indices[base + 0] = point_base + j;
				if(j<XZSTEP-1) {indices[base + 1] = point_base + j + 1;}
				else 				  {indices[base + 1] = point_base + 1;}
				indices[base + 2] = point_base + XZSTEP  + j;
				base += step;
				
				indices[base + 0] = point_base + j+1;
				indices[base + 1] = point_base + XZSTEP  + j;
				if(j<XZSTEP-1) {indices[base + 2] = point_base + XZSTEP  + j + 1;}
				else				      {indices[base + 2] = point_base + XZSTEP  + 1;}
				base += step;
			}
			point_base += XZSTEP;
		}
		return indices;
	}
	
	  public static final float[] vertices = createVertices();	  
	  public static final int[] indices = createIndices();  
}
