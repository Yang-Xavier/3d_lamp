package basisObj;

public class Hemisphere {
	private static final int XZCSTEP= 30;
	  private static final int  YSTEP = 15;	
	  
	  private static float[] createVertices() {
		    double r = 0.5;
		    int step = 8;
		    float[] vertices = new float[(XZCSTEP*YSTEP+XZCSTEP+1)*step];
		    int base = 0;
		    for (int j = 0; j<YSTEP; ++j) {
		      double b = Math.toRadians(180*(double)(j)/(YSTEP-1));
		      for (int i = 0; i<XZCSTEP; ++i) {
		    	  double a = Math.toRadians(360*(double)(i)/(XZCSTEP-1));
		          double z = r*Math.cos(b) * Math.cos(a);
		          double x = r*Math.cos(b) * Math.sin(a);
		          double y = r* Math.sin(b);
		          vertices[base +0] = (float)x;
		          vertices[base +1] = (float)y;
		          vertices[base +2] = (float)z; 
		          vertices[base +3] = (float)x;
		          vertices[base +4] = (float)y;
		          vertices[base +5] = (float)z;
		          vertices[base +6] = (float)(i)/(float)(XZCSTEP-1);
		          vertices[base +7] = (float)(j)/(float)(YSTEP-1);
		          base+=step;
		      }
		    }
		    // Hemi body
			vertices[base + 0] = (float) 0;
			vertices[base + 1] = (float) 0.5;
			vertices[base + 2] = (float) 0;
			vertices[base + 3] = (float) 0;
			vertices[base + 4] = (float) 1;
			vertices[base+ 5] = (float) 0;
			vertices[base + 6] = (float) 0.5;
			vertices[base + 7] = (float) 0.5;
			//bottom center point
			double theta,x,y,z;
			for(int i = 0; i < XZCSTEP; i++ ) {
				theta = Math.toRadians(360*(double)(i)/(XZCSTEP-1));
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
		    return vertices;
		  }
	  
	  private static int[] createIndices() {
		    int[] indices = new int[(XZCSTEP-1)*(YSTEP-1)*6+30*3];
		    int base = 0;
		    for (int j = 0; j<YSTEP-1; ++j) {
		      for (int i = 0; i<YSTEP-1; ++i) {
		        indices[base +0] = j*XZCSTEP+i;
		        indices[base +1] = j*XZCSTEP+i+1;
		        indices[base +2] = (j+1)*XZCSTEP+i+1;
		        indices[base +3] = j*XZCSTEP+i;
		        indices[base +4] = (j+1)*XZCSTEP+i+1;
		        indices[base +5] = (j+1)*XZCSTEP+i;
		        base+=6;
		      }
		    }
		 // Hemi body
		    
		    int pointBase= (XZCSTEP-1)*(YSTEP-1);
		    for(int i = 0; i < XZCSTEP; i++) {
				indices[base + 0] = pointBase;
				indices[base + 1] = pointBase + (i+1);
				if (i< XZCSTEP-1)	{indices[base + 2] = pointBase + (i+2);}
				else 					{ indices[base + 2] = pointBase + 1;}
				base += 3;
			}
		 // Hemi bottom
		    return indices;
	  }
	  
		public static final float[] vertices = createVertices();	  
		public static final int[] indices = createIndices();
}
