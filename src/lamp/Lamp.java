package lamp;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.ModelContainer;

public class Lamp{
	
	Foundation foundation;
	BodyPole bodyPole;
	
	Vec3 baseAxis;
	GL3 gl;
	
	ModelContainer[] modelContainers;
	public Lamp(GL3 gl) {
		this.gl = gl;
		baseAxis = new Vec3(0f, 5f, -7f);
		foundation = new Foundation(gl);
		bodyPole = new BodyPole(gl);
		
		modelContainers = new ModelContainer[2];
		modelContainers[0] = foundation;
		modelContainers[1] = bodyPole;
	}
	
	public void initial() {
		foundation.setBaseAxis(baseAxis);
		bodyPole.setBaseAxis(baseAxis);
		
		foundation.initial();
		bodyPole.initial();
	}
	
	public void render(Camera camera,Light light,GL3 gl) {
		for(int i = 0; i < modelContainers.length; i++) {
			modelContainers[i].render(camera, light, gl);
		}
	}
	
	public void dispose(GL3 gl) {
		for(int i = 0; i < modelContainers.length; i++) {
			modelContainers[i].dispose(gl);
		}
	}
}
