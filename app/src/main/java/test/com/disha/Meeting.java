package test.com.disha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static test.com.disha.tearcher.otpsignin.mDeviceNum;

public class Meeting extends AppCompatActivity {

    EditText edtReqMeet;
    Button btnSubmit;
    String mProfNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        edtReqMeet = (EditText)findViewById(R.id.edtReqMeet);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);

        Intent intent = getIntent();
        mProfNum = intent.getStringExtra("mProfNum");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mPurpose = edtReqMeet.getText().toString();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("requests").push();
                mDatabase.child("purpose").setValue(mPurpose);
                mDatabase.child("profNum").setValue(mProfNum);
                mDatabase.child("StudNum").setValue(mDeviceNum);
                startActivity(new Intent(Meeting.this,StudentDashboard.class));
            }
        });
    }
}
