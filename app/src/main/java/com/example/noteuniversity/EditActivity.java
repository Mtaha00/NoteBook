package com.example.noteuniversity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class EditActivity extends AppCompatActivity {

    String title;
    String text;
    String date;
    EditText inputTitle;
    EditText inputText;
    TextView dateText;
    Button btnSave;
    Bundle bundle;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        inputTitle = findViewById(R.id.titleEdit_edit);
        inputText = findViewById(R.id.textEdit_edit);
        dateText=findViewById(R.id.textDate_edit);
        btnSave = findViewById(R.id.saveBTN_edit);
        dbHelper=new DBHelper(this);

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        date=mYear+"/"+mMonth+"/"+mDay;
        dateText.setText(date);
        if(getIntent().getExtras()!=null){
            bundle=getIntent().getExtras();
            title=bundle.getString("title");
            text=bundle.getString("text");

            inputTitle.setText(title);
            inputText.setText(text);
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputTitle.getText().toString().trim().isEmpty() && inputText.getText().toString().trim().isEmpty()){
                    Toast.makeText(EditActivity.this, "please file all blank", Toast.LENGTH_SHORT).show();
                }else{
                    if (bundle!=null){
                        dbHelper.updateNote(new Note(title,text,date),new Note
                                (inputTitle.getText().toString().trim(),inputText.getText().toString().trim(),date));
                    }else{
                        dbHelper.insertNote(new Note(inputTitle.getText().toString().trim(),inputText.getText().toString().trim(),date));
                    }
                    setResult(RESULT_OK);
                    finish();
                }

            }
        });

    }
}