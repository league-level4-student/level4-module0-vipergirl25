package _03_Conways_Game_of_Life;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class WorldPanel extends JPanel implements MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private int cellsPerRow;
	private int cellSize;
	int numNeighbors = 0;
	int livingNeighbors = 0;
	private Timer timer;
	
	//1. Create a 2D array of Cells. Do not initialize it.
	Cell[][]cell;

	
	
	public WorldPanel(int w, int h, int cpr) {
		setPreferredSize(new Dimension(w, h));
		addMouseListener(this);
		timer = new Timer(500, this);
		this.cellsPerRow = cpr;
	
		//2. Calculate the cell size.
		cellSize = w/cpr;
		//3. Initialize the cell array to the appropriate size.
		cell=new Cell[cpr][cpr];
		//3. Iterate through the array and initialize each cell.
		//   Don't forget to consider the cell's dimensions when 
		//   passing in the location.
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				cell[i][j]=new Cell(i, j, cellSize);
			}
		}
		
	}
	
	public void randomizeCells() {
		//4. Iterate through each cell and randomly set each
		//   cell's isAlive memeber to true of false
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				if(new Random().nextInt(2)==1) {
					cell[i][j].isAlive = true;
				}else {
					cell[i][j].isAlive = false;
				}
				
			}
		}
		
		repaint();
	}
	
	public void clearCells() {
		//5. Iterated through the cells and set them all to dead.
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				cell[i][j].isAlive = false;
			}
		}
		repaint();
	}
	
	public void startAnimation() {
		timer.start();
	}
	
	public void stopAnimation() {
		timer.stop();
	}
	
	public void setAnimationDelay(int sp) {
		timer.setDelay(sp);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		//6. Iterate through the cells and draw them all
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				g.drawRect(cell[i][j].getX(), cell[i][j].getY(), cellSize, cellSize);
			}
		}
		
		
		// draws grid
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
	//advances world one step
	public void step() {
		//7. iterate through cells and get their neighbors
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				for (int j2 = -1; j2 < 2; j2++) {
					for (int k = -1; k < 2; k++) {
						if(cell[i][j]==cell[0][0]) {
							j2=0;
							k=0;
						}
						if(cell[i+j2][j+k].isAlive==true) {
							numNeighbors+=1;
						}
					}
				}
				cell[i][j].liveOrDie(numNeighbors);
			}
		}
		
		//8. check if each cell should live or die
	
		
		
		
		repaint();
	}
	
	//9. Complete the method.
	//   It returns an array list of the  8 or less neighbors of the 
	//   cell identified by x and y
	public int getLivingNeighbors(int x, int y){
		for (int i = 0; i < cell.length; i++) {
			for (int j = 0; j < cell.length; j++) {
				for (int j2 = -1; j2 < 2; j2++) {
					for (int k = -1; k < 2; k++) {
						if(cell[i+j2][j+k].isAlive==true) {
							livingNeighbors+=1;
						}
					}
				}
			}
		}
		return livingNeighbors;
	} 

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//10. Use e.getX() and e.getY() to determine
		//    which cell is clicked. Then toggle
		//    the isAlive variable for that cell.
		cell[e.getX()][e.getY()].liveOrDie(numNeighbors);
		
		
		
		
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		step();		
	}
}
