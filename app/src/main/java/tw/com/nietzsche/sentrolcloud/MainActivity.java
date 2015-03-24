package tw.com.nietzsche.sentrolcloud;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.UserDictionary;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpService;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.HttpsURLConnection;

//讀json


public class MainActivity extends Activity {


    //輸入eamil
    private EditText Text_email;

    private EditText Text_password;

    private Button sendButton;


    private ProgressBar pb;

    //網址
    private String urlLogin = "http://www.sentrolcloud.com/app/getinformation.php";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Text_email = (EditText)findViewById(R.id.Text_email);

        Text_password = (EditText)findViewById(R.id.Text_password);

        sendButton = (Button)findViewById(R.id.button_sendPost);


        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setVisibility(View.GONE);





            //网络管理类，可以判断是否能上网，以及网络类型
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null){
            //Toast.makeText(MainActivity.this, "连网正常"+info.getTypeName(), Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(MainActivity.this, "未连网", Toast.LENGTH_SHORT).show();
        }


    }

    private void readStream(InputStream in) {
    }


    public void clickOk(View view) {
        // 呼叫這個方法結束Activity元件
        finish();
    }


    public void openDashView(View view) {


        Intent newAct = new Intent();
        newAct.setClass( MainActivity.this, DashboardActivity.class );
        startActivity(newAct);


        // 結束原先的 Activity Class
        MainActivity.this.finish();
    }



    //控制台
    public void openDashView(){


    }

    public void singInClick(View view) {
        Log.d("登入","111");



        if(view == sendButton){
            //post到雲端
            String mail = null;
            String pass = null;

            mail = Text_email.getEditableText().toString();
            pass = Text_password.getEditableText().toString();

            //
            String[] Loginvalues = {mail,pass};

            new MyAsyncTask().execute(Loginvalues);


            if(Text_email.getText().toString().length()<1){
                Toast.makeText(this, "please enter something", Toast.LENGTH_LONG).show();

            }else{
                pb.setVisibility(View.VISIBLE);
                new MyAsyncTask().execute(Loginvalues);

            }


        }

    }

    public void openRegistView(View view) {
        Intent newAct = new Intent();
        newAct.setClass( MainActivity.this, RegistActivity.class );
        startActivity(newAct);


        // 結束原先的 Activity Class


    }


    private class MyAsyncTask extends AsyncTask<String, Integer, String[]>{


        @Override
        protected String[] doInBackground(String... params) {
            // TODO Auto-generated method stub
            //postData(params[0].toString(),params[1].toString());
            sendPostDataToInternet(params[0].toString(),params[1].toString());



            return null;
        }




        protected void onPostExecute(Double result){
            pb.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "command sent", Toast.LENGTH_LONG).show();
        }

        protected void onProgressUpdate(Integer... progress){
            pb.setProgress(progress[0]);
        }



        private void sendPostDataToInternet(String mailTxt,String passTxt) {

            Log.d(mailTxt,passTxt);

            HttpPost httpRequest = new HttpPost(urlLogin);

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("account",mailTxt));

            params.add(new BasicNameValuePair("password",passTxt));

            try{
                //將需求兩個參數加入httpRequest之中
                String entityValue = URLEncodedUtils.format(params, HTTP.UTF_8);
                StringEntity entity = new StringEntity(entityValue, HTTP.UTF_8);
                entity.setContentType(URLEncodedUtils.CONTENT_TYPE);


                Log.d("資料",entity.getContent().toString());




                httpRequest.setEntity(entity);

                String ReqStr = httpRequest.getEntity().getContentEncoding().toString();


                Log.d("檢查",ReqStr);


                HttpClient getClient = new DefaultHttpClient();

                HttpResponse httpResponse = getClient.execute(httpRequest);

                //取得回應 解析httpResponse
                if(httpResponse.getStatusLine().getStatusCode() == 200){

                    String strResult = EntityUtils.toString(httpResponse.getEntity());

                    Log.d("回傳結論",strResult);

                }

            }catch (ClientProtocolException e){

                //Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }catch (IOException e){

                //Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                e.printStackTrace();

            }catch (Exception e){

                //Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }




        //處理
        public void postData(String mail,String pass) {
            HttpClient httpclient = new DefaultHttpClient();
            // specify the URL you want to post to
            HttpPost httppost = new HttpPost(urlLogin);
            try {
                // create a list to store HTTP variables and their values
                List nameValuePairs = new ArrayList();
                // add an HTTP variable and value pair


                Log.d("傳入","2");
                nameValuePairs.add(new BasicNameValuePair("account",mail));

                Log.d("傳入","3");
                nameValuePairs.add(new BasicNameValuePair("password",pass));

                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                // send the variable and value, in other words post, to the URL
                HttpResponse response = httpclient.execute(httppost);
                if(response.getStatusLine().getStatusCode() == 200){

                    String strResult = EntityUtils.toString(response.getEntity());


                    Log.d("登入",strResult);

                }

            } catch (ClientProtocolException e) {
                // process execption
            } catch (IOException e) {
                // process execption
            }
        }
    }





}



