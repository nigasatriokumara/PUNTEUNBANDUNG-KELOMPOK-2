package apps.sinterklas.punteunbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText Username, E_mail, Password, ConfirmPass;
    Button Register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        getSupportActionBar().setTitle("Register Activity");

        Register_btn = (Button)findViewById(R.id.btn_regist);
        Username = (EditText) findViewById(R.id.username_register);
        E_mail = (EditText) findViewById(R.id.email_register);
        Password = (EditText) findViewById(R.id.password_register);
        ConfirmPass = (EditText) findViewById(R.id.confirm_password);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(RegisterActivity.this);

        Register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = Username.getText().toString().trim();
                String email = E_mail.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirmPass = ConfirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(RegisterActivity.this, "Enter Your Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(RegisterActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(RegisterActivity.this, "Enter Your Password Again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.length() < 8) {
                    Toast.makeText(RegisterActivity.this, "Username is Too Short", Toast.LENGTH_SHORT).show();
                }
                if (password.length() < 6) {
                    Toast.makeText(RegisterActivity.this, "Password is Too Short", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(confirmPass)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }
}
