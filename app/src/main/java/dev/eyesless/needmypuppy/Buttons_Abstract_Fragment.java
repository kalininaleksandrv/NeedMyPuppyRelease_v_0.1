package dev.eyesless.needmypuppy;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Eyesless on 26.06.2017.
 */

public abstract class Buttons_Abstract_Fragment extends Fragment {

    public InitiationActivity inact;
    public View parentview;
    public ImageButton completebutton;

    onButtonListner myButtonListner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inact = ((InitiationActivity) getActivity().getApplicationContext());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.myButtonListner = (onButtonListner) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        parentview = getView();
        completebutton = (ImageButton) parentview.findViewById(R.id.button_complete);

    }

    // if button was pressed and trying next time, set toast about
    public void toastmaker() {
        String helpstring = getString(R.string.disabled_button_short);
        Toast myToast = Toast.makeText(getContext(), helpstring, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.BOTTOM, 0, 30);
        myToast.show();
    }

    // toast random text
    public void toastmakerany(String s) {
        Toast myToast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.BOTTOM, 0, 30);
        myToast.show();
    }
}