package test.com.disha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import test.com.disha.tearcher.otpsignin;

import static test.com.disha.SignIn.mPhone;

public class Student_details extends AppCompatActivity {

    EditText edtName, edtCollegeName, edtBranch;
    Button btnSubmit;
    DatabaseReference mDatabase;
    String mStudPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
          mDatabase = FirebaseDatabase.getInstance().getReference().child("students");
        edtName=(EditText)findViewById(R.id.edtNamet);
        edtCollegeName=(EditText)findViewById(R.id.edtCollegeNamet);
        edtBranch=(EditText)findViewById(R.id.edtBrancht);

        btnSubmit=(Button)findViewById(R.id.btnSubmitt);
        mStudPhone = otpsignin.mDeviceNum;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = edtName.getText().toString();
                String mCollegeName=edtCollegeName.getText().toString();
                String mBranch = edtBranch.getText().toString();

                if(TextUtils.isEmpty(mName.trim()))
                {
                    edtName.setError("Name can't be empty");
                }
                else if(TextUtils.isEmpty(mBranch.trim()))
                {
                    edtBranch.setError("Branch can't be empty");
                }
                else if(TextUtils.isEmpty(mCollegeName.trim()))
                {
                    edtCollegeName.setError("College name can't be empty");
                }
                else
                {     mDatabase= mDatabase.child(mStudPhone);
                     mDatabase.child("name").setValue(mName);
                      mDatabase.child("branch").setValue(mBranch);
                      mDatabase.child("college").setValue(mCollegeName);


                    Intent intent = new Intent(Student_details.this, StudentDashboard.class);
                    startActivity(intent);
                }

            }
        });
    }
}
