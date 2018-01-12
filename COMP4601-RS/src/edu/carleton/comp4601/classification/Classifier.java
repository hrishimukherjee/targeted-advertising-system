package edu.carleton.comp4601.classification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.carleton.comp4601.dao.ClusterCollection;
import edu.carleton.comp4601.dao.UserClusterCollection;
import edu.carleton.comp4601.dao.UserCollection;
import edu.carleton.comp4601.model.Cluster;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.model.UserCluster;
import edu.carleton.comp4601.utils.Constants;

public class Classifier {
	
	public static void classify() {
		System.out.println("x----------------------x");
		System.out.println("CLASSIFIER INITIATING...");
		System.out.println();
		
		System.out.println("Retrieving Clusters from DB...");
		List<List<User>> clusters = getAllClusters();
		
		System.out.println("\nClassfying the clusters...");
		ClusterCollection clusterCollection = ClusterCollection.getInstance();
		
		for(int i = 0; i < clusters.size(); i++) {
			System.out.println();
			System.out.println("Cluster " + i);
			double optimismAvg = averageOptimism(clusters.get(i));
			double reliabilityAvg = averageReliability(clusters.get(i));
			
			System.out.println("Optimism Average: " + optimismAvg);
			System.out.println("Reliability Average: " + reliabilityAvg);
			
			Cluster cluster = new Cluster();
			
			cluster.setCluster(i);
			
			// CLASSIFY BASED ON THRESHOLD
			
			// OPTIMISM
			if(optimismAvg > Constants.OPTIMISM_THRESHOLD) {
				cluster.setOptimismClass(Constants.HIGH_OPTIMISM);
			} else {
				cluster.setOptimismClass(Constants.LOW_OPTIMISM);
			}
			
			// RELIABILITY
			if(reliabilityAvg > Constants.RELIABILITY_THRESHOLD) {
				cluster.setReliabilityClass(Constants.HIGH_RELIABILITY);
			} else {
				cluster.setReliabilityClass(Constants.LOW_RELIABILITY);
			}
			
			System.out.println("Adding to DB...");
			System.out.println(cluster);
			
			// ADD TO DB
			clusterCollection.add(cluster);
		}
		
		System.out.println();
		System.out.println("CLASSIFICATION COMPLETE!");
		System.out.println("x----------------------x");
	}
	
	private static List<List<User>> getAllClusters() {
		List<List<User>> clusters = new ArrayList<List<User>>();
		
		System.out.println("Retrieving all clusters...");
		
		for(int i = 0; i < Constants.NUM_CLUSTERS; i++) {
			clusters.add(getCluster(i));
		}
		
		System.out.println("All clusters retrieved!");
		
		return clusters;
	}
	
	private static List<User> getCluster(int n) {
		List<User> usersInCluster = new ArrayList<User>();
		
		// Extracting all users from cluster n
		System.out.println();
		System.out.println("Extracting cluster " + n + " users...");
		List<UserCluster> cluster = 
				UserClusterCollection.getInstance().findUsersByCluster(n);
		
		System.out.println("Size of cluster " + n + ": " + cluster.size());
		
		System.out.println("Retrieving User Details...");
		
		// Retrieve details for each user in cluster
		UserCollection userColl = UserCollection.getInstance();
		
		for(UserCluster userCluster: cluster) {
			User user = new User();
			user.setName(userCluster.getName());
			
			User retrievedUser = userColl.find(user);
			
			usersInCluster.add(retrievedUser);
		}
		
		System.out.println("Number of user details retrieved: " + 
				usersInCluster.size());
		
		return usersInCluster;
	}
	
	public static void optimismSort(List<List<User>> clusters) {
		clusters.sort(new Comparator<List<User>>() {

			@Override
			public int compare(List<User> cluster1, List<User> cluster2) {
				
				double totalOpt1 = 0;
				double avgOpt1 = 0;
				
				for(User user: cluster1) {
					totalOpt1 += user.getOptimism();
				}
				
				avgOpt1 = totalOpt1/cluster1.size();
				
				double totalOpt2 = 0;
				double avgOpt2 = 0;
				
				for(User user: cluster2) {
					totalOpt2 += user.getOptimism();
				}
				
				avgOpt2 = totalOpt2/cluster2.size();
				
				if(avgOpt1 > avgOpt2) {
					return 1;
				} else if(avgOpt1 < avgOpt2) {
					return -1;
				} else {
					return 0;
				}
			
			}
		});
	}
	
	public static void reliabilitySort(List<List<User>> clusters) {
		clusters.sort(new Comparator<List<User>>() {

			@Override
			public int compare(List<User> cluster1, List<User> cluster2) {
				
				double totalRel1 = 0;
				double avgRel1 = 0;
				
				for(User user: cluster1) {
					totalRel1 += user.getReliability();
				}
				
				avgRel1 = totalRel1/cluster1.size();
				
				double totalRel2 = 0;
				double avgRel2 = 0;
				
				for(User user: cluster2) {
					totalRel2 += user.getReliability();
				}
				
				avgRel2 = totalRel2/cluster2.size();
				
				if(avgRel1 > avgRel2) {
					return 1;
				} else if(avgRel1 < avgRel2) {
					return -1;
				} else {
					return 0;
				}
			
			}
		});
	}
	
	public static double averageOptimism(List<User> cluster) {
		double totalOpt = 0;
		double avgOpt = 0;
		
		for(User user: cluster) {
			totalOpt += user.getOptimism();
		}
		
		avgOpt = totalOpt/cluster.size();
		
		return avgOpt;
	}
	
	public static double averageReliability(List<User> cluster) {
		double totalRel = 0;
		double avgRel = 0;
		
		for(User user: cluster) {
			totalRel += user.getReliability();
		}
		
		avgRel = totalRel/cluster.size();
		
		return avgRel;
	}
	
	public static void main(String[] args) {
		classify();
	}

}
