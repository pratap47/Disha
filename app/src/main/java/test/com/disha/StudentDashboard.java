package test.com.disha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static test.com.disha.tearcher.otpsignin.mDeviceNum;

public class StudentDashboard extends AppCompatActivity {

    Spinner spinner;
    Button btnAsk,btnMeet,btnMyQues,btnReq;
    StudentDashboardAdapter mAdapter;
    ArrayList<String> mQuestions ;
    public static String flagForMeeting;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        SharedPreferences sharedPref = getSharedPreferences("num", Context.MODE_PRIVATE);
       // mDeviceNum = sharedPref.getString("NUM", "");

        mQuestions = new ArrayList<>();
        btnAsk = (Button)findViewById(R.id.btnAsk);
        btnMeet = (Button)findViewById(R.id.btnMeet);
        btnMyQues = (Button)findViewById(R.id.btnMyQues);
        btnReq = (Button)findViewById(R.id.btnReq);
        btnAsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagForMeeting = "false";
                startActivity(new Intent(StudentDashboard.this,ProfessorList.class));
            }
        });

        btnMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flagForMeeting = "true";
                Intent intent = new Intent(StudentDashboard.this,ProfessorList.class);

                startActivity(intent);
            }
        });
        btnMyQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this,StudQues.class));
            }
        });
        btnReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentDashboard.this,RequestedMeeting.class));
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        String[] mField = {"Engineering-IT","Engineering-ETC","Engineering-Mech","MBA","Medical"};
        ArrayAdapter mSA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mField);
        spinner.setAdapter(mSA);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if(pos==0){
                    mQuestions.clear();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream").child("EngineeringIT");
                    mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                            for(DataSnapshot mChild:mChildren){
                                String mQues = mChild.child("question").getValue().toString();
                                mQuestions.add(mQues);
                                mAdapter.notifyItemInserted(mQuestions.size()-1);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                    mAdapter = new StudentDashboardAdapter(getApplicationContext(),mQuestions);
                    recyclerView.setAdapter(mAdapter);
                }
                if(pos==1){
                    mQuestions.clear();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream").child("EngineeringETC");
                    mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                            for(DataSnapshot mChild:mChildren){
                                String mQues = mChild.child("question").getValue().toString();
                                mQuestions.add(mQues);
                                mAdapter.notifyItemInserted(mQuestions.size()-1);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mAdapter = new StudentDashboardAdapter(getApplicationContext(),mQuestions);
                    recyclerView.setAdapter(mAdapter);
                }
                if(pos==2){
                    mQuestions.clear();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream").child("EngineeringMech");
                    mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                            for(DataSnapshot mChild:mChildren){
                                String mQues = mChild.child("question").getValue().toString();
                                mQuestions.add(mQues);
                                mAdapter.notifyItemInserted(mQuestions.size()-1);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mAdapter = new StudentDashboardAdapter(getApplicationContext(),mQuestions);
                    recyclerView.setAdapter(mAdapter);
                }
                if(pos==3){
                    mQuestions.clear();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream").child("MBA");
                    mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                            for(DataSnapshot mChild:mChildren){
                                String mQues = mChild.child("question").getValue().toString();
                                mQuestions.add(mQues);
                                String debug = mQues;
                                int d = mQuestions.size();
                                int l =d;
                                mAdapter.notifyItemInserted(mQuestions.size()-1);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mAdapter = new StudentDashboardAdapter(getApplicationContext(),mQuestions);
                    recyclerView.setAdapter(mAdapter);
                }
                if(pos ==4){
                    mQuestions.clear();
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Stream").child("Medical");
                    mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                            for(DataSnapshot mChild:mChildren){
                                String mQues = mChild.child("question").getValue().toString();
                                mQuestions.add(mQues);
                                mAdapter.notifyItemInserted(mQuestions.size()-1);
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    mAdapter = new StudentDashboardAdapter(getApplicationContext(),mQuestions);
                    recyclerView.setAdapter(mAdapter);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


    }
}
