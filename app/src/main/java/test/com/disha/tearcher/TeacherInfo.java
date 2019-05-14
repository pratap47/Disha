package test.com.disha.tearcher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import test.com.disha.R;
import static test.com.disha.tearcher.otpsignin.phonenumber;


public class TeacherInfo extends AppCompatActivity {
EditText  edtName;
EditText edtCollegeName;
EditText edtBranch;
Button btnSubmit;
    String  mPhonet;

    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

          edtName = (EditText) findViewById(R.id.edtNamet);
          edtCollegeName=(EditText) findViewById(R.id.edtCollegeNamet);
          edtBranch =(EditText) findViewById(R.id.edtBrancht);
          btnSubmit = (Button) findViewById(R.id.btnSubmitt);
       mDatabase = FirebaseDatabase.getInstance().getReference().child("Teachers");
       mPhonet=getIntent().getStringExtra("phoneno");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = edtName.getText().toString();
                String mCollegeName = edtCollegeName.getText().toString();
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
                {     mDatabase= mDatabase.child(phonenumber);
                    mDatabase.child("name").setValue(mName);
                    mDatabase.child("research").setValue(mBranch);
                    mDatabase.child("college").setValue(mCollegeName);


                    Intent intent = new Intent(TeacherInfo.this, teacherDashboard.class);
                    startActivity(intent);
                }

            }
        });


    }
}
