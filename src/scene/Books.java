package scene;

import com.jogamp.opengl.GL3;

import basisObj.Cube;
import gmaths.Vec3;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;

public class Books extends ModelContainer{
	
	int[] test_texture;
	Book book1, book2, book3, book4;
	
	public Books(GL3 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
		book1 = new Book(gl); 
		book2 = new Book(gl);
		book3 = new Book(gl); 
		book4 = new Book(gl);
		
		book1.setTexture(test_texture);
		book2.setTexture(test_texture);
		book3.setTexture(test_texture);
		book4.setTexture(test_texture);
		
		
		baseAxis = new Vec3( 0f, 5f, -7f);
		
		baseModels.add(book1);
		baseModels.add(book2);
		baseModels.add(book3);
		baseModels.add(book4);
		
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		
		book1.scale(1.2f,0.3f,2f);
		book1.rotate(0,15,0);
		book1.translate(Vec3.add(baseAxis, new Vec3(5f,0.3f,-1f)));
		
		
		book2.scale(1.2f,0.3f,2f);
		book2.rotate(0,-10,0);
		book2.translate(Vec3.add(baseAxis, new Vec3(5.2f,0.6f,-1.1f)));
		
		book3.scale(1.2f,0.3f,2f);
		book3.rotate(0,0,-25);
		book3.translate(Vec3.add(baseAxis, new Vec3(6.2f,0.65f,-1.1f)));
		
		book4.scale(1.2f,0.3f,2f);
		book4.rotate(0,110,0);
		book4.translate(Vec3.add(baseAxis, new Vec3(5.2f,0.6f,1.3f)));
	}
}

class Book extends BaseModel {
	public Book(GL3 gl) {
		super(gl);
		// TODO Auto-generated constructor stub
		super.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	} 
	
}
