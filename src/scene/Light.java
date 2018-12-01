package scene;

import java.nio.*;
import com.jogamp.common.nio.*;
import com.jogamp.opengl.*;
import gmaths.*;
import tool.Material;
import tool.Shader;
import basisObj.*;


public class Light {
	  private Material material;
	  private Vec3 position;
	  private Mat4 model;
	  private Shader shader;
	  private Camera camera;
	  private GL3 gl;
	  
	  public Light(GL3 gl) {
		material = new Material();
		material.setAmbient(0.3f, 0.3f, 0.3f);
		material.setDiffuse(0.7f, 0.7f, 0.7f);
		material.setSpecular(0.8f, 0.8f, 0.8f);
		position = new Vec3(-5f,30f,-5f);
		model = new Mat4(1);
		this.gl = gl;
	  }
	  
	  public void setShade(String[] path) {
		  this.shader = new Shader(gl, path[0], path[1]);
		  fillBuffers(this.gl);
	  }
	  
	  public void setPosition(Vec3 v) {
		    position.x = v.x;
		    position.y = v.y;
		    position.z = v.z;
		  }
	  
	  public void setPosition(float x, float y, float z) {
		    position.x = x;
		    position.y = y;
		    position.z = z;
		  }
	  
	  public Vec3 getPosition() {
		    return position;
		  }
		  
		  public void setMaterial(Material m) {
		    material = m;
		  }

		  public Material getMaterial() {
		    return material;
		  }
		  
	  public void setCamera(Camera camera) {
		  this.camera = camera;
	  }
	  
	  public void render(GL3 gl) { 
		    Mat4 model = new Mat4(1);
		    model = Mat4.multiply(Mat4Transform.scale(0.3f,0.3f,0.3f), model);
		    model = Mat4.multiply(Mat4Transform.translate(position), model);
		    
		    Mat4 mvpMatrix = Mat4.multiply(camera.getPerspectiveMatrix(), Mat4.multiply(camera.getViewMatrix(), model));
		    
		    shader.use(gl);
		    shader.setFloatArray(gl, "mvpMatrix", mvpMatrix.toFloatArrayForGLSL());

		    gl.glBindVertexArray(vertexArrayId[0]);
		    gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
		    gl.glBindVertexArray(0);
	  }
	  
	  public void dispose(GL3 gl) {
		    gl.glDeleteBuffers(1, vertexBufferId, 0);
		    gl.glDeleteVertexArrays(1, vertexArrayId, 0);
		    gl.glDeleteBuffers(1, elementBufferId, 0);
	  }
	  
	  // create obj
	  private float[] vertices = createVertices();
	  private int[] indices = createIndices();
	  private int vertexStride = 3;
	  private int vertexXYZFloats = 3;
	  // buffer
	  private int[] vertexBufferId = new int[1];
	  private int[] vertexArrayId = new int[1];
	  private int[] elementBufferId = new int[1];
	  
	  public void setVertices(float[] vertices) {
		  this.vertices = vertices;
	}
	  
	  public void setIndices(int[] indices) {
		  this.indices = indices;
	  }
	  
	  private void setVertice(float[] vertices) {
		this.vertices = vertices;
	}
	  
	  private void fillBuffers(GL3 gl) {
		    gl.glGenVertexArrays(1, vertexArrayId, 0);
		    gl.glBindVertexArray(vertexArrayId[0]);
		    gl.glGenBuffers(1, vertexBufferId, 0);
		    gl.glBindBuffer(GL.GL_ARRAY_BUFFER, vertexBufferId[0]);
		    FloatBuffer fb = Buffers.newDirectFloatBuffer(vertices);
		    
		    gl.glBufferData(GL.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, fb, GL.GL_STATIC_DRAW);
		    
		    int stride = vertexStride;
		    int numXYZFloats = vertexXYZFloats;
		    int offset = 0;
		    gl.glVertexAttribPointer(0, numXYZFloats, GL.GL_FLOAT, false, stride*Float.BYTES, offset);
		    gl.glEnableVertexAttribArray(0);
		     
		    gl.glGenBuffers(1, elementBufferId, 0);
		    IntBuffer ib = Buffers.newDirectIntBuffer(indices);
		    gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, elementBufferId[0]);
		    gl.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, Integer.BYTES * indices.length, ib, GL.GL_STATIC_DRAW);
		    gl.glBindVertexArray(0);
	  } 
	  
	  private final int XZCSTEP= 36;
	  private final int  YSTEP = 36;	
	  
	  private float[] createVertices() {
		    double r = 0.5;
		    int step = 3;
		    float[] vertices = new float[XZCSTEP*YSTEP*step];
		    
		    for (int j = 0; j<YSTEP; ++j) {
		      double b = Math.toRadians(-90+180*(double)(j)/(YSTEP-1));
		      for (int i = 0; i<XZCSTEP; ++i) {
		    	  double a = Math.toRadians(360*(double)(i)/(XZCSTEP-1));
		          double z = r*Math.cos(b) * Math.cos(a);
		          double x = r*Math.cos(b) * Math.sin(a);
		          double y = r* Math.sin(b);
		          int base = j*XZCSTEP*step;
		          vertices[base + i*step+0] = (float)x;
		          vertices[base + i*step+1] = (float)y;
		          vertices[base + i*step+2] = (float)z; 
//		          vertices[base + i*step+3] = (float)x;
//		          vertices[base + i*step+4] = (float)y;
//		          vertices[base + i*step+5] = (float)z;
//		          vertices[base + i*step+6] = (float)(i)/(float)(XZCSTEP-1);
//		          vertices[base + i*step+7] = (float)(j)/(float)(YSTEP-1);
		      }
		    }
		    return vertices;
	  }
	  
	  private int[] createIndices() {
		    int[] indices = new int[(XZCSTEP-1)*(YSTEP-1)*6];
		    for (int j = 0; j<YSTEP-1; ++j) {
		      for (int i = 0; i<YSTEP-1; ++i) {
		        int base = j*(YSTEP-1)*6;
		        indices[base + i*6+0] = j*XZCSTEP+i;
		        indices[base + i*6+1] = j*XZCSTEP+i+1;
		        indices[base + i*6+2] = (j+1)*XZCSTEP+i+1;
		        indices[base + i*6+3] = j*XZCSTEP+i;
		        indices[base + i*6+4] = (j+1)*XZCSTEP+i+1;
		        indices[base + i*6+5] = (j+1)*XZCSTEP+i;
		      }
		    }
		    return indices;
	  }
		  
		  
}
