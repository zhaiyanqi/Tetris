package tetris;

public class BrickJ extends Brick{
	
	
	public BrickJ() {
		cells[0] = new Cell(0, 4, Main.imgJ);
		cells[1] = new Cell(0, 3, Main.imgJ);
		cells[2] = new Cell(0, 5, Main.imgJ);
		cells[3] = new Cell(1, 5, Main.imgJ);
		centerCell = cells[0];
		states = new State[]{
				new State(0,-1, 0,1, 1,1),
				new State(-1,0, 1,0, 1,-1),
				new State(0,1, 0,-1, -1,-1),
				new State(1,0, -1,0, -1,1 )};
	}
}
