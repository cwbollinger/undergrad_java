package edu.ncsu.csc216.predator_prey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality and implementation of the UserParams class. Tests
 * that the Singleton is implemented correctly.
 * @author Daniel Rice
 *
 */
public class UserParamsTest {

	/** A UserParams object to help automate testing */
	private UserParams test;

	/**
	 * Initializes the UserParams object to the default values as specified
	 * in the UserParams class.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		test = UserParams.instance();
	}

	/**
	 * Tests the singleton implementation.
	 */
	@Test
	public void testInstance () {
		UserParams two = null;
		assertNull(two);
		two = UserParams.instance();
		assertFalse(two == null);
		assertTrue(two == test);
	}

	/**
	 * Tests both the getter and setter method for the size variable.
	 */
	@Test
	public void testSize() {
		UserParams.instance().setSize(19);
		assertEquals(19, test.getSize());
		try {
			test.setSize(0);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(19, test.getSize());
		}
		test.setSize(1);
		assertEquals(1, test.getSize());
		UserParams two = UserParams.instance();
		assertEquals(1, two.getSize());
	}

	/**
	 * Tests the getter and setter methods for the preyLoneliness variable.
	 */
	@Test
	public void testPreyLoneliness() {
		UserParams.instance().setPreyLoneliness(UserParams.D_PREY_LONELINESS);
		assertEquals(.1, test.getPreyLoneliness(), .01);
		try {
			test.setPreyLoneliness(-.1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.1, test.getPreyLoneliness(), .01);
		}
		try {
			test.setPreyLoneliness(1.0001);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.1, test.getPreyLoneliness(), .01);
		}
		test.setPreyLoneliness(0);
		assertEquals(0, test.getPreyLoneliness(), .01);
		test.setPreyLoneliness(1.0);
		assertEquals(1, test.getPreyLoneliness(), .01);
	}

	/**
	 * Tests both the setter and getter methods for the variable preyEaten.
	 */
	@Test
	public void testPreyEaten() {
		UserParams.instance().setPreyEaten(UserParams.D_PREY_EATEN);
		assertEquals(.9, test.getPreyEaten(), .01);
		try {
			test.setPreyEaten(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.9, test.getPreyEaten(), .01);
		}
		try {
			test.setPreyEaten(1.0001);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.9, test.getPreyEaten(), .01);
		}
		test.setPreyEaten(1);
		assertEquals(1, test.getPreyEaten(), .01);
		test.setPreyEaten(0);
		assertEquals(0, test.getPreyEaten(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable predatorIsolation.
	 */
	@Test
	public void testPredatorIsolation() {
		UserParams.instance().setPredatorIsolation(UserParams.D_PREDATOR_ISOLATION);
		assertEquals(.8, test.getPredatorIsolation(), 0);
		try {
			test.setPredatorIsolation(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.8, test.getPredatorIsolation(), 0);
		}
		try {
			test.setPredatorIsolation(1.001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.8, test.getPredatorIsolation(), 0);
		}
		test.setPredatorIsolation(0);
		assertEquals(0, test.getPredatorIsolation(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable predatorStarvation.
	 */
	@Test
	public void testPredatorStarvation() {
		UserParams.instance().setPredatorStarvation(UserParams.D_PREDATOR_STARVATION);
		assertEquals(1, test.getPredatorStarvation(), 0);
		try {
			test.setPredatorStarvation(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(1, test.getPredatorStarvation(), 0);
		}
		try {
			test.setPredatorStarvation(1.001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(1, test.getPredatorStarvation(), 0);
		}
		test.setPredatorStarvation(0);
		assertEquals(0, test.getPredatorStarvation(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable predatorLoneliness.
	 */
	@Test
	public void testPredatorLoneliness() {
		UserParams.instance().setPredatorLoneliness(UserParams.D_PREDATOR_LONELINESS);
		assertEquals(.2, test.getPredatorLoneliness(), 0);
		try {
			test.setPredatorLoneliness(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.2, test.getPredatorLoneliness(), 0);
		}
		try {
			test.setPredatorLoneliness(1.001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.2, test.getPredatorLoneliness(), 0);
		}
		test.setPredatorLoneliness(0);
		assertEquals(0, test.getPredatorLoneliness(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable preyReproduction.
	 */
	@Test
	public void testPreyReproduction() {
		assertEquals(1, test.getPreyReproduction(), 0);
		try {
			test.setPreyReproduction(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(1, test.getPreyReproduction(), 0);
		}
		test.setPreyReproduction(1.001);
		assertEquals(1.001, test.getPreyReproduction(), 0);
		test.setPreyReproduction(0);
		assertEquals(0, test.getPreyReproduction(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable predatorReproduction.
	 */
	@Test
	public void testPredatorReproduction() {
		assertEquals(1, test.getPredatorReproduction(), 0);
		try {
			test.setPredatorReproduction(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(1, test.getPredatorReproduction(), 0);
		}
		test.setPredatorReproduction(1.001);
		assertEquals(1.001, test.getPredatorReproduction(), 0);
		test.setPredatorReproduction(0);
		assertEquals(0, test.getPredatorReproduction(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable preySurvivalWhenPredators.
	 */
	@Test
	public void testPreySurvivalWhenPredators() {
		assertEquals(1, test.getPreySurvivalWhenPredators(), 0);
		try {
			test.setPreySurvivalWhenPredators(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(1, test.getPreySurvivalWhenPredators(), 0);
		}
		test.setPreySurvivalWhenPredators(1.001);
		assertEquals(1.001, test.getPreySurvivalWhenPredators(), 0);
		test.setPreySurvivalWhenPredators(0);
		assertEquals(0, test.getPreySurvivalWhenPredators(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variable predatorSurvivalWhenPrey.
	 */
	@Test
	public void testPredatorSurvivalWhenPrey() {
		assertEquals(1, test.getPredatorSurvivalWhenPrey(), 0);
		try {
			test.setPredatorSurvivalWhenPrey(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(1, test.getPredatorSurvivalWhenPrey(), 0);
		}
		test.setPredatorSurvivalWhenPrey(1.001);
		assertEquals(1.001, test.getPredatorSurvivalWhenPrey(), 0);
		test.setPredatorSurvivalWhenPrey(0);
		assertEquals(0, test.getPredatorSurvivalWhenPrey(), 0);
	}

	/**
	 * Tests both the setter and getter methods for the variables preyInitialProbability
	 * and PredatorInitialProbability, as well as the validateInput method.
	 */
	@Test
	public void testInitialProbabilities() {
		UserParams.instance().setPreyInitialProbability(.4);
		assertEquals(.4, test.getPreyInitialProbability(), 0);
		try {
			test.setPreyInitialProbability(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.4, test.getPreyInitialProbability(), 0);
		}
		try {
			test.setPreyInitialProbability(1.001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.4, test.getPreyInitialProbability(), 0);
		}
		test.setPreyInitialProbability(0);
		assertEquals(0, test.getPreyInitialProbability(), 0);

		//test predator initial probability
		UserParams.instance().setPredatorInitialProbability(.2);
		assertEquals(.2, test.getPredatorInitialProbability(), 0);
		try {
			test.setPredatorInitialProbability(-.99);
			fail();
		} catch (IllegalArgumentException d) {
			assertEquals(.2, test.getPredatorInitialProbability(), 0);
		}
		try {
			test.setPredatorInitialProbability(1.001);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.2, test.getPredatorInitialProbability(), 0);
		}
		test.setPredatorInitialProbability(0);
		assertEquals(0, test.getPredatorInitialProbability(), 0);

		test.validateInitialProbabilities();
		assertEquals(0, test.getPredatorInitialProbability(), 0);
		assertEquals(0, test.getPreyInitialProbability(), 0);

		test.setPredatorInitialProbability(.6);
		test.setPreyInitialProbability(.401);
		try {
			test.validateInitialProbabilities();
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(.6, test.getPredatorInitialProbability(), 0);
			assertEquals(.401, test.getPreyInitialProbability(), 0);
		}
	}
}
