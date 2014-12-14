package aw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

public class Lab_14_Main {
	
	private static URL topVidsURL = null;
	private static final File topVidsFile = new File("youtubeTopVids.json");
	private static final String urlStr = "https://gdata.youtube.com/feeds/api/standardfeeds/most_popular?v=2&alt=json";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		downloadTopVids();
		
		printTop10VidsInfo();
		

	}

	private static void printTop10VidsInfo() {
		
		JSONParser parser = new JSONParser();
		 
		try {
	 
			Object obj = parser.parse(new FileReader(topVidsFile.getAbsoluteFile()));
			
			JSONObject jsonObject = (JSONObject) obj;
	 
			JSONObject feed = (JSONObject) jsonObject.get("feed");
			JSONObject title = (JSONObject) feed.get("title");
			System.out.println(title.get("$t"));
			
			JSONArray jsonObject2 = (JSONArray) jsonObject.get("entry");
			// TODO Loop through 10 times for top 10 vids
			Iterator<JSONObject> iter = jsonObject2.iterator();
			
//			JSONObject jsonArrayTitles = (JSONObject) jsonObject2.get("title");
//			JSONObject jsonArrayViews = (JSONObject) jsonObject2.get("yt$statistics");
//			JSONObject jsonArrayTitles = (JSONObject) jsonObject.get("title");
//			JSONObject jsonArrayViews = (JSONObject) jsonObject.get("yt$statistics");
//			System.out.println("Title: " + jsonArrayTitles.get("$t"));
//			System.out.println("VC: " + jsonArrayViews.get("viewCount"));
//			
//			Iterator<JSONObject> iter = jsonArrayTitles.iterator();
//			Iterator<JSONObject> iter2 = jsonArrayViews.iterator();
			
			 
			
			// take each value from the json array separately
//			for(int i = 0; i < 10; i++){
//					JSONObject innerObjTitle = (JSONObject) iter.next();
//					JSONObject innerObjViews = (JSONObject) iter2.next();
//					System.out.println(innerObjTitle.get("$t") + " has " + 
//							Integer.parseInt(innerObjViews.get("viewCount").toString()) + " views.");
//			}

			
//			for(int i = 0; i < 10; i++){
//				if(jsonArrayTitles.get(i) != null && jsonArrayViews.get(i) != null){
//					System.out.println(jsonArrayTitles.get(i).toString() + " has " + 
//							Integer.parseInt(jsonArrayViews.get(i).toString()) + " views.");
//				}
//			}
	 
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void downloadTopVids() {
		
		try {
			topVidsURL = new URL(urlStr);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		
		try {
			org.apache.commons.io.FileUtils.copyURLToFile(topVidsURL, topVidsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
