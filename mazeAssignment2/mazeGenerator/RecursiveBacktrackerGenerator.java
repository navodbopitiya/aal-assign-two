package mazeGenerator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

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
		
		
		// access all of the current cell's neighbor through neigh 
		// if the cell is not equal to null and it is not yet visited then add it into stack and mark it as visited
		// remove the wall
		// update the current row & current column value
		
		for(int i = 0; i < maze.map[currentRow][currentColumn].neigh.length; i++ ) {
			
			if(maze.map[currentRow][currentColumn].neigh[i] != null &&  isVisited.contains(maze.map[currentRow][currentColumn].neigh[i]) == false) {
				
				isVisited.add(maze.map[currentRow + maze.deltaR[i]][currentColumn + maze.deltaC[i]]);
				path.push(maze.map[currentRow + maze.deltaR[i]][currentColumn + maze.deltaC[i]]);
				
				// removing the wall
				maze.map[currentRow][currentColumn].wall[i].present = false;
				
				// update current row & current column value here
				
			} 
			
			// BACKTRACK happens when a cell goes through every possible neighbor and all of them are marked visited
			// path.pop() then try the for loop again to check every possible neighbor
			// must do this RECURSIVELY
				
		}
		


	} // end of generateMaze()
	
		
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

	
} // end of class RecursiveBacktrackerGenerator
