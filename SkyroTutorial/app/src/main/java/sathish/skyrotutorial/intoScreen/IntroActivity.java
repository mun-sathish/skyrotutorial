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
    ImageView skyro, record, smallCard;
    int currentPage;
    Context context;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        context = this;

        skyro = (ImageView) findViewById(R.id.skyro);
        record = (ImageView) findViewById(R.id.record);
        smallCard = (ImageView) findViewById(R.id.small_card);

        width = getWidth(context);
        height = getHeight(context);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        // Set an Adapter on the ViewPager
        mViewPager.setAdapter(new IntroAdapter(getSupportFragmentManager()));

        // Set a PageTransformer
        mViewPager.setPageTransformer(false, new IntroPageTransformer());




        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.i("onPage","onPageScrolled **** "+position);
//                Log.i("onPage","onPageScrolled **** "+positionOffset);
                Log.i("onPage","onPageScrolled **** "+(positionOffset) + "   " + position);
//                Log.i("onPage", "onPageScrolled **** " + currentPage);


//                // to fade away the element
//                skyro.setAlpha(1.0f - positionOffset);
//                if (currentPage == 1 && positionOffset == 0.0f) skyro.setAlpha(0.0f);
//
//
//                // to move an object from one fragment to another
//                if (currentPage == 1 && positionOffset == 0.0f) {
//                    skyro.setTranslationX(195.0f);
//                    skyro.setTranslationY(195.0f);
//                } else {
//                    skyro.setTranslationX(positionOffset * 195f);
//                    skyro.setTranslationY(positionOffset * 195f);
//                }
//
//
//                // to keep object at other fragment
//                if(currentPage == 0 && positionOffsetPixels == 0)
//                {
//                    record.setTranslationX(width);
//                }
//                else if(currentPage == 1 && positionOffsetPixels == 0)
//                {
//                    record.setTranslationX(0);
//                }
//                else
//                {
//                    record.setTranslationX(width - positionOffsetPixels);
//                }


                /***************************/

//                int skyroPosition = width * 0;
//                int recordPosition = width * 1;
//                int pausePosition = width * 2;

                if(currentPage == 0 && positionOffsetPixels == 0)
                {
                    skyro.setTranslationX(width * 0);
                    record.setTranslationX(width * 1);
                    smallCard.setTranslationX(width * 2);
                }
                else if(currentPage == 1 && positionOffsetPixels == 0)
                {
                    skyro.setTranslationX(width * -1);
                    record.setTranslationX(width * 0);
                    smallCard.setTranslationX(width * 1);
                }
                else if(currentPage == 2 && positionOffsetPixels == 0)
                {
                    skyro.setTranslationX(width * -2);
                    record.setTranslationX(width * -1);
                    smallCard.setTranslationX(width * 0);
                }
//                else if((currentPage == 0 && positionOffsetPixels <= 360) ||
//                        (currentPage == 1 && positionOffsetPixels > 360) )
                else if(position == 0)
                {
                    record.setTranslationX( (width) - (positionOffsetPixels) );
                    smallCard.setTranslationX( (width * 3) - (positionOffsetPixels + 720) );
                }
//                else if((currentPage == 1 && positionOffsetPixels <= 360) ||
//                        (currentPage == 2 && positionOffsetPixels > 360) )
                else if(position == 1)
                {
//                    record.setTranslationX( (width) + (positionOffsetPixels - 720) );
                    record.setTranslationX(positionOffsetPixels / 360f);
                    smallCard.setTranslationX( (width) - (positionOffsetPixels) );
//                    Log.i("onPage", "" + ( (width) - (positionOffsetPixels) ));
                }




//                    Log.i("onPage", "" + ( (width * 1) - (positionOffsetPixels + 0) ));


            }

            @Override
            public void onPageSelected(int position) {
//                Log.i("onPage","onPageSelected **** "+position);
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.i("onPage","onPageScrollStateChanged **** "+state);

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
