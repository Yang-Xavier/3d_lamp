package tool;
/* Author Bingxun Yang xavierybx@gmail.com */
import com.jogamp.opengl.GL3;

import gmaths.Mat4;
import scene.Camera;
import scene.Light;

public class NodeContainer extends BaseNode{

	
	public NodeContainer(GL3 gl, String name) {
		super(gl, name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Model render(Camera camera, Light light) {
		// TODO Auto-generated method stub
		if (!childrenNodes.isEmpty()) {
			for(BaseNode child : childrenNodes) {
				child.render(camera,light);
			}
		}
		return null;
	}
	
	@Override
	public void update(Mat4 worldTransform, Camera camera, Light light) {
		// TODO Auto-generated method stub
		if (!childrenNodes.isEmpty()) {
			for(BaseNode child : childrenNodes) {
				child.update(worldTransform,camera,light);
			}
		}
	}

}
