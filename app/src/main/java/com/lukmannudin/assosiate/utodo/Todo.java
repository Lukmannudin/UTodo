package com.lukmannudin.assosiate.utodo;

class Todo {
    private String ToDoAt,ToDoDesc;
    private String ToDoDate;

    private String CbToDo;

    public String getToDoAt() {
        return ToDoAt;
    }

    public void setToDoAt(String toDoAt) {
        ToDoAt = toDoAt;
    }

    public String getToDoDesc() {
        return ToDoDesc;
    }

    public void setToDoDesc(String toDoDesc) {
        ToDoDesc = toDoDesc;
    }

    public String getToDoDate() {
        return ToDoDate;
    }

    public void setToDoDate(String toDoDate) {
        ToDoDate = toDoDate;
    }

    public String getCbToDo() {
        return CbToDo;
    }

    public void setCbToDo(String cbToDo) {
        CbToDo = cbToDo;
    }
}
