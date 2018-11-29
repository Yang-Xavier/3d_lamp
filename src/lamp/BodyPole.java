//package lamp;
//
//import com.jogamp.opengl.GL3;
//
//import basisObj.Cylinder;
//import tool.BaseModel;
//import tool.Constant;
//import tool.Material;
//import tool.Mesh;
//import tool.ModelContainer;
//import tool.Shader;
//import tool.TextureLibrary;
//
//public class BodyPole extends ModelContainer{
//	int[] test_texture;
//	Pole pole1;
//	Joint joint;
//	Pole pole2;
//	
//	public BodyPole(GL3 gl) {
//		super(gl);
//		test_texture = TextureLibrary.loadTexture(gl, Constant.TEXTURE_BASEPATH+"brickwall.jpg");
//		pole1 = new Pole(gl);
//		joint = new Joint(gl);
//		pole2 = new Pole(gl);
//		
//
//		baseModels.add(pole1);
//		baseModels.add(pole2);
//		baseModels.add(joint);
//}
//	
//	@Override
//	public void initial() {
//		// TODO Auto-generated method stub
//		super.initial();
//		pole1.setTexture(test_texture);
//		pole1.translate(relativePosition(-0.55f, 1.3f, 0f));
//		pole1.rotate(0,0,45f);
//		
//		joint.setTexture(test_texture);
//		joint.translate(relativePosition(-1.1f, 1.85f, 0f));
//		joint.rotate(0,0,45f);
//		
//		pole2.setTexture(test_texture);
//		pole2.translate(relativePosition(-0.55f, 2.4f, 0f));
//		pole2.rotate(0,0,-45f);
//	}
//}
//
//class Pole extends BaseModel{
//	public Pole(GL3 gl) {
//		// TODO Auto-generated constructor stub
//		super(gl);
//		super.mesh = new Mesh(gl, Cylinder.vertices.clone(), Cylinder.indices.clone());
//		super.shader = new Shader(gl, Constant.DEFAULT_VS, Constant.DEFAULT_FS);
//		super.material = new Material(Constant.DEFAULT_AMBIENT,Constant.DEFAULT_DIFFUSE,Constant.DEFAULT_SPECULAR, Constant.DEFAULT_SHIININESS);
//		
//		scale(0.15f,1.5f,0.15f);
//	}
//}
