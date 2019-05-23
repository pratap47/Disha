






package test.com.disha;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignIn extends AppCompatActivity {

    private EditText edtPhone;
    private Button btnSubmitOtp , btnSendOtp;
    private EditText edtOtp;
    FirebaseAuth auth;
    private String verificationCode;
    private String mOtp;
    public static String  mPhone;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtPhone = (EditText)findViewById(R.id.edtPhone);
        btnSubmitOtp = (Button)findViewById(R.id.btnSubmitOtp);
        btnSendOtp = (Button)findViewById(R.id.btnSendOtp);
        edtOtp= (EditText)findViewById(R.id.edtOtp);

        btnSendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPhone = edtPhone.getText().toString();
                if(TextUtils.isEmpty(mPhone.trim()))
                {
                    edtPhone.setError("Phone No can't be empty");
                }
                else if(mPhone.length() < 10)
                {
                    edtPhone.setError("Phone No can't be "+mPhone.length()+" digits");
                }
                else
                {
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            mPhone,                     // Phone number to verify
                            60,                           // Timeout duration
                            TimeUnit.SECONDS,                // Unit of timeout
                            SignIn.this,        // Activity (for callback binding)
                            mCallback);                      // OnVerificationStateChangedCallbacks
                }
            }
        });

        StartFirebaseLogin();

        btnSubmitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOtp=edtOtp.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, mOtp);
                SigninWithPhone(credential);
            }
        });


    }
    private void StartFirebaseLogin(){

        auth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(SignIn.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignIn.this,"verification fialed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationCode = s;
                Toast.makeText(SignIn.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(SignIn.this,Student_details.class));
                            finish();
                        } else {
                            Toast.makeText(SignIn.this,"Incorrect OTP",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
