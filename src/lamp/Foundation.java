package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.TwoTriangles;
import gmaths.Mat4;
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
	Foundation_B fB;
	public Foundation(GL3 gl) {
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		fB = new Foundation_B(gl);
		
		fB.setTexture(test_texture);
		fB.scale(2f, 2f, 2f);
		
		baseModels = new BaseModel[0];
		baseModels[0] = fB;
	}
}

class Foundation_B extends BaseModel{
	public Foundation_B(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cone.vertices.clone(), Cone.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		super.transformMat = new Mat4(1);
	}
	
}
