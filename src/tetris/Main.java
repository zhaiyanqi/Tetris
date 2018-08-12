package tetris;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	static BufferedImage background;
	static BufferedImage imgI;
	static BufferedImage imgJ;
	static BufferedImage imgL;
	static BufferedImage imgO;
	static BufferedImage imgS;
	static BufferedImage imgT;
	static BufferedImage imgZ;	
	
	public static void main(String[] args) throws IOException {
		// 初始化素材
		background = ImageIO.read(Main.class.getResource("/pic/tetris.png"));
		imgI = ImageIO.read(Main.class.getResource("/pic/I.png"));
		imgJ = ImageIO.read(Main.class.getResource("/pic/J.png"));
		imgL = ImageIO.read(Main.class.getResource("/pic/L.png"));
		imgO = ImageIO.read(Main.class.getResource("/pic/O.png"));
		imgS = ImageIO.read(Main.class.getResource("/pic/S.png"));
		imgT = ImageIO.read(Main.class.getResource("/pic/T.png"));
		imgZ = ImageIO.read(Main.class.getResource("/pic/Z.png"));
		JFrame frame = new JFrame("俄罗斯方块");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(500, 30);
		
		GamePanel game = new GamePanel();
		frame.add(game);
		frame.pack();
		
		frame.setVisible(true);
		game.action();
		game.requestFocus();
	}
}
