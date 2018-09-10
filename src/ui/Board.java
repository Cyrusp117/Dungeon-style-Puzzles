package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import entities.Player;
import entities.moveAdapter;

public class Board extends JPanel
        implements ActionListener {

    private int boardWidth;
    private int boardHeight;
    private int initialPlayerX; 
    private int initialPlayerY;
    private Player player;
    private int x, y;

    public Board(int boardWidth, int boardHeight, int initialX, int initialY) {
    	this.boardWidth = boardWidth;
    	this.boardHeight = boardHeight;
    	this.initialPlayerX = initialX;
    	this.initialPlayerY = initialY;
    	this.player = new Player(initialX, initialY); 
        initBoard();
    }

    private void initBoard() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        addKeyListener(new moveAdapter(player, this));
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.black);
        setDoubleBuffered(true);
        x = initialPlayerX;
        y = initialPlayerY;
    }
    
    private void updatePlayer() {
    	player.move();
    }
	

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        
        Toolkit.getDefaultToolkit().sync();
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(player.getImage(), player.getPosition().getxPosition(), 
            player.getPosition().getyPosition(), this);
    }
	public void updateGame() {
		System.out.println("Action detected");
		updatePlayer();
		printPlayerCoordinates();
		repaint();
		//updateEnemies();
		
	}
	
	private void printPlayerCoordinates() {
		System.out.println(player.returnPosition());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

