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
		bodyPole.degree = pole_drgree;
		head.pole_drgree = pole_drgree;
		head.shade_degree = 90f;
		
		if (jump) {
			move();
		}
		
		super.update(moveTransform, camera, light);
	}
	
	
	private boolean jump  = true;
	public void randomJump() {
		initJumpParameter();
		jump  = true;
	}
	
	public void jumpDone() {
//		jump  = false;
		flag = true;
		moveTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,h,0)));
		basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,h,0)));
	}
	
	private float distance,vx,vh,h,x,g;
	private void initJumpParameter() {
		distance = getRandomDistance();
		vx = 0.1f;
		g = 0.005f;
		vh = (distance*g/vx)/2f;
		h = 0;
		System.out.println(distance+" " + vx + " " + vh + " " + x +" "  + should_turning );
	}
	
	boolean flag = true;
	private void move() {
		if (flag) {
			initJumpParameter();
			flag = false;
		}
		if (!should_turning) {
			vh-=g;
			x+=vx;
			h+=vh;
			if(x>=10) {
				x =10;
			}
			moveTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,h,0)));
			if (h<=0) {
				h=0;
				jumpDone();
				if(x ==10) {
					should_turning = true;
				}
			}
		} else {
			turning();
		}
	}
	
	
	private void moveTransition() {
		
	}
	
	
	private float turn_angel = 0;
	private boolean turning_dirct = true; //true: clockwist, false:anti-clockwist
	private boolean should_turning = false;
	private void turning() {
		moveTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.rotateAroundY(turn_angel));
		turn_angel+=turning_dirct?-2:2;
		if(turning_dirct) {
			if(turn_angel <-180) {
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundY(-180));
				turn_angel = 0;
				turning_dirct = !turning_dirct;
				should_turning = false;
				x = 0;
			}
		} else {
			if(turn_angel >180) {
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(new Vec3(x,0,0)));
				basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundY(180));
				turn_angel = 0;
				turning_dirct = !turning_dirct;
				should_turning = false;
				x = 0;
			}
		}
	}
	
	private float getRandomDistance() {
		
		float randomDis = (float) Math.random()*5+2;
//		if(10-x<=4f) {
//			if (10-x>=2f) {
//				randomDis = 10-x;
//			} else {
//				randomDis = (float) Math.random()*x;
//				x = 10-x;
//				should_turning = true;
//			}
//		} else if (x+randomDis>10) {
//			randomDis = (float) Math.random()*(8-x)+2;
//		}
		return randomDis;
	}
	
	private double startTime=getSeconds();
	
	private double getSeconds() {
	    return System.currentTimeMillis()/1000.0;
	}
	
	private float getInterval() {
		return (float) (getSeconds() - startTime);
	}
	  
	  
}

