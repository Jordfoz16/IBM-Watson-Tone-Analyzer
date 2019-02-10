package com.jordan.watson;

import java.util.ArrayList;
import java.util.Iterator;

import com.jordan.API_File;
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
    private ToneAnalysis toneAnalysis;
    private String api_key;

    public Watson(String api_key){
        this.api_key = api_key;
    }

    private void watsonToneAnalysis(String text) {

        IamOptions options = new IamOptions.Builder()
                .apiKey(api_key)
                .build();

        ToneAnalyzer toneAnalyzer = new ToneAnalyzer("2017-09-21", options);

        toneAnalyzer.setEndPoint("https://gateway-lon.watsonplatform.net/tone-analyzer/api");

        ToneOptions toneOptions = new ToneOptions.Builder()
                .text(text)
                .build();

        toneAnalysis = toneAnalyzer.tone(toneOptions).execute();
    }

    public String getResult(String text){

        watsonToneAnalysis(text);

        JSONParser parser = new JSONParser();

        StringBuilder ss = new StringBuilder();

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
                ss.append("Emotion: " + myList.get(i).get("tone_name") + "\n");
                ss.append("Score: " + myList.get(i).get("score") + "\n\n");
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ss.toString();
    }
}
