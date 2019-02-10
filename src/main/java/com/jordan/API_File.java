package com.jordan;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class API_File {

    private File apiFile = new File("api_key.json");

    private String template = "{\n" +
            "   \"api_key\":\"Enter key here\"\n" +
            "}";

    private void checkFile(){
        if(!apiFile.exists()) {
            PrintWriter writer = null;

            try {
                writer = new PrintWriter("api_key.json", "UTF-8");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            writer.write(template);
            writer.close();
        }
    }

    public String readAPI(){

        checkFile();

        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(new FileReader("api_key.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = (JSONObject) obj;
        String api_Key = jsonObject.get("api_key").toString();

        return api_Key;
    }
}
