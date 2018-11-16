package lamp;

import com.jogamp.opengl.GL3;

import scene.Camera;
import scene.Light;

public class Lamp{
	
	Foundation foundation;
	GL3 gl;
	public Lamp(GL3 gl) {
		this.gl = gl;
		foundation = new Foundation(gl);
		
	}
	
	public void render(Camera camera,Light light,GL3 gl) {
		foundation.render(camera, light, gl);
	}
	
	public void dispose(GL3 gl) {
		foundation.dispose(gl);
	}
}
