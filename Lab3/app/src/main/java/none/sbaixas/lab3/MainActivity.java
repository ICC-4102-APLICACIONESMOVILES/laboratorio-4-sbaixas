package none.sbaixas.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    FormFragment fragment;
    FormViewFragment viewFragment;
    private SharedPreferences settings;
    private TextView emailTextView;
    private TextView passwordTextView;
    public static final String LOGIN_PREFERENCES = "LoginPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(LOGIN_PREFERENCES, MODE_PRIVATE);
        emailTextView = (TextView) findViewById(R.id.username);
        passwordTextView = (TextView) findViewById(R.id.password);
        String email = settings.getString("userEmail", null);
        String password = settings.getString("userPwd", null);
        if(email == null && password == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivityForResult(intent, 48);
        }
        else{
            emailTextView.setText(email);
            passwordTextView.setText(password);
        }
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        NavigationView.OnNavigationItemSelectedListener listener = new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_new_form){
                    fragment = new FormFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();
                }
                if(item.getItemId()==R.id.nav_view_forms){
                    viewFragment = new FormViewFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, viewFragment).commit();
                }
                drawerLayout.closeDrawers();
                return true;
            }
        };
        navView.setNavigationItemSelectedListener(listener);
    }

    public void OnLogoutClick(View view){
        SharedPreferences.Editor prefEditor = settings.edit();
        prefEditor.remove("userEmail");
        prefEditor.remove("userPwd");
        prefEditor.commit();
        emailTextView.setText("");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivityForResult(intent, 48);
    }

    public void onDoneClick(View view){
        fragment.onDoneClick(view);
    }
}

