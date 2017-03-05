package com.robertkiszelirk.cornergrocer;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

// OPEN AND READ THE JSON FILE THAT HOLDS ALL DATA
class HandleJson {

    // CREATE A LIST TO STORE ALL PRODUCES
    private ArrayList<BaseProduce> producesList;
    //SETTING BASE JSON DATA
    void setJSON(Context context){
        // DEFINE BASE JSON STRING
        String json = null;
        // TRY TO READ JSON FILE
        try {
            //SET JSON FILE NAME
            //GET CURRENT LANGUAGE
            String language = Locale.getDefault().getLanguage();
            String file;
            //SELECT LANGUAGE DATA
            if(language.equals("hu")){
                file = "datahu.json";
            }else{
                file = "data.json";
            }
            //OPEN AND READ JSON
            InputStream is = context.getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // TRY TO SET BASE PRODUCES LIST
        try {
            //GET THE PRODUCES
            JSONArray produces = new JSONArray(json);
            producesList = new ArrayList<>();
            //ITERATE THROUGH ALL PRODUCE
            for(int i = 0; i < produces.length(); i++){
                //CREATE PRODUCE JSONOBJECT
                JSONObject produce = produces.getJSONObject(i);
                //CREATE BASE PRODUCE OBJECT
                BaseProduce baseProduce = new BaseProduce(produce.getString("produceName"),
                        ""+ produce.getString("sellingPrice")+ produce.getString("currency") + "/" + produce.getString("unit"),
                        produce.getString("picture"));
                //ADD BASE PRODUCE TO PRODUCE LIST
                producesList.add(baseProduce);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    //RETURN BASE PRODUCE LIST
    ArrayList<BaseProduce> getProducesList(){
        return producesList;
    }
}
