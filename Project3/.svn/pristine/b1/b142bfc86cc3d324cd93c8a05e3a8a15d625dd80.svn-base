package edu.ncsu.csc216.predator_prey.console;

import java.util.Scanner;

import edu.ncsu.csc216.predator_prey.model.Territory;
import edu.ncsu.csc216.predator_prey.model.UserParams;

/**
 * This class represents the simulation that is done through the console
 * where the output is the number of empty cells, cells in the predator
 * state, and cells in the prey state for each timestep.
 * 
 * @author Daniel Rice
 *
 */
public class ConsoleSimulationDriver {

	/** The number of time units that has passed since the sim began */
	private int timestep;

	/** A Scanner to receive information from the user (probabilities) */
	private Scanner input;

	/** The Territory of the simulation */
	private Territory territory;

	/**
	 * Constructs the class, setting time-step to 0, and initializing the
	 * Scanner.
	 */
	public ConsoleSimulationDriver() {
		timestep = 0;
		input = new Scanner(System.in);
	}

	/**
	 * Prompts the user for all of the input values, and prints error
	 * messages when the input is invalid.
	 */
	public void userInterface() {
		//string for adjustment error
		String adj = "The input must be greater than or equal to 0.";
		//string for probability error
		String prob = "The input must be between 0.0 and 1.0.";
		//start them both out with invalid values

		while (true) {
			System.out.print("Enter the side length: ");
			//create a linescanner to read in the whole line and then process it
			//to look for int, I couldn't think of a better way to do it
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextInt()) {
				int size = lineScan.nextInt();
				if (validateSize(size)) {
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setSize(UserParams.D_SIZE);
					break;
				} else {
					System.out.println("The side length must be greater than 0.");
				}
			}
			lineScan.close();
		}
	
