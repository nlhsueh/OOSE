package abstractfactory;

public class MazeGameDemo {

	public static void main(String[] args) {
		MazeGame mg = new MazeGame();
		mg.createMaze();

		MazeGame mg2 = new GhostMazeGame();
		mg2.createMaze();
	}

}

class MazeGame {

	Maze maze;
	Door door;
	Wall wall;

	public void createMaze() {
		maze = makeMaze();
		door = makeDoor();
		wall = makeWall();
	}

        //factory method
	protected Wall makeWall() {
		return new Wall();
	}

        //factory method
	protected Door makeDoor() {
		return new Door();
	}

        //factory method
	protected Maze makeMaze() {
		return new Maze();
	}
}

class GhostMazeGame extends MazeGame {
	//factory method
	protected Wall makeWall() {
		return new GhostWall();
	}

	//factory method
	protected Door makeDoor() {
		// TODO Auto-generated method stub
		return new GhostDoor();
	}

	//factory method
	protected Maze makeMaze() {
		// TODO Auto-generated method stub
		return new GhostMaze();
	}

}

class Maze {
	public Maze() {
		System.out.println(this);

	}
}

class Wall {
	public Wall() {
		System.out.println(this);

	}
}

class Door {
	public Door() {
		System.out.println(this);
	}
}

class GhostMaze extends Maze {

}

class GhostDoor extends Door {

}

class GhostWall extends Wall {

}
