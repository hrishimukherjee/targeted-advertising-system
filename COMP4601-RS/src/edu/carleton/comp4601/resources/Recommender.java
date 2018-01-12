package edu.carleton.comp4601.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.carleton.comp4601.dao.AdvertisementCollection;
import edu.carleton.comp4601.dao.ClusterCollection;
import edu.carleton.comp4601.dao.ConfigCollection;
import edu.carleton.comp4601.dao.MovieCollection;
import edu.carleton.comp4601.dao.ReviewCollection;
import edu.carleton.comp4601.dao.UserClusterCollection;
import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.Advertisement;
import edu.carleton.comp4601.model.Cluster;
import edu.carleton.comp4601.model.Config;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.model.UserCluster;
import edu.carleton.comp4601.utils.AppUtils;
import edu.carleton.comp4601.utils.Constants;
import edu.carleton.comp4601.utils.ContextInitializer;
import edu.carleton.comp4601.utils.HTMLBuilder;

/**
 * The REST Recommender Service.
 * 
 * @author @author Hrishi Mukherjee (100888108) & Yonathan Kidanemariam (100890519).
 * 
 */

@Path("/rs")
public class Recommender {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private static final String NAME = "COMP4601 Recommender System";
	
	public Recommender() {
		
	}
	
	/**
	 * Returns the name of the web application.
	 * 
	 * @return name of web application
	 */
	@GET
	public String printName() {
		return NAME;
	}
	
	@GET
	@Path("/fetch/{user}/{page}")
	@Produces(MediaType.TEXT_HTML)
	public Response displayAugmentedPage(@PathParam("page") String pageTitle, 
			@PathParam("user") String userName) {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET Display Advertisement Augmented Page");
		
		Response response = null;
		String html = null;
		
		boolean isContextualized = AppUtils.isContextInitialized();
		
		if(!isContextualized) {
			System.out.println("Context has NOT been initialized. "
					+ "\nReturning Error Message.");
			
			html = HTMLBuilder.buildFailure("Context needs to be "
					+ "initialized before pages are viewable.");
			
			response = Response.ok(html, MediaType.TEXT_HTML).build();
			
			return response;
		}
		
		System.out.println();
		System.out.println("Page Requested: " + pageTitle);
		System.out.println("Page Requested By User: " + userName);
		
		System.out.println("Retrieving File...");
		File file = new File(Constants.RAW_DATA_PATH_MOVIES + "/" + pageTitle + ".html");
		
		System.out.println("File: " + file);
		
		System.out.println("Parsing File...");
		Document pageHTMLDoc = null;
		
		try {
			pageHTMLDoc = Jsoup.parse(file, "UTF-8", "http://example.com");
		} catch (IOException e) {
			pageHTMLDoc = null;
			e.printStackTrace();
		}
		
		System.out.println();
		// System.out.println(pageHTMLDoc);
		
		// Find User's Cluster ID
		System.out.println("Finding Cluster ID of " + userName + "...");
		UserCluster query = new UserCluster();
		query.setName(userName);
		
		UserCluster userCluster = UserClusterCollection.getInstance().find(query);
		
		System.out.println(userCluster);
		
		//Find Clusters Class
		System.out.println("Finding Clusters Class...");
		
		Cluster clusterQuery = new Cluster();
		clusterQuery.setCluster(userCluster.getCluster());
		
		Cluster cluster = ClusterCollection.getInstance().find(clusterQuery);
		
		System.out.println(cluster);
		
		//Display Appropriate content to user according to specified cluster
		System.out.println("Retrieving Following Advertisements:");
		
		AdvertisementCollection adsDB = AdvertisementCollection.getInstance();
		
		List<Advertisement> ads = new ArrayList<Advertisement>();
		
		// Get Optimism Ads
		if(cluster.getOptimismClass() == Constants.HIGH_OPTIMISM) {
			System.out.println("HIGH OPTIMISM;");
			ads.addAll(adsDB.findByOptimism(Constants.HIGH_OPTIMISM));
		} else if(cluster.getOptimismClass() == Constants.LOW_OPTIMISM) {
			System.out.println("LOW OPTIMISM;");
			ads.addAll(adsDB.findByOptimism(Constants.LOW_OPTIMISM));
		}
		
		// Get Reliability Ads
		if(cluster.getReliabilityClass() == Constants.HIGH_RELIABILITY) {
			System.out.println("HIGH RELIABILITY;");
			ads.addAll(adsDB.findByReliability(Constants.HIGH_RELIABILITY));
		} else if(cluster.getReliabilityClass() == Constants.LOW_RELIABILITY) {
			System.out.println("LOW RELIABILITY;");
			ads.addAll(adsDB.findByReliability(Constants.LOW_RELIABILITY));
		}
		
		System.out.println();
		System.out.println(ads.size() + " ads retrieved.");
		System.out.println("Creating augmented page...");
		
		html = HTMLBuilder.augmentAdvertisement(pageHTMLDoc.body().html(), ads);
				
		System.out.println();
		System.out.println("Responding...");
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("x---------x");
		
		return response;
	}
	
