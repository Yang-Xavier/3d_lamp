package tool;

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

public class BaseModel {
	public Mesh mesh;
	public Shader shader;
	public Material material;
	public Mat4 transformMat;
	public GL3 gl;
	public Model model;
	public int[] texture;

	public BaseModel(GL3 gl) {}
	
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
	
	public Model getModel(Camera camera,Light light) {
		model = new Model(gl, camera, light, shader, material, transformMat, mesh, texture);
		return model;
	}
	
	public Model render(Camera camera,Light light, GL3 gl) {
		model = new Model(gl, camera, light, shader, material, transformMat, mesh, texture);
		model.render(gl);
		return model;
	}
	
	public void dispose(GL3 gl) {
		this.model.dispose(gl);
	}
}
