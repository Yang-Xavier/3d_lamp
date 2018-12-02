package tool;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.GL3;

import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;

public class BaseNode {
	
	public Mesh mesh;
	public Shader shader;
	public Material material;
	public GL3 gl;
	public Model model;
	public Camera camera;
	public Light light;
	public int[] texture;
	public String name;
	
	List<BaseNode> childrenNodes;
	public Mat4 transform = new Mat4(1);
	public Mat4 worldT = new Mat4(1);
	public Mat4 position =  new Mat4(1);
	public Mat4 scale =  new Mat4(1);
	public Mat4 rotate =  new Mat4(1);
	public Mat4 originP = new Mat4(1);
	public Mat4 originR = new Mat4(1);
	public Mat4 originS = new Mat4(1);
	
	public Vec3 wordPosition;
	
	public BaseNode(GL3 gl) {
		this.gl = gl;
		childrenNodes = new ArrayList<BaseNode>();
	}
	public BaseNode(GL3 gl, String name) {
		this.gl = gl;
		this.name = name;
		childrenNodes = new ArrayList<BaseNode>();
	}
	
	public void init(Camera camera,Light light) {
		this.camera = camera;
		this.light = light;
	}
	
	public void initTransform() {
		this.transform = calculateTransform(this.originP,this.originR,this.originS);
		this.position =  this.originP;
		this.scale =  this.originS;
		this.rotate =  this.originR;
	}
	
	public void setTexture( int[] texture) {
		this.texture = texture;
	}
	
	public void setTexture(String fname) {
		this.texture = TextureLibrary.loadTexture(gl, fname);
	}
	
	public void addChild(BaseNode node) {
		childrenNodes.add(node);
	}
	
	public void addToLast(BaseNode node) {
		if (!childrenNodes.isEmpty()) {
			childrenNodes.get(childrenNodes.size()-1).addToLast(node);
		} else {
			addChild(node);
		}
	}
	
	public void update(Mat4 worldTransform,Camera camera,Light light) {
		worldT = Mat4.multiply(worldTransform, transform);
		updateWorldPosition(worldT);
		if (!childrenNodes.isEmpty()) {
			for(BaseNode child : childrenNodes) {
				child.update(worldT,camera,light);
			}
		}
	}
	
	
	public Model render(Camera camera,Light light) {
		model = new Model(gl, camera, light, shader, material, calculateScale(worldT), mesh, texture);
		model.render(gl);
		if (!childrenNodes.isEmpty()) {
			for(BaseNode child : childrenNodes) {
				child.render(camera,light);
			}
		}
		initTransform();
		return model;
	}
	
	
	public void dispose(GL3 gl) {
		if (!childrenNodes.isEmpty()) {
			for(BaseNode child : childrenNodes) {
				child.dispose(gl);
			}
		}
		this.model.dispose(gl);
	}
	
	public void translate(Vec3 T) {
		this.position = Mat4.multiply(this.position, Mat4Transform.translate(T.x, T.y, T.z));
		transform = Mat4.multiply(transform, this.position);
	}

	
	public void rotate(Vec3 R) {
		Mat4 rotateMat =  Mat4Transform.rotateAroundX(R.x);
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundY(R.y));
		rotateMat = Mat4.multiply(rotateMat, Mat4Transform.rotateAroundZ(R.z));
		this.rotate = Mat4.multiply(this.rotate, rotateMat);
		transform = Mat4.multiply(transform, this.rotate);
	}
	
	
	public void scale(Vec3 S) {
		this.scale = Mat4.multiply(this.scale, Mat4Transform.scale(S.x, S.y,S.z));
	}
	
	public Mat4 calculateScale(Mat4 transform) {
		Mat4 transformMat = Mat4.multiply(transform, scale);
		return transformMat;
	}
	
	public Mat4 calculateTransform() {
		Mat4 transformMat =  new Mat4(1);
		transformMat = Mat4.multiply(transformMat, position);
		transformMat = Mat4.multiply(transformMat, rotate);
		return transformMat;
	}
	
	public Mat4 calculateTransform(Mat4 position, Mat4 rotate, Mat4 scale) {
		Mat4 transformMat =  new Mat4(1);
		transformMat = Mat4.multiply(transformMat, position);
		transformMat = Mat4.multiply(transformMat, rotate);
		return transformMat;
	}
	
	public void updateWorldPosition(Mat4 worldT) {
		float[] fs= worldT.toFloatArrayForGLSL();
		wordPosition = new Vec3(fs[13],fs[14],fs[15]);
	} 
	
	public Vec3 getWorldPosition() {
		
		return this.wordPosition;
	}
	
}
