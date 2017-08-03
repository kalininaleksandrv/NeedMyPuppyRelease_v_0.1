package dev.eyesless.needmypuppy;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity {

    Button startbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_start_screen);
        startbutton = (Button) findViewById(R.id.button2);
        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startintent = new Intent(StartScreen.this, MainActivity.class);
                startActivity(startintent);
            }
        });



    }


}
