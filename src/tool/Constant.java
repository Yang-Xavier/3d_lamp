package tool;

import gmaths.Vec3;

public class Constant {
	public static final String TEXTURE_BASEPATH = "model_source/texture/";
	public static final String SHADER_BASEPATH = "model_source/shader/";
	public static final String DEFAULT_VS = SHADER_BASEPATH + "vs_default.txt";
	public static final String DEFAULT_FS = SHADER_BASEPATH + "fs_default.txt";
	
	public static final String DEFAULT_LIGHT_VS = SHADER_BASEPATH + "vs_light.txt";
	public static final String DEFAULT_LIGHT_FS = SHADER_BASEPATH + "fs_light.txt";
	
	public static final Vec3  DEFAULT_AMBIENT =  new Vec3(0.0f, 0.5f, 0.81f);
	public static final Vec3  DEFAULT_DIFFUSE =  new Vec3(0.0f, 0.5f, 0.81f);
	public static final Vec3  DEFAULT_SPECULAR =  new Vec3(0.3f, 0.3f, 0.3f);
	public static final float DEFAULT_SHIININESS = 50f;
	
	public static final Vec3 DEFAULT_CAMERA_POSITION = new Vec3(0f,10f,10f);
	public static final Vec3 DEFAULT_CAMERA_TRAGET = new Vec3(0f,5f,0f);
	
	public static final Vec3 NATURE_LIGHT_DIRECTION = new Vec3(-0.2f, -1.0f, -0.3f);
	public static final Vec3 NATURE_LIGHT_AMBIENT = new Vec3(0.2f, 0.2f, 0.2f);
	public static final Vec3 NATURE_LIGHT_DIFFUSE = new Vec3(0.3f, 0.3f, 0.3f);
	public static final Vec3 NATURE_LIGHT_SPECULAR = new Vec3(0.4f, 0.4f, 0.4f);

}
