package basisObj;

public class Cylinder {
	private static final int XZSTEP = 30;
	private static final int YSTEP = 20;

	private float[] createVertices() {
		int step = 8;
		int base = 0;
		double r = 0.5;
		float[] vertices = new float[(XZSTEP*YSTEP+2)*step];
		
		double a,x,y,z;
		for(int i = 0; i < XZSTEP; i++ ) {
			a = Math.toRadians(360*(double)(i)/(XZSTEP-1));
			x = r*Math.sin(a);
			z = r*Math.cos(a);
			y = -0.5;
			vertices[base + i*step + 0] = (float) x;
			vertices[base + i*step + 1] = (float) y;
			vertices[base + i*step + 2] = (float) z;
			vertices[base + i*step + 3] = (float) 0;
			vertices[base + i*step + 4] = (float) -0.5;
			vertices[base + i*step + 5] = (float) 0;
			vertices[base + i*step + 6] = (float) 0;
			vertices[base + i*step + 7] = (float) 0;
		}
		// top
		
		for(int j = 0; j < YSTEP; j++) {
			base = (j+1)*XZSTEP*step;
			for(int i = 0; i < XZSTEP; i++ ) {
				a = Math.toRadians(360*(double)(i)/(XZSTEP-1));
				x = r*Math.sin(a);
				z = r*Math.cos(a);
				y = -0.5;
				vertices[base + i*step + 0] = (float) x;
				vertices[base + i*step + 1] = (float) y;
				vertices[base + i*step + 2] = (float) z;
				vertices[base + i*step + 3] = (float) x;
				vertices[base + i*step + 4] = (float) y;
				vertices[base + i*step + 5] = (float) z;
				vertices[base + i*step + 6] = (float) 0;
				vertices[base + i*step + 7] = (float) 0;
			}
		}
		
		
		
		return vertices;
	}
}
