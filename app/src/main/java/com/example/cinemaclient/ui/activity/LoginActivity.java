package com.example.cinemaclient.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.presenter.LoginPresenter;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button loginButton;
    private EditText loginEdit;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        loginEdit = findViewById(R.id.lineToLogin);
        presenter = new LoginPresenter(this);
        try {
            presenter.createUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        try {
            presenter.clickPushButton(this.loginEdit.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showError (){
        Toast.makeText(this, "Input login", Toast.LENGTH_SHORT).show();
    }

    public void showRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void showStartActivity(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
