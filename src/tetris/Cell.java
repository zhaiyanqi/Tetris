package tetris;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * ����
 * @author zhaiy
 *
 */
public class Cell {
	public static final int WIDTH = Main.imgI.getWidth();//����Ŀ��
	int row;
    int col;
	BufferedImage img;
	
	public Cell() {
		
	}
	
	public Cell(int row, int col, BufferedImage img) {
		this.row = row;
		this.col = col;
		this.img = img;
	}
	
	public void paint(Graphics g) {
		g.drawImage(img, 15 + col * WIDTH, 15 + row * WIDTH, null);
	}

	public void moveRight(){
		col++;
	}
	
	public void moveLeft(){
		col--;
	}
	
	public void moveDown(){
		row++;
	}
	public void moveUp() {
		row--;
	}
	public boolean isOut() {
		return row >= GamePanel.ROWS || row < 0
				|| col >= GamePanel.COLS || col < 0;
	}
}






