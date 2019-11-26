package jdulal.com.np.androidshopapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText ufirstname, ulastname, uemail, upassword, uconfpassword, ucontactno;
    Button btnRegister;
    TextInputLayout userFirstNameWrapper, userLastNameWrapper, userEmailWrapper, userPasswordWrapper,
            userConfPasswordWrapper, userContactNoWrapper;
    private FirebaseDatabase database;
    DatabaseReference table_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("Users");

        ufirstname = findViewById(R.id.userFirstName);
        ulastname = findViewById(R.id.userLastName);
        uemail = findViewById(R.id.userEmail);
        upassword = findViewById(R.id.userPassword);
        uconfpassword = findViewById(R.id.userConfPassword);
        ucontactno = findViewById(R.id.userContactNumber);
        btnRegister = findViewById(R.id.btnRegister);


        userFirstNameWrapper = findViewById(R.id.userFirstNameWrapper);
        userLastNameWrapper = findViewById(R.id.lastNameWrapper);
        userEmailWrapper = findViewById(R.id.userEmailWrapper);
        userPasswordWrapper = findViewById(R.id.userPasswordWrapper);
        userConfPasswordWrapper = findViewById(R.id.confPasswordWrapper);
        userContactNoWrapper = findViewById(R.id.contactNoWrapper);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String firstname = ufirstname.getText().toString().trim();
                final String lastname = ulastname.getText().toString().trim();
                final String email = uemail.getText().toString().trim();
                final String password = upassword.getText().toString().trim();
                final String confpassword = uconfpassword.getText().toString().trim();
                final String contactno = ucontactno.getText().toString().trim();
                if (firstname.isEmpty()) {
                    userFirstNameWrapper.setError("Enter First Name");
                    userFirstNameWrapper.requestFocus();
                    return;
                }
                if (lastname.isEmpty()) {
                    userLastNameWrapper.setError("Enter Last Name");
                    userLastNameWrapper.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    userEmailWrapper.setError("Enter Username");
                    userEmailWrapper.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    userPasswordWrapper.setError("Enter Password");
                    userPasswordWrapper.requestFocus();
                    return;
                }
                if (confpassword.isEmpty()) {
                    userConfPasswordWrapper.setError("Enter Confirmation Password");
                    userConfPasswordWrapper.requestFocus();
                    return;
                }
                if (!confpassword.equals(password)) {
                    userConfPasswordWrapper.setError("Password didn't match");
                    userConfPasswordWrapper.requestFocus();
                    return;
                }
                if (contactno.isEmpty()) {
                    userContactNoWrapper.setError("Enter Contact No");
                    userContactNoWrapper.requestFocus();
                    return;
                }
                if (Common.isConnectedToInternet(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("Please waiting...");
                    mDialog.show();

                    table_user.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Check if user phone already registered
                            if (dataSnapshot.child(uemail.getText().toString()).exists()) {
                                mDialog.dismiss();
                                userEmailWrapper.setError("Username is already exists");
                                userEmailWrapper.requestFocus();
                            } else {
                                mDialog.dismiss();
                                User user = new User(ufirstname.getText().toString(), ulastname.getText().toString(), ucontactno.getText().toString(), upassword.getText().toString());
                                table_user.child(uemail.getText().toString()).setValue(user);
                                Toast.makeText(RegisterActivity.this, "Sign up successfully !", Toast.LENGTH_SHORT).show();
                                finish();
                                table_user.removeEventListener(this);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "please check your connection !!", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}