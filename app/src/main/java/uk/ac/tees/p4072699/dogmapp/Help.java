package uk.ac.tees.p4072699.dogmapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help extends AppCompatActivity {
    Button home;
    Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        owner = (Owner) getIntent().getSerializableExtra("owner");

        home = (Button) findViewById(R.id.help_btn_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Home.class);
                i.putExtra("owner", owner);
                startActivity(i);
                setContentView(R.layout.activity_home);
                finish();
            }
        });
    }
}
