package tetris;

public class BrickI extends Brick{
	
	public BrickI() {
		cells[0] = new Cell(0, 4, Main.imgI);
		cells[1] = new Cell(0, 3, Main.imgI);
		cells[2] = new Cell(0, 5, Main.imgI);
		cells[3] = new Cell(0, 6, Main.imgI);
		centerCell = cells[0];
		states = new State[]{
				new State(0, 1, 0, -1, 0, -2),
				new State(-1, 0, 1, 0, 2, 0)};
	}
	
}
