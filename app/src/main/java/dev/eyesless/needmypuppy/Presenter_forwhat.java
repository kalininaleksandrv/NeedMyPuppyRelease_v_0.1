package dev.eyesless.needmypuppy;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 28.06.2017.
 */

class Presenter_forwhat {


    private MVPInterface_forwhat minterface;

    private InitiationActivity inact;

    private ArrayList<Breed_mod> myListOfBreed;

    private LinkedHashSet<Breed_mod> novaListOfBreed;

    public Presenter_forwhat(MVPInterface_forwhat minterface) {

        this.minterface = minterface;
        this.novaListOfBreed = new LinkedHashSet<>();
    }

    public void inactsetter(InitiationActivity inact) {
        this.inact = inact;
        myListOfBreed = inact.getMyListOfBreed_m();
    }


    public void valuereader() {
        inact.setButtonforwhatispressed(true);
        inact.setMyListOfBreed_m(converttoarraylist(novaListOfBreed));
    }


    public void countTheBreeds() {
        getnewlist();
        minterface.setnumberofbreeds(gettotal(), getchoosed(), getnumbers());
    }

    private void getnewlist() {

        novaListOfBreed.clear();

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

    private int gettotal() { return myListOfBreed.size();}

    private int getchoosed() {return novaListOfBreed.size();}


    private Integer[] getnumbers() {

        int child = 0;
        int company = 0;
        int running = 0;
        int hunt = 0;
        int obidience = 0;
        int guard = 0;
        int zks = 0;
        int agility = 0;

        Integer[] count = new Integer[]{child, company, running, hunt, obidience, guard, zks, agility};

        Iterator<Breed_mod> myBreedIterator = myListOfBreed.iterator();

        while (myBreedIterator.hasNext()) {
            Breed_mod breed = myBreedIterator.next();
                 if (breed.getFor_child()==1){
                     count[0] ++;
                 }
                 if (breed.getFor_company()==1){
                       count[1] ++;
                  }
                 if (breed.getFor_running()==1){
                      count[2] ++;
                  }
                 if (breed.getFor_hunt()==1){
                       count[3] ++;
                   }
                 if (breed.getFor_obidience()==1){
                     count[4] ++;
                 }
                 if (breed.getFor_guardterritory()==1){
                     count[5] ++;
                 }
                 if (breed.getFor_zks()==1){
                      count[6] ++;
                 }
                 if (breed.getFor_agility()==1){
                     count[7] ++;
                 }
        }

        return count;
    }

    private ArrayList<Breed_mod> converttoarraylist(LinkedHashSet<Breed_mod> novaListOfBreed) {

        Iterator<Breed_mod> myBreedIterator = novaListOfBreed.iterator();

        ArrayList<Breed_mod> destinationlist = new ArrayList<>();

        while (myBreedIterator.hasNext()) {
            Breed_mod breed = myBreedIterator.next();
            destinationlist.add(breed);
        }

        return destinationlist;
    }
}
