package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.Cylinder;
import basisObj.Hemisphere;
import basisObj.Sphere;
import gmaths.Vec3;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class Head extends ModelContainer{
	int[] test_texture;
	Joint joint ;

	Lampshade_A lampshade_a;
	Lampshade_B lampshade_b;
	Lampshade_C lampshade_c;
	
	Bulb bulb;
	
	public Head(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		joint = new Joint(gl);
		lampshade_a = new Lampshade_A(gl);
		lampshade_b = new Lampshade_B(gl);
		lampshade_c = new Lampshade_C(gl);
		bulb = new Bulb(gl);
		
		baseModels.add(joint);
		baseModels.add(lampshade_a);
		baseModels.add(lampshade_b);
		baseModels.add(lampshade_c);
		baseModels.add(bulb);
		
		
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
		
		
		joint.translate(relativePosition(0, 2.95f, 0));
		
		
		lampshade_a.translate(relativePosition(0.2f, 2.8f, 0));
		lampshade_a.scale(0.3f, 0.3f, 0.3f);
		lampshade_a.rotate(0,0,45);
		
		lampshade_b.translate(relativePosition(0.33f, 2.68f, 0));
		lampshade_b.scale(0.6f, 0.2f, 0.6f);
		lampshade_b.rotate(0,0,45);
		
		lampshade_c.translate(relativePosition(0.65f, 2.35f, 0));
		lampshade_c.rotate(0,0,45);
		
		bulb.translate(relativePosition(0.65f, 2.4f, 0));
		bulb.scale(0.4f,0.6f,0.4f);
		bulb.rotate(0,0,45);

	}
	
}


class Lampshade_A extends BaseModel{
	public Lampshade_A(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		super.mesh = new Mesh(gl, Cone.vertices.clone(), Cone.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class Lampshade_B extends BaseModel{
	public Lampshade_B(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class  Lampshade_C extends BaseModel{
	public Lampshade_C(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		super.mesh = new Mesh(gl, Hemisphere.vertices.clone(), Hemisphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class  Bulb extends BaseModel{
	public Bulb(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		super.mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}



