package tetris;

import java.awt.Graphics;

public abstract class Brick {
	Cell[] cells = new Cell[4];
	Cell centerCell;// 砖块的旋转中心,默认为cells[0]
	State[] states;
	int speed = 20;
	int currentStateIndex = 1001;

	public boolean isOut() {
		for (int i = 0; i < cells.length; i++) {
			if (cells[i].isOut()) {
				return true;
			}
		}
		return false;
	}

	public void moveDown() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].moveDown();
		}
	}
	
	public void moveUp() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].moveUp();
		}
	}

	public void moveLeft() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].moveLeft();
		}
	}

	public void moveRight() {
		for (int i = 0; i < cells.length; i++) {
			cells[i].moveRight();
		}
	}

	public void paint(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			cells[i].paint(g);
		}
	}

	public void rotate() {
		currentStateIndex++;
		updateState();
	}

	public void rotateBack() {
		currentStateIndex--;
		updateState();
	}

	public void step() {
		if (GamePanel.counter % speed == 0) {
			moveDown();
		}
	}

	public void updateState() {
		State curState = states[currentStateIndex % states.length];
		cells[1].row = centerCell.row + curState.row1;
		cells[1].col = centerCell.col + curState.col1;
		cells[2].row = centerCell.row + curState.row2;
		cells[2].col = centerCell.col + curState.col2;
		cells[3].row = centerCell.row + curState.row3;
		cells[3].col = centerCell.col + curState.col3;
	}
}
