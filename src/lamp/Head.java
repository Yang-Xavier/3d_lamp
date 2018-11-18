package lamp;

import com.jogamp.opengl.GL3;

import tool.BaseModel;
import tool.Constant;
import tool.ModelContainer;
import tool.TextureLibrary;

public class Head extends ModelContainer{
	int[] test_texture;
	public Head(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
		
	}
}

class  Lampshade extends BaseModel{
	public Lampshade(GL3 gl) {
		// TODO Auto-generated constructor stub
		super(gl);
		
	}
}