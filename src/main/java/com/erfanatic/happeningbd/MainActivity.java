package com.erfanatic.happeningbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //class Variables
    private RecyclerView eventListView;

    DatabaseReference eRef = FirebaseDatabase.getInstance().getReference().child("events");

    //ViewHolder Class for The RecyclerView

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        View list_itemView;
        public EventViewHolder(View itemView) {
            super(itemView);
            list_itemView = itemView;
        }

        void setName(String name){

            TextView tvName = (TextView) itemView.findViewById(R.id.tv_eventName);
            tvName.setText(name);
        }
        void setLocation(String location){

            TextView tvLocation = (TextView) itemView.findViewById(R.id.tv_eventLocation);
            tvLocation.setText(location);
        }
        void setDate(String date){

            TextView tvDate = (TextView) itemView.findViewById(R.id.tv_eventDate);
            tvDate.setText(date);
        }
        void setDesc(String desc){

            TextView tvDesc = (TextView) itemView.findViewById(R.id.tv_eventDesc);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing recyclerView
        eventListView = (RecyclerView) findViewById(R.id.list_rv);
        eventListView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
         FirebaseRecyclerAdapter<Events,EventViewHolder>  adapter = new FirebaseRecyclerAdapter<Events, EventViewHolder>(
                Events.class,
                R.layout.list_item,
                EventViewHolder.class,
                 eRef

        ) {
            @Override
            protected void populateViewHolder(EventViewHolder viewHolder, Events model, int position) {
                viewHolder.setName(model.getName());
                viewHolder.setLocation(model.getLocation());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setDate(model.getDate());

            }
        };
        eventListView.setAdapter(adapter);
    }
}
