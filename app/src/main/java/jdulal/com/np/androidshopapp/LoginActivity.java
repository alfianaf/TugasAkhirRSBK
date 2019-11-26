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

public class LoginActivity extends AppCompatActivity {
    TextInputLayout userEmailWrapper, userPasswordWrapper;
    EditText userEmail, userPassword;
    Button btnLogin;
    FirebaseDatabase database;
    DatabaseReference table_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmailWrapper = findViewById(R.id.userEmailWrapper);
        userPasswordWrapper = findViewById(R.id.userPasswordWrapper);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        btnLogin = findViewById(R.id.btnUserLogin);

        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("Users");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if(email.isEmpty()){
                    userEmailWrapper.setError("Enter email");
                    userEmailWrapper.requestFocus();
                    return;
                }
                if(password.isEmpty()){
                    userPasswordWrapper.setError("Enter password");
                    userPasswordWrapper.requestFocus();
                    return;
                }

                if (Common.isConnectedToInternet(getBaseContext())) {

                    final ProgressDialog mDialog = new ProgressDialog(LoginActivity.this);
                    mDialog.setMessage("Please waiting...");
                    mDialog.show();

                    table_user.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            //Check if user not exist in database
                            if (dataSnapshot.child(userEmail.getText().toString()).exists()) {

                                //Get user information
                                mDialog.dismiss();
                                User user = dataSnapshot.child(userEmail.getText().toString()).getValue(User.class);
                                user.setEmail(userEmail.getText().toString()); //Set Phone
                                if (user.getPassword().equals(userPassword.getText().toString())) {
                                    {
                                        Intent shopIntent = new Intent(LoginActivity.this, ShopActivity.class);
                                        Common.currentUser = user;
                                        startActivity(shopIntent);
                                        finish();

                                        table_user.removeEventListener(this);
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Wrong password !!!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "User not exist in Database", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "please check your connection !!", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
