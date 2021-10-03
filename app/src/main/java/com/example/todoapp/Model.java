package com.example.todoapp;

public class Model {
    private String task,description,id,date;
    public Model() {}
    public Model(String task, String description, String id, String date) {
        this.description = description;
        this.task = task;
        this.id = id;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
