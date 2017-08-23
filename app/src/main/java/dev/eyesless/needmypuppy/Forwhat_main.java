package dev.eyesless.needmypuppy;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;


public class Forwhat_main extends Buttons_Abstract_Fragment implements MVPInterface_forwhat{


   private CheckBox babycheck;
   private CheckBox frendcheck;
   private CheckBox runcheck;
   private CheckBox huntcheck;
   private CheckBox obidencecheck;
   private CheckBox guardcheck;
   private CheckBox zkscheck;
   private CheckBox agilitycheck;
    private ImageButton compleetbutton_forwhat;
    private TextView numberofbreeds;
    private TextView number_forchild;
    private TextView number_forfrend;
    private TextView number_forrun;
    private TextView number_forhunt;
    private TextView number_forobidience;
    private TextView number_forguard;
    private TextView number_forzks;
    private TextView number_foragility;

    Presenter_forwhat presenter;

    public Forwhat_main() {

        presenter = new Presenter_forwhat(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.forwhat_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        checkbuttonstatussetter ();

        presenterinactsetter(inact);

        compleetbutton_forwhat.setOnClickListener(myOnClickListner);

        presenter.countTheBreeds();
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


    //init checkboxes and set it disabled if re-entry

    private void checkbuttonstatussetter() {

        numberofbreeds = (TextView) parentview.findViewById(R.id.textView_numberOfBreeds);
        number_forchild = (TextView) parentview.findViewById(R.id.number_for_child);
        number_forfrend = (TextView) parentview.findViewById(R.id.number_for_company);
        number_forrun = (TextView) parentview.findViewById(R.id.number_for_running);
        number_forhunt = (TextView) parentview.findViewById(R.id.number_for_hunt);
        number_forobidience = (TextView) parentview.findViewById(R.id.number_for_obidience);
        number_forguard = (TextView) parentview.findViewById(R.id.number_for_guard);
        number_forzks = (TextView) parentview.findViewById(R.id.number_for_zks);
        number_foragility = (TextView) parentview.findViewById(R.id.number_for_agility);
        babycheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt1);
        frendcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt2);
        runcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt3);
        huntcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt4);
        obidencecheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt5);
        guardcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt6);
        zkscheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt7);
        agilitycheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt8);
        compleetbutton_forwhat = (ImageButton)parentview.findViewById(R.id.button_complete_forwhat);

        addcheckboxlistner (babycheck);
        addcheckboxlistner (frendcheck);
        addcheckboxlistner (runcheck);
        addcheckboxlistner (huntcheck);
        addcheckboxlistner (obidencecheck);
        addcheckboxlistner (guardcheck);
        addcheckboxlistner (zkscheck);
        addcheckboxlistner (agilitycheck);


        if (inact.isButtonforwhatispressed()) {

            toastmaker();

            babycheck.setEnabled(false);
            frendcheck.setEnabled(false);
            runcheck.setEnabled(false);
            huntcheck.setEnabled(false);
            obidencecheck.setEnabled(false);
            guardcheck.setEnabled(false);
            zkscheck.setEnabled(false);
            agilitycheck.setEnabled(false);
        }
    }

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
    public boolean isbabychecked() {
        if (babycheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean isfrendchecked() {
        if (frendcheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean isrunchecked() {
        if (runcheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean ishuntchecked() {
        if (huntcheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean isobidiencechecked() {
        if (obidencecheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean isguardchecked() {
        if (guardcheck.isChecked()) return true;
        else return false;
    }

    @Override
    public boolean iszkschecked() {
        if (zkscheck.isChecked()) return true;
        else return false;    }

    @Override
    public boolean isagilitychecked() {
        if (agilitycheck.isChecked()) return true;
        else return false;    }

    @Override
    public void setnumberofbreeds(int breeds, int getchoosed, Integer[] count) {
        numberofbreeds.setText("ИТОГО ВЫБРАНО: " + String.valueOf(getchoosed) + " ИЗ "+ String.valueOf(breeds));
        number_forchild.setText(String.valueOf(count[0]));
        number_forfrend.setText(String.valueOf(count[1]));
        number_forrun.setText(String.valueOf(count[2]));
        number_forhunt.setText(String.valueOf(count[3]));
        number_forobidience.setText(String.valueOf(count[4]));
        number_forguard.setText(String.valueOf(count[5]));
        number_forzks.setText(String.valueOf(count[6]));
        number_foragility.setText(String.valueOf(count[7]));
    }

    public void valuereader() {

        presenter.valuereader();

    }

}

