package edu.carleton.comp4601.clustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The K Means Algorithm.
 * 
 * @author @author Hrishi Mukherjee (100888108).
 * 
 */

public class KMeans {
	
	private int no_users;
	private User[] users;
	private int no_features;
	private boolean changed;
	private int no_clusters;
	
	/*
	 * CLUSTERS + CENTROIDS
	 */
	private List<User>[] clusters;
	private User[] newCentroids;
	private User[] oldCentroids;
	
	private static final int NUM_CLUSTERS = 3;
	
	/*
	 * Constructor that reads the data in from a file.
	 * You must specify the number of clusters.
	 */
	public KMeans(int numClusters, int numFeatures, List<edu.carleton.comp4601.model.User> userList) {
		changed = true;
		
		no_users = userList.size();
		no_features = numFeatures;
		users = new User[no_users];
		this.no_clusters = numClusters;
		
		clusters = new ArrayList[no_clusters];
		newCentroids = new User[no_clusters];
		oldCentroids = new User[no_clusters];
		
		// Initialize clusters
		for(int i = 0; i < clusters.length; i++) {
			clusters[i] = new ArrayList<User>();
		}
		
		for(int i = 0; i < userList.size(); i++) {
			edu.carleton.comp4601.model.User userToTransform = 
					userList.get(i);
			
			String name = userToTransform.getName();
			double optimism = userToTransform.getOptimism();
			double reliability = userToTransform.getReliability();
			
			users[i] = new User(name, no_features, numClusters);
			
			users[i].features[0] = optimism;
			users[i].features[1] = reliability;
		}
		
		System.out.println("Num Users: " + no_users);
		System.out.println("Num Features: " + no_features);
		System.out.println("Num Clusters: " + no_clusters);
		System.out.println("Users to Cluster: " + users.length);
	}
	
	public List<edu.carleton.comp4601.model.UserCluster> execute() {
		System.out.println();
		System.out.println("x-----------------------x");
		System.out.println("K Means Algorithm Starting...");
		
		/*
		 * CREATE RANDOM CENTROIDS		
		 */
		// Compute the bounding box
		User minBounds = minimum(users);
		User maxBounds = maximum(users);

		System.out.println("BOUNDS:");
		System.out.println(minBounds);
		System.out.println(maxBounds);
		
		// Generate random centroids within bounds
		for(int i = 0; i < newCentroids.length; i++) {
			User randomCentroid = randomPoint(minBounds, maxBounds);
			randomCentroid.name = "CENTROID_" + i;
			
			newCentroids[i] = randomCentroid;
		}
		
		System.out.println("\nINITIAL OLD CENTROIDS:");
		printArray(oldCentroids);
		
		System.out.println("\nINITIAL NEW CENTROIDS:");
		printArray(newCentroids);
		
		int counter = 0;
		// CLUSTERING LOOP
		while (changed) {
			// Your code here
			counter++;
			System.out.println("\nIteration " + counter + "...");
			System.out.println("x------------------x");
			
			System.out.println("\nCalculating distances...");
			// Calculate Distance of EACH User from Centroids
			for(int i = 0; i < users.length; i++) {
				User user = users[i];
				
				for(int j = 0; j < newCentroids.length; j++) {
					User centroid = newCentroids[j];
					
					// Compute distance
					double distance = distance(user, centroid);
					
					user.distance[j] = distance;
				}
			}
			
			// Reset Clusters
			for(int i = 0; i < clusters.length; i++) {
				clusters[i].clear();
			}
			
			System.out.println("Adding users to clusters...");
			// Add user to appropriate cluster
			for(int i = 0; i < users.length; i++) {
				User user = users[i];
				double min = user.distance[0];
				int minIndex = 0;
				
				// Find minimum distance and corresponding index
				for(int j = 0; j < user.distance.length; j++) {
					//System.out.println("d" + j + ": " + user.distance[j]);
					if(user.distance[j] < min) {
						min = user.distance[j];
						minIndex = j;
					}
				}
				
				// Add to appropriate cluster
				clusters[minIndex].add(user);
			}
			
			System.out.println();
			for(int i = 0; i < clusters.length; i++) {
				System.out.println("Cluster " + i + ": " + clusters[i].size());
			}
			
			// Recompute centroids of each cluster
			System.out.println("Recomputing centroids...");
			for(int i = 0; i < clusters.length; i++) {
				User newCentroid;
				
				if(clusters[i].size() > 0) {
					newCentroid = computeCentroid(clusters[i]);
					newCentroid.name = "CENTROID_" + i;
				} else {
					newCentroid = randomPoint(minBounds, maxBounds);
				}
				
				// Store current centroid before updating
				oldCentroids[i] = newCentroids[i];
				
				// Update current centroid
				newCentroids[i] = newCentroid;
			}
			
			System.out.println("\nOLD CENTROIDS:");
			printArray(oldCentroids);
			
			System.out.println("\nNEW CENTROIDS:");
			printArray(newCentroids);
			
			// Compare centroids
			changed = false;
			
			for(int i = 0; i < newCentroids.length; i++) {
				if(newCentroids[i].equals(oldCentroids[i]) == false) {
					changed = true;
				}
			}
			
			System.out.println("\nCentroids Changed? " + changed);
			
			System.out.println("x------------------x");
		}
		
		System.out.println();
		System.out.println("######################");
		System.out.println("# CLUSTERS FINALIZED #");
		System.out.println("######################");
		System.out.println();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		printClusters(clusters);
		
		System.out.println();
		System.out.println("K Means Algorithm Done!");
		System.out.println("x-----------------------x");
		
		// Return the computed clusters
		return convertUsers(clusters);
	}
	
