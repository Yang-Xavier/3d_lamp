package basisObj;

public class Sphere {
	
	  private static final int XZCSTEP= 36;
	  private static final int  YSTEP = 36;	
	  
	  private static float[] createVertices() {
		    double r = 0.5;
		    int step = 8;
		    float[] vertices = new float[XZCSTEP*YSTEP*step];
		    
		    for (int j = 0; j<YSTEP; ++j) {
		      double b = Math.toRadians(-90+180*(double)(j)/(YSTEP-1));
		      for (int i = 0; i<XZCSTEP; ++i) {
		        double a = Math.toRadians(360*(double)(i)/(XZCSTEP-1));
		        double z = Math.cos(b) * Math.cos(a);
		        double x = Math.cos(b) * Math.sin(a);
		        double y = Math.sin(b);
		        int base = j*XZCSTEP*step;
		        vertices[base + i*step+0] = (float)(r*x);
		        vertices[base + i*step+1] = (float)(r*y);
		        vertices[base + i*step+2] = (float)(r*z); 
		        vertices[base + i*step+3] = (float)x;
		        vertices[base + i*step+4] = (float)y;
		        vertices[base + i*step+5] = (float)z;
		        vertices[base + i*step+6] = (float)(i)/(float)(XZCSTEP-1);
		        vertices[base + i*step+7] = (float)(j)/(float)(YSTEP-1);
		      }
		    }
		    return vertices;
		  }
	  private static int[] createIndices() {
		    int[] indices = new int[(XZCSTEP-1)*(YSTEP-1)*6];
		    for (int j = 0; j<YSTEP-1; ++j) {
		      for (int i = 0; i<YSTEP-1; ++i) {
		        int base = j*(YSTEP-1)*6;
		        indices[base + i*6+0] = j*XZCSTEP+i;
		        indices[base + i*6+1] = j*XZCSTEP+i+1;
		        indices[base + i*6+2] = (j+1)*XZCSTEP+i+1;
		        indices[base + i*6+3] = j*XZCSTEP+i;
		        indices[base + i*6+4] = (j+1)*XZCSTEP+i+1;
		        indices[base + i*6+5] = (j+1)*XZCSTEP+i;
		      }
		    }
		    return indices;
	  }
	  
		private static final float[] verteice = createVertices();	  
		private static final int[] indices = createIndices();	  
}
