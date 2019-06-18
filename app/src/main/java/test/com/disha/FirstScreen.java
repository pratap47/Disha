package test.com.disha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import test.com.disha.tearcher.otpsignin;
import test.com.disha.tearcher.teacherdashboard;

public class FirstScreen extends AppCompatActivity {

    Button btnProf,btnStud;
   String  phone1;
    public static SharedPreferences sharedPreferences1;
    public static final String Default1="";
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
                sharedPreferences1=getSharedPreferences("num", Context.MODE_PRIVATE);
                phone1=sharedPreferences1.getString("Phone",Default1);     //Default value get displayed when no data is entered in main activity
//                uid=sharedPreferences1.getString("Uid",Default);

                if(phone1.equals(Default1) )
                {

                    Toast.makeText(getApplicationContext(), "NO DATA FOUND", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(FirstScreen.this,
                            otpsignin.class));
                }
                else{
                    Intent as=new Intent(FirstScreen.this, teacherdashboard.class);
                    startActivity(as);
                    Toast.makeText(getApplicationContext(), "Data found", Toast.LENGTH_SHORT).show();
                }

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
