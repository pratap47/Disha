package test.com.disha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import test.com.disha.tearcher.otpsignin;

public class FirstScreen extends AppCompatActivity {

    Button btnProf,btnStud;

    SharedPreferences sharedPref;

    SharedPreferences.Editor editor;

    public static String flagForCategory;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first_screen);


        btnProf = (Button)findViewById(R.id.btnProf);

        btnStud = (Button)findViewById(R.id.btnStudent);


        btnProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flagForCategory = "1";

                sharedPref = getSharedPreferences("lang", Context.MODE_PRIVATE);

                editor = sharedPref.edit();

                editor.putString("FLAG", "1");

                editor.apply();

                startActivity(new Intent(FirstScreen.this,
                        otpsignin.class));
            }
        }
        );

        btnStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                flagForCategory = "2";

                sharedPref = getSharedPreferences("lang", Context.MODE_PRIVATE);

                editor = sharedPref.edit();

                editor.putString("FLAG", "2");


                editor.apply();

                startActivity(new Intent(FirstScreen.this, otpsignin.class));
            }
        }
        );
    }

}





