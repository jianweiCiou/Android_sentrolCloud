package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by macmini on 15/3/24.
 */
public class AccountActivity extends Activity {


    String[] fiilliste;
    ArrayAdapter<String> adapter;


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_account);



        listView = (ListView)findViewById(R.id.listView1);

        String[] item = getResources().getStringArray(R.array.account_list);

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,item);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.adapter_account_list,R.id.account_list_title,item);

        listView.setAdapter(adapter);





        fiilliste = getResources().getStringArray(R.array.account_list);




    }



    public void openDashboard(View view) {

        Intent newAct = new Intent();
        newAct.setClass( AccountActivity.this, DashboardActivity.class );
        startActivity(newAct);
        AccountActivity.this.finish();

    }

    public void openSetting(View view) {

        Intent newAct = new Intent();
        newAct.setClass( AccountActivity.this, SettingActivity.class );
        startActivity(newAct);
        AccountActivity.this.finish();

    }

    public void openEvent(View view) {

        Intent newAct = new Intent();
        newAct.setClass( AccountActivity.this, EventActivity.class );
        startActivity(newAct);
        AccountActivity.this.finish();
    }

    public void openAccount(View view) {


    }


}
