package dev.eyesless.needmypuppy;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_byingpuppey extends Buttons_Abstract_Fragment {


    public Fragment_byingpuppey() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_byingpuppey, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        guidlinemoover ();

        String listoflinks = "Кроме WIKI (куда мы даем ссылку с описания пород), " +
                "можно использовать например следующие сайты:\n\n"
                + "http://www.vsesobaki.ru\n"
                + "http://superpesik.ru\n"
                + "http://poisk-druga.ru\n"
                + "https://tvaryny.com/ru\n"
                + "http://animal.ru\n"
                + "http://www.zooclub.ru\n"
                + "http://www.konura.info\n"
                + "http://petsik.ru\n"
                + "http://vseprosobak.ru\n\n"
                + "а также обратите внимания на крупные форумы собаководов:\n\n"
                + "http://pesiq.ru/forum\n\n"
                + "и на клубные ресурсы, специализирующиеся на конкретной породе, например:\n\n"
                + "http://husky.forum.ru\n"
                + "http://www.biglik.ru\n\n"
                + "ЖЕЛАЕМ ВАМ УДАЧИ!";

        TextView mListLinks = (TextView)parentview.findViewById(R.id.listoflinks_buying);

        mListLinks.setText(listoflinks);


    }

    //moove guidline to maximize size of 2nd container in activity_main.xml
    private void guidlinemoover() {

        int orient = getActivity().getResources().getConfiguration().orientation;

        if (orient == Configuration.ORIENTATION_PORTRAIT){

            ((MainActivity) getActivity()).gudlinesetter((float) 0.08);

        } else  ((MainActivity) getActivity()).gudlinesetter((float) 0.001);
    }
}
