package com.example.cinemaclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionPlacesAdapter.ViewHolder viewHolder, int i) {
        String placeString = Integer.toString(this.freePlacesList.get(i));
        String hall = Integer.toString(this.hall);
        viewHolder.placeString.setText(placeString);
        viewHolder.hallString.setText(hall);
    }

    @Override
    public int getItemCount() {
        return freePlacesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView placeString;
        TextView hallString;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeString = itemView.findViewById(R.id.tv_place);
            hallString = itemView.findViewById(R.id.tv_hall);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                presenter.selectPlace(freePlacesList.get(getLayoutPosition()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
