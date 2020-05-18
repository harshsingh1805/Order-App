package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.Adapters.ReciepeAdapter;
import com.example.sample.Models.ReciepeModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<ReciepeModel> list = new ArrayList<>();

        list.add(new ReciepeModel(R.drawable.food1, "Burger"));
        list.add(new ReciepeModel(R.drawable.food2, "Pizza"));
        list.add(new ReciepeModel(R.drawable.food3, "Hamburger"));
        list.add(new ReciepeModel(R.drawable.food4, "Indian Cuisine"));
        list.add(new ReciepeModel(R.drawable.food5, "Mix Vegetable"));
        list.add(new ReciepeModel(R.drawable.food6, "Pasta"));
        list.add(new ReciepeModel(R.drawable.food7, "Indian Tadka"));
        list.add(new ReciepeModel(R.drawable.food8, "Special Roll"));
        list.add(new ReciepeModel(R.drawable.food9, "Sandwich"));

        ReciepeAdapter adapter = new ReciepeAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case
                R.id.logout:

                break;
                default:

                    break;
        }

        return true;
    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
