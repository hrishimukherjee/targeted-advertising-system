package edu.carleton.comp4601.utils;

import java.util.List;

import edu.carleton.comp4601.dao.UserClusterCollection;
import edu.carleton.comp4601.model.Advertisement;
import edu.carleton.comp4601.model.Cluster;
import edu.carleton.comp4601.model.User;
import edu.carleton.comp4601.model.UserCluster;

/**
 * @author Hrishi Mukherjee (100888108) & Yonathan Kidanemariam (100890519).
 */

public class HTMLBuilder {
	
	private static final String CONTEXT = "CONTEXT";
	private static final String APP_CONTEXT = "WEB APP CONTEXT";
	private static final String ADVERTISEMENTS = "ADVERTISEMENTS";
	private static final String COMMUNITIES = "COMMUNITIES";
	
	/**
	 * Builds a three column HTML table using the 
	 * user's details.
	 * 
	 * @param users the list of users to be used
	 * @return HTML representation of users' details
	 */
	public static String buildContextTable(List<User> users) {
		StringBuilder htmlDoc = new StringBuilder();
		
		// BUILD HEADER
		htmlDoc.append("<html>");
		htmlDoc.append("<head><title>" + CONTEXT + "</title></head>");
		
		// BUILD BODY
		htmlDoc.append("<body>");
		
		htmlDoc.append("<b><font color=\"royalblue\" size=\"6\" face=\"verdana\">" + "<center>" +
				APP_CONTEXT + "</center>" + "</font></b>");
		htmlDoc.append("<br/>");
		htmlDoc.append("<br/>");
		
		htmlDoc.append("<table style=\"width:100%\" border=\"1\">");
		
		// Insert Table Headers
		htmlDoc.append("<tr>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"USERS" + "</font></b>"  + "</th>");
		htmlDoc.append("<th colspan=\"2\">" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"FEATURES" + "</font></b>"  + "</th>");
		htmlDoc.append("</tr>");
		
		htmlDoc.append("<tr>");
		htmlDoc.append("<th></th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"2\" face=\"verdana\">" +
				"OPTIMISM" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"2\" face=\"verdana\">" +
				"RELIABILITY" + "</font></b>"  + "</th>");
		htmlDoc.append("</tr>");
		
		// Insert Table Rows
		for(User user: users) {
			htmlDoc.append("<tr>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(user.getName());
			htmlDoc.append("</td>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(user.getOptimism());
			htmlDoc.append("</td>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append(user.getReliability());
			htmlDoc.append("</td>");
			
			htmlDoc.append("</tr>");
		} 
		
		htmlDoc.append("</table>");
		htmlDoc.append("</body>");
		
		return htmlDoc.toString();
	}
	
	public static String buildAdCategory(List<Advertisement> ads) {
		StringBuilder response = new StringBuilder();
		response.append("<html>");
		response.append("<head><title>ADVERTISEMENTS</title></head>");
		response.append("<body>");
		response.append("<b><font color=\"red\" size=\"6\" face=\"verdana\">" +
				ADVERTISEMENTS + "</font></b>");
		
		if(ads == null || !ads.isEmpty()) {
			response.append("<ul>");
			
			for(Advertisement ad: ads) {
				response.append("<li>");
				response.append(ad.getTitle());
				response.append("</li>");
			}
			
			response.append("</ul>");
		} else {
			response.append("</br>");
			response.append("</br>");
			response.append("No document(s) found.");
		}
		
		response.append("</body>");
		
		return response.toString();
	}
	
	public static String augmentAdvertisement(String original, List<Advertisement> ads) {
		StringBuilder htmlDoc = new StringBuilder();
		
		htmlDoc.append("<html>");
		
		htmlDoc.append("<head>");
		
		htmlDoc.append("<title>");
		htmlDoc.append("TESTER");
		htmlDoc.append("</title>");
		
		htmlDoc.append("<link rel=\"stylesheet\" "
				+ "type=\"text/css\" "
				+ "href=\"http://localhost:8080/COMP4601-RS/theme.css\">");
		
		htmlDoc.append("</head>");
		
		htmlDoc.append("<body>");
		
		htmlDoc.append("<div class=\"wrap\">");
		
		htmlDoc.append("<div class=\"fleft\">");
		htmlDoc.append(original);
		htmlDoc.append("</div>");
		
		htmlDoc.append("<div class=\"fright\">");
		
		// Append all the Advertisement Blocks
		for(Advertisement ad: ads) {
			htmlDoc.append("<div class=\"ad\">");
			
			htmlDoc.append("<div class=\"ad_text\">");
			htmlDoc.append("<p><center>" + ad.getCaption() + "</center><p>");
			htmlDoc.append("</div>");
			
			htmlDoc.append("<div class=\"ad_visual\">");
			htmlDoc.append("<img src=\"" + ad.getVisual() + "\">");
			htmlDoc.append("</div>");
			
			htmlDoc.append("</div>");
		}
		
		htmlDoc.append("</div>");
		
		
		htmlDoc.append("</div>");
		
		htmlDoc.append("</body>");
		
		htmlDoc.append("</html>");
		
		return htmlDoc.toString();		
	}
	
	public static String buildCommunitiesTable(List<Cluster> communities) {
		StringBuilder htmlDoc = new StringBuilder();
		
		// BUILD HEADER
		htmlDoc.append("<html>");
		htmlDoc.append("<head><title>" + COMMUNITIES + "</title></head>");
		
		// BUILD BODY
		htmlDoc.append("<body>");
		
		htmlDoc.append("<b><font color=\"royalblue\" size=\"6\" face=\"verdana\">" + "<center>" +
				COMMUNITIES + "</center>" + "</font></b>");
		htmlDoc.append("<br/>");
		htmlDoc.append("<br/>");
		
		htmlDoc.append("<table style=\"width:100%\" border=\"1\">");
		
		// Insert Table Headers
		htmlDoc.append("<tr>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"COMMUNITIES" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"OPTIMISM" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"RELIABILITY" + "</font></b>"  + "</th>");
		htmlDoc.append("<th>" + "<b><font color=\"seagreen\" size=\"4\" face=\"verdana\">" +
				"USERS" + "</font></b>"  + "</th>");
		htmlDoc.append("</tr>");
		
		// Insert Table Rows
		for(Cluster cluster: communities) {
			htmlDoc.append("<tr>");
			
			htmlDoc.append("<td align=\"center\">");
			htmlDoc.append("C" + cluster.getCluster());
			htmlDoc.append("</td>");
			
			// Append reliability class
			htmlDoc.append("<td align=\"center\">");
			if(cluster.getOptimismClass() == Constants.HIGH_OPTIMISM) {
				htmlDoc.append("HIGH");
			} else if(cluster.getOptimismClass() == Constants.LOW_OPTIMISM) {
				htmlDoc.append("LOW");
			}
			htmlDoc.append("</td>");
			
			//Append reliability class
			htmlDoc.append("<td align=\"center\">");
			if(cluster.getReliabilityClass() == Constants.HIGH_RELIABILITY) {
				htmlDoc.append("HIGH");
			} else if(cluster.getReliabilityClass() == Constants.LOW_RELIABILITY) {
				htmlDoc.append("LOW");
			}
			htmlDoc.append("</td>");
			
			htmlDoc.append("<td align=\"center\">");
			
			//Append clusters users, optimism, and reliability
			List<UserCluster> users = UserClusterCollection.getInstance().findUsersByCluster(cluster.getCluster());
			
			for(UserCluster user: users) {
				htmlDoc.append(user.getName() + ", ");
			}
			
			
			htmlDoc.append("</td>");
			
			htmlDoc.append("</tr>");
		}
		
		htmlDoc.append("</table>");
		htmlDoc.append("</body>");
		
		return htmlDoc.toString();
	}
	
	/**
	 * Builds an HTML String representing
	 * a failure page.
	 * 
	 * @param message error message to be shown to user
	 * @return HTML page as a String
	 */
	public static String buildFailure(String errorMessage) {
		StringBuilder response = new StringBuilder();
		response.append("<html>");
		response.append("<head><title>ERROR</title></head>");
		response.append("<body><b><font color=\"red\" size=\"6\" face=\"verdana\">" +
				errorMessage + "</font></b></body>");
		return response.toString();
	}

}
