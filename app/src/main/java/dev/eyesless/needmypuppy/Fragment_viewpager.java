package dev.eyesless.needmypuppy;

import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Eyesless on 23.06.2017.
 */

public class Fragment_viewpager extends Fragment {

    protected int myBreedId;
    protected InitiationActivity inact;
    protected ArrayList<Breed_mod> myListOfBreed;
    protected View parentview;
    protected static String MY_BREED_ID = "myBreedId";
    protected static String MY_LIST_OF_BREED = "myListOfBreed";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inact = ((InitiationActivity) getActivity().getApplicationContext());

        myListOfBreed = inact.getMyListOfBreed_m();

        if (savedInstanceState !=null) {
            this.myBreedId = savedInstanceState.getInt(MY_BREED_ID);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return   inflater.inflate(R.layout.fragment_viewpager, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        parentview = getView();

       ViewPagerAdapter myViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), myListOfBreed);

        ViewPager myViewPager = (ViewPager) parentview.findViewById(R.id.view_pager);

        myViewPager.setAdapter(myViewPagerAdapter);

        myViewPager.setCurrentItem(myBreedId);

    }

    public void onSaveInstanceState (Bundle savedInstanceState) {
        // TODO: 20.07.2017 does nor work when screen rotate - return to start fragment

        savedInstanceState.putInt(MY_BREED_ID, myBreedId);

    }

    public void setBreedId (int id) {
        this.myBreedId = id;
    }

}
