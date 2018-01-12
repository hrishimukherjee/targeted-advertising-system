package edu.carleton.comp4601.utils;

import edu.carleton.comp4601.dao.AdvertisementCollection;
import edu.carleton.comp4601.model.Advertisement;

public class AdvertismentFactory {
	
	public static void createAds() {
		System.out.println("x----------------------x");
		System.out.println("CREATING ADS...");
		System.out.println();
		
		Advertisement ad1 = new Advertisement();
		ad1.setTitle("Management Role Hire");
		ad1.setCaption("HIRING PEOPLE FOR MANAGEMENT ROLES! CONTACT 1-800-HIGHLY-RELIABLE NOW!");
		ad1.setVisual("http://www.incrementa.ca/wp-content/uploads/2015/12/8737945758_ab6a66e4b7_o.jpg");
		ad1.setOptimismClass(null);
		ad1.setReliabilityClass(Constants.HIGH_RELIABILITY);

		Advertisement ad2 = new Advertisement();
		ad2.setTitle("Police Force Hire");
		ad2.setCaption("Have you ever considered joining the police force?");
		ad2.setVisual("https://s-media-cache-ak0.pinimg.com/originals/a7/75/e5/a775e5e398da98a1eb78f84e22b42fe5.jpg");
		ad2.setOptimismClass(null);
		ad2.setReliabilityClass(Constants.HIGH_RELIABILITY);
		
		Advertisement ad3 = new Advertisement();
		ad3.setTitle("Reliability Training");
		ad3.setCaption("Have people been saying that you are unreliable? WELL prove them wrong! Get Trained Now!");
		ad3.setVisual("https://www.profferbrainchild.com/sites/default/files/public/training-assets/icons/text-plaque/reliability-icon-t_0.png");
		ad3.setOptimismClass(null);
		ad3.setReliabilityClass(Constants.LOW_RELIABILITY);
		
		Advertisement ad4 = new Advertisement();
		ad4.setTitle("Indian James Bond");
		ad4.setCaption("THE INDIAN JAMES BOND STARRING HRISHI MUCKERBARN NOW PLAYING");
		ad4.setVisual("https://regmedia.co.uk/2015/11/19/sanskari_james_bond_teaser.jpg?x=648&y=348&crop=1");
		ad4.setOptimismClass(Constants.HIGH_OPTIMISM);
		ad4.setReliabilityClass(null);
		
		Advertisement ad5 = new Advertisement();
		ad5.setTitle("Sham Wow");
		ad5.setCaption("SHAM WOW!");
		ad5.setVisual("https://media3.s-nbcnews.com/j/streams/2013/september/130906/8c8881134-shamwowguy.nbcnews-fp-1200-800.jpg");
		ad5.setOptimismClass(Constants.HIGH_OPTIMISM);
		ad5.setReliabilityClass(null);
		
		Advertisement ad6 = new Advertisement();
		ad6.setTitle("Hike Fuji");
		ad6.setCaption("Go for a hike up Mount Fuji for ONLY $50,000!");
		ad6.setVisual("https://www.jtbgenesis.com/pic/tour/141231Mt.fuji.Mitsutouge.jpg");
		ad6.setOptimismClass(Constants.HIGH_OPTIMISM);
		ad6.setReliabilityClass(null);
		
		Advertisement ad7 = new Advertisement();
		ad7.setTitle("Adopt Pet");
		ad7.setCaption("Adopt a pet and make your life a lot happier!");
		ad7.setVisual("https://s-media-cache-ak0.pinimg.com/originals/64/e5/ca/64e5caca8b538e6f2bb3608352949915.jpg");
		ad7.setOptimismClass(Constants.LOW_OPTIMISM);
		ad7.setReliabilityClass(null);
		
		System.out.println(ad1);
		System.out.println(ad2);
		System.out.println(ad3);
		System.out.println(ad4);
		System.out.println(ad5);
		System.out.println(ad6);
		System.out.println(ad7);
		
		// Add to DB
		System.out.println("Adding to DB...");
		
		AdvertisementCollection collection = AdvertisementCollection.getInstance();
		
		collection.add(ad1);
		collection.add(ad2);
		collection.add(ad3);
		collection.add(ad4);
		collection.add(ad5);
		collection.add(ad6);
		collection.add(ad7);

		System.out.println();
		System.out.println("AD CREATION COMPLETE!");
		System.out.println("x----------------------x");
	}

}
