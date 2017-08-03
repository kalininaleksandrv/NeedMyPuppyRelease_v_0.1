package dev.eyesless.needmypuppy;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * A simple {@link Fragment} subclass.
 */
public class Forwhat_main extends Buttons_Abstract_Fragment implements MVPInterface_forwhat{


   private CheckBox babycheck;
   private CheckBox frendcheck;
   private CheckBox runcheck;
   private CheckBox huntcheck;
   private CheckBox obidencecheck;
   private CheckBox guardcheck;

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

        completebutton.setOnClickListener(myOnClickListner);
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

        babycheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt1);
        frendcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt2);
        runcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt3);
        huntcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt4);
        obidencecheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt5);
        guardcheck = (CheckBox) parentview.findViewById(R.id.checkBox_opt6);

        if (inact.isButtonforwhatispressed()) {

            toastmaker();

            babycheck.setEnabled(false);
            frendcheck.setEnabled(false);
            runcheck.setEnabled(false);
            huntcheck.setEnabled(false);
            obidencecheck.setEnabled(false);
            guardcheck.setEnabled(false);

        }
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

    public void valuereader() {

        presenter.valuereader();

    }
}

