package lamp;

import com.jogamp.opengl.GL3;

import gmaths.Mat4;
import gmaths.Mat4Transform;
import gmaths.Vec3;
import scene.Camera;
import scene.Light;
import tool.Constant;
import tool.NodeContainer;
import tool.TextureLibrary;

public class BodyPoleNode extends NodeContainer{
	
	PoleNode pole1;
	JointNode joint;
	PoleNode pole2;
	float theta;
	Vec3 pol1_t;
	Vec3 joint_t;
	Vec3 pol2_t;
	
	float pole_length = 1f;
	float degree = 0;
	
	public BodyPoleNode(GL3 gl) {
		super(gl,"BodyPole");
		// TODO Auto-generated constructor stub
		
		texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		
		
		pole1 = new PoleNode(gl, "pole1");
		joint = new JointNode(gl,"p1-joint-p2");
		pole2 = new PoleNode(gl,"pole2");
		
		
		pole1.setTexture(texture);
		joint.setTexture(texture);
		pole2.setTexture(texture);
		
		this.addChild(pole1);
		pole1.addChild(joint);
		joint.addChild(pole2);
	}
	
	void updateBranch() {
		theta = (float) Math.toRadians(degree);
		
		pole1.rotate(new Vec3(0,0,degree));
		pole1.translate(new Vec3(0,(pole_length/2),0));
		
		joint.translate(new Vec3(0,(pole_length/2),0));
		joint.rotate(new Vec3(0,0,-degree));
		
		pole2.rotate(new Vec3(0,0,-degree));
		pole2.translate(new Vec3(0,(pole_length/2),0));
	}
	
	@Override
	public void update(Mat4 worldTransform, Camera camera, Light light) {
		// TODO Auto-generated method stubs
		updateBranch();
		super.update(worldTransform, camera, light);
	}
	
	
}
