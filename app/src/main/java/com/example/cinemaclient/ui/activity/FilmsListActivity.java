package com.example.cinemaclient.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.adapter.FilmsAdapter;
import com.example.cinemaclient.ui.presenter.FilmsListPresenter;

public class FilmsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FilmsListPresenter presenter;
    private static final String SESSIONS = "Sessions";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_films_list);
        recyclerView = findViewById(R.id.rv_films);
        presenter = new FilmsListPresenter(this);
        initFilmsList();
    }

    private void initFilmsList() {
        FilmsAdapter adapter = new FilmsAdapter(presenter.getListOfFilms(), presenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void showSessionsActivity(String sessions){
        Intent intent = new Intent(this, SessionFilmActivity.class);
        intent.putExtra(SESSIONS,sessions);
        startActivity(intent);
    }
}
