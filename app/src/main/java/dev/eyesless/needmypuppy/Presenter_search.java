package dev.eyesless.needmypuppy;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * Created by Eyesless on 29.06.2017.
 */

public class Presenter_search {

    static final int MAX_SEARCH_OUTPUT = 5;

    private MVPInterface_search minterface;
    private Context context;
    private InitiationActivity inact;
    BreedDataBaseCreator myDataCreator;


    public Presenter_search(MVPInterface_search minterface) {

        this.minterface = minterface;
    }

    public void inactsetter(InitiationActivity inact, Context context) {

        this.inact = inact;
        this.context = context;
    }


    public void searchstarter() {


            myDataCreator = new BreedDataBaseCreator(context, inact);
            myDataCreator.databasecreator();
            myDataCreator.onCreateDb(getSearchAsq(stringnormalizer (minterface.onRecive())));
            inact.setListOfTitles();

        ArrayList<String> ret = inact.getListOfTitles();

        if (ret.size() == 0){

            minterface.toastmaker("По вашему запросу ничего не найдено, попробуйте еще раз");

            minterface.buttonenabler(false);

        } else {

            minterface.buttonenabler(true);

            int cnt = ret.size();

            if (cnt > MAX_SEARCH_OUTPUT) {
                cnt = MAX_SEARCH_OUTPUT;
            } else {
                cnt = ret.size();
            }

            StringBuilder sb = new StringBuilder();

            Iterator<String> itr = ret.iterator();

            int i = 0;

            while (itr.hasNext() && i < MAX_SEARCH_OUTPUT) {

                    sb.append( itr.next() + "\n");
                    i++;
            }


            if (ret.size() > MAX_SEARCH_OUTPUT) {
                minterface.onSend(context.getString(R.string.onyourasq) + "\n \n" + sb.toString() + "\n" + context.getString(R.string.andmore) + " " + String.valueOf(ret.size() - MAX_SEARCH_OUTPUT) + " ...");
            } else minterface.onSend(context.getString(R.string.onyourasq) + "\n \n" + sb.toString() + "\n");

        }

    }

    private String stringnormalizer(String s) {

        if (s.isEmpty()) {
            return "%";
        }

        else {

            String m = s.substring(1);
            return "%"+m+"%";
        }
    }

    private String[] getSearchAsq(String s) {

        String title = s;

        int obidience_n = 0;
        int guard_n = 6;
        int agressive_n = 6;
        int active_n = 6;
        int hardy_n = 0;
        int size_n = 6;
        int care_n = 6;

        String hunt_n = "%";
        String hair_n = "%";
        String blackorwhite_n = "%";
        String noalergy_n = "%";
        String rare = "%";


        return new String [] {title, rare, String.valueOf(obidience_n), String.valueOf(guard_n),
                String.valueOf(agressive_n), String.valueOf(active_n),
                String.valueOf(hardy_n), String.valueOf(size_n),
                String.valueOf(care_n), hunt_n, hair_n, blackorwhite_n, noalergy_n};
    }
    
}
