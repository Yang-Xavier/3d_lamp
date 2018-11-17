package tool;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;

public class ModelContainer {
	public BaseModel[] baseModels;
	GL3 gl;
	Vec3 basePoint;
	
	public ModelContainer(GL3 gl) {
		this.gl = gl;
	}
	
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
}
