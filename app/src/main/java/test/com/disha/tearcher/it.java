package test.com.disha.tearcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import test.com.disha.R;


public class it extends Fragment {

    String question;
    ArrayList <String> mQuestions1 ;
    RecyclerView recyclerView;
    MyadaptorIT Myadap;
    DatabaseReference Databaseref2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
          View v= inflater.inflate(R.layout.it,container,false);


        Databaseref2 = FirebaseDatabase.getInstance().
                getReference().child("Stream").child("EngineeringIT");



        if(tquestion.ETC==1) {

            Databaseref2 = FirebaseDatabase.getInstance().
                    getReference().child("Stream").child("EngineeringETC");

        }

        if(tquestion.Meach==1) {

            Databaseref2 = FirebaseDatabase.getInstance().
                    getReference().child("Stream").child("EngineeringMech");

        }

        if(tquestion.mba==1) {

            Databaseref2 = FirebaseDatabase.getInstance().
                    getReference().child("Stream").child("MBA");

        }

        if(tquestion.medical==1) {

            Databaseref2 = FirebaseDatabase.getInstance().
                    getReference().child("Stream").child("Medical");

        }




        mQuestions1 = new ArrayList<>();
        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerViewIT);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Databaseref2.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> mChildrennn = dataSnapshot.getChildren();
                for(DataSnapshot mChildd:mChildrennn)
                {
                    question =mChildd.child("question").getValue().toString().trim();
                    mQuestions1.add(question);

                }
                Myadap = new MyadaptorIT(mQuestions1,getContext());

                recyclerView.setAdapter(Myadap);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        return v;
    }
}
