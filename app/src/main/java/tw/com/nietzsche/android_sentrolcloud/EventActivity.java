package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by macmini on 15/3/24.
 */
public class EventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_event);
    }


    public void openDashboard(View view) {

        Intent newAct = new Intent();
        newAct.setClass( EventActivity.this, DashboardActivity.class );
        startActivity(newAct);
        EventActivity.this.finish();

    }

    public void openSetting(View view) {

        Intent newAct = new Intent();
        newAct.setClass( EventActivity.this, SettingActivity.class );
        startActivity(newAct);
        EventActivity.this.finish();

    }

    public void openEvent(View view) {

    }

    public void openAccount(View view) {
        Intent newAct = new Intent();
        newAct.setClass( EventActivity.this, AccountActivity.class );
        startActivity(newAct);
        EventActivity.this.finish();


    }
}
