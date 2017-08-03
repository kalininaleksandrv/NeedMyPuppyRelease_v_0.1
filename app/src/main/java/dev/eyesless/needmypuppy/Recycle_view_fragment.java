package dev.eyesless.needmypuppy;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class Recycle_view_fragment extends Fragment {

    protected RecyclerView myRecycleView;
    protected LinearLayoutManager myLayoutManager;
    protected ItemClickListner myListner;


    public Recycle_view_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parentview = inflater.inflate(R.layout.fragment_recycle_view, container, false);

        InitiationActivity inact = ((InitiationActivity) getActivity().getApplicationContext());


        myRecycleView = (RecyclerView) parentview.findViewById(R.id.recycle_view);

        myLayoutManager = new LinearLayoutManager(getActivity());

        myRecycleView.setLayoutManager(myLayoutManager);

        Context mcontext = getContext();


        RVAdapter adapter = new RVAdapter(inact.getMyListOfBreed_m(), mcontext);

        adapter.setClickListner((ItemClickListner) getActivity());

        myRecycleView.setAdapter(adapter);

        return parentview;
    }

    @Override
    public void onStart() {
        super.onStart();

            guidlinemoover();

    }
//moove guidline to maximize size of 2nd container in activity_main.xml
    private void guidlinemoover() {

        int orient = getActivity().getResources().getConfiguration().orientation;

        if (orient == Configuration.ORIENTATION_PORTRAIT){

            ((MainActivity) getActivity()).gudlinesetter((float) 0.08);

        } else  ((MainActivity) getActivity()).gudlinesetter((float) 0.001);
    }
}