		while (true) {
			System.out.print("Enter the probability that a prey will die in the "
					+ "absence of any neighbors: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double preyLonely = lineScan.nextDouble();
				if (isValidProb(preyLonely)) {
					UserParams.instance().setPreyLoneliness(preyLonely);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPreyLoneliness(UserParams.D_PREY_LONELINESS);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		
		while (true) {
			System.out.print("Enter the probability that a single prey will be eaten "
					+ "when in the presence of predators: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double preyEaten = lineScan.nextDouble();
				if (isValidProb(preyEaten)) {
					UserParams.instance().setPreyEaten(preyEaten);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPreyEaten(UserParams.D_PREY_EATEN);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		while (true) {
			System.out.print("Enter the probability that a predator will die on its "
					+ "own with no food: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predIso = lineScan.nextDouble();
				if (isValidProb(predIso)) {
					UserParams.instance().setPredatorIsolation(predIso);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorIsolation(UserParams.D_PREDATOR_ISOLATION);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		while (true) {
			System.out.print("Enter the probability that a predator will die in "
					+ "in a group with no food: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predStarve = lineScan.nextDouble();
				if (isValidProb(predStarve)) {
					UserParams.instance().setPredatorStarvation(predStarve);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorStarvation(UserParams.D_PREDATOR_STARVATION);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		while (true) {
			System.out.print("Enter the probability that a single predator will die "
					+ "of loneliness in the presence of prey: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predLonely = lineScan.nextDouble();
				if (isValidProb(predLonely)) {
					UserParams.instance().setPredatorLoneliness(predLonely);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorLoneliness(UserParams.instance().getPredatorLoneliness());
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		while (true) {
			System.out.print("Enter the adjustment factor for reproduction rate "
					+ "of prey: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double preyRepro = lineScan.nextDouble();
				if (isValidAdj(preyRepro)) {
					UserParams.instance().setPreyReproduction(preyRepro);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPreyReproduction(UserParams.D_PREY_REPRODUCTION);
					break;
				}
			}
			lineScan.close();
			System.out.println(adj);
		}

		while (true) {
			System.out.print("Enter the adjustment factor for reproduction rate "
					+ "of predators: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predRepro = lineScan.nextDouble();
				if (isValidAdj(predRepro)) {
					UserParams.instance().setPredatorReproduction(predRepro);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorReproduction(UserParams.D_PREDATOR_REPRODUCTION);
					break;
				}
			}
			lineScan.close();
			System.out.println(adj);
		}

		while (true) {
			System.out.print("Enter the adjustment factor for effect of predators "
					+ "upon prey survival: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double preySurv = lineScan.nextDouble();
				if (isValidAdj(preySurv)) {
					UserParams.instance().setPreySurvivalWhenPredators(preySurv);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPreySurvivalWhenPredators(UserParams.D_PREY_SURVIVAL_WHEN_PREDATORS);
					break;
				}
			}
			lineScan.close();
			System.out.println(adj);
		}

		while (true) {
			System.out.print("Enter the adjustment factor for effect of prey upon "
					+ "predator survival: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predSurv = lineScan.nextDouble();
				if (isValidAdj(predSurv)) {
					UserParams.instance().setPredatorSurvivalWhenPrey(predSurv);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorSurvivalWhenPrey(UserParams.D_PREDATOR_SURVIVAL_WHEN_PREY);
					break;
				}
			}
			lineScan.close();
			System.out.println(adj);
		}	

		while (true) {
			System.out.print("Enter the initial probability that a cell contains prey: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double preyInit = lineScan.nextDouble();
				if (isValidProb(preyInit)) {
					UserParams.instance().setPreyInitialProbability(preyInit);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPreyInitialProbability(UserParams.D_PREY_INITIAL_PROBABILITY);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}

		while (true) {
			System.out.print("Enter the initial probability that a cell contains predator: ");
			String line = input.nextLine();
			Scanner lineScan = new Scanner(line);
			if (lineScan.hasNextDouble()) {
				double predInit = lineScan.nextDouble();
				if (isValidProb(predInit)) {
					UserParams.instance().setPredatorInitialProbability(predInit);
					break;
				}
			} else {
				if (line.equals("")) {
					UserParams.instance().setPredatorInitialProbability(UserParams.D_PREDATOR_INITIAL_PROBABILITY);
					break;
				}
			}
			lineScan.close();
			System.out.println(prob);
		}
	
		//now test to make sure the sum of the two initial probs isn't more than 1
		while (true) {
			try {
				UserParams.instance().validateInitialProbabilities();
				break;
			} catch (IllegalArgumentException d) {
				System.out.println(d.getMessage());
			}
			//if they aren't valid, ask the user to enter them again
			while (true) {
				System.out.print("Enter the initial probability that a cell contains prey: ");
				String line = input.nextLine();
				Scanner lineScan = new Scanner(line);
				if (lineScan.hasNextDouble()) {
					double preyInit = lineScan.nextDouble();
					if (isValidProb(preyInit)) {
						UserParams.instance().setPreyInitialProbability(preyInit);
						break;
					}
				} else {
					if (line.equals("")) {
						UserParams.instance().setPreyInitialProbability(UserParams.D_PREY_INITIAL_PROBABILITY);
						break;
					}
				}
				lineScan.close();
				System.out.println(prob);
			}

			while (true) {
				System.out.print("Enter the initial probability that a cell contains predator: ");
				String line = input.nextLine();
				Scanner lineScan = new Scanner(line);
				if (lineScan.hasNextDouble()) {
					double predInit = lineScan.nextDouble();
					if (isValidProb(predInit)) {
						UserParams.instance().setPredatorInitialProbability(predInit);
						break;
					}
				} else {
					if (line.equals("")) {
						UserParams.instance().setPredatorInitialProbability(UserParams.D_PREDATOR_INITIAL_PROBABILITY);
						break;
					}
				}
				lineScan.close();
				System.out.println(prob);
			}
		}
	}

	/**
	 * Validates that all of the user entered variables are valid. Only the size
	 * variable is an integer.
	 * @param value the value of the integer to check
	 * @return true if it is a valid number, false if it is not
	 */
	private boolean validateSize(int value) {
		try {
			UserParams.instance().setSize(value);
		} catch (IllegalArgumentException  d) {
			System.out.println(d.getMessage());
			return false;
		}
		return true;
	}

	/**
	 * Checks all of the values for the user parameters. If the input is a valid
	 * input for that parameter, it returns true, else it returns false.
	 * 
	 * @param value the value of the double to check
	 * @return whether the input is a valid probability or not.
	 */
	private boolean isValidProb(double value) {
		return (value >= 0 && value <= 1);
	}

	/**
	 * Checks to make sure the value is a valid value for an adjustment factor.
	 * @param value the value of the double to check
	 * @return true if the value is a valid adjustment factor, false otherwise
	 */
	private boolean isValidAdj(double value) {
		return (value >= 0);
	}

	/**
	 * Prints out the results of each time-step of the simulation including
	 * the time-step, the number of prey, predators, and empty cells.
	 */
	public void simulate() {
		int preys = territory.getNumPrey();
		int preds = territory.getNumPredator();
		int emptys = territory.getNumEmpty();
		System.out.printf("%-16d %-16d %-16d %-16d", timestep, preys, preds, emptys);
		System.out.println();
		timestep++;
		territory.nextTimestep();
	}

	/**
	 * Runs the program by initializing the territory with the
	 * user inputs.
	 * @param args command line arguments - not used
	 */
	public static void main(String[] args) {
		ConsoleSimulationDriver ui = new ConsoleSimulationDriver();
		ui.userInterface();
		ui.territory = new Territory();
		String time = "Timestep";
		String prey = "Number Prey";
		String pred = "Number Predators";
		String empty = "Number Empty";
		System.out.printf("%-16s %-16s %-16s %-16s", time, prey, pred, empty);
		System.out.println();
		while (!ui.territory.isDone()) {
			ui.simulate();
		}
		//just have it print out the final step where the values are 0
		ui.simulate();
	}
}
