package com.example.cinemaclient.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.models.UserSession;
import com.example.cinemaclient.ui.adapter.UserSessionAdapter;
import com.example.cinemaclient.ui.presenter.UserSessionPresenter;

import java.util.ArrayList;

public class UserSessionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserSessionPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_session);
        recyclerView = findViewById(R.id.rv_user_session);
        presenter = new UserSessionPresenter(this);
        try {
            initSessionsList();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void initSessionsList() throws InterruptedException {
        ArrayList<UserSession> userSessions = presenter.getUserSessionsList();
        UserSessionAdapter adapter = new UserSessionAdapter(userSessions,presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
