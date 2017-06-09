package edu.ncsu.csc216.predator_prey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Cell class including the functionality of the finite state
 * machine implementation, ensuring that the states are properly transitioned
 * to and from each state to each state.
 * @author Daniel Rice
 *
 */
public class CellTest {

	/** A Cell object to help automate testing */
	private Cell cell;

	/**
	 * Initializes the cell to the empty state.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cell = new Cell(Cell.EMPTY_VALUE);
	}

	/**
	 * Tests receiving a copy of the Cell for a new Cell
	 * to have the same state as the initial cell.
	 */
	@Test
	public void testCopy() {
		Cell cell2 = null;
		assertNull(cell2);
		cell2 = cell.copy();
		assertTrue(cell2.getState() == 0);
		cell = new Cell(Cell.PREDATOR_VALUE);
		cell2 = cell.copy();
		assertTrue(cell2.getState() == 1);
		cell = new Cell(Cell.PREY_VALUE);
		cell2 = cell.copy();
		assertEquals(2, cell2.getState());
		assertFalse(cell2 == cell);
	}

	/**
	 * Will test all of the update methods for the individual states
	 * and all of the transitions between the states.
	 */
	@Test
	public void testUpdates() {
		//update the user parameters
		//should die since nobody around him
		UserParams.instance().setPredatorIsolation(1);
		cell = new Cell(Cell.PREDATOR_VALUE);
		cell.update(0, 0);
		assertEquals(0, cell.getState());
		UserParams.instance().setPredatorIsolation(0);
		cell = new Cell(Cell.PREDATOR_VALUE);
		cell.update(0, 0);
		assertEquals(1, cell.getState());

		//second case for predators
		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorStarvation(1);
		cell.update(0, 1);
		assertEquals(0, cell.getState());

		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorStarvation(0);
		cell.update(0, 1);
		assertEquals(1, cell.getState());

		//third case for predators
		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorLoneliness(1);
		cell.update(2, 0);
		assertEquals(0, cell.getState());
		
		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorLoneliness(0);
		cell.update(1, 0);
		assertEquals(1, cell.getState());

		//fourth case for predators
		cell = new Cell(Cell.PREDATOR_VALUE);
		cell.update(5, 3);
		assertEquals(1, cell.getState());

		//fifth case for predators
		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorSurvivalWhenPrey(0);
		cell.update(5, 4);
		assertEquals(0, cell.getState());

		cell = new Cell(Cell.PREDATOR_VALUE);
		UserParams.instance().setPredatorSurvivalWhenPrey(1);
		cell.update(5, 4);
		assertEquals(1, cell.getState());

		//test the empty state transitions
		cell = new Cell(Cell.EMPTY_VALUE);
		UserParams.instance().setPredatorReproduction(0);
		cell.update(3, 3);
		assertEquals(cell.getState(), 0);
		//now change it so that the predator will reproduce (3/3)
		UserParams.instance().setPredatorReproduction(1);
		cell = new Cell(Cell.EMPTY_VALUE);
		cell.update(3, 2);
		assertEquals(1, cell.getState());

		//now try empty state with no preds or prey around
		cell = new Cell(Cell.EMPTY_VALUE);
		cell.update(1, 1);
		assertEquals(0, cell.getState());

		//now try the second case
		cell = new Cell(Cell.EMPTY_VALUE);
		UserParams.instance().setPreyReproduction(0);
		cell.update(3, 1);
		assertEquals(0, cell.getState());
		cell = new Cell(Cell.EMPTY_VALUE);
		UserParams.instance().setPreyReproduction(1);
		cell.update(3, 0);
		assertEquals(2, cell.getState());

		//test the prey transitions
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreyLoneliness(1);
		cell.update(0, 0);
		assertEquals(0, cell.getState());
		
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreyLoneliness(0);
		cell.update(0, 0);
		assertEquals(2, cell.getState());

		//second case for prey
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreyEaten(0);
		cell.update(0, 1);
		assertEquals(2, cell.getState());
		
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreyEaten(1);
		cell.update(0, 5);
		assertEquals(0, cell.getState());

		//try out the third case for prey
		cell = new Cell(Cell.PREY_VALUE);
		cell.update(2, 4);
		assertEquals(0, cell.getState());

		//try out the fourth case for prey
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreySurvivalWhenPredators(0);
		cell.update(3, 4);
		assertEquals(2, cell.getState());
		
		cell = new Cell(Cell.PREY_VALUE);
		UserParams.instance().setPreySurvivalWhenPredators(1);
		cell.update(3, 4);
		assertEquals(0, cell.getState());
	}
}
