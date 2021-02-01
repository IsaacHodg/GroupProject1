package GroupProject;

import java.io.FileInputStream;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class GroupProject {
	static Scanner scnr = new Scanner(System.in);
	static ArrayList<Movie> nowPlaying = new ArrayList<Movie>();
	static ArrayList<Movie> comingSoon = new ArrayList<Movie>();
	
	enum Statuses{
		received, released;
	}

	public static void main(String[] args) throws IOException, ParseException {
		FileInputStream inputFile = new FileInputStream("gp1_input.txt");
		Scanner scanner = new Scanner(inputFile);
		String movieName;
		Date releaseDate;
		String description;
		Date receiveDate;
		Statuses status;

		// Reading file and making new movies, adding movies to array list
		//scanner.useDelimiter(" | ");
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		//LocalDate dateTime = LocalDate.parse.scannernext();
		while (scanner.hasNext()) {
			//movieName = scanner.useDelimiter(" | ").next();
			//releaseDate = formatter.parse(scanner.useDelimiter(" | ").next());
			//description = scanner.nextLine();
			String movieline = scanner.nextLine();
			String movieinfo [] = movieline.split(" \\| ");
			movieName = movieinfo[0];
			releaseDate = formatter.parse(movieinfo[1]);
			description = movieinfo[2];
			receiveDate = formatter.parse(movieinfo[3]);
			status = Statuses.valueOf(movieinfo[4]);
			
			
			Movie movie1 = new Movie(movieName, releaseDate, description, receiveDate, status);
			if (releaseDate.after(today.getTime())) {
				comingSoon.add(movie1);
			} else {
				nowPlaying.add(movie1);
			}
		}
		
		char menuChoice = '?';
		while(menuChoice != 'q') {
			Iterator<Movie> iterator = comingSoon.iterator();
			Iterator<Movie> iterator2 = nowPlaying.iterator();
			
			menuChoice = printMenu();
		
		 if(menuChoice == 'd') {
			 
			 System.out.println("Coming Soon Movies:\n");
			 while(iterator.hasNext()) {
				 
				 Movie st = iterator.next();
				 System.out.println(st.getMovieName() + ", " + st.getReleaseDate() + ", " + st.getDescription());
			 }
			 
			 System.out.println("\nNow Playing Movies:\n");
			 while(iterator2.hasNext()) {
				 
				 Movie st = iterator2.next();
				 System.out.println(st.getMovieName() + ", " + st.getReleaseDate() + ", " + st.getDescription());
			 }
		 } 
		 
		 else if(menuChoice == 'a') {
 
		 }
		 
		 else if(menuChoice == 'e') {
			 System.out.print("Please enter the name of the movie to edit: ");
			 scnr.nextLine();
			 movieName = scnr.nextLine();
			 System.out.print("\nWould you like to edit the release date (r) or description (d)? Please type the respective character:");
			 menuChoice = scnr.next().charAt(0);
			 editMovie(movieName, menuChoice);
		 }
		 
		 else if(menuChoice == 'n') {
			 String date1 = "";
				  
			 System.out.print("\nPlease write a date in MM/DD/YYYY format: ");
			 scnr.nextLine();
			 date1 = scnr.nextLine();
			 releasesBeforeDate(date1);
		 }
		 
		 else if(menuChoice == 'm') {
			 System.out.print("Please enter the release date to begin showing: ");
			 releaseDate = formatter.parse(scnr.next());
			 nowShowingMovies(releaseDate);
		 }
		 
		 else if(menuChoice == 's') {
			 FileOutputStream outputFile = new FileOutputStream("gp1_input.txt");
			 PrintWriter writer = new PrintWriter(outputFile);
			 while (iterator.hasNext()) {
					writer.write(iterator.next().toString() + "\n");
			 }
			 while (iterator2.hasNext()) {
					writer.write(iterator2.next().toString() + "\n");
			 }
			 writer.close();
			 outputFile.close();
		 }
		 
		 else if(menuChoice == 'q') {
			 System.out.println("Goodbye!");  
		 }
		      
		 else {
			 System.out.println("You entered a wrong option. Please, select the correct option again!!!");
		      
		 }
	}

		
		// Closing everything
		scanner.close();
		inputFile.close();
	}
	
	//Method to edit a "coming" movie's release date or description
	//O(n)
	public static void editMovie(String movieName, char editSelection) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Iterator<Movie> iterator = comingSoon.iterator();
		Iterator<Movie> itEdit = comingSoon.iterator();
		while (iterator.hasNext()) {
			String currentName = iterator.next().getMovieName();
			if(currentName.equals(movieName)) {
				if(editSelection == 'r') {
					System.out.print("\nPlease write the new release date in MM/DD/YY format: ");
					itEdit.next().setReleaseDate(formatter.parse(scnr.next()));
					System.out.println("\nEdit complete.");
				}
				else {
					if(editSelection == 'd') {
						scnr.nextLine();
						System.out.print("\nPlease write the new description: ");
						itEdit.next().setDescription(scnr.nextLine());
						System.out.println("\nEdit complete.");
					}
				}
			}
		}
	}

	//Find a way to reduce from O(n^2)
	//Method to begin showing movies of a certain release date
	//O(n^2)
	public static void nowShowingMovies(Date showDate) {
		for (int i = 0; i < comingSoon.size(); i++) {
			if (comingSoon.get(i).getReleaseDate().compareTo(showDate) == 0) {
				for (int j = 0; j < nowPlaying.size(); j++) {
					if (nowPlaying.get(j).getMovieName().equals(comingSoon.get(i).getMovieName())) {
						comingSoon.remove(i);
						i--;
						break;
					}
					else if(j != nowPlaying.size() - 1) {
						continue;
					}
					else {
						nowPlaying.add(comingSoon.get(i));
						comingSoon.remove(i);
						i--;
						break;
					}
				}
			}
		}	
	}
	
	//Method to test if date has been entered in the correct format
	//O(1)
	public static boolean validateJavaDate(String strDate) {
			
		if (strDate.trim().equals("")) {
			return true;
		}
			
		else {
			   
			//Set date format
			SimpleDateFormat myfrmt = new SimpleDateFormat("MM/dd/yyyy");
			myfrmt.setLenient(false);
			    
			try {
				Date myDate = myfrmt.parse(strDate); 
			}
			    
			//If Date format is invalid return false
			catch (ParseException e) {
				System.out.println(strDate+" is Invalid Date");
				return false;
			}
			    
			return true;
		}
		   
	}
	
	//Method that tells the number of movies with a release date before the given date
	//O(n)
	public static void releasesBeforeDate(String date1) {
		Iterator<Movie> iterator = comingSoon.iterator();
		int count = 0;
		Date date2 = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		 
		 //test if date format is entered in the correct format
		 while(!validateJavaDate(date1)) {
			 
		 System.out.print("\nPlease write a date in MM/DD/YYYY format: ");
		 date1 = scnr.nextLine();
		 }
		 
		 //Put user entry from String type date1 to Date type date2
		 try {
			 
			 date2 = dateFormat.parse(date1);
		 } 
		 
		 catch (ParseException e) {
			 
			 e.printStackTrace();
			 
		 }
		 
		//Iterate through the list and count release date smaller than user entry
		 while(iterator.hasNext()) {
			 
			 Date release = iterator.next().getReleaseDate();
			 if(date2.compareTo(release) > 0) {
				 count++;
			 } 
		 }
			
		 System.out.print("There are " + count + " movies before " + date2);
	}
		
	//print menu
	 public static char printMenu(){
		    char menuChoice;
		    System.out.println("\n      MENU");
		    System.out.println("d - Display Movies");
		    System.out.println("a - Add Movies");
		    System.out.println("e - Edit Movies");
		    System.out.println("m - Start Showing Movies in Theater");
		    System.out.println("n - Numbers of movies before a date");
		    System.out.println("s - Save");
		    System.out.println("q - Exit");
		    System.out.println("\nPlease, Choose an Option:");
		    menuChoice = scnr.next().charAt(0);
		    return menuChoice;
		  }	
		
}
