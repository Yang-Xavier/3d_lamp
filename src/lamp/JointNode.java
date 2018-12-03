package lamp;
/* Author Bingxun Yang xavierybx@gmail.com */
import com.jogamp.opengl.GL3;

import basisObj.Sphere;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import tool.BaseNode;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.Shader;

public class JointNode extends BaseNode{
	public JointNode(GL3 gl,String name) {
		super(gl,name);
		super.mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		originS = Mat4Transform.scale(new Vec3(0.3f, 0.3f, 0.3f));
	}
}
