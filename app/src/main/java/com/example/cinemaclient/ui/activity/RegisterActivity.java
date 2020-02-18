package com.example.cinemaclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.presenter.RegisterPresenter;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button registerButton;
    private EditText firstName;
    private EditText lastName;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerButton = findViewById(R.id.bt_register);
        firstName = findViewById(R.id.et_first_name);
        lastName = findViewById(R.id.et_last_name);
        presenter = new RegisterPresenter(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            presenter.pushRegisterButton(this.firstName.getText().toString(),this.lastName.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showErrorEmpty(){
        Toast.makeText(this, "Input first and last name", Toast.LENGTH_SHORT).show();
    }

    public void showError(){
        Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
    }

    public void showStartActivity(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }






}
