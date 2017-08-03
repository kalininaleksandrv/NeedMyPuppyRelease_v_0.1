package dev.eyesless.needmypuppy;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Eyesless on 23.06.2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    protected ArrayList<Breed_mod> myListOfBreed;

    protected FragmentManager fragmentManager;
    protected Map <Integer, String> mapOfTags;


    public ViewPagerAdapter(FragmentManager fm, ArrayList<Breed_mod> arrayList) {

        super(fm);
        this.myListOfBreed = arrayList;
        this.fragmentManager = fm;
        mapOfTags = new HashMap<Integer, String>();

    }

        @Override
    public Fragment getItem(int position) {

        Fragment_description myDescrFragm = new Fragment_description();
        myDescrFragm.setBreedId(position);

        return myDescrFragm;
    }

    @Override
    public int getCount() {
        return myListOfBreed.size();
    }


    //save current item to hashmap (for getCurrFragment)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj  = super.instantiateItem(container, position);

        if (obj instanceof Fragment) {

            Fragment myFragment = (Fragment) obj;

            String mTag = myFragment.getTag();
            mapOfTags.put(position, mTag);

        }

        return obj;
    }

    //return current fragment to MainActivity.share
    public Fragment getCurrFragment (int lastPosition){

        String myTag = mapOfTags.get(lastPosition);

        if (myTag == null) {return null;} else {return fragmentManager.findFragmentByTag(myTag);}

    }
}
