package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.Cylinder;
import basisObj.Sphere;
import basisObj.TwoTriangles;
import gmaths.Mat4;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class Foundation extends ModelContainer{
	int[] test_texture;
	FoundationBottom foundationBottom;
	FoundationBottomCone foundationBottomCone;
	Joint joint;
	public Foundation(GL3 gl) {
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		foundationBottom = new FoundationBottom(gl);
		joint = new Joint(gl);
		foundationBottomCone = new FoundationBottomCone(gl);
		
		baseModels = new BaseModel[3];
		baseModels[0] = foundationBottom;
		baseModels[1] = joint;
		baseModels[2] = foundationBottomCone;
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
		foundationBottom.setTexture(test_texture);
		foundationBottom.translate(relativePosition(0,0.3f,0));
		foundationBottom.scale(1.2f, 0.15f, 1.2f);
		
		foundationBottomCone.setTexture(test_texture);
		foundationBottomCone.translate(relativePosition(0,0.45f,0));
		foundationBottomCone.scale(0.6f, 0.4f, 0.6f);
		
		joint.setTexture(test_texture);
		joint.translate(relativePosition(0,0.7f,0));
	}
}

class FoundationBottom extends BaseModel{
	public FoundationBottom(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class Joint extends BaseModel{
	public Joint(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		scale(0.3f, 0.3f, 0.3f);
	}
}

class FoundationBottomCone extends BaseModel{
	public FoundationBottomCone(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cone.vertices.clone(), Cone.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

