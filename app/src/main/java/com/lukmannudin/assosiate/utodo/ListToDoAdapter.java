package com.lukmannudin.assosiate.utodo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ListToDoAdapter extends RecyclerView.Adapter<ListToDoAdapter.CategoryViewHolder> {
    private Context context;
    private ArrayList<Todo> listToDo;

    public ListToDoAdapter(Context context){
        this.context = context;
    }

    public ArrayList<Todo> getListToDo() {
        return listToDo;
    }

    public void setListToDo(ArrayList<Todo> listToDo){
        this.listToDo = listToDo;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvAt;
        TextView tvDescription;
        CheckBox CbToDoTask;
        public CategoryViewHolder(View itemView) {
            super(itemView);
          //  tvAt = itemView.findViewById(R.id.tv_toDoAt);
         //   tvDescription = itemView.findViewById(R.id.tv_toDoDesc);
             CbToDoTask = itemView.findViewById(R.id.cb_toDoTask);
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.todoitem,parent,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
      //  holder.tvAt.setText(String.valueOf(getListToDo().get(position).getToDoAt()));
       // holder.tvDescription.setText(getListToDo().get(position).getToDoDesc());
      //  holder.tvDescription.setText(getListToDo().get(position).getToDoDesc());
        holder.CbToDoTask.setText(getListToDo().get(position).getToDoDesc());
    }

    @Override
    public int getItemCount() {
        return getListToDo().size();
    }


}
