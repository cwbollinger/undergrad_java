package edu.ncsu.csc216.predator_prey.model;

import java.util.Random;

/**
 * Represents the territory of the simulation. It has an array of Cells, and
 * updates the state of the cells at each timestep.
 * 
 * @author Daniel Rice
 * 
 */
public class Territory {

	/** The grid of cells */
	private Cell[][] cells;

	/**
	 * Constructs the array of cells by generating random numbers and deciding
	 * what state each cell will be in.
	 */
	public Territory() {
		int size = UserParams.instance().getSize();
		cells = new Cell[size + 2][size + 2];
		// private method to initialize the territory
		initialize();
	}

	/**
	 * Returns true if the Simulation is done, because there aren't any more
	 * predators or prey.
	 * 
	 * @return true if the simulation is done
	 */
	public boolean isDone() {
		return (getNumPrey() == 0 || getNumPredator() == 0);
	}

	/**
	 * Returns a copy of the Territory by returning the Cells making up the
	 * Territory.
	 * 
	 * @return an array of Cells that stands for the Territory
	 */
	public Cell[][] getTerritory() {
		return cells;
	}

	/**
	 * Updates all of the cells in the array based on the values of last
	 * timesteps states.
	 */
	public void nextTimestep() {
		// create a local instance of a 2D array of the state of the
		// cells to use
		Cell[][] copy = new Cell[cells.length][cells.length];
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells.length; j++) {
				copy[i][j] = cells[i][j].copy();
			}
		}

		// now step through each row of the cells array to update it
		for (int i = 1; i < cells.length - 1; i++) {
			for (int j = 1; j < cells.length - 1; j++) {
				int prey = 0;
				int preds = 0;
				for (int k = i - 1; k < i + 2; k++) {
					for (int l = j - 1; l < j + 2; l++) {
						if (k != i || l != j) {// if it is the cell we're
												// looking at, do nothing
							if (copy[k][l].getState() == Cell.PREY_VALUE) {
								prey++;
							} else if (copy[k][l].getState() == Cell.PREDATOR_VALUE) {
								preds++;
							}
						}
					}
					cells[i][j].update(prey, preds);
				}
			}
		}
	}

	/**
	 * Returns the number of cells that are currently in the prey state.
	 * 
	 * @return number of cells in prey state
	 */
	public int getNumPrey() {
		int prey = 0;
		for (int i = 1; i < cells.length - 1; i++) {
			for (int j = 1; j < cells.length - 1; j++) {
				if (cells[i][j].getState() == Cell.PREY_VALUE) {
					prey++;
				}
			}
		}
		return prey;
	}

	/**
	 * Returns the number of cells that are currently in the predator state.
	 * 
	 * @return number of cells in predator state
	 */
	public int getNumPredator() {
		int pred = 0;
		for (int i = 1; i < cells.length - 1; i++) {
			for (int j = 1; j < cells.length - 1; j++) {
				if (cells[i][j].getState() == Cell.PREDATOR_VALUE) {
					pred++;
				}
			}
		}
		return pred;
	}

	/**
	 * Returns the number of cells that are currently in the empty state.
	 * 
	 * @return number of cells in empty state
	 */
	public int getNumEmpty() {
		int empty = 0;
		for (int i = 1; i < cells.length - 1; i++) {
			for (int j = 1; j < cells.length - 1; j++) {
				if (cells[i][j].getState() == Cell.EMPTY_VALUE) {
					empty++;
				}
			}
		}
		return empty;
	}

	/**
	 * Initializes the territory.
	 */
	private void initialize() {
		Random r = new Random();
		double emptyProbability = 1 - (UserParams.instance()
				.getPredatorInitialProbability() + UserParams.instance()
				.getPreyInitialProbability());
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				double rand = r.nextDouble();
				// if its top row or bottom row, its an empty cell
				if (i == 0 || i == cells.length - 1) {
					cells[i][j] = new Cell(Cell.EMPTY_VALUE);
				} else if (j == 0 || j == cells[i].length - 1) {
					cells[i][j] = new Cell(Cell.EMPTY_VALUE); // if its on the
																// edges
				} else if (rand < emptyProbability) {
					cells[i][j] = new Cell(Cell.EMPTY_VALUE);
				} else if (rand >= emptyProbability
						&& rand < (UserParams.instance()
								.getPredatorInitialProbability() + emptyProbability)) {
					cells[i][j] = new Cell(Cell.PREDATOR_VALUE);
				} else {
					cells[i][j] = new Cell(Cell.PREY_VALUE);
				}
			}
		}
	}
}
