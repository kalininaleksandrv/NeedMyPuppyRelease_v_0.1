package dev.eyesless.needmypuppy;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.daimajia.numberprogressbar.OnProgressBarListener;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressBarFragment extends Buttons_Abstract_Fragment implements OnProgressBarListener {

    protected TextView progresstext;
    protected int currentprogress;
    private Timer timer;
    private NumberProgressBar bnp;

    protected static String MY_PROGRESS = "myProgress";



    public ProgressBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState !=null) {

            this.currentprogress = savedInstanceState.getInt(MY_PROGRESS);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress_bar, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        currentprogress = inact.countProgressStatus();

        if (inact.countProgressStatus()>0) runStatusBar (currentprogress);



         progresstext = (TextView)parentview.findViewById(R.id.statusText);

    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (timer != null){
        timer.cancel();}
    }

    private void runStatusBar(final int count) {

        bnp = (NumberProgressBar)parentview.findViewById(R.id.numberProgressBar);
        bnp.setProgress(count-25);
        bnp.setOnProgressBarListener(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int i = bnp.getProgress();

                        if(i < count){bnp.incrementProgressBy(1);}

                    }
                });
            }
        }, 1000, 100);

    }

    @Override
    public void onProgressChange(int current, int max) {

        if(current == max) {
            progresstext.setText("Достигнута максимальная точность, нажмите кнопку ГОТОВО");
        }

    }

    public void onSaveInstanceState (Bundle savedInstanceState) {

        savedInstanceState.putInt(MY_PROGRESS, currentprogress);

    }
}
