package dev.eyesless.needmypuppy;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;

public class About_dog_main extends Buttons_Abstract_Fragment implements MVPInterface_aboutdog {

    public ImageButton completebutton_ad;
    private CheckBox havedog;
    private CheckBox havepet;
    private Spinner spinner_walking;
    private Spinner spinner_cynologist;
    private Spinner spinner_vet;
    private Spinner spiner_exp;
    private Spinner spiner_time;
    private Spinner spiner_age;
    private Spinner spiner_activ;
    private Spinner spiner_family;
    private Spinner spiner_grummer;

    private int walkvalue;
    private int cynologistvalue;
    private int vetvalue;
    private int expvalue;
    private int timevalue;
    private int agevalue;
    private int activvalue;
    private int familyvalue;
    private int grummervalue;

    public static final String SPINNER_WALK = "spinnerwalk";
    public static final String SPINNER_CYNO = "spinnercyno";
    public static final String SPINNER_VET = "spinnervet";
    public static final String SPINNER_EXP = "spinnerexp";
    public static final String SPINNER_TIME = "spinnertime";
    public static final String SPINNER_AGE = "spinnerage";
    public static final String SPINNER_ACIV = "spinneractive";
    public static final String SPINNER_FAMILY = "spinnefamily";
    public static final String SPINNER_GRUMMER = "spinnergrummer";

    private Bundle savedState;

    Presenter_aboutdog presenter;

    public About_dog_main() {
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
        createspinner(spiner_exp, inact.getSpinner_exp_array());
        createspinner(spiner_time, inact.getSpinner_time_array());
        createspinner(spiner_age, inact.getSpinner_age_array());
        createspinner(spiner_activ, inact.getSpinner_activ_array());
        createspinner(spiner_family, inact.getSpinner_family_array());
        createspinner(spiner_grummer, inact.getSpinner_grummer_array());

        if (savedState != null) {restorespinnersvalue();}

        presenterinactsetter(inact);

        //реализуем онкликлистнер на подключенной кнопке
        completebutton_ad.setOnClickListener(myOnClickListner);
    }

    private void restorespinnersvalue() {

        spinner_walking.setSelection(savedState.getInt(SPINNER_WALK));
        spinner_cynologist.setSelection(savedState.getInt(SPINNER_CYNO));
        spinner_vet.setSelection(savedState.getInt(SPINNER_VET));
        spiner_exp.setSelection(savedState.getInt(SPINNER_EXP));
        spiner_time.setSelection(savedState.getInt(SPINNER_TIME));
        spiner_age.setSelection(savedState.getInt(SPINNER_AGE));
        spiner_activ.setSelection(savedState.getInt(SPINNER_ACIV));
        spiner_family.setSelection(savedState.getInt(SPINNER_FAMILY));
        spiner_grummer.setSelection(savedState.getInt(SPINNER_GRUMMER));

        walkvalue = savedState.getInt(SPINNER_WALK);
        cynologistvalue = savedState.getInt(SPINNER_CYNO);
        vetvalue = savedState.getInt(SPINNER_VET);
        expvalue = savedState.getInt(SPINNER_EXP);
        timevalue = savedState.getInt(SPINNER_TIME);
        agevalue = savedState.getInt(SPINNER_AGE);
        activvalue = savedState.getInt(SPINNER_ACIV);
        familyvalue = savedState.getInt(SPINNER_FAMILY);
        grummervalue = savedState.getInt(SPINNER_GRUMMER);

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
                    case (R.id.spinner_grummer):
                        grummervalue = position;
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
        completebutton_ad = (ImageButton) parentview.findViewById(R.id.button_complete_aboutdog);
        havedog = (CheckBox) parentview.findViewById(R.id.checkBox_havedog);
        havepet = (CheckBox) parentview.findViewById(R.id.checkBox_havepet);
        spinner_walking = (Spinner)parentview.findViewById(R.id.spinner_walking);
        spinner_cynologist = (Spinner)parentview.findViewById(R.id.spinner_cynologist);
        spinner_vet = (Spinner)parentview.findViewById(R.id.spinner_vet);
        spiner_exp = (Spinner)parentview.findViewById(R.id.spinner_exp);
        spiner_time = (Spinner)parentview.findViewById(R.id.spinner_time);
        spiner_age = (Spinner)parentview.findViewById(R.id.spinner_age);
        spiner_activ = (Spinner)parentview.findViewById(R.id.spinner_activ);
        spiner_family = (Spinner)parentview.findViewById(R.id.spinner_family);
        spiner_grummer = (Spinner)parentview.findViewById(R.id.spinner_grummer);

        if (inact.isButtonaboutdogispressed()) {

            toastmaker();

            havedog.setEnabled(false);
            havepet.setEnabled(false);
            spinner_walking.setEnabled(false);
            spinner_cynologist.setEnabled(false);
            spinner_vet.setEnabled(false);
            spiner_exp.setEnabled(false);
            spiner_time.setEnabled(false);
            spiner_age.setEnabled(false);
            spiner_activ.setEnabled(false);
            spiner_family.setEnabled(false);
            spiner_grummer.setEnabled(false);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt(SPINNER_WALK, spinner_walking.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_CYNO, spinner_cynologist.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_VET, spinner_vet.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_EXP, spiner_exp.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_TIME, spiner_time.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_AGE, spiner_age.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_ACIV, spiner_activ.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_FAMILY, spiner_family.getSelectedItemPosition());
        savedInstanceState.putInt(SPINNER_GRUMMER, spiner_grummer.getSelectedItemPosition());


        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    public boolean ishavedogboxchecked() {
        if (havedog.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean ishavepetboxchecked() {
        if (havepet.isChecked()) return true;
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
    public int isfamily() { return familyvalue;}

    @Override
    public int isgrimmer() {return grummervalue;}


    public void valuereader () {
        presenter.valuereader();
    }
}
