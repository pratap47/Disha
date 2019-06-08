package test.com.disha.tearcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import test.com.disha.R;

public class tprofile extends Fragment {
    DatabaseReference Databaseref3;
    String college;
    String name;
    String research;
    TextView namepro1;
    TextView college1;
    TextView research1;
    ImageView imageView;
    Button update;
    public static SharedPreferences sharedPreferences2;
    public static final String Default2="";
    public static String phone3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.tprofile, container, false);
        namepro1 = (TextView) v.findViewById(R.id.namepro);
        college1 = (TextView) v.findViewById(R.id.collegepro);
        research1 = (TextView) v.findViewById(R.id.researchpro);
        imageView = v.findViewById(R.id.impro);
        update = v.findViewById(R.id.upd);

        sharedPreferences2 = getActivity().getSharedPreferences("num", Context.MODE_PRIVATE);
        phone3 = sharedPreferences2.getString("Phone", Default2);
        name=sharedPreferences2.getString("name",Default2);
        research=sharedPreferences2.getString("research",Default2);
        college=sharedPreferences2.getString("college",Default2);
        namepro1.setText(name);
        college1.setText(college);
        research1.setText(research);
        imageView.setImageURI(TeacherInfo.uriprofileimage);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TeacherInfo.class);
                startActivity(intent);
            }
        });


        //        Databaseref3 = FirebaseDatabase.getInstance().
//                getReference().child("Teachers").child(phone3);
//
//
//        Databaseref3.orderByValue().addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                Iterable<DataSnapshot> mChildrennn = dataSnapshot.getChildren();
//                college = dataSnapshot.child("college").getValue().toString();
//                name = dataSnapshot.child("name").getValue().toString();
//                research = dataSnapshot.child("research").getValue().toString();
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//
//        namepro1.setText(name);
//        college1.setText(college);
//        research1.setText(research);
//        imageView.setImageURI(TeacherInfo.uriprofileimage);
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TeacherInfo.class);
//                startActivity(intent);
//            }
//        });


        return v;
    }
}

