package apps.sinterklas.punteunbandung;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminLoginActivity extends AppCompatActivity {
    private Button buttonregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        buttonregister = (Button)findViewById(R.id.button4);
        buttonregister.setOnClickListener(new View.OnClickListener()    {

            public void onClick(View argo) {
                Intent i = new Intent (getApplicationContext(), AdminRegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
