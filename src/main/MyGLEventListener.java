package main;

import gmaths.*;
import lamp.Foundation;
import lamp.Lamp;
import scene.*;
import test.TestCube;
import tool.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;

import basisObj.*;

public class MyGLEventListener implements GLEventListener {
		
	String TestTexture = Constant.TEXTURE_BASEPATH+"brickwall.jpg";
	Light light;
// model
	Space space;
	Desk desk;
	Lamp lamp;
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
//	    gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
//	    gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
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
	

	public void initialise(GL3 gl) {
		int[] testTexture = TextureLibrary.loadTexture(gl, TestTexture);
		
		light = new Light(gl);
		light.setShade(new String[] {Constant.DEFAULT_LIGHT_VS, Constant.DEFAULT_LIGHT_FS});
	    light.setCamera(camera);
	    // build model	    
	    
	    space  = new Space(gl);
	    desk = new Desk(gl);
	    lamp = new Lamp(gl);
	    
	    space.initial();
	    desk.initial();
	    lamp.initial();
	}
	
	// it could be rewrite in render a model array
	public void render(GL3 gl) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	    
	    light.setPosition(getLightPosition());  // changing light position each frame
	    light.render(gl);
	    
	    space.render(camera, light, gl);
	    desk.render(camera, light, gl);
	    lamp.render(camera, light, gl);
	    
	    
	}
	  private Vec3 getLightPosition() {
		    double elapsedTime = getSeconds()-startTime;
		    float x = 5.0f*(float)(Math.sin(Math.toRadians(elapsedTime*50))*Math.sin(Math.toRadians(elapsedTime*50)));
		    float y = 2.7f;
		    float z = 5.0f*(float)(Math.cos(Math.toRadians(elapsedTime*50)));
		    
//		    return new Vec3(x,y,z);
		    
		    return new Vec3(5f,3.4f,5f);  // use to set in a specific position for testing
		  }
	  

	  
	  private void disposeModels(GL3 gl) {
	    light.dispose(gl);
	    space.dispose(gl);
	    desk.dispose(gl);
	    lamp.dispose(gl);
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