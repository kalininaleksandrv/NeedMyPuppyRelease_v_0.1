package dev.eyesless.needmypuppy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.security.keystore.KeyNotYetValidException;

/**
 * Created by Eyesless on 10.06.2017.
 */

public class BreedDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "breeds_base.db";
    private static final int DB_VERSION = 5;

    public static final String TABLE_NAME = "breeds";
    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DECRIPTION = "description";
    public static final String KEY_DECRIPTION_FULL = "description_full";
    public static final String KEY_IMAGE_RES_ID = "image_resource_id";
    public static final String KEY_IMAGE_RES_ID_BIG = "image_resource_id_big";
    public static final String KEY_OBIDIENCE = "obidience";
    public static final String KEY_GUARD = "guard";
    public static final String KEY_AGRESSIVE = "agressive";
    public static final String KEY_ACTIVE = "active";
    public static final String KEY_HARDY = "hardy";
    public static final String KEY_SIZE = "size";
    public static final String KEY_CARE = "care";
    public static final String KEY_HUNT = "hunt";
    public static final String KEY_WEBLINC = "weblinc";
    public static final String KEY_WEBLINC_WIKI = "weblinc_wiki";
    public static final String KEY_FCIID = "fciid";
    public static final String KEY_HAIR = "hair";
    public static final String KEY_BLACKORWHITE = "blackorwhite";
    public static final String KEY_NOALERGY = "noalergy";
    public static final String KEY_FAVOR = "favorite";
    public static final String KEY_COMMENT = "comment";
    public static final String KEY_FORCHILD = "for_child";
    public static final String KEY_FORCOMPANY = "for_company";
    public static final String KEY_FORRUNNING = "for_running";
    public static final String KEY_FORHUNT = "for_hunt";
    public static final String KEY_FOROBIDIENCE = "for_obidience";
    public static final String KEY_FORGUARDTERRITORY = "for_guardterritory";
    public static final String KEY_FORZKS = "for_zks";
    public static final String KEY_FORAGILITY = "for_agility";

    public BreedDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(" + KEY_ID
                + " integer primary key autoincrement,"
                + KEY_TITLE + " text,"
                + KEY_DECRIPTION + " text,"
                + KEY_DECRIPTION_FULL + " text,"
                + KEY_IMAGE_RES_ID + " text,"
                + KEY_IMAGE_RES_ID_BIG + " text,"
                + KEY_OBIDIENCE + " integer,"
                + KEY_GUARD + " integer,"
                + KEY_AGRESSIVE + " integer,"
                + KEY_ACTIVE + " integer,"
                + KEY_HARDY + " integer,"
                + KEY_SIZE + " integer,"
                + KEY_CARE + " integer,"
                + KEY_HUNT + " text,"
                + KEY_WEBLINC + " text,"
                + KEY_WEBLINC_WIKI + " text,"
                + KEY_FCIID + " integer,"
                + KEY_HAIR + " text,"
                + KEY_BLACKORWHITE + " text,"
                + KEY_NOALERGY + " text,"
                + KEY_FAVOR + " numeric,"
                + KEY_COMMENT + " text,"
                + KEY_FORCHILD + " integer,"
                + KEY_FORCOMPANY + " integer,"
                + KEY_FORRUNNING + " integer,"
                + KEY_FORHUNT + " integer,"
                + KEY_FOROBIDIENCE + " integer,"
                + KEY_FORGUARDTERRITORY + " integer,"
                + KEY_FORZKS + " integer,"
                + KEY_FORAGILITY + " integer" + ")");

        incertBreedsToDb (db,
                "Сибирский Хаски",
                "средняя по размеру, с энергичным характером",
                "Дружелюбная и энергичная. При нормальном развитии не способна укусить человека ни при каких обстоятельствах. Собака категорически непригодна для использования в качестве охотничьей (охотится, но не приносит добычу), сторожевой (инстинкт охраны территории сведён к минимуму) и охранной (в норме у этих собак начисто отсутствует агрессия к человеку). Собака излишне самостоятельна. Источник: wikipedia.org.",
                "husky", "no",
                2, 1, 1, 4, 5, 3, 1, "yes",
                "https://firebasestorage.googleapis.com/v0/b/needmypuppy.appspot.com/o/images%2Fhusky.jpg?alt=media&token=91135e60-98da-43af-875a-01cd571543f9",
                "https://ru.m.wikipedia.org/wiki/%D0%A1%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA%D0%B8%D0%B9_%D1%85%D0%B0%D1%81%D0%BA%D0%B8",
                270, "long", "white", "yes",
                1, 1, 1, 0, 0, 0, 0, 0);
        incertBreedsToDb (db,
                "Лабрадор - ретривер",
                "средняя по размеру, подвижная, веселая",
                "Является одной из самых популярных пород собак. Первоначально эта порода была выведена в качестве рабочей собаки. С хорошим характером, очень подвижный. Легко адаптирующийся, преданный компаньон. Смышлёный, проницательный и послушный, ласковый, настоящий друг. Добрый по натуре, без следа агрессии или чрезмерной робости. Источник: wikipedia.org.",
                "labrador", "no",
                3, 2, 2, 3, 4, 3, 1, "yes",
                "https://firebasestorage.googleapis.com/v0/b/needmypuppy.appspot.com/o/images%2Flabrador.jpg?alt=media&token=22663f52-927f-47e8-8426-9f4a0be6ae8b",
                "https://ru.m.wikipedia.org/wiki/%D0%9B%D0%B0%D0%B1%D1%80%D0%B0%D0%B4%D0%BE%D1%80-%D1%80%D0%B5%D1%82%D1%80%D0%B8%D0%B2%D0%B5%D1%80",
                122, "short", "white black", "no",
                1, 1, 1, 1, 1, 0, 0, 1);
        incertBreedsToDb (db,
                "Hемецкая овчарка",
                "крупная по размеру служебная собака",
                "Это служебная собака с уравновешенным, подвижным типом поведения, способная к разнообразной дрессировке. Немецкая овчарка наиболее успешна, если имеет одного хозяина, но при этом её сильной стороной (как служебной собаки) является то, что она очень легко меняет хозяев и заинтересованно работает с новыми. Немецкая овчарка входит в тройку в рейтинге самых умных пород, составленном доктором Стенли Кореном (англ.). Источник: wikipedia.org.",
                "germ_shep", "no",
                4, 5, 3, 3, 3, 4, 1, "no",
                "https://firebasestorage.googleapis.com/v0/b/needmypuppy.appspot.com/o/images%2Fgerm_shep.jpg?alt=media&token=09a5b557-4e5f-4920-96c3-cfa099f2de5e",
                "https://ru.m.wikipedia.org/wiki/%D0%9D%D0%B5%D0%BC%D0%B5%D1%86%D0%BA%D0%B0%D1%8F_%D0%BE%D0%B2%D1%87%D0%B0%D1%80%D0%BA%D0%B0",
                166, "long", "black", "no",
                1, 1, 1, 0, 1, 1, 1, 0);
        incertBreedsToDb (db,
                "Bельш корги Пемброк",
                "миниатюрная пастушья собака, дружелюбная",
                "Вельш-корги отличает огромное жизнелюбие, живость, доброжелательность. Корги — любящие и преданные, трепетно любят семью своего хозяина. Они очень лояльно относятся ко всем людям и другим животным, легко уживаются с кошками. Очень трепетно относятся к детям, особенно маленьким, следят за ними и оберегают. В отличие от пемброка, кардиган спокойнее, рассудительнее и осторожнее, а пемброк более возбудимый, живой и чуткий. Источник: wikipedia.org.",
                "welsh_corgi_pembr", "no",
                4, 2, 2, 4, 2, 2, 1, "no",
                "https://firebasestorage.googleapis.com/v0/b/needmypuppy.appspot.com/o/images%2Ffs_welsh_pembrok.jpg?alt=media&token=02f832a7-8558-45ab-b464-ecc749d4ae7a",
                "https://ru.m.wikipedia.org/wiki/%D0%92%D0%B5%D0%BB%D1%8C%D1%88-%D0%BA%D0%BE%D1%80%D0%B3%D0%B8",
                39, "long", "no", "no",
                1, 1, 0, 0, 1, 0, 0, 1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);

    }

    public void incertBreedsToDb(SQLiteDatabase db, String name, String description, String description_full,
                                 String resourceId, String resourceIdBig, int obidience, int guard, int agressive,
                                 int active, int hardy, int size, int care, String hunt, String weblinc,String weblinc_wiki,
                                 int fciid, String hair, String blackorwhite, String noalergy,
                                 int for_child, int for_company, int for_running, int for_hunt, int for_obidience, int for_guardterritory,
                                 int for_zks, int for_agility) {

        ContentValues con_breed = new ContentValues();
        con_breed.put (KEY_TITLE, name);
        con_breed.put (KEY_DECRIPTION, description);
        con_breed.put (KEY_DECRIPTION_FULL, description_full);
        con_breed.put (KEY_IMAGE_RES_ID, resourceId);
        con_breed.put (KEY_IMAGE_RES_ID_BIG, resourceIdBig);
        con_breed.put (KEY_OBIDIENCE, obidience);
        con_breed.put (KEY_GUARD, guard);
        con_breed.put (KEY_AGRESSIVE, agressive);
        con_breed.put (KEY_ACTIVE, active);
        con_breed.put (KEY_HARDY, hardy);
        con_breed.put (KEY_SIZE, size);
        con_breed.put (KEY_CARE, care);
        con_breed.put (KEY_HUNT, hunt);
        con_breed.put (KEY_WEBLINC, weblinc);
        con_breed.put (KEY_WEBLINC_WIKI, weblinc_wiki);
        con_breed.put(KEY_FCIID, fciid);
        con_breed.put(KEY_HAIR, hair);
        con_breed.put(KEY_BLACKORWHITE, blackorwhite);
        con_breed.put(KEY_NOALERGY, noalergy);
        con_breed.put(KEY_FORCHILD, for_child);
        con_breed.put(KEY_FORCOMPANY, for_company);
        con_breed.put(KEY_FORRUNNING, for_running);
        con_breed.put(KEY_FORHUNT, for_hunt);
        con_breed.put(KEY_FOROBIDIENCE, for_obidience);
        con_breed.put(KEY_FORGUARDTERRITORY, for_guardterritory);
        con_breed.put(KEY_FORZKS, for_zks);
        con_breed.put(KEY_FORAGILITY, for_agility);

        db.insert(TABLE_NAME, null, con_breed);

    }
}
