package uk.ac.tees.p4072699.dogmapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class DogList extends AppCompatActivity {
    DatabaseHandler dh = new DatabaseHandler(this);
    int selected = -1;
    String[] dogs = {};
    Integer[] dogsId = {};
    Owner owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        final Context con = this;
        final Button home = (Button) findViewById(R.id.button_home);
        final Button add = (Button) findViewById(R.id.button_add);
        final ImageButton set = (ImageButton) findViewById(R.id.imageButton_settings);
        final ListView listView = (ListView) findViewById(R.id.lv_dgs);
        owner = (Owner) getIntent().getSerializableExtra("owner");
        DecimalFormat df = new DecimalFormat("#.00");
        List<Dog> list = dh.getAllDogs(owner.getId());

        for (Dog dg : list) {
            dogs = Arrays.copyOf(dogs, dogs.length + 1);

            dogs[dogs.length - 1] = "Name: " + dg.getName() +"\nTotWalks: " + dg.getTotwalks() + "    TotDis: " + df.format(dg.getTotdistance()) + "\nOwner: " + dh.getOwnerHelper(owner).getName();

            dogsId = Arrays.copyOf(dogsId, dogsId.length + 1);
            dogsId[dogsId.length - 1] = dg.getId();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dogs);
        listView.setAdapter(adapter);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(con, Settings.class);
                i.putExtra("owner", dh.getOwnerHelper(owner));
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = dogsId[position];
                List<Dog> list = dh.getAllDogs(owner.getId());
                Intent i = new Intent(con, DogProfile.class);
                i.putExtra("dog",list.get(position));
                i.putExtra("owner",dh.getOwnerHelper(owner));
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, Home.class);
                intent.putExtra("owner", dh.getOwnerHelper(owner));
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(con, AddDogActivity.class);
                intent.putExtra("owner",dh.getOwnerHelper(owner));
                startActivity(intent);
            }
        });
    }
}