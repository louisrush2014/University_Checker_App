package com.example.android.universitychecker;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by jadaa on 1/22/2018.
 */

public class NetworkUtil {
    private static final String URL_STRING = "https://api.data.gov/ed/collegescorecard/v1/schools.json";
    private static final String API_KEY = "hTK1R7XCPBR6ZWZDopwkPOhL2NofhyFduTww5dpJ";
    private static final String API_PARAM = "api_key";
    private static final String TAG = NetworkUtil.class.getSimpleName();
    private static final String DEGREES_AWARDED = "school.degrees_awarded.predominant";
    private static String degreeAwardedValue = "2";
    private static String FIELDS = "_fields";
    private static String ID = "id";
    private static String SCHOOL_NAME = "school.name";
    private static String SCHOOL_YEAR_SIZE = "2013.student.size"; //default to now
    private static String FIELDS_VALUE = SCHOOL_NAME + ","+SCHOOL_YEAR_SIZE;
    public static URL buildUrl(){
        Uri uri = Uri.parse(URL_STRING).buildUpon()

                .appendQueryParameter(DEGREES_AWARDED,degreeAwardedValue)
                .appendQueryParameter(FIELDS,FIELDS_VALUE)
                .appendQueryParameter(API_PARAM,API_KEY)
                .build();
        URL url = null;
        try{
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getSchoolYearSize(){
        return SCHOOL_YEAR_SIZE;
    }


public static String URLResponse(URL url) throws IOException{
    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
    System.out.println(url.toString());
    try{
        InputStream in = connect.getInputStream();
        Scanner scan = new Scanner(in);
        scan.useDelimiter("\\A");
        boolean hasInput = scan.hasNext();
        if (hasInput) {
            return scan.next();
        } else {
            return null;
        }
    } finally {
        connect.disconnect();
    }
}

}





