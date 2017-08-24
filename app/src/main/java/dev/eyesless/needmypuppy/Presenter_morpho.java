package dev.eyesless.needmypuppy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by Eyesless on 27.06.2017.
 */

public class Presenter_morpho {

    private MVPInterface minterface;

    private InitiationActivity inact;

    private ArrayList<Breed_mod> myListOfBreed;

    private LinkedHashSet<Breed_mod> novaListOfBreed;

    public Presenter_morpho(MVPInterface minterface) {
        this.minterface = minterface;
        this.novaListOfBreed = new LinkedHashSet<>();
    }

    public void inactsetter(InitiationActivity inact) {
        this.inact = inact;
        myListOfBreed = inact.getMyListOfBreed_m();
    }

    public void checkboxreader() {
        inact.setButtonmorphoispressed(true);
        inact.setMyListOfBreed_m(converttoarraylist(novaListOfBreed));
    }

    public void countTheBreeds() {
        getnewlist();
        minterface.setnumberofbreeds(gettotal(), getchoosed());
    }

    private void getnewlist() {

        novaListOfBreed.clear();

        Iterator<Breed_mod> myBreedIterator = myListOfBreed.iterator();

        while (myBreedIterator.hasNext()) {
            Breed_mod breed = myBreedIterator.next();
            novaListOfBreed.add(breed);

            if (minterface.isboxchecked()){
                if (!breed.getB_noalergy().equals("yes")){
                    novaListOfBreed.remove(breed);
                }
            }
            if (!minterface.israreboxchecked()){
                if (breed.getB_image_fs_res_id().equals("yes")){
                    novaListOfBreed.remove(breed);
                }
            }

            if (minterface.ishair() == 1){
                if (!breed.getB_hair().equals("short")){
                    novaListOfBreed.remove(breed);
                }
            }

            if (minterface.ishair() == 2){
                if (!breed.getB_hair().equals("long")){
                    novaListOfBreed.remove(breed);
                }
            }

            if (minterface.isblackorwhite() == 1 ){
                if (!breed.getB_blackorwhite().equals("white") && !breed.getB_blackorwhite().equals("white black")){
                    novaListOfBreed.remove(breed);
                }
            }

            if (minterface.isblackorwhite() == 2 ){
                if (!breed.getB_blackorwhite().equals("black") && !breed.getB_blackorwhite().equals("white black")){
                    novaListOfBreed.remove(breed);
                }
            }

            if (minterface.issize() != 0 ){
                if (breed.getB_size()>minterface.issize()){
                    novaListOfBreed.remove(breed);
                }
            }
        }
    }

    private int gettotal() { return myListOfBreed.size();}

    private int getchoosed() {return novaListOfBreed.size();}

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

