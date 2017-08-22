package dev.eyesless.needmypuppy;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Eyesless on 03.08.2017.
 */

public class BreedFromAssetsDataBaseHelper extends SQLiteOpenHelper {

    private Context mycontext;
    private static final String DB_NAME = "breeds_base.db";
    private static final int DB_VERSION = 4;
    private final String DB_PATH = "/data/data/dev.eyesless.needmypuppy/databases/";
    public SQLiteDatabase myDataBase;


    public BreedFromAssetsDataBaseHelper(Context context)  {
            super(context, DB_NAME, null, DB_VERSION);

        this.mycontext = context;

        boolean dbexist = checkdatabase();
        if (dbexist) {
            Log.w("MY_TAG", "DB exist");
            opendatabase();
        } else {
            Log.w("MY_TAG", "try to create DB");
            createdatabase();
        }
    }

    public void createdatabase()  {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            Log.w("MY_TAG", "DB exist");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch(SQLiteException e) {
            Log.w("MY_TAG", "DB does not exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {

        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream("/data/data/dev.eyesless.needmypuppy/databases/breeds_base.db");

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READONLY);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (newVersion > oldVersion)
            try {
                copydatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
