package sathish.skyrotutorial.intoScreen;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sathish on 8/12/15.
 */
public class IntroAdapter extends FragmentPagerAdapter {

    public IntroAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IntroFragment.newInstance(Color.parseColor("#F44336"), position); // red
            case 1:
                return IntroFragment.newInstance(Color.parseColor("#C2185B"), position); // pink
            case 2:
                return IntroFragment.newInstance(Color.parseColor("#2196F3"), position); // blue
            default:
                return IntroFragment.newInstance(Color.parseColor("#FFC107"), position); // amber
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
