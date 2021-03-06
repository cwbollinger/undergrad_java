package edu.ncsu.csc216.todolist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import edu.ncsu.csc216.todolist.model.CategoryList;
import edu.ncsu.csc216.todolist.model.TaskList;

/**
 * The Class ToDoList which maintains the list of categories and list of
 * taskLists.
 * 
 * @author Cole Logar
 */
public class ToDoList extends Observable implements Observer, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 34992L;

	/** The number of lists of tasks. */
	private int numLists;

	/** The Constant of which the the array will be resized */
	private static final int RESIZE = 10;

	/** The filename. */
	private String filename;

	/**
	 * The variable that shows if the information has changed since the file has
	 * been saved
	 */
	private boolean changed;

	/** The unique number given to the next task to be added. */
	private int nextTaskListNum;

	/** The tasks. */
	private TaskList[] tasks;

	/** The categories. */
	private CategoryList categories;

	/** The frame for adding error messages. */
	private JFrame frame;

	/**
	 * Instantiates a new to do list.
	 */
	public ToDoList() {

		nextTaskListNum = 1;
		categories = new CategoryList();
		numLists = 0;
		tasks = new TaskList[10];
		addTaskList();
		categories.addObserver(this);
		changed = false;
	}

	/**
	 * Checks if information is changed.
	 * 
	 * @return true, if is changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * Sets the changed variable. And notifies observers. This signifies that
	 * the information has changed.
	 * 
	 * @param changed
	 *            the boolean value of the what to set changed
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the filename.
	 * 
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * Sets the filename.
	 * 
	 * @param name
	 *            the new filename
	 */
	public void setFilename(String name) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.filename = name;
		setChanged(true);

	}

	/**
	 * Gets the number of task lists.
	 * 
	 * @return the number of task lists
	 */
	public int getNumTaskLists() {
		return numLists;
	}

	/**
	 * Gets the task list at index idx.
	 * 
	 * @param idx
	 *            the index
	 * @return the task list
	 */
	public TaskList getTaskList(int idx) {
		if (idx < 0 || idx >= numLists) {
			throw new IndexOutOfBoundsException();
		}
		return tasks[idx];
	}

	/**
	 * Gets the category list.
	 * 
	 * @return the category list
	 */
	public CategoryList getCategoryList() {
		return categories;
	}

	/**
	 * Adds the task list.
	 * 
	 * @return the index the TaskList was added at
	 */
	public int addTaskList() {
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i] == null) {
				tasks[i] = new TaskList("New List", "TL" + nextTaskListNum);
				tasks[i].addObserver(this);
				numLists++;
				nextTaskListNum++;
				setChanged(true);
				return i;
			}
		}
		resize();
		return addTaskList();
	}

	/**
	 * Resizes the tasks Array so that more TaskLists may be added.
	 */
	private void resize() {
		TaskList[] temp = new TaskList[tasks.length * 2];
		for (int i = 0; i < tasks.length; i++) {
			temp[i] = tasks[i];
		}
		tasks = temp;
	}

	/**
	 * Removes the task list at the given index.
	 * 
	 * @param idx
	 *            the index
	 */
	public void removeTaskList(int idx) {

		if (idx < 0 || idx >= numLists) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = numLists; i > idx; i--) {
			tasks[i - 1] = tasks[i];
		}
		tasks[idx] = null;
		numLists--;
		setChanged(true);
	}

	/**
	 * Saves the CategoryList and the array of TaskLists to the given file using
	 * object serialization.
	 * 
	 * @param fname
	 *            filename to save ToDoList information to.
	 */
	public void saveDataFile(String fname) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fname);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i = 0; i < numLists; i++) {
				out.writeObject(tasks[i]);
			}
			out.writeObject(categories);
			out.writeObject(filename);
			out.writeInt(nextTaskListNum);
			changed = false;
			out.close();
			fileOut.close();
		} catch (IOException e) {
			System.err.println("An error occurred while saving file " + fname);
			e.printStackTrace(System.err);
		}
	}

	/**
	 * Opens a data file with the given name and creates the data structures
	 * from the serialized objects in the file.
	 * 
	 * @param fname
	 *            filename to create ToDoList information from.
	 */
	public void openDataFile(String fname) {
		if (changed) {
			saveDataFile(filename);
		}
		try {
			if (!fname.endsWith(".tdl")) {
				JOptionPane.showMessageDialog(frame,
						"File is not a valid ToDoList data file.",
						"File error", JOptionPane.ERROR_MESSAGE);
			} else {
				FileInputStream fileIn = new FileInputStream(fname);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				ArrayList<TaskList> temp = new ArrayList<TaskList>();
				Object tl = in.readObject();
				while (tl instanceof TaskList) {
					TaskList l = (TaskList) tl;
					l.addObserver(this);
					temp.add(l);
					tl = in.readObject();
				}
				tasks = new TaskList[RESIZE];
				tasks = temp.toArray(tasks);
				numLists = temp.size();
				categories = (CategoryList) tl;
				categories.addObserver(this);
				filename = (String) in.readObject();
				nextTaskListNum = (int) in.readInt();
				changed = false;
				in.close();
				fileIn.close();
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame,
					"File is not a valid ToDoList data file.", "File error",
					JOptionPane.ERROR_MESSAGE);
			// System.err.println("An error occurred while reading file " +
			// fname);
			// e.printStackTrace(System.err);
		} catch (ClassNotFoundException c) {
			JOptionPane.showMessageDialog(frame, "Error reading data file.",
					"File error", JOptionPane.ERROR_MESSAGE);
			// System.err.println("Error reconstructing ToDoList from file "
			// + fname);
			// c.printStackTrace(System.err);
		}

		setChanged();
		notifyObservers(this);
	}



	/**
	 * This method is called whenever the observed object is changed. An
	 * application calls an Observable object's notifyObservers method to have
	 * all the object's observers notified of the change.
	 * 
	 * @param o
	 *            the observable object.
	 * @param arg
	 *            an argument passed to the notifyObservers method
	 */
	public void update(Observable o, Object arg) {
		this.changed = true;
		setChanged();
		notifyObservers(arg);
	}

}
