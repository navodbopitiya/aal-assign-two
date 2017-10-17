package mazeGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {


	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		// number of rows and columns
		int row = maze.sizeR;
		int column = maze.sizeC;
		
		//Keeps track of all the visited cells and the current path
		ArrayList<Cell> visitedCells = new ArrayList<Cell>(); 
		Stack<Cell> path = new Stack<Cell>();
		
		// pick a random starting cell
		int currentRow = generateRow(row);
		int currentColumn = generateColumn(column);
		
				
		// ensures that the starting cell is valid (mostly needed for hex)
		while(maze.map[currentRow][currentColumn] == null)
		{
			// finds a new row and column if its not valid
			currentRow = generateRow(row);
			currentColumn = generateColumn(column);
		}
		
		// mark the starting cell as visited
		// insert into stack
		visitedCells.add(maze.map[currentRow][currentColumn]);
		path.push(maze.map[currentRow][currentColumn]);
		
	   // PICK a random unvisited neighbouring cell and MOVE to that neighbor including removing the wall between the cell
			
	  while(!isAllVisited(maze, visitedCells)) {
		 
		  if(!path.isEmpty()) {
				
				// use the cell that is currently on top of the stack
				Cell currentCell = (Cell) path.peek();		
				// check to see if there is an available neighbor
				boolean isThereNeighbor = false;
				// mix up the directions
				Integer[] randDirs = generateRandomDirections();
			  				  
					if(maze.type == Maze.TUNNEL && currentCell.tunnelTo != null && visitedCells.contains(currentCell.tunnelTo) == false) {
						
						// The current cell aka the top of the stack is a tunnel
						// change the the top of the stack to the end of the tunnel
						visitedCells.add(currentCell.tunnelTo);
						path.push(currentCell.tunnelTo);
						currentCell = (Cell) path.peek();
					
					}
				
					// Examine each direction		  
					for (int i = 0; i < randDirs.length; i++) {
				  
			  			 if(currentCell.neigh[randDirs[i]] != null && visitedCells.contains(currentCell.neigh[randDirs[i]]) == false) {
						        		 
			  				 	// add the new cell to the path and mark it visited
			        		 	visitedCells.add(currentCell.neigh[randDirs[i]]);
								path.push(currentCell.neigh[randDirs[i]]);
								
								
								// removing the wall
								currentCell.wall[randDirs[i]].present = false;
													
								
								isThereNeighbor = true;
								
								  break;
				        	 
			        	 }
			     
			    	 		    	 
			        	 if(i == (randDirs.length-1) && isThereNeighbor == false ) {
			        		 // if you reach the last neighbor then there is no available neighbor then must backtrack
			        		 // remove the current cell from the stack so that the previous cell that was visited will be 
			        		 // checked again if there's available neighbor
			        		 path.pop();
			        		 break;
											
			        	 }
			        	 
		
			   } // end of for loop
			  
		  }
		  
	  } 
	  
	  		
	} // end of generateMaze()
	
	
	
	public boolean isAllVisited(Maze m, ArrayList<Cell> aL) {
		
		// goes through all cells to check if all have been visited
		
		if(m.type == Maze.HEX) {
			for (int i = 0; i < m.sizeR; i++) {
				for (int j = (i + 1) / 2; j < m.sizeC + (i + 1) / 2; j++) {
					if(!aL.contains(m.map[i][j])) {
							return false;
					}
				}
			}
		} else {
			for(int i = 0; i < m.sizeR; i++) {
				for(int j = 0; j < m.sizeC; j++) {
					if(!aL.contains(m.map[i][j])) {
							return false;
					}
				}
			}
		}

		return true;
	
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
	  // randomises the order of the direcions
		ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 6; i++)
	           randoms.add(i);
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[6]);
	 }

	
} // end of class RecursiveBacktrackerGenerator
