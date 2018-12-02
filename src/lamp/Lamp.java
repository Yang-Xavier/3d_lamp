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
	
	Vec3 baseAxis = new Vec3(0f, 5f, -7f);	// table platform
	
	Vec3 basisSurface = new Vec3(0f, 5.35f, -7f);	// basis position for lamp
	Mat4 basisTransform = Mat4Transform.translate(basisSurface); // initial position and status 
	Mat4 moveTransform = new Mat4(1);		// status for each action
	
	
	final float pole_length = 1f;		// pole for lamp
	
			
	float min_pole_degree = 10; 	// the angle between the poles and the vertical direction
	float max_pole_degree = 70;
	float init_pole_degree = 60;
	float pole_degree = init_pole_degree; 

//	float min_shade_degree = 10; 	// the angle for the shade
//	float max_shade_degree = 120;
	float init_shade_degree = 60;
	float shade_degree = init_shade_degree; 
	
	GL3 gl;
	List<ModelContainer> modelContainers;
	
	Vec3 forword = new Vec3();
	
	
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
		bodyPole.degree = pole_degree;
		head.pole_drgree = pole_degree;
		head.shade_degree = shade_degree;
		head.x_degree = shade_shake_degree;
		
		double theta = Math.toRadians(shade_degree);
		double alpha = Math.toRadians(count_angle+shade_shake_degree);
		forword.x = (float) (Math.sin(theta)*Math.cos(alpha));
		forword.y = -(float) Math.cos(theta);
		forword.z = (float) (Math.sin(theta)*Math.sin(alpha));
		
		if (jump) {
			move();
		}
		super.update(Mat4.multiply(basisTransform, moveTransform), camera, light);
	}
	private boolean jump  = true;
	
	public void randomJump() {
		initJumpParameter();
		jump  = true;
	}
	
	public void jumpDone() {
		jump  = false;
	}
	
	private float distance,hori_v,v_v,h,x=0,y=0,g,turning_angle,forward, count_angle = 0, max_random, min_random;
	private float compress_scale, compress_degree,final_compress_degree, a_degree,v_degree;
	private float shade_shake_degree; int shade_shake_times;
	private float[] X= {-4,4}, Y = {-2,2};	// limitation of the surface
	
	private boolean turning_dirct; //true: clockwist, false:anti-clockwist
	private float turning_step = 0;
	
	private void initJumpParameter() {
		// initialise the jump
		max_random = 5;
		min_random = 2;
		distance = (float) Math.random()*(max_random-min_random)+min_random; // minimum 2   jumping length
		hori_v = 0.1f;	// horizontal speed
		g = 0.005f;	// gravity 
		v_v = (distance*g/hori_v)/2f;	// vertical accelerate speed
		h = 0;	// height 
		
		// initialise the turning 
		turning_angle = (float) Math.random()*225+45;		// minimum 45 maximum 270
		turning_dirct = Math.random()>0.5;		// true: clocktwisting, false:anti-clocktwisting
		turning_step = turning_angle/40;		// angle for turning each time
		forward = turning_dirct? -turning_angle:turning_angle;	//	turning direction
		is_turning = true;		// a switch for turning 
		is_looking = true;
		recover = true;
		count_angle+=forward;	// accumulation angle
		
		// initialise the transition
		compress_scale = (max_pole_degree-min_pole_degree)/max_random; // the angle scale of the poles before jump
		compress_degree = compress_scale*distance;	//  the angle  of the poles before jump
		final_compress_degree = max_pole_degree-compress_degree; //  the final angle scale of the poles in jumping
		v_degree = 4;
		a_degree = 0.04f;
		
		shade_shake_degree = 0f;
		shade_shake_times = 2;
	}
	
