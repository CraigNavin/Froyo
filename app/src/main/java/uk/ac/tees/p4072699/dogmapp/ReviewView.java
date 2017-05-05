package uk.ac.tees.p4072699.dogmapp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ReviewView extends AppCompatActivity {
    DatabaseHandler dh = new DatabaseHandler(this);
    Walk w;
    Owner owner;
    ImageButton p1, p2, p3, p4, p5;
    ArrayList<ImageButton> imgarray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_view);
        owner = (Owner) getIntent().getSerializableExtra("owner");
        w = (Walk) getIntent().getSerializableExtra("walk");
        final TextView name = (TextView) findViewById(R.id.textView_Revname);
        final TextView comm = (TextView) findViewById(R.id.textView_Review);
        final TextView dis = (TextView) findViewById(R.id.textView_dis);
        final TextView time = (TextView) findViewById(R.id.textView_time);
        final Button retur = (Button) findViewById(R.id.button_return);
        final Button edit = (Button) findViewById(R.id.button_edit);
        final Button remove = (Button) findViewById(R.id.button_remove);
        DecimalFormat df = new DecimalFormat("#.00");

        name.setText(w.getName());
        comm.setText(w.getComment());
        dis.setText(df.format(w.getLength()));
        time.setText(String.valueOf(w.getTime()));
        p1 = (ImageButton) findViewById(R.id.paw_1);
        p2 = (ImageButton) findViewById(R.id.paw_2);
        p3 = (ImageButton) findViewById(R.id.paw_3);
        p4 = (ImageButton) findViewById(R.id.paw_4);
        p5 = (ImageButton) findViewById(R.id.paw_5);

        imgarray.add(p1);
        imgarray.add(p2);
        imgarray.add(p3);
        imgarray.add(p4);
        imgarray.add(p5);

        for (int i=0; i== imgarray.size();i++){
            if (i >= w.getRating()){
                imgarray.get(i).setImageResource(R.drawable.selected);
            }
            else{
                imgarray.get(i).setImageResource(R.drawable.paw);
            }
        }

        retur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });

        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditWalk.class);
                i.putExtra("walk",w);
                startActivity(i);
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dh.removeWalk(w.getId());
                Intent intent = new Intent(getApplicationContext(), ReviewList.class);
                intent.putExtra("owner", dh.getOwnerHelper(owner));
                startActivity(intent);
            }
        });

    }
}
