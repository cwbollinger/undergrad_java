package edu.ncsu.csc216.predator_prey.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import edu.ncsu.csc216.predator_prey.model.Cell;
import edu.ncsu.csc216.predator_prey.model.Territory;

/**
 * Swing visualization of Predator-Prey Simulation
 * @author Ed Gehringer
 * @author Sarah Heckman
 **/
public class TerritoryDisplay extends JPanel {
	/** Default Serial Version ID for serialization */
	private static final long serialVersionUID = 1L;
	/** Array containing colors in the display: yellow, green, red */
	private Color[] colors;
	/** The length/width of a cell in the display */
	private int cellSize;
	/** An instance of the underlying model */
	private Territory territory;
	/** The size of the grid */
	private int dimension;

	/**
	 * Constructor, sets up top-level window and Canvas
	 * @param t a Territory to display
	 */
	public TerritoryDisplay(Territory t) {
		setTerritory(t);
	}
	
	/**
	 * Sets up the TerritoryDisplay with the given Territory
	 * @param t a Territory to display
	 */
	public void setTerritory(Territory t) {
		// Initialize the state of the display with the given 
		// Grid information
		colors = new Color[]{Color.green, Color.red, Color.cyan};
		cellSize = 22;
		territory = t;
		dimension = territory.getTerritory().length;
		
		// Set the size of the canvas to show all info
		setPreferredSize(new Dimension(cellSize * dimension, cellSize * dimension));
	}
	
	
	/**
	 * Override of JPanel.paintComponent that paints the
	 * JPanel with the yellow, green, or red cells as appropriate for
	 * the given Grid.
	 * @param g Graphics object to edit
	 */
	public void paintComponent(Graphics g) {
		Cell[][] tempGrid = territory.getTerritory();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				g.setColor(colors[tempGrid[i][j].getState()]);
				g.fillRect(cellSize * i, cellSize * j, cellSize, cellSize);
			}
		}
	}
	
	/**
	 * Call this method when you want the grid updated.
	 * The repaint() method will call the overridden paintComponent()
	 * method that will update the GridDisplay with the latest Territory
	 * data.
	 */
	public void show() {
		repaint();
	}
}
