package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 6952882875848963759L;
	public static final int ROWS = 18;// 20行
	public static final int COLS = 9;// 10列
	public static final int WIDTH = Main.background.getWidth();// 窗口宽度
	public static final int HEIGHT = Main.background.getHeight();// 窗口高度

	public static final int GAME_NOT_START = 0;

	public static final int GAME_RUN = 1;
	public static final int GAME_OVER = 2;
	public static final int GAME_PAUSE = 3;
	public static int counter;

	BufferedImage background = Main.background;
	int status = GAME_NOT_START;
	Timer timer = new Timer();

	Cell[][] cells = new Cell[ROWS][COLS];
	Brick current; // 当前砖块
	Brick next; // 下个砖块
	int score;

	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//gameStart();
	}

	public void action() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					moveRightCurrent();
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					moveLeftCurrent();
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					current.speed = 2;
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					rotateCurrent();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
					if(status == GAME_NOT_START||status == GAME_OVER) {
						gameStart();
					}else if(status == GAME_RUN) {
						status = GAME_PAUSE;
					}else if(status == GAME_PAUSE) {
						status = GAME_RUN;
					}
				} else if(e.getKeyCode() == KeyEvent.VK_Q) {
					System.exit(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					current.speed = 20;
				}
			}
		});
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(status == GAME_RUN) {
					counter++;
					if (canStep()) {
						current.step();
					} else {
						check();
					}
				}
				repaint();
			}
		}, 100, 1000 / 60);
	}

	private boolean canStep() {
		current.moveDown();
		boolean canstep = true;
		if (current.isOut() || touchCheck()) {
			canstep = false;
		}
		current.moveUp();
		return canstep;
	}

	protected void check() {
		currentLanded();
		destroyLines();
		checkGameOver();
		current = next;
		next = randomBrick();
	}

	private void checkGameOver() {
		if(cells[0][4]==null){
			return;
		}
		status = GAME_OVER;
		repaint();
	}

	private void clearCells() {
		for (int i = 0; i < cells.length; i++) {
			Arrays.fill(cells[i], null);
		}

	}

	private void currentLanded() {
		for (int i = 0; i < current.cells.length; i++) {
			cells[current.cells[i].row][current.cells[i].col] = current.cells[i];
		}
		score += 10;
	}

	private void destroyLines() {
		int line = 0;
		for (int i = 0; i < cells.length; i++) {
			if (isFull(i)) {
				Arrays.fill(cells[i], null);
				for (int j = i; j >= 1; j--) {
					System.arraycopy(cells[j - 1], 0, cells[j], 0, COLS);
					for (int k = 0; k < cells[j].length; k++) {
						if (cells[j][k] != null)
							cells[j][k].moveDown();
					}
				}
				Arrays.fill(cells[0], null);
				line++;
			}
		}
		if (line > 0) {
			score += 100 * Math.pow(2, line - 1);
		}
	}

	protected void gameStart() {
		clearCells();
		current = randomBrick();
		next = randomBrick();
		status = GAME_RUN;
	}

	private boolean isFull(int row) {
		Cell[] tmp = cells[row];
		for (int i = 0; i < tmp.length; i++) {
			if (tmp[i] == null)
				return false;
		}
		return true;
	}

	protected void moveLeftCurrent() {
		current.moveLeft();
		if (current.isOut() || touchCheck()) {
			current.moveRight();
		}
	}

	protected void moveRightCurrent() {
		current.moveRight();
		if (current.isOut() || touchCheck()) {
			current.moveLeft();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		paintScore(g);
		paintStatus(g);
		if (status == GAME_NOT_START) {
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("回车键开始游戏", 315, 230);
		} else if (status == GAME_RUN) {
			current.paint(g);
			paintCells(g);
			paintNext(g);
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("回车键暂停游戏", 315, 230);
		} else if (status == GAME_PAUSE) {
			current.paint(g);
			paintCells(g);
			paintNext(g);
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("回车键继续游戏", 315, 230);
		} else if (status == GAME_OVER) {
			paintCells(g);
			paintNext(g);
			Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
			g.setFont(font);
			g.setColor(Color.black);
			g.drawString("回车键重新开始", 315, 230);
		}
	}

	private void paintStatus(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		g.setFont(font);
		g.setColor(Color.black);
		switch (status) {
		case GAME_NOT_START:  g.drawString("状态: 未开始", 320, 285); break;
		case GAME_PAUSE:  g.drawString("状态: 暂停", 320, 285); break;
		case GAME_RUN:  g.drawString("状态:运行中", 320, 285); break;
		case GAME_OVER:  g.drawString("状态:游戏结束", 320, 285); break;
		default:
			break;
		}
		
		
	}

	private void paintScore(Graphics g) {
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("得分:" + score, 320, 175);

	}

	private void paintCells(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (cells[i][j] != null) {
					cells[i][j].paint(g);
				}
			}
		}
	}

	private void paintNext(Graphics g) {
		Cell[] cells = next.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			int x = (c.col + 10) * Cell.WIDTH - 15;
			int y = (c.row + 1) * Cell.WIDTH + 6;
			g.drawImage(c.img, x, y, null);
		}

	}

	private Brick randomBrick() {
		int i = new Random().nextInt(7);
		// i = 0;
		Brick brick = null;
		switch (i) {
		case 0:
			brick = new BrickI();
			break;
		case 1:
			brick = new BrickJ();
			break;
		case 2:
			brick = new BrickL();
			break;
		case 3:
			brick = new BrickO();
			break;
		case 4:
			brick = new BrickS();
			break;
		case 5:
			brick = new BrickT();
			break;
		case 6:
			brick = new BrickZ();
			break;
		default:
			break;
		}
		return brick;
	}

	protected void rotateCurrent() {
		current.rotate();
		if (current.isOut() || touchCheck()) {
			current.rotateBack();
		}
	}

	/**
	 * 检测是否碰到了其他已经落在墙面上的砖块
	 * 
	 * @return
	 */
	private boolean touchCheck() {
		boolean touched = false;
		for (int i = 0; i < current.cells.length; i++) {
			int row = current.cells[i].row;
			int col = current.cells[i].col;
			if (row < ROWS && row >= 0 && col < COLS && col >= 0) {
				Cell c = cells[row][col];
				if (c != null) {
					touched = true;
					break;
				}
			}
		}
		return touched;
	}

}
