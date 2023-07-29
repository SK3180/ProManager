package com.sksingh.promanager;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sksingh.promanager.model.TaskModel;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private final ArrayList<TaskModel> Taskdataset;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskname,taskstatus;
        private ImageView tick;
        TextView Taskstatus;
        TextView Description;
        TextView Deadline;

        ConstraintLayout constraintLayout;
        LinearLayout deadlinegone;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            taskname = (TextView) view.findViewById(R.id.TaskName);
            taskstatus = (TextView) view.findViewById(R.id.Taskstatus);
            tick = (ImageView) view.findViewById(R.id.tick);
            constraintLayout = (ConstraintLayout) view.findViewById(R.id.container);
            Description = (TextView) view.findViewById(R.id.Description);
            Deadline = (TextView) view.findViewById(R.id.Deadline);
            deadlinegone = (LinearLayout) view.findViewById(R.id.deadlinegone);



        }

        public TextView getTextView() {
            return taskname;
        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public TaskAdapter(ArrayList<TaskModel> dataSet) {
        this.Taskdataset = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


            viewHolder.taskname.setText(Taskdataset.get(position).getTaskname());
            viewHolder.taskstatus.setText(Taskdataset.get(position).getTaskstatus());
            viewHolder.Description.setText(Taskdataset.get(position).getTaskdisp());
            viewHolder.Deadline.setText(Taskdataset.get(position).getTaskdeadline());


            String status = Taskdataset.get(position).getTaskstatus();

            if (status.toLowerCase().equals("pending"))
            {
                viewHolder.tick.setImageResource(R.drawable.time);
                viewHolder.taskstatus.setTextColor(Color.parseColor("#ffff66"));
            } else if (status.toLowerCase().equals("completed")) {
                viewHolder.taskstatus.setTextColor(Color.parseColor("#7cfc00"));
                viewHolder.tick.setImageResource(R.drawable.baseline_check_24);
                viewHolder.deadlinegone.setVisibility(View.GONE);
            }
            else {
                viewHolder.taskstatus.setTextColor(Color.parseColor("#ffffff"));
            }
                viewHolder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        PopupMenu popupMenu = new PopupMenu(view.getContext(), viewHolder.constraintLayout );
                        popupMenu.inflate(R.menu.taskmenu);
                        popupMenu.show();

                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {

                                if (menuItem.getItemId()==R.id.deletemenu){

                                    FirebaseFirestore.getInstance().collection("tasks").document(Taskdataset.get(position).getTaskid()).delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(view.getContext(), "Task Deleted!!", Toast.LENGTH_SHORT).show();
                                                    viewHolder.constraintLayout.setVisibility(view.GONE);
                                                    viewHolder.deadlinegone.setVisibility(View.GONE);


                                                }
                                            });
                                }
                                if (menuItem.getItemId()==R.id.Markcompletemenu) {

                                    TaskModel Completed = Taskdataset.get(position);
                                    Completed.setTaskstatus("COMPLETED");
                                    FirebaseFirestore.getInstance().collection("tasks").document(Taskdataset.get(position).getTaskid())
                                            .set(Completed).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(view.getContext(), "Task Status Updated", Toast.LENGTH_SHORT).show();
                                                    viewHolder.deadlinegone.setVisibility(View.GONE);

                                                }
                                            });
                                    viewHolder.taskstatus.setTextColor(Color.parseColor("#7cfc00"));
                                    viewHolder.tick.setImageResource(R.drawable.baseline_check_24);
                                    viewHolder.taskstatus.setText("COMPLETED");
                                }
                                return false;
                            }
                        });

                        return false;
                    }
                });
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return Taskdataset.size();
    }
}