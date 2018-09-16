package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start

		// 5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(randGen.nextInt(maze.getWidth()), randGen.nextInt(maze.getHeight())));

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		ArrayList<Cell> delaraisgreat = getUnvisitedNeighbors(currentCell);
		// C. if has unvisited neighbors,
		if (delaraisgreat.size() > 0) {
			// C1. select one at random.
			Cell newCell = delaraisgreat.get(randGen.nextInt((delaraisgreat.size())));
			// C2. push it to the stack
			uncheckedCells.push(newCell);
			// C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			// C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			currentCell.setBeenVisited(true);
			selectNextPath(currentCell);
		} else {
			if (!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		if(c1.getX()==c2.getX()) {
			if(c1.getY() > c2.getY()) {
				c1.setNorthWall(false);
				c2.setSouthWall(false);
			}else {
				c2.setNorthWall(false);
				c1.setSouthWall(false);
			}
		} else {
			if( c1.getX()>c2.getX()) {
				c1.setWestWall(false);
				c2.setEastWall(false);
			} else {
				c2.setWestWall(false);
				c1.setEastWall(false);
			}
		}

	
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> cell = new ArrayList<Cell>();
		if (x > 0 && !maze.getCell(x - 1, y).hasBeenVisited()) {
			cell.add(maze.getCell(x - 1, y));
		}
		if (y > 0 && !maze.getCell(x, y - 1).hasBeenVisited()) {
			cell.add(maze.getCell(x, y - 1));
		}
		if (x < width - 1 && !maze.getCell(x + 1, y).hasBeenVisited()) {
			cell.add(maze.getCell(x + 1, y));
		}
		if (y < height - 1 && !maze.getCell(x, y + 1).hasBeenVisited()) {
			cell.add(maze.getCell(x, y + 1));
		}

		return cell;
	}
}