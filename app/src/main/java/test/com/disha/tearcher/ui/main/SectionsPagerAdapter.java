package test.com.disha.tearcher.ui.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//import test.com.disha.tearcher.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

//    @StringRes
//    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};

    private final Context mContext;

    private final List <Fragment> mFragmentList= new ArrayList <>();

    private final List <String> mFragmentTitleList= new ArrayList <>();

    public void addFragment(Fragment fragment, String title)
    {
        mFragmentList.add(fragment);

        mFragmentTitleList.add(title);


    }

    public SectionsPagerAdapter(Context context, FragmentManager fm) {

        super(fm);

        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {

        // getItem is called to instantiate the fragment for the given page.

        // Return a PlaceholderFragment (defined as a static inner class below).
        return mFragmentList.get(position);

//       return PlaceholderFragment.newInstance(position + 1);
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

//        return mContext.getResources().getString(TAB_TITLES[position]);
      return  mFragmentTitleList.get(position);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
//        return 3;
          return  mFragmentList.size();
    }
}