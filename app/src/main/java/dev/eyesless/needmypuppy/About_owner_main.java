package dev.eyesless.needmypuppy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * A simple {@link Fragment} subclass.
 */
public class About_owner_main extends Buttons_Abstract_Fragment implements MVPInterface_aboutowner {

    private int expvalue;
    private int timevalue;
    private int agevalue;
    private int activvalue;
    private int familyvalue;

    private Spinner spiner_exp;
    private Spinner spiner_time;
    private Spinner spiner_age;
    private Spinner spiner_activ;
    private Spinner spiner_family;

    public static final String SPINNER_EXP = "spinnerexp";
    public static final String SPINNER_TIME = "spinnertime";
    public static final String SPINNER_AGE = "spinnerage";
    public static final String SPINNER_ACIV = "spinneractive";
    public static final String SPINNER_FAMILY = "spinnefamily";

    private Bundle savedState;

    Presenter_aboutowner presenter;

     public About_owner_main() {
        // Required empty public constructor

         presenter = new Presenter_aboutowner(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            savedState = savedInstanceState;
        }



        return inflater.inflate(R.layout.about_owner_main, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();

        spinnerstatussetter ();

        createspinner(spiner_exp, inact.getSpinner_exp_array());
        createspinner(spiner_time, inact.getSpinner_time_array());
        createspinner(spiner_age, inact.getSpinner_age_array());
        createspinner(spiner_activ, inact.getSpinner_activ_array());
        createspinner(spiner_family, inact.getSpinner_family_array());

        if (savedState != null) { restorespinnersvalue(); }

        presenterinactsetter(inact);

        completebutton.setOnClickListener(myOnClickListner);
    }

    private void restorespinnersvalue() {

        spiner_exp.setSelection(savedState.getInt(SPINNER_EXP));
        spiner_time.setSelection(savedState.getInt(SPINNER_TIME));
        spiner_age.setSelection(savedState.getInt(SPINNER_AGE));
        spiner_activ.setSelection(savedState.getInt(SPINNER_ACIV));
        spiner_family.setSelection(savedState.getInt(SPINNER_FAMILY));

        expvalue = savedState.getInt(SPINNER_EXP);
        timevalue = savedState.getInt(SPINNER_TIME);
        agevalue = savedState.getInt(SPINNER_AGE);
        activvalue = savedState.getInt(SPINNER_ACIV);
        familyvalue = savedState.getInt(SPINNER_FAMILY);

    }

    private void presenterinactsetter(InitiationActivity inact) {

        presenter.inactsetter(inact);

    }

    //создаем онклик листнер для кнопок и передаем в методе онклик значение кнопки в метод buttonclicked интерфейса

    View.OnClickListener myOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            valuereader();

            myButtonListner.buttonClicked(v);

        }
    };



    //spinners creater
    private void createspinner(Spinner sp, String [] strar) {

        final int mSpinner = sp.getId();

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>
                (getContext(), R.layout.list_item, strar);

        sp.setAdapter(myAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (mSpinner) {

                    case (R.id.spinner_exp):
                        expvalue = position;
                        break;

                    case (R.id.spinner_time):
                        timevalue = position;
                        break;

                    case (R.id.spinner_age):

                        agevalue = position;
                        break;

                    case (R.id.spinner_activ):

                        activvalue = position;
                        break;

                    case (R.id.spinner_family):

                        familyvalue = position;
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //init spinners and set it disabled if re-entry

    private void spinnerstatussetter() {

        spiner_exp = (Spinner)parentview.findViewById(R.id.spinner_exp);
        spiner_time = (Spinner)parentview.findViewById(R.id.spinner_time);
        spiner_age = (Spinner)parentview.findViewById(R.id.spinner_age);
        spiner_activ = (Spinner)parentview.findViewById(R.id.spinner_activ);
        spiner_family = (Spinner)parentview.findViewById(R.id.spinner_family);

        if (inact.isButtonaboutownerispressed()) {

            toastmaker();

            spiner_exp.setEnabled(false);
            spiner_time.setEnabled(false);
            spiner_age.setEnabled(false);
            spiner_activ.setEnabled(false);
            spiner_family.setEnabled(false);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(SPINNER_EXP, spiner_exp.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_TIME, spiner_time.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_AGE, spiner_age.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_ACIV, spiner_activ.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_FAMILY, spiner_family.getSelectedItemPosition());

        super.onSaveInstanceState(savedInstanceState);

    }




    @Override
    public int isexp() {
        return expvalue;
    }

    @Override
    public int istime() {
        return timevalue;
    }

    @Override
    public int isage() {
        return agevalue;
    }

    @Override
    public int isactive() {
        return activvalue;
    }

    @Override
    public int isfamily() {
        return familyvalue;
    }


    private void valuereader() {
        presenter.valuereader();
    }

}
