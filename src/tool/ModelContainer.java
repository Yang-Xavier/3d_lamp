package tool;


import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL3;

import gmaths.Vec3;
import scene.Camera;
import scene.Light;

public class ModelContainer {
	public List<BaseModel> baseModels;
	public Vec3 baseAxis;
	GL3 gl;
	
	public ModelContainer(GL3 gl) {
		this.gl = gl;
		baseModels = new ArrayList<BaseModel>();
	}
	
	public void initial() {}
	
	public void render(Camera camera,Light light,GL3 gl) {
		for(BaseModel baseModel: baseModels) {
			baseModel.render(camera, light, gl);
		}
	}
	
	public void dispose(GL3 gl) {
		for(BaseModel baseModel: baseModels) {
			baseModel.dispose(gl);
		}
	}
	
	public void setBaseModels(List<BaseModel> baseModels) {
		this.baseModels = baseModels;
	}
	
	public void setBaseAxis(Vec3 baseAxis) {
		this.baseAxis = baseAxis;
	}
	
	public void translate(Vec3 translate) {
		for(BaseModel baseModel: baseModels) {
			baseModel.translate(translate);
		}
	}
	
	public Vec3 relativePosition(float x, float y, float z) {
		return new Vec3(baseAxis.x+x, baseAxis.y+y, baseAxis.z+z);
	}
}
