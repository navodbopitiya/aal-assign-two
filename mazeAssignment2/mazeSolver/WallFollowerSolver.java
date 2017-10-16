package mazeSolver;

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
	
	Stack path = new Stack();
	ArrayList explored = new ArrayList();
		
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
			
			if(maze.type == maze.HEX)
			{
				if(current.neigh[maze.WEST] != null && current.wall[maze.WEST].present == false && explored.contains(current.neigh[maze.WEST]) == false)
				{
					path.push(current.neigh[maze.WEST]);
					explored.add(current.neigh[maze.WEST]);
					maze.drawFtPrt(current.neigh[maze.WEST]);
					
				} else if (current.neigh[maze.NORTHWEST] != null && current.wall[maze.NORTHWEST].present == false  && explored.contains(current.neigh[maze.NORTHWEST]) == false)
				{
					
					path.push(current.neigh[maze.NORTHWEST]);
					explored.add(current.neigh[maze.NORTHWEST]);
					maze.drawFtPrt(current.neigh[maze.NORTHWEST]);
					
				} else if(current.neigh[maze.NORTHEAST] != null && current.wall[maze.NORTHEAST].present == false  && explored.contains(current.neigh[maze.NORTHEAST]) == false) {
					
					path.push(current.neigh[maze.NORTHEAST]);
					explored.add(current.neigh[maze.NORTHEAST]);
					maze.drawFtPrt(current.neigh[maze.NORTHEAST]);
					
				} else if(current.neigh[maze.EAST] != null && current.wall[maze.EAST].present == false  && explored.contains(current.neigh[maze.EAST]) == false) {
					
					path.push(current.neigh[maze.EAST]);
					explored.add(current.neigh[maze.EAST]);
					maze.drawFtPrt(current.neigh[maze.EAST]);
					
				} else if(current.neigh[maze.SOUTHEAST] != null && current.wall[maze.SOUTHEAST].present == false  && explored.contains(current.neigh[maze.SOUTHEAST]) == false) {
					
					path.push(current.neigh[maze.SOUTHEAST]);
					explored.add(current.neigh[maze.SOUTHEAST]);
					maze.drawFtPrt(current.neigh[maze.SOUTHEAST]);
					
				} else if(current.neigh[maze.SOUTHWEST] != null && current.wall[maze.SOUTHWEST].present == false  && explored.contains(current.neigh[maze.SOUTHWEST]) == false) {
					
					path.push(current.neigh[maze.SOUTHWEST]);
					explored.add(current.neigh[maze.SOUTHWEST]);
					maze.drawFtPrt(current.neigh[maze.SOUTHWEST]);
					
				} else {
					
					path.pop();
					
				}
			} 
			else 
			{
			
			// Prioritizes going left of the maze
			
			// checks if the maze is not null, there isnt a wall present and its not explored by the solver yet
				if(current.neigh[maze.WEST] != null && current.wall[maze.WEST].present == false && explored.contains(current.neigh[maze.WEST]) == false)
				{
					path.push(current.neigh[maze.WEST]);
					explored.add(current.neigh[maze.WEST]);
					maze.drawFtPrt(current.neigh[maze.WEST]);
					
					if(current.neigh[maze.WEST].tunnelTo != null)
					{
						path.push(current.neigh[maze.WEST].tunnelTo);
						explored.add(current.neigh[maze.WEST].tunnelTo);
						maze.drawFtPrt(current.neigh[maze.WEST].tunnelTo);
					}
				
				} else if (current.neigh[maze.NORTH] != null && current.wall[maze.NORTH].present == false  && explored.contains(current.neigh[maze.NORTH]) == false)
				{
				
					path.push(current.neigh[maze.NORTH]);
					explored.add(current.neigh[maze.NORTH]);
					maze.drawFtPrt(current.neigh[maze.NORTH]);
					
					
					if(current.neigh[maze.NORTH].tunnelTo != null)
					{
						path.push(current.neigh[maze.NORTH].tunnelTo);
						explored.add(current.neigh[maze.NORTH].tunnelTo);
						maze.drawFtPrt(current.neigh[maze.NORTH].tunnelTo);
					}
				
				} else if(current.neigh[maze.EAST] != null && current.wall[maze.EAST].present == false  && explored.contains(current.neigh[maze.EAST]) == false) {
				
					path.push(current.neigh[maze.EAST]);
					explored.add(current.neigh[maze.EAST]);
					maze.drawFtPrt(current.neigh[maze.EAST]);
					
					if(current.neigh[maze.EAST].tunnelTo != null)
					{
						path.push(current.neigh[maze.EAST].tunnelTo);
						explored.add(current.neigh[maze.EAST].tunnelTo);
						maze.drawFtPrt(current.neigh[maze.EAST].tunnelTo);
					}
				
					} else if(current.neigh[maze.SOUTH] != null && current.wall[maze.SOUTH].present == false  && explored.contains(current.neigh[maze.SOUTH]) == false) {
				
					path.push(current.neigh[maze.SOUTH]);
					explored.add(current.neigh[maze.SOUTH]);
					maze.drawFtPrt(current.neigh[maze.SOUTH]);
					
					if(current.neigh[maze.SOUTH].tunnelTo != null)
					{
						path.push(current.neigh[maze.SOUTH].tunnelTo);
						explored.add(current.neigh[maze.SOUTH].tunnelTo);
						maze.drawFtPrt(current.neigh[maze.SOUTH].tunnelTo);
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
