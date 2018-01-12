package edu.carleton.comp4601.migration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import edu.carleton.comp4601.dao.MovieCollection;
import edu.carleton.comp4601.model.Movie;
import edu.carleton.comp4601.utils.Constants;

public class MovieMigration {
	
	public static List<Movie> extractMovies() {
		List<Movie> movies = new ArrayList<Movie>();
		
		File folder = new File(Constants.RAW_DATA_PATH_MOVIES);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("Number of Files: " + listOfFiles.length);
		System.out.println("Number of Movies: " + movies.size());
		
		System.out.println("Creating Movies...");
		
		for(int i = 0; i < listOfFiles.length; i++) {
			if(listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				
				// Clean Up Name
				fileName = fileName.replaceAll(".html", "");
				
				// Create New User
				Movie movie = new Movie();
				movie.setName(fileName);
				
				movies.add(movie);
			}
		}
		
		System.out.println("Number of Movies: " + movies.size());
		
		return movies;
	}
	
	public static void migrate() {
		System.out.println();
		System.out.println("x----------------------x");
		System.out.println("Extracting Movies from Source...");
		System.out.println();
		
		// Extract movies from source
		List<Movie> movies = extractMovies();
		
		// Add each movie to Mongo DB
		System.out.println();
		System.out.println("Adding Movies to DB...");
		
		MovieCollection collection = MovieCollection.getInstance();
		
		for(Movie movie: movies) {
			collection.add(movie);
		}
		
		System.out.println("Movies migrated successfully!");
		System.out.println("x----------------------x"); 
	}

}
