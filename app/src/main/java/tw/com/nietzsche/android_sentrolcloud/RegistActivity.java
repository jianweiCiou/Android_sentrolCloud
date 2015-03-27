package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

/**
 * Created by macmini on 15/3/23.
 */
public class RegistActivity extends Activity {

    private Spinner spinner1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist);
    }



}
