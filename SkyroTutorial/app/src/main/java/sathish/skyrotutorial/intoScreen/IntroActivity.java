package sathish.skyrotutorial.intoScreen;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import sathish.skyrotutorial.R;


public class IntroActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    ImageView skyro, record, bigCard;
    int currentPage;
    Context context;
    int width, height;
    public static final float SKYRO_TRANSLATING_Y = 345f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        context = this;

        skyro = (ImageView) findViewById(R.id.skyro);
        record = (ImageView) findViewById(R.id.record);
        bigCard = (ImageView) findViewById(R.id.big_card);

        width = getWidth(context);
        height = getHeight(context);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Set an Adapter on the ViewPager
        mViewPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                //   exactly at fragment 1
                if(position == 0 && positionOffset == 0)
                {
                    skyro.setTranslationX(0);
                    record.setTranslationX(width);
                    bigCard.setTranslationX(width * 2);
                }
                // exactly at fragment 2
                else if(position == 1 && positionOffset == 0)
                {
                    skyro.setTranslationX(0);
                    skyro.setTranslationY(SKYRO_TRANSLATING_Y);
                    record.setTranslationX(0);
                    bigCard.setTranslationX(width);
                }
                // exactly at fragment 3
                else if(position == 2 && positionOffset == 0)
                {
                    skyro.setTranslationX(width * -2);
                    bigCard.setTranslationX(0);
                }
                // scroll between fragment 1 & 2
                else if(position == 0)
                {
                    skyro.setTranslationX(positionOffset);
                    skyro.setTranslationY(positionOffset * SKYRO_TRANSLATING_Y);
                    skyro.setAlpha(1.0f - positionOffset);
                    record.setTranslationX( (width) - (positionOffsetPixels) );
                    bigCard.setTranslationX( (width * 3) - (positionOffsetPixels + width) );
                }
                // scroll between fragment 2 & 3
                else if(position == 1)
                {
                    record.setAlpha(1.0f - positionOffset);
                    bigCard.setTranslationX( (width) - (positionOffsetPixels) );
                }
                // scroll between fragment 3 & 4
                else if(position == 2)
                {
                    bigCard.setAlpha(1.0f - positionOffset);
                }

            }


            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    public static int getWidth(Context mContext){
        int width=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>12){
            Point size = new Point();
            display.getSize(size);
            width = size.x;
        }
        else{
            width = display.getWidth();  // Deprecated
        }
        return width;
    }

    public static int getHeight(Context mContext){
        int height=0;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        if(Build.VERSION.SDK_INT>12){
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }
        else{
            height = display.getHeight();  // Deprecated
        }
        return height;
    }

}
