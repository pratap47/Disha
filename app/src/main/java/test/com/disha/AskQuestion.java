package test.com.disha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AskQuestion extends AppCompatActivity {

    Button btnSubmit;
    EditText edtQuestion;
    Spinner spinner;
    DatabaseReference mDatabase;
    String mStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        btnSubmit=(Button)findViewById(R.id.btnSubmit);
        edtQuestion = (EditText)findViewById(R.id.edtQuestion);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream");
        spinner = (Spinner)findViewById(R.id.spinner);

        String[] mField = {"Engineering-IT","Engineering-ETC","Engineering-Mech","MBA","Medical"};
        ArrayAdapter mSA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mField);
        spinner.setAdapter(mSA);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                if(pos ==  0){
                    mStream = "EngineeringIT";
                }
                else if(pos == 1){
                    mStream= "EngineeringETC";
                }
                else if(pos == 1){
                    mStream="EngineeringMech";
                }
                else if(pos == 1){
                    mStream="MBA";
                }
                else if(pos == 1){
                    mStream = "Medical";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String mQues =  edtQuestion.getText().toString();
            mDatabase=mDatabase.child(mStream).push();
            mDatabase.child("question").setValue(mQues);
            mDatabase.child("flag").setValue("false");
            mDatabase.child("mProfNumber").setValue("q");
            mDatabase.child("mStudentNumber").setValue("q");

            }
        });
    }
}
