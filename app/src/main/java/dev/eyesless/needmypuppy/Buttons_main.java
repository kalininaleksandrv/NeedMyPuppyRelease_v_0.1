package dev.eyesless.needmypuppy;


import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class Buttons_main extends Fragment {

onButtonListner myButtonListner;


    public Buttons_main() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("MY_TAG", "Buttons_main");

        return inflater.inflate(R.layout.buttons_main, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.myButtonListner = (onButtonListner) context;

    }

    @Override
    public void onStart() {
        super.onStart();

        ((MainActivity) getActivity()).gudlinesetter((float) 0.35);


        final View parentview = getView();

        ImageButton aboutownerbutton = (ImageButton) parentview.findViewById(R.id.imageButton_aboutowner);
        ImageButton forwhatbutton = (ImageButton) parentview.findViewById(R.id.imageButton_aboutdog);
        ImageButton justlist = (ImageButton) parentview.findViewById(R.id.imageButton_justlist);
        ImageButton morpho = (ImageButton) parentview.findViewById(R.id.imageButton_morpho);


        //реализуем онкликлистнер на подключенной кнопке

        aboutownerbutton.setOnClickListener(myOnClickListner);
        justlist.setOnClickListener(myOnClickListner);
        forwhatbutton.setOnClickListener(myOnClickListner);
        morpho.setOnClickListener(myOnClickListner);


    }

    //создаем онклик листнер для кнопок и передаем в методе онклик значение кнопки в метод buttonclicked интерфейса

    View.OnClickListener myOnClickListner = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

       myButtonListner.buttonClicked(v);

        }
    };

}
