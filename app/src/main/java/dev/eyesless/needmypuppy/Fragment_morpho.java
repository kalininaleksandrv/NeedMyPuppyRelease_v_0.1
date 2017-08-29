package dev.eyesless.needmypuppy;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import static java.lang.Math.min;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_morpho extends Buttons_Abstract_Fragment implements MVPInterface {


    protected CheckBox noalergy;
    protected CheckBox addrare;
    protected CheckBox exectly;
    protected Spinner spinnerhair;
    protected Spinner spinnerblackorwhite;
    protected Spinner spinnersize;
    private int blackorwhiteposition;
    private int hairposition;
    private int sizeposition;
    private TextView numberofbreeds;


    public static final String SPINNER_HAIR = "spinnerhair";
    public static final String SPINNER_BOW = "spinnerbow";
    public static final String SPINNER_SIZE = "spinnersize";

    private Bundle savedState;

    Presenter_morpho presenter;

    public Fragment_morpho() {
        // Required empty public constructor

        presenter = new Presenter_morpho(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            savedState = savedInstanceState;
        }

        return inflater.inflate(R.layout.fragment_morpho, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();


        spinnerinit ();

        statussetter ();

        createspinner (spinnerblackorwhite, inact.getSpinner_blackorwhite());
        createspinner (spinnerhair, inact.getSpinner_hair());
        createspinner(spinnersize, inact.getSpinner_size());

        if (savedState != null) { restorespinnersvalue(); }

        presenterinactsetter(inact);
        completebutton.setOnClickListener(myOnClickListner);

        presenter.countTheBreeds();

    }

    private void restorespinnersvalue() {


        spinnerhair.setSelection(savedState.getInt(SPINNER_HAIR));
        spinnerblackorwhite.setSelection(savedState.getInt(SPINNER_BOW));
        spinnersize.setSelection(savedState.getInt(SPINNER_SIZE));

        hairposition = savedState.getInt(SPINNER_HAIR);
        blackorwhiteposition = savedState.getInt(SPINNER_BOW);
        sizeposition = savedState.getInt(SPINNER_SIZE);
    }

    private void presenterinactsetter(InitiationActivity inact) {

        presenter.inactsetter(inact);

    }

    private void spinnerinit() {

        numberofbreeds = (TextView) parentview.findViewById(R.id.textView_numberOfBreeds_morpho);
        spinnerhair = (Spinner) parentview.findViewById(R.id.spinner_hair);
        spinnerblackorwhite = (Spinner) parentview.findViewById(R.id.spinner_blackorwhite);
        spinnersize = (Spinner) parentview.findViewById(R.id.spinner_size);

        noalergy = (CheckBox) parentview.findViewById(R.id.checkBox_noalergy);
        addrare = (CheckBox) parentview.findViewById(R.id.checkBox_addrare);
        exectly = (CheckBox) parentview.findViewById(R.id.checkBox_exectlysize);

        addcheckboxlistner (noalergy);
        addcheckboxlistner (addrare);
        addcheckboxlistner (exectly);

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

                    case (R.id.spinner_blackorwhite):
                        blackorwhiteposition = position;
                        presenter.countTheBreeds();

                        break;

                    case (R.id.spinner_hair):
                        hairposition = position;
                        presenter.countTheBreeds();

                        break;

                    case (R.id.spinner_size):

                        sizeposition = position;
                        presenter.countTheBreeds();

                        break;

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void statussetter() {

        if (inact.isButtonmorphoispressed()) {

            toastmaker();

            noalergy.setEnabled(false);
            addrare.setEnabled(false);
            exectly.setEnabled(false);
            spinnerhair.setEnabled(false);
            spinnerblackorwhite.setEnabled(false);
            spinnersize.setEnabled(false);

        }
    }

    View.OnClickListener myOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            checkboxreader();

            myButtonListner.buttonClicked(v);

        }
    };

    private void addcheckboxlistner(CheckBox checkbox) {

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    presenter.countTheBreeds();

                }
                if (!isChecked){
                    presenter.countTheBreeds();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(SPINNER_HAIR, spinnerhair.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_BOW, spinnerblackorwhite.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_SIZE, spinnersize.getSelectedItemPosition());

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean isboxchecked() {

        if (noalergy.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean israreboxchecked() {
        if (addrare.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean isexectlyboxchecked() {
        if (exectly.isChecked()) return true;
        else return false;
    }

    @Override
    public int issize() {
        return sizeposition;
    }

    @Override
    public void setnumberofbreeds(int breeds, int getchoosed) {
        numberofbreeds.setText("ИТОГО ВЫБРАНО: " + String.valueOf(getchoosed) + " ИЗ "+ String.valueOf(breeds));

    }

    @Override
    public int ishair() {
        return hairposition;
    }

    @Override
    public int isblackorwhite() {
        return blackorwhiteposition;
    }


    public void checkboxreader() {

        presenter.checkboxreader();

    }
}
