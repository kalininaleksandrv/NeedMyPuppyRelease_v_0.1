package dev.eyesless.needmypuppy;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 28.06.2017.
 */

class Presenter_forwhat {


    private MVPInterface_forwhat minterface;

    private InitiationActivity inact;

    private ArrayList<Breed_mod> myListOfBreed;

    private ArrayList<Breed_mod> novaListOfBreed;

    public Presenter_forwhat(MVPInterface_forwhat minterface) {

        this.minterface = minterface;
        this.novaListOfBreed = new ArrayList<>();
    }

    public void inactsetter(InitiationActivity inact) {

        this.inact = inact;
        myListOfBreed = inact.getMyListOfBreed_m();

    }


    public void valuereader() {

        inact.setButtonforwhatispressed(true);

    }


    public void countTheBreeds() {


        getnewlist();

        minterface.setnumberofbreeds(gettotal(), getnumbers());

    }



    private void getnewlist() {

        Iterator<Breed_mod> myBreedIterator = myListOfBreed.iterator();

        while (myBreedIterator.hasNext()) {
            Breed_mod breed = myBreedIterator.next();
            if (minterface.isbabychecked()){
                if (breed.getFor_child()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.isfrendchecked()){
                if (breed.getFor_company()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.isrunchecked()){
                if (breed.getFor_running()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.ishuntchecked()){
                if (breed.getFor_hunt()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.isobidiencechecked()){
                if (breed.getFor_obidience()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.isguardchecked()){
                if (breed.getFor_guardterritory()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.iszkschecked()){
                if (breed.getFor_zks()==1){
                    novaListOfBreed.add(breed);
                }
            }
            if (minterface.isagilitychecked()){
                if (breed.getFor_agility()==1){
                    novaListOfBreed.add(breed);
                }
            }

        }

    }

    private int gettotal() {

        if (novaListOfBreed.size() != 0){
            return novaListOfBreed.size();
        } else {
            return myListOfBreed.size();
        }
    }

    private Integer[] getnumbers() {

        int child = 0;

        Integer[] count = new Integer[]{child};

        Iterator<Breed_mod> myBreedIterator = novaListOfBreed.iterator();

        while (myBreedIterator.hasNext()) {
            Breed_mod breed = myBreedIterator.next();
                if (breed.getFor_child()==1){
                    count[0] ++;
                }
        }

        return count;
    }
}
