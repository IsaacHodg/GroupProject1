package GroupProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import GroupProject.GroupProject.Statuses;

public class Movie {
	
	//Data fields
	private String movieName;
	private Date releaseDate;
	private String description;
	private Date receiveDate;
	private Statuses status;
	
	//constructor
	public Movie() {
	}
	
	//constructor with five arguments
	public Movie(String movieName, Date releaseDate, String description, Date receiveDate, Statuses status) {
		this.movieName = movieName;
		this.releaseDate = releaseDate;
		this.description = description;
		this.receiveDate = receiveDate;
		this.status = status;
	}

	//Getters and setters
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	
	public Statuses getStatus() {
		return status;
	}

	public void setStatus(Statuses status) {
		this.status = status;
	}
	
	//Formats the movie object to be output as a string
	public String toString() {
		String pattern = "MM/dd/yyyy";
		DateFormat formatter = new SimpleDateFormat(pattern);
		String movieLine = movieName + " | " + formatter.format(releaseDate) + " | " + description + " | " + formatter.format(receiveDate)
				+ " | " + status;
		return movieLine;
	}
}