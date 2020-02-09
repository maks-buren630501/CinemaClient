package com.example.cinemaclient.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.ui.adapter.SessionFilmAdapter;
import com.example.cinemaclient.ui.presenter.SessionFilmPresenter;

import java.util.ArrayList;

public class SessionFilmActivity extends AppCompatActivity {
    private SessionFilmPresenter presenter;
    private static final String SESSIONS = "Sessions";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_session);
        presenter = new SessionFilmPresenter(this);
        initSessionList(presenter.getSessionList(getIntent().getStringExtra(SESSIONS)));
    }

    private void initSessionList(ArrayList<Session> sessionList) {
        SessionFilmAdapter adapter = new SessionFilmAdapter(sessionList, presenter);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
