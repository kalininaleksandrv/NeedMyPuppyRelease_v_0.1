package dev.eyesless.needmypuppy;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_search extends Fragment implements MVPInterface_search {

    onButtonListner myButtonListner;

    private View parentview;
    private Button gonextbutton;
    private ImageButton findbutton;
    private TextView incomtext;
    private EditText outcomtext;
    private String searchasq;
    private ImageView findimage;

    public InitiationActivity inact;


    Presenter_search presenter;



    public Fragment_search() {
        // Required empty public constructor

        presenter = new Presenter_search(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inact = ((InitiationActivity) getActivity().getApplicationContext());



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.myButtonListner = (onButtonListner) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        guidlinemoover ();

        parentview = getView();

        iteminitier();

        findimage.setImageResource(R.drawable.find_image);

        presenterinactsetter(inact, getActivity().getApplicationContext());

        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchasq = outcomtext.getText().toString();
                searchstarter();
            }
        });

        gonextbutton.setOnClickListener(myOnClickListner);

    }

    //moove guidline to maximize size of 2nd container in activity_main.xml
    private void guidlinemoover() {

        int orient = getActivity().getResources().getConfiguration().orientation;

        if (orient == Configuration.ORIENTATION_PORTRAIT){

            ((MainActivity) getActivity()).gudlinesetter((float) 0.08);

        } else  ((MainActivity) getActivity()).gudlinesetter((float) 0.001);
    }

    private void iteminitier() {

        findimage = (ImageView) parentview.findViewById(R.id.imageViewSearch);
        gonextbutton = (Button) parentview.findViewById(R.id.button_gonext);
        findbutton = (ImageButton) parentview.findViewById(R.id.imageButton_find);
        incomtext = (TextView) parentview.findViewById(R.id.db_text_recived);
        outcomtext = (EditText) parentview.findViewById(R.id.db_editText_sended);
    }

    private void presenterinactsetter(InitiationActivity inact, Context context) {

        presenter.inactsetter(inact, context);

    }

    View.OnClickListener myOnClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            myButtonListner.buttonClicked(v);

        }
    };

    @Override
    public String onRecive() {

     return  searchasq;

    }

    @Override
    public void onSend(String s) {

        incomtext.setText(s);

    }

    public void toastmaker(String s) {
        Toast myToast = Toast.makeText(getContext(), s, Toast.LENGTH_SHORT);
        myToast.setGravity(Gravity.CENTER, 0, 0);
        myToast.show();
    }

    @Override
    public void buttonenabler(boolean b) {
        gonextbutton.setEnabled(b);
    }

    private void searchstarter() {

        presenter.searchstarter();

    }
}
