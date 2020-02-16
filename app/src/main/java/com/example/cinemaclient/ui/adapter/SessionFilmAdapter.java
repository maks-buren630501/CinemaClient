package com.example.cinemaclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.models.Session;
import com.example.cinemaclient.ui.presenter.SessionFilmPresenter;

import java.io.IOException;
import java.util.ArrayList;

public class SessionFilmAdapter extends RecyclerView.Adapter<SessionFilmAdapter.ViewHolder> {
    private final ArrayList<Session> listOfSessions;
    private final SessionFilmPresenter presenter;

    public SessionFilmAdapter(ArrayList<Session> listOfSessions, SessionFilmPresenter presenter) {
        this.listOfSessions = listOfSessions;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public SessionFilmAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_session_film, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull SessionFilmAdapter.ViewHolder holder, int position) {
        String filmTittle = listOfSessions.get(position).getFilmTittle();
        String filmDate = listOfSessions.get(position).getDate();
        String filmTime = listOfSessions.get(position).getTime();
        holder.filmTittle.setText(filmTittle);
        holder.filmDate.setText(filmDate);
        holder.filmTime.setText(filmTime);
    }

    @Override
    public int getItemCount() {
        return listOfSessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView filmTittle;
        TextView filmDate;
        TextView filmTime;

        public ViewHolder(@NonNull View view) {
            super(view);
            filmTittle = view.findViewById(R.id.tv_film_title);
            filmDate = view.findViewById(R.id.tv_film_date);
            filmTime = view.findViewById(R.id.tv_film_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            try {
                presenter.selectSession(listOfSessions.get(getLayoutPosition()).getNumber());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
