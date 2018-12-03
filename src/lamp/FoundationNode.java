package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.Cylinder;
import basisObj.Sphere;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.BaseNode;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.NodeContainer;
import tool.Shader;
import tool.TextureLibrary;

public class FoundationNode extends NodeContainer {
	FoundationBottom foundationBottom;
	FoundationBottomCone foundationBottomCone;
	JointNode joint;
	
	int[] test_texture;
	public FoundationNode(GL3 gl) {
		super(gl,"Foundation");
		// TODO Auto-generated constructor stub
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		foundationBottom = new FoundationBottom(gl,"f-bottom");
		joint = new JointNode(gl, "f-joint");
		foundationBottomCone = new FoundationBottomCone(gl, "f-cone");
		foundationBottom.setTexture(test_texture);
		foundationBottomCone.setTexture(test_texture);
		joint.setTexture(test_texture);
		
		this.addChild(foundationBottom);
		foundationBottom.addChild(foundationBottomCone);
		foundationBottomCone.addChild(joint);
	}
	
	@Override
	public void update(Mat4 worldTransform, Camera camera, Light light) {
		// TODO Auto-generated method stub
		foundationBottomCone.translate(new Vec3(0f, 0.15f, 0f));
		joint.translate(new Vec3(0f, 0.30f, 0f));
		super.update(worldTransform, camera, light);
	}
	
}

class FoundationBottom extends BaseNode {
	public FoundationBottom(GL3 gl,String name) {
		super(gl,name);
		// TODO Auto-generated constructor stub
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		originS = Mat4Transform.scale(new Vec3(1.2f, 0.15f, 1.2f));
	}
}

class FoundationBottomCone extends BaseNode{
	public FoundationBottomCone(GL3 gl,String name) {
		super(gl,name);
		super.mesh = new Mesh(gl, Cone.vertices.clone(), Cone.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		originS = Mat4Transform.scale(new Vec3(0.6f, 0.4f, 0.6f));
	}
}