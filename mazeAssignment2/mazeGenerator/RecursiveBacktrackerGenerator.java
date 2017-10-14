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
		
		//Keeps track of all the visited cells and the current path
		ArrayList visitedCells = new ArrayList(); 
		Stack path = new Stack();
		
		// pick a random starting cell
		int currentRow = generateRow(row);
		int currentColumn = generateColumn(column);
		
		// mark the starting cell as visited
		// insert into stack
		
		// ensures that the starting cell is valid (mostly needed for hex)
		
		while(maze.map[currentRow][currentColumn] == null)
		{
			currentRow = generateRow(row);
			currentColumn = generateColumn(column);
		}

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
			
					
			  Integer[] randDirs = generateRandomDirections();
			  
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
					
								
							/*****PROBLEM!
							 * I can access the neighbors of the other side of the tunnel through: currentCell.neigh[randDirs[i]].tunnelTo.neigh[randDirs[i]]
							 * 
							 * I dont even know what they want to happen
							 * what happens when you find a tunnel? do you go to the other end then continue to generate the maze from there?
							 * 
							 * 
							 * FROM DISCUSSION BOARD: " I think what you need to do is to make sure you know how to select the neighbour for the tunnel neighbours.
							 *  Basically, the tunnel neighbor is the other side of the tunnel, and you need to access it use the .tunnelTo attribute 
							 *  of the tunnel cell to find its neighbors." Yongli posted this a couple of times
							 * 
							 * I used currentCell.neigh[randDirs[i]].tunnelTo to check if the cell is a tunnel
							 * 
							 * My attempt was when you find a tunnel, go to the other side then add that cellto visited and push it into the path
							 * basically, doing the DFS from there.
							 * 
							 * Result	  : sometimes it is perfect, sometimes its not
							 *            : sometimes it is perfect BUT there are still WALLS around the tunnel
							 *            
							 * 
							 * *****/
								
							  if(currentCell.neigh[randDirs[i]].tunnelTo != null)
								  {
								  
								  if(currentCell.neigh[randDirs[i]].tunnelTo.neigh[randDirs[i]] != null)
								  {
									 // To access the neighbors of the other side of the tunnel
								  }
									 
								  	  visitedCells.add(currentCell.neigh[randDirs[i]].tunnelTo.neigh[randDirs[i]]);
									  path.push(currentCell.neigh[randDirs[i]].tunnelTo.neigh[randDirs[i]]);
									
									 // currentCell.neigh[randDirs[i]].wall[randDirs[i]].present = false; 
									 
								  } 
								
							
					
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
		
			   }
		  }
	  }
		  
	
	} // end of generateMaze()
	
	
	public boolean isAllVisited(Maze m, ArrayList aL) {
		
		if(m.type == m.HEX)
		{
			for (int i = 0; i < m.sizeR; i++){
				
				for (int j = (i + 1) / 2; j < m.sizeC + (i + 1) / 2; j++) {
						
						if(!aL.contains(m.map[i][j])) {
							return false;
						}
				}
			}
		} 
		else
		{
			
			for(int i = 0; i < m.sizeR; i++)
		{
			for(int j = 0; j < m.sizeC; j++)
			{
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
	      ArrayList<Integer> randoms = new ArrayList<Integer>();
	      for (int i = 0; i < 6; i++)
	           randoms.add(i);
	      Collections.shuffle(randoms);
	 
	     return randoms.toArray(new Integer[6]);
	 }

	
} // end of class RecursiveBacktrackerGenerator
