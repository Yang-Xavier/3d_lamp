package lamp;



import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.ModelContainer;

public class Lamp{
	
	Foundation foundation;
	BodyPole bodyPole;
	Head head;
	
	Vec3 baseAxis = new Vec3(0f, 5f, -7f);
	Vec3 H_speed = new Vec3(0.01f,0,0);
	Vec3 V_speed = new Vec3(0, 0.5f, 0);
	
	float theta = 0;
	
	GL3 gl;
	List<ModelContainer> modelContainers;
	
	public Lamp(GL3 gl) {
		this.gl = gl;
		foundation = new Foundation(gl);
		bodyPole = new BodyPole(gl);
		head = new Head(gl);
		
		modelContainers = new ArrayList<ModelContainer>();
		
		modelContainers.add(foundation);
		modelContainers.add(bodyPole);
		modelContainers.add(head);
	}
	
	public void initial() {
		
		setChildBaseAxis(baseAxis);
		for(ModelContainer modelContainer :modelContainers) {
			modelContainer.initial();
		}
	}
	
	public void update(Camera camera,Light light,GL3 gl) {
		Vec3 step = new Vec3(0,0,0);
		step.add(H_speed);
		baseAxis.add(step);
		setChildBaseAxis(baseAxis);
		
		render(camera, light, gl);
	}
	
	public void setChildBaseAxis(Vec3 baseAxis) {
		for(ModelContainer modelContainer :modelContainers) {
			modelContainer.setBaseAxis(baseAxis);
		}
	}
	
	public void render(Camera camera,Light light,GL3 gl) {
		for(ModelContainer modelContainer :modelContainers) {
			modelContainer.render(camera, light, gl);
		}
	}
	
	public void dispose(GL3 gl) {
		for(ModelContainer modelContainer :modelContainers) {
			modelContainer.dispose(gl);
		}
	}
}
