package app.techsol.myapplication;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

      Integer[] myImageArray={
              R.drawable.thumb1,
              R.drawable.thumb2,
              R.drawable.thumb5,
              R.drawable.thumb6,
              R.drawable.thumb7};
    GridView mygridView;
    ImageView myCurrentWallpaper;
    Drawable myDrawable;
    WallpaperManager myWallManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mygridView=findViewById(R.id.gridview);
        myCurrentWallpaper=findViewById(R.id.myImageView);
        mygridView.setAdapter(new ImageAdapter(getApplicationContext()));
        UpdateWallpaper();
    }
    private void UpdateWallpaper(){
        myWallManager=WallpaperManager.getInstance(getApplicationContext());
        myDrawable=myWallManager.getDrawable();
        myCurrentWallpaper.setImageDrawable(myDrawable);

    }
    public  class ImageAdapter extends BaseAdapter{
       Context myContext;

        public ImageAdapter(Context applicationContext) {
        myContext=applicationContext;
        }

        @Override
        public int getCount() {
            return myImageArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
          ImageView GridImageView;
           if(view==null){
               GridImageView=new ImageView(myContext);
               GridImageView.setLayoutParams(new GridView.LayoutParams(512,512));
               GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
           }
           else {
               GridImageView=(ImageView) view;
           }
           GridImageView.setImageResource(myImageArray[position]);
           GridImageView.setOnClickListener(new View.OnClickListener(){

               @Override
               public void onClick(View v) {
                   try {
                       myWallManager.setResource(myImageArray[position]);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   UpdateWallpaper();
               }
           });
            return GridImageView;
        }
    }
}
