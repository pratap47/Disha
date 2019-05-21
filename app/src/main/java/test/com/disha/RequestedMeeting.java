package test.com.disha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static test.com.disha.tearcher.otpsignin.mDeviceNum;

public class RequestedMeeting extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ReqMeetingItem> mList;
    ReqMeetingAdapter mAdapter;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requested_meeting);


        mList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("requests");
        mDatabase.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Iterable<DataSnapshot> mChildren = dataSnapshot.getChildren();
                 for(DataSnapshot mChild:mChildren){
                     String debug = mChild.child("StudNum").getValue().toString();
                     if(mChild.child("StudNum").getValue().toString().equals(mDeviceNum)){
                         ReqMeetingItem item = new ReqMeetingItem();
                         item.mPurpose = mChild.child("purpose").getValue().toString();
                         item.mStatus = mChild.child("accepted").getValue().toString();

                         mList.add(item);
                         mAdapter.notifyItemInserted(mList.size()-1);

                     }
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

         mAdapter = new ReqMeetingAdapter(getApplicationContext(),mList);
         recyclerView.setAdapter(mAdapter);

    }
}
