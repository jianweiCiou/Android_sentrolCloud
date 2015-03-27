package tw.com.nietzsche.android_sentrolcloud;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountryJSONParser {

            /** Receives a JSONObject and returns a list */
            public List<HashMap<String,String>> parse(JSONObject jObject){

                JSONArray jCountries = null;
                try {
                    /** Retrieves all the elements in the 'countries' array */
                    jCountries = jObject.getJSONArray("DachBoardListArray");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                return getCountries(jCountries);
            }

    private List<HashMap<String, String>> getCountries(JSONArray jCountries){


        int countryCount = jCountries.length();
        List<HashMap<String, String>> countryList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> country = null;

        /** Taking each country, parses and adds to list object */
        for(int i=0; i<countryCount;i++){
            try {
                /** Call getCountry with country JSON object to parse the country */
                country = getCountry((JSONObject)jCountries.get(i));
                countryList.add(country);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return countryList;
    }
    /** Parsing the Country JSON object */
    private HashMap<String, String> getCountry(JSONObject jCountry){

        HashMap<String, String> country = new HashMap<String, String>();
        String className = "";
        String classImage= "";

        try {
            className = jCountry.getString("className");
            classImage = jCountry.getString("classImage");





            String details = "classImage : " + classImage + "\n";

            country.put("className", className);
            country.put("classImage", details);

            } catch (JSONException e) {
            e.printStackTrace();
            }
        return country;
       }
}