	@GET
	@Path("/context")
	@Produces(MediaType.TEXT_HTML)
	public Response getContext() {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET Context");
		
		Response response = null;
		String html = null;
		
		boolean isDataMigrated = AppUtils.isDatabaseMigrated();
		
		if(!isDataMigrated) {
			System.out.println("Database currently migrating. Please be patient.");
			
			long userCount = UserCollection.getInstance().count();
			long movieCount = MovieCollection.getInstance().count();
			long reviewCount = ReviewCollection.getInstance().count();
			
			System.out.println("Num users migrated: " + userCount);
			System.out.println("Num movies migrated: " + movieCount);
			System.out.println("Num reviews migrated: " + reviewCount);
			
			long totalCount = userCount + movieCount + reviewCount;
			
			System.out.println("Total items migrated: " + totalCount);
			
			float progress = (float) totalCount/Constants.TOTAL_ITEMS_TO_MIGRATE;
			progress = progress * 100;
			
			System.out.println("Migration is " + (int) progress + "% complete.");
			
			html = HTMLBuilder.buildFailure("Database currently migrating. Progress: " + (int) progress + "%");
			
			response = Response.ok(html, MediaType.TEXT_HTML).build();
			
			System.out.println("x---------x");
			
			return response;
		}
		
		boolean isContextualized = AppUtils.isContextInitialized();
		
		if(!isContextualized) {
			System.out.println("Context has NOT been initialized. "
					+ "\nExecuting initialization sequence.");
			
			// Initialize Context
			ContextInitializer.initialize();
		} else {
			System.out.println("Context has already been initialized.");
		}
		
		// Extract all the users from the DB
		System.out.println("Extracting all users...");
		
		List<User> users = UserCollection.getInstance().findAll();
		
		System.out.println("Numer of users retrieved: " + users.size());
		
		System.out.println("Creating table...");
		html = HTMLBuilder.buildContextTable(users);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("x---------x");
		
		return response;
	}
	
	@GET
	@Path("/advertising/{category}")
	@Produces(MediaType.TEXT_HTML)
	public Response getAdvertisingCategory(@PathParam("category") String category) {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET Advertising Category");
		
		Response response = null;
		String html = null;
		
		boolean isContextualized = AppUtils.isContextInitialized();
		
		if(!isContextualized) {
			System.out.println("Context has NOT been initialized. "
					+ "\nReturning Error Message.");
			
			html = HTMLBuilder.buildFailure("Context needs to be "
					+ "initialized before advertisements are viewable.");
			
			response = Response.ok(html, MediaType.TEXT_HTML).build();
			
			return response;
		}
		
		System.out.println("Ads requested for category: " + category);
		
		// Retrieve Cluster
		System.out.println("Retrieving cluster from DB...");
		Cluster query = new Cluster();
		query.setCluster(new Integer(category));
		
		Cluster cluster = ClusterCollection.getInstance().find(query);

		System.out.println(cluster);
		
		System.out.println("Retrieving community ads...");
		List<Advertisement> ads = new ArrayList<Advertisement>();
		
		// Get all optimism ads
		ads.addAll(AdvertisementCollection.getInstance().findByOptimism(cluster.getOptimismClass()));
		
		// Get all reliability ads
		ads.addAll(AdvertisementCollection.getInstance().findByReliability(cluster.getReliabilityClass()));
		
		System.out.println("Creating html...");
		
		html = HTMLBuilder.buildAdCategory(ads);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("x---------x");
		
		return response;
	}
	
	@GET
	@Path("/community")
	@Produces(MediaType.TEXT_HTML)
	public Response getCommunities() {
		System.out.println();
		System.out.println("x---------x");
		System.out.println("Request Type: GET Communities");
		
		Response response = null;
		String html = null;
		
		boolean isContextualized = AppUtils.isContextInitialized();
		
		if(!isContextualized) {
			System.out.println("Context has NOT been initialized. "
					+ "\nReturning Error Message.");
			
			html = HTMLBuilder.buildFailure("Context needs to be "
					+ "initialized before communities are viewable.");
			
			response = Response.ok(html, MediaType.TEXT_HTML).build();
			
			return response;
		}
		
		//Get all communities
		List<Cluster> communities = ClusterCollection.getInstance().findAll();
	
		html =  HTMLBuilder.buildCommunitiesTable(communities);
		
		response = Response.ok(html, MediaType.TEXT_HTML).build();
		
		System.out.println("x---------x");
		
		return response;
	}

}
