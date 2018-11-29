package lamp;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

import com.jogamp.opengl.GL3;

import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.BaseNode;
import tool.Model;
import tool.ModelContainer;
import tool.NodeContainer;


public class Lamp extends NodeContainer{
	
	FoundationNode foundation;
	BodyPoleNode bodyPole;
	HeaderNode head;
	
	Vec3 baseAxis = new Vec3(0f, 5f, -7f);
	Vec3 H_speed = new Vec3(0.01f,0,0);
	Vec3 V_speed = new Vec3(0, 0.5f, 0);
	
	Vec3 basisSurface = new Vec3(-5f, 5.35f, -7f);
	Mat4 basisTransform = Mat4Transform.translate(basisSurface);
	Mat4 moveTransform = basisTransform;
	
	
	final float pole_length = 1f;
	
	float pole_drgree = 60;
	float shade_degree = 30;
	
	GL3 gl;
	List<ModelContainer> modelContainers;
	
	public Lamp(GL3 gl) {
		super(gl, "lamp");
		this.gl = gl;
		
		
		foundation = new FoundationNode(gl);
		bodyPole = new BodyPoleNode(gl);
		head = new HeaderNode(gl);
		
		bodyPole.pole_length = pole_length;
	
		
		this.addChild(foundation);
		foundation.addToLast(bodyPole);
		bodyPole.addToLast(head);
	}
	
	public void update(Camera camera,Light light) {

//		foundationTransform = Mat4.multiply(foundationTransform, Mat4Transform.rotateAroundY(theta));
		
		bodyPole.degree = pole_drgree;
		head.pole_drgree = pole_drgree;
		head.shade_degree = 90f;
		

		if( x > 10f && h == 0f) {
			turning();
		} else {
			move();
		}
		
		super.update(moveTransform, camera, light);
	}
	
	
	float vx = 0.02f;
	float o_vh = 0.05f;
	float g = 0.001f;
	float vh = o_vh;
	float h = 0;
	float x = 0;
	private void move() {
		h+=vh;
		vh-=g;
		x+=vx;
		moveTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,h,0)));
		if (h<=0) {
			h = 0;
			vh = o_vh;
		}
	}
	
	private float turn_angel = 0;
	private boolean turning_dirct = true; //true: clockwist, false:anti-clockwist
	private void turning() {
		moveTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.rotateAroundY(turn_angel));
		turn_angel+=turning_dirct?-2:2;
		if(turning_dirct) {
			if(turn_angel <-180) {
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundY(-180));
				x = 0;
				vh = o_vh;
				turn_angel = 0;
				turning_dirct = !turning_dirct;
			}
		} else {
			if(turn_angel >180) {
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundY(180));
				x = 0;
				vh = o_vh;
				turn_angel = 0;
				turning_dirct = !turning_dirct;
			}
		}
		
	}
	
	
	private double startTime=getSeconds();
	
	private double getSeconds() {
	    return System.currentTimeMillis()/1000.0;
	}
	
	private float getInterval() {
		return (float) (getSeconds() - startTime);
	}
	  
	  
}

