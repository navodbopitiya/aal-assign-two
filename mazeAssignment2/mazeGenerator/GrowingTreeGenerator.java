package mazeGenerator;

import java.util.*;

import maze.Maze;
import maze.Cell;

public class GrowingTreeGenerator implements MazeGenerator {
	// Growing tree maze generator. As it is very general, here we implement as "usually pick the most recent cell, but occasionally pick a random cell"

	double threshold = 0.1;
	Random random = new Random();
	final int RANDOM = 1;
	final int RECENT = 0;

	@Override
	public void generateMaze(Maze maze) {
		int maxRows = maze.sizeR;
		int maxCols = maze.sizeC;
		Cell rndCellB = null;
		Cell rndCellC = null;
		int randomValue;

		ArrayList<Cell> visitedCells = new ArrayList<Cell>();


		//1 - Pick a random starting cell - done
		Cell startingCell = maze.map[random.nextInt(maxRows)][random.nextInt(maxCols)];

		//2- Add it to CellSetZ - done
		ArrayList<Cell> cellSetZ = new ArrayList<Cell>();
		cellSetZ.add(startingCell);
		visitedCells.add(startingCell);

		while(!cellSetZ.isEmpty()){
			//3- Select a strategy - done 75% - Random 25% - Recent
			int strategy = selectStrategy();
			if(strategy == RANDOM){
				//4a- Select a cell b from Z - Random - done
				randomValue = random.nextInt(cellSetZ.size());
				rndCellB = cellSetZ.get(randomValue);
			}else if(strategy == RECENT){

				//4b - Select a cell b from Z - Most Recent - done
				rndCellB = cellSetZ.get(cellSetZ.size()-1);
			}

			//5- Check if cell B has unvisited neighbour cells
			ArrayList<Cell> unvisitedNeighbourCells = getUnVisitedNeighbourCells(rndCellB, visitedCells);

			if(!unvisitedNeighbourCells.isEmpty()){
				//6 - Randomly select a neighbour - Cell C
				randomValue = random.nextInt(unvisitedNeighbourCells.size());
				rndCellC = unvisitedNeighbourCells.get(randomValue);
				//7- Carve a path to it - done
				rndCellC.wall[getNeighbourId(rndCellB,rndCellC)].present = false;
				visitedCells.add(rndCellC);

				//8- Add selected neighbour to set Z
				cellSetZ.add(rndCellC);
			}



			//9- Check if cell B has unvisited neighbours
			unvisitedNeighbourCells = getUnVisitedNeighbourCells(rndCellB, visitedCells);
			if(unvisitedNeighbourCells.isEmpty()){
				//9a- If there are isn't any unvisited neighbours, then remove it from Z
				cellSetZ.remove(rndCellB);

			}



		}//10-Repeat from step 3 till cellSetZ is empty


	}

	ArrayList<Cell> getUnVisitedNeighbourCells(Cell b, ArrayList<Cell> visitedCells){
		ArrayList<Cell> neighbourCells = new ArrayList<Cell>();
		for(int i = 0; i < b.neigh.length; i++ ){
			if(!(visitedCells.contains(b.neigh[i])) && !(b.neigh[i] == null)){
				neighbourCells.add(b.neigh[i]);
			}
		}
		return neighbourCells;
	}

	int selectStrategy(){
		int[] strategy = {RANDOM,RANDOM,RANDOM,RECENT};
		return strategy[random.nextInt(strategy.length)];
	}

	int getNeighbourId(Cell b, Cell c) {
		int neighId = 0;
		for (int i = 0; i < c.neigh.length; i++) {
			if(c.neigh[i] != null){
				if (c.neigh[i].equals(b)) {
					neighId = i;
				}
			}

		}
		return neighId;
	}

}
