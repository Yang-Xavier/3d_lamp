package lamp;

import com.jogamp.opengl.GL3;

import basisObj.Cone;
import basisObj.Cylinder;
import basisObj.Hemisphere;
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

public class HeaderNode extends NodeContainer{
	JointNode joint ;

	Lampshade_A lampshade_a;
	Lampshade_B lampshade_b;
	Lampshade_C lampshade_c;
	
	Bulb bulb;
	

	float shade_degree = 0;
	float pole_drgree = 0;
	float x_degree=0;
	
	public HeaderNode(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl, "header");
		
		texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		
		joint = new JointNode(gl,"h-joint");
		lampshade_a = new Lampshade_A(gl);
		lampshade_b = new Lampshade_B(gl);
		lampshade_c = new Lampshade_C(gl);
		bulb = new Bulb(gl);
		
		joint.setTexture(texture);
		lampshade_a.setTexture(texture);
		lampshade_b.setTexture(texture);
		lampshade_c.setTexture(texture);
		bulb.setTexture(texture);

		
		lampshade_a.originS = Mat4Transform.scale(new Vec3(0.3f, 0.3f, 0.3f));
		lampshade_b.originS = Mat4Transform.scale(new Vec3(0.6f, 0.2f, 0.6f));
		bulb.originS = Mat4Transform.scale(new Vec3(0.4f,0.5f,0.4f));
		
		
		this.addChild(joint);
		joint.addChild(lampshade_a);
		lampshade_a.addChild(lampshade_b);
		lampshade_b.addChild(lampshade_c);
		lampshade_c.addChild(bulb);
	}
	
	void updateHead() {
		lampshade_a.translate(new Vec3(0,-0.2f,0));
		lampshade_b.translate(new Vec3(0f,-0.15f,0));
		lampshade_c.translate(new Vec3(0f,-0.45f,0));
		bulb.translate(new Vec3(0f,0f,0));
	}
	
	@Override
	public void update(Mat4 worldTransform, Camera camera, Light light) {
		// TODO Auto-generated method stub
		Mat4 newWorldTransform = Mat4.multiply(worldTransform,Mat4Transform.translate(new Vec3(0,0.5f,0)));
		newWorldTransform = Mat4.multiply(newWorldTransform, Mat4Transform.rotateAroundZ(pole_drgree+shade_degree));
		newWorldTransform = Mat4.multiply(newWorldTransform, Mat4Transform.rotateAroundX(x_degree));
		updateHead();

		super.update(newWorldTransform, camera, light);
//		System.out.println(bulb.getWorldPosition()+" " + lampshade_b.getWorldPosition());
//		System.out.println(Vec3.minus(bulb.getWorldPosition(), lampshade_b.getWorldPosition()));
		
	}	
}


class Lampshade_A extends BaseNode{
	public Lampshade_A(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl,"Lampshade_A");
		super.mesh = new Mesh(gl, Cone.vertices.clone(), Cone.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class Lampshade_B extends BaseNode{
	public Lampshade_B(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl,"Lampshade_B");
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class  Lampshade_C extends BaseNode{
	public Lampshade_C(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl,"Lampshade_C");
		super.mesh = new Mesh(gl, Hemisphere.vertices.clone(), Hemisphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class  Bulb extends BaseNode{
	public Bulb(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl,"Bulb");
		super.mesh = new Mesh(gl, Sphere.vertices.clone(), Sphere.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}



