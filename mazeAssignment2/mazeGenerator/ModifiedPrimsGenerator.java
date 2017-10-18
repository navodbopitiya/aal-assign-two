package mazeGenerator;

/** Author -  Navod Bopitiya - s3617222 **/


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
		int randomRow = random.nextInt(maxRows);
		int randomCol = random.nextInt(maxCols);
		while(maze.map[randomRow][randomCol] == null)
		{
			randomRow = random.nextInt(maxRows);
			randomCol = random.nextInt(maxCols);
		}
		Cell startingCell = maze.map[randomRow][randomCol];

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
			int neighbourId = checkNeighbour(rndCellB, rndCellC);
			while(neighbourId == -1){
				randomValue = random.nextInt(cellSetZ.size());
				rndCellB = cellSetZ.get(randomValue);
				neighbourId = checkNeighbour(rndCellB, rndCellC);
			}

			//Carve path between B and C
			rndCellB.wall[neighbourId].present = false;
			visitedCells.add(rndCellC);

			//Add cell C to set Z
			cellSetZ.add(rndCellC);

			//Add neighbours of cell c to the set F
			int j = 0;
			while(j < rndCellC.neigh.length){
				if(rndCellC.neigh[j] != null && !(visitedCells.contains(rndCellC.neigh[j])) && cellSetF.contains(rndCellC.neigh[j]) == false ){
					cellSetF.add(rndCellC.neigh[j]);
				}
				j++;
			}
		}

	}

	int checkNeighbour(Cell b, Cell c){ // Checks if Cell B is adjacent C
		int id = -1;
		for(int i = 0; i < b.neigh.length; i++){
			if(b.neigh[i] != null){
				if(b.neigh[i].equals(c)){
					id = i;
					return id;
				}
			}
		}
		return id;
	}

} // end of class ModifiedPrimsGenerator
