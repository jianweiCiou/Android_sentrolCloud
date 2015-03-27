package tw.com.nietzsche.android_sentrolcloud;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

/**
 * Created by macmini on 15/3/26.
 */
public class Custom_DashBoard_ListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String>itemname;
    private final List<String>sectionType;
    private final List<String>sectionImageName;
    private final List<String>name;



    private final String[] imgName;


    public Custom_DashBoard_ListAdapter(Activity context,List<String> sectionType,List<String> sectionImageName,List<String> name,List<String> itemname,String[] imgName) {
        super(context, R.layout.list_cell_dashboard, itemname);


        this.context = context;
        this.itemname = itemname;
        this.sectionType = sectionType;
        this.sectionImageName = sectionImageName;
        this.imgName = imgName;
        this.name = name;


    }


    public View getView(int position,View view,ViewGroup parent){

        View rowView;
        if(!sectionType.get(position).equals("cell")){
            //標題
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_section_dashboard,null,true);
            TextView textview = (TextView) rowView.findViewById(R.id.section_title);
            textview.setText(itemname.get(position));


            //標圖
            ImageView imageV = (ImageView) rowView.findViewById(R.id.classImage);

            //imageV.setImageResource(R.);



//            File imgFile = new  File("res/drawable/"+ sectionImageName.get(position) +".png");
//
//            Log.d("圖片路徑","src/main/res/mipmap-xhdpi/"+ sectionImageName.get(position) +".png");
//
//
//            if(imgFile.exists()){
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//                imageV.setImageBitmap(myBitmap);
//
//            }






        }else{
            //狀態
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.list_cell_dashboard,null,true);
            TextView textview = (TextView) rowView.findViewById(R.id.className);
            TextView textview2 = (TextView) rowView.findViewById(R.id.className2);
            ImageView imageview = (ImageView) rowView.findViewById(R.id.classImage);
            textview.setText(itemname.get(position));
            textview2.setText(name.get(position));




        }
        return rowView;
    }

}
