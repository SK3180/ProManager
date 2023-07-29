package com.sksingh.promanager;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sksingh.promanager.model.TaskModel;


public class AddTaskActivity extends AppCompatActivity {
    EditText Taskname;
    EditText TaskDisp;
    EditText TaskDead;
    FirebaseFirestore db;
    Button Save;
    ImageView tick;
    String TAG = "ProManager";
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
         db = FirebaseFirestore.getInstance();
        Taskname = findViewById(R.id.TaskName);
        TaskDisp = findViewById(R.id.TaskDis);
        TaskDead = findViewById(R.id.TaskDeadline);
        Save = findViewById(R.id.save);
        linearLayout = findViewById(R.id.addtask);


        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TaskName = Taskname.getText().toString().trim();
                String TaskDis = TaskDisp.getText().toString().trim();
                String TaskDeadline = TaskDead.getText().toString().trim();

                if (Taskname != null)
                {
                    TaskModel taskModel = new TaskModel("",TaskName,"PENDING",TaskDis,TaskDeadline, FirebaseAuth.getInstance().getUid());
                    db.collection("tasks").add(taskModel).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());

                                    findViewById(R.id.addtask).setVisibility(View.VISIBLE);
                                    findViewById(R.id.linearLayout5).setVisibility(View.GONE);
                                    findViewById(R.id.save).setVisibility(View.GONE);
                                    Intent back = new Intent(AddTaskActivity.this,SecondActivity.class);
                                    startActivity(back);

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}