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


	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub
		
		// number of rows and columns
		int row = maze.sizeR;
		int column = maze.sizeC;
		
		ArrayList<Cell> tunnelCells = new ArrayList<Cell>(); 
		
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
		
	
	  while(!isAllVisited(maze, visitedCells))
	  {
		  if(!path.isEmpty()) {
				
				// use the cell that is currently on top of the stack
				Cell currentCell = (Cell) path.peek();		
				// check to see if there is an available neighbor
				boolean isThereNeighbor = false;
				// mix up the directions
				Integer[] randDirs = generateRandomDirections();
			  
				  
				if(maze.type == Maze.TUNNEL && currentCell.tunnelTo != null && visitedCells.contains(currentCell.tunnelTo) == false)
				{
					// The current cell aka the top of the stack is a tunnel
					// change the the top of the stack to the end of the tunnel
					visitedCells.add(currentCell.tunnelTo);
					path.push(currentCell.tunnelTo);
					
				}
				
			  // Examine each direction		  
			  for (int i = 0; i < randDirs.length; i++) {
				  
			  			 if(currentCell.neigh[randDirs[i]] != null && visitedCells.contains(currentCell.neigh[randDirs[i]]) == false)
					 		{
						        		 
			        		 // add the new cell to the path and mark it visited
			        		 	visitedCells.add(currentCell.neigh[randDirs[i]]);
								path.push(currentCell.neigh[randDirs[i]]);
								
								
								// removing the wall
								currentCell.wall[randDirs[i]].present = false;
													
								
								isThereNeighbor = true;
								
								  break;
				        	 
			        	 }
			     
			    	 		    	 
			        	 if(i == (randDirs.length-1) && isThereNeighbor == false ) 
			        	 {
			        		 // if you reach the last neighbor then there is no available neighbor then must backtrack
			        		 // remove the current cell from the stack so that the previous cell that was visited will be 
			        		 // checked again if there's available neighbor
			        		 path.pop();
			        		 break;
											
			        	 }
			        	 
		
			   } // end of for loop
			  
		  }
		  
	  } // every cell is visited
	  
	  		
	} // end of generateMaze()
	
	
	
	/** ISSUE **/
	/* Have the same exact problem with the one posted on discussion board
	 * 
	 * Yongli's response: "Some other students also reported this. I think what you need to do is to make sure you know how to select 
	 * the neighbour for the tunnel neighbours. Basically, the tunnel neighbour is the other side of the tunnel, and you need to access it use the . tunnelTo  
	 * attribute of the tunnel cell to find its neighbours. This is different from the normal cells. Thanks."
	 * 
	 * It needs to satisfy the isPerfect() function of tunnel
	 * 
	 * What do we need to do after we found a cell with tunnels/ (via currentCell.tunnelTo != null)
	 * 
	 * Attempt #1: Whenever a tunnel is found, go to the other side of that tunnel and continue the maze generation from here
	 * Result: random chance of generating a perfect maze, some perfect maze have walls around some cells (i.e the only way you can go to some cells is through the portal)
	 * 
	 * Attempt #2: Generate the maze like normal maze then swap the neighbors of the tunnelcell
	 * Result: If i run it on normal maze, it is perfect but if i run it on tunnels its not
	 * 
	 */
	
	
	public boolean isAllVisited(Maze m, ArrayList<Cell> aL) {
		
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
	
		System.out.println("~ALL CELLS VISITED");

		return true;
	
	}
	
	public void changeNeighOfTunnels(ArrayList<Cell> tunns, Maze maze) {
			
		for(int i = 0; i < tunns.size(); i++)
		{
			for(int j = 0; j < 6; j++)
			{
				Cell curr =  (Cell) tunns.get(i);
				Cell[] temp = new Cell[6];

				
			// curr cant be null but their neighbors can be null
				
				temp[j] = curr.neigh[j];
				
				// update the neigh of the curr cell
				
								
				curr.neigh[j] = curr.tunnelTo.neigh[j];
				
				curr.tunnelTo.neigh[j] = temp[j];
						
		
							
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
