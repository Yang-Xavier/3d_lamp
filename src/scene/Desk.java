package scene;

import com.jogamp.opengl.GL3;

import basisObj.Cube;
import gmaths.Mat4;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class Desk extends ModelContainer{
	int[] test_texture;
	BaseModel l1,l2,l3,l4,deskPlatform;
	
	public Desk(GL3 gl) {
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		l1 = new Leg(gl);
		l2 = new Leg(gl);
		l3 = new Leg(gl);
		l4 = new Leg(gl);
		deskPlatform = new DeskPlatform(gl);
		
		baseModels.add(l1);
		baseModels.add(l2);
		baseModels.add(l3);
		baseModels.add(l4);
		baseModels.add(deskPlatform);
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
		l1.setTexture(test_texture);
		l2.setTexture(test_texture);
		l3.setTexture(test_texture);
		l4.setTexture(test_texture);
		
		l1.translate(-5f, 2.5f, -5f);
		l2.translate(5f, 2.5f, -5f);
		l3.translate(-5f, 2.5f, -9f);
		l4.translate(5f, 2.5f, -9f);
		deskPlatform.translate(0f, 5f, -7f);
		
		l1.scale(0.5f, 5f,0.5f);
		l2.scale(0.5f, 5f,0.5f);
		l3.scale(0.5f, 5f,0.5f);
		l4.scale(0.5f, 5f,0.5f);
		deskPlatform.scale(15f,0.5f,6f);
	}
}

class Leg extends BaseModel {
	public Leg(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class DeskPlatform extends BaseModel {
	public DeskPlatform(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}