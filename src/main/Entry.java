package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import scene.*;


class MyMouseInput extends MouseMotionAdapter {
	  private Point lastpoint;
	  private Camera camera;
	  
	  public MyMouseInput(Camera camera) {
	    this.camera = camera;
	  }
	  public void mouseDragged(MouseEvent e) {
	    Point ms = e.getPoint();
	    float sensitivity = 0.001f;
	    float dx=(float) (ms.x-lastpoint.x)*sensitivity;
	    float dy=(float) (ms.y-lastpoint.y)*sensitivity;
	    if (e.getModifiers()==MouseEvent.BUTTON1_MASK)
	      camera.updateYawPitch(dx, -dy);
	    lastpoint = ms;
	  }
	  public void mouseMoved(MouseEvent e) {   
	    lastpoint = e.getPoint(); 
	  }
	}

class MyKeyboardInput extends KeyAdapter  {
	  private Camera camera;
	  
	  public MyKeyboardInput(Camera camera) {
	    this.camera = camera;
	  }
	  public void keyPressed(KeyEvent e) {
	    Camera.Movement m = Camera.Movement.NO_MOVEMENT;
	    switch (e.getKeyCode()) {
	      case KeyEvent.VK_LEFT:  m = Camera.Movement.LEFT;  break;
	      case KeyEvent.VK_RIGHT: m = Camera.Movement.RIGHT; break;
	      case KeyEvent.VK_UP:    m = Camera.Movement.UP;    break;
	      case KeyEvent.VK_DOWN:  m = Camera.Movement.DOWN;  break;
	      case KeyEvent.VK_A:  m = Camera.Movement.FORWARD;  break;
	      case KeyEvent.VK_Z:  m = Camera.Movement.BACK;  break;
	    }
	    camera.keyboardInput(m);
	  }
	}


public class Entry extends JFrame {
	  private static final int WIDTH = 1024;
	  private static final int HEIGHT = 768;
	  private static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
	  public GLCanvas canvas;
	  public GLEventListener my_glEventListener;
	  private final FPSAnimator animator; 
	  
	  
	  public Entry(String textForTitleBar) {
		    super(textForTitleBar);
		    GLCapabilities glcapabilities = new GLCapabilities(GLProfile.get(GLProfile.GL3));
		    glcapabilities.setSampleBuffers(true); 
		    glcapabilities.setNumSamples(4);
		    canvas = new GLCanvas(glcapabilities);
		    Camera camera = new Camera(Camera.DEFAULT_POSITION, Camera.DEFAULT_TARGET, Camera.DEFAULT_UP);
		    MyGLEventListener my_glEventListener = new MyGLEventListener(camera);
		    this.my_glEventListener = my_glEventListener;
		    canvas.addGLEventListener(my_glEventListener);
		    canvas.addMouseMotionListener(new MyMouseInput(camera));
		    canvas.addKeyListener(new MyKeyboardInput(camera));
		    getContentPane().add(canvas, BorderLayout.CENTER);
		    
		    JPanel p = new JPanel();
		    JButton b = new JButton("Random Jumping! (once)");
		    b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					my_glEventListener.randomJump();
				}
			});
		    
		    p.add(b);
		    
		    b = new JButton("Random posing! ");
		    b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					my_glEventListener.randomPosing();
				}
			});
		    
		    p.add(b);
		    
		    this.add(p, BorderLayout.SOUTH);
		    
		    addWindowListener(new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        animator.stop();
			        remove(canvas);
			        dispose();
			        System.exit(0);
			      }
			    });
			    animator = new FPSAnimator(canvas, 60);
			    animator.start();
		  }
	  
	  public static void main(String[] args) {
		    Entry e1 = new Entry("Lamp");
		    e1.getContentPane().setPreferredSize(dimension);
		    e1.pack();
		    e1.setVisible(true);
		  }
}
	 
