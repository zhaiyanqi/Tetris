package tetris;

public class BrickT extends Brick{
	public BrickT() {
		cells[0] = new Cell(0, 4, Main.imgT);
		cells[1] = new Cell(0, 3, Main.imgT);
		cells[2] = new Cell(0, 5, Main.imgT);
		cells[3] = new Cell(1, 4, Main.imgT);
		centerCell = cells[0];
		states = new State[]{
				new State(0,-1, 0,1, 1, 0),
				new State(-1,0, 1,0, 0,-1),
				new State(0,1,  0,-1, -1,0),
				new State(1,0, -1,0, 0,1)};
	}
}
