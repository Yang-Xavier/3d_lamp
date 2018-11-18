package lamp;

import com.jogamp.opengl.GL3;

import tool.BaseModel;
import tool.Constant;
import tool.ModelContainer;
import tool.TextureLibrary;

public class Head extends ModelContainer{
	int[] test_texture;
	Joint joint ;
	
	public Head(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		joint = new Joint(gl);
		
		baseModels = new BaseModel[3];
		baseModels[0] = joint;
	}
	
	@Override
	public void initial() {
		// TODO Auto-generated method stub
		super.initial();
		
	}
	
}

class  Lampshade extends BaseModel{
	public Lampshade(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		
	}
}