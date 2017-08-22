package dev.eyesless.needmypuppy;

import android.util.Log;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Eyesless on 27.06.2017.
 */

public class Presenter_aboutdog {

    private MVPInterface_aboutdog minterface;


    private InitiationActivity inact;

    public Presenter_aboutdog(MVPInterface_aboutdog minterface) {

        this.minterface = minterface;

    }

    public void inactsetter(InitiationActivity inact) {

        this.inact = inact;
    }

    public void valuereader (){

        inact.setButtonaboutdogispressed(true);

        //послушка
        inact.obidience.setValue(max (inact.obidience.getValue(), max(4-minterface.istime(), 5-finalyactive()))); //чем меньше времени тем выше должно быть послушание породы, чем меньше активность тем выше послушание породы
        if (minterface.isexp()<2 && minterface.iscynologist()<4) {inact.obidience.setValue (min(inact.obidience.getValue(), 3));} //если нет опыта и нет кинолога, то минимальная послушка 3
        if (minterface.istime()>1 && minterface.iscynologist()==4){inact.obidience.setValue(min(inact.obidience.getValue(), 2));} //если времени более часа в день и есть отличный доступ к кинологу, повысить минимально допустимое послушание до 2, даже если ранее выставлено больше
        if (minterface.isexp()>3) {inact.obidience.setValue(min(inact.obidience.getValue(), 1));} //если опыт ЭКСПЕРТ то уменьшить минимально допустимое послушание до 1, даже если ранее выставлено больше
        if (minterface.isage()<2) {inact.obidience.setValue(max(inact.obidience.getValue(), 3));} //если возраст менее 16 лет то увеличить минимально допустимое послушание до 3, даже если ранее выставлено меньше

        //охранные качества
        if (minterface.iswalk()==5) {inact.guard.setValue(min(inact.guard.getValue(), 5));}//если собака содержится на своем участке, то то максимально охранные качества 5

        //агрессия
        inact.agressive.setValue(min(inact.agressive.getValue(), max(minterface.isexp()+1, minterface.istime()+2))); //минимальное значение из прямой зависимости от опыта и времени,
        if (minterface.istime()>1 && (minterface.iscynologist()==4 || minterface.isexp()>3)){inact.agressive.setValue(max(inact.agressive.getValue(), minterface.istime()+2));} //если времени  2-3 часа в день и есть отличный доступ к кинологу или пользователь ЭКСПЕРТ, повысить максимально допустимую агрессию до 4, даже если ранее выставлено больше
        if (minterface.iswalk()==5 && minterface.isexp()>1) {inact.agressive.setValue(max(inact.agressive.getValue(),(minterface.isexp()+2)));}//если собака содержится на своем участке, то допускается повышенная агрессия, кореллирующая с опытом
        if (minterface.isage()<2 || (minterface.isage() ==5 && minterface.isfamily() !=3 && minterface.iswalk()!=5)){ inact.agressive.setValue(min(inact.agressive.getValue(), 2));} //возраст менее 16лет или более 60 лет при отсутствии более активных членов семьи, то уменьшить максимально допустимую агрессивность до 2, даже если ранее выставлено больше
        if (finalyactive()<3){ inact.agressive.setValue(min(inact.agressive.getValue(), 2));} //если уровень физической активности не удовлетворительный то уменьшить максимально допустимую агрессивность до 2, даже если ранее выставлено больше

        //активность
        inact.active.setValue(min(inact.active.getValue(), max(finalyactive(), max (minterface.iswalk()+1 ,minterface.istime()+2)))); //чем больше времени или активности, тем более активная порода допускается, также если хорошие условия выгула
        if (minterface.istime()<2 && (minterface.isexp()<3 || minterface.iscynologist()<4)) {inact.active.setValue(min(inact.active.getValue(), 4));} //если нет опыта и нет кинолога или времени, то минимальная активность 4
        if (minterface.isage() >3) {inact.size.setValue(min(inact.size.getValue(), finalyactive()));} //если возраст больше 40 то активность собаки прямо равна активность (finalyactive)
        if (finalyactive()<3){ inact.active.setValue(min(inact.active.getValue(), 3));} //если уровень физической активности не удовлетворительный то уменьшить максимально допустимую активность до 3, даже если ранее выставлено больше

        //размер
        if (minterface.isage() < 2 || minterface.isage() ==5 || finalyactive() < 2) {inact.size.setValue(min(inact.size.getValue(), 3));} //если возраст менее 16 или активность менее нормальной (для старшего возраста менее хорошей), то максимальный размер не более 3
        if (minterface.iscynologist() < 3 || minterface.isexp() < 2) {inact.size.setValue(min(inact.size.getValue(), 3));} //если нет кинолога или опыта то размер не более 3
        if (minterface.iscynologist() > 2 || minterface.isexp() >1) {inact.size.setValue(min(inact.size.getValue(), max(minterface.istime()+2, minterface.iswalk()+1)));} //если есть опыт или кинолог то прямая зависимость от времени
        if (minterface.iswalk() >3 && minterface.isexp() >1) {inact.size.setValue(max(inact.size.getValue(), minterface.isexp()+2));} //если есть собственный участок и опыт не "нет", то размер = опыт +2
        if (minterface.isexp()<4) {inact.size.setValue(min(inact.size.getValue(), minterface.iswalk()+2));} //если нет места для выгула то размер уменьшается кроме экспертных пользователей

        //уход
        if (minterface.isage()<2 || (minterface.isage() ==5 && minterface.isfamily() !=3)){ inact.care.setValue(min(inact.care.getValue(), 2));} //возраст менее 16лет или более 60 лет при отсутствии более активных членов семьи, то уменьшить заботу до 2
        inact.care.setValue(min(inact.care.getValue(), max(1, max (minterface.isgrimmer() ,minterface.istime())))); //сложность в уходе напрямую зависит от наличия времени или доступа к груммеру

        //чекбоксы
        if (minterface.ishavechildboxchecked() && minterface.istime()<2){
            inact.care.setValue(min(inact.care.getValue(), 2)); //собаки со сложным уходом если есть дети и времени на собаку менее 2-3 часов в день
            inact.obidience.setValue(max(inact.obidience.getValue(), 3)); //послушание не менее 4
            inact.agressive.setValue(min(inact.agressive.getValue(), 3)); //агрессия не более 3
        }
        if (minterface.ishavepetboxchecked() && minterface.isexp()<2 && minterface.iswalk()<3){
            inact.obidience.setValue(min(inact.obidience.getValue(), 3)); //если есть домашние питомцы и нет опыта и выгул ограничен то послушание не менее 3 и активность не более 3 (исключаем многих терьеров и охотничих)
            inact.active.setValue(min(inact.active.getValue(), 3));
        }

        //корректируем показатели от размера
        if (inact.size.getValue()<3) {
            inact.obidience.setValue(0);
            inact.agressive.setValue(5);
            inact.care.setValue(max(inact.care.getValue(), 3));
        }
    }

    //корректируем показатель активности от возраста или от менее активных членов семьи
    private int finalyactive (){

        int activwithage = minterface.isactive()+1;
        if (minterface.isage() == 5){activwithage --;}

        if (minterface.isfamily() ==4) {activwithage --;}

        return activwithage;
    }
}
