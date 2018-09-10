package entities;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ui.Board;

public class moveAdapter extends KeyAdapter {
    
	private Player player;
	private Board board;
	
	public moveAdapter(Player player, Board board) {
		this.player = player;
		this.board = board;
	}
//	@Override
//    public void keyReleased(KeyEvent e) {
//        player.keyReleased(e);
//    }
    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
        board.updateGame();
        
    }
}


