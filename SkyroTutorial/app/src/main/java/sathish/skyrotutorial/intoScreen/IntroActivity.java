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
    ImageView image1, image2;
    int currentPage;
    Context context;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        context = this;

        image1 = (ImageView) findViewById(R.id.imagesat1);
        image2 = (ImageView) findViewById(R.id.imagesat2);

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
//                Log.i("onPage","onPageScrolled **** "+positionOffset *95);
                Log.i("onPage","onPageScrolled **** "+(width - positionOffsetPixels) + "   " + currentPage);
//                Log.i("onPage", "onPageScrolled **** " + currentPage);


                // to fade away the element
                image1.setAlpha(1.0f - positionOffset);
                if (currentPage == 1 && positionOffset == 0.0f) image1.setAlpha(0.0f);


                // to move an object from one fragment to another
                if (currentPage == 1 && positionOffset == 0.0f) {
                    image1.setTranslationX(195.0f);
                    image1.setTranslationY(195.0f);
                } else {
                    image1.setTranslationX(positionOffset * 195f);
                    image1.setTranslationY(positionOffset * 195f);
                }

                // to keep object at other fragment
                if(currentPage == 0 && positionOffsetPixels == 0)
                {
                    image2.setTranslationX(width);
                }
                else if(currentPage == 1 && positionOffsetPixels == 0)
                {
                    image2.setTranslationX(0);
                }
//                else if(currentPage == 1 && positionOffsetPixels >= 1)
//                {

//                }
                else
                {
                    image2.setTranslationX(width - positionOffsetPixels);
                }

//                image2.

//                Log.i("onPage", "" + width + "   " + height);


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
