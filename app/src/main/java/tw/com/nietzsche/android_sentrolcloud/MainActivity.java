package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends Activity {


    //登入成功後儲存
    private SharedPreferences settings;
    private static final String data = "LoginDATA";
    private static final String accountSet = "account";
    private static final String passwordSet = "password";


    Handler mHandler;

    public boolean running = true;

    private MyAsyncTask mTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Text_email = (EditText)findViewById(R.id.Text_email);
        Text_password = (EditText)findViewById(R.id.Text_password);
        sendButton = (Button)findViewById(R.id.button_sendPost);


        settings = getSharedPreferences(data,0);
        Text_email.setText(settings.getString(accountSet, ""));
        Text_password.setText(settings.getString(passwordSet, ""));



        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //輸入eamil
    private EditText Text_email;
    private EditText Text_password;
    private Button sendButton;
    private ProgressBar pb;

    //網址
    private String urlLogin = "http://www.sentrolcloud.com/app/getinformation.php";




    //開著冊頁
    public void openRegistView(View view) {
        if(checkCmNetwork ()){
            Intent newAct = new Intent();
            newAct.setClass( MainActivity.this, RegistActivity.class );
            startActivity(newAct);
        }

    }

    //登入
    public void singInClick(View view) {
        if(checkCmNetwork ()){
            //post到雲端
            String mail = null;
            String pass = null;
            mail = Text_email.getEditableText().toString();
            pass = Text_password.getEditableText().toString();


            String[] Loginvalues = {mail,pass};



            if(Text_email.getText().toString().length()<1 && Text_password.getText().toString().length()<1){
                Toast.makeText(this, "please enter email & passwork", Toast.LENGTH_LONG).show();
            }else if(Text_email.getText().toString().length()<1 && Text_password.getText().toString().length()>0){
                Toast.makeText(this, "please enter email", Toast.LENGTH_LONG).show();
            }else if(Text_email.getText().toString().length()>0 && Text_password.getText().toString().length()<1){
                Toast.makeText(this, "please enter passwork", Toast.LENGTH_LONG).show();
            }else{
                pb.setVisibility(View.VISIBLE);

                running = true;

                //處理
                new MyAsyncTask().execute(Loginvalues);



//                mHandler = new Handler();
//                mHandler.post(runnable);


            }
        }
    }





    private class MyAsyncTask extends AsyncTask<String, Integer, String[]> {




        @Override
        protected String[] doInBackground(String... params) {

            Log.d("doInBackground","doInBackground");

            while (running) {
                // does the hard work

                // TODO Auto-generated method stub
                //postData(params[0].toString(),params[1].toString());
                //sendPostDataToInternet(params[0].toString(),params[1].toString());
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://www.sentrolcloud.com/app/getinformation.php");
                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("account",params[0].toString()));
                nameValuePair.add(new BasicNameValuePair("password", params[1].toString()));
                nameValuePair.add(new BasicNameValuePair("command", "login"));

                try {
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                    HttpResponse response = httpClient.execute(httpPost);
                    // write response to log

                    //取得回應 解析httpResponse

                    if (response.getStatusLine().getStatusCode() == 200) {

                        String strResult = EntityUtils.toString(response.getEntity());

                        JSONObject jsonObj = new JSONObject(strResult);

                        String Jresult = jsonObj.get("result").toString();

                        Log.d("上傳結果",Jresult);

                        if(Jresult.equals("1")){

                            //存本機
                            settings = getSharedPreferences(data,0);
                            settings.edit()
                                    .putString(accountSet, Text_email.getText().toString())
                                    .putString(passwordSet, Text_password.getText().toString())
                                    .commit();


                            Log.d("正確登入", "正確登入");
                            //開啟控制台
                            cancel(true);
                            running = false;
                            openDashboard();

                        }else{

                            Log.d("錯誤登入", "錯誤登入");

                            cancel(true);

                            running = false;

                            LoginError();

                            //onCancelled(true);

                        }


//                    JSONArray OBJnMAE = jsonObj.names();
//                    for(int i = 0;i<OBJnMAE.length();i++ ){
//                        if(OBJnMAE.getString(i).equals("result")){
//
//
//                        }
//                    }

                    }
                } catch (org.apache.http.client.ClientProtocolException  e) {
                    // Log exception
                    e.printStackTrace();
                } catch (IOException e) {
                    // Log exception
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }




    }


    //檢查網路
    private boolean checkCmNetwork() {
        Boolean result;
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null){
            result = true;
        }else{

            //無網路提醒
            Toast.makeText(MainActivity.this, getString(R.string.No_networking), Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }



    //控制台
    public void openDashboard() {
        Intent newAct = new Intent();
        newAct.setClass( MainActivity.this, DashboardActivity.class );
        startActivity(newAct);
        MainActivity.this.finish();

    }

    //出現錯誤提醒
    //控制台
    public void LoginError() {
       // Toast.makeText(MainActivity.this, getString(R.string.LoginError), Toast.LENGTH_SHORT).show();
        pb.setVisibility(View.GONE);
    }


}
