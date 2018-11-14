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
	
	private final String TEXTUREBASEPATH = "model_source/texture/";
	private final String SHADERBASEPATH = "model_source/shader/";
	
	String TestTexture = TEXTUREBASEPATH+"brickwall.jpg";

	// scene model
	private Camera camera;
	
	public MyGLEventListener(Camera camera) {
	    this.camera = camera;
	    this.camera.setPosition(new Vec3(4f,6f,15f));
	    this.camera.setTarget(new Vec3(0f,5f,0f));
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
		// TODO Auto-generated method stub
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
//	    startTime = getSeconds();
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
		light.setShade(new String[] {SHADERBASEPATH + "vs_light.txt", SHADERBASEPATH + "fs_light.txt"});
	    light.setCamera(camera);
	    String[] ps = new String[] {SHADERBASEPATH + "vs_light.txt", SHADERBASEPATH + "fs_light.txt"};
	    	System.out.println(ps[0]);
	    	System.out.println(ps[1]);
	    
	    
	    // build model	    
	    Mesh mesh = new Mesh(gl, Cube.vertices.clone(), Cube.indices.clone());
	    Shader shader = new Shader(gl, SHADERBASEPATH + "vs_sphere.txt", SHADERBASEPATH + "fs_sphere.txt");
	    Material material = new Material(new Vec3(0.0f, 0.5f, 0.81f), new Vec3(0.0f, 0.5f, 0.81f), new Vec3(0.3f, 0.3f, 0.3f), 32.0f);
	    Mat4 modelMatrix = Mat4Transform.scale(2f,2f,2f);
	    
	    modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.translate(0,1f,0));
	    modelMatrix = Mat4.multiply(modelMatrix, Mat4Transform.rotateAroundZ((float) 50));
	    Model cylinder = new Model(gl, camera, light, shader, material, modelMatrix, mesh, testTexture);
	
	    models = new Model[] {cylinder};
	    
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
		    float x = 5.0f*(float)(Math.sin(Math.toRadians(elapsedTime*50)));
		    float y = 2.7f;
		    float z = 5.0f*(float)(Math.cos(Math.toRadians(elapsedTime*50)));
		    
//		    return new Vec3(x,y,z);
		    
		    return new Vec3(5f,3.4f,5f);  // use to set in a specific position for testing
		  }
	  
	  private double startTime;
	private double getSeconds() {
		    return System.currentTimeMillis()/1000.0;
		  }
	  
	private void disposeModels(GL3 gl) {
		
		for(Model model: models) {
			 model.dispose(gl);
		}
	    light.dispose(gl);
	  }
	
}
//MyGLEventListener