package dev.eyesless.needmypuppy;

import android.util.Log;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 27.06.2017.
 */

public class Presenter_aboutdog {

    private MVPInterface_aboutdog minterface;


    private InitiationActivity inact;

    public Presenter_aboutdog(MVPInterface_aboutdog minterface) {

        this.minterface = minterface;



    }

    public void inactsetter(InitiationActivity inact) {

        this.inact = inact;
    }

    public void valuereader (){

        inact.setButtonaboutdogispressed(true);

        if (minterface.ishavedogboxchecked()){inact.agressive.setValue(min(inact.agressive.getValue(),4));} //в случае другой собаки агрессивность не более 3

        if (minterface.ishavechildboxchecked()){inact.agressive.setValue(min(inact.agressive.getValue(),2));} //в случае наличия детей агрессивность не более 2

        inact.obidience.setValue(max(inact.obidience.getValue(), 3-minterface.iscynologist())); //чем лучше кинологические услуги тем хуже может быть послушание

        inact.guard.setValue(max(inact.guard.getValue(), minterface.iscynologist())); //чем хуже развиты кинологические услуги, тем менее выраженные охранные качества допускаются

        inact.agressive.setValue(min(inact.agressive.getValue(), minterface.iswalk()+2)); //чем хуже условия выгула, тем менее агрессивная собака допускается
        inact.agressive.setValue(min(inact.agressive.getValue(), minterface.iscynologist()+2)); // чем хуже развиты кинологические услуги, тем менее агрессивная собака допускается

        inact.active.setValue(min(inact.active.getValue(), minterface.iswalk()+1)); //чем хуже условия выгула, тем менее активная собака допускается

        inact.size.setValue(min(inact.size.getValue(), minterface.iswalk()+1)); //чем хуже условия выгула, тем менее крупная собака допускается

        inact.care.setValue(max(inact.care.getValue(), minterface.isvet()+2)); //чем хуже ветеринарная поддержка тем более неприхотливая собака допускается

    }

}
