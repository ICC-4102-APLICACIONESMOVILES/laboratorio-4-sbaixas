package none.sbaixas.lab3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.email_textbox);
        passwordEditText = findViewById(R.id.password_textbox);
    }

    protected void onLoginClick(View view){
        Intent result = new Intent();
        result.putExtra("email", emailEditText.getText().toString());
        result.putExtra("password", passwordEditText.getText().toString());
        setResult(Activity.RESULT_OK, result);
        this.finish();
    }
}
