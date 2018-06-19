package com.lukmannudin.assosiate.utodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTodoList;
    private ArrayList<Todo> list,list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTodoList = findViewById(R.id.toDoList);
        rvTodoList.setHasFixedSize(true);

        final CalendarView calendar = findViewById(R.id.UTodoCalendar);
        final LinearLayout bigCalendar = findViewById(R.id.linearLayout);
        ImageView addTodo = findViewById(R.id.addImageView);

        calendar.setVisibility(View.GONE);

        bigCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bigCalendar.setVisibility(View.INVISIBLE);
                calendar.setVisibility(View.VISIBLE);
            }
        });

        final Calendar rightNow = Calendar.getInstance();


        final String dayRightNow = String.valueOf(rightNow.get(Calendar.DAY_OF_MONTH));
        final String monthRightNow = String.valueOf(rightNow.get(Calendar.MONTH)+1);
        final String yearRightNow = String.valueOf(rightNow.get(Calendar.YEAR));

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddTask.class);
                intent.putExtra("date", yearRightNow+"/"+monthRightNow+"/"+dayRightNow);
                startActivity(intent);
            }
        });


        DatabaseHandler db = new DatabaseHandler(this);

        list = new ArrayList<>();
        //list.addAll(ToDoData.getListData());

//        long cek = db.insertData("09:00","Buat UTodo","2012");

        list.addAll(db.readAllData());

        CheckBox cb = findViewById(R.id.cb_toDoTask);
      
        showRecyclerList();


    }

    private void showRecyclerList() {
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        ListToDoAdapter listToDoAdapter = new ListToDoAdapter(this);
        listToDoAdapter.setListToDo(list);
        rvTodoList.setAdapter(listToDoAdapter);

        ItemClickSupport.addTo(rvTodoList).setOnItemClickedListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedTodo(list.get(position));
            }
        });
    }

    private void showSelectedTodo(Todo todo){
        Toast.makeText(this, "Kamu memilih "+ todo.getToDoAt(), Toast.LENGTH_SHORT).show();
    }
}