//	private float basis_angel = 0;
	private float turning_ = 0;
	private boolean is_looking = true;
	private int times_of_shake = 0;
	private float increase = 2;
	private void turning() {
		if(is_looking) {
			if (shade_shake_degree>=60) {
				shade_shake_degree = 60;
				times_of_shake ++;
				increase = -increase;
				
				startTime = getSeconds();
				while ((getSeconds()-startTime)<0.3) {}
			}else {
				shade_shake_degree+=increase;
			} 
			if(shade_shake_degree<= -60){
				shade_shake_degree= -60;
				times_of_shake ++;
				increase = -increase;
				startTime = getSeconds();
				while ((getSeconds()-startTime)<0.3) {}
			}  else {
				shade_shake_degree+=increase;
			}
			if (shade_shake_degree*(shade_shake_degree+increase)<=0 && times_of_shake==shade_shake_times) {
				shade_shake_degree = 0;
				times_of_shake = 0;
				shade_shake_times = 2;
				is_looking = false;
				recover = false;
			}
		} else {
			if(turning_+turning_step>=turning_angle) { // final step for rotating
				turning_ = turning_angle;
				is_turning = false;
				is_transition = true;
				moveTransform = new Mat4(1);
				transformBasis(new Vec3(0,0,0), new Vec3(0,turning_dirct? -turning_angle: turning_angle,0));
				turning_= 0;
			} else {
				turning_ += turning_step;
				transformMoving(new Vec3(0,0,0), new Vec3(0,turning_dirct? -turning_step: turning_step,0));
			}
		}
		
	}
	
	private boolean recover = false;
	private void transtion() {
//		distance decide the angle
		if(!recover) {
			if (pole_degree+2 >= max_pole_degree) {
				pole_degree = max_pole_degree;
				recover = true;
			} else {
				pole_degree +=2;
			}
		} else {
			if (pole_degree - v_degree <= final_compress_degree) {
				pole_degree = final_compress_degree;
				is_transition = false;
				is_jumping = true;
			} else {
				pole_degree -= v_degree;
				if(v_degree - a_degree <=1) {
					v_degree = 1f;
				} else {
					v_degree -= a_degree;
				}
			}
		}
		
	}
	
	private float lamp_theta = 0;
	private float count_hori = 0;
	private float horizontal_step = 0;
	private void jumping() {
		if (x+hori_v*Math.cos(lamp_theta)>=X[1]) {  // x right end
			x = X[1];
		} else if(x+hori_v*Math.cos(lamp_theta)<=X[0]){ // x left end
			x = X[0];
		} else {
			if (y+hori_v*Math.sin(lamp_theta)>=Y[1]) { // y top end
				y = Y[1];
			} else if (y+hori_v*Math.sin(lamp_theta)<=Y[0]) {	// y bottom end
				y = Y[0];
			} else {	// y,x not end
				x+=hori_v*Math.cos(lamp_theta);
				y+=hori_v*Math.sin(lamp_theta);
				transformMoving(new Vec3(hori_v,0,0), new Vec3(0,0,0));
				horizontal_step+=hori_v;
				count_hori+=hori_v;
			}
		} // horizontal moving judging
		
		if (h+v_v<=0) {
			h=0;
			is_jumping=false;
			transformBasis(new Vec3(horizontal_step,0,0), new Vec3(0,0,0));
			moveTransform = new Mat4(1);
			horizontal_step = 0;
			is_jumping = false;
			is_stop_jumping_transition = true;
			v_degree = 4;
			a_degree = 0.04f;
		} else {
			h+=v_v;
			v_v-=g;
			transformMoving(new Vec3(0,v_v,0), new Vec3(0,0,0));
			
			if (h<=1) {
				pole_degree += 1;
			} else {
				if (pole_degree - 0.5 <= min_pole_degree) {
					pole_degree = min_pole_degree;
				} else {
					pole_degree -= 0.5;
				}
			}
		}
	}
	
	private void stop_jump_transition() {
		if (pole_degree + v_degree >= init_pole_degree) {
			pole_degree = init_pole_degree;
			is_stop_jumping_transition = false;
		} else {
			pole_degree += v_degree;
			if(v_degree - a_degree <=1) {
				v_degree = 1f;
				jumpDone();
			} else {
				v_degree -= a_degree;
			}
		}
	}
		
	private boolean is_turning= false;
	private boolean is_jumping= false;
	private boolean is_transition= false;
	private boolean is_stop_jumping_transition = false;
	
	private void move() {
		if(is_turning) {
			turning();
		}
		if(is_transition) {
			transtion();
		}
		if(is_jumping) {
			lamp_theta = (float) Math.toRadians(count_angle);
			jumping();
		}
		if(is_stop_jumping_transition) {
			stop_jump_transition();
		}
	}
	
	private void transformMoving(Vec3 translate,Vec3 turning_angle) {
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.translate(translate));
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.rotateAroundX(turning_angle.x));
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.rotateAroundY(turning_angle.y));
		moveTransform = Mat4.multiply(moveTransform, Mat4Transform.rotateAroundZ(turning_angle.z));
	}
	
	private void transformBasis(Vec3 translate,Vec3 turning_angle) {
		basisTransform = Mat4.multiply(basisTransform, Mat4Transform.translate(translate));
		basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundX(turning_angle.x));
		basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundY(turning_angle.y));
		basisTransform = Mat4.multiply(basisTransform, Mat4Transform.rotateAroundZ(turning_angle.z));
	}
	

	private float getRandomDistance() {
		
		float randomDis = (float) Math.random()*8+2;
		
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

