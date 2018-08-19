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
		selectNextPath(uncheckedCells.get(randGen.nextInt(uncheckedCells.size())));
		
		return maze;
	}

	//6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		//A. mark cell as visited
			currentCell.setBeenVisited(true);
		//B. check for unvisited neighbors using the cell
		    getUnvisitedNeighbors(currentCell);
		//C. if has unvisited neighbors,
		if(getUnvisitedNeighbors(currentCell).size()>0) {
			Cell random = uncheckedCells.get(randGen.nextInt(getUnvisitedNeighbors(currentCell).size()));
			uncheckedCells.push(random);
			removeWalls(currentCell, random);currentCell = random; random.setBeenVisited(true);
		}
			//C1. select one at random.
			
			//C2. push it to the stack
		
			//C3. remove the wall between the two cells

			//C4. make the new cell the current cell and mark it as visited
			
			
		//D. if all neighbors are visited
		if(getUnvisitedNeighbors(currentCell).size()==0) {
			//D1. if the stack is not empty
				if(uncheckedCells.size()>0) {
				// D1a. pop a cell from the stack
					currentCell  = uncheckedCells.pop();
				// D1b. make that the current cell
					
				}	
		}
			
		
	}

	//7. Complete the remove walls method.
	//   This method will check if c1 and c2 are adjacent.
	//   If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		//HELP ME!!! HOW DO I DO THIS!!!
	}
	
	//8. Complete the getUnvisitedNeighbors method
	//   Any unvisited neighbor of the passed in cell gets added
	//   to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		ArrayList<Cell>cell = new ArrayList<Cell>();
		Cell thing = maze.getCell(c.getX()+100, c.getY());
		if(thing.hasBeenVisited()==false) {
			cell.add(thing);
		}
		thing = maze.getCell(c.getX()-100, c.getY());
		if(thing.hasBeenVisited()==false) {
			cell.add(thing);
		}
		thing = maze.getCell(c.getX(), c.getY()+100);
		if(thing.hasBeenVisited()==false) {
			cell.add(thing);
		}
		thing = maze.getCell(c.getX(), c.getY()-100);
		if(thing.hasBeenVisited()==false) {
			cell.add(thing);
		}
		
		return cell;
	}
}