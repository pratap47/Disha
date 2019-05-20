package test.com.disha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfessorList extends AppCompatActivity {

    RecyclerView recyclerViewProf;
    ProfListAdapter mAdapter;
    Spinner spinner;
    DatabaseReference mDatabase;
    ArrayList<ProfListItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_list);

        mList = new ArrayList<>();

        recyclerViewProf = (RecyclerView)findViewById(R.id.recyclerViewProf);
        recyclerViewProf.setHasFixedSize(true);
        recyclerViewProf.setLayoutManager(new LinearLayoutManager(this));
        spinner = (Spinner)findViewById(R.id.spinner);

        String[] mField = {"Engineering-IT","Engineering-ETC","Engineering-Mech","MBA","Medical"};
        ArrayAdapter mSA = new ArrayAdapter(this,android.R.layout.simple_spinner_item, mField);
        spinner.setAdapter(mSA);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Teachers");
        mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                for(DataSnapshot mChild:mChildren){
                    ProfListItem item = new ProfListItem();
                    item.mProfNum = mChild.getKey();
                    item.mCollegeName= mChild.child("college").getValue().toString();
                    item.mProfName = mChild.child("name").getValue().toString();
                    item.mResearch= mChild.child("research").getValue().toString();

                    mList.add(item);
                    mAdapter.notifyItemInserted(mList.size()-1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mAdapter =  new ProfListAdapter(getApplicationContext(),mList);
        recyclerViewProf.setAdapter(mAdapter);



    }
}
