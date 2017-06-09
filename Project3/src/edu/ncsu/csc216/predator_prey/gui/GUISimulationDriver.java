package edu.ncsu.csc216.predator_prey.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.ncsu.csc216.predator_prey.model.Territory;

/**
 * GUI that controls the Predator-Prey Simulation. The GUI handles the input of
 * the parameters and the visualization of the simulation through the
 * TerritoryDisplay and the PredatorPreyChart.
 * 
 * @author Dr. Sarah Heckman
 */
public class GUISimulationDriver extends JFrame {

	/** Used for serialization. */
	private static final long serialVersionUID = 1L;

	/** Buttons for simulation GUI */
	private JButton btnSubmit;
	private JButton btnReset;
	private JButton btnQuit;

	/** Panels for the major GUI components */
	private JPanel pnlButtons;
	private UserParamsPanel userParamsPanel;
	private PredatorPreyChart chart;
	private TerritoryDisplay display;

	/** Instance of the underlying model */
	private Territory territory;

	/**
	 * Controls the refresh of the TerritoryDisplay and PredatorPreyCharts at
	 * each timestep.
	 */
	private Timer timer;
	
	/** TimerTask for describing the run functionality */
	private TimerTask task;

	/** Maintains the current timestep */
	private int timestep;

	/**
	 * Constant for the amount of time between the refresh of the GUI components
	 * during the simulation.
	 */
	private static final int PERIOD = 200;

	/**
	 * Constructs the GUI.
	 */
	public GUISimulationDriver() {
		// Sets up default GUI
		super("Predator-Prey Simulation");
		setSize(830, 810);
		setLocation(50, 20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		territory = new Territory();

		// Construct the panels
		createPnlButtons();
		userParamsPanel = new UserParamsPanel();
		display = new TerritoryDisplay(territory);
		chart = new PredatorPreyChart();

		// Add the panels to the GUI
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(userParamsPanel, BorderLayout.WEST);
		c.add(pnlButtons, BorderLayout.NORTH);
		c.add(display, BorderLayout.CENTER);
		c.add(chart, BorderLayout.SOUTH);

		// Set GUI visible
		setVisible(true);
	}

	/**
	 * Runs the simulation by creating a timer. Every time the timer goes off,
	 * the SimulationTaskTimer.run() method is called which calls
	 * nextTimestep().  The simulation will run and the GUI components will update.
	 */
	public void simulate() {
		timestep = 0;
		task = new SimulationTimerTask();
		timer = new Timer();
		timer.scheduleAtFixedRate(task, PERIOD, PERIOD);
	}

	/**
	 * Private method that takes the current stats and updates the console
	 * output table and the chart.
	 * 
	 * @param timestep
	 *            the current timestep to report on
	 */
	private void displayStats(int timestep) {
		chart.addDataPoint(timestep, territory.getNumPrey(),
				territory.getNumPredator());
	}

	/**
	 * Creates the buttons and panel that holds the buttons.
	 */
	private void createPnlButtons() {
		createBtnSubmit();
		createBtnReset();
		createBtnQuit();

		pnlButtons = new JPanel();
		pnlButtons.setLayout(new GridLayout(1, 3));
		pnlButtons.add(btnSubmit);
		pnlButtons.add(btnReset);
		pnlButtons.add(btnQuit);
	}

	/**
	 * Creates the Submit button and adds the ActionListener to the button. The
	 * ActionListener will update the values provided by the user and run the
	 * simulation.
	 */
	private void createBtnSubmit() {
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			/**
			 * Updates the UserParams with the values provided by the user and
			 * runs the simulation.
			 */
			public void actionPerformed(ActionEvent e) {
				if (task != null) {
					task.cancel();
				}
				try {
					userParamsPanel.updateUserParams();
					territory = new Territory();
					display.setTerritory(territory);
					chart.removeAllDataPoints();
					GUISimulationDriver.this.repaint();
					display.show();
					simulate();
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(GUISimulationDriver.this, iae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
	}

	/**
	 * Creates the Reset button and adds the ActionListener to the button. The
	 * ActionListener will reset the text fields to the default values for the
	 * UserParams. Reset also resets the Territory.
	 */
	private void createBtnReset() {
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			/**
			 * Resets the text fields to the default values for the UserParams.
			 */
			public void actionPerformed(ActionEvent e) {
				if (task != null) {
					task.cancel();
				}
				userParamsPanel.populateDefaultValues();
				chart.removeAllDataPoints();
				GUISimulationDriver.this.repaint();
				display.show();
			}

		});
	}

	/**
	 * Creates the Quit button and adds the ActionListener to the button. The
	 * ActionListener quits the application.
	 */
	private void createBtnQuit() {
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {

			/**
			 * Quits the application.
			 */
			public void actionPerformed(ActionEvent e) {
				// You can ignore the FindBugs notification on the
				// System.exit(0);
				System.exit(0);
			}

		});
	}
	
	/**
	 * A TimerTask for running the simulation.  
	 * 
	 * Ignore the FindBugs notification about the serializable inner class.  The
	 * inner class cannot be static since it must access fields of the outer
	 * class.
	 * @author SarahHeckman
	 */
	private class SimulationTimerTask extends TimerTask implements Serializable {
		
		private static final long serialVersionUID = -2942231027681197181L;

		/**
		 * The following is done when the Timer fires: 1. A check if the
		 * simulation is done. If it is, the TimerTask is canceled. 2. The
		 * Territory is updated with the next timestep. 3. The
		 * TerritoryDisplay is updated from the changes in the model. 4. The
		 * output is printed to the console and the chart is updated. 5. The
		 * SimulationDriver GUI is repainted to display all updates.
		 */
		public void run() {
			if (territory.isDone()) {
				cancel();
			}
			territory.nextTimestep();
			display.show();
			GUISimulationDriver.this.repaint();
			displayStats(timestep++);
		}
	}

	/**
	 * Starts the GUI.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		new GUISimulationDriver();
	}
}
