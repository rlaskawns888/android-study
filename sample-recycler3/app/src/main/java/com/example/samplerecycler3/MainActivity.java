package com.example.samplerecycler3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonAdapter();

        adapter.addItem(new Person("test1", "11111"));
        adapter.addItem(new Person("test2", "22222"));
        adapter.addItem(new Person("test3", "33333"));
        adapter.addItem(new Person("test1", "11111"));
        adapter.addItem(new Person("test2", "22222"));
        adapter.addItem(new Person("test3", "33333"));
        adapter.addItem(new Person("test1", "11111"));
        adapter.addItem(new Person("test2", "22222"));
        adapter.addItem(new Person("test3", "33333"));
        adapter.addItem(new Person("test1", "11111"));
        adapter.addItem(new Person("test2", "22222"));
        adapter.addItem(new Person("test3", "33333"));
        adapter.addItem(new Person("test1", "11111"));
        adapter.addItem(new Person("test2", "22222"));
        adapter.addItem(new Person("test3", "33333"));

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                Person item = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "selected! " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}