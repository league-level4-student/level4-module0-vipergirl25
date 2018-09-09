package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class MazeMaker{
	
	private static int width;
	private static int height;
	
	private static Maze maze;
	
	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();
	
	
	public static Maze generateMaze(int w, int h){
		width = w;
		height = h;
		maze = new Maze(width, height);
		
		//4. select a random cell to start
		
		
		//5. call selectNextPath method with the randomly selected cell
		selectNextPath(maze.getCell(randGen.nextInt(maze.getWidth()),randGen.nextInt(maze.getHeight())));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
			currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		    ArrayList<Cell> delaraisgreat = getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(delaraisgreat.size()>0) {
			Cell random = uncheckedCells.get(randGen.nextInt(getUnvisitedNeighbors(currentCell).size()));
			uncheckedCells.push(random);
			removeWalls(currentCell, random);
			currentCell = random; random.setBeenVisited(true);
			//C1. select one at random.
			Cell newCell = delaraisgreat.get(randGen.nextInt((delaraisgreat.size())));
			//C2. push it to the stack
			uncheckedCells.push(newCell);
			//C3. remove the wall between the two cells
			removeWalls(currentCell, newCell);
			//C4. make the new cell the current cell and mark it as visited
			currentCell = newCell;
			selectNextPath(currentCell);
		}else {
			if(!uncheckedCells.isEmpty()) {
				currentCell = uncheckedCells.pop();
				selectNextPath(currentCell);
			}
		}
			
			
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		int x = c1.getX(); int y = c1.getY();
		int x1 = c2.getX(); int y1 = c2.getY();
		if(x==x1 && y-100==y1) {
			c1.setNorthWall(false);c2.setSouthWall(false);
		}else if(x-100==x1 && y==y1) {
			c1.setWestWall(false);c2.setEastWall(false);
		}else if(x==x1 && y+100==y1) {
			c1.setSouthWall(false);c2.setNorthWall(false);
		}else if(x+100==x1 && y==y1) {
			c1.setEastWall(false);c2.setWestWall(false);
		}
		else {
			
		}
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell>cell = new ArrayList<Cell>();
		if(x>0 && !maze.getCell(x-1, y).hasBeenVisited()) {
			cell.add(maze.getCell(x-1, y));
		}
		if(y>0 && !maze.getCell(x,  y - 1).hasBeenVisited()) {
			cell.add(maze.getCell(x,  y-1));
		}
		if(x<width - 1 && !maze.getCell(x+1, y).hasBeenVisited()) {
			cell.add(maze.getCell(x + 1, y));
		}
		if(y<height - 1 && !maze.getCell(x,  y + 1).hasBeenVisited()) {
			cell.add(maze.getCell(x,  y+1));
		}

		return cell;
	}
}