package mazeGenerator;

import java.util.*;

import maze.*;

public class ModifiedPrimsGenerator implements MazeGenerator {

	@Override
	public void generateMaze(Maze maze) {
		// TODO Auto-generated method stub

		int maxRows = maze.sizeR;
		int maxColumns = maze.sizeC;
		Random random = new Random();


		//1-Pick a random starting cell
		int currentRow = random.nextInt(maxRows);
		int currentColumn = random.nextInt(maxColumns);


		//2-Add to set Z
		ArrayList<Cell> cellSetZ = new ArrayList<Cell>();
		cellSetZ.add(maze.map[currentRow][currentColumn]);

		//3-Put all neighbouring cells of starting cell to set F
		ArrayList<Cell> cellSetF = new ArrayList<Cell>();
		Cell currentCell = maze.map[currentRow][currentColumn];
		int i = 0;
		while(currentCell.neigh[i] != null){
			cellSetF.add(currentCell.neigh[i]);
			i++;
		}

		while(cellSetZ.size() != maze.map.length){
			//4-Select a Random cell C from set F and remove it
			Cell randomCellC = cellSetF.get(random.nextInt(cellSetF.size()));
			cellSetF.remove(randomCellC);

			//5-Randomly select a cell b from set Z and adjacent to cell C
			int cellDir = random.nextInt(cellSetZ.size());
			Cell randomCellB =  cellSetZ.get(cellDir);

			boolean isAdjacent = checkAdjacent(randomCellC, randomCellB);
			while(!isAdjacent){
				cellDir = random.nextInt(cellSetZ.size());
				randomCellB = cellSetZ.get(cellDir);
				isAdjacent = checkAdjacent(randomCellC, randomCellB);
			}

			//6-Carve path between c and b
			randomCellB.wall[cellDir].present = false;

			//7-Add cell c to set Z
			cellSetZ.add(randomCellC);
			//8-Add neighbours of cell C to the set F
			int j = 0;
			while(randomCellC.neigh[j] != null){
				cellSetF.add(randomCellC.neigh[i]);
				j++;
			}
		}

	} // end of generateMaze()

	boolean checkAdjacent(Cell c, Cell b) {
		boolean isAdjacent = false;
		for (int i = 0; i < c.neigh.length; i++) {
			if(c.neigh[i] != null){
				if (c.neigh[i].equals(b)) {
					isAdjacent = true;
				}
			}

		}
		return isAdjacent;

	}

} // end of class ModifiedPrimsGenerator
