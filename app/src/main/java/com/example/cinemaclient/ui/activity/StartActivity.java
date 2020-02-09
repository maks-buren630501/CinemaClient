package com.example.cinemaclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.presenter.StartPresenter;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    private Button showListOfSessions;
    private Button addNewSession;
    private StartPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        showListOfSessions = findViewById(R.id.bt_show_sessions);
        addNewSession = findViewById(R.id.bt_add_new_session);
        presenter = new StartPresenter(this);
        addNewSession.setOnClickListener(this);
        showListOfSessions.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_new_session:{
                presenter.clickButtonAddNewSession();
                break;
            }
            case R.id.bt_show_sessions:{
                presenter.clickButtonShowListOfSessions();
                break;
            }
        }
    }

    public void showActivityListOfFilms(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void showActivityListOfUserSessions(){
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
