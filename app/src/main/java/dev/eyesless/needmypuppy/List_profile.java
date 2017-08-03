package dev.eyesless.needmypuppy;

import android.app.ListActivity;
import android.content.Intent;
import android.support.constraint.solver.SolverVariable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class List_profile extends ListActivity {

    private ArrayList<?> myList;
    private InitiationActivity inact;
    private ListView listBuskets;
    public static final String LIST = "list";


    public List_profile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inact = ((InitiationActivity) getApplicationContext());
        listBuskets = getListView();
        Intent intent = getIntent();
        myList = (ArrayList<?>)intent.getSerializableExtra(LIST);

        //depends of type, contains in list, use different adapter

        Class actualclass = myList.get(0).getClass();

        if (actualclass == MyBucket.class){
            MyCustomAdapter bucketadapter = new MyCustomAdapter(this, R.layout.list_item_doubletext, (ArrayList<MyBucket>) myList);
            listBuskets.setAdapter(bucketadapter);
        }

        if (actualclass == String.class){
            ArrayAdapter<String> stringadapter = new ArrayAdapter<>(this, R.layout.list_item, (ArrayList<String>)myList);
            listBuskets.setAdapter(stringadapter);
        }


    }

}
