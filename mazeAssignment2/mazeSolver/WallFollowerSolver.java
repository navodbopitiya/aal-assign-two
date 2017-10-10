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
			
			
			/** WORKS WITH NORMAL MAZES **/
			/** BUGGY WITH HEX MAZES, I THINK IT'S MOST LIKELY SOMETHING TO DO WITH THE DIRECTIONS **/
			
			// Prioritizes going left of the maze
			
			// checks if the maze is not null, there isnt a wall present and its not explored by the solver yet
			if(current.neigh[maze.WEST] != null && current.wall[maze.WEST].present == false && explored.contains(current.neigh[maze.WEST]) == false)
			{
				path.push(current.neigh[maze.WEST]);
				explored.add(current.neigh[maze.WEST]);
				maze.drawFtPrt(current.neigh[maze.WEST]);
				
			} else if (current.neigh[maze.NORTH] != null && current.wall[maze.NORTH].present == false  && explored.contains(current.neigh[maze.NORTH]) == false)
			{
				
				path.push(current.neigh[maze.NORTH]);
				explored.add(current.neigh[maze.NORTH]);
				maze.drawFtPrt(current.neigh[maze.NORTH]);
				
			} else if(current.neigh[maze.EAST] != null && current.wall[maze.EAST].present == false  && explored.contains(current.neigh[maze.EAST]) == false) {
				
				path.push(current.neigh[maze.EAST]);
				explored.add(current.neigh[maze.EAST]);
				maze.drawFtPrt(current.neigh[maze.EAST]);
				
			} else if(current.neigh[maze.SOUTH] != null && current.wall[maze.SOUTH].present == false  && explored.contains(current.neigh[maze.SOUTH]) == false) {
				
				path.push(current.neigh[maze.SOUTH]);
				explored.add(current.neigh[maze.SOUTH]);
				maze.drawFtPrt(current.neigh[maze.SOUTH]);
				
			} else {
				
				path.pop();
				
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
