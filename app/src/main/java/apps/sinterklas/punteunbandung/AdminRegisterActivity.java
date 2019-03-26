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

public class AdminRegisterActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText Username, E_mail, Password, ConfirmPass;
    Button Mangga_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        getSupportActionBar().setTitle("Admin Register Activity");

        Mangga_regist = (Button)findViewById(R.id.button);
        Username = (EditText) findViewById(R.id.editText2);
        E_mail = (EditText) findViewById(R.id.editText6);
        Password = (EditText) findViewById(R.id.editText4);
        ConfirmPass = (EditText) findViewById(R.id.editText5);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseApp.initializeApp(AdminRegisterActivity.this);

        Mangga_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = Username.getText().toString().trim();
                String email = E_mail.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String confirmPass = ConfirmPass.getText().toString().trim();

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(AdminRegisterActivity.this, "Enter Your Username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(AdminRegisterActivity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(AdminRegisterActivity.this, "Enter Your Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmPass)) {
                    Toast.makeText(AdminRegisterActivity.this, "Enter Your Password Again", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (username.length() < 8) {
                    Toast.makeText(AdminRegisterActivity.this, "Username is Too Short", Toast.LENGTH_SHORT).show();
                }
                if (password.length() < 6) {
                    Toast.makeText(AdminRegisterActivity.this, "Password is Too Short", Toast.LENGTH_SHORT).show();
                }
                if (password.equals(confirmPass)) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AdminRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), AdminLoginActivity.class));
                                        Toast.makeText(AdminRegisterActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(AdminRegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }
            }
        });
    }
}
