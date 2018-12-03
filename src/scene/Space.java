package scene;
/* Author Bingxun Yang xavierybx@gmail.com */
import com.jogamp.opengl.GL3;

import basisObj.Cube;
import basisObj.TwoTriangles;
import gmaths.Mat4;
import gmaths.Vec3;
import tool.BaseModel;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.ModelContainer;
import tool.Shader;
import tool.TextureLibrary;

public class Space extends ModelContainer{
	public int MODELS_NUM = 3;
	Floor floor;
	WindowWall windowWall1,windowWall2,windowWall3,windowWall4;
	LeftWall leftWall;
	WindowView windowView;
	WindowLine l1,l2,l3,l4;
	GL3 gl;
	int[] floor_texture,wall_texture,city_view;
	public Space(GL3 gl) {
		super(gl);
		floor_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"floor.jpg");
		wall_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"wall.jpg");
		city_view = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"city_view.jpg");
		
		floor = new Floor(gl);
		leftWall = new LeftWall(gl);
		windowWall1 = new WindowWall(gl);
		windowWall2 = new WindowWall(gl);
		windowWall3 = new WindowWall(gl);
		windowWall4 = new WindowWall(gl);
		windowView = new WindowView(gl);
		l1 = new WindowLine(gl);
		l2 = new WindowLine(gl);
		l3 = new WindowLine(gl);
		l4 = new WindowLine(gl);
		
		floor.setTexture(floor_texture);
		leftWall.setTexture( wall_texture);
		windowWall1.setTexture( wall_texture);
		windowWall2.setTexture( wall_texture);
		windowWall3.setTexture( wall_texture);
		windowWall4.setTexture( wall_texture);
		l1.setTexture(wall_texture);
		l2.setTexture(wall_texture);
		l3.setTexture(wall_texture);
		l4.setTexture(wall_texture);
		
		
		baseModels.add(floor);
		baseModels.add(leftWall);
		
		baseModels.add(windowWall1);
		baseModels.add(windowWall2);
		baseModels.add(windowWall3);
		baseModels.add(windowWall4);
		
		baseModels.add(windowView);
		
		baseModels.add(l1);
		baseModels.add(l2);
		baseModels.add(l3);
		baseModels.add(l4);
	}
	
	@Override
	public void initial() {
		super.initial();
		windowView.setTexture(city_view);
		
		floor.translate(0, 0, 0);
		leftWall.translate(-10f, 10f, 0f);

		floor.rotate(0, 0, 0);
		leftWall.rotate(0, 0, 270);
		
		floor.scale(20,1,20);
		leftWall.scale(20,1,20);
		
		Vec3 w_basis = new Vec3(0f, 0f, -10f);
		windowWall1.scale(20,1,7f);
		windowWall1.translate(Vec3.add(w_basis, new Vec3(0,3f,0)));
		windowWall2.scale(20,1,7f);
		windowWall2.translate(Vec3.add(w_basis, new Vec3(0,16.5f,0)));
		windowWall3.scale(5f,1,12f);
		windowWall3.translate(Vec3.add(w_basis, new Vec3(-7.5f,12f,0)));
		windowWall4.scale(5f,1,12f);
		windowWall4.translate(Vec3.add(w_basis, new Vec3(7.5f,12f,0)));
		
		windowView.scale(20,1,10);
		windowView.translate(Vec3.add(w_basis, new Vec3(0,10f,-5)));
		
		l1.scale(12, 0.2f, 0.2f);
		l2.scale(12, 0.2f, 0.2f);
		l1.translate(Vec3.add(w_basis, new Vec3(0,6.5f,0)));
		l2.translate(Vec3.add(w_basis, new Vec3(0,13f,0)));
		
		l3.scale(8, 0.2f, 0.2f);
		l4.scale(8, 0.2f, 0.2f);
		l3.rotate(0,0,90);
		l3.translate(Vec3.add(w_basis, new Vec3(-5f,10f,0)));
		l4.rotate(0,0,90);
		l4.translate(Vec3.add(w_basis, new Vec3(5f,10f,0)));
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
			rotate(90, 0, 0);
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

class WindowView extends BaseModel {

	public WindowView(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		rotate(90, 0, 0);
	}
}

class WindowLine extends BaseModel {
	public WindowLine(GL3 gl) {
		super(gl);
		super.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		translate(0,0,0.1f);
	}
}

