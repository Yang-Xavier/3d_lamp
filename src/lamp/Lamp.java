package lamp;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;

public class Lamp{
	
	Foundation foundation;
	
	
	Vec3 baseAxis;
	GL3 gl;
	public Lamp(GL3 gl) {
		this.gl = gl;
		baseAxis = new Vec3(0f, 5f, -7f);
		foundation = new Foundation(gl);
		
	}
	
	public void initial() {
		foundation.setBaseAxis(baseAxis);
		foundation.initial();
	}
	
	public void render(Camera camera,Light light,GL3 gl) {
		foundation.render(camera, light, gl);
	}
	
	public void dispose(GL3 gl) {
		foundation.dispose(gl);
	}
}
