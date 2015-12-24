package sathish.skyrotutorial.intoScreen;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import sathish.skyrotutorial.R;


public class IntroActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    ImageView image;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.intro_layout);

        image = (ImageView) findViewById(R.id.imagesat);

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
//                Log.i("onPage","onPageScrolled **** "+positionOffsetPixels);
//                Log.i("onPage", "onPageScrolled **** " + currentPage);


                // to fade away the element
                image.setAlpha(1.0f - positionOffset);
                if (currentPage == 1 && positionOffset == 0.0f) image.setAlpha(0.0f);


                // to move an object from one fragment to another
                if (currentPage == 1 && positionOffset == 0.0f) {
                    image.setTranslationX(195.0f);
                    image.setTranslationY(195.0f);
                } else {
                    image.setTranslationX(positionOffset * 195f);
                    image.setTranslationY(positionOffset * 195f);
                }
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

}
