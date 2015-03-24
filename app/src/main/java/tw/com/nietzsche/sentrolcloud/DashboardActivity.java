package tw.com.nietzsche.sentrolcloud;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by macmini on 15/3/18.
 */
public class DashboardActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);

//
//
//
//        //四顆按鈕
//        ImageButton bottommenudashboard = (ImageButton)findViewById(R.id.bottommenudashboard);
//        ImageButton bottommenusetting = (ImageButton)findViewById(R.id.bottommenusetting);
//        ImageButton bottommenuaccount = (ImageButton)findViewById(R.id.bottommenuaccount);
//        ImageButton bottommenuevent = (ImageButton)findViewById(R.id.bottommenuevent);
//
//
//        String strJson = "{\"DachBoardListArray\": [\n" +
//                "                        {\n" +
//                "                        \"className\": \"臥室\",\n" +
//                "                        \"classImage\": \"Bed_Room\",\n" +
//                "                        \"gatewayList\": [{\n" +
//                "                                        \"type\": \"Door\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Glass\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Motion\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"off\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Gas\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },{\n" +
//                "                                        \"type\": \"Camera\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":1,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Humidity\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"14\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Smoke\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Motion2\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Siren\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"off\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"CO2\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"off\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Temperature\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"23\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        }]\n" +
//                "                        },{\n" +
//                "                        \"className\": \"二樓臥室\",\n" +
//                "                        \"classImage\": \"Bed_Room\",\n" +
//                "                        \"gatewayList\": [{\n" +
//                "                                        \"type\": \"Curtain\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Humidity\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"21\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Lamp\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Temperature\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"21\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },{\n" +
//                "                                        \"type\": \"Curtain\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":1,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Camera\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Lamp\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"off\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        }]\n" +
//                "                        },\n" +
//                "                        {\n" +
//                "                        \"className\": \"一樓客廳\",\n" +
//                "                        \"classImage\": \"Living_Room\",\n" +
//                "                        \"gatewayList\": [{\n" +
//                "                                        \"type\": \"Curtain\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Humidity\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"29\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Lamp\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Temperature\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"19\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },{\n" +
//                "                                        \"type\": \"Curtain\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":1,\n" +
//                "                                        \"night\":1,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Camera\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"off\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":0\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Lamp\",\n" +
//                "                                        \"name\": \"152DJ\",\n" +
//                "                                        \"status\": \"on\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        },\n" +
//                "                                        {\n" +
//                "                                        \"type\": \"Temperature\",\n" +
//                "                                        \"name\": \"15D4G\",\n" +
//                "                                        \"status\": \"15\",\n" +
//                "                                        \"away\":0,\n" +
//                "                                        \"night\":0,\n" +
//                "                                        \"home\":1\n" +
//                "                                        }]\n" +
//                "                        }]\n" +
//                "}\n" +
//                "\n" +
//                "\n" +
//                "\n";
//
//
//
//
//
//
//        /** The parsing of the xml data is done in a non-ui thread */
//        ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();
//
//        /** Start parsing xml data */
//        listViewLoaderTask.execute(strJson);
    }



    private class ListViewLoaderTask extends AsyncTask<String, Void, SimpleAdapter> {
        JSONObject jObject;
        /** Doing the parsing of xml data in a non-ui thread */
        @Override
        protected SimpleAdapter doInBackground(String... strJson) {
            try{
                jObject = new JSONObject(strJson[0]);
                CountryJSONParser countryJsonParser = new CountryJSONParser();
                countryJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("JSON Exception1", e.toString());
            }

            CountryJSONParser countryJsonParser = new CountryJSONParser();

            List<HashMap<String, String>> countries = null;

            try{
                /** Getting the parsed data as a List construct */
                countries = countryJsonParser.parse(jObject);
            }catch(Exception e){
                Log.d("Exception",e.toString());
            }

            /** Keys used in Hashmap */
            String[] from = { "className","classImage"};

            /** Ids of views in listview_layout */
            int[] to = { R.id.tv_country,R.id.tv_country_details};

            /** Instantiating an adapter to store each items
                         *  R.layout.listview_layout defines the layout of each item
                         */
            SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), countries, R.layout.lv_layout, from, to);

            return adapter;

        }

        /** Invoked by the Android system on "doInBackground" is executed completely */
        /** This will be executed in ui thread */
        @Override
        protected void onPostExecute(SimpleAdapter adapter) {
            /** Getting a reference to listview of main.xml layout file */
            //ListView listView = ( ListView ) findViewById(R.id.lv_className);

            /** Setting the adapter containing the country list to listview */
           // listView.setAdapter(adapter);


        }
    }


}
