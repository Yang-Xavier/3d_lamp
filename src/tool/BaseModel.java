package tool;
/* Author Bingxun Yang xavierybx@gmail.com */
import com.jogamp.opengl.GL3;

import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.Material;
import tool.Mesh;
import tool.Model;
import tool.Shader;
import tool.TextureLibrary;

public class BaseModel {
	public Mesh mesh;
	public Shader shader;
	public Material material;
	public GL3 gl;
	public Model model;
	public int[] texture;
	
	public Mat4 position =  new Mat4(1);
	public Mat4 scale =  new Mat4(1);
	public Mat4 rotate =  new Mat4(1);

	
	public BaseModel(GL3 gl) {}
	
	public void setTexture( int[] texture) {
		this.texture = texture;
	}
	
	public void setTexture(String fname) {
		this.texture = TextureLibrary.loadTexture(gl, fname);
	}
	
	public void translate(float x, float y, float z) {
		this.position = Mat4.multiply(this.position, Mat4Transform.translate(x, y, z));
	}
	
	public void translate(Vec3 T) {
		this.position = Mat4.multiply(this.position, Mat4Transform.translate(T.x, T.y, T.z));
	}
	
	public void scale(float x, float y, float z) {
		this.scale = Mat4.multiply(this.scale, Mat4Transform.scale(x, y, z));
	}
	
	public void rotate(float rx, float ry, float rz) {
		Mat4 rotateMat =  Mat4Transform.rotateAroundX(rx);
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundY(ry));
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundZ(rz));
		this.rotate = Mat4.multiply(this.rotate, rotateMat);
	}
	
	public void scale(float s) {
		this.scale(s, s, s);
	}
	

	
	public void rotate(int rx, int ry, int rz) {
		this.rotate((float)rx, (float)ry, (float)rz);
	}
	
	public Model getModel(Camera camera,Light light) {
		model = new Model(gl, camera, light, shader, material, calculate(), mesh, texture);
		return model;
	}
	
	public Mat4 calculate() {
		Mat4 transformMat =  new Mat4(1);
		transformMat = Mat4.multiply(transformMat, position);
		transformMat = Mat4.multiply(transformMat, rotate);
		transformMat = Mat4.multiply(transformMat, scale);
		return transformMat;
	}
	
	public Model render(Camera camera,Light light, GL3 gl) {
		model = new Model(gl, camera, light, shader, material, calculate(), mesh, texture);
		model.render(gl);
		return model;
	}
	
	public void dispose(GL3 gl) {
		this.model.dispose(gl);
	}
}
