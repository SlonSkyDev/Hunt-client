package trueteam.org.hunt;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        Button btnAuthorize = (Button) findViewById(R.id.authorize);
        btnAuthorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthorizationTask at = new AuthorizationTask();
                at.execute();
            }
        });
    }


    class AuthorizationTask extends AsyncTask<Void, Void, Void> {
        private String login;
        private String password;
        private Role role;
        private boolean success;

        protected void onPreExecute() {
            super.onPreExecute();
            login = ((EditText)findViewById(R.id.loginAuthorisation)).getText().toString();
            password = ((EditText)findViewById(R.id.passwordAuthorization)).getText().toString();

            RadioGroup rgRole = (RadioGroup) findViewById(R.id.role);
            switch (rgRole.getCheckedRadioButtonId()) {
                case R.id.hunter:
                    role = Role.HUNTER;
                    break;
                case R.id.runner:
                    role = Role.RUNNER;
                    break;
            }
        }

        @Override
        protected Void doInBackground(Void... params) {
            success = Core.getInstance().requestAuthorization(login, password, role);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(success) {
                Intent intent = new Intent(AuthorizationActivity.this.getBaseContext(), MapsActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(AuthorizationActivity.this.getBaseContext(), "Ошибка авторизации", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
