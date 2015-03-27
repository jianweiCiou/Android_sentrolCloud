package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by macmini on 15/3/24.
 */
public class SettingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
    }



    public void openDashboard(View view) {

        Intent newAct = new Intent();
        newAct.setClass( SettingActivity.this, DashboardActivity.class );
        startActivity(newAct);
        SettingActivity.this.finish();

    }

    public void openSetting(View view) {

    }

    public void openEvent(View view) {

        Intent newAct = new Intent();
        newAct.setClass( SettingActivity.this, EventActivity.class );
        startActivity(newAct);
        SettingActivity.this.finish();
    }

    public void openAccount(View view) {

        Intent newAct = new Intent();
        newAct.setClass( SettingActivity.this, AccountActivity.class );
        startActivity(newAct);
        SettingActivity.this.finish();

    }





}
