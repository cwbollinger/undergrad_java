package edu.ncsu.csc216.todolist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;


/**
 * The Class that contains and manages information about a task.
 * @author Cole Logar
 */
public class Task extends Observable implements Comparable<Task>, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7459L;

	/** The title fo the task. */
	private String title;

	/** The details of the task. */
	private String details;

	/** The start date time of the task. */
	private Date startDateTime;

	/** The due date time the task. */
	private Date dueDateTime;

	/** The completed date time. */
	private Date completedDateTime;

	/** If the task is completed. */
	private boolean completed;

	/** The task id. */
	private String taskID;

	/** The category. */
	private Category category;

	/**
	 * Instantiates a new task.
	 * 
	 * @param title
	 *            the title
	 * @param details
	 *            the details of the task
	 * @param start
	 *            the start time of the task
	 * @param due
	 *            the due time of the task
	 * @param cat
	 *            the category of the task
	 * @param id
	 *            the task id
	 */
	public Task(String title, String details, Date start, Date due,
			Category cat, String id) {
		setCategory(cat);
		setDueDateTime(due);
		setStartDateTime(start);
		setDetails(details);
		setTitle(title);
		this.taskID = id;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 * @throws IllegalArgumentException when the title is null or an empty String
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		if (title == null || title.trim().equals(""))
			throw new IllegalArgumentException();
		this.title = title;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the details.
	 * 
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the details.
	 * 
	 * @param details
	 *            the new details
	 */
	public void setDetails(String details) {
		this.details = details;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the due date time.
	 * 
	 * @return the due date time
	 */
	public Date getDueDateTime() {
		return dueDateTime;
	}

	/**
	 * Sets the due date time.
	 * 
	 * 
	 * @throws IllegalArgumentException when the due date is null
	 * @param due
	 *            the new due date time
	 */
	public void setDueDateTime(Date due) {
		if (due == null)
			throw new IllegalArgumentException();
		this.dueDateTime = due;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the start date time.
	 * 
	 * @return the start date time
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Sets the start date time.
	 * 
	 * @throws IllegalArgumentException when the start date is null
	 * @param start
	 *            the new start date time
	 */
	public void setStartDateTime(Date start) {
		if (start == null)
			throw new IllegalArgumentException();
		this.startDateTime = start;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the completed date time.
	 * 
	 * @return the completed date time
	 */
	public Date getCompletedDateTime() {
		return completedDateTime;
	}

	/**
	 * Sets the completed date time.
	 * 
	 * @throws IllegalArgumentException when the completion date is null
	 * @param comp
	 *            the new completed date time
	 */
	public void setCompletedDateTime(Date comp) {
		if (comp == null)
			throw new IllegalArgumentException();
		this.completedDateTime = comp;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Checks if is completed.
	 * 
	 * @return true, if is completed
	 */
	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Sets the completed.
	 * 
	 * @param comp
	 *            the new completed
	 */
	public void setCompleted(boolean comp) {
		this.completed = comp;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Sets the category.
	 * 
	 * @throws IllegalArgumentException when the category is null
	 * @param c
	 *            the new category
	 */
	public void setCategory(Category c) {
		if (c == null)
			throw new IllegalArgumentException();
		this.category = c;
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Gets the category.
	 * 
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Gets the task id.
	 * 
	 * @return the task id
	 */
	public String getTaskID() {
		return taskID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taskID == null) ? 0 : taskID.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (taskID == null) {
			if (other.taskID != null)
				return false;
		} else if (!taskID.equals(other.taskID))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Task o) {
		return dueDateTime.compareTo(o.getDueDateTime());

	}
}
