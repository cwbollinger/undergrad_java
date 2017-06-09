package edu.ncsu.csc216.movie_inventory;

/**
 * The Movie class contains information about the title, release year, genre,
 * and rating of a movie. It is used by MovieInventory to represent the movies
 * in its collection.
 * 
 * @author Sarah Heckman (sarah_heckman@ncsu.edu)
 * @author Chris Bollinger (cwbollin@ncsu.edu)
 * @version 1.0
 * 
 */
public class Movie {

	/**
	 * The earliest allowed movie release date.
	 */
	private static final int MIN_RELEASE_YEAR = 1880;

	/**
	 * The movie's title.
	 */
	private String title;

	/**
	 * The movie's release date. Must be greater than or equal to
	 * MIN_RELEASE_DATE.
	 */
	private int releaseYear;

	/**
	 * The movie's genre.
	 */
	private String genre;

	/**
	 * The movie's MPAA rating.
	 */
	private String rating;

	/**
	 * Constructs a Movie object.
	 * 
	 * @param title
	 *            Movie's title.
	 * @param releaseYear
	 *            Movie's release year. Must be 1880 or later.
	 * @param genre
	 *            Movie's genre.
	 * @param rating
	 *            Movie's rating.
	 */
	public Movie(String title, int releaseYear, String genre, String rating) {
		super();
		if (releaseYear < MIN_RELEASE_YEAR) {
			throw new IllegalArgumentException();
		}
		this.title = title;
		this.releaseYear = releaseYear;
		this.genre = genre;
		this.rating = rating;
	}

	/**
	 * Get the title of the movie.
	 * 
	 * @return the title of the movie.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Get the release year of the movie. Will always be 1880 or later.
	 * 
	 * @return the release year of the movie.
	 */
	public int getReleaseYear() {
		return releaseYear;
	}

	/**
	 * Get the genre of the movie.
	 * 
	 * @return the genre of the movie.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Get the rating of the movie.
	 * 
	 * @return the rating of the movie.
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Generates and returns this movie's hash code.
	 * 
	 * @return the movie's hash code.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Tests to see if two movies are equal.
	 * 
	 * @param obj
	 *            Movie to compare to this movie.
	 * @return true if both objects are movies and have matching fields, false
	 *         otherwise.
	 * 
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/***
	 * Returns a string representation of the Movie.
	 * 
	 * @return a string containing information about the Movie's title, release
	 *         date, genre, and rating.
	 */
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear
				+ ", genre=" + genre + ", rating=" + rating + "]";
	}

}
