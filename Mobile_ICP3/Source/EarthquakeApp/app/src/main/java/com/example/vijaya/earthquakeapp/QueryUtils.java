package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder strings = new StringBuilder();

        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            url = new URL(requestUrl);
            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse)

            //Get a connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            //Get inputstream from connection
            InputStream inputstream = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));

            String Line ="";
            while ((Line = reader.readLine()) != null){
                strings.append(Line);
            }

            jsonResponse = strings.toString();




            /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
             */

            // Parsing jSON Object
            JSONObject jsonObject = new JSONObject(jsonResponse);

            JSONArray jsonAarray = (JSONArray) jsonObject.get("features");

            // Iterate features list
            for( int i =0;i < jsonAarray.length(); i++) {
                JSONObject json = jsonAarray.getJSONObject(i).getJSONObject("properties");
                System.out.println(json);

                Earthquake earthquake = new Earthquake((double) json.get("mag"), (String) json.get("place"), (long) json.get("time"), (String) json.get("url"));

                earthquakes.add(earthquake);

            }

            // Return the list of earthquakes

        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
