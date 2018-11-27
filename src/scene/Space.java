package scene;

import com.jogamp.opengl.GL3;

import basisObj.Cube;
import basisObj.TwoTriangles;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.Model;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class Space extends ModelContainer{
	public int MODELS_NUM = 3;
	Floor floor;
	WindowWall windowWall;
	LeftWall leftWall;
	TopWall topWall;
	GL3 gl;
	int[] floor_texture,wall_texture;
	public Space(GL3 gl) {
		super(gl);
		floor_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"floor.jpg");
		wall_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"wall.jpg");
		floor = new Floor(gl);
		leftWall = new LeftWall(gl);
		windowWall = new WindowWall(gl);
//		topWall = new TopWall(gl);
		
		baseModels.add(floor);
		baseModels.add(leftWall);
		baseModels.add(windowWall);
	}
	
	@Override
	public void initial() {
		super.initial();
		floor.setTexture(floor_texture);
		leftWall.setTexture( wall_texture);
		windowWall.setTexture( wall_texture);
		
		floor.translate(0, 0, 0);
		leftWall.translate(-10f, 10f, 0f);
		windowWall.translate(0f, 10f, -10f);

		floor.rotate(0, 0, 0);
		leftWall.rotate(0, 0, 270);
		windowWall.rotate(90, 0, 0);
		
		floor.scale(20,10,20);
		leftWall.scale(20,10,20);
		windowWall.scale(20,10,20);
	}
}

class Floor extends BaseModel{
	Mesh mesh;
	Shader shader;
	Material material;
	Mat4 transformMat;
	GL3 gl;
	int[] texture;
	
	public Floor(GL3 gl) {
			super(gl);
			super.mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
			super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
			super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		}
}

class LeftWall extends BaseModel{
	public LeftWall(GL3 gl) {
			super(gl);
			super.mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
			super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
			super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		}
}

class WindowWall extends BaseModel{
	public WindowWall(GL3 gl) {
			super(gl);
			super.mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
			super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
			super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}

class TopWall extends BaseModel{
	public TopWall(GL3 gl) {
			super(gl);
			super.mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
			super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
			super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	}
}


