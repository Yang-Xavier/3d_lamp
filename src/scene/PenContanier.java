package scene;
/* Author Bingxun Yang xavierybx@gmail.com */
import com.jogamp.opengl.GL3;

import basisObj.Cylinder;
import basisObj.CylinderContainer;
import gmaths.Vec3;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class PenContanier extends ModelContainer{
	
	int[] texture,texture2;
	Container penContainer;
	Pen p1,p2,p3;
	
	public PenContanier(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"iron.jpeg");
		texture2 = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"pen_container.jpg");
		penContainer = new Container(gl);
		p1 = new Pen(gl);
		p2 = new Pen(gl);
		p3 = new Pen(gl);
		
		baseAxis = new Vec3( -1f, 5f, -7f);
		baseAxis = Vec3.add(baseAxis, new Vec3(-5f,0f,0));
		
		p1.setTexture(texture);
		p2.setTexture(texture);
		p3.setTexture(texture);
		penContainer.setTexture(texture2);
		baseModels.add(penContainer);
		baseModels.add(p1);
		baseModels.add(p2);
		baseModels.add(p3);
	}
	
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
		penContainer.scale(0.6f,0.7f,0.6f);
		penContainer.translate(Vec3.add(baseAxis, new Vec3(0f,0.6f,0)));
		
		p1.rotate(0,-30,-40);
		p1.translate(Vec3.add(baseAxis, new Vec3(0.2f,1f,0)));
		
		p2.rotate(0,0,40);
		p2.translate(Vec3.add(baseAxis, new Vec3(-0.2f,1f,0)));
		
		p3.rotate(90,0,-40);
		p3.translate(Vec3.add(baseAxis, new Vec3(0,0.3f,2f)));
	}
}

class Container extends BaseModel {
	public Container(GL3 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
		super.mesh = new Mesh(gl, CylinderContainer.vertices.clone(), CylinderContainer.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class Pen extends BaseModel {
	public Pen(GL3 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		scale(0.1f,1,0.1f);
	}
}