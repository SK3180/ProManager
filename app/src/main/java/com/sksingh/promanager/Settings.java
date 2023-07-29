package com.sksingh.promanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {
    Button Signout;
    TextView Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Signout = findViewById(R.id.Signout);
        Username = findViewById(R.id.Username);
        Username.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        Signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Settings.this, "SignOut Successfully", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(Settings.this,MainActivity.class);
                main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                Toast.makeText(Settings.this, "SignOut Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}