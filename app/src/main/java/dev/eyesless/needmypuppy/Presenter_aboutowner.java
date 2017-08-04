package dev.eyesless.needmypuppy;

import android.util.Log;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 27.06.2017.
 */

class Presenter_aboutowner {

    private MVPInterface_aboutowner minterface;

    private InitiationActivity inact;

    public Presenter_aboutowner(MVPInterface_aboutowner minterface) {

        this.minterface = minterface;

        Log.w("MY_TAG", "aboutowner presenter");


    }

    public void inactsetter(InitiationActivity inact) {

        this.inact = inact;

    }

    public void valuereader() {

        inact.setButtonaboutownerispressed(true);


        inact.obidience.setValue(max (inact.obidience.getValue(), max(4-minterface.istime(), 3-finalyactive()))); //чем меньше времени тем выше должно быть послушание породы, чем меньше активность тем выше послушание породы
        if (minterface.isexp()>3) {inact.obidience.setValue(min(inact.obidience.getValue(), 2));} //если опыт ЭКСПЕРТ то уменьшить минимально допустимое послушание до 2, даже если ранее выставлено больше
        if (minterface.isage()<2) {inact.obidience.setValue(max(inact.obidience.getValue(), 3));} //если возраст менее 16 лет то увеличить минимально допустимое послушание до 3, даже если ранее выставлено меньше, превалирует над ЭКСПЕРТ


        inact.agressive.setValue(min(inact.agressive.getValue(), min(minterface.isexp()+1, minterface.istime()+2))); //минимальное значение из если прямой зависимости от опыта и времени,
        if (minterface.isage()<2 || minterface.isage() ==5){ inact.agressive.setValue(min(inact.agressive.getValue(), 2));} //возраст менее 16лет или более 60 лет, то уменьшить максимально допустимую агрессивность до 2, даже если ранее выставлено больше

        inact.active.setValue(min(inact.active.getValue(), max(finalyactive(), max(minterface.isexp(), minterface.istime())))); //чем больше времени, опыта или активности, тем более активная порода допускается
        if (minterface.isage() < 2 || minterface.isage() ==5 || finalyactive() < 2) {inact.size.setValue(min(inact.size.getValue(), 3));} //если возраст менее 16 или активность менее нормальной (для старшего возраста менее хорошей), то максимальный размер не более 3
        if (minterface.istime() < 2 || minterface.isexp() < 2) {inact.size.setValue(min(inact.size.getValue(), 4));} //если на на собаку готов тратить менее часа в день или опыта нет то размер не более 4

        inact.care.setValue(min(inact.care.getValue(), min(minterface.istime()+1, minterface.isexp() +1))); //чем больше времени или экспертизы тем больше показатель специфического ухода

        if (minterface.isage()<2 || minterface.isage() ==5){ inact.size.setValue(min(inact.size.getValue(), 3));} //возраст менее 16лет или более 60 лет, то уменьшить максимально допустимый размер до 3

    }

    //корректируем показатель активности от возраста или от менее активных членов семьи
   private int finalyactive (){

       int activwithage;
       if (minterface.isage() == 5){activwithage = minterface.isactive()+1;} else activwithage = minterface.isactive()+2;

       int activwithfamily;
       if (minterface.isfamily() ==4) {activwithfamily = activwithage-1;} else activwithfamily = activwithage;

       int summaryactive = (min(minterface.isactive()+2, min(activwithfamily, activwithage)));

        return summaryactive;
   }
}
