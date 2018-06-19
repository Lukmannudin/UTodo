package com.lukmannudin.assosiate.utodo;

import java.util.ArrayList;

public class ToDoData {
    public static String [][] data = new String[][]{
            {"Beli kertas portofolio","21.00"},
            {"Nganterin mama ke pasar","06.00"},
            {"Nganterin adek ke sekolah","13.00"},
            {"Kerjain tugas basdat 2","18.00"},
            {"Donwload Linux Debian","21.00"}
    };

    public static ArrayList<Todo> getListData(){
        Todo todo = null;
        ArrayList<Todo> list = new ArrayList<>();
        for (int i=0;i<data.length;i++){
            todo = new Todo();
            todo.setToDoDesc(data[i][0]);
            todo.setToDoAt(data[i][1]);
            list.add(todo);
        }
        return list;
    }
}
