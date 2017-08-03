package dev.eyesless.needmypuppy;

import static java.lang.Math.min;

/**
 * Created by Eyesless on 27.06.2017.
 */

public class Presenter_morpho {

    private MVPInterface minterface;

    private InitiationActivity inact;

    public Presenter_morpho(MVPInterface minterface) {

        this.minterface = minterface;

    }


    public void inactsetter(InitiationActivity inact) {

        this.inact = inact;
    }

    public void checkboxreader() {

        inact.setButtonmorphoispressed(true);

        if (minterface.isboxchecked()){inact.noalergy.setValue(1);}
        if (minterface.israreboxchecked()){inact.rare.setValue(1);}
        inact.blackorwhite.setValue(minterface.isblackorwhite());
        inact.hair.setValue(minterface.ishair());
        inact.size.setValue(min(inact.size.getValue(), minterface.issize()));

    }
}
