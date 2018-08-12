package tetris;

public class BrickZ extends Brick{
	public BrickZ() {
		cells[0] = new Cell(1, 4, Main.imgZ);
		cells[1] = new Cell(0, 3, Main.imgZ);
		cells[2] = new Cell(0, 4, Main.imgZ);
		cells[3] = new Cell(1, 5, Main.imgZ);
		centerCell = cells[0];
		states = new State[]{
				new State(-1, -1, -1, 0, 0, 1 ),
				new State(-1, 1, 0, 1, 1, 0 )};
	}
}