	/* 
	 * Computes distance between two users
	 * Could implement this on User too.
	 */
	private double distance(User a, User b) {
		double rtn = 0.0;
		// Assumes a and b have same number of features
		for (int i = 0; i < a.features.length; i++) {
			rtn += (a.features[i] - b.features[i])
					* (a.features[i] - b.features[i]);
		}
		return Math.sqrt(rtn);
	}
	
	/*
	 * Computes maximum of all dimensions.
	 */
	private User maximum(User[] users) {
		int numFeatures = users[0].features.length;
		User maximum = new User("MAX", numFeatures, 0);
		
		// Find min of all features
		for(int f = 0; f < numFeatures; f++) {
			double max = 0.0;
			
			for(int i = 0; i < users.length; i++) {
				User user = users[i];
				double feature = user.features[f];
				
				if(feature > max) {
					max = feature;
				}
			}
			
			maximum.features[f] = max;
		}
		
		return maximum;
	}
	
	/*
	 * Computes minimum of all dimensions.
	 */
	private User minimum(User[] users) {
		int numFeatures = users[0].features.length;
		User minimum = new User("MIN", numFeatures, 0);
		
		// Find min of all features
		for(int f = 0; f < numFeatures; f++) {
			double min = 0.0;
			
			for(int i = 0; i < users.length; i++) {
				User user = users[i];
				double feature = user.features[f];
				
				if(feature < min) {
					min = feature;
				}
			}
			
			minimum.features[f] = min;
		}
		
		return minimum;
	}
	
	/*
	 * Generates a random point in N-dimensions
	 * between the min and max bounds.
	 */
	private User randomPoint(User min, User max) {
		int numFeatures = min.features.length;
		User randomPoint = new User("CENTROID", numFeatures, 0);
		
		Random generator = new Random();
		
		// Generate Random for feature 0 between bounds
		for(int f = 0; f < numFeatures; f++) {
			double currentMin = min.features[f];
			double currentMax = max.features[f];
			
			double random = 
					ThreadLocalRandom.current().
					nextDouble(currentMin, currentMax);
			
			randomPoint.features[f] = random;
		}
		
		return randomPoint;
	}
	
