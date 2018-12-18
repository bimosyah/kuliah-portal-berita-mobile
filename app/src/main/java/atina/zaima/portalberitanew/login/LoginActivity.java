package atina.zaima.portalberitanew.login;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import atina.zaima.portalberitanew.Helper.HelperLogin;
import atina.zaima.portalberitanew.MainActivity;
import atina.zaima.portalberitanew.R;

public class LoginActivity extends AppCompatActivity{
    EditText username,password;
    Button btn_login;
    TextView btn_register;
    Intent i;
    HelperLogin dbHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new HelperLogin(this);

        username = (EditText) findViewById(R.id.editTextUsername);
        password = (EditText) findViewById(R.id.editTextPassword);

        btn_register = (TextView) findViewById(R.id.btn_register);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Boolean login = dbHelper.checkLogin(user,pass);

                if (user.equals("") || pass.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_LONG).show();
                }else{
                    if (login == true){
                        i = new Intent(getApplicationContext(),MainActivity.class);
                        i.putExtra("username",user);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Username or password wrong!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

