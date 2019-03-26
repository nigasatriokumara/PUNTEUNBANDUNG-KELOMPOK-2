package apps.sinterklas.punteunbandung.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import apps.sinterklas.punteunbandung.AdminRegisterActivity;
import apps.sinterklas.punteunbandung.MenuActivity;
import apps.sinterklas.punteunbandung.R;

public class AdminLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonregister;
    EditText User, Email, Pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        User = (EditText) findViewById(R.id.Username);
        Email = (EditText) findViewById(R.id.editText);
        Pass = (EditText) findViewById(R.id.Password);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);

        buttonregister = (Button)findViewById(R.id.button4);
        buttonregister.setOnClickListener(new View.OnClickListener()    {

            public void onClick(View argo) {
                Intent i = new Intent (getApplicationContext(), AdminRegisterActivity.class);
                startActivity(i);
            }
        });
    }
    private void loginAdmin() {
        String username = User.getText().toString().trim();
        String email = Email.getText().toString().trim();
        String password = Pass.getText().toString().trim();

        if (username.isEmpty()) {
            User.setError("Username is Required");
            User.requestFocus();
            return;
        }

        if (username.length() < 10)  {
            User.setError("Minimum length of username should be 10");
            User.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            Pass.setError("Password is required");
            Pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            Pass.setError("Minimum lenght of password should be 6");
            Pass.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(AdminLoginActivity.this, MenuActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already Login", Toast.LENGTH_SHORT).show();

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
            case R.id.button3:
                loginAdmin();
                break;

            case R.id.button4:
                finish();
                startActivity(new Intent(this, AdminRegisterActivity.class));
                break;
        }
    }
}
