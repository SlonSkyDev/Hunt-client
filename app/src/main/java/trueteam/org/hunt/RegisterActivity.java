package trueteam.org.hunt;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btnRegister = (Button) findViewById(R.id.register);
        TextView tvHasAnAccount = (TextView) findViewById(R.id.authLabel);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterTask rt = new RegisterTask();
                rt.execute();

            }
        });

        tvHasAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this.getBaseContext(), AuthorizationActivity.class);
                startActivity(intent);
            }
        });

    }


    class RegisterTask extends AsyncTask<Void, Void, Void> {
        private String login;
        private String password;
        private boolean success;

        protected void onPreExecute() {
            super.onPreExecute();
            login = ((EditText)findViewById(R.id.loginRegister)).getText().toString();
            password = ((EditText)findViewById(R.id.passwordRegister)).getText().toString();
        }

        @Override
        protected Void doInBackground(Void... params) {
            success = Core.getInstance().requestRegister(login, password);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(success) {
                Intent intent = new Intent(RegisterActivity.this.getBaseContext(), AuthorizationActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegisterActivity.this.getBaseContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
            }
        }

    }


}
