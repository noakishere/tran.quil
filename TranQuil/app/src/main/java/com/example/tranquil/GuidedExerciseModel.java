package com.example.tranquil;

public class GuidedExerciseModel {
    private String title;
    private String type;
    private String desc;
    private String time;

    private GuidedExerciseModel() {}

    private GuidedExerciseModel(String t, String ty, String d, String ti)
    {
        this.title = t;
        this.type = ty;
        this.desc = d;
        this.time = ti;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
