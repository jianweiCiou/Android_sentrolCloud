package tw.com.nietzsche.sentrolcloud;

import android.app.Activity;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;


/**
 * Created by macmini on 15/3/17.
 */
public class AboutActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 取消元件的應用程式標題
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
    }

    // 結束按鈕
    public void clickOk(View view) {
        // 呼叫這個方法結束Activity元件
        finish();
    }
}
