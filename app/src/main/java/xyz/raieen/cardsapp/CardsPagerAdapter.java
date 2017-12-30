package xyz.raieen.cardsapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xyz.raieen.cardsapp.fragment.CardsFragment;
import xyz.raieen.cardsapp.fragment.SearchFragment;

/**
 * Created by Admin on 2017-12-28.
 */

public class CardsPagerAdapter extends FragmentPagerAdapter {

    public CardsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new CardsFragment();
            case 1:
                return new SearchFragment();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "CardsFragment";
            case 1:
                return "SearchFragment";
            default:
                    return super.getPageTitle(position);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
