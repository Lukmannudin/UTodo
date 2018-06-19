package com.lukmannudin.assosiate.utodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Intent incoming = getIntent();
        final String date = incoming.getStringExtra("date");
        final EditText inputTodo = findViewById(R.id.edt_inputTodo);

        TextView ckdone = findViewById(R.id.tv_done);

        final DatabaseHandler db = new DatabaseHandler(this);

        ckdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               long validasiInsert  = db.insertData("21:00",inputTodo.getText().toString(),date);
               if (validasiInsert == -1){
                   Toast.makeText(getApplicationContext(), "unable to save", Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(getApplicationContext(),"Data saved",Toast.LENGTH_SHORT).show();
               }
               Intent intent = new Intent(AddTask.this,MainActivity.class);
               startActivity(intent);
            }
        });

    }
}
