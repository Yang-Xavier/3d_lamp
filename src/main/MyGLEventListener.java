package main;

import gmaths.*;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.util.awt.*;

public class MyGLEventListener implements GLEventListener {

	@Override
	public void display(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
	    GL3 gl = drawable.getGL().getGL3();
	    System.err.println("Chosen GLCapabilities: " + drawable.getChosenGLCapabilities());
	    gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
	    gl.glClearDepth(1.0f);
	    gl.glEnable(GL.GL_DEPTH_TEST);	// render the foremost object
	    gl.glDepthFunc(GL.GL_LESS);	
	    gl.glFrontFace(GL.GL_CCW);    // default is 'CCW'
	    gl.glEnable(GL.GL_CULL_FACE); // default is 'not enabled'
	    gl.glCullFace(GL.GL_BACK);   // default is 'back', assuming CCW
//	    initialise(gl);
//	    startTime = getSeconds();
	}

	@Override
	public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		
	}
	
}
//MyGLEventListener