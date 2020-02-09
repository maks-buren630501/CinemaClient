package com.example.cinemaclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.ui.presenter.FilmsListPresenter;

import java.io.IOException;
import java.util.ArrayList;

public class FilmsAdapter extends RecyclerView.Adapter<FilmsAdapter.ViewHolder> {
    private final ArrayList<String> listOfFilms;
    private final FilmsListPresenter presenter;

    public FilmsAdapter(ArrayList<String> listOfFilms, FilmsListPresenter presenter) {
        this.listOfFilms = listOfFilms;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public FilmsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_films, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull FilmsAdapter.ViewHolder holder, int position) {
        String filmName = listOfFilms.get(position);
        holder.filmName.setText(filmName);
    }

    @Override
    public int getItemCount() {
        return listOfFilms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView filmName;

        public ViewHolder(@NonNull View view) {
            super(view);
            filmName = view.findViewById(R.id.tv_film);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                presenter.selectFilm(listOfFilms.get(getLayoutPosition()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
