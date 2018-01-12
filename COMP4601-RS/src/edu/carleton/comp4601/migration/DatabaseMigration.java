package edu.carleton.comp4601.migration;

/*
 * MIGRATES THE FULL DATABASE
 * FROM A DIRECTORY TO MONGO DB.
 */

public class DatabaseMigration {
	
	public static void migrate() {
		System.out.println("x----------------------x");
		System.out.println("DATABASE MIGRATION INITIATING...");
		System.out.println();
		
		UserMigration.migrate();
		MovieMigration.migrate();
		ReviewMigration.migrate();
		
		System.out.println();
		System.out.println("DATABASE MIGRATION COMPLETE!");
		System.out.println("x----------------------x");
	}

}
