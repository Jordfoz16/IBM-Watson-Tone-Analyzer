package CreativeInc.Watson;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class Watson 
{
	
	public void start(String text) {
		IamOptions options = new IamOptions.Builder()
			    .apiKey("wEarLSXTHai8oJxQD8tyDOyoh54_7a6nMLQY05MeB805")
			    .build();
		
		ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);
	
		toneAnalyzer.setEndPoint("https://gateway-lon.watsonplatform.net/tone-analyzer/api");
		
		ToneOptions toneOptions = new ToneOptions.Builder()
				  .text(text)
				  .build();
	
		ToneAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute();
		//System.out.println(toneAnalysis);
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(toneAnalysis.toString());
			
			JSONObject tonesObject = (JSONObject) jsonObject.get("document_tone");
			
			JSONArray tonesArray = (JSONArray) tonesObject.get("tones");
			
			Iterator<String> toneIterator = tonesArray.iterator();
			
			ArrayList<JSONObject> myList = new ArrayList<JSONObject>();
			
			
			while(toneIterator.hasNext()) {
				
				Object temp = toneIterator.next();
				
				myList.add((JSONObject) temp);
			}
			
			for(int i = 0; i < myList.size(); i++) {
				System.out.println("Tone = " + myList.get(i).get("tone_name"));
	    		System.out.println("Score = " + myList.get(i).get("score"));
	    		System.out.println("--------------------------------------");
			}
			
	
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
}
