package mazeGenerator;

import java.util.*;

import maze.*;

public class ModifiedPrimsGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub

		int maxRows = maze.sizeR;
		int maxCols = maze.sizeC;

		Random random = new Random();
		int randomValue;
		Cell rndCellB;
		Cell rndCellC;
		ArrayList<Cell> visitedCells = new ArrayList<Cell>();

		//Pick a random starting cell
		Cell startingCell = maze.map[random.nextInt(maxRows)][random.nextInt(maxCols)];

		//Add to Set Z - IN SET
		ArrayList<Cell> cellSetZ = new ArrayList<Cell>();
		cellSetZ.add(startingCell);

		//Put all neighbouring cells of starting cell to Set F - FRONTIER SET
		ArrayList<Cell> cellSetF = new ArrayList<Cell>();
		int i = 0;
		while(i < startingCell.neigh.length){
			if(startingCell.neigh[i] != null){
				cellSetF.add(startingCell.neigh[i]);
			}
			i++;
		}

		while(!cellSetF.isEmpty()){
			
			//Select a random cell C from set F and remove it
			randomValue = random.nextInt(cellSetF.size());
			rndCellC = cellSetF.get(randomValue);
			cellSetF.remove(rndCellC);

			//Randomly select a cell b from setZ and adjacent to cell c
			randomValue = random.nextInt(cellSetZ.size());
			rndCellB = cellSetZ.get(randomValue);
			boolean isAdjacent = checkAdjacent(rndCellB, rndCellC);
			while(!isAdjacent){
				randomValue = random.nextInt(cellSetZ.size());
				rndCellB = cellSetZ.get(randomValue);
				isAdjacent = checkAdjacent(rndCellB, rndCellC);
			}

			//Carve path between c and b
			rndCellC.wall[getNeighbourId(rndCellB,rndCellC)].present = false;
			visitedCells.add(rndCellC);

			//Add cell c to set Z
			cellSetZ.add(rndCellC);

			//Add neighbours of cell c to the set F
			int j = 0;
			while(j < rndCellC.neigh.length){
				if(rndCellC.neigh[j] != null && !(visitedCells.contains(rndCellC.neigh[j]))){
					cellSetF.add(rndCellC.neigh[j]);
				}
				j++;
			}
		}

	}

	boolean checkAdjacent(Cell b, Cell c){
		boolean isAdjacent = false;
		for(int i = 0; i < b.neigh.length; i++){
			if(b.neigh[i] != null){
				if(b.neigh[i].equals(c)){
					isAdjacent = true;
					return isAdjacent;
				}
			}
		}
		return isAdjacent;
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
	
	boolean checkVisited(Cell b, ArrayList<Cell> visitedCells){
		boolean visited = false;
		if(visitedCells.contains(b)){
			visited = true;
		}
		return visited;
	}


} // end of class ModifiedPrimsGenerator
