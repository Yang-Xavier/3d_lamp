package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.Cylinder;
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
	FoundationBottom foundationBottom;
	public Foundation(GL3 gl) {
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		foundationBottom = new FoundationBottom(gl);
		
		foundationBottom.setTexture(test_texture);
		foundationBottom.scale(2f, 2f, 2f);
		
		baseModels = new BaseModel[1];
		baseModels[0] = foundationBottom;
	}
}

class FoundationBottom extends BaseModel{
	public FoundationBottom(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		super.transformMat = new Mat4(1);
	}
	
}
