package mazeGenerator;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	/** NOT YET WORKING W/ TUNNELS! **/
	/** BASED ON DISCUSSION BOARD: 
	 * Well, you need to create a perfect maze, then add the tunnel/s - 
	 * so they shouldn't disrupt anything. If the maze is not perfect, you may have some other bug.
	 * BUT, when the tunnel maze is initialized then IT WILL include the tunnels
	 */
	
	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		// number of rows and columns
		int row = maze.sizeR;
		int column = maze.sizeC;
		
		//Keeps track of all the visited cells and the current path
		ArrayList isVisited = new ArrayList(); 
		Stack path = new Stack();
		
		// pick a random starting cell
		int currentRow = generateRow(row);
		int currentColumn = generateColumn(column);
		
		// mark the starting cell as visited
		// insert into stack

		isVisited.add(maze.map[currentRow][currentColumn]);
		path.push(maze.map[currentRow][currentColumn]);
		
	// PICK a random unvisited neighbouring cell and MOVE to that neighbor including removing the wall between the cell
		
		recursion(maze, path, isVisited);
		

		
	
	} // end of generateMaze()
	
			
	public void recursion(Maze maze, Stack path, ArrayList isVisited) {
				
		if(!path.isEmpty()) {
			
			// use the cell that is currently on top of the stack
			Cell currentCell = (Cell) path.peek();		
		
			// check to see if there is an available neighbor
				boolean isThereNeighbor = false;
		
		  Integer[] randDirs = generateRandomDirections();
		  
		  // Examine each direction
		     for (int i = 0; i < randDirs.length; i++) {
		    
		        	 if(currentCell.neigh[randDirs[i]] != null && isVisited.contains(currentCell.neigh[randDirs[i]]) == false)
		        	 {
		        		 // add the new cell to the path and mark it visited
		        		 	isVisited.add(currentCell.neigh[randDirs[i]]);
							path.push(currentCell.neigh[randDirs[i]]);
			
							
							// removing the wall
							currentCell.wall[randDirs[i]].present = false;
			
							
							isThereNeighbor = true;
			
							recursion(maze,path,isVisited);
			        	 
		        	 }
		        	
		    	 		    	 
		 		if(i == (randDirs.length-1) && isThereNeighbor == false ) {
					// if you reach the last neighbor then there is no available neighbor then must backtrack
		 			// remove the current cell from the stack so that the previous cell that was visited will be 
		 			// checked again if there's available neighbor
					path.pop();
					recursion(maze,path,isVisited);
											
				}
	
		   }
		}
		
		
		
		
		
}
	public int generateRow(int maxRow) {
		
		Random random = new Random();
				
		int row = random.nextInt(maxRow);
					
		return row;
		
	}
	
	public int generateColumn(int maxColumn) {
		
		Random random = new Random();
		
		int column = random.nextInt(maxColumn);
					
		return column;
		
	}
	
	public Integer[] generateRandomDirections() {
	      ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 6; i++)
	           randoms.add(i);
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[6]);
	 }

	
} // end of class RecursiveBacktrackerGenerator
