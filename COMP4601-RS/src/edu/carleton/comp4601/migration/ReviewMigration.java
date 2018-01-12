package edu.carleton.comp4601.migration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.carleton.comp4601.dao.ReviewCollection;
import edu.carleton.comp4601.model.Review;
import edu.carleton.comp4601.utils.Constants;

public class ReviewMigration {
	
	public static List<Review> extractReviews() {
		List<Review> reviews = new ArrayList<Review>();
		
		File folder = new File(Constants.RAW_DATA_PATH_REVIEWS);
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("Number of Files: " + listOfFiles.length);
		System.out.println("Number of Reviews: " + reviews.size());
		
		System.out.println("Creating Reviews...");
		System.out.println("Please be patient...");
		int id = 0;
		
		System.out.println();
		
		for(int i = 0; i < listOfFiles.length; i++) {
			id++;
			
			File file = listOfFiles[i];
			
			if(listOfFiles[i].isFile()) {
				// Create HTML Document
				Document reviewDoc = getParseableDoc(file);
				
				// Transform to Review
				Review review = transformDocToReview(reviewDoc);
				
				review.setId(id);
				
				reviews.add(review);
			}
		}  
		
		System.out.println("Number of Reviews: " + reviews.size());
		
		return reviews;
	}
	
	public static void migrate() {
		System.out.println();
		System.out.println("x----------------------x");
		System.out.println("Extracting Reviews from Source...");
		System.out.println();
		
		// Extract reviews from source
		List<Review> reviews = extractReviews();
		
		// Add each review to Mongo DB
		System.out.println();
		System.out.println("Adding Reviews to DB...");
		System.out.println("Please be patient...");
		
		ReviewCollection collection = ReviewCollection.getInstance();
		
		for(Review review: reviews) {
			collection.add(review);
		}
		
		System.out.println("Reviews migrated successfully!");
		System.out.println("x----------------------x");
	}
	
	private static Document getParseableDoc(File fileToConvert) {
		Document parseableDoc = null;
		
		try {
			parseableDoc = Jsoup.parse(fileToConvert, "UTF-8", "http://example.com");
		} catch (IOException e) {
			parseableDoc = null;
			e.printStackTrace();
		}
		
		return parseableDoc;
	}
	
	/**
	 * Transforms an HTML document
	 * to a review by extracting information
	 * from the HTML.
	 * 
	 * @param reviewDoc the HTML document
	 * @return Review POJO
	 */
	private static Review transformDocToReview(Document reviewDoc) {
	//	System.out.println(reviewDoc);
		
	//	System.out.println();
	//	System.out.println("Scraping Title...");
		String movieName = reviewDoc.title();
		
	//	System.out.println("Title: " + movieName);
		
	//	System.out.println();
	//	System.out.println("Scraping Metadata...");
		Elements metadata = reviewDoc.select("meta");
		
		String userNameRaw = null;
		String scoreRaw = null;
		String helpfulnessRaw = null;
		
	//	System.out.println();
		for(Element meta: metadata) {
			String metaName = meta.attr("name");
			String metaValue = meta.attr("content");
			
			if(metaName.equals("userId")) {
				userNameRaw = metaValue;
			} else if(metaName.equals("score")) {
				scoreRaw = metaValue;
			} else if(metaName.equals("helpfulness")) {
				helpfulnessRaw = metaValue;
			}
			
		/*	System.out.print(meta.attr("name") + " ");
			System.out.print(meta.attr("content"));
			System.out.println();  */
		}
		
	//	System.out.println("Movie Name: " + movieName);
	//	System.out.println("User Name: " + userNameRaw);
	//	System.out.println("Score: " + scoreRaw);
	//	System.out.println("Helpfulness: " + helpfulnessRaw);
		
	//	System.out.println("\nCalculating Helpfulness...");
		String[] feedback = helpfulnessRaw.split("/");
			
		Double positive =  Double.valueOf(feedback[0]);
		Double total =  Double.valueOf(feedback[1]);
		
		Double helpfulness;
		
		if(total != 0) {
			helpfulness = positive/total;
			helpfulness = helpfulness * 10;
		} else {
			helpfulness = 0.0;
		}
		
	//	System.out.println("Helpfulness: " + helpfulness);
		
	//	System.out.println("\nCalculating Score...");
		Double score = Double.valueOf(scoreRaw);
		score = score * 2;
		
	//	System.out.println("Score: " + score);
		
		Review review = new Review();
		review.setUserName(userNameRaw);
		review.setMovieName(movieName);
		review.setScore(score);
		review.setHelpfulness(helpfulness);
		
		return review;
	}

}
