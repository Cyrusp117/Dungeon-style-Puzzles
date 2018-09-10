package ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Application {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int windowWidth, windowHeight;
	
	public Application(String title, int windowWidth, int windowHeight) {
		this.title = title;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
        initUI();
    }

    private void initUI() {
    	frame = new JFrame(title);
        frame.setSize(windowWidth,windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        canvas.setMaximumSize(new Dimension(windowWidth, windowHeight));
        canvas.setMinimumSize(new Dimension(windowWidth, windowHeight));
        canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
    }

	/**
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}    
    
    
}