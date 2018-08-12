package tetris;

/**
 * 表示砖块状态的类
 * 
 * @author zhaiy
 *
 */
public class State {
	int col1, row1, col2, row2, col3, row3, col4, row4;
	public State(int col1, int row1, int col2, int row2, 
				 int col3, int row3) {
		this.col1 = col1;
		this.col2 = col2;
		this.col3 = col3;
		this.row1 = row1;
		this.row2 = row2;
		this.row3 = row3;
	}
}
