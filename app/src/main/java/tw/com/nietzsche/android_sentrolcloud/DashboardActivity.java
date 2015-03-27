package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by macmini on 15/3/18.
 */
public class DashboardActivity extends Activity {


    List<String> itemname = new ArrayList<String>();//主文字
    List<String> sectionType = new ArrayList<String>();//判斷是否Section
    List<String> sectionImageName = new ArrayList<String>();//Section 標圖
    List<String> name = new ArrayList<String>();//Section 標圖



    String[] imagename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dashboard);


        ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
        //JSON Node Names
        final String className = "className";
        final String classImage = "classImage";
        final String gatewayList = "gatewayList";

        JSONArray android = null;


        ListView list;




        //四顆按鈕
        ImageButton bottommenudashboard = (ImageButton)findViewById(R.id.bottommenudashboard);
        ImageButton bottommenusetting = (ImageButton)findViewById(R.id.bottommenusetting);
        ImageButton bottommenuaccount = (ImageButton)findViewById(R.id.bottommenuaccount);
        ImageButton bottommenuevent = (ImageButton)findViewById(R.id.bottommenuevent);


        String strJson = "{\"DachBoardListArray\": [\n" +
                "                        {\n" +
                "                        \"className\": \"臥室\",\n" +
                "                        \"classImage\": \"Bed_Room\",\n" +
                "                        \"gatewayList\": [{\n" +
                "                                        \"type\": \"Door\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Glass\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Motion\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"off\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Gas\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },{\n" +
                "                                        \"type\": \"Camera\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":1,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Humidity\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"14\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Smoke\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Motion2\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Siren\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"off\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"CO2\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"off\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Temperature\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"23\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        }]\n" +
                "                        },{\n" +
                "                        \"className\": \"二樓臥室\",\n" +
                "                        \"classImage\": \"Bed_Room\",\n" +
                "                        \"gatewayList\": [{\n" +
                "                                        \"type\": \"Curtain\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Humidity\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"21\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Lamp\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Temperature\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"21\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },{\n" +
                "                                        \"type\": \"Curtain\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":1,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Camera\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Lamp\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"off\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        }]\n" +
                "                        },\n" +
                "                        {\n" +
                "                        \"className\": \"一樓客廳\",\n" +
                "                        \"classImage\": \"Living_Room\",\n" +
                "                        \"gatewayList\": [{\n" +
                "                                        \"type\": \"Curtain\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Humidity\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"29\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Lamp\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Temperature\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"19\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":0\n" +
                "                                        },{\n" +
                "                                        \"type\": \"Curtain\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":1,\n" +
                "                                        \"night\":1,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Camera\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"off\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":0\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Lamp\",\n" +
                "                                        \"name\": \"152DJ\",\n" +
                "                                        \"status\": \"on\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                        \"type\": \"Temperature\",\n" +
                "                                        \"name\": \"15D4G\",\n" +
                "                                        \"status\": \"15\",\n" +
                "                                        \"away\":0,\n" +
                "                                        \"night\":0,\n" +
                "                                        \"home\":1\n" +
                "                                        }]\n" +
                "                        }]\n" +
                "}\n" +
                "\n" +
                "\n" +
                "\n";






        try {
            JSONObject jObj = new JSONObject(strJson);
            android =jObj.getJSONArray("DachBoardListArray");

            //分類總數
            for(int i = 0; i < android.length(); i++){
                JSONObject c = android.getJSONObject(i);
                String ver = c.getString(className);
                String ImageName = c.getString(classImage).toLowerCase();
                JSONArray gatewayListArray = c.getJSONArray("gatewayList");



                sectionType.add("setcion");//種類
                sectionImageName.add(format(ImageName));//標圖

                Log.d("    姊姊format",format(ImageName));
                itemname.add(ver);//標題
                name.add("");

//                HashMap<String, String> map = new HashMap<String, String>();
//                map.put(className, ver);
//                oslist.add(map);
//                list=(ListView)findViewById(R.id.dashBoard_list);
//                ListAdapter adapter = new SimpleAdapter(DashboardActivity.this, oslist,
//                        R.layout.list_section_dashboard,
//                        new String[] { className }, new int[] {
//                        R.id.section_title});
//                list.setAdapter(adapter);
//



                //分類內容物
                for(int g = 0; g < gatewayListArray.length(); g++){

                    JSONObject q = gatewayListArray.getJSONObject(g);
                    String ggg = q.getString("type");
                    String gggname = q.getString("name");
                    Log.d("    姊姊",ggg);



                    sectionType.add("cell");
                    sectionImageName.add("no");
                    itemname.add(ggg);
                    name.add(gggname);

                    // Adding value HashMap key => value
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    map.put(className, ggg);
//                    oslist.add(map);
//
//                    list=(ListView)findViewById(R.id.dashBoard_list);
//                    ListAdapter adapter = new SimpleAdapter(DashboardActivity.this, oslist,
//                            R.layout.list_cell_dashboard,
//                            new String[] { className }, new int[] {
//                            R.id.className});
//                    list.setAdapter(adapter);



//                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view,
//                                                int position, long id) {
//                            Toast.makeText(MainActivity.this, "You Clicked at "+oslist.get(+position).get("name"), Toast.LENGTH_SHORT).show();
//                        }
//                    });



                }






            }



            Custom_DashBoard_ListAdapter adapter=new Custom_DashBoard_ListAdapter(this,sectionType,sectionImageName,name, itemname, imagename);
            list=(ListView)findViewById(R.id.dashBoard_list);
            list.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }



        oslist = new ArrayList<HashMap<String, String>>();



        /** The parsing of the xml data is done in a non-ui thread */
        //ListViewLoaderTask listViewLoaderTask = new ListViewLoaderTask();

        /** Start parsing xml data */
        //listViewLoaderTask.execute(strJson);
    }



    //控制台
    public void openDashboard(View view) {

    }

    //設定
    public void openSetting(View view) {

        Intent newAct = new Intent();
        newAct.setClass( DashboardActivity.this, SettingActivity.class );
        startActivity(newAct);
        DashboardActivity.this.finish();

    }

    //事件
    public void openEvent(View view) {
        Intent newAct = new Intent();
        newAct.setClass( DashboardActivity.this, EventActivity.class );
        startActivity(newAct);
        DashboardActivity.this.finish();

    }

    //會員帳號
    public void openAccount(View view) {
        Intent newAct = new Intent();
        newAct.setClass( DashboardActivity.this, AccountActivity.class );
        startActivity(newAct);
        DashboardActivity.this.finish();

    }





    //解析
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
                Log.d("Exception", e.toString());
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
            ListView listView = (ListView) findViewById(R.id.dashBoard_list);

            /** Setting the adapter containing the country list to listview */
            listView.setAdapter(adapter);


        }
    }

    public static String format(String s){
        String str=s.replaceAll("[`~_!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……& amp;*（）——+|{}【】‘；：”“’。，、？|-]", "");
        return str;
    }


}
