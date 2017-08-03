package dev.eyesless.needmypuppy;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * A simple {@link Fragment} subclass.
 */
public class About_dog_main extends Buttons_Abstract_Fragment implements MVPInterface_aboutdog {


    private CheckBox havedog;
    private CheckBox havechild;

    private Spinner spinner_walking;
    private Spinner spinner_cynologist;
    private Spinner spinner_vet;

    private int walkvalue;
    private int cynologistvalue;
    private int vetvalue;

    public static final String SPINNER_WALK = "spinnerwalk";
    public static final String SPINNER_CYNO = "spinnercyno";
    public static final String SPINNER_VET = "spinnervet";

    private Bundle savedState;

    Presenter_aboutdog presenter;


    public About_dog_main() {
        // Required empty public constructor

        presenter = new Presenter_aboutdog(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            savedState = savedInstanceState;
        }

        return inflater.inflate(R.layout.about_dog_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        statussetter ();

        createspinner(spinner_walking, inact.getSpinner_walk_array());
        createspinner(spinner_cynologist, inact.getSpinner_cynologist_array());
        createspinner(spinner_vet, inact.getSpinner_vet_array());


        if (savedState != null) {restorespinnersvalue();}

        presenterinactsetter(inact);

        //реализуем онкликлистнер на подключенной кнопке
        completebutton.setOnClickListener(myOnClickListner);
    }

    private void restorespinnersvalue() {

        spinner_walking.setSelection(savedState.getInt(SPINNER_WALK));
        spinner_cynologist.setSelection(savedState.getInt(SPINNER_CYNO));
        spinner_vet.setSelection(savedState.getInt(SPINNER_VET));

        walkvalue = savedState.getInt(SPINNER_WALK);
        cynologistvalue = savedState.getInt(SPINNER_CYNO);
        vetvalue = savedState.getInt(SPINNER_VET);
    }

    private void presenterinactsetter(InitiationActivity inact) {

        presenter.inactsetter(inact);

    }

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

                    case (R.id.spinner_walking):
                        walkvalue = position;
                        break;

                    case (R.id.spinner_cynologist):
                        cynologistvalue = position;
                        break;

                    case (R.id.spinner_vet):

                        vetvalue = position;
                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //создаем онклик листнер для кнопок и передаем в методе онклик значение кнопки в метод buttonclicked интерфейса

    View.OnClickListener myOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            valuereader();

            myButtonListner.buttonClicked(v);

        }
    };

    private void statussetter() {

        havedog = (CheckBox) parentview.findViewById(R.id.checkBox_havedog);
        havechild = (CheckBox) parentview.findViewById(R.id.checkBox_havechild);
        spinner_walking = (Spinner)parentview.findViewById(R.id.spinner_walking);
        spinner_cynologist = (Spinner)parentview.findViewById(R.id.spinner_cynologist);
        spinner_vet = (Spinner)parentview.findViewById(R.id.spinner_vet);


        if (inact.isButtonaboutdogispressed()) {

            toastmaker();

            havedog.setEnabled(false);
            havechild.setEnabled(false);
            spinner_walking.setEnabled(false);
            spinner_cynologist.setEnabled(false);
            spinner_vet.setEnabled(false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(SPINNER_WALK, spinner_walking.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_CYNO, spinner_cynologist.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_VET, spinner_vet.getSelectedItemPosition());


        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean ishavechildboxchecked() {

        if (havechild.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean ishavedogboxchecked() {

        if (havedog.isChecked()) return true;
        else return false;
    }

    @Override
    public int iswalk() {
        return walkvalue;
    }

    @Override
    public int iscynologist() {
        return cynologistvalue;
    }

    @Override
    public int isvet() {
        return vetvalue;
    }

    public void valuereader () {

        presenter.valuereader();
    }
}
