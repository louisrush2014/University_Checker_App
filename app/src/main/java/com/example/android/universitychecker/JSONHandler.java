package com.example.android.universitychecker;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jadaa on 1/24/2018.
 */

public class JSONHandler {

    public static String[] jsonData(String jsonString, String schoolSize) throws JSONException{
        String error_string = "errors";
        String meta_data = "metadata";//data showing how many pages, total results
        String meta_data_total = "total";
        String meta_data_page = "page";
        String meta_per_page = "per_page";
        String results = "results"; //array of schools
        String school_name = "school.name";
        String student_size = schoolSize;


        String[] parsedData = null;
        JSONObject jsonObj = new JSONObject(jsonString);
    if(jsonObj.has(error_string)){
        //do later
        return null;
    }
        JSONArray schoolArray = jsonObj.getJSONArray(results);
        parsedData = new String[schoolArray.length()];
        for(int i = 0; i < schoolArray.length();i++){
            String schoolName;
            String size_of_school;
            JSONObject single_school = schoolArray.getJSONObject(i);
            schoolName = single_school.getString(school_name);
            size_of_school = String.valueOf(single_school.getInt(student_size));
            parsedData[i] = schoolName + "---" + size_of_school;
        }
return parsedData;
    }
}
