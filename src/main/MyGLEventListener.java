package main;

import gmaths.*;
import scene.*;
import tool.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;

import basisObj.*;

public class MyGLEventListener implements GLEventListener {
		
	String TestTexture = Constant.TEXTURE_BASEPATH+"brickwall.jpg";

	// scene model
	private Camera camera;
	
	public MyGLEventListener(Camera camera) {
	    this.camera = camera;
	    this.camera.setPosition(Constant.DEFAULT_CAMERA_POSITION);
	    this.camera.setTarget(Constant.DEFAULT_CAMERA_TRAGET);
	  }
	
	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL3 gl = drawable.getGL().getGL3();
	    render(gl);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL3 gl = drawable.getGL().getGL3();
	    disposeModels(gl);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
//		createRandomNumbers();
	    GL3 gl = drawable.getGL().getGL3();
	    System.out.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
	    gl.glClearDepth(1.0f);
	    gl.glEnable(GL.GL_DEPTH_TEST);	// render the foremost object
	    gl.glDepthFunc(GL.GL_LESS);	
	    gl.glFrontFace(GL.GL_CCW);    // default is 'CCW'
	    gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
	    gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
	    initialise(gl);
	    startTime = getSeconds();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
	    GL3 gl = drawable.getGL().getGL3();
	    gl.glViewport(x, y, width, height);
	    float aspect = (float)width/(float)height;
	    camera.setPerspectiveMatrix(Mat4Transform.perspective(45, aspect));
	}
	
	Light light;
	Model[] models;
	public void initialise(GL3 gl) {
		int[] testTexture = TextureLibrary.loadTexture(gl, TestTexture);
		
		light = new Light(gl);
		light.setShade(new String[] {Constant.DEFAULT_LIGHT_VS, Constant.DEFAULT_LIGHT_FS});
	    light.setCamera(camera);
	    // build model	    
	    Mesh cm = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
	    Shader shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
	    Material cmaterial = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	    Mat4 cmodelMatrix = Mat4Transform.scale(2f,2f,2f);
	    cmodelMatrix = Mat4.multiply(cmodelMatrix, Mat4Transform.translate(0,1f,0));
	    cmodelMatrix = Mat4.multiply(cmodelMatrix, Mat4Transform.rotateAroundZ((float) 50));
	    Model cylinder = new Model(gl, camera, light, shader, cmaterial, cmodelMatrix, cm, testTexture);
	    
	    Mesh tm = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
	    Material tMaterial = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	    Mat4 tmMat4 = Mat4Transform.scale(10f,1f,10f);
	    Model floor = new Model(gl, camera, light, shader, tMaterial, tmMat4, tm);
	    
	    Mesh wm = new Mesh(gl, TwoTriangles.vertices.clone(), TwoTriangles.indices.clone());
	    Material wMaterial = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
	    Mat4 wallMat4 = Mat4Transform.rotateAroundX(90);
	    wallMat4 = Mat4.multiply(wallMat4, Mat4Transform.translate(0,-5f,-5f));
	    wallMat4 = Mat4.multiply(wallMat4, Mat4Transform.scale(10f,1,10f));
	    Model wall = new Model(gl, camera, light, shader, wMaterial, wallMat4, wm);
	    
	    models = new Model[] {
	    		cylinder, 
	    		floor,
	    		wall
	    		};
	    
	}
	
	// it could be rewrite in render a model array
	public void render(GL3 gl) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	    
	    light.setPosition(getLightPosition());  // changing light position each frame
	    light.render(gl);
	    
	    for(Model model: models) {
			 model.render(gl);
		}
	}
	  private Vec3 getLightPosition() {
		    double elapsedTime = getSeconds()-startTime;
		    // return
		    float x = 5.0f*(float)(Math.sin(Math.toRadians(elapsedTime*50))*Math.sin(Math.toRadians(elapsedTime*50)));
		    float y = 2.7f;
		    float z = 5.0f*(float)(Math.cos(Math.toRadians(elapsedTime*50)));
		    
		    return new Vec3(x,y,z);
		    
//		    return new Vec3(5f,3.4f,5f);  // use to set in a specific position for testing
		  }
	  

	  
	  private void disposeModels(GL3 gl) {
		for(Model model: models) {
			 model.dispose(gl);
		}
	    light.dispose(gl);
	  }
	  
	  private double startTime;
	  private double getSeconds() {
		    return System.currentTimeMillis()/1000.0;
		  }
	  
//	  private int NUM_RANDOMS = 1000;
//	  private float[] randoms;
//	  private void createRandomNumbers() {
//		    randoms = new float[NUM_RANDOMS];
//		    for (int i=0; i<NUM_RANDOMS; ++i) {
//		      randoms[i] = (float)Math.random();
//		    }
//		  }
	  
}
//MyGLEventListener