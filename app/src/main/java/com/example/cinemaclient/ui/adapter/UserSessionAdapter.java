package com.example.cinemaclient.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinemaclient.R;
import com.example.cinemaclient.models.UserSession;
import com.example.cinemaclient.ui.presenter.UserSessionPresenter;

import java.util.ArrayList;

public class UserSessionAdapter extends RecyclerView.Adapter<UserSessionAdapter.ViewHolder>{

    private final ArrayList<UserSession> userSessions;
    private final UserSessionPresenter presenter;

    public UserSessionAdapter(ArrayList<UserSession> userSessions, UserSessionPresenter presenter) {
        this.userSessions = userSessions;
        this.presenter = presenter;
        userSessions.add(0,new UserSession("film","date","time",1000,1000));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_user_session, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserSessionAdapter.ViewHolder viewHolder, int position) {


        viewHolder.film.setText(userSessions.get(position).getFilmTittle());
        viewHolder.time.setText(userSessions.get(position).getTime());
        viewHolder.date.setText(userSessions.get(position).getDate());
        if(position == 0){
            viewHolder.hall.setText("hall");
            viewHolder.place.setText("place");
        }
        else {
            viewHolder.hall.setText(Integer.toString(userSessions.get(position).getHall()));
            viewHolder.place.setText(Integer.toString(userSessions.get(position).getPlace()));
        }

    }

    @Override
    public int getItemCount() {
        return userSessions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView film;
        TextView time;
        TextView date;
        TextView place;
        TextView hall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            film = itemView.findViewById(R.id.tv_film_session);
            time = itemView.findViewById(R.id.tv_session_time);
            date = itemView.findViewById(R.id.tv_session_date);
            place = itemView.findViewById(R.id.tv_session_place);
            hall = itemView.findViewById(R.id.tv_session_hall);
        }
    }
}
