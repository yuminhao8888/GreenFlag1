package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //ImageView image;

    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // image = findViewById(R.id.imageGreenFlag);

       // image.setImageResource(R.drawable.logo_green_flag@2x.png);

        layout = findViewById(R.id.buttonCreateAccount);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "hello patrick", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getBaseContext(), CreateAccount.class);
                startActivity(i);
            }
        });
    }
}