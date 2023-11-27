package com.chad.exams;

import java.util.ArrayList;
import java.util.List;

public class Exams {

    private List<ArrayList> list;


    public Exams() {}


    public Exams(List<ArrayList> list) {
        this.list = list;
    }

    public List<ArrayList> getList() {
        return list;
    }

    public void setList(List<ArrayList> list) {
        this.list = list;
    }
}
