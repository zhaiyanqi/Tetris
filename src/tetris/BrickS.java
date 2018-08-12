package tetris;

public class BrickS extends Brick{
	public BrickS() {
		cells[0] = new Cell(0, 4, Main.imgS);
		cells[1] = new Cell(0, 5, Main.imgS);
		cells[2] = new Cell(1, 3, Main.imgS);
		cells[3] = new Cell(1, 4, Main.imgS);
		centerCell = cells[0];
		states = new State[]{
			new State(0,1, 1,-1, 1,0 ),
			new State(-1,0, 1,1, 0,1 )};
	}
}
