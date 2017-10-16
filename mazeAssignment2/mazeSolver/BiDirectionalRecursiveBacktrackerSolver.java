package mazeSolver;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import maze.Cell;
import maze.Maze;
=======
import java.util.Random;
import java.util.Stack;

import maze.*;
>>>>>>> branch 'master' of https://github.com/rmit-s3617221-navod-bopitiya/aal-assign-two.git

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {
<<<<<<< HEAD
	
=======
	Random random = new Random();

>>>>>>> branch 'master' of https://github.com/rmit-s3617221-navod-bopitiya/aal-assign-two.git
	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		
	}
		
=======

		Cell entranceCellSolver = maze.entrance;
		Cell exitCellSolver =  maze.exit;

		Stack<Cell> entrancePath = new Stack<Cell>();
		Stack<Cell> exitPath = new Stack<Cell>();

		boolean entranceVisited[][] = new boolean[maze.sizeR][maze.sizeC];
		boolean exitVisited[][] = new boolean[maze.sizeR][maze.sizeC];
		boolean isSolved = false;

		while(isSolved == false){
			//Solve from both the entrance and exit step by step
			entranceCellSolver = solverStep(entranceCellSolver, entrancePath, entranceVisited, maze);
			exitCellSolver = solverStep(exitCellSolver, exitPath, exitVisited, maze);
			
			//Check if the entranceCellSolver or the exitCellSolver has visited their respective ends of the maze
			if(entranceCellSolver == maze.exit || exitCellSolver == maze.entrance ){
				isSolved = true;
			}
			
			//Check if the exit path has visited the entranceCellSolver Cell
			if(exitPath.contains(entranceCellSolver)){
				isSolved = true;
			}
			//check if the entrance path has visited the exitCellSolver cell
			if(entrancePath.contains(exitCellSolver)){
				isSolved = true;
			}
			
			//If any of the above conditions return true, the loop stops since a path has been found.

		}



	} // end of solveMaze()


>>>>>>> branch 'master' of https://github.com/rmit-s3617221-navod-bopitiya/aal-assign-two.git
	@Override
	public boolean isSolved() {
<<<<<<< HEAD
		return false;
		// TODO Auto-generated method stub
	
=======
		// TODO Auto-generated method stud
		return true;

>>>>>>> branch 'master' of https://github.com/rmit-s3617221-navod-bopitiya/aal-assign-two.git
	} // end if isSolved()


	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return 0;
	} // end of cellsExplored()
<<<<<<< HEAD
	
	
=======

	Cell solverStep(Cell cellSolver, Stack<Cell> path, boolean[][] visitedCells, Maze maze){
		//Get possible neighbour cells to visist
		ArrayList<Cell> unvisitedNeighbours = getUnvisitedNeighbours(cellSolver, visitedCells, maze);

		//If current cell is not set as visited, set it to visited.
		if(!visitedCells[cellSolver.r][cellSolver.c]){
			visitedCells[cellSolver.r][cellSolver.c] = true;
			maze.drawFtPrt(cellSolver);
		}
		

		//If there are unvisited neighbours, !unvisitedNeighbours.isEmpty()
		if(!unvisitedNeighbours.isEmpty()){
			path.push(cellSolver);
			cellSolver = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
			maze.drawFtPrt(cellSolver);
		}else{
		//If there aren't any unvisited neighbours, then backtrack to the last cell.
			cellSolver = path.pop();

		}

		return cellSolver;
	}//end of solverStep

	ArrayList<Cell> getUnvisitedNeighbours(Cell b, boolean[][] visitedCells, Maze m){
		ArrayList<Cell> neighbours = new ArrayList<Cell>();

		for(int i = 0; i < Maze.NUM_DIR; i++){

			Cell checkCell = b.neigh[i];
			if(checkCell != null){
				if(!b.wall[i].present && !visitedCells[checkCell.r][checkCell.c]){
					//If there isn't a wall present and we haven't visited it before, add it to neighbours
					neighbours.add(checkCell);
				}
			}
		}
		return neighbours;
	}//end of getUnvisitedNeighbours

>>>>>>> branch 'master' of https://github.com/rmit-s3617221-navod-bopitiya/aal-assign-two.git
} // end of class BiDirectionalRecursiveBackTrackerSolver
