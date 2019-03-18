package apps.sinterklas.punteunbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button userregister;
    private Button loginasadmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginasadmin = (Button)findViewById(R.id.button2);
        loginasadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getApplicationContext(), AdminLoginActivity.class);
                startActivity(a);
            }
        });

        userregister = (Button)findViewById(R.id.button4);
        userregister.setOnClickListener(new View.OnClickListener()    {

            public void onClick(View argo) {
                Intent i = new Intent (getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
