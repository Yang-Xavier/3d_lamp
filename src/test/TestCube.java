package test;

import com.jogamp.opengl.GL3;

import basisObj.Cube;
import gmaths.Mat4;
import gmaths.Mat4Transform;
import scene.Camera;
import scene.Light;
import tool.Constant;
import tool.Material;
import tool.Mesh;
import tool.Model;
import tool.Shader;
import tool.TextureLibrary;

public class TestCube {
	Mesh mesh;
	Shader shader;
	Material material;
	Mat4 transformMat;
	GL3 gl;
	int[] texture;
//	Camera camera;
//	Light light;
	
//	public TestCube(	GL3 gl,Mesh mesh, Shader shader, Material material, Mat4 transformMat) {
//		this.mesh = mesh;
//		this.shader = shader;
//		this.material = material;
//		this.transformMat = transformMat;
//		
//	}
	
//	public TestCube(	GL3 gl, Mesh mesh, Shader shader, Material material) {
//		this.mesh = mesh;
//		this.shader = shader;
//		this.material = material;
//		Mat4 modelMatrix = Mat4Transform.scale(1f,1f,1f);
//	    modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.translate(0,0f,0));
//	    this.transformMat = modelMatrix;
//	}
//	
//	public TestCube(GL3 gl, Mat4 modelMatrix) {
//		this.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
//		this.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
//		this.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
//	    this.transformMat = modelMatrix;
//	}
	
	public TestCube(GL3 gl) {
		this.mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
		this.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
		this.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
		Mat4 modelMatrix = Mat4Transform.scale(1f,1f,1f);
	    modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.translate(0,0f,0));
	    this.transformMat = modelMatrix;
	}
	
	public void setTexture( int[] texture) {
		this.texture = texture;
	}
	
	public void setTexture(String fname) {
		this.texture = TextureLibrary.loadTexture(gl, fname);
	}
	public void translate(float x, float y, float z) {
		this.transformMat = Mat4.multiply(this.transformMat, Mat4Transform.translate(x, y, z));
	}
	
	public void scale(float x, float y, float z) {
		this.transformMat = Mat4.multiply(this.transformMat, Mat4Transform.scale(x, y, z));
	}
	
	public void scale(float s) {
		this.transformMat = Mat4.multiply(this.transformMat, Mat4Transform.scale(s,s,s));
	}
	
	public void rotate(float rx, float ry, float rz, boolean around_origin) {
		Mat4 rotateMat =  Mat4Transform.rotateAroundX(rx);
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundY(ry));
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundZ(rz));
	}
	
	public void rotate(float rx, float ry, float rz) {
		Mat4 rotateMat =  Mat4Transform.rotateAroundX(rx);
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundY(ry));
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundZ(rz));
		this.transformMat = Mat4.multiply(this.transformMat, rotateMat);
	}
	
	public void rotate(int rx, int ry, int rz) {
		this.rotate((float)rx, (float)ry, (float)rz);
	}
	
	public void rotateAroundPoint(float ve) {
		
	}
	
	public Model getModel(Camera camera,Light light) {
		return new Model(gl, camera, light, shader, material, transformMat, mesh, texture);
	}
	
}
