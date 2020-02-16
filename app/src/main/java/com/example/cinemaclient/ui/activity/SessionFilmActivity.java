package com.example.cinemaclient.ui.activity;

import android.content.Intent;
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
    private static final String HALL = "Hall";
    private static final String FREEPLACES = "FreePlaces";
    private static final String NUMBER = "Number";

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_film_list);
        recyclerView = findViewById(R.id.rv_session_film);
        presenter = new SessionFilmPresenter(this);
        initSessionList(presenter.getSessionList(getIntent().getStringExtra(SESSIONS)));
    }

    private void initSessionList(ArrayList<Session> sessionList) {
        SessionFilmAdapter adapter = new SessionFilmAdapter(sessionList, presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public void showSessionPlacesActivity(String hall, String freePlacesList, int number) {
        Intent intent = new Intent(this, SessionPlacesActivity.class);
        intent.putExtra(HALL, hall);
        intent.putExtra(FREEPLACES, freePlacesList);
        intent.putExtra(NUMBER, number);
        startActivity(intent);
    }
}
