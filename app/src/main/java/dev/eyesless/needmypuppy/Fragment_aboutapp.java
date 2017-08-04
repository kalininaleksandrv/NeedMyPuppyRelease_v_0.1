package dev.eyesless.needmypuppy;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_aboutapp extends Buttons_Abstract_Fragment {


    public Fragment_aboutapp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_aboutapp, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        guidlinemoover();

        TextView linkedtext = (TextView) parentview.findViewById(R.id.linctogit);
        String mText = getString(R.string.linktogit);
        linkedtext.setText(spanmytext(mText));

        linkedtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openexternalurl();
            }
        });

    }

    @SuppressWarnings("deprecation")
    private Spanned spanmytext(String mText) {

        if (android.os.Build.VERSION.SDK_INT >=24) {
            Spanned textSpan = android.text.Html.fromHtml(mText, Html.FROM_HTML_MODE_LEGACY);
            return textSpan;
        } else {
            Spanned textSpan = android.text.Html.fromHtml(mText);
            return textSpan;
        }
    }

    private final void openexternalurl () {

        final Uri address = Uri.parse(getString(R.string.project_adress));

        try {
            Intent myintent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(myintent);
        } catch (ActivityNotFoundException e) {
            toastmakerany(getString(R.string.nosuchactivity));
        }

    }

    //moove guidline to maximize size of 2nd container in activity_main.xml
    private void guidlinemoover() {

        int orient = getActivity().getResources().getConfiguration().orientation;

        if (orient == Configuration.ORIENTATION_PORTRAIT){

            ((MainActivity) getActivity()).gudlinesetter((float) 0.08);

        } else  ((MainActivity) getActivity()).gudlinesetter((float) 0.001);
    }
}

