package mazeSolver;

/** Author -  Adrian Nacor **/

import java.util.ArrayList;
import java.util.Stack;

import maze.Cell;
import maze.Maze;

/**
 * Implements WallFollowerSolver
 */

public class WallFollowerSolver implements MazeSolver {
	
		
	Cell curr;
	Cell end;
	
	Stack<Cell> path = new Stack<Cell>();
	ArrayList<Cell> explored = new ArrayList<Cell>();
		
	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub
		// use drawFtPrt(Cell cell)
		
		 curr = maze.entrance;
		 end = maze.exit;
		
		// add the starting cell to the explored arraylist and pushedit on the stack
		explored.add(curr);
		path.push(curr);
		
		maze.drawFtPrt(curr);
		
		
		while(isSolved() == false)
		{
			
			if(!path.isEmpty()) {	
			Cell current = (Cell) path.peek();
			
			
			// Follows the left wall
			// if the maze type is hex it has more directions
			if(maze.type == Maze.HEX)
			{
				
				// checks if the cell is not null, there is not a wall present and its not explored by the solver yet
				if(current.neigh[Maze.WEST] != null && current.wall[Maze.WEST].present == false && explored.contains(current.neigh[Maze.WEST]) == false)
				{
					// add to path
					path.push(current.neigh[Maze.WEST]);
					//add to exploerd
					explored.add(current.neigh[Maze.WEST]);
					//draw
					maze.drawFtPrt(current.neigh[Maze.WEST]);
					
				} else if (current.neigh[Maze.NORTHWEST] != null && current.wall[Maze.NORTHWEST].present == false  && explored.contains(current.neigh[Maze.NORTHWEST]) == false)
				{
					
					path.push(current.neigh[Maze.NORTHWEST]);
					explored.add(current.neigh[Maze.NORTHWEST]);
					maze.drawFtPrt(current.neigh[Maze.NORTHWEST]);
					
				} else if(current.neigh[Maze.NORTHEAST] != null && current.wall[Maze.NORTHEAST].present == false  && explored.contains(current.neigh[Maze.NORTHEAST]) == false) {
					
					path.push(current.neigh[Maze.NORTHEAST]);
					explored.add(current.neigh[Maze.NORTHEAST]);
					maze.drawFtPrt(current.neigh[Maze.NORTHEAST]);
					
				} else if(current.neigh[Maze.EAST] != null && current.wall[Maze.EAST].present == false  && explored.contains(current.neigh[Maze.EAST]) == false) {
					
					path.push(current.neigh[Maze.EAST]);
					explored.add(current.neigh[Maze.EAST]);
					maze.drawFtPrt(current.neigh[Maze.EAST]);
					
				} else if(current.neigh[Maze.SOUTHEAST] != null && current.wall[Maze.SOUTHEAST].present == false  && explored.contains(current.neigh[Maze.SOUTHEAST]) == false) {
					
					path.push(current.neigh[Maze.SOUTHEAST]);
					explored.add(current.neigh[Maze.SOUTHEAST]);
					maze.drawFtPrt(current.neigh[Maze.SOUTHEAST]);
					
				} else if(current.neigh[Maze.SOUTHWEST] != null && current.wall[Maze.SOUTHWEST].present == false  && explored.contains(current.neigh[Maze.SOUTHWEST]) == false) {
					
					path.push(current.neigh[Maze.SOUTHWEST]);
					explored.add(current.neigh[Maze.SOUTHWEST]);
					maze.drawFtPrt(current.neigh[Maze.SOUTHWEST]);
					
				} else {
					
					path.pop();
					
				}
			} 
			else 
			{
		
				if(current.neigh[Maze.WEST] != null && current.wall[Maze.WEST].present == false && explored.contains(current.neigh[Maze.WEST]) == false)
				{
					path.push(current.neigh[Maze.WEST]);
					explored.add(current.neigh[Maze.WEST]);
					maze.drawFtPrt(current.neigh[Maze.WEST]);
					
					// Checks if the cell is a tunnel
					// If it is then 'jump' to the other side
					if(current.neigh[Maze.WEST].tunnelTo != null)
					{
						path.push(current.neigh[Maze.WEST].tunnelTo);
						explored.add(current.neigh[Maze.WEST].tunnelTo);
						maze.drawFtPrt(current.neigh[Maze.WEST].tunnelTo);
					}
				
				} else if (current.neigh[Maze.NORTH] != null && current.wall[Maze.NORTH].present == false  && explored.contains(current.neigh[Maze.NORTH]) == false)
				{
				
					path.push(current.neigh[Maze.NORTH]);
					explored.add(current.neigh[Maze.NORTH]);
					maze.drawFtPrt(current.neigh[Maze.NORTH]);
					
					
					if(current.neigh[Maze.NORTH].tunnelTo != null)
					{
						path.push(current.neigh[Maze.NORTH].tunnelTo);
						explored.add(current.neigh[Maze.NORTH].tunnelTo);
						maze.drawFtPrt(current.neigh[Maze.NORTH].tunnelTo);
					}
				
				} else if(current.neigh[Maze.EAST] != null && current.wall[Maze.EAST].present == false  && explored.contains(current.neigh[Maze.EAST]) == false) {
				
					path.push(current.neigh[Maze.EAST]);
					explored.add(current.neigh[Maze.EAST]);
					maze.drawFtPrt(current.neigh[Maze.EAST]);
					
					if(current.neigh[Maze.EAST].tunnelTo != null)
					{
						path.push(current.neigh[Maze.EAST].tunnelTo);
						explored.add(current.neigh[Maze.EAST].tunnelTo);
						maze.drawFtPrt(current.neigh[Maze.EAST].tunnelTo);
					}
				
					} else if(current.neigh[Maze.SOUTH] != null && current.wall[Maze.SOUTH].present == false  && explored.contains(current.neigh[Maze.SOUTH]) == false) {
				
					path.push(current.neigh[Maze.SOUTH]);
					explored.add(current.neigh[Maze.SOUTH]);
					maze.drawFtPrt(current.neigh[Maze.SOUTH]);
					
					if(current.neigh[Maze.SOUTH].tunnelTo != null)
					{
						path.push(current.neigh[Maze.SOUTH].tunnelTo);
						explored.add(current.neigh[Maze.SOUTH].tunnelTo);
						maze.drawFtPrt(current.neigh[Maze.SOUTH].tunnelTo);
					}
				
					} else {
				
						path.pop();
				
					}
				
			
				}
			}
		}
		
        
	} // end of solveMaze()
	


	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stub
		
		// Maze is considered solved if the 'path' contains the exit of the maze
				
		if(path.contains(end))
		{
			return true;
		}
		
			
		return false;
	} // end if isSolved()
	
	

	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		
		return 0;
	} // end of cellsExplored()

} // end of class WallFollowerSolver
