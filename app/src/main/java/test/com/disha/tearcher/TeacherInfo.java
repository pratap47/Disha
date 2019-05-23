package test.com.disha.tearcher;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import test.com.disha.R;
import static test.com.disha.tearcher.otpsignin.phonenumber;


public class TeacherInfo extends AppCompatActivity {

    EditText  edtName;

    EditText edtCollegeName;

    EditText edtBranch;

    Button btnSubmit;

    private StorageReference mstoreref;

    Uri uriprofileimage;

    private ImageView imageView;

    private int requestcode=12;


    DatabaseReference mDatabase;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_info);

        imageView=(ImageView)findViewById(R.id.image);



          edtName = (EditText) findViewById(R.id.edtNamet);

          edtCollegeName=(EditText) findViewById(R.id.edtCollegeNamet);

          edtBranch =(EditText) findViewById(R.id.edtBrancht);

          btnSubmit = (Button) findViewById(R.id.btnSubmitt);

          mDatabase = FirebaseDatabase.getInstance().getReference().child("Teachers");



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Showimagechooser();
            }
        }
        );



        mstoreref= FirebaseStorage.getInstance().getReference("uploads").child(phonenumber);

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
                {
                    mDatabase= mDatabase.child(phonenumber);

                    mDatabase.child("name").setValue(mName);

                    mDatabase.child("research").setValue(mBranch);

                    mDatabase.child("college").setValue(mCollegeName);

                    uploadImageToFirebaseStorage();


                    Intent intent = new Intent(TeacherInfo.this, teacherdashboard.class);

                    startActivity(intent);
                }

            }
        }
        );


    }

    private void Showimagechooser() {

        Intent intent=new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(intent,requestcode);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        //Toast.makeText(this, "OnactivityResult", Toast.LENGTH_SHORT).show();

        if(requestCode==12 && resultCode==RESULT_OK &&
                data!=null && data.getData()!=null)
        {

            uriprofileimage=data.getData();

            imageView.setImageURI(uriprofileimage);

            try
            {

                //      Toast.makeText(this, "try", Toast.LENGTH_SHORT).show();

                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriprofileimage);

                imageView.setImageBitmap(bitmap);

                //uploadImageToFirebaseStorage();
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    private String getFileExtention(Uri uri)
    {

        ContentResolver CR=getContentResolver();

        MimeTypeMap mine=MimeTypeMap.getSingleton();

        return  mine.getExtensionFromMimeType(CR.getType(uri));
    }

    private void uploadImageToFirebaseStorage() {

        if(uriprofileimage!=null)

        {

            //        Toast.makeText(this, "UploadImageToFirebase", Toast.LENGTH_SHORT).show();


            mstoreref=mstoreref.child(System.currentTimeMillis()+"."+getFileExtention(uriprofileimage));

            mstoreref.putFile(uriprofileimage).addOnSuccessListener(new OnSuccessListener <UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Toast.makeText(TeacherInfo.this, "Uploaded successful", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(TeacherInfo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener <UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                }
            });
        }
        else{
            Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT).show();
        }

    }



}
