package com.example.noteuniversity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.setOnItemClickListener, RecyclerAdapter.setOnDeleteItemListener {
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    DBHelper dbHelper;
    ImageView addBTN;
    private static final int EDITCONTACT_REQ_CODE = 105;
    private static final int ADDCONTACT_REQ_CODE = 106;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recyclerView_main);
        adapter = new RecyclerAdapter(this, dbHelper.getNote());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.onItemClickListener(this);
        adapter.onDeleteItemListener(this);

        addBTN=findViewById(R.id.add_main);
        addBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, ADDCONTACT_REQ_CODE);
            }
        });
    }

    @Override
    public void OnClickItem(int position, Note note) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("title", note.getTitle());
        bundle.putString("text", note.getText());
        intent.putExtras(bundle);
        startActivityForResult(intent, EDITCONTACT_REQ_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.updateList(dbHelper.getNote());
    }


    @Override
    public void OnDeleteItemListener(int position) {
        dbHelper.deleteNote(adapter.getNote(position));
    }

    @Override
    public void onBackPressed() {

    }

}