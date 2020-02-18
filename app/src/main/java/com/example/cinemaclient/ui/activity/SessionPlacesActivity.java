package com.example.cinemaclient.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.cinemaclient.R;
import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.ui.adapter.SessionFilmAdapter;
import com.example.cinemaclient.ui.adapter.SessionPlacesAdapter;
import com.example.cinemaclient.ui.presenter.SessionFilmPresenter;
import com.example.cinemaclient.ui.presenter.SessionPlacesPresenter;

import java.util.ArrayList;


public class SessionPlacesActivity extends AppCompatActivity {

    private static final String HALL = "Hall";
    private static final String FREEPLACES = "FreePlaces";
    private static final String NUMBER = "Number";
    private SessionPlacesPresenter presenter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_places);
        recyclerView = findViewById(R.id.rv_session_places);
        presenter = new SessionPlacesPresenter(this);
        initPlacesList(getIntent().getStringExtra(FREEPLACES),getIntent().getStringExtra(HALL));

    }

    private void initPlacesList(String freePlacesList, String hall) {
        ArrayList<String> temp = Session.getListStringOfParameters(freePlacesList);
        ArrayList<Integer> freeList = new ArrayList<>();
        for(String s:temp){
            freeList.add(Integer.valueOf(s));
        }
        ArrayList<String> temp2 = Session.getListStringOfParameters(hall);

        SessionPlacesAdapter adapter = new SessionPlacesAdapter(presenter,freeList,Integer.valueOf(temp2.get(0)));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public String getSessionNumber(){
        return getIntent().getStringExtra(NUMBER);
    }

    public void showStartActivity() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    public void showError() {
        Toast.makeText(this, "error DB", Toast.LENGTH_SHORT).show();
    }
}
