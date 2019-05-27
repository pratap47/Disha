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
import java.util.List;

import test.com.disha.R;

import static test.com.disha.tearcher.otpsignin.phonenumber;

public class tmeating extends Fragment {

    String profphoneno ;
    String status;
    String purpose;
    String Name;
    String studentphno;
    List <ListItem> listitems;
     RecyclerView.Adapter adapter;
     RecyclerView recyclerView;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
      View rx= inflater.inflate(R.layout.tmeating,container,false);
        listitems = new ArrayList <>();

        recyclerView= (RecyclerView) rx.findViewById(R.id.recyclerView1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference Databaseref = FirebaseDatabase.getInstance()
                .getReference().child("requests");

         Databaseref.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Iterable<DataSnapshot> mChildrenn = dataSnapshot.getChildren();
                 for(DataSnapshot mChildd:mChildrenn)
                 {
                        profphoneno =mChildd.child("profNum").getValue().toString().trim();


                     if(profphoneno.equals(phonenumber))
                        {
                            status=mChildd.child("accepted").getValue().toString().trim();
                            if(status.equals("yes") || status.equals("YES") || status.equals("yeS")
                            || status.equals("yES") || status.equals("Yes") || equals("YEs") ||
                                    status.equals("YeS") || status.equals("yEs")) {
                                Name=mChildd.child("Name").getValue().toString().trim();
                                purpose=mChildd.child("purpose").getValue().toString().trim();
                                studentphno=mChildd.child("StudNum").getValue().toString().trim();

                                ListItem lis =new ListItem(Name,purpose,studentphno); //single list
                                listitems.add(lis); //listitems is array of list

                            }
                        }

                 }

                 adapter = new MyAdapter(listitems,getContext());
                 recyclerView.setAdapter(adapter);


             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });


        return rx;
    }

}
