package tetris;

public class BrickO extends Brick{
	public BrickO() {
		cells[0] = new Cell(0, 4, Main.imgO);
		cells[1] = new Cell(0, 5, Main.imgO);
		cells[2] = new Cell(1, 4, Main.imgO);
		cells[3] = new Cell(1, 5, Main.imgO);
		centerCell = cells[0];
		states = new State[]{
				new State(0,1, 1,0, 1,1 ),
				new State(0,1, 1,0, 1,1 )};
	}
}
