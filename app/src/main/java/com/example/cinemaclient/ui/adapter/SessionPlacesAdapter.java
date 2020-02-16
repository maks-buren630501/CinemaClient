package com.example.cinemaclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.presenter.SessionPlacesPresenter;

import java.util.ArrayList;

public class SessionPlacesAdapter extends RecyclerView.Adapter<SessionPlacesAdapter.ViewHolder>{

    private final ArrayList<Integer> freePlacesList;
    private final SessionPlacesPresenter presenter;
    private final int hall;

    public SessionPlacesAdapter(SessionPlacesPresenter presenter, ArrayList<Integer> freePlacesList, int hall) {
        this.freePlacesList = freePlacesList;
        this.presenter = presenter;
        this.hall = hall;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_places, parent, false);
        return new SessionPlacesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
