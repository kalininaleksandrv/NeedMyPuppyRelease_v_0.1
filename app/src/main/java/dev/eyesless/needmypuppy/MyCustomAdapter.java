package dev.eyesless.needmypuppy;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eyesless on 31.05.2017.
 */

public class MyCustomAdapter extends ArrayAdapter<MyBucket> {
    
    private final ArrayList<MyBucket> values;
    private final Context context;


    public MyCustomAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<MyBucket> values) {
        super(context, R.layout.list_item_doubletext, values);
        this.values = values;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        
        View v = convertView;
        
        
        if (v == null){

            LayoutInflater vinfalater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vinfalater.inflate(R.layout.list_item_doubletext, parent, false);
        }
        
        MyBucket newbucket = values.get(position);
        
        if (newbucket != null) {

            TextView title = (TextView) v.findViewById(R.id.title);
            TextView value = (TextView) v.findViewById(R.id.value);

            
            if (title != null) {
                
                title.setText(newbucket.getTitle());
            }
            if (value != null) {

                value.setText(String.valueOf(newbucket.getValue()));

            }
            
        }
        
        return v;
    }
}
