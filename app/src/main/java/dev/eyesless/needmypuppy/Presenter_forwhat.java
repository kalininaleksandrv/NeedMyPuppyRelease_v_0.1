package dev.eyesless.needmypuppy;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 28.06.2017.
 */

class Presenter_forwhat {


    private MVPInterface_forwhat minterface;

    private InitiationActivity inact;

    public Presenter_forwhat(MVPInterface_forwhat minterface) {
        this.minterface = minterface;
    }

    public void inactsetter(InitiationActivity inact) {
        this.inact = inact;
    }

    public void valuereader() {

        inact.setButtonforwhatispressed(true);

        if (minterface.isbabychecked()){
            inact.obidience.setValue(max(inact.obidience.getValue(),3));
            inact.agressive.setValue(min(inact.agressive.getValue(),2));
            inact.active.setValue(min(inact.active.getValue(),4));
        }

        if (minterface.isfrendchecked()){

            inact.agressive.setValue(min(inact.agressive.getValue(),3));
        }

        if (minterface.isrunchecked()){

            inact.obidience.setValue(max(inact.obidience.getValue(),2));
            inact.agressive.setValue(min(inact.agressive.getValue(),3));
            inact.active.setValue(max(inact.active.getValue(),3));
            inact.hardy.setValue(max(inact.hardy.getValue(),4));
        }

        if (minterface.ishuntchecked()){inact.hunt.setValue(1); }

        if (minterface.isobidiencechecked()){

            inact.obidience.setValue(max(inact.obidience.getValue(),4));
            inact.agressive.setValue(min(inact.agressive.getValue(),4));
            inact.size.setValue(max(inact.size.getValue(),3));

        }
        if (minterface.isguardchecked()){

            inact.guard.setValue(max(inact.guard.getValue(),4));
        }
    }
}
