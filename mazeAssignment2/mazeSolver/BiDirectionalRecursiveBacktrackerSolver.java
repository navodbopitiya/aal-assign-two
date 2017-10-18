package mazeSolver;

/** Author -  Navod Bopitiya - s3617222 **/

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import maze.*;

/**
 * Implements the BiDirectional recursive backtracking maze solving algorithm.
 */
public class BiDirectionalRecursiveBacktrackerSolver implements MazeSolver {
	Random random = new Random();

	@Override
	public void solveMaze(Maze maze) {
		// TODO Auto-generated method stub

		Cell entranceCellSolver = maze.entrance; // Cell That solves from the entrance
		Cell exitCellSolver =  maze.exit; //Cell that solves from the exit

		Stack<Cell> entrancePath = new Stack<Cell>(); //The path the entrance Cell takes
		Stack<Cell> exitPath = new Stack<Cell>(); //The path the exit Cell takes

		boolean entranceVisited[][] = null; //All visited cells of the entrance Cell
		boolean exitVisited[][] = null; //All visited cells of the exit cell

		if(maze.type == maze.HEX) {
			// adjusts the max column for hex
			entranceVisited = new boolean[maze.sizeR][(maze.sizeC+1)/2+maze.sizeC]; 
			exitVisited = new boolean[maze.sizeR][(maze.sizeC+1)/2+maze.sizeC];

		} else {

			entranceVisited = new boolean[maze.sizeR][maze.sizeC];
			exitVisited = new boolean[maze.sizeR][maze.sizeC];
		}

		recursiveSolve(maze, entranceCellSolver, exitCellSolver, entrancePath, exitPath, entranceVisited, exitVisited); //solve the maze recursively till the paths meet
		//, or one cell reaches the exit or entrance



	} // end of solveMaze()


	protected void recursiveSolve(Maze maze, Cell entranceCellSolver, Cell exitCellSolver, Stack<Cell> entrancePath,
			Stack<Cell> exitPath, boolean[][] entranceVisited, boolean[][] exitVisited) {
		boolean isSolved = false;

		//Solve from both the entrance and exit step by step
		entranceCellSolver = solverStep(entranceCellSolver, entrancePath, entranceVisited, maze);
		exitCellSolver = solverStep(exitCellSolver, exitPath, exitVisited, maze);

		//Check if the entranceCellSolver or the exitCellSolver has visited their respective ends of the maze
		if(entranceCellSolver == maze.exit || exitCellSolver == maze.entrance ){
			isSolved = true;
		}

		//Check if the exit path has visited the entranceCellSolver Cell or if the entrancePath has visited the exitCellSolver cell
		if(exitPath.contains(entranceCellSolver) || entrancePath.contains(exitCellSolver)){
			isSolved = true;
		}

		//If none of the if conditions return true, recursively solve it, till it is solved.
		if(!isSolved){
			recursiveSolve(maze, entranceCellSolver, exitCellSolver, entrancePath, exitPath, entranceVisited, exitVisited);
		}

	}


	@Override
	public boolean isSolved() {
		// TODO Auto-generated method stud
		return true;

	} // end if isSolved()


	@Override
	public int cellsExplored() {
		// TODO Auto-generated method stub
		return 0;
	} // end of cellsExplored()

	Cell solverStep(Cell cellSolver, Stack<Cell> path, boolean[][] visitedCells, Maze maze){
		//Get possible neighbor cells to visit
		ArrayList<Cell> unvisitedNeighbours = getUnvisitedNeighbours(cellSolver, visitedCells, maze);

		//If current cell is not set as visited, set it to visited.

		if(!visitedCells[cellSolver.r][cellSolver.c]){
			visitedCells[cellSolver.r][cellSolver.c] = true;
			maze.drawFtPrt(cellSolver);
			//Draw the path
		}


		//If there are unvisited neighbours, !unvisitedNeighbours.isEmpty()
		if(!unvisitedNeighbours.isEmpty()){
			path.push(cellSolver);
			cellSolver = unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
			maze.drawFtPrt(cellSolver);
			if(cellSolver.tunnelTo != null){
				//cellSolver is in a tunnel - so move into it
				cellSolver = cellSolver.tunnelTo;
				maze.drawFtPrt(cellSolver);
				//Draw the path
			}
		}else{
			//If there aren't any unvisited neighbours, then backtrack to the last cell.

			if(!path.isEmpty())
			{
				cellSolver = path.pop();
			} 
			else {
				//If path is empty, return the cellSolver.
				return cellSolver;
			}



		} 

		return cellSolver;
	}//end of solverStep

	ArrayList<Cell> getUnvisitedNeighbours(Cell b, boolean[][] visitedCells, Maze m){

		ArrayList<Cell> neighbours = new ArrayList<Cell>();

		for(int i = 0; i < Maze.NUM_DIR; i++){
			Cell checkCell = b.neigh[i];
			if(checkCell != null){
				//if the neighbour isn't null
				if(!b.wall[i].present){
					if(!visitedCells[checkCell.r][checkCell.c])
						//If there isn't a wall present and we haven't visited it before, add it to neighbours
						neighbours.add(checkCell);
				}
			}
		}
		return neighbours;
	}//end of getUnvisitedNeighbours

} // end of class BiDirectionalRecursiveBackTrackerSolver
