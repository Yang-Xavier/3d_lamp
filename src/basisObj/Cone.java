package basisObj;

/* Author Bingxun Yang xavierybx@gmail.com */
public class Cone {
	private static final int XZSTEP = 30;
	private static final int YTERML = 6;
	
	private static float[] createVertices() {
		int step = 8;
		float[] vertices = new float[(XZSTEP*(YTERML+1)+1)*step];
		int base = 0;
		double theta,x,y_base = -0.25,z;
		double r = 0.5, h = 1;
		double y_stpe = h/(YTERML-1);
		
		
		vertices[base + 0] = (float) 0;
		vertices[base + 1] = (float) -0.25;
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
			vertices[base + 0] = (float) x;
			vertices[base + 1] = (float) y_base;
			vertices[base + 2] = (float) z;
			vertices[base + 3] = (float) 0;
			vertices[base + 4] = (float) -1;
			vertices[base + 5] = (float) 0;
			vertices[base + 6] = (float) 0;
			vertices[base + 7] = (float) 0;
			base += step;
		}
		// bottom circle
		
		double det_r = 1;
		double alpha = Math.sqrt(0.5);
		for(int i=0; i<YTERML; i++) {
			for(int j = 0; j< XZSTEP; j++) {
				theta = Math.toRadians(360*(double)(j)/(XZSTEP-1));
				x = Math.sin(theta);
				z = Math.cos(theta);
				vertices[base + 0] = (float) ((float) det_r*x);
				vertices[base + 1] = (float)  y_base;
				vertices[base + 2] = (float) ((float) det_r*z);
				vertices[base + 3] = (float) ((float) alpha*x);
				vertices[base + 4] = (float) 1;
				vertices[base + 5] = (float) ((float) alpha*z);
				vertices[base + 6] = (float) ((float) det_r);
				vertices[base + 7] = (float) ((float) det_r);
				base += step;
			}
			y_base += y_stpe;
			det_r -= y_stpe;
			
		}
		return vertices;
	}
	
	private static int[] createIndices() {

		int step = 3;
		int base = 0;
		int point_base = 0;
		int[] indices = new int[XZSTEP*11*step];
		
		for(int i = 0; i < XZSTEP; i++) {
			indices[base + 0] = point_base;
			indices[base + 1] = point_base + (i+1);
			if (i< XZSTEP-1)	{indices[base + 2] = point_base + (i+2);}
			else 					{ indices[base + 2] = point_base + 1;}
		
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
				
				indices[base + 0] = point_base + XZSTEP  + j;
				indices[base + 1] = point_base + j+1;
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
