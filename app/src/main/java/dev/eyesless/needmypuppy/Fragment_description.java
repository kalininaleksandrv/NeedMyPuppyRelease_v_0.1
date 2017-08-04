package dev.eyesless.needmypuppy;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_description extends Fragment {


    protected int myBreedId;
    private View parentview;
    protected InitiationActivity inact;
    protected ArrayList<Breed_mod> myListOfBreed;
    protected static String MY_LIST_OF_BREED = "myListOfBreed";

    protected String titletext;
    protected String pictureweblinc;
    protected String descriptiontext;
    protected String extlinktext;
    protected static String MY_TITLE = "myTitle";
    protected static String MY_DESCRIPTION = "myDescription";
    protected static String MY_PICTURE = "myPicture";
    protected static String MY_LINC = "myLinc";

    public Fragment_description() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState !=null) {
            this.myListOfBreed = (ArrayList<Breed_mod>) savedInstanceState.getSerializable(MY_LIST_OF_BREED);
            this.titletext = savedInstanceState.getString(MY_TITLE);
            this.descriptiontext = savedInstanceState.getString(MY_DESCRIPTION);
            this.pictureweblinc = savedInstanceState.getString(MY_PICTURE);
            this.extlinktext = savedInstanceState.getString(MY_LINC);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View mView = inflater.inflate(R.layout.fragment_description, container, false);


        inact = ((InitiationActivity) getActivity().getApplicationContext());
        // Inflate the layout for this fragment

        return mView;

    }

    @Override
    public void onStart() {
        super.onStart();
        parentview = getView();
        myListOfBreed = inact.getMyListOfBreed_m();

        guidlinemoover();

        if (parentview != null){

            // из листа пород по ID выводим название описание и картинку конкретной породы
            TextView myBreedTitle = (TextView) parentview.findViewById(R.id.breed_title);
            if (titletext == null){titletext = myListOfBreed.get(myBreedId).getB_title();}
            myBreedTitle.setText(titletext);

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            if (pictureweblinc == null) {pictureweblinc =  myListOfBreed.get(myBreedId).getB_weblinc();}

            ImageView myBreedImage = (ImageView) parentview.findViewById(R.id.breed_image);

            Picasso.with(getActivity().getApplicationContext())
                    .load(pictureweblinc)
                    .placeholder(R.drawable.try_to_download)
                    .error(R.drawable.sad_dog)
                    .resize(width, width*(int)0.75)
                    .into(myBreedImage);

            TextView myBreedDescript = (TextView) parentview.findViewById(R.id.breed_descript);
            if (descriptiontext == null) {descriptiontext = myListOfBreed.get(myBreedId).getB_description_full();}
            myBreedDescript.setText(descriptiontext);

            TextView myExternal = (TextView) parentview.findViewById(R.id.web_linc_wiki);
            if (extlinktext == null) {extlinktext = myListOfBreed.get(myBreedId).getB_weblinc_wiki();}

            myExternal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    openexternalurl(extlinktext);
                }
            });

        }

    }

    public void onSaveInstanceState (Bundle savedInstanceState) {

        savedInstanceState.putSerializable(MY_LIST_OF_BREED, myListOfBreed);
        savedInstanceState.putString(MY_TITLE, titletext);
        savedInstanceState.putString(MY_DESCRIPTION, descriptiontext);
        savedInstanceState.putString(MY_PICTURE, pictureweblinc);
        savedInstanceState.putString(MY_LINC, extlinktext);


    }

    public void setBreedId (int id) {
        this.myBreedId = id;
    }

    //moove guidline to maximize size of 2nd container in activity_main.xml
    private void guidlinemoover() {

        int orient = getActivity().getResources().getConfiguration().orientation;

        if (orient == Configuration.ORIENTATION_PORTRAIT){

            ((MainActivity) getActivity()).gudlinesetter((float) 0.08);

        } else  ((MainActivity) getActivity()).gudlinesetter((float) 0.001);
    }

    private final void openexternalurl (String s) {

        final Uri address = Uri.parse(s);

        try {
            Intent myintent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(myintent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

}