	/*
	 * Computes the centroid of the
	 * given cluster by taking the
	 * average of all users.
	 */
	private User computeCentroid(List<User> cluster) {
		int numFeatures = cluster.get(0).features.length;
		User centroid = new User("CLUSTER_AVG", numFeatures, 0);
		User total = new User("CLUSTER_TOTAL", numFeatures, 0);
		
		// Sum up each feature for all users
		for(int f = 0; f < numFeatures; f++) {
			double sum = 0.0;
			
			for(int i = 0; i < cluster.size(); i++) {
				User user = cluster.get(i);
				double feature = user.features[f];
				
				sum += feature;
			}
			
			total.features[f] = sum;
		}
		
		// Divide each total by the number of users in the cluster
		for(int f = 0; f < numFeatures; f++) {
			double featureTotal = total.features[f];
			int numUsers = cluster.size();
			
			double featureAvg = featureTotal/numUsers;
			
			centroid.features[f] = featureAvg; 
		}
		
		return centroid;
	}
	
	private void printCluster(List<User> cluster) {
		if(cluster != null) {
			for(int i = 0; i < cluster.size(); i++) {
				System.out.println(cluster.get(i));
			}
		} else {
			System.out.println("No Users in Cluster.");
		}
	}
	
	private void printArray(User[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
	
	public List<edu.carleton.comp4601.model.UserCluster> convertUsers(List<User>[] clusters) {
		List<edu.carleton.comp4601.model.UserCluster> allClusteredUsers = 
				new ArrayList<edu.carleton.comp4601.model.UserCluster>();
		
		for(int i = 0; i < clusters.length; i++) {
			List<User> cluster = clusters[i];
			
			List<edu.carleton.comp4601.model.UserCluster> clusteredUsers = 
					userAdapter(cluster, i);
			
			allClusteredUsers.addAll(clusteredUsers);
		}
		
		return allClusteredUsers;
	}
	
	public List<edu.carleton.comp4601.model.UserCluster> userAdapter(List<User> kMeansUsers, int clusterNumber) {
		List<edu.carleton.comp4601.model.UserCluster> clusterUsers = 
				new ArrayList<edu.carleton.comp4601.model.UserCluster>();
		
		for(User kMeansUser: kMeansUsers) {
			edu.carleton.comp4601.model.UserCluster userCluster = 
					new edu.carleton.comp4601.model.UserCluster();
			
			// Transfer Information
			userCluster.setName(kMeansUser.name);
			userCluster.setCluster(clusterNumber);
			
			clusterUsers.add(userCluster);
		}
		
		return clusterUsers;
	}
	
	private void printClusters(List<User>[] array) {
		System.out.println();
		System.out.println("CURRENT CLUSTERS:");
		System.out.println("*****************************");
		
		for(int i = 0; i < array.length; i++) {
			System.out.println();
			System.out.println("CLUSTER " + i + ": " + array[i].size() + " USERS");
			System.out.println("------------------------");
			printCluster(array[i]);
			System.out.println("------------------------");
		}
		
		System.out.println();
		System.out.println("*****************************");
	}
	
	/*
	 *  Private class for representing user
	 */
	private class User {
		public double[] features;
		public double[] distance;
		public String name;
		public int cluster;
		public int last_cluster;

		public User(String name, int noFeatures, int noClusters) {
			this.name = name;
			this.features = new double[noFeatures];
			this.distance = new double[noClusters];
			this.cluster = -1;
			this.last_cluster = -2;
		}

		// Check if cluster association has changed.
		public boolean changed() {
			return last_cluster != cluster;
		}
		
		// Update the saved cluster from iteration to iteration
		public void update() {
			last_cluster = cluster;
		}

		public String toString() {
			StringBuffer b = new StringBuffer(name);
			for (int i = 0; i < features.length; i++) {
				b.append(' ');
				b.append(features[i]);
			}
			return b.toString();
		}
		
		public boolean equals(User other) {
			// Compare all features
			for(int i = 0; i < this.features.length; i++) {
				if(this.features[i] != other.features[i]) {
					return false;
				}
			}
			
			return true;
		}
	}	
}
