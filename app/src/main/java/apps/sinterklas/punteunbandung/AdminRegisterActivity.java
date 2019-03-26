package apps.sinterklas.punteunbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import apps.sinterklas.punteunbandung.Activities.AdminLoginActivity;

public class AdminRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText Users, E_mail, PassW, CnfrmPass;
    private FirebaseAuth mAuth;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_register);

        Users = (EditText) findViewById(R.id.editText2);
        E_mail = (EditText) findViewById(R.id.editText6);
        PassW = (EditText) findViewById(R.id.editText4);
        CnfrmPass = (EditText)findViewById(R.id.editText5);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button).setOnClickListener(this);
        }
    private void registerUser() {
        String username = Users.getText().toString().trim();
        String email = E_mail.getText().toString().trim();
        String password = PassW.getText().toString().trim();
        String cnfrmpass = CnfrmPass.getText().toString().trim();

        if (username.isEmpty()) {
            Users.setError("Username is required");
            Users.requestFocus();
            return;
        }

        if (username.length() < 10) {
            Users.setError("Minimum lenght of username should be 10");
            Users.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            E_mail.setError("Email is required");
            E_mail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            E_mail.setError("Please enter a valid email");
            E_mail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            PassW.setError("Password is required");
            PassW.requestFocus();
            return;
        }

        if (password.length() < 6) {
            PassW.setError("Minimum lenght of password should be 6");
            PassW.requestFocus();
            return;
        }
        if (cnfrmpass.isEmpty()) {
            CnfrmPass.setError("Same password is required");
            CnfrmPass.requestFocus();
            return;
        }

        if (cnfrmpass.length() < 6) {
            CnfrmPass.setError("Minimum lenght of password should be 6");
            CnfrmPass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(AdminRegisterActivity.this, AdminLoginActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                finish();
                startActivity(new Intent(this, MenuActivity.class));
                break;
        }
    }
}


