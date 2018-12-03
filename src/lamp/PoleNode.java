package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cylinder;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import tool.BaseNode;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.Shader;

public class PoleNode extends BaseNode {
	float length = 1f;

	public PoleNode(GL3 gl,String name) {
		super(gl,name);
		// TODO Auto-generated constructor stub
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		originS = Mat4Transform.scale(new Vec3(0.15f,length,0.15f));
	}
}
