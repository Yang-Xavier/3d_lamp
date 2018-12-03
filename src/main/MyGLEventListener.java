package main;

import gmaths.*;
import lamp.Lamp;
import scene.*;
import tool.*;

import com.jogamp.opengl.*;


public class MyGLEventListener implements GLEventListener {
		
	Light light;
// model
	Space space;
	Desk desk;
	Books books;
	Lamp lamp;
	PenContanier penContanier;
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
//	    gl.glFrontFace(GL.GL_CCW);    // default is 'CCW'
//	    gl.glEnable(GL.GL_MULTISAMPLE);  // anti-Aliasing
//	    gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
//	    gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
	    initialise(gl);
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
		
		light = new Light(gl);
		light.setShade(new String[] {Constant.DEFAULT_LIGHT_VS, Constant.DEFAULT_LIGHT_FS});
	    light.setCamera(camera);
	    
	    // build model	    
	    
	    space  = new Space(gl);
	    desk = new Desk(gl);
	    books = new Books(gl);
	    penContanier = new PenContanier(gl);
	    lamp = new Lamp(gl);
	    
	    space.initial();
	    desk.initial();
	    books.initial();
	    penContanier.initial();
	    
//	    light.createFlashLight();
	    
	}
	
	
	// it could be rewrite in render a model array
	public void render(GL3 gl) {
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
	    
//	    light.setPosition(getLightPosition());  // changing light position each frame
	    light.render(gl);
	    
	    space.render(camera, light, gl);
	    desk.render(camera, light, gl);
	    books.render(camera, light, gl);
	    penContanier.render(camera, light, gl);
	    
	    
	    lamp.render(camera, light);
	    updateLamp();
	}
	
	public void updateLamp() {
		lamp.update(camera, light);
		light.updateFlashLight(lamp.getBulbPosition(), lamp.getForward());
	}
	
	  private void disposeModels(GL3 gl) {
	    light.dispose(gl);
	    space.dispose(gl);
	    desk.dispose(gl);
	    lamp.dispose(gl);
	  }
	  
	  	
	  public void turnSpotLight(boolean status) {
		if (status) {
			light.openLight();
		} else {
			light.closeLight();
		}
	}
	  
	  public void turnFlashLight(boolean status) {
		  if (status) {
				light.createFlashLight();
				lamp.turnOn();
			} else {
				light.disposeFlashLight();
				lamp.turnOff();
			}
	  }
	  
		public void randomJump() {
			if(!lamp.isJumping()) {	lamp.randomJump();}
		}
		
		public void randomPosing() {
			
		}
	  
}
//MyGLEventListener