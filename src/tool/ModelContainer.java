package tool;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;

public class ModelContainer {
	public BaseModel[] baseModels;
	public Vec3 baseAxis;
	GL3 gl;
	
	public ModelContainer(GL3 gl) {
		this.gl = gl;
	}
	
	public void initial() {}
	
	public void render(Camera camera,Light light,GL3 gl) {
		for(int i = 0; i < baseModels.length; i++) {
			baseModels[i].render(camera, light, gl);
		}
	}
	
	public void dispose(GL3 gl) {
		for(int i = 0; i < baseModels.length; i++) {
			baseModels[i].dispose(gl);
		}
	}
	
	public void setBaseModels(BaseModel[] baseModels) {
		this.baseModels = baseModels;
	}
	
	public void setBaseAxis(Vec3 baseAxis) {
		this.baseAxis = baseAxis;
	}
	
	public Vec3 relativePosition(float x, float y, float z) {
		return new Vec3(baseAxis.x+x, baseAxis.y+y, baseAxis.z+z);
	}
}
