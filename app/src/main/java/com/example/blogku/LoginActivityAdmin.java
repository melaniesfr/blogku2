package com.example.blogku;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivityAdmin extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="LoginActivityAdmin";

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private EditText loginEmail;
    private EditText isiPass;
    Button btnLogin;

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        mDatabase=FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();
        loginEmail=findViewById(R.id.login_email);
        btnBack=findViewById(R.id.btn_back);
        isiPass=findViewById(R.id.login_pass);
        btnLogin=findViewById(R.id.btn_login);

        btnBack.setOnClickListener(view -> finish());
        btnLogin.setOnClickListener(this);
    }

    private void signIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        String email = loginEmail.getText().toString();
        String password = isiPass.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, //hideProgressDialog();
                        this::onComplete);
    }
    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(Objects.requireNonNull(user.getEmail()));

        // Membuat User admin baru
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        startActivity(new Intent(LoginActivityAdmin.this, AdminActivity.class));
        finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(loginEmail.getText().toString())) {
            loginEmail.setError("Harus diisi");
            result = false;
        } else {
            loginEmail.setError(null);
        }

        if (TextUtils.isEmpty(isiPass.getText().toString())) {
            isiPass.setError("Harus diisi");
            result = false;
        } else {
            isiPass.setError(null);
        }

        return result;
    }

    private void writeNewUser(String userId, String name, String email) {
        Admin user = new Admin(name, email);

        mDatabase.child("admin").child(userId).setValue(user);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_login) {
            signIn();
        }
    }

    private void onComplete(Task<AuthResult> task) {
        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
        // hideProgressDialog();

        if (task.isSuccessful()) {
            onAuthSuccess(Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()));
        } else {
            Toast.makeText(LoginActivityAdmin.this, "Sign Up Failed",
                    Toast.LENGTH_SHORT).show();
        }
    }
}