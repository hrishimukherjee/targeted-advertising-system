package edu.carleton.comp4601.utils;

public class Constants {
	
	public static final String RAW_DATA_PATH_BASE = 
			"C:/Users/Hreeels/Desktop/CarletonU/Winter 2017/COMP 4601 - Mobile Multimedia/Assignment3";
	
	public static final String RAW_DATA_PATH_USERS = RAW_DATA_PATH_BASE + "/users";
	
	public static final String RAW_DATA_PATH_MOVIES = RAW_DATA_PATH_BASE + "/pages";
	
	public static final String RAW_DATA_PATH_REVIEWS = RAW_DATA_PATH_BASE + "/reviews";
	
	public static final String DB_NAME = "rs";
	
	public static final int NUM_CLUSTERS = 4;
	public static final int OPTIMISM_THRESHOLD = 7;
	public static final int RELIABILITY_THRESHOLD = 5;
	
	public static final int LOW_OPTIMISM = 0;
	public static final int HIGH_OPTIMISM = 1;
	public static final int LOW_RELIABILITY = 0;
	public static final int HIGH_RELIABILITY = 1;
	
	public static final int TOTAL_ITEMS_TO_MIGRATE = 84532;
	
	private Constants() {
		throw new RuntimeException();
	}

}
