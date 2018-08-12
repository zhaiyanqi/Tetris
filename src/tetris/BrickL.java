package tetris;

public class BrickL extends Brick{
	public BrickL() {
		cells[0] = new Cell(0, 4, Main.imgL);
		cells[1] = new Cell(0, 3, Main.imgL);
		cells[2] = new Cell(0, 5, Main.imgL);
		cells[3] = new Cell(1, 3, Main.imgL);
		centerCell = cells[0];
		states = new State[]{
				new State(0,-1, 0,1, 1,-1 ),
				new State(-1,0, 1,0, -1,-1),
				new State(0,1, 0,-1, -1,1),
				new State(1,0, -1,0, 1,1)};	
	}
}
